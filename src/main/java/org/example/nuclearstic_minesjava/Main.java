package org.example.nuclearstic_minesjava;
import org.example.nuclearstic_minesjava.Models.CasillaModel;
import org.example.nuclearstic_minesjava.Models.ChefModel;
import org.example.nuclearstic_minesjava.Models.Personaje;
import org.example.nuclearstic_minesjava.Models.Tablero;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int move = 0;
        ChefModel ch = new ChefModel();
        Tablero tm = new Tablero(ch);
        CasillaModel[][] tablero;
        int dimension = tm.generarDificultad("Regular");
        int hazard = tm.generarHazard("Regular");
        int minas = tm.generarMinas(hazard);
        int cactus = tm.generarCactus(hazard);
        tablero = tm.generarTablero(dimension);
        tablero = tm.generarInicioMeta(tablero, dimension);
        tablero = tm.colocarCasillasSeguras(tablero, dimension);
        tablero = tm.colocarHazards(tablero, minas, cactus, dimension);
        tablero = tm.rastrearMinas(tablero, dimension);
        Personaje pr = new Personaje(ch, tablero);
        pr.obtenerCoordenadasInicio();
        tm.actualizarVision(tablero, ch);
        tm.imprimirTablero(tablero, dimension);


        while(ch.isVivo() && !pr.winCondition()){
            if (ch.getRegen()){
                tablero = tm.recolocarHazards(tablero, minas, cactus, dimension);
                tablero = tm.rastrearMinas(tablero, dimension);
                tm.imprimirTablero(tablero, dimension);
                ch.setEsRegen(false);
            }
            move = sc.nextInt();
            switch (move){
                case 1:
                case 2:
                case 3:
                case 4:
                    pr.avanzarChef(move);
                    tm.actualizarVision(tablero, ch);
                    pr.comprobarCasilla();
                    break;
            }
            tm.imprimirTablero(tablero, dimension);
        }
        if (ch.isVivo()) {
            System.out.println("¡Has ganado!");
        } else {
            System.out.println("Has perdido.");
        }
    }
}