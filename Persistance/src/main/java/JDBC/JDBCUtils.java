package JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class JDBCUtils {
    private Properties JDBCProps;
    public JDBCUtils(Properties props){
        this.JDBCProps=props;
    }
    private Connection instance=null;
    public Connection getNewConnection(){
        String url=JDBCProps.getProperty("Firma.jdbc.url");
        Connection con=null;
        try{
            con= DriverManager.getConnection(url);
        }
        catch(SQLException ex){
            System.out.println(ex);
        }
        return con;
    }
    public Connection getConnection(){

        try{
            if(instance==null || instance.isClosed())
                instance=this.getNewConnection();
        }catch(SQLException ex){
            System.out.println(ex);
        }
        return instance;
    }
}
