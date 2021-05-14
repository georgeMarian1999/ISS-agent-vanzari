package Model;

import java.util.*;

/**
 * 
 */
public class Stoc {
    public Stoc(){

    }
    public Stoc(int stocid,int userID,int produsID,int cant) {

        this.userID=userID;
        this.produsID=produsID;
        this.cantitate=cant;
    }

    private int userID;
    private int produsID;
    private int cantitate;

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getUserID() {
        return userID;
    }

    public int getCantitate() {
        return cantitate;
    }

    public int getProdusID() {
        return produsID;
    }



    public void setCantitate(int cantitate) {
        this.cantitate = cantitate;
    }

    public void setProdusID(int produsID) {
        this.produsID = produsID;
    }


}