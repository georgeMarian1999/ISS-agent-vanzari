package Model;

import java.util.*;

/**
 * 
 */
public class User {

    public User() {
    }
    public User(String username, String password, int id, String Nume, String prenume,int firmaID,AngajatType type) {
        this.username = username;
        this.password = password;
        this.userID = id;
        this.Nume = Nume;
        this.Prenume = prenume;
        this.firmaID=firmaID;
        this.type=type;
    }

    private String username;
    private String password;
    private int userID;
    private String Nume;
    private String Prenume;
    private int firmaID;
    private AngajatType type;

    public int getUserID() {
        return userID;
    }

    public AngajatType getType() {
        return type;
    }

    public void setType(AngajatType type) {
        this.type = type;
    }

    public int getFirmaID() {
        return firmaID;
    }

    public void setFirmaID(int firmaID) {
        this.firmaID = firmaID;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNume() {
        return Nume;
    }

    public void setNume(String nume) {
        Nume = nume;
    }

    public String getPrenume() {
        return Prenume;
    }

    public void setPrenume(String prenume) {
        Prenume = prenume;
    }
}