/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.eam.lenguaje.universidad.modelo;

import co.edu.eam.lenguaje.universidad.modelo.DiaSemanaEnum;
import java.io.Serializable;

/**
 *
 * @author alexc
 */
public class Horario implements Serializable {

    private int horaInicio;
    private int horaFinal;
    private DiaSemanaEnum dia;

    public Horario(int horaInicio, int horaFinal, DiaSemanaEnum dia) {
        this.horaInicio = horaInicio;
        this.horaFinal = horaFinal;
        this.dia = dia;
    }

    public int getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(int horaInicio) {
        this.horaInicio = horaInicio;
    }

    public int getHoraFinal() {
        return horaFinal;
    }

    public void setHoraFinal(int horaFinal) {
        this.horaFinal = horaFinal;
    }

    public DiaSemanaEnum getDia() {
        return dia;
    }

    public void setDia(DiaSemanaEnum dia) {
        this.dia = dia;
    }

}
