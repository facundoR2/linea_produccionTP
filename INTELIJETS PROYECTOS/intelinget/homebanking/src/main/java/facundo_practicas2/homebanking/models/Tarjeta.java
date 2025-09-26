package facundo_practicas2.homebanking.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
public class Tarjeta {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private int id;

    private String numero;

    private String nombreCliente;

    private LocalDate fechaInicio;
    private LocalDate fechaVencimiento;
    private String apellidoCliente;
    private TarjetaColor color;
    private TarjetaTipo tipo;
    private String cvv;

//    relacion: Varias tarjetas tienen un cliente.
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;


    public Tarjeta (){}

    public Tarjeta(Cliente cliente,String numero, Integer Fvencimiento,TarjetaTipo tipo, TarjetaColor color,String cvv){
        this.nombreCliente = cliente.getNombre();
        this.apellidoCliente = cliente.getApellido();
        this.fechaInicio =LocalDate.now();
        this.fechaVencimiento = LocalDate.now().plusYears(Fvencimiento);
        this.tipo = tipo;
        this.color = color;
        this.cvv = cvv;

    }


    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(LocalDate fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public String getApellidoCliente() {
        return apellidoCliente;
    }

    public void setApellidoCliente(String apellidoCliente) {
        this.apellidoCliente = apellidoCliente;
    }

    public TarjetaColor getColor() {
        return color;
    }

    public void setColor(TarjetaColor color) {
        this.color = color;
    }

    public TarjetaTipo getTipo() {
        return tipo;
    }

    public void setTipo(TarjetaTipo tipo) {
        this.tipo = tipo;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }



}
