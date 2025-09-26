package facundo_practicas2.homebanking.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Prestamo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private int id;
    private String nombre;
    private double montoMax;
    @ElementCollection
    private List<Integer> cuotas;

    //
    @OneToMany(mappedBy = "prestamo", fetch = FetchType.EAGER)
    Set<ClientePrestamo> clientePrestamos = new HashSet<>();

    public Prestamo(){}

    public Prestamo( String nombre, double montoMax, List<Integer> cuotas){
        this.nombre = nombre;
        this.montoMax = montoMax;
        this.cuotas =  cuotas;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getMontoMax() {
        return montoMax;
    }

    public void setMontoMax(double montoMax) {
        this.montoMax = montoMax;
    }

    public List<Integer> getCuotas() {
        return cuotas;
    }

    public void setCuotas(List<Integer> cuotas) {
        this.cuotas = cuotas;
    }

    public void addClientePrestamo(ClientePrestamo clientePrestamo){
        clientePrestamo.setPrestamo(this); // agrega un Prestamo
        clientePrestamos.add(clientePrestamo);//agregamos a la lista de prestamos el clienteprestamo

    }
}
