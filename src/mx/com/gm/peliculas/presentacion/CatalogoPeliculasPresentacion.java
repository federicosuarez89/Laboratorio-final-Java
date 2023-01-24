package mx.com.gm.peliculas.presentacion;

import mx.com.gm.peliculas.negocio.*;

import java.util.Scanner;

public class CatalogoPeliculasPresentacion {
    public static void main(String[] args) {
        //Variables de clase
        var opcion = -1;
        Scanner entrada = new Scanner(System.in);
        CatalogoPeliculas catalogo = new CatalogoPeliculasImpl();
        while (opcion != 0){
            System.out.println("Elige una opcion: ");
            System.out.println("1.Iniciar catalogo de peliculas");
            System.out.println("2.Agregar pelicula");
            System.out.println("3.Listar peliculas");
            System.out.println("4.Buscar pelicula");
            System.out.println("0.Salir");
            opcion = Integer.parseInt(entrada.nextLine());
            switch (opcion){
                case 1:
                    catalogo.iniciarCatalogoPeliculas();
                    break;
                case 2:
                    System.out.println("Ingrese el nombre de la pelicula");
                    var nombrePelicula = entrada.nextLine();
                    catalogo.agregarPelicula(nombrePelicula);
                    break;
                case 3:
                    catalogo.listarPeliculas();
                    break;
                case 4:
                    System.out.println("Ingrese el nombre de la pelicula a buscar");
                    var buscarPeli = entrada.nextLine();
                    catalogo.buscarPelicula(buscarPeli);
                    break;
                case 0:
                    System.out.println("FINALIZANDO PROGRAMA");
                    break;
                default:
                    System.out.println("Opcion incorrecta");

            }
        }
    }
}
