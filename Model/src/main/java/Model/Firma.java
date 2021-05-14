package Model;

import java.util.*;

/**
 * 
 */
public class Firma {
    public Firma(){

    }
    public Firma(String Nume,String Locatie,int id) {
        this.nume=Nume;
        this.Locatie=Locatie;
        this.firmaID=id;
    }
    private String nume;
    private String Locatie;
    private int firmaID;

    public void setFirmaID(int firmaID) {
        this.firmaID = firmaID;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public int getFirmaID() {
        return firmaID;
    }

    public String getNume() {
        return nume;
    }

    public String getLocatie() {
        return Locatie;
    }

    public void setLocatie(String locatie) {
        Locatie = locatie;
    }

}