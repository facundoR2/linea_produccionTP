package facundo_practicas2.homebanking.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private int id;
    private String nombre;
    private String apellido;
    //agregar estado;

    private String correo;
    private String password;
    private Roles rol;

//    atributo tipo HashSet de Cuentas (donde se guardan las cuentas)
    @OneToMany(mappedBy = "cliente", fetch = FetchType.EAGER)
    Set<Cuenta> cuentas = new HashSet<>();

    //    atributo tipo HashSet de Tarjetas (donde se guardan las tarjetas)
    @OneToMany(mappedBy = "cliente", fetch = FetchType.EAGER)
    Set<Tarjeta> tarjetas = new HashSet<>();



    //    atributo tipo HashSet de Prestamos (donde se guardan los prestamos)
    @OneToMany(mappedBy = "cliente", fetch = FetchType.EAGER)
    Set<ClientePrestamo> clientePrestamos = new HashSet<>();




    public Cliente(){}
    public Cliente(String nombre, String apellido, String correo, String password) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.password = password;
    }

    public Roles getRol() {
        return rol;
    }

    public void setRol(Roles rol) {
        this.rol = rol;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Set<Cuenta> getCuentas() {
        return cuentas;
    }

    public Set<Tarjeta> getTarjetas(){return tarjetas;}

    public void setTarjetas(Set<Tarjeta> tarjetas){this.tarjetas = tarjetas;}

    public void setCuentas(Set<Cuenta> cuentas) {
        this.cuentas = cuentas;
    }

    public void  addCuenta(Cuenta cuenta) {
        cuenta.setCliente(this);
        cuentas.add(cuenta);
    }
    //----------------metodos para tarjetas
    public void addTarjeta(Tarjeta tarjeta){
        tarjeta.setCliente(this);
        tarjetas.add(tarjeta);
    }


    //----------------metodos para prestamos
    public void addClientePrestamo(ClientePrestamo clientePrestamo){
        clientePrestamo.setCliente(this); // agrega un cliente al FK de cliente en ClientePrestamo
        clientePrestamos.add(clientePrestamo);

    }

    public Set<Prestamo> getPrestamos() {
        return clientePrestamos.stream().map(clientePrestamo -> clientePrestamo.getPrestamo()).collect(Collectors.toSet());
    }







}
