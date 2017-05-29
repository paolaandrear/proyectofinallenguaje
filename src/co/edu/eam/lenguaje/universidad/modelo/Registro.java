/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.eam.lenguaje.universidad.modelo;

import co.edu.eam.lenguaje.universidad.modelo.Estudiante;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author alexc
 */
public class Registro implements Serializable {

    private Estudiante estudiante;
    private Curso curso;
    private List<Nota> notas;

    public Registro(Estudiante estudiante, Curso curso) {
        this.estudiante = estudiante;
        this.curso = curso;
        notas = new ArrayList<>();
    }

    public double calcularNotaFinal() {
        double total = 0;
        for (Nota nota : notas) {
            total += nota.getNota() * (nota.getEvaluacion().getPorcentaje()/100);
        }
        return total;
    }

    public void asignarNota(Nota nota) {
        notas.add(nota);
        Universidad.getInstance().guardarArchivo();
    }

    public Nota buscarNotaPorEvaluacion(Evaluacion eval) {
        for (Nota nota : notas) {
            if (nota.getEvaluacion().equals(eval)) {
                return nota;
            }
        }
        return null;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

}
