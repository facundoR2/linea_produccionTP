package facundo_practicas2.homebanking.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Cuenta {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO,generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private int id;
    private String numero;
    private LocalDate fechaCreacion;
    //private LocalDate fechaBaja;
    //agregar Estado boolean;
    private double saldo;
    //relacion con Cliente(1:n)
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;




    //relacion con Transaccion(1:n)
    @OneToMany(mappedBy = "cuenta", fetch = FetchType.EAGER)
    Set<Transaccion> transacciones= new HashSet<>();

    //-----Contructores---//
    public Cuenta(){}

    public Cuenta(String numero, double saldo) {
        this.numero = numero;
        fechaCreacion = LocalDate.now();
        this.saldo = saldo;
    }

    //----Metodos--//

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Set<Transaccion> getTransacciones() {
        return transacciones;
    }

    public void  addTransaccion(Transaccion transaccion) {
        transaccion.setCuenta(this);
        transacciones.add(transaccion);
    }



    public int getId() {
        return id;
    }
    public String getNumero() {
        return numero;
    }
    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }
    public double getSaldo() {
        return saldo;
    }


}
