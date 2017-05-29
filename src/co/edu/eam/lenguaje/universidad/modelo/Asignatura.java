/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.eam.lenguaje.universidad.modelo;

import java.io.Serializable;
import sun.util.logging.PlatformLogger;

/**
 *
 * @author alexc
 */
public class Asignatura implements Serializable {
// private static  Asignatura instance = new Asignatura();

    private String codigo;
    private String nombre;
    private int creditos;
    private TipoAsignatura tipoAsignatura;

    public Asignatura(String codigo, String nombre, int creditos, TipoAsignatura tipoAsignatura) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.creditos = creditos;
        this.tipoAsignatura = tipoAsignatura;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCreditos() {
        return creditos;
    }

    public void setCreditos(int creditos) {
        this.creditos = creditos;
    }

    public TipoAsignatura getTipoAsignatura() {
        return tipoAsignatura;
    }

    public void setTipoAsignatura(TipoAsignatura tipoAsignatura) {
        this.tipoAsignatura = tipoAsignatura;
    }

}
