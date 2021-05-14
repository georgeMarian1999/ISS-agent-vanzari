package Repo;

import JDBC.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import Model.Firma;
import Service.RepositoryException;

public class FirmaRepository {
    private JDBCUtils utils;

    public FirmaRepository(Properties props){
        utils=new JDBCUtils(props);
    }
    public Firma findOne(Integer integer){
        Connection con=utils.getConnection();
        try(PreparedStatement preStmt=con.prepareStatement("select * from Firma where firmaID=?")){
            preStmt.setInt(1,integer);
            try(ResultSet result=preStmt.executeQuery()){
                if (result.next()){
                    Integer id=result.getInt("firmaID");
                    String nume,locatie;
                    nume=result.getString("nume");
                    locatie=result.getString("Locatie");
                    Firma P=new Firma(nume,locatie,id);
                    System.out.println(P.getNume());
                    return P;
                }
            }
        }catch (SQLException ex){
            System.out.println(ex);
        }
        return null;
    }
    public int getFirmaIDFromUser(int userID) throws RepositoryException{
        Connection con=utils.getConnection();
        try(PreparedStatement preStmt=con.prepareStatement("select F.firmaID from Firma F inner join User U on U.firmaID=F.firmaID where U.userID=?")){
            preStmt.setInt(1,userID);
            try(ResultSet result=preStmt.executeQuery()){
                if (result.next()){
                    return result.getInt("firmaID");
                }
                else throw new RepositoryException("Not found");
            }
        }catch (SQLException ex){
            throw new RepositoryException("Problem");
        }
    }
}
