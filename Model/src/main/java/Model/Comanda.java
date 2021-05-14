package Model;

/**
 * 
 */
public class Comanda {
    public Comanda(){

    }
    public Comanda(int id,int userid,int produsid,String data,int cant,double valoare,Status status) {
        this.userID=userid;
        this.produsID=produsid;
        this.data=data;
        this.cantitate=cant;
        this.valoare=valoare;
        this.status=status;
        this.comandaID=id;
    }

    private int comandaID;
    private int userID;
    private int produsID;
    private String data;
    private int cantitate;
    private double valoare;
    private Status status;

    public int getProdusID() {
        return produsID;
    }

    public void setProdusID(int produsID) {
        this.produsID = produsID;
    }

    public void setCantitate(int cantitate) {
        this.cantitate = cantitate;
    }

    public int getCantitate() {
        return cantitate;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public double getValoare() {
        return valoare;
    }

    public void setComandaID(int comandaID) {
        this.comandaID = comandaID;
    }

    public int getComandaID() {
        return comandaID;
    }

    public Status getStatus() {
        return status;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Comanda{" +
                "userID=" + userID +
                ", produsID=" + produsID +
                ", data='" + data + '\'' +
                ", cantitate=" + cantitate +
                ", valoare=" + valoare +
                ", status=" + status +
                '}';
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setValoare(double valoare) {
        this.valoare = valoare;
    }
}