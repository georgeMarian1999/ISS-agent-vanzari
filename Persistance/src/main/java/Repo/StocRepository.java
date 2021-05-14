package Repo;

import JDBC.JDBCUtils;
import Service.RepositoryException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class StocRepository {
    private JDBCUtils utils;

    public StocRepository(Properties props){
        utils=new JDBCUtils(props);
    }
    public void comandaPlasata(int produsID,int cantitate){
        int cantInitiala=getCantitateProdus(produsID);
        Connection con=utils.getConnection();
        try(PreparedStatement preStmt=con.prepareStatement("update Stoc set cantitate=? where produsID=?")){
            preStmt.setInt(1,cantInitiala-cantitate);
            preStmt.setInt(2,produsID);
            int result=preStmt.executeUpdate();
        }catch (SQLException ex){
            System.out.println(ex);
        }
    }
    public int getCantitateProdus(int produsID){
        Connection con=utils.getConnection();
        try(PreparedStatement preStmt=con.prepareStatement("select SUM(cantitate) as cantitate from Stoc  where produsID=?")){
            preStmt.setInt(1,produsID);
            try(ResultSet result=preStmt.executeQuery()) {
                if (result.next()){
                    int cant=result.getInt("cantitate");
                    return cant;
                }
            }
        }catch(SQLException ex){
            System.out.println(ex);
        }
        return 0;
    }
    public void adaugaStoc(int cant,int produsID) throws RepositoryException {
        int cantInitiala=getCantitateProdus(produsID);
        Connection con=utils.getConnection();
        try(PreparedStatement preStmt=con.prepareStatement("update Stoc set cantitate=? where produsID=?")){
            preStmt.setInt(1,cantInitiala+cant);
            preStmt.setInt(2,produsID);
            int result=preStmt.executeUpdate();
        }catch (SQLException ex){
            throw new RepositoryException(ex.getMessage());
        }
    }

    public void adaugaProdus(int userID,int produsID,int cant){
        Connection con=utils.getConnection();
        try(PreparedStatement preStmt=con.prepareStatement("insert into Stoc values (?,?,?)")){
            preStmt.setInt(1,userID);
            preStmt.setInt(2,produsID);
            preStmt.setInt(3,cant);
            int result=preStmt.executeUpdate();
        }catch (SQLException ex){
            System.out.println(ex);
        }
    }
}
