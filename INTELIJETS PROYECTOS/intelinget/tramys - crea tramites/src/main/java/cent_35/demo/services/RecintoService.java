package cent_35.demo.services;

import cent_35.demo.Dtos.BusquedaRecintoDTO;
import cent_35.demo.Dtos.CrearRecintoDTO;
import cent_35.demo.Dtos.RecintoDTO;
import cent_35.demo.Models.Recinto;
import cent_35.demo.repositorys.RecintoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RecintoService {

    @Autowired
    private RecintoRepository recintoRepository;



    public Recinto CrearRecinto(CrearRecintoDTO crearRecintoDTO){



        Recinto recinto = new Recinto(crearRecintoDTO.getLat(),crearRecintoDTO.getLng(),crearRecintoDTO.getNombre(),crearRecintoDTO.getDireccion(),crearRecintoDTO.gethAtencion(),crearRecintoDTO.gethCierre());
        recintoRepository.save(recinto);
        return recinto;
    }

    public List<Recinto> obtenerRecintosAleatorios(int cantidad){
        return recintoRepository.findRandomRecintos().stream().limit(cantidad).collect(Collectors.toList());
    }

    public List<BusquedaRecintoDTO> buscarRecintosPorNombre(String nombre){
        List<Recinto> recintos = recintoRepository.findBynombreContainingIgnoreCase(nombre);

        return recintos.stream().map(recinto ->new BusquedaRecintoDTO(recinto.getNombre(),recinto.getDireccion())).collect(Collectors.toList());
    }

    public void borrarRecintoLogicamente (int id,String usuario){
        recintoRepository.desactivarRecinto(id);
    }
}
