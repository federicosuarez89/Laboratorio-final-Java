package mx.com.gm.peliculas.datos;

import mx.com.gm.peliculas.domain.Pelicula;
import mx.com.gm.peliculas.excepciones.*;
import java.io.*;
import java.util.*;

public class AccesoDatosImpl implements AccesoDatos{


    @Override
    public boolean existe(String nombreRecurso) throws AccesoDatosEx {
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
        return null;
    }

    @Override
    public void crear(String nombreRecurso) throws AccesoDatosEx {

    }

    @Override
    public void borrar(String nombreRecurso) throws AccesoDatosEx {

    }
}
