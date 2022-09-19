package com.solvers.proyectoempresa.dto;

import com.solvers.proyectoempresa.entities.TipoDoc;

public class registroDTO {

    private String nombres;
    private String apellidos;
    private String numeroDocumento;
    private String mail1;
    private TipoDoc tipoDoc;
    private String password;
    private String validaPassword;

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public String getMail() {
        return mail1;
    }

    public void setMail(String mail) {
        this.mail1 = mail1;
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
}
