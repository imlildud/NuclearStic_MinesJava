package org.example.nuclearstic_minesjava;
import org.example.nuclearstic_minesjava.Models.CasillaModel;
import org.example.nuclearstic_minesjava.Models.TableroModel;

public class Main {
    public static void main(String[] args) {
        TableroModel tm = new TableroModel();
        CasillaModel[][] tablero;
        int dimension = tm.generarDificultad("Simple");
        int hazard = tm.generarHazard("Simple");
        int minas = tm.generarMinas(hazard);
        int cactus = tm.generarCactus(hazard);
        tablero = tm.generarTablero(dimension);
        tablero = tm.generarInicioMeta(tablero, dimension);
        tablero = tm.colocarCasillasSeguras(tablero, dimension);
        tablero = tm.colocarHazards(tablero, minas, cactus, dimension);
        tablero = tm.rastrearMinas(tablero, dimension);
        tm.imprimirTablero(tablero, dimension);
    }
}
