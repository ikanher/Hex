
package com.mycompany.unicafe;

public class Kassapaate {

    private int kassassaRahaa;
    private int edulliset;
    private int maukkaat;
    
    private static final int HINTA_EDULLINEN = 250;
    private static final int HINTA_MAUKAS = 400;

    public Kassapaate() {
        this.kassassaRahaa = 100000;
    }

    public int syoEdullisesti(int maksu) {
        if (maksu >= HINTA_EDULLINEN) {
            this.kassassaRahaa = kassassaRahaa + HINTA_EDULLINEN;
            ++this.edulliset;
            return maksu - HINTA_EDULLINEN;
        } else {
            return maksu;
        }
    }

    public int syoMaukkaasti(int maksu) {
        if (maksu >= HINTA_MAUKAS) {
            this.kassassaRahaa = kassassaRahaa + HINTA_MAUKAS;
            this.maukkaat++;
            return maksu - HINTA_MAUKAS;
        } else {
            return maksu;
        }
    }

    public boolean syoEdullisesti(Maksukortti kortti) {
        if (kortti.saldo() >= HINTA_EDULLINEN) {
            kortti.otaRahaa(HINTA_EDULLINEN);
            this.edulliset++;
            return true;
        } else {
            return false;
        }
    }

    public boolean syoMaukkaasti(Maksukortti kortti) {
        if (kortti.saldo() >= HINTA_MAUKAS) {
            kortti.otaRahaa(HINTA_MAUKAS);
            this.maukkaat++;
            return true;
        } else {
            return false;
        }
    }

    public void lataaRahaaKortille(Maksukortti kortti, int summa) {
        if (summa >= 0) {
            kortti.lataaRahaa(summa);
            this.kassassaRahaa += summa;
        } else {
            return;
        }
    }

    public int kassassaRahaa() {
        return kassassaRahaa;
    }

    public int maukkaitaLounaitaMyyty() {
        return maukkaat;
    }

    public int edullisiaLounaitaMyyty() {
        return edulliset;
    }
}