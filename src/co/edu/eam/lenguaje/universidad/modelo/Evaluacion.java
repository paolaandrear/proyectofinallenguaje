/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.eam.lenguaje.universidad.modelo;

import java.io.Serializable;

/**
 *
 * @author alexc
 */
public class Evaluacion implements Serializable {

    private String nombre;
    private double porcentaje;
    private Curso curso;

    public Evaluacion(Curso curso, String nombre, double porcentaje) {
        this.nombre = nombre;
        this.porcentaje = porcentaje;
        this.curso = curso;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(double porcentaje) {
        this.porcentaje = porcentaje;
    }

    @Override
    public String toString() {
        return nombre;
    }

}
