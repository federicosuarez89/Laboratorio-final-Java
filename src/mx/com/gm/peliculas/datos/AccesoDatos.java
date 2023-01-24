package mx.com.gm.peliculas.datos;

import mx.com.gm.peliculas.domain.Pelicula;
import mx.com.gm.peliculas.excepciones.*;
import java.util.List;

public interface AccesoDatos {
    //Metodos de la interfaz

    //Este metodo comprueba si existe un recurso
    boolean existe(String nombreRecurso) throws AccesoDatosEx;

    //Este metodo regresa una lista de objetos de tipo Pelicula
    List<Pelicula> listar(String nombreRecurso) throws LecturaDatosEx;

    //Este metodo agrega peliculas en la lista
    void escribir(Pelicula pelicula, String nombreRecurso, boolean anexar)throws EscrituraDatosEx;

    //Metodo para buscar una pelicula
    String buscar(String nombreRecurso, String buscar)throws LecturaDatosEx;

    //Metodo para crear una nueva pelicula
    void crear(String nombreRecurso)throws AccesoDatosEx;

    //Metodo para borrar una pelicula
    void borrar(String nombreRecurso)throws AccesoDatosEx;
}
