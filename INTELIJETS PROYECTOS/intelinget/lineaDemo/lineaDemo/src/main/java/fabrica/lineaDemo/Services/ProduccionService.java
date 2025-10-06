package fabrica.lineaDemo.Services;

import fabrica.lineaDemo.DTOS.ComponenteDTO;
import fabrica.lineaDemo.DTOS.UsuarioDTO;
import fabrica.lineaDemo.DTOS.ValeLectura;
import fabrica.lineaDemo.Models.*;
import fabrica.lineaDemo.Repositorys.*;
import jakarta.transaction.Transactional;
import org.apache.naming.java.javaURLContextFactory;
import org.apache.tomcat.util.bcel.Const;
import org.springframework.data.relational.core.sql.In;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Service
public class ProduccionService {


    private final ValeProduccionDetalleRepository detalleRepository;


    private final ValeProduccionRepository produccionRepository;
    private final ProductoRepository productoRepository;

    private final OrdenProduccionDetallesRepository ordenProduccionDetallesRepository;

    private final OrdenProduccionRepository ordenPRepository;

    private final FormulaPuestosRepository formupuestorepo;

    private final UsuarioRepository usuarioRepository;
    private final CodigoProduccionService codigoService;

    public ProduccionService(OrdenProduccionRepository ordenPRepository ,OrdenProduccionDetallesRepository ordenProduccionDetallesRepository ,FormulaPuestosRepository formupuestorepo, UsuarioRepository usuarioRepository,CodigoProduccionService codigoService, ValeProduccionDetalleRepository detalleRepository, ValeProduccionRepository produccionRepository, ProductoRepository productoRepository) {

        this.detalleRepository = detalleRepository;
        this.produccionRepository = produccionRepository;
        this.productoRepository = productoRepository;
        this.codigoService = codigoService;
        this.usuarioRepository = usuarioRepository;
        this.formupuestorepo = formupuestorepo;
        this.ordenProduccionDetallesRepository = ordenProduccionDetallesRepository;
        this.ordenPRepository = ordenPRepository;

    }


    //region:funciones sencillas.

    public List<ComponenteDTO> convertirADTO(List<Producto> componentes){

        List<ComponenteDTO> dtos = new ArrayList<>();
        for (Producto com : componentes){
            ComponenteDTO componenteDTO = new ComponenteDTO(com.getNombre(),com.getCodigo(),com.getCantidad());
            dtos.add(componenteDTO);
        }
        return dtos;
    }

    //fin region: funciones sencillas.

    public ValeLectura iniciarValeLectura(UsuarioDTO usuario, String codigoProducto){
        //generamos el codigo.
        String codigo = codigoService.generarCodigo(codigoProducto);

        //creamos un vale
        ValeLectura vale = new ValeLectura();
        vale.setCodigoProducto(codigo);
        vale.setUsuario(usuario);
        vale.setPuestoActual(1); // colocamos el puesto donde se inicia.
        vale.setEstado("EN_PROCESO");

        return vale;

    }

    //por cada componente registrado, se carga un valeDetalle.
    public ValeProduccionDetalle  registrarComponente(String codigoProducto, ComponenteDTO componente, Integer puesto,UsuarioDTO usuarioDTO){

        //paso 1 :buscamos el valeProduccionDetalle con ese usuario y ese codigo producto final.
        if(detalleRepository.existsByUsuario_IdUsuarioAndCodigoProducto(usuarioDTO.getId(),codigoProducto)){
            //validar duplicado si el componente ya fue cargado.
            detalleRepository.findByCodigoProductoAndComponente_Codigo(codigoProducto,componente.getCodigoProducto()
            ).ifPresent(existing ->{
                throw new IllegalArgumentException("El componente ya fue registrado para este producto");
            });

        }
        //si no existe, registrar el nuevo valeDetalle.



        ValeProduccionDetalle nuevodetalle = new ValeProduccionDetalle();
        nuevodetalle.setCodigoProducto(codigoProducto);
        //buscamos el componente por el codigo.
        nuevodetalle.setComponente(productoRepository.findByCodigo(componente.getCodigoProducto()).orElseThrow(()-> new IllegalArgumentException("no se encontro el componente con el codigo:"+componente.getCodigoProducto())));
        //buscamos el usuario por el id del usuarioDTO.
        nuevodetalle.setUsuario(usuarioRepository.findById(usuarioDTO.getId()).orElseThrow(()->new IllegalArgumentException("No se encontro un usuario con el id:"+usuarioDTO.getId())));
        nuevodetalle.setPuesto(puesto);

        nuevodetalle.setEstado(2); // 1= completado; 2 = en_proceso; 5= error;
        return detalleRepository.save(nuevodetalle);//guardamos el detalle.

    }

