/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.eam.lenguaje.universidad.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author alexc
 */
public class Estudiante extends Persona implements Serializable {

    private int edad;
    private List<Registro> registros;

    public Estudiante(int edad, String nombre, String apellido, String direccion, String correo, String documento, String telefono) {
        super(nombre, apellido, direccion, correo, documento, telefono);
        this.edad = edad;
        registros = new ArrayList<>();
    }

    public void agregarRegistro(Registro registro) {
        registros.add(registro);
    }

    public boolean verificarCruceHorario(Curso curso) {
        //Para recorrer los registros del estudiante
        for (Registro registro : registros) {
            //Para recorrer los horarios del registro del estudiante
            for (Horario horRe : registro.getCurso().getHorarios()) {
                //Para recorrer los horarios del curso enviado
                for (Horario horNu : curso.getHorarios()) {
                    if (horRe.getDia().toString().equals(horNu.getDia().toString())) {
                        if ((horNu.getHoraInicio() >= horRe.getHoraInicio() && horNu.getHoraInicio() < horRe.getHoraFinal())
                                || (horNu.getHoraFinal() > horRe.getHoraInicio() && horNu.getHoraFinal() < horRe.getHoraFinal())) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    public boolean verificarAsignaturaRegistrada(Curso curso) {
        for (Registro registro : registros) {
            if (registro.getCurso().getAsignatura().getCodigo().equals(curso.getAsignatura().getCodigo())) {
                return false;
            }
        }
        return true;
    }

    public int calcularCreditos() {
        int acumulado = 0;
        for (Registro hor : registros) {
            acumulado += hor.getCurso().getAsignatura().getCreditos();
        }
        return acumulado;
    }

    public List<Registro> getRegistros() {
        return registros;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public int getEdad() {
        return edad;
    }

}
