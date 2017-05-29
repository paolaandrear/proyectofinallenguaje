/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.eam.lenguaje.universidad.modelo;

import java.util.List;
import co.edu.eam.excepcion.LogicaExcepciones;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 *
 * @author alexc
 */
public class Curso implements Serializable {

    private List<Horario> horarios;
    private List<Registro> registros;
    private List<Evaluacion> evaluaciones;

    private Asignatura asignatura;
    private Docente docente;
    private String codigo;

    public Curso(Asignatura asignatura, Docente docente) {
        this.asignatura = asignatura;
        this.docente = docente;
        registros = new ArrayList<>();
        evaluaciones = new ArrayList<>();
        this.codigo = generarCodigo(asignatura);
    }

    public Registro buscarRegistro(String cedula) {
        for (Registro registro : registros) {
            if (registro.getEstudiante().getDocumento().equals(cedula)) {
                return registro;
            }
        }
        return null;
    }

    public void crearRegistro(Curso curso, Estudiante estudiante) throws LogicaExcepciones {
        if (curso.getRegistros().size() == 10) {
            throw new LogicaExcepciones("El curso alcanzó el límite permitido de estudiantes");
        }
        if (estudiante.calcularCreditos() == 12) {
            throw new LogicaExcepciones("El estudiante ha alcanzando el limite de creditos");
        }
        if ((estudiante.calcularCreditos() + curso.getAsignatura().getCreditos()) > 12) {
            throw new LogicaExcepciones("No puede registrar el curso ya que sobrepasa los créditos permitidos");
        }
        if (!estudiante.verificarCruceHorario(curso)) {
            throw new LogicaExcepciones("Se encuentra un cruce de horarios con otro curso registrado");
        }
        if (estudiante.verificarAsignaturaRegistrada(curso)) {
            Registro registro = new Registro(estudiante, curso);
            registros.add(registro);
            estudiante.agregarRegistro(registro);
            Universidad.getInstance().guardarArchivo();
        } else {
            throw new LogicaExcepciones("El estudiante ya ha registrado la asignatura");
        }
    }

    public void crearEvaluacion(Evaluacion eval) throws LogicaExcepciones {
        if (calcularPorcentajeEvaluaciones() == 100) {
            throw new LogicaExcepciones("El curso ya tiene el 100% de las evaluaciones asignado");
        }
        if ((calcularPorcentajeEvaluaciones() + eval.getPorcentaje()) > 100) {
            throw new LogicaExcepciones("El porcentaje asignado a la evaluación sobrepasa el 100% del curso");
        }
        evaluaciones.add(eval);
        Universidad.getInstance().guardarArchivo();
    }

    public double calcularPorcentajeEvaluaciones() {
        double total = 0;
        for (Evaluacion eval : evaluaciones) {
            total += eval.getPorcentaje();
        }
        return total;
    }

    public String generarCodigo(Asignatura asignatura) {
        String time = ((Long) System.nanoTime()).toString();
        String dato = "" + time.charAt(4) + time.charAt(6);
        String dato1 = asignatura.getCodigo();
        Calendar fecha = new GregorianCalendar();
        int ano = fecha.get(Calendar.YEAR);
        String cod = dato + "-" + dato1 + ano + semestreDelAnio();
        return cod;
    }

    public String semestreDelAnio() {
        Calendar fecha = new GregorianCalendar();
        int mes = fecha.get(Calendar.MONTH);
        if (mes >= 0 && mes <= 5) {
            return "I";
        } else {
            return "II";
        }
    }

    public List<Evaluacion> getEvaluaciones() {
        return evaluaciones;
    }

    public void setEvaluaciones(List<Evaluacion> evaluaciones) {
        this.evaluaciones = evaluaciones;
    }

    public List<Horario> getHorarios() {
        return horarios;
    }

    public void setHorarios(List<Horario> horarios) {
        this.horarios = horarios;
    }

    public List<Registro> getRegistros() {
        return registros;
    }

    public void setRegistros(List<Registro> registros) {
        this.registros = registros;
    }

    public Asignatura getAsignatura() {
        return asignatura;
    }

    public void setAsignatura(Asignatura asignatura) {
        this.asignatura = asignatura;
    }

    public Docente getDocente() {
        return docente;
    }

    public void setDocente(Docente docente) {
        this.docente = docente;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    @Override
    public String toString() {
        return codigo;
    }

}
