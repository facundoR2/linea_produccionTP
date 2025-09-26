package facundo_practicas2.homebanking.dtos;

import facundo_practicas2.homebanking.models.Cliente;
import facundo_practicas2.homebanking.models.Cuenta;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class ClienteDTO {
    private int id;
    private String nombre;
    private String apellido;
    private String correo;
    private Set<CuentasDTO> cuentas = new HashSet<>();
    private Set<TarjetaDTO> tarjetas = new HashSet<>();




    public ClienteDTO(Cliente cliente){
        this.id = cliente.getId();
        this.nombre = cliente.getNombre();
        this.apellido = cliente.getApellido();
        this.correo = cliente.getCorreo();
        this.cuentas = cliente.getCuentas().stream().map(cuenta -> new CuentasDTO(cuenta)).collect(Collectors.toSet());
        this.tarjetas = cliente.getTarjetas().stream().map(tarjeta -> new TarjetaDTO(tarjeta)).collect(Collectors.toSet());
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getCorreo() {
        return correo;
    }

    public Set<CuentasDTO> getCuentas() {
        return cuentas;
    }
    public Set<TarjetaDTO> getTarjetas() {
        return tarjetas;
    }
}