    //cuando pasa de puesto, se consolida los componentes anexados al componente padre.
    public void consolidar(String codigoProducto){
        List<ValeProduccionDetalle> detalles = detalleRepository.findByCodigoProducto(codigoProducto);
        for (ValeProduccionDetalle detalle : detalles){
            detalle.setEstado(1); //indicamos que esta completo.
        }
        detalleRepository.saveAll(detalles); //guardamos la modificacion.
    }


    public boolean verificarExistencia(String codigoProducto){
        return detalleRepository.existsByComponente_Codigo(codigoProducto);
    }



    //proceso para cerrar el ciclo del producto, sumando 1 a valeProduccion y restando lo consumido.
    @Transactional //agregando este estereotipo, si algo falla, hace rollBack.
    public void cerrarProducto(Integer idValeProduccion) {

        //paso 1 : obtener el vale Produccion en proceso.
        ValeProduccion valeProduccion = produccionRepository.findById(idValeProduccion).orElseThrow(() -> new IllegalStateException("No existe el vale de produccion"));

        if (valeProduccion.getEstado() !=2){ //2 = en proceso.
            throw new IllegalStateException("El vale no esta en proceso");
        }

        //paso 2: traer todos los valeDetalle asociados.
        List<ValeProduccionDetalle> detalles = detalleRepository.findByValeProduccion(valeProduccion);
        if (detalles.isEmpty()){
            throw new IllegalStateException("No hay componentes registrados para este vale");
        }

        //paso 3: descontar los componentes del inventario.
        for(ValeProduccionDetalle detalle : detalles){
            Producto componente = detalle.getComponente();

            Producto inventario = productoRepository.findByCodigo(componente.getCodigo()).orElseThrow(() -> new IllegalStateException("No se encontro el inventario para el componente: "+componente.getCodigo()));

            //retamos la cantidad usada.

            Integer cantidadUsada = componente.getCantidad();
            inventario.setCantidad(inventario.getCantidad() - cantidadUsada);
            productoRepository.save(inventario);
        }

        //paso 4 : marcar el ValeProduccion como completado.
        valeProduccion.setEstado(1); //1= completado.
        produccionRepository.save(valeProduccion);

        //paso 5: actualizar la cantidad producida en la Orden.

        OrdenProduccionDetalles detalleOP = ordenProduccionDetallesRepository.findByProducto(valeProduccion.getOrdenProduccion().getProducto());

        detalleOP.setCantProducida(detalleOP.getCantProducida() + 1);
        ordenProduccionDetallesRepository.save(detalleOP);

        // paso 6: verificar si aun hay productos pendientes.
        if(detalleOP.getCantProducida() < detalleOP.getCantPrevista()){
            //creamos un nuevo ValeProduccion.
            ValeProduccion nuevoVale = new ValeProduccion();

            //buscamos la OrdenProduccion.


            nuevoVale.setOrdenProduccion(detalles.get(0).getValeProduccion().getOrdenProduccion());
            nuevoVale.setEstado(2);
            nuevoVale.setFormula(detalles.get(0).getValeProduccion().getFormula());
            nuevoVale.setFechaRegistro(LocalDate.now());

        }else {
            OrdenProduccion orden = detalles.get(0).getValeProduccion().getOrdenProduccion();

            orden.setEstado("COMPLETADA");
            ordenPRepository.save(orden);
        }


    }

