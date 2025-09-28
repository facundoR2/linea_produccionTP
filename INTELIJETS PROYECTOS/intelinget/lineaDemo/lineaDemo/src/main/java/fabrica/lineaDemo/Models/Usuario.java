package fabrica.lineaDemo.Models;

import jakarta.persistence.*;

@Entity
@Table(name = "usuarios",schema = "practico_fabrica")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idUsuario;

    private String nombre;
    private String email;
    private String password;

    public Usuario(){}

    public Usuario(String nombre, String email, String password){
        this.nombre = nombre;
        this.email = email;
        this.password = password;
    }

    //getter y setter


    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId() {
        return idUsuario;
    }

    public void setId(int id) {
        this.idUsuario = id;
    }
}
