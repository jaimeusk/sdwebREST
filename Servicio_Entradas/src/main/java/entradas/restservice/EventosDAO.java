package entradas.restservice;

import java.sql.*;
import java.util.*;


class EventosDAO {

    private ConexionBBDD conexion;
    private Connection conn;
    private boolean resultado;

    public EventosDAO(){
        conexion = new ConexionBBDD();
        conn = conexion.getConnection();
    }

    public boolean agregarEvento(Evento evento){
        
        try{
            Statement stAux = conn.createStatement();
            String sqlAux = "SELECT MAX(idevento) FROM eventos";
            ResultSet rsAux = stAux.executeQuery(sqlAux);
            int ultimoIdEvento = 0;

            
            while(rsAux.next()){
                ultimoIdEvento = rsAux.getInt("max") + 1;
            }
            rsAux.close();
            stAux.close();

            
            Statement st = conn.createStatement();
            
            String sql = "INSERT INTO eventos (idevento, artista,fecha,lugar,ciudad,numentradasdisponibles,tipoEstadio) VALUES (" + ultimoIdEvento + ",'" + evento.getArtista() + "','" + evento.getFecha() + "','" + evento.getLugar() + "','" + evento.getCiudad() + "'," + evento.getEntradas() + "," + evento.getTipoEstadio() + ")";
            System.out.println(sql);
            int filas = st.executeUpdate(sql);

            st.close();

            if(filas>0){
                System.out.println("Evento " + evento.getArtista() + " añadido correctamente\n");
                resultado = true;

            }else{
                resultado = false;
            }
            
        }catch(SQLException se){
            System.out.println("SQLException: " + se.getMessage());
            se.printStackTrace(System.out);
        }
        
        return resultado;
    }


    public boolean borrarEvento (int idEvento){

        try{
            Statement st = conn.createStatement();
            
            // Borrar primero las entradas para evitar problemas de FK
            String sql_deleteC = "DELETE FROM compras WHERE idevento=" + idEvento;
            int filasC = st.executeUpdate(sql_deleteC);
            if(filasC>0){
                System.out.println("Entradas evento " + idEvento + " eliminadas correctamente\n");
            }
            else{
                System.out.println("Error al eliminar el evento o no hay entradas asociadas a " + idEvento + "\n");
            }


            String sql_deleteE = "DELETE FROM eventos WHERE idevento=" + idEvento;
            int filasE = st.executeUpdate(sql_deleteE);
            
            st.close();
            
            // Solo comprobar el borrado del evento, ya que no todos los eventos tienen entradas asociadas
            // y devuelve errores.
            if(filasE>0){
                System.out.println("Evento " + idEvento + " eliminado correctamente\n");
                resultado = true;
            }
            else{
                System.out.println("Error al eliminar el evento " + idEvento);
                resultado = false;
            }
        }catch(SQLException se){
            System.out.println("SQLException: " + se.getMessage());
            se.printStackTrace(System.out);
        }   
        return resultado;
    }

    public Evento obtenerEvento(int idEvento){
        Evento evento = new Evento();         
        
        try{
            Statement st = conn.createStatement();
            String sql = "SELECT * FROM eventos WHERE idevento="+idEvento;
            ResultSet rs = st.executeQuery(sql);
                      
            
            while(rs.next()){
                evento.setId(rs.getInt("idevento"));
                evento.setArtista(rs.getString("artista"));
                evento.setEntradas(rs.getInt("numentradasdisponibles"));
                evento.setFecha(rs.getString("fecha"));
                evento.setLugar(rs.getString("lugar"));
                evento.setCiudad(rs.getString("ciudad"));
                evento.setTipoEstadio(rs.getInt("tipoEstadio"));
            }

            System.out.println("Evento " + idEvento +" obtenido con éxito\n");

            rs.close();
            st.close();

            
        }catch(SQLException se){
            System.out.println("SQLException: " + se.getMessage());
            se.printStackTrace(System.out);
        }
        
        return evento;  
    }

    public List<Evento> obtenerListaEventos() {

        List<Evento> lista = new ArrayList<Evento>();

        try{
            Statement st = conn.createStatement();
            String sql = "SELECT * FROM eventos ORDER BY idevento";
            ResultSet rs = st.executeQuery(sql);
            
            while(rs.next()){
                Evento evento = new Evento();
                evento.setId(rs.getInt("idevento"));
                evento.setArtista(rs.getString("artista"));
                evento.setFecha(rs.getString("fecha"));
                evento.setLugar(rs.getString("lugar"));
                evento.setCiudad(rs.getString("ciudad"));
                evento.setEntradas(rs.getInt("numentradasdisponibles"));
                evento.setTipoEstadio(rs.getInt("tipoEstadio"));
                lista.add(evento);
            }

            System.out.println("Eventos agregados a la lista\n");
            rs.close();
            st.close();

            
        }catch(SQLException se){
            System.out.println("ERROR AL OBTENER LISTA EVENTOS\n");
            System.out.println("SQLException: " + se.getMessage());
            se.printStackTrace(System.out);
        }
        
        
        return lista;
    }
    
    public List<Evento> obtenerListaEventos(String ciudad) {

        List<Evento> lista = new ArrayList<Evento>();

        try{
            Statement st = conn.createStatement();
            String sql = "SELECT * FROM eventos WHERE ciudad='" + ciudad + "' ORDER BY idevento" ;
            ResultSet rs = st.executeQuery(sql);
				
				System.out.println(sql+"\n");            
            
            
            while(rs.next()){
                Evento evento = new Evento();
                evento.setId(rs.getInt("idevento"));
                evento.setArtista(rs.getString("artista"));
                evento.setFecha(rs.getString("fecha"));
                evento.setLugar(rs.getString("lugar"));
                evento.setCiudad(rs.getString("ciudad"));
                evento.setEntradas(rs.getInt("numentradasdisponibles"));
                evento.setTipoEstadio(rs.getInt("tipoEstadio"));
                lista.add(evento);
            }

            System.out.println("Eventos por ciudad agregados a la lista\n");
            rs.close();
            st.close();

            
        }catch(SQLException se){
            System.out.println("ERROR AL OBTENER LISTA EVENTOS\n");
            System.out.println("SQLException: " + se.getMessage());
            se.printStackTrace(System.out);
        }
        
        
        return lista;
    }
}
