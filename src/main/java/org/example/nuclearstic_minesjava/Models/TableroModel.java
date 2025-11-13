package org.example.nuclearstic_minesjava.Models;
import java.util.Random;

public class TableroModel {
    Random random = new Random();
    int minas;
    int cactus;

    public int generarDificultad(String dificultad){
        int dimension = 0;
        switch (dificultad) {
            case "Simple":
                dimension = 8;
                break;
            case "Regular":
                dimension = 12;
                break;
            case "Complejo":
                dimension = 16;
                break;
                case "Extremo":
                dimension = 20;
                break;
            default:
                break;
        }
        System.out.println("Dificultad: " + dificultad + " Dimension: " + dimension);
        return dimension;
    }

    public int generarHazard(String dificultad){
        int hazard = 0;
        switch (dificultad) {
            case "Simple":
                hazard = 10;
                break;
            case "Regular":
                hazard = 25;
                break;
            case "Complejo":
                hazard = 40;
                break;
            case "Extremo":
                hazard = 60;
                break;
            default:
                break;
        }
        System.out.println("Hazard total: " + hazard);
        return hazard;
    }

    public int generarMinas(int hazard){
        minas = random.nextInt((hazard - 3 + 1)) + 3;
        System.out.println("Minas: " + minas);
        return minas;
    }

    public int generarCactus(int hazard){
        int cactus = hazard - minas;
        System.out.println(hazard + " - " + minas + " = " + "Cactus: " + cactus);
        return cactus;
    }

