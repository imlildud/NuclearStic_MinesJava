package org.example.nuclearstic_minesjava.Models;

public class Personaje {
    ChefModel chef;
    CasillaModel[][] tablero;
    public Personaje(ChefModel chef, CasillaModel[][] tablero){
        this.chef = chef;
        this.tablero = tablero;
    }

    public void obtenerCoordenadasInicio(){
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero.length; j++) {
                if (tablero[i][j].getEsInicio()) {
                    chef.setPosx(i);
                    chef.setPosy(j);
                }
            }
        }
    }

    public void avanzarChef(int direccion){
        switch (direccion){
            case 1: // Arriba
                if (chef.getPosx() > 0) {
                    chef.setPosx(chef.getPosx() -1);
                }
                break;
            case 2: // Abajo
                if (chef.getPosx() < tablero.length - 1) {
                    chef.setPosx(chef.getPosx() +1);
                }
                break;
            case 3: // Izquierda
                if (chef.getPosy() > 0) {
                    chef.setPosy(chef.getPosy() -1);
                }
                break;
            case 4: // Derecha
                if (chef.getPosy() < tablero.length - 1) {
                    chef.setPosy(chef.getPosy() +1);
                }
                break;
        }
    }

    public void comprobarCasilla(){
        if (tablero[chef.getPosx()][chef.getPosy()].getEsMina()) {
            matarchef();
        }
        if (tablero[chef.getPosx()][chef.getPosy()].getEsCactus()) {
            herirchef();
        }
        if (tablero[chef.getPosx()][chef.getPosy()].getEsMeta()) {
            chef.setRescate(true);
            chef.setEsRegen(true);
        }
    }

    public boolean winCondition(){
        return tablero[chef.getPosx()][chef.getPosy()].getEsInicio() && chef.isRescate();
    }

    public void herirchef(){
        chef.setVida(chef.getVida() -1);
        if (chef.getVida() <= 0) {
            matarchef();
        }
    }

    public void matarchef(){
        chef.setVivo(false);
    }
}