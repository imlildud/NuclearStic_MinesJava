package org.example.nuclearstic_minesjava.Models;
import java.util.Random;

public class Tablero {
    ChefModel chef;
    public Tablero(ChefModel chef) {
        this.chef = chef;
    }
    Random random = new Random();
    int minas;

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
                hazard = 7;
                break;
            case "Regular":
                hazard = 15;
                break;
            case "Complejo":
                hazard = 30;
                break;
            case "Extremo":
                hazard = 50;
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
        int probabilidad = random.nextInt(4);
        int bordeinf = 0;
        int bordesup = tablero.length -1;
        int pos = random.nextInt(dimension);
        int cx;
        int cy;

        switch (probabilidad) {
            case 0:
                tablero[bordeinf][pos].setEsInicio(true);
                System.out.println("Inicio generado");

                cx = random.nextInt(dimension);
                cy = random.nextInt(dimension);
                if (cx < tablero.length / 2) {
                    cx += (tablero.length / 2);
                }
                tablero[cx][cy].setEsMeta(true);
                System.out.println("Meta generada");
            break;
            case 1:
                tablero[pos][bordeinf].setEsInicio(true);
                System.out.println("Inicio generado");

                cx = random.nextInt(dimension);
                cy = random.nextInt(dimension);
                if (cy < tablero.length / 2) {
                    cy += (tablero.length / 2);
                }
                tablero[cx][cy].setEsMeta(true);
                System.out.println("Meta generada");
                break;
            case 2:
                tablero[bordesup][pos].setEsInicio(true);
                System.out.println("Inicio generado");

                cx = random.nextInt(dimension);
                cy = random.nextInt(dimension);
                if (cx > tablero.length / 2) {
                    cx -= (tablero.length / 2);
                }
                tablero[cx][cy].setEsMeta(true);
                System.out.println("Meta generada");
                break;
            case 3:
                tablero[pos][bordesup].setEsInicio(true);
                System.out.println("Inicio generado");

                cx = random.nextInt(dimension);
                cy = random.nextInt(dimension);
                if (cy > tablero.length / 2) {
                    cy -= (tablero.length / 2);
                }
                tablero[cx][cy].setEsMeta(true);
                System.out.println("Meta generada");
                break;
        }
        return tablero;
    }

    public CasillaModel[][] colocarCasillasSeguras(CasillaModel[][] tablero, int dimension){
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                tablero[i][j].setEstaOculta(false);
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
        int x;
        int y;
        while (minasrestantes > 0) {
            x = random.nextInt(dimension);
            y = random.nextInt(dimension);
            if (!tablero[x][y].getEsInicio() && !tablero[x][y].getEsCactus() && !tablero[x][y].getEsSegura() && !tablero[x][y].getEsMina() && !tablero[x][y].getEsMeta() && !tablero[x][y].getEsMarcada()) {
                tablero[x][y].setEsMina(true);
                minasrestantes --;
            }
        }
        System.out.println("Minas colocadas...");

        while (cactusrestantes > 0) {
            x = random.nextInt(dimension);
            y = random.nextInt(dimension);
            if (!tablero[x][y].getEsInicio() && !tablero[x][y].getEsCactus() && !tablero[x][y].getEsSegura() && !tablero[x][y].getEsMina() && !tablero[x][y].getEsMeta() && !tablero[x][y].getEsMarcada()) {
                tablero[x][y].setEsCactus(true);
                cactusrestantes --;
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

    public void actualizarVision(CasillaModel[][] tablero, ChefModel chef) {

        int n = tablero.length;
        int px = chef.getPosx();
        int py = chef.getPosy();

        // Ocultar casillas
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (!tablero[i][j].getEsInicio() && !tablero[i][j].getEsMeta() && !tablero[i][j].getEsSegura()) {
                    tablero[i][j].setEstaOculta(true);
                }
            }
        }

        // Jugador visible
        tablero[px][py].setEstaOculta(false);

        // Limites del 5x5
        int minX = Math.max(0, px - 2);
        int maxX = Math.min(n - 1, px + 2);
        int minY = Math.max(0, py - 2);
        int maxY = Math.min(n - 1, py + 2);

        // Cardinales: arriba, abajo, izquierda, derecha
        int[][] direccionesCardinales = {
                {-1, 0}, {1, 0}, {0, -1}, {0, 1}
        };

        // Marca para saber si puntos cardinales estan bloqueados
        boolean[][] visible = new boolean[n][n];
        visible[px][py] = true;

        // Revelar en las 4 direcciones principales
        for (int[] d : direccionesCardinales) {

            int x = px;
            int y = py;

            while (true) {
                x += d[0];
                y += d[1];

                if (x < minX || x > maxX || y < minY || y > maxY) break;

                // Si el rastreador pisa numero, no revelar mas en esa direccion
                if (tablero[px][py].getMinas() > 0) {
                    break;
                }

                // Mina/cactus: no revelar, detiene el rastreador
                if (tablero[x][y].getEsMina() || tablero[x][y].getEsCactus()) {
                    break;
                }

                tablero[x][y].setEstaOculta(false);
                visible[x][y] = true;

                // Numero > 0 bloquea continuación
                if (tablero[x][y].getMinas() > 0) {
                    break;
                }
            }
        }

        // DIAGONALES
        // Solo revelamos diagonal si sus dos casillas cardinales están visibles

        int[][] diagonales = {
                {-1, -1}, {-1, 1},
                { 1, -1}, { 1, 1}
        };

        for (int[] d : diagonales) {
            int dx = d[0];
            int dy = d[1];

            // casilla diagonal
            int dx1 = px + dx;
            int dy1 = py + dy;

            if (dx1 < minX || dx1 > maxX || dy1 < minY || dy1 > maxY) continue;

            // casillas cardinales requeridas
            int cx1 = px + dx;
            int cy1 = py;      // vertical
            int cx2 = px;
            int cy2 = py + dy; // horizontal

            boolean puedeVer = true;

            // Cardinal 1
            if (cx1 >= minX && cx1 <= maxX && cy1 >= minY && cy1 <= maxY) {
                if (!visible[cx1][cy1] || tablero[cx1][cy1].getMinas() > 0) {
                    puedeVer = false;
                }
                if (tablero[cx1][cy1].getEsMina() || tablero[cx1][cy1].getEsCactus()) {
                    puedeVer = false;
                }
            }

            // Cardinal 2
            if (cx2 >= minX && cx2 <= maxX && cy2 >= minY && cy2 <= maxY) {
                if (!visible[cx2][cy2] || tablero[cx2][cy2].getMinas() > 0) {
                    puedeVer = false;
                }
                if (tablero[cx2][cy2].getEsMina() || tablero[cx2][cy2].getEsCactus()) {
                    puedeVer = false;
                }
            }

            if (puedeVer) {
                tablero[dx1][dy1].setEstaOculta(false);
            }
        }
    }

    public CasillaModel[][] recolocarHazards(CasillaModel[][] tablero, int minasrestantes, int cactusrestantes, int dimension) {
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero.length; j++) {
                if (tablero[i][j].getEsMina()) {
                    if (tablero[i][j].getEsFlageada()) {
                        tablero[i][j].setEsMarcada(true);
                        minasrestantes--;
                    } else {
                        tablero[i][j].setEsMina(false);
                    }
                }

                // CACTUS
                if (tablero[i][j].getEsCactus()) {
                    if (tablero[i][j].getEsFlageada()) {
                        tablero[i][j].setEsMarcada(true);
                        cactusrestantes--;
                    } else {
                        tablero[i][j].setEsCactus(false);
                    }
                }

                if (!tablero[i][j].getEsMarcada()) {
                    tablero[i][j].setFlageada(false);
                }
            }
        }
        System.out.println("Hazard regenerados");
        return this.colocarHazards(tablero, minasrestantes, cactusrestantes, dimension);
    }

    public void imprimirTablero(CasillaModel[][] tablero, int dimension){
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                if (i == chef.getPosx() && chef.getPosy() == j) {
                    System.out.print("♜ ");
                }else if (tablero[i][j].getEstaOculta()){
                    System.out.print("■ ");
                }else if(!tablero[i][j].getEstaOculta() && tablero[i][j].getEsMina()){
                    System.out.print("M ");
                } else if(!tablero[i][j].getEstaOculta() && tablero[i][j].getEsCactus()) {
                    System.out.print("C ");
                } else if(!tablero[i][j].getEstaOculta() && tablero[i][j].getEsInicio()){
                    System.out.print("I ");
                } else if (!tablero[i][j].getEstaOculta() && tablero[i][j].getEsMeta() && !chef.isRescate()) {
                    System.out.print("F ");
                } else {
                    System.out.print(tablero[i][j].getMinas() + " ");
                }
            }
            System.out.println();
        }
    }
}