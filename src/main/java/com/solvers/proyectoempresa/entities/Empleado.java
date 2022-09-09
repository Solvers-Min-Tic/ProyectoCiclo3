package com.solvers.proyectoempresa.entities;

import javax.persistence.*;

@Entity
@Table (name = "empleado")
public class Empleado {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idEmpleado;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "correo")
    private String CorreoElectronico;
    @Column(name = "direccion")
    private String direccion;
    @Column(name = "empresa")
    private Empresa empresa;


    @Column(name = "rol")
    private String rol;

    public Empleado() {
    }

    public Empleado(String nombre, String correoElectronico, String direccion, Empresa empresa, String rol) {
        this.nombre = nombre;
        CorreoElectronico = correoElectronico;
        this.direccion = direccion;
        this.empresa = empresa;
        this.rol = rol;
    }

    public int getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreoElectronico() {
        return CorreoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        CorreoElectronico = correoElectronico;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Empresa getEmpresa()
    { return this.empresa; }

    public void setEmpresa(Empresa empresa)
    { this.empresa = empresa; }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
}
