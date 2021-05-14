package RequestModel;

public class RequestStoc {
    private int produsID;
    private int cant;
    public RequestStoc(){

    }
    public RequestStoc(int produsID,int cant){
        this.produsID=produsID;
        this.cant=cant;
    }

    public void setCant(int cant) {
        this.cant = cant;
    }

    public int getCant() {
        return cant;
    }

    public int getProdusID() {
        return produsID;
    }

    public void setProdusID(int produsID) {
        this.produsID = produsID;
    }
}
