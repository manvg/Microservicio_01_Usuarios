package com.usuarios.usuarios.Clases;

import java.time.LocalDate;
//import java.util.Map;

public class Usuario {
    private int idUsuario;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String email;
    private String password;
    private int telefono;
    private String direccion;
    private LocalDate fechaCreacion;
    private Perfil perfil;
    private boolean activo;

    // Constructor
    public Usuario(int idUsuario, String nombre, String apellidoPaterno, String apellidoMaterno,
                   String email, String password, int telefono, String direccion, LocalDate fechaCreacion,
                   Perfil perfil, boolean activo) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.email = email;
        this.password = password;
        this.telefono = telefono;
        this.direccion = direccion;
        this.fechaCreacion = fechaCreacion;
        this.perfil = perfil;
        this.activo = activo;
    }

    // Métodos get
    public int getIdUsuario() {
        return idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public int getTelefono() {
        return telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public Perfil getPerfil() {
        return perfil;
    }

    public boolean estaActivo() {
        return activo;
    }
}