    public boolean noEsUltimoPuesto(String codigoFormula, Integer puestoActual){

        //paso 1 : obtenemos todos los formulaPuesto de la formula.
        List<FormulaPuesto> puestosFormula = formupuestorepo.findByFormulaDetalle_Formula_CodigoFormula(codigoFormula);


        if (puestosFormula.isEmpty()){
            throw new RuntimeException("No hay puestos definidos para la formula "+ codigoFormula);
        }

        //paso 2 : determinar el maximo idP(el ultimoPuesto)
        Integer maxOrden = puestosFormula.stream()
                .map(FormulaPuesto::getOrden)
                .max(Integer::compareTo)
                .orElseThrow();
        return puestoActual < maxOrden; //
    }

    public Boolean todosComponentesRegistrados(List<ValeProduccionDetalle> detallesRegistrados, Integer puestoActual){

        //paso 1 : traer todos los los componentes necesarios.

        //de los detalles, traemos el valeProduccion que nos indicara la formula.

        ValeProduccion valeProduccion = detallesRegistrados.getFirst().getValeProduccion();


        //paso 2 :obtenemos todos los FormulaPuesto para la formula (codigoformula) y el puesto actual.

        List<FormulaPuesto> formulaPuestos = formupuestorepo.findByFormulaDetalle_Formula_CodigoFormulaAndPuesto_IdP(valeProduccion.getFormula().getCodigoFormula(),puestoActual);

        //paso 3: extraemos todos los codigos de los componentes necesarios.
        List<FormulaDetalle>  componentesNecesarios = new ArrayList<>();
        for (FormulaPuesto fp : formulaPuestos){
            componentesNecesarios.add(fp.getFormulaDetalle());
        }
        List<Producto> registrados = new ArrayList<>();
        for (FormulaDetalle fd : componentesNecesarios){
            registrados.add(fd.getComponente());

        }

        //paso 4:filtramos si todos los componentes(por los nombres) segun formulaPuesto estanRegistrados.


        return formulaPuestos.stream()
                .allMatch(fp -> registrados.contains(fp.getFormulaDetalle().getComponente().getNombre()));

    }




    //proceso para cargar el proximo puesto con el producto y sus componentes ya anexados.
    public boolean pasarSiguientePuesto (String codigoProductofinal, Integer puestoActual){

        //paso 1: validar Componentes:


        //paso 1a:
        //obtenemos todos los ValeProduccionDetalle generados por ese puesto.

        List<ValeProduccionDetalle> detallesRegistrados = detalleRepository.findByCodigoProductoAndPuesto(codigoProductofinal,puestoActual);

        //llamamos a la funcion.

        if(!todosComponentesRegistrados(detallesRegistrados, puestoActual)){
            throw new IllegalArgumentException("Faltan Componentes por registrar para el puesto:"+puestoActual);
        }else {
            System.out.println("todos los componentes del puesto estan Registrados en este Vale");
        }

        //paso 2: validamos que no sea el puesto final.

        boolean noUltimo = noEsUltimoPuesto(detallesRegistrados.getFirst().getValeProduccion().getFormula().getCodigoFormula(),puestoActual);

        if (noUltimo){
            System.out.println("El puesto actual No es el ultimo.");
        }else {
            System.out.println("Este es el ultimo Puesto de la formula");
            cerrarProducto(detallesRegistrados.get(0).getValeProduccion().getIdVale());


        }


        //buscamos todos los detalles del producto en el puesto actual.

        List<ValeProduccionDetalle> detalles = detalleRepository.findByCodigoProductoAndPuesto(codigoProductofinal, puestoActual);
        if (detalles.isEmpty()){
            throw new IllegalArgumentException("No se encontro el producto para el puesto"+puestoActual);
        }

        //generamos un valeDetalle pendiente para que el siguiente puesto consulte.

        ValeProduccionDetalle detalleproximoPuesto = new ValeProduccionDetalle();
        detalleproximoPuesto.setCodigoProducto(codigoProductofinal);
        detalleproximoPuesto.setPuesto(puestoActual+1);
        detalleproximoPuesto.setEstado(2);
        detalleproximoPuesto.setValeProduccion(detalles.getFirst().getValeProduccion());
        detalleRepository.save(detalleproximoPuesto);

        return true;
        //creamos nuevos nuevo vale lectura para el siguiente puesto con lo ya agregado.

        // ValeLectura  nuevaLectura = new ValeLectura();
        // for (ValeProduccionDetalle detalle: detalles){
        //     nuevaLectura.setCodigoProducto(codigoProductofinal);
        //     nuevaLectura.setPuestoActual(puestoActual+1);
        //     nuevaLectura.setEstado("EN PROCESO.");
        //     nuevaLectura.setTiempo(LocalDateTime.now());
        //
        //     List<Producto> componentes = detalles.stream().map(com -> com.getComponente()).collect(Collectors.toList());
        //
        //     //mapeamos los componentes a ComponenteDTO.
        //     List<ComponenteDTO> componenteDTOS = new ArrayList<>();
        //     for(Producto comp: componentes){
        //         ComponenteDTO dto = new ComponenteDTO(comp.getNombre(),comp.getCodigo(),comp.getCantidad());
        //         componenteDTOS.add(dto);
        //     }
        //     //mientras el sistema este levantado, existira en memoria una lectura cargada para el proximo puesto.
        //     nuevaLectura.setComponentes(componenteDTOS);
        //
        //     //en otra version, agregamos el retorno en memoria de sistema para enviarselo a al proximo puesto.
        //     //en esta version
        //
        // }
        // return nuevaLectura ;

    }



