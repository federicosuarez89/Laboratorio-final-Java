package mx.com.gm.peliculas.datos;

import mx.com.gm.peliculas.domain.Pelicula;
import mx.com.gm.peliculas.excepciones.*;
import java.io.*;
import java.util.*;

public class AccesoDatosImpl implements AccesoDatos{


    @Override
    public boolean existe(String nombreRecurso) {
        File archivo = new File(nombreRecurso);
        return archivo.exists();
    }

    @Override
    public List<Pelicula> listar(String nombreRecurso) throws LecturaDatosEx {
        File archivo = new File(nombreRecurso);
        //Creamos la lista de peliculas
        List<Pelicula> peliculas = new ArrayList<>();
        try {
            BufferedReader entrada = new BufferedReader(new FileReader(archivo));
            String linea = null;
            //Leemos el texto ingresado
            linea = entrada.readLine();
            //Comprobamos que linea no este vacia para crear un objeto
            while (linea !=null){
                Pelicula pelicula = new Pelicula(linea);
                //Agregamos un ojeto de tipo pelicula a la lista de peliculas
                peliculas.add(pelicula);
                //Leemos para ver si hay mas peliculas para agregar a la lista
                linea = entrada.readLine();
            }
            //Cerramos el flujo
            entrada.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new LecturaDatosEx("Excepcion al listar peliculas:"+e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
            throw new LecturaDatosEx("Excepcion al listar peliculas:"+e.getMessage());
        }
        return peliculas;
    }

    @Override
    public void escribir(Pelicula pelicula, String nombreRecurso, boolean anexar) throws EscrituraDatosEx {
        File archivo = new File(nombreRecurso);
        try {
            var salida = new PrintWriter(new FileWriter(archivo,anexar));
            //Escribimos en el archivo el nombre de la pelicula
            salida.println(pelicula.toString());
            //Cerramos el flujo
            salida.close();
            System.out.println("Se ha escrito informacion en el archivo: "+pelicula);
        } catch (IOException e) {
            e.printStackTrace();
            throw new EscrituraDatosEx("Excepcion al escribir peliculas:"+e.getMessage());
        }
    }

    @Override
    public String buscar(String nombreRecurso, String buscar) throws LecturaDatosEx {
        var archivo = new File(nombreRecurso);
        //Si existe la pelicula se almacena en esta variable con su nombre
        String resultado = null;
        try {
            var entrada = new BufferedReader(new FileReader(archivo));
            //Definimos la variable linea para guardar lo que se ingrese por teclado
            String linea = null;
            //Leemos lo que hay en la variable
            linea = entrada.readLine();
            //Definimos un contador de lineas de la lista
            int indice = 1;
            //Si la variable no esta vacia
            while (linea != null){
                if (buscar != null && buscar.equalsIgnoreCase(linea)){
                    resultado = "Pelicula "+linea+" encontrada en el indice "+indice;
                    break;
                }
                //Si no se encuentra la pelicula en la primer linea buscamos en la siguiente
                linea = entrada.readLine();
                indice++;
            }
            //Cerramos el flujo
            entrada.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new LecturaDatosEx("Excepcion al buscar pelicula: "+e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
            throw new LecturaDatosEx("Excepcion al buscar pelicula: "+e.getMessage());
        }
        return resultado;
    }

    @Override
    public void crear(String nombreRecurso) throws AccesoDatosEx {
        var archivo = new File(nombreRecurso);
        try {
            var salida = new PrintWriter(new FileWriter(archivo));
            salida.close();
            System.out.println("Se ha creado el archivo");
        } catch (IOException e) {
            e.printStackTrace();
            throw new AccesoDatosEx("Excepcion al crear archivo: "+e.getMessage());
        }

    }

    @Override
    public void borrar(String nombreRecurso) {
        var archivo = new File(nombreRecurso);
        if (archivo.exists()){
            archivo.delete();
            System.out.println("Se ha borrado exitosamente el archivo");
        }


    }
}
