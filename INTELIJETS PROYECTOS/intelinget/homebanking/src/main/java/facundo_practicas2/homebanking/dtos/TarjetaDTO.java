package facundo_practicas2.homebanking.dtos;

import facundo_practicas2.homebanking.models.Tarjeta;

import java.util.HashSet;
import java.util.Set;

public class TarjetaDTO {
    private int id;
    private String nombreCliente;
    private String apellidoCliente;
    private String numeroTarjeta;


    public TarjetaDTO(Tarjeta tarjeta){
        this.nombreCliente = tarjeta.getNombreCliente();
        this.apellidoCliente = tarjeta.getApellidoCliente();
        this.numeroTarjeta = tarjeta.getNumero();
    }

}
