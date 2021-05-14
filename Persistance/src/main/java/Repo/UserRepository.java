package Repo;

import JDBC.JDBCUtils;
import Model.AngajatType;
import Model.User;
import Service.RepositoryException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class UserRepository {
    private JDBCUtils utils;

    public UserRepository(Properties props){
        utils=new JDBCUtils(props);
    }
    public User LocalLogin(String username, String password)  throws RepositoryException {
        Connection con=utils.getConnection();
        try(PreparedStatement preStmt=con.prepareStatement("select userID,username,password,Nume,Prenume,firmaID,type from User where username=? AND password=?")){
            preStmt.setString(1,username);
            preStmt.setString(2,password);
            try(ResultSet result=preStmt.executeQuery()){
                if(result.next()){
                    int userID=result.getInt("userID");
                    String Nume,Prenume;
                    Nume=result.getString("Nume");
                    Prenume=result.getString("Prenume");
                    int firmaID=result.getInt("firmaID");
                    String type=result.getString("type");
                    if(type.equals("agent")){
                        return new User(username,password,userID,Nume,Prenume,firmaID,AngajatType.agent);
                    }
                    else return new User(username,password,userID,Nume,Prenume,firmaID,AngajatType.manager);

                }
                else throw new RepositoryException("Wrong credentials");
            }
        }catch(SQLException ex){
            throw new RepositoryException(ex.getMessage());
        }
    }
}
