package org.example.nuclearstic_minesjava.Models;

public class CasillaModel {
    private int minas = 0;
    private boolean mina;
    private boolean cactus;
    private boolean inicio;
    private boolean meta;
    private boolean segura;

    public CasillaModel() {
        this.minas = 0;
        this.mina = false;
        this.cactus = false;
        this.inicio = false;
        this.meta = false;
        this.segura = false;
    }

    public boolean getEsInicio(){
        return inicio;
    }

    public void setEsInicio(boolean inicio){
        this.inicio = inicio;
    }

    public boolean getEsMeta(){
        return meta;
    }

    public void setEsMeta(boolean meta){
        this.meta = meta;
    }

    public int getMinas() {
        return minas;
    }

    public void setMinas() {
        minas ++;
    }

    public boolean getEsMina() {
        return mina;
    }

    public void setEsMina(boolean mina){
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

    public void setSegura (boolean segura) {
        this.segura = segura;
    }
}
