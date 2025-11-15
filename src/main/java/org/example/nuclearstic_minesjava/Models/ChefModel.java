package org.example.nuclearstic_minesjava.Models;

public class ChefModel {
    private int posx;
    private int posy;
    private int banderillas;
    private int vida;
    private boolean rescate;
    private boolean regen;
    private boolean vivo;

    public ChefModel() {
        this.posx = 0;
        this.posy = 0;
        this.banderillas = 5;
        this.vida = 5;
        this.rescate = false;
        this.regen = false;
        this.vivo = true;
    }

    public int getPosx() {
        return posx;
    }

    public void setPosx(int posx) {
        this.posx = posx;
    }

    public int getPosy() {
        return posy;
    }

    public void setPosy(int posy) {
        this.posy = posy;
    }

    public int getBanderillas() {
        return banderillas;
    }

    public void setBanderillas(int banderillas) {
        this.banderillas = banderillas;
    }

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public boolean isRescate() {
        return rescate;
    }

    public void setRescate(boolean rescate) {
        this.rescate = rescate;
    }

    public boolean isVivo() {
        return vivo;
    }

    public void setVivo(boolean vivo) {
        this.vivo = vivo;
    }

    public boolean getRegen(){
        return regen;
    }

    public void setEsRegen(boolean regen){
        this.regen = regen;
    }
}