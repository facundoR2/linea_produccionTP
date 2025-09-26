package fabrica.lineaDemo.Services;

import fabrica.lineaDemo.Models.Producto;
import fabrica.lineaDemo.Repositorys.ProductoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoService {

    private final ProductoRepository productoRepository;


    public ProductoService(ProductoRepository productoRepository){
        this.productoRepository = productoRepository;
    }

    public List<Producto> listarProductos(){
        return productoRepository.findAll();
    }

    public Producto obtenerProducto(Integer id){
        return productoRepository.findById(id).orElse(null);
    }

    public Producto guardarProducto(Producto producto){
        return productoRepository.save(producto);
    }

    public void eliminarProducto(Integer id){
        productoRepository.deleteById(id);
    }


    public List<Producto> buscarporNombre(String nombre){
        return productoRepository.findByNombreContainingIgnoreCase(nombre);
    }

}
