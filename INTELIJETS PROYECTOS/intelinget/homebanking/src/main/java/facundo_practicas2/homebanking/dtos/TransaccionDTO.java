package facundo_practicas2.homebanking.dtos;

import facundo_practicas2.homebanking.models.Transaccion;
import facundo_practicas2.homebanking.models.TransaccionTipo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class TransaccionDTO {
    private int id;
    private TransaccionTipo tipo;
    private double monto;
    private String descripcion;

    private List<Transaccion> transacciones = new ArrayList<>();



    public TransaccionDTO(Transaccion transaccion){
        this.id = transaccion.getId();
        this.descripcion = transaccion.getDescripcion();
        this.monto = transaccion.getMonto();
        this.tipo = transaccion.getTipo();
        this.transacciones = transaccion.getTransacciones();
    }

}
