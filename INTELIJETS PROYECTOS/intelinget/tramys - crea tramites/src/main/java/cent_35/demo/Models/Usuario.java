package cent_35.demo.Models;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private int id;

    private String nombre;

    private String apellido;

    private Integer estado; //campo para borrados logicos

    private String correo;
    private String password;



    private Rol rol;

    public Usuario(){}

    public Usuario(String nombre,String apellido,String correo, String password){
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.estado = 1; // 1:activo , 0 deBaja.
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }


}
