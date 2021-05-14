package Model;

import java.util.*;

/**
 * 
 */
public class Produs {
    public Produs(){

    }
    public Produs(String nume,int id,String desc,double pret,int firmaID ) {
        this.numeProdus=nume;
        this.produsID=id;
        this.descriereProdus=desc;
        this.pret=pret;
        this.firmaID=firmaID;
    }
    private String numeProdus;
    private int produsID;
    private String descriereProdus;
    private double pret;
    private int firmaID;

    public void setProdusID(int produsID) {
        this.produsID = produsID;
    }

    public int getProdusID() {
        return produsID;
    }

    public void setFirmaID(int firmaID) {
        this.firmaID = firmaID;
    }

    public String getDescriereProdus() {
        return descriereProdus;
    }

    public int getFirmaID() {
        return firmaID;
    }

    public double getPret() {
        return pret;
    }

    public String getNumeProdus() {
        return numeProdus;
    }

    public void setDescriereProdus(String descriereProdus) {
        this.descriereProdus = descriereProdus;
    }

    public void setNumeProdus(String numeProdus) {
        this.numeProdus = numeProdus;
    }

    public void setPret(double pret) {
        this.pret = pret;
    }

    @Override
    public String toString() {
        return "Produs{" +
                "numeProdus='" + numeProdus + '\'' +
                ", produsID=" + produsID +
                ", descriereProdus='" + descriereProdus + '\'' +
                ", pret=" + pret +
                ", firmaID=" + firmaID +
                '}';
    }
}