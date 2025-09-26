package facundo_practicas2.homebanking.dtos;

import facundo_practicas2.homebanking.models.Cuenta;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class CuentasDTO {
    private int id;
    private String numero;
    private LocalDate fechaCreacion;
    private double saldo;
    private Set<TransaccionDTO> transacciones = new HashSet<>();


    public CuentasDTO(Cuenta cuenta){
        this.id = cuenta.getId();
        this.saldo = cuenta.getSaldo();
        this.numero = cuenta.getNumero();
        this.transacciones = cuenta.getTransacciones().stream().map(transaccion -> new TransaccionDTO(transaccion)).collect(Collectors.toSet());
    }

}
