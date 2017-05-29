/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.eam.lenguaje.universidad.modelo;

import co.edu.eam.lenguaje.universidad.modelo.Curso;
import co.edu.eam.lenguaje.universidad.modelo.Persona;
import co.edu.eam.lenguaje.universidad.modelo.Estudiante;
import co.edu.eam.lenguaje.universidad.modelo.Docente;
import co.edu.eam.lenguaje.universidad.modelo.Horario;
import co.edu.eam.lenguaje.universidad.modelo.Asignatura;
import java.util.List;
import co.edu.eam.excepcion.LogicaExcepciones;
import co.edu.eam.lenguaje.persistencia.Persistencia;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author alexc
 */
public class Universidad implements Serializable {

    private static Universidad instance;

    private List<Docente> docentes;
    private List<Asignatura> asignaturas;
    private List<Curso> cursos;
    private List<Estudiante> estudiantes;
    private static Persistencia pu;

    private Universidad() {
        this.docentes = new ArrayList<>();
        this.asignaturas = new ArrayList<>();
        this.cursos = new ArrayList<>();
        this.estudiantes = new ArrayList<>();

    }

    public static Universidad getInstance() {
        //Si la instancia es null
        pu = new Persistencia();
        if (instance == null) {
            try {
                //Se revisa si existe un archivo con el banco para leerlo
                System.out.println("se intenta leer el archivo");
                instance = (Universidad) pu.leerObjeto();
                System.out.println("se lee el archivo");
            } catch (FileNotFoundException ex) {
                //Si el archivo no existe se crea una nueva instancia del banco
                instance = new Universidad();
                System.out.println("no hay archivo aún");
            } catch (IOException e) {
                instance = new Universidad();
                System.out.println("Datos incompatibles");
            }
        }
        return instance;
    }

    public void guardarArchivo() {
        pu.guardarObjeto(this);
    }

    public List<Curso> listarCursoAsig(Asignatura asignatura) {
        ArrayList<Curso> lista = new ArrayList<>();
        for (Curso curso : cursos) {
            if (curso.getAsignatura().getCodigo().equals(asignatura.getCodigo())) {
                lista.add(curso);
            }
        }
        return lista;
    }

    public Curso buscarCursoPorCodigo(String codigo) {
        for (Curso curso : cursos) {
            if (curso.getCodigo().equals(codigo)) {
                return curso;
            }
        }
        return null;
    }

    public Curso buscarPorAsignaturaYDocente(Asignatura asig, Docente doc) {
        for (Curso curso : cursos) {
            if (curso.getAsignatura().getCodigo().equals(asig.getCodigo())
                    && curso.getDocente().getDocumento().equals(doc.getDocumento())) {
                return curso;
            }
        }
        return null;
    }

    public List<Curso> listarCursoDocente(Docente docente) {
        List<Curso> lista = new ArrayList<>();
        for (Curso curso : cursos) {
            if (curso.getDocente().getDocumento().equals(docente.getDocumento())) {
                lista.add(curso);
            }
        }
        return lista;
    }

    public void crearCurso(Curso curso) throws LogicaExcepciones {
        List<Curso> cursosDoc = listarCursoDocente(curso.getDocente());
        for (int i = 0; i < cursosDoc.size(); i++) {
            for (Horario hora : cursosDoc.get(i).getHorarios()) {
                for (Horario horaC : curso.getHorarios()) {
                    if (hora.getDia().toString().equals(horaC.getDia().toString())) {
                        System.out.println("1"+hora.getDia().toString());
                        System.out.println("2"+horaC.getDia().toString());
                        if ((horaC.getHoraInicio() >= hora.getHoraInicio() && horaC.getHoraInicio() < hora.getHoraFinal())
                                || (horaC.getHoraFinal() > hora.getHoraInicio() && horaC.getHoraFinal() < hora.getHoraFinal())) {
                            throw new LogicaExcepciones("Hay un cruce de horarios ");
                        }
                    }
                }
            }
        }
        cursos.add(curso);
        curso.getDocente().agregarCurso(curso);
        guardarArchivo();
    }

