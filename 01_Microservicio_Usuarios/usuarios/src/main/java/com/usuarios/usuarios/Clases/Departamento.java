package com.usuarios.usuarios.Clases;

public class Departamento {
    private int idDepartamento;
    private String nombre;

    public Departamento(int idDepartamento, String nombre){
        this.idDepartamento = idDepartamento;
        this.nombre = nombre;
    }

    public int getidDepartamento(){
        return idDepartamento;
    }

    public String getNombre(){
        return nombre;
    }
}
