package cent_35.demo.services;

import cent_35.demo.Dtos.BusquedaRecintoDTO;
import cent_35.demo.Dtos.RecintoDTO;
import cent_35.demo.Models.Recinto;
import cent_35.demo.repositorys.RecintoRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RecintoService {
    private final RecintoRepository recintoRepository;

    public RecintoService(RecintoRepository recintoRepository){
        this.recintoRepository = recintoRepository;
    }

    public List<Recinto> obtenerRecintosAleatorios(int cantidad){
        return recintoRepository.findRandomRecintos().stream().limit(cantidad).collect(Collectors.toList());
    }

    public List<BusquedaRecintoDTO> buscarRecintosPorNombre(String nombre){
        List<Recinto> recintos = recintoRepository.findBynombreContainingIgnoreCase(nombre);

        return recintos.stream().map(recinto ->new BusquedaRecintoDTO(recinto.getNombre(),recinto.getDireccion())).collect(Collectors.toList());
    }

    public Recinto buscarRecintoPorNombre(String nombreRecinto){
        return recintoRepository.findBynombre(nombreRecinto);

    }
}