    public void editarCurso(Curso curso, List<Horario> horarios) throws LogicaExcepciones {
        List<Curso> cursosDoc = listarCursoDocente(curso.getDocente());
        for (Curso cur : cursosDoc) {
            if (!cur.getCodigo().equals(curso.getCodigo())) {
                for (Horario horEd : horarios) {
                    for (Horario horCu : cur.getHorarios()) {
                        if (horCu.getDia().toString().equals(horEd.getDia().toString())) {
                            if ((horEd.getHoraInicio() >= horCu.getHoraInicio() && horEd.getHoraInicio() < horCu.getHoraFinal())
                                    || (horEd.getHoraFinal() > horCu.getHoraInicio() && horEd.getHoraFinal() < horCu.getHoraFinal())) {
                                throw new LogicaExcepciones("No se puede editar, hay un cruce de horarios para el docente");
                            }
                        }
                    }
                }
            }
        }
        curso.setHorarios(horarios);
        curso.getDocente().editarCurso(curso);
        guardarArchivo();
    }

    public Asignatura buscarAsig(String codigo) {
        for (Asignatura asignatura : asignaturas) {
            if (asignatura.getCodigo().equals(codigo)) {
                return asignatura;
            }
        }
        return null;
    }

    public void crearAsignatura(Asignatura asignatura) throws LogicaExcepciones {
        Asignatura aux = buscarAsig(asignatura.getCodigo());
        if (aux != null) {
            throw new LogicaExcepciones(" no se pudo crear la asignatura, ya existe una con este codigo" + " " + asignatura.getCodigo());

        } else {
            asignaturas.add(asignatura);
            guardarArchivo();
        }
    }

    public void editarAsig(Asignatura asignatura) throws LogicaExcepciones {
        Asignatura aux = buscarAsig(asignatura.getCodigo());
        if (aux != null) {
            aux.setNombre(asignatura.getNombre());
            aux.setCreditos(asignatura.getCreditos());
            aux.setTipoAsignatura(asignatura.getTipoAsignatura());
            guardarArchivo();
        } else {
            throw new LogicaExcepciones("No se encontró la asignatura a editar");
        }

    }

    public Estudiante buscarEstu(String documento) {
        for (Estudiante estudiante : estudiantes) {
            if (estudiante.getDocumento().equals(documento)) {
                return estudiante;
            }
        }
        return null;
    }

    public void crearEstudiante(Estudiante estudiante) throws LogicaExcepciones {
        if (buscarEstu(estudiante.getDocumento()) != null || buscarDoc(estudiante.getDocumento()) != null) {
            throw new LogicaExcepciones(" ya existe alguien registrado con ese documento" + " " + estudiante.getDocumento());
        } else {
            estudiantes.add(estudiante);
            guardarArchivo();
        }
    }

    public void editarEstudiante(Estudiante estudianteNuevo) throws LogicaExcepciones {
        Estudiante aux = buscarEstu(estudianteNuevo.getDocumento());
        if (aux != null) {
            aux.setNombre(estudianteNuevo.getNombre());
            aux.setApellido(estudianteNuevo.getApellido());
            aux.setCorreo(estudianteNuevo.getCorreo());
            aux.setDireccion(estudianteNuevo.getDireccion());
            aux.setEdad(estudianteNuevo.getEdad());
            aux.setTelefono(estudianteNuevo.getTelefono());
            guardarArchivo();
        } else {
            throw new LogicaExcepciones("No se encontró el estudiante a editar");
        }
    }

    public Docente buscarDoc(String documento) {
        for (Docente docente : docentes) {
            if (docente.getDocumento().equals(documento)) {
                return docente;
            }
        }
        return null;
    }

    public void registrarDocente(Docente docente) throws LogicaExcepciones {
        if (buscarEstu(docente.getDocumento()) != null || buscarDoc(docente.getDocumento()) != null) {
            throw new LogicaExcepciones("ya hay alguien registrado con esa cedula" + " " + docente.getDocumento());
        } else {
            docentes.add(docente);
            guardarArchivo();
        }
    }

    public void editarDocente(Docente docenteNuevo) throws LogicaExcepciones {
        Docente aux = buscarDoc(docenteNuevo.getDocumento());
        if (aux != null) {
            aux.setNombre(docenteNuevo.getNombre());
            aux.setApellido(docenteNuevo.getApellido());
            aux.setCorreo(docenteNuevo.getCorreo());
            aux.setDireccion(docenteNuevo.getDireccion());
            aux.setTelefono(docenteNuevo.getTelefono());
            guardarArchivo();
        } else {
            throw new LogicaExcepciones("No se encontró el docente a editar");
        }
    }

    public boolean verificarNumEstu() {
        return false;

    }

    public List<Docente> getDocentes() {
        return docentes;
    }

    public List<Asignatura> getAsignaturas() {
        return asignaturas;
    }

    public List<Curso> getCursos() {
        return cursos;
    }

    public List<Estudiante> getEstudiantes() {
        return estudiantes;
    }

}