    public CasillaModel[][] generarTablero(int dimension){
        CasillaModel[][] tablero = new CasillaModel[dimension][dimension];
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                tablero[i][j] = new CasillaModel();
            }
        }
        System.out.println("Tablero generado de dimension: " + dimension + "x" + dimension);
        return tablero;
    }

    public CasillaModel[][] generarInicioMeta(CasillaModel[][] tablero, int dimension) {
        int probabilidad = random.nextInt(4 - 1) + 1;
        int probabilidadMeta = random.nextInt(4 - 1) + 1;
        switch (probabilidad) {
            case 1:
                tablero[0][tablero.length / 2].setEsInicio(true);
                tablero[tablero.length - probabilidadMeta][tablero.length / 2].setEsMeta(true);
                System.out.println("Inicio generado en: 0," + tablero.length / 2);
                System.out.println("Meta generado en: " + (tablero.length - 1) + "," + tablero.length / 2);
                break;
            case 2:
                tablero[tablero.length / 2][0].setEsInicio(true);
                tablero[tablero.length / 2][tablero.length - probabilidadMeta].setEsMeta(true);
                System.out.println("Inicio generado en: " + tablero.length / 2 + ",0");
                System.out.println("Meta generado en: " + tablero.length / 2 + "," + (tablero.length - 1));
                break;
            case 3:
                tablero[tablero.length - 1][tablero.length / 2].setEsInicio(true);
                tablero[probabilidadMeta][tablero.length / 2].setEsMeta(true);
                System.out.println("Inicio generado en: " + (tablero.length - 1) + "," + tablero.length / 2);

                break;
            case 4:
                tablero[tablero.length / 2][tablero.length - 1].setEsInicio(true);
                tablero[tablero.length / 2][probabilidadMeta].setEsMeta(true);
                System.out.println("Inicio generado en: " + tablero.length / 2 + "," + (tablero.length - 1));
                System.out.println("Meta generado en: " + tablero.length / 2 + ",0");
                break;
        }
        return tablero;
    }

    public CasillaModel[][] colocarCasillasSeguras(CasillaModel[][] tablero, int dimension){
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                if (tablero[i][j].getEsInicio() || tablero[i][j].getEsMeta()) {
                    if ((i-1)>= 0){
                        tablero[i-1][j].setSegura(true);
                    }
                    if ((i-1)>= 0 && (j-1)>= 0){
                        tablero[i-1][j-1].setSegura(true);
                    }
                    if ((j-1)>= 0){
                        tablero[i][j-1].setSegura(true);
                    }
                    if ((i+1)< tablero.length && (j-1)>=0){
                        tablero[i+1][j-1].setSegura(true);
                    }
                    if ((i+1)< tablero.length){
                        tablero[i+1][j].setSegura(true);
                    }
                    if ((i+1)< tablero.length && (j+1)< tablero.length){
                        tablero[i+1][j+1].setSegura(true);
                    }
                    if ((j+1)< tablero.length){
                        tablero[i][j+1].setSegura(true);
                    }
                    if ((i-1)>= 0 && (j+1)< tablero.length){
                        tablero[i-1][j+1].setSegura(true);
                    }
                }
            }
        }
        return tablero;
    }

    public CasillaModel[][] colocarHazards(CasillaModel[][] tablero, int minasrestantes, int cactusrestantes, int dimension){
        while (minasrestantes > 0) {
            for (int i = 0; i < dimension; i++) {
                for (int j = 0; j < dimension; j++) {
                    int probabilidad = random.nextInt(10 - 1) + 1;
                    if (!tablero[i][j].getEsInicio() && !tablero[i][j].getEsCactus() && !tablero[i][j].getEsSegura() && !tablero[i][j].getEsMina() && !tablero[i][j].getEsMeta()) {
                        if(probabilidad == 1 && minasrestantes > 0){
                            tablero[i][j].setEsMina(true);
                            minasrestantes--;
                        }
                    }
                }
            }
        }
        System.out.println("Minas colocadas...");

        while (cactusrestantes > 0) {
            for (int i = 0; i < dimension; i++) {
                for (int j = 0; j < dimension; j++) {
                    int probabilidad = random.nextInt(8 - 1) + 1;
                    if (!tablero[i][j].getEsInicio() && !tablero[i][j].getEsCactus() && !tablero[i][j].getEsSegura() && !tablero[i][j].getEsMina() && !tablero[i][j].getEsMeta()) {
                        if(probabilidad == 2 && cactusrestantes > 0){
                            tablero[i][j].setEsCactus(true);
                            cactusrestantes--;
                        }
                    }
                }
            }
        }
        System.out.println("Cactus colocados...");
        return tablero;
    }

    public CasillaModel[][] rastrearMinas(CasillaModel[][] tablero, int dimension){
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                if (tablero[i][j].getEsMina() || tablero[i][j].getEsCactus()) {
                    if ((i-1)>= 0){
                       tablero[i-1][j].setMinas();
                    }
                    if ((i-1)>= 0 && (j-1)>= 0){
                        tablero[i-1][j-1].setMinas();
                    }
                    if ((j-1)>= 0){
                        tablero[i][j-1].setMinas();
                    }
                    if ((i+1)< tablero.length && (j-1)>=0){
                        tablero[i+1][j-1].setMinas();
                    }
                    if ((i+1)< tablero.length){
                        tablero[i+1][j].setMinas();
                    }
                    if ((i+1)< tablero.length && (j+1)< tablero.length){
                        tablero[i+1][j+1].setMinas();
                    }
                    if ((j+1)< tablero.length){
                        tablero[i][j+1].setMinas();
                    }
                    if ((i-1)>= 0 && (j+1)< tablero.length){
                        tablero[i-1][j+1].setMinas();
                    }
                }
            }
        }
        return tablero;
    }

    public void imprimirTablero(CasillaModel[][] tablero, int dimension){
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                if(tablero[i][j].getEsMina()){
                    System.out.print("M ");
                } else if(tablero[i][j].getEsCactus()) {
                    System.out.print("C ");
                } else if(tablero[i][j].getEsInicio()){
                    System.out.print("I ");
                } else if (tablero[i][j].getEsMeta()) {
                    System.out.print("F ");
                } else if (tablero[i][j].getEsSegura()){
                    System.out.print("s ");
                } else {
                    System.out.print(tablero[i][j].getMinas() + " ");
                }
            }
            System.out.println();
        }
    }
}