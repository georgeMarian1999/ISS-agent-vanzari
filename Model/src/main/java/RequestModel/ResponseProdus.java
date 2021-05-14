package RequestModel;

import Model.Produs;

public class ResponseProdus {
    private String numeProdus;
    private int produsID;
    private String descriereProdus;
    private double pret;
    private int firmaID;

    private int stoc;
    public ResponseProdus(){

    }
    public ResponseProdus(Produs produs,int stoc){
        this.numeProdus=produs.getNumeProdus();
        this.produsID= produs.getProdusID();
        this.descriereProdus=produs.getDescriereProdus();
        this.pret=produs.getPret();
        this.firmaID=produs.getFirmaID();
        this.stoc=stoc;
    }

    public int getFirmaID() {
        return firmaID;
    }

    public void setProdusID(int produsID) {
        this.produsID = produsID;
    }

    public int getProdusID() {
        return produsID;
    }

    public void setFirmaID(int firmaID) {
        this.firmaID = firmaID;
    }

    public void setPret(double pret) {
        this.pret = pret;
    }

    public void setNumeProdus(String numeProdus) {
        this.numeProdus = numeProdus;
    }

    public void setDescriereProdus(String descriereProdus) {
        this.descriereProdus = descriereProdus;
    }

    public String getNumeProdus() {
        return numeProdus;
    }

    public double getPret() {
        return pret;
    }

    public String getDescriereProdus() {
        return descriereProdus;
    }

    public int getStoc() {
        return stoc;
    }

    public void setStoc(int stoc) {
        this.stoc = stoc;
    }
}

