package entradas.restservice;

import java.sql.*;
import java.util.*;

public class EntradasDAO{

    private ConexionBBDD conexion;
    private Statement st;
    private ResultSet rs;
    private Connection conn;
    private Entrada entrada;
    private int filas_actualizadas;
    private int filas_insertadas;
    private int filas_eliminadas;
    private boolean resultado;
    private List<Entrada> listaEntradasCompradas;


    public EntradasDAO(){

        conexion = new ConexionBBDD();
        conn = conexion.getConnection();

    }

    public Entrada comprarEntrada(int idEvento, int cantidadEntradasSolicitadas, String nombreComprador, String dniComprador, int numGrada) throws SQLException{

        try{
            st = conn.createStatement();
            String sql_update = "UPDATE eventos SET numentradasdisponibles = numentradasdisponibles - " + cantidadEntradasSolicitadas + " WHERE idevento = " + idEvento;
            String sql_insert = "INSERT INTO compras (idevento,nombrecomprador,dnicomprador,cantidadentradascompradas,grada) VALUES (" + idEvento + ",'" + nombreComprador + "','" + dniComprador + "'," + cantidadEntradasSolicitadas + "," + numGrada + ")";
            String sql_select = "SELECT * FROM eventos WHERE idevento = " + idEvento;
            filas_actualizadas = st.executeUpdate(sql_update);
            filas_insertadas = st.executeUpdate(sql_insert);

            if(filas_actualizadas>0 && filas_insertadas>0){
                rs = st.executeQuery(sql_select);
                while(rs.next()){
                    entrada = new Entrada(idEvento, dniComprador, nombreComprador, rs.getString("artista"), rs.getString("fecha"), rs.getString("lugar"), rs.getString("ciudad"), cantidadEntradasSolicitadas, numGrada);
                }

            }else{
                entrada = null;
            }   

        }catch(SQLException se){
            System.out.println("SQLException: " + se.getMessage());
            se.printStackTrace(System.out);
        }

        rs.close();
        st.close();
        conn.close();

        return entrada;

    }

    public boolean cancelarEntrada(int idCompra) throws SQLException{

        int idEvento = 0;
        int cantidadentradascompradas = 0;
        
        try{
            String sql_select = "SELECT * FROM compras WHERE idcompra = " + idCompra;
            String sql_delete = "DELETE FROM compras WHERE idcompra = " + idCompra;
            String sql_update = "UPDATE eventos SET numentradasdisponibles = numentradasdisponibles + " + cantidadentradascompradas;

            st = conn.createStatement();
            rs = st.executeQuery(sql_select);
            while(rs.next()){
                idEvento = rs.getInt("idevento");
                cantidadentradascompradas = rs.getInt("cantidadentradascompradas");
            }

            filas_eliminadas = st.executeUpdate(sql_delete);
            filas_actualizadas = st.executeUpdate(sql_update);

            if(filas_eliminadas>0 && filas_actualizadas>0 && idEvento>0){
                resultado = true;
            }
            else{
                resultado = false;
            }

            rs.close();
            st.close();
            conn.close();

        }catch(SQLException se){
            System.out.println("SQLException: " + se.getMessage());
            se.printStackTrace(System.out);
        }

      

        return resultado;
    }

    public List<Entrada> listarEntradasCompradas(String dni) throws SQLException{

        try{
            Statement st = conn.createStatement();
            String sql = "SELECT * FROM compras NATURAL JOIN eventos WHERE dnicomprador = '" + dni + "'";
            rs = st.executeQuery(sql);

            listaEntradasCompradas = new ArrayList<Entrada>();

            while(rs.next()){
                entrada = new Entrada(rs.getInt("idcompra"),rs.getInt("idevento"), dni, rs.getString("nombrecomprador"), rs.getString("artista"), rs.getString("fecha"), rs.getString("lugar"), rs.getString("ciudad"), rs.getInt("cantidadentradascompradas"), rs.getInt("grada"));
                listaEntradasCompradas.add(entrada);
            }
            rs.close();
            st.close();
            conn.close();

        }catch(SQLException se){
            System.out.println("SQLException: " + se.getMessage());
            se.printStackTrace(System.out);
        }

       

        return listaEntradasCompradas;
        
    }

    public boolean cambiarAsiento(int idCompra, int numGrada) throws SQLException{

        try{
            st = conn.createStatement();
            String sql = "UPDATE compras SET grada = " + numGrada + " WHERE idcompra = " + idCompra;
            filas_actualizadas = st.executeUpdate(sql);

            if(filas_actualizadas>0){
                resultado = true;
            }
            else{
                resultado = false;
            }

            st.close();
            conn.close();

        }catch(SQLException se){
            System.out.println("SQLException: " + se.getMessage());
            se.printStackTrace(System.out);
        }

        

        return resultado;
    }

}