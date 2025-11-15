package org.example.nuclearstic_minesjava.Models;

public class CasillaModel {
    private int minas = 0;
    private boolean oculta;
    private boolean mina;
    private boolean cactus;
    private boolean inicio;
    private boolean meta;
    private boolean segura;
    private boolean flageada;
    private boolean marcada;

    public CasillaModel() {
        this.minas = 0;
        this.oculta = true;
        this.mina = false;
        this.cactus = false;
        this.inicio = false;
        this.meta = false;
        this.segura = false;
        this.flageada = false;
        this.marcada = false;
    }

    public boolean getEsInicio() {
        return inicio;
    }

    public void setEsInicio(boolean inicio) {
        this.inicio = inicio;
    }

    public boolean getEsMeta() {
        return meta;
    }

    public void setEsMeta(boolean meta) {
        this.meta = meta;
    }

    public int getMinas() {
        return minas;
    }

    public void setMinas() {
        minas++;
    }

    public boolean getEstaOculta() {
        return oculta;
    }

    public void setEstaOculta(boolean oculta) {
        this.oculta = oculta;
    }

    public boolean getEsMina() {
        return mina;
    }

    public void setEsMina(boolean mina) {
        this.mina = mina;
    }

    public boolean getEsCactus() {
        return cactus;
    }

    public void setEsCactus(boolean cactus) {
        this.cactus = cactus;
    }

    public boolean getEsSegura() {
        return segura;
    }

    public void setSegura(boolean segura) {
        this.segura = segura;
    }

    public boolean getEsFlageada() {
        return flageada;
    }

    public void setFlageada(boolean flageada) {
        this.flageada = flageada;
    }

    public boolean getEsMarcada() {
        return marcada;
    }

    public void setEsMarcada(boolean marcada){
        this.marcada = marcada;
    }
}