    //proceso para consultar pendientes para puestos.

    public ValeLectura ConsultarPendientes ( String codigoProducto,Integer puestoActual,UsuarioDTO usuarioDTO){
        //comprobamos si existe un registro. pendiente existente para ese puesto.
        List<ValeProduccionDetalle> detalles = detalleRepository.findByEstadoAndPuesto(2,puestoActual);
        if(detalles.isEmpty()){
            throw new IllegalArgumentException("No hay vales pendientes para este puesto");
        }



        //paso 2: obtener los datos de referencia del primero.



        ValeProduccionDetalle detallebase = detalles.getFirst();
        ValeProduccion valeProduccion = detallebase.getValeProduccion();
        OrdenProduccion orden = valeProduccion.getOrdenProduccion();
        String codigoFormula = orden.getFormula().getCodigoFormula();
        String codigoProductoAHacer = orden.getProducto().getCodigo();


        //paso 3: buscar el orden actual.

        Integer ordenActual = formupuestorepo.findByPuestoIdP(puestoActual).stream().findFirst().map(FormulaPuesto::getOrden).orElseThrow(() -> new IllegalArgumentException("El puesto actual no tiene un orden definido"));


        //validar si no es el primer puesto.
        if(ordenActual == 1){
            throw  new IllegalStateException("El primer puesto no tiene puesto anterior");
        }

        //buscarmos puesto anterior por formula y orden.

        int ordenAnterior = ordenActual - 1;
        FormulaPuesto formulaAnterior = formupuestorepo.findByFormulaDetalle_Formula_CodigoFormulaAndOrden(codigoFormula,ordenAnterior).orElseThrow(() -> new IllegalStateException("No se encontro el puesto anterior"));

        Puesto puestoAnterior = formulaAnterior.getPuesto();


        //traer los componentes completos del producto y puesto anterior.

        List<ValeProduccionDetalle> componentesCompletos = detalleRepository.findByCodigoProductoAndPuestoAndEstado(codigoProducto,puestoAnterior.getIdP(),1);

        if(componentesCompletos.isEmpty()){
            throw new IllegalStateException("no hay componentes completos del puesto anterior para este producto");
        }

        //generamos el nuevo valeLectura.

        ValeLectura nuevaLectura = new ValeLectura();

        nuevaLectura.setPuestoActual(puestoActual);
        nuevaLectura.setUsuario(usuarioDTO);
        nuevaLectura.setTiempo(LocalDateTime.now());
        nuevaLectura.setCodigoProducto(codigoProducto);

        List<Producto> componentes = new ArrayList<>();
        for (ValeProduccionDetalle componente : componentesCompletos){
            componentes.add(componente.getComponente());
        }
        //agregamos los componentes del puesto anterior al la nueva Lectura.

        nuevaLectura.setComponentes(convertirADTO(componentes));


        return nuevaLectura;

    }


}
