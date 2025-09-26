package fabrica.lineaDemo.Models;

import jakarta.persistence.*;
@Entity
@Table(name = "productos", schema = "practico_fabrica")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_producto")
    private Integer id_producto;

    @Column(name = "codigo_producto", length = 50, nullable = false)
    private String codigo;

    @Column(name = "producto",length = 100, nullable = false)
    private String nombre;

    @Column(length = 100)
    private String origen;

    @Column(length = 100)
    private String tipo;

    @Column(name = "costo")
    private Double costo;

    @Column(name = "permite_fraccionamiento")
    private Boolean permiteFraccionamiento;

    private Integer cantidad;


    public Producto(){}

    public Producto( String codigo_p,String nombre,String origen, String tipo, Double costo, Boolean PF){
        this.codigo = codigo_p;
        this.nombre = nombre;
        this.origen = origen;
        this.tipo = tipo;
        this.costo = costo;
        this.permiteFraccionamiento = PF;
    }

    //parte de getters y setters
    public void setCodigo(String codigo){
        this.codigo = codigo;
    }
    public String getCodigo(){
        return codigo;
    }
    public String getNombre(){
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getOrigen(){
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public String getTipo(){
        return tipo;
    }

    public void setCosto(Double costo) {
        this.costo = costo;
    }

    public Double getCosto() {
        return costo;
    }

    public Boolean getPermiteFraccionamiento() {
        return permiteFraccionamiento;
    }

    public void setPermiteFraccionamiento(Boolean permiteFraccionamiento) {
        this.permiteFraccionamiento = permiteFraccionamiento;
    }

    public void setTipo(String tipo){
        this.tipo =tipo;
    }

    public Integer getId_producto() {
        return id_producto;
    }

    public void setId_producto(Integer id_producto) {
        this.id_producto = id_producto;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Integer getCantidad() {
        return cantidad;
    }
}
