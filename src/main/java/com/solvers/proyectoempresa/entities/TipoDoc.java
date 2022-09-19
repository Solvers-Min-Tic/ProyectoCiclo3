package com.solvers.proyectoempresa.entities;


import javax.persistence.*;

@Entity
@Table(name = "tipodoc")
public class TipoDoc {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;

    @Column(name = "nombre", nullable = false, length = 30)
    private String nombre;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
