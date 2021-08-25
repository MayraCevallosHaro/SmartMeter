package com.example.smartmeter.Modelo;

public class Usuario {

    private String nombres, apellidos, cedula, tipo, usuario,clave,correo, idusuario;

    public Usuario(String nombres, String apellidos, String cedula, String tipo, String usuario,String clave, String correo, String idusuario )
    {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.cedula = cedula;
        this.tipo = tipo;
        this.usuario = usuario;
        this.clave= clave;
        this.correo = correo;
        this.idusuario = idusuario;

    }

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

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(String idusuario) {
        this.idusuario = idusuario;
    }
}
