package cent_35.demo.services;

import cent_35.demo.Dtos.PasoDTO;
import cent_35.demo.Dtos.TramiteDTO;
import cent_35.demo.Models.Tramite;
import cent_35.demo.Models.TramitePaso;
import cent_35.demo.repositorys.RecintoRepository;
import cent_35.demo.repositorys.TramitePasoRepository;
import cent_35.demo.repositorys.TramiteRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class TramiteService {

    private final TramiteRepository tramiteRepository;

    private final TramitePasoRepository tramitePasoRepository;


    public TramiteService(TramiteRepository tramiteRepository, TramitePasoRepository tramitePasoRepository){
        this.tramiteRepository = tramiteRepository;
        this.tramitePasoRepository = tramitePasoRepository;
    }

    public List<TramiteDTO> obtenerTramitesPorNombreRecinto(String nombreRecinto){
        List<Tramite> tramites = tramiteRepository.findByRecintoNombre(nombreRecinto);

        return tramites.stream()
                .map(tramite -> {
                    // creo una array  de tramitePasos y traigo los pasos ordenados del tramite.
                    List<TramitePaso> tramitePasos = tramitePasoRepository.findByTramiteOrderByOrdenAsc(tramite);
                    //creo una array de PasoDto y voy agregando cada paso a la misma.
                    List<PasoDTO> pasosDTO = tramitePasos.stream()
                            .map(tramitePaso -> new PasoDTO(tramitePaso.getPaso().getNombre(),tramitePaso.getPaso().getDescripcion()))
                            .collect(Collectors.toList());
                    //devuelvo un TramiteDto con cada paso
                    return new TramiteDTO(
                            pasosDTO,
                            tramite.getNombre(),
                            tramite.getRecinto().getNombre()
                    );
                })
                .collect(Collectors.toList());

    }
    //Servicio para buscar un tramite solo( por nombre)
    public TramiteDTO BuscarTramite(String nombreTramite){

        Tramite trm = new Tramite();
        try{
            //validamos que no este vacio o sea nulo
            if( nombreTramite == null || nombreTramite.trim().isEmpty()){
                throw new Exception();

            }
            trm = tramiteRepository.findByNombre(nombreTramite);
            if (trm != null){
                List<TramitePaso> tramitePasos = tramitePasoRepository.findByTramiteOrderByOrdenAsc(trm);

                List<PasoDTO> pasoDTOS = tramitePasos.stream()
                        .map(tramitePaso -> new PasoDTO(tramitePaso.getPaso().getNombre(),tramitePaso.getPaso().getDescripcion()))
                        .collect(Collectors.toList());

                //devuelve un TramiteDto con el tramite encontrado.
                return new TramiteDTO(
                        pasoDTOS,
                        trm.getNombre(),
                        trm.getRecinto().getNombre());
            }

        }catch (Exception e){
            System.out.println("Error, causa probable: "+ e.getCause());
        }

        //return por defecto en caso de que no haya encontrado un tramite o haya un excepcion.
        return new TramiteDTO();





    };
}
