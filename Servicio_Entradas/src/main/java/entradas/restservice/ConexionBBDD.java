package entradas.restservice;

import java.sql.*;

public class ConexionBBDD {

    private Connection conn;

    public ConexionBBDD() {

	    try{

	        Class.forName("org.postgresql.Driver"); //Probar a quitarlo para ver si funciona

	        String url = "jdbc:postgresql://localhost:5432/entradas_rest";
	        String user = "rest";
	        String pass = "rest20";

	        conn = DriverManager.getConnection(url,user,pass);

    	}
	    catch(ClassNotFoundException cnfe) {
           System.err.println(cnfe);
        }
        catch(SQLException sqle) {
            System.err.println(sqle);
        }
    }

    public Connection getConnection() {
	    return conn;
    }
}
