package com.solvers.proyectoempresa.dto;

import com.solvers.proyectoempresa.entities.EnumRol;
import com.solvers.proyectoempresa.entities.TipoDoc;

public class registroDTO {

    private String nombre;

    private Integer numeroDocumento;

    private String correoElectronico;

    private String direccion;

    private TipoDoc tipoDoc;
    private String password;
    private String validaPassword;

    private EnumRol rol;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(Integer numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public TipoDoc getTipoDoc() {
        return tipoDoc;
    }

    public void setTipoDoc(TipoDoc tipoDoc) {
        this.tipoDoc = tipoDoc;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getValidaPassword() {
        return validaPassword;
    }

    public void setValidaPassword(String validaPassword) {
        this.validaPassword = validaPassword;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public EnumRol getRol() {
        return rol;
    }

    public void setRol(EnumRol rol) {
        this.rol = rol;
    }
}
