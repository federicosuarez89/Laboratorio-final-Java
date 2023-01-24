package mx.com.gm.peliculas.negocio;

import mx.com.gm.peliculas.datos.*;
import mx.com.gm.peliculas.domain.Pelicula;
import mx.com.gm.peliculas.excepciones.AccesoDatosEx;
import mx.com.gm.peliculas.excepciones.LecturaDatosEx;

public class CatalogoPeliculasImpl implements CatalogoPeliculas{
    private final AccesoDatos datos;

    public CatalogoPeliculasImpl() {
        this.datos = new AccesoDatosImpl();
    }

    @Override
    public void agregarPelicula(String nombrePelicula) {
        Pelicula pelicula = new Pelicula(nombrePelicula);
        boolean anexar = false;
        try {
            anexar = datos.existe(NOMBRE_RECURSO);
            datos.escribir(pelicula,NOMBRE_RECURSO,anexar);
        } catch (AccesoDatosEx e) {
            e.printStackTrace();
            System.out.println("Error de acceso a datos");
        }
    }

    @Override
    public void listarPeliculas() {
        try {
            var peliculas = this.datos.listar(NOMBRE_RECURSO);
            for (var pelicula:peliculas) {
                System.out.println("pelicula = " + pelicula);
            }
        } catch (AccesoDatosEx e) {
            e.printStackTrace();
            System.out.println("Error de acceso datos");
        }
    }

    @Override
    public void buscarPelicula(String buscar) {
        String resultado = null;
        try {
            resultado = this.datos.buscar(NOMBRE_RECURSO,buscar);
        }catch (AccesoDatosEx e){
            e.printStackTrace();
            System.out.println("Error de acceso datos en el metodo buscar peliculas");
        }
        System.out.println("resultado = " + resultado);

    }

    @Override
    public void iniciarCatalogoPeliculas() {
        try {
            if (this.datos.existe(NOMBRE_RECURSO)){
                /*
                    si ya existe el catalogo de peliculas,primero lo borramos
                    y luego se crea uno nuevo
                 */
                datos.borrar(NOMBRE_RECURSO);
                datos.crear(NOMBRE_RECURSO);
            }else {
                datos.crear(NOMBRE_RECURSO);
            }
        } catch (AccesoDatosEx e) {
            e.printStackTrace();
            System.out.println("Error al iniciar catalogo de peliculas");
        }
    }
}
