/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.eam.lenguaje.persistencia;

import co.edu.eam.lenguaje.universidad.modelo.Curso;
import co.edu.eam.lenguaje.universidad.modelo.Estudiante;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.util.List;

/**
 *
 * @author ASISPROF
 */
public class Persistencia {

    public void guardarObjeto(Object obj) {
        try {
            //Se indica el archivo objetivo para guardar, en este caso banco.dat
            FileOutputStream archivo = new FileOutputStream("proyecto.dat");
            //Se crea el objeto encargado de escribir y se le indica el archivo que va a escribir
            ObjectOutputStream objOut = new ObjectOutputStream(archivo);
            //Se escribe el objeto enviado en el archivo
            objOut.writeObject(obj);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public Object leerObjeto() throws FileNotFoundException, IOException {
        try {
            //Se indica el archivo que se va a abrir para la lectura
            FileInputStream entArchi = new FileInputStream("proyecto.dat");
            //Se crea el objeto encargado de leer el archivo
            ObjectInputStream leerArchi = new ObjectInputStream(entArchi);
            //Se lee el archivo y se guarda como Object
            Object obj = leerArchi.readObject();
            return obj;
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return null;
    }

}
