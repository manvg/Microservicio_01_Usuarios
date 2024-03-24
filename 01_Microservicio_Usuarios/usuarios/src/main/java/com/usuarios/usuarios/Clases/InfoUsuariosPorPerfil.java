package com.usuarios.usuarios.Clases;

public class InfoUsuariosPorPerfil {
    private String departamento;
    private Integer cantidad;

    public InfoUsuariosPorPerfil(String departamento, Integer cantidad){
        this.departamento = departamento;
        this.cantidad = cantidad;
    }

    public String getDepartamento(){
        return departamento;
    }

    public Integer getCantidad(){
        return cantidad;
    }
}
