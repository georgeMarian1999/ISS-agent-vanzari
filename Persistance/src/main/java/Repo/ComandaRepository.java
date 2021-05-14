package Repo;

import JDBC.JDBCUtils;
import Model.Comanda;
import Model.Status;
import Service.RepositoryException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ComandaRepository {
    private JDBCUtils utils;

    public ComandaRepository(Properties props){
        utils=new JDBCUtils(props);
    }
    public List<Comanda> getIstoricComenzi(int userID){
        Connection con=utils.getConnection();
        List<Comanda> comenzi=new ArrayList<>();
        try(PreparedStatement preStmt=con.prepareStatement("select * from Comanda where userID=?")) {
            preStmt.setInt(1,userID);
            try(ResultSet result=preStmt.executeQuery()) {
                while (result.next()) {
                    int produsID=result.getInt("produsID");
                    String data=result.getString("data");
                    int comandaID=result.getInt("comandaID");
                    double valoare=result.getDouble("valoare");
                    int cantitate=result.getInt("cantitate");
                    String status=result.getString("status");
                    Status type=Status.Procesare;
                    if(status.equals("Returnare")){
                        type=Status.Returnare;
                    }
                    if(status.equals("Confirmata")){
                        type= Status.Confirmata;
                    }
                    if(status.equals("Anulata")){
                        type= Status.Anulata;
                    }
                    if(status.equals("Returnata")){
                        type= Status.Returnata;
                    }
                    Comanda comanda=new Comanda(comandaID,userID,produsID,data,cantitate,valoare,type);
                    comenzi.add(comanda);
                }
            }
        } catch (SQLException e) {
            System.out.println(e);

            System.out.println("Error DB "+e);
        }
        return comenzi;
    }
    public void plasareComanda(Comanda comanda) throws RepositoryException {
        Connection con=utils.getConnection();
        try(PreparedStatement preStmt=con.prepareStatement("insert into Comanda values (?,?,?,?,?,?,?)")){
            preStmt.setInt(1,comanda.getUserID());
            preStmt.setInt(2,comanda.getProdusID());
            preStmt.setString(3,comanda.getData());
            preStmt.setInt(4,comanda.getCantitate());
            preStmt.setDouble(5,comanda.getValoare());
            preStmt.setString(6,comanda.getStatus().toString());
            preStmt.setInt(7,comanda.getComandaID());
            int result=preStmt.executeUpdate();
        }catch(SQLException ex){
            throw new RepositoryException(ex.getMessage());
        }
    }
    public int getMaxID(){
        Connection con=utils.getConnection();
        try(PreparedStatement preStmt=con.prepareStatement("select MAX(comandaID) as id from Comanda ")){
            try(ResultSet result= preStmt.executeQuery()){
                if(result.next()){
                    return result.getInt("id");
                }
            }
        }catch (SQLException ex){
            System.out.println(ex);
        }
        return 0;
    }
    public void confirmareComanda(int comandaID) throws RepositoryException{
        Connection con=utils.getConnection();
        try(PreparedStatement preStmt=con.prepareStatement("update Comanda set status=? where comandaID=?")){
            preStmt.setString(1,Status.Confirmata.toString());
            preStmt.setInt(2,comandaID);
            int result=preStmt.executeUpdate();
        }catch (SQLException ex){
            throw new RepositoryException(ex.getMessage());
        }
    }
    public void anulareComanda(int comandaID) throws RepositoryException{
        Connection con=utils.getConnection();
        try(PreparedStatement preStmt=con.prepareStatement("update Comanda set status=? where comandaID=?")){
            preStmt.setString(1,Status.Anulata.toString());
            preStmt.setInt(2,comandaID);
            int result=preStmt.executeUpdate();
        }catch (SQLException ex){
            throw new RepositoryException(ex.getMessage());
        }
    }
    public void returnareComanda(int comandaID) throws RepositoryException{
        Connection con=utils.getConnection();
        try(PreparedStatement preStmt=con.prepareStatement("update Comanda set status=? where comandaID=?")){
            preStmt.setString(1,Status.Returnare.toString());
            preStmt.setInt(2,comandaID);
            int result=preStmt.executeUpdate();
        }catch (SQLException ex){
            throw new RepositoryException(ex.getMessage());
        }
    }
    public void confirmareReturnare(int comandaID) throws RepositoryException{
        Connection con=utils.getConnection();
        try(PreparedStatement preStmt=con.prepareStatement("update Comanda set status=? where comandaID=?")){
            preStmt.setString(1,Status.Returnata.toString());
            preStmt.setInt(2,comandaID);
            int result=preStmt.executeUpdate();
        }catch (SQLException ex){
            throw new RepositoryException(ex.getMessage());
        }
    }
    public int getProdusIDFromComanda(int comandaID){
        Connection con=utils.getConnection();
        try(PreparedStatement preStmt=con.prepareStatement("select produsID from Comanda where comandaID=?")){
            preStmt.setInt(1,comandaID);
            try(ResultSet result= preStmt.executeQuery()){
                if(result.next()){{
                    return result.getInt("produsID");
                }}
            }
        }catch (SQLException ex){
            System.out.println(ex);
        }
        return 0;
    }
    public int getComandaCant(int comandaID){
        Connection con=utils.getConnection();
        try(PreparedStatement preStmt=con.prepareStatement("select cantitate from Comanda where comandaID=?")){
            preStmt.setInt(1,comandaID);
            try(ResultSet result= preStmt.executeQuery()){
                if(result.next()){{
                    return result.getInt("cantitate");
                }}
            }
        }catch (SQLException ex){
            System.out.println(ex);
        }
        return 0;
    }
    public List<Comanda> vizualizareComenziPrimite(int firmaID,Status status) throws RepositoryException{
        Connection con=utils.getConnection();
        List<Comanda> comenzi=new ArrayList<>();
        try(PreparedStatement preStmt=con.prepareStatement("select C.userID,C.produsID,C.data,C.valoare,C.cantitate,C.comandaID from Comanda C inner join Produs P on C.produsID=P.produsID where P.firmaID=? AND C.status=?")) {
            preStmt.setInt(1,firmaID);
            preStmt.setString(2,status.toString());
            try(ResultSet result=preStmt.executeQuery()) {
                while (result.next()) {
                    int userID=result.getInt("userID");
                    int produsID=result.getInt("produsID");
                    String data=result.getString("data");
                    double valoare=result.getDouble("valoare");
                    int cantitate=result.getInt("cantitate");
                    int comandaID=result.getInt("comandaID");
                    Comanda comanda=new Comanda(comandaID,userID,produsID,data,cantitate,valoare,status);
                    comenzi.add(comanda);
                }
            }
        } catch (SQLException ex) {
            throw new RepositoryException(ex.getMessage());
        }
        return comenzi;
    }
    public List<Comanda> vizualizareComenziPrimite(int firmaID) throws RepositoryException{
        Connection con=utils.getConnection();
        List<Comanda> comenzi=new ArrayList<>();
        try(PreparedStatement preStmt=con.prepareStatement("select C.userID,C.produsID,C.data,C.valoare,C.cantitate,C.comandaID,C.status from Comanda C inner join Produs P on C.produsID=P.produsID where P.firmaID=?")) {
            preStmt.setInt(1,firmaID);
            try(ResultSet result=preStmt.executeQuery()) {
                while (result.next()) {
                    int userID=result.getInt("userID");
                    int produsID=result.getInt("produsID");
                    String data=result.getString("data");
                    double valoare=result.getDouble("valoare");
                    int cantitate=result.getInt("cantitate");
                    int comandaID=result.getInt("comandaID");
                    String status=result.getString("status");
                    Comanda comanda=new Comanda();
                    if(status.equals(Status.Procesare.toString())){
                        comanda=new Comanda(comandaID,userID,produsID,data,cantitate,valoare,Status.Procesare);
                    }
                    if(status.equals(Status.Confirmata.toString())){
                        comanda=new Comanda(comandaID,userID,produsID,data,cantitate,valoare,Status.Confirmata);
                    }
                    if(status.equals(Status.Anulata.toString())){
                        comanda=new Comanda(comandaID,userID,produsID,data,cantitate,valoare,Status.Anulata);
                    }
                    if(status.equals(Status.Returnare.toString())){
                        comanda=new Comanda(comandaID,userID,produsID,data,cantitate,valoare,Status.Returnare);
                    }
                    if(status.equals(Status.Returnata.toString())){
                        comanda=new Comanda(comandaID,userID,produsID,data,cantitate,valoare,Status.Returnata);
                    }

                    comenzi.add(comanda);
                }
            }
        } catch (SQLException ex) {
            throw new RepositoryException(ex.getMessage());
        }
        return comenzi;
    }
    public List<Comanda> vizualizareReturnari(int firmaID) throws RepositoryException{
        Connection con=utils.getConnection();
        List<Comanda> comenzi=new ArrayList<>();
        try(PreparedStatement preStmt=con.prepareStatement("select C.userID,C.produsID,C.data,C.valoare,C.cantitate,C.comandaID from Comanda C inner join Produs P on C.produsID=P.produsID where P.firmaID=? AND C.status=?")) {
            preStmt.setInt(1,firmaID);
            preStmt.setString(2,Status.Returnare.toString());
            try(ResultSet result=preStmt.executeQuery()) {
                while (result.next()) {
                    int userID=result.getInt("userID");
                    int produsID=result.getInt("produsID");
                    String data=result.getString("data");
                    double valoare=result.getDouble("valoare");
                    int cantitate=result.getInt("cantitate");
                    int comandaID=result.getInt("comandaID");
                    Comanda comanda=new Comanda(comandaID,userID,produsID,data,cantitate,valoare,Status.Returnare);
                    comenzi.add(comanda);
                }
            }
        } catch (SQLException ex) {
            throw new RepositoryException(ex.getMessage());
        }
        return comenzi;
    }
}
