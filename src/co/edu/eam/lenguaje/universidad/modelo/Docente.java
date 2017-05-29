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
public class Docente extends Persona implements Serializable {

    private List<Curso> cursos;
    private boolean postgrado;
    private String profesion;

    public Docente(boolean postgrado, String profesion, String nombre, String apellido, String direccion, String correo, String documento, String telefono) {
        super(nombre, apellido, direccion, correo, documento, telefono);
        this.postgrado = postgrado;
        this.profesion = profesion;
        cursos = new ArrayList<>();
    }

    public void agregarCurso(Curso curso) {
        cursos.add(curso);
    }

    public void editarCurso(Curso curso) {
        for (int i = 0; i < cursos.size(); i++) {
            if (cursos.get(i).getCodigo().equals(curso.getCodigo())) {
                cursos.remove(i);
            }
        }
        cursos.add(curso);
    }

    public List<Curso> getCursos() {
        return cursos;
    }

    public boolean isPostgrado() {
        return postgrado;
    }

    public void setPostgrado(boolean postgrado) {
        this.postgrado = postgrado;
    }

    public String getProfesion() {
        return profesion;
    }

    public void setProfesion(String profesion) {
        this.profesion = profesion;
    }

}
