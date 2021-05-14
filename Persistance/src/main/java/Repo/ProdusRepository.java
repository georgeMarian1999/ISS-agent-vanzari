package Repo;

import JDBC.JDBCUtils;
import Model.Produs;
import Service.RepositoryException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ProdusRepository {
    private JDBCUtils utils;

    public ProdusRepository(Properties props){
        utils=new JDBCUtils(props);
    }

    public List<Produs> getAll(){
        Connection con=utils.getConnection();
        List<Produs> Produse=new ArrayList<>();
        try(PreparedStatement preStmt=con.prepareStatement("select * from Produs")) {
            try(ResultSet result=preStmt.executeQuery()) {
                while (result.next()) {
                    int id = result.getInt("produsID");
                    String nume,desc;
                    double pret;
                    int firmaID;
                    nume=result.getString("numeProdus");
                    desc=result.getString("descriereProdus");
                    pret=result.getDouble("pret");
                    firmaID=result.getInt("firmaID");
                    Produs P=new Produs(nume,id,desc,pret,firmaID);
                    Produse.add(P);
                }
            }
        } catch (SQLException e) {
            System.out.println(e);

            System.out.println("Error DB "+e);
        }
        return Produse;
    }
    public void adaugareProdus(Produs produs) throws RepositoryException {
        Connection con=utils.getConnection();
        try(PreparedStatement preStmt=con.prepareStatement("insert into Produs values (?,?,?,?,?)")){
            preStmt.setInt(1,produs.getProdusID());
            preStmt.setString(2,produs.getNumeProdus());
            preStmt.setString(3,produs.getDescriereProdus());
            preStmt.setDouble(4,produs.getPret());
            preStmt.setInt(5,produs.getFirmaID());
            int result=preStmt.executeUpdate();
        }catch (SQLException ex){
            throw new RepositoryException(ex.getMessage());
        }
    }
    public int getMaxID(){
        Connection con=utils.getConnection();
        try(PreparedStatement preStmt=con.prepareStatement("select MAX(produsID) as id from Produs ")){
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
    public List<Produs> getAllFromFirma(int idFirma) throws RepositoryException{
        Connection con=utils.getConnection();
        List<Produs> Produse=new ArrayList<>();
        try(PreparedStatement preStmt=con.prepareStatement("select * from Produs where firmaID=?")) {
            preStmt.setInt(1,idFirma);
            try(ResultSet result=preStmt.executeQuery()) {
                while (result.next()) {
                    int id = result.getInt("produsID");
                    String nume,desc;
                    double pret;
                    int firmaID;
                    nume=result.getString("numeProdus");
                    desc=result.getString("descriereProdus");
                    pret=result.getDouble("pret");
                    firmaID=result.getInt("firmaID");
                    Produs P=new Produs(nume,id,desc,pret,firmaID);
                    Produse.add(P);
                }
            }
        } catch (SQLException ex) {
            throw new RepositoryException(ex.getMessage());
        }
        return Produse;
    }
    public double getPretFromID(int produsID) throws RepositoryException{
        Connection con=utils.getConnection();
        try(PreparedStatement preStmt=con.prepareStatement("select pret from Produs where produsID=?")){
            preStmt.setInt(1,produsID);
            try(ResultSet result= preStmt.executeQuery()){
                if(result.next()){
                    double pret=result.getDouble("pret");
                    return pret;
                }
                else throw new RepositoryException("Not found");
            }
        }catch (SQLException ex){
            throw new RepositoryException(ex.getMessage());
        }
    }
    public int getStocProdus(int produsID) throws RepositoryException{
//        int id=getIDFromNamePrice(nume,pret);
        Connection con=utils.getConnection();
        try(PreparedStatement preStmt=con.prepareStatement("select SUM(cantitate) as cantitate from Stoc  where produsID=?")){
            preStmt.setInt(1,produsID);
            try(ResultSet result=preStmt.executeQuery()) {
                if (result.next()){
                    return result.getInt("cantitate");
                }
                else throw new RepositoryException("Product not found");
            }
        }catch(SQLException ex){
            throw new RepositoryException(ex.getMessage());
        }
    }
    public int getIDFromNamePrice(String nume, double pret){
        int id;
        Connection con=utils.getConnection();
        try(PreparedStatement preStmt=con.prepareStatement("select produsID from Produs where numeProdus=? and pret=?")){
            preStmt.setString(1,nume);
            preStmt.setDouble(2,pret);
            try(ResultSet result=preStmt.executeQuery()) {
                if (result.next()){
                    id=result.getInt("produsID");
                    return id;
                }
            }
        }catch(SQLException ex){
            System.out.println(ex);
        }
        return 0;
    }
}
