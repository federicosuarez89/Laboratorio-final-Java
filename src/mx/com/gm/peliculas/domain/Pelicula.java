package mx.com.gm.peliculas.domain;

public class Pelicula {

    //Atributos de clase
    private String nombre;

    public Pelicula(){}
    public Pelicula(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return this.nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Pelicula{");
        sb.append("nombre:").append(nombre);
        sb.append('}');
        return sb.toString();
    }
}
