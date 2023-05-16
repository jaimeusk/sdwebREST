package entradas.restservice;

import java.sql.*;
import java.util.*;

import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

@RestController
public class EntradaController {

    private EntradasDAO entradasDAO;
    private Entrada entrada;
    private boolean resultado;
    private List<Entrada> listaEntradasCompradas;

    @PostMapping("/comprar")
    public Entrada comprarEntrada(@RequestParam(value = "idEvento") int idEvento, @RequestParam(value = "cantidad") int cantidadEntradasSolicitadas, @RequestParam(value = "nombre") String nombre, @RequestParam(value = "dni") String dni, @RequestParam(value = "grada") int numGrada){

        try{
            entradasDAO = new EntradasDAO();
            entrada = entradasDAO.comprarEntrada(idEvento,cantidadEntradasSolicitadas,nombre,dni,numGrada);
        }
        catch(SQLException e){
            System.out.println("SQLException: " + e.getMessage());
            e.printStackTrace(System.out);
        }

        return entrada;    

    }
    
    @DeleteMapping("/cancelarEntrada")
    public boolean cancelarEntrada(@RequestParam(value = "idCompra") int idCompra){

        try{
            entradasDAO = new EntradasDAO();
            resultado = entradasDAO.cancelarEntrada(idCompra);
        }
        catch(SQLException e){
            System.out.println("SQLException: " + e.getMessage());
            e.printStackTrace(System.out);
        }

        return resultado;

    }

    @GetMapping("/listarentradas")
    public List<Entrada> listarEntradas(@RequestParam(value= "dni") String dni){

        try{
            entradasDAO = new EntradasDAO();
            listaEntradasCompradas = entradasDAO.listarEntradasCompradas(dni);

        }
        catch(SQLException e){
            System.out.println("SQLException: " + e.getMessage());
            e.printStackTrace(System.out);
        }

        return listaEntradasCompradas;
    }

    @PutMapping("/cambiarasiento")
    public boolean cambiarAsiento(@RequestParam(value= "idCompra") int idCompra, @RequestParam(value= "numgrada") int numGrada){

        try{
            entradasDAO = new EntradasDAO();
            resultado = entradasDAO.cambiarAsiento(idCompra,numGrada);
        }
        catch(SQLException e){
            System.out.println("SQLException: " + e.getMessage());
            e.printStackTrace(System.out);
    }

        return resultado;

    }
}
