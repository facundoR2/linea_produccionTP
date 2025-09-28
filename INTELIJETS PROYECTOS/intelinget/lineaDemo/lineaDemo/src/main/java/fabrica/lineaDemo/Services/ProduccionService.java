package fabrica.lineaDemo.Services;

import fabrica.lineaDemo.Repositorys.ProductoRepository;
import fabrica.lineaDemo.Repositorys.ValeProduccionDetalleRepository;
import fabrica.lineaDemo.Repositorys.ValeProduccionRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ProduccionService {


    private final ValeProduccionDetalleRepository detalleRepository;
    private final ValeProduccionRepository produccionRepository;
    private final ProductoRepository productoRepository;

    public ProduccionService( ValeProduccionDetalleRepository detalleRepository, ValeProduccionRepository produccionRepository, ProductoRepository productoRepository) {

        this.detalleRepository = detalleRepository;
        this.produccionRepository = produccionRepository;
        this.productoRepository = productoRepository;
    }

    public void cerrarProduccion(Integer idValeProduccion) {
    }
}
