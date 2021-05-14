package RequestModel;

public class RequestProdus {
    private String numeProdus;
    private String descriereProdus;
    private double pret;
    private int cant;
    public RequestProdus(){

    }
    public RequestProdus(String nume,String desc,double pret,int cant) {
        this.numeProdus=nume;
        this.descriereProdus= desc;
        this.pret=pret;
        this.cant=cant;
    }

    public String getDescriereProdus() {
        return descriereProdus;
    }

    public double getPret() {
        return pret;
    }

    public int getCant() {
        return cant;
    }

    public void setCant(int cant) {
        this.cant = cant;
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


}
