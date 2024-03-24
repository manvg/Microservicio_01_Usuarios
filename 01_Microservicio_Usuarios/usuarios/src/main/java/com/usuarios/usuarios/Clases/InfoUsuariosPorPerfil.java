package com.usuarios.usuarios.Clases;

public class InfoUsuariosPorPerfil {
    private String perfil;
    private Integer cantidad;

    public InfoUsuariosPorPerfil(String perfil, Integer cantidad){
        this.perfil = perfil;
        this.cantidad = cantidad;
    }

    public String getPerfil(){
        return perfil;
    }

    public Integer getCantidad(){
        return cantidad;
    }
}
