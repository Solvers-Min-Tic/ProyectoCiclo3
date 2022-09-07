package com.solvers.proyectoempresa;

public class Empleado {

    private String nombre;
    private String CorreoElectronico;
    private String direccion;
    private Empresa empresa;
    private String rol;

    public Empleado(String nombre, String correoElectronico, String direccion, Empresa empresa, String rol) {
        this.nombre = nombre;
        CorreoElectronico = correoElectronico;
        this.direccion = direccion;
        this.empresa = empresa;
        this.rol = rol;
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
