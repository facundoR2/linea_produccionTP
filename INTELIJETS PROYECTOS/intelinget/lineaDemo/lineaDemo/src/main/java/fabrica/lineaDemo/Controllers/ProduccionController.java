package fabrica.lineaDemo.Controllers;

import fabrica.lineaDemo.DTOS.ComponenteDTO;
import fabrica.lineaDemo.DTOS.InfoGeneralDTO;
import fabrica.lineaDemo.DTOS.UsuarioDTO;
import fabrica.lineaDemo.DTOS.ValeLectura;
import fabrica.lineaDemo.Models.ValeProduccionDetalle;
import fabrica.lineaDemo.Services.OrdenProduccionService;
import fabrica.lineaDemo.Services.ProduccionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/produccion")
public class ProduccionController {

    private final ProduccionService produccionService;
    private final OrdenProduccionService ordenProduccionService;

    public ProduccionController(ProduccionService produccionService,
                                OrdenProduccionService ordenProduccionService){

        this.produccionService = produccionService;
        this.ordenProduccionService = ordenProduccionService;
    }


    @PostMapping("/cerrar/{idValeProduccion}")
    public ResponseEntity<String> cerrarProduccion(@PathVariable Integer idValeProduccion){
        produccionService.cerrarProducto(idValeProduccion);
        return ResponseEntity.ok("Produccion cerrada correctamente y stock actualizado");
    }

    @GetMapping("/crear-Vale/{UsuarioDTO}")
    public ResponseEntity<ValeLectura> iniciarProduccion(@RequestParam UsuarioDTO usuarioDTO){
        //buscamos el codigo del producto a crear de la Orden de Produccion.
        String producto = ordenProduccionService.BuscarProducto();
        //generamos un valeLectura para el usuario ( debe ser el primer puesto)

        //agregar validacion para solo en caso de ser el primer puesto de la linea se genere el vale lectura.


        ValeLectura valenuevo = produccionService.iniciarValeLectura(usuarioDTO,producto);
        //retornamos el vale generado.
        return ResponseEntity.ok().body(valenuevo);
    }
    @GetMapping("/info-general")
    public ResponseEntity<InfoGeneralDTO>InfoRequest(){
        //devolvemos la informacion general.
        return ResponseEntity.ok(ordenProduccionService.darInfoGeneral());
    }

    @PostMapping("/registrar-componente")
    public ResponseEntity<?> registrarEscaneo(
            @RequestParam String codigoProducto,
            @RequestBody ComponenteDTO componente,
            @RequestParam Integer puesto,
            @RequestParam UsuarioDTO usuarioDTO
            ){
        try{
            ValeProduccionDetalle detalle = produccionService.registrarComponente(codigoProducto, componente,puesto, usuarioDTO);
            return ResponseEntity.ok(detalle);
        }catch (IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

    }
    @PostMapping("/consolidar")
    public ResponseEntity<String> consolidar(@RequestParam String codigoProducto){
        produccionService.consolidar(codigoProducto);
        return ResponseEntity.ok("Vale consolidado para el producto:"+ codigoProducto);
    }

    @PostMapping("/siguientePuesto")
    public ResponseEntity<Object> pasarAlSiguientePuesto(@RequestParam String codigoProducto, @RequestParam Integer puestoActual){
        try{
            //validar que no sea el ultimo puesto.

            boolean validar = produccionService.pasarSiguientePuesto(codigoProducto,puestoActual);
            if (validar){
                return ResponseEntity.ok("Producto:"+ codigoProducto +"paso al puesto"+(puestoActual+1));
            }

        }catch (IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("no se pudo pasar el producto al siguiente puesto");

    }




   // @GetMapping("/pendientes")
   // public ResponseEntity<?> ConsultarPendientes(@RequestParam Integer puestoActual){

   // }

}
