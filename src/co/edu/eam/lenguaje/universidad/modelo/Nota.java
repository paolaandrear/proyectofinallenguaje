/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.eam.lenguaje.universidad.modelo;

import co.edu.eam.lenguaje.universidad.modelo.Evaluacion;
import java.io.Serializable;

/**
 *
 * @author alexc
 */
public class Nota implements Serializable {

    private double nota;
    private boolean editarNota;
    private Evaluacion evaluacion;

    public Nota(double nota, boolean editarNota, Evaluacion evaluacion) {
        this.nota = nota;
        this.editarNota = editarNota;
        this.evaluacion = evaluacion;
    }

    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }

    public boolean isEditarNota() {
        return editarNota;
    }

    public void setEditarNota(boolean editarNota) {
        this.editarNota = editarNota;
    }

    public Evaluacion getEvaluacion() {
        return evaluacion;
    }

    public void setEvaluacion(Evaluacion evaluacion) {
        this.evaluacion = evaluacion;
    }

}
