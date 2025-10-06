package fabrica.lineaDemo.Repositorys;

import fabrica.lineaDemo.Models.ValeProduccion;
import fabrica.lineaDemo.Models.ValeProduccionDetalle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ValeProduccionDetalleRepository extends JpaRepository<ValeProduccionDetalle,Long> {


    //buscar registros con el mismo ValeProduccion.
    List<ValeProduccionDetalle> findByValeProduccion(ValeProduccion valeProduccion);


    //para buscar los componentes completos del puesto anterior.
    List<ValeProduccionDetalle> findByCodigoProductoAndPuestoAndEstado(
            String codigoProducto,Integer puesto, Integer estado
    );


    //buscar vales de un puesto con cierto estado ( 1= completado, 2 = en proceso, 3= pendiente , 5= error)
    List<ValeProduccionDetalle> findByPuestoAndEstado(Integer idpuesto,Integer estado);

    List<ValeProduccionDetalle> findByEstadoAndPuesto(Integer estado, Integer puesto);

    //comprueba si existe un registro con el idUsuario y CodigoProducto(final) dados.
    boolean existsByUsuario_IdUsuarioAndCodigoProducto (Integer idUsuario, String codigoProducto);

    boolean existsByComponente_Codigo(String codigoProducto);

    List<ValeProduccionDetalle> findByCodigoProductoAndPuesto(String codigoProducto, Integer puesto);

    List<ValeProduccionDetalle> findByCodigoProducto(String codigoProducto);

    Optional<ValeProduccionDetalle> findByCodigoProductoAndComponente_Codigo(String codigoProducto, String idComponente);




}
