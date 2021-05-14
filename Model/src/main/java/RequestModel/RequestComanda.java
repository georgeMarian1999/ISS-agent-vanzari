package RequestModel;

public class RequestComanda {
    private int produsID;
    private int cant;
    private int userID;
    private String data;
    public RequestComanda(){

    }
    public RequestComanda(int produsID,int cant,int userID,String data){
        this.produsID=produsID;
        this.cant=cant;
        this.userID=userID;
        this.data=data;
    }

    public void setProdusID(int produsID) {
        this.produsID = produsID;
    }

    public int getProdusID() {
        return produsID;
    }

    public int getCant() {
        return cant;
    }

    public void setCant(int cant) {
        this.cant = cant;
    }

    public void setData(String data) {
        this.data = data;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getData() {
        return data;
    }

    public int getUserID() {
        return userID;
    }
}
