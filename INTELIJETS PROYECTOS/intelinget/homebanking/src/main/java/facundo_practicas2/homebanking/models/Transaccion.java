package facundo_practicas2.homebanking.models;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Transaccion {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO,generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private int id;
    private TransaccionTipo tipo;
    private double monto;
    private String descripcion;

//relacion con Cuentas
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cuenta_id")
    private Cuenta cuenta;

    @ElementCollection
    private List<Transaccion> transacciones = new ArrayList<>();

//-------------Getters y Setters----------------------//

 //----construcctores----//
    public Transaccion (){}

    public Transaccion(TransaccionTipo tipo, int monto, String descripcion){
        this.tipo = tipo;
        this.monto = monto;
        this.descripcion = descripcion;
    }

    //metodos


    public int getId() {
        return id;
    }

    public TransaccionTipo getTipo() {
        return tipo;
    }

    public void setTipo(TransaccionTipo tipo) {
        this.tipo = tipo;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Cuenta getCuenta() {
        return cuenta;
    }

    public List<Transaccion> getTransacciones() {
        return transacciones;
    }

    public void setCuenta(Cuenta cuenta) {
        this.cuenta = cuenta;
    }
}
