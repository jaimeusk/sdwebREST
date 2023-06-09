package entradas.restservice;

import java.sql.*;
import java.util.*;

import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

@RestController
public class EventoController {

    private EventosDAO eventosDAO;
    private Evento evento;
    private boolean resultado;
    private List<Evento> listaEventos;

    @PostMapping("/crearEvento")
    public boolean crearEvento(@RequestParam(value = "artista") String artista, 
    							@RequestParam(value = "fecha") String fecha, @RequestParam(value = "lugar") String lugar, 
    							@RequestParam(value = "ciudad") String ciudad, @RequestParam(value = "entradas") int numEntradas,
    							@RequestParam(value = "estadio") int tipoEstadio){

        
		
        	evento = new Evento(artista,fecha,lugar,ciudad,numEntradas,tipoEstadio);
            eventosDAO = new EventosDAO();
            resultado = eventosDAO.agregarEvento(evento);

        return resultado;    

    }
    
    
    @DeleteMapping("/borrarEvento")
    public boolean cancelarEntrada(@RequestParam(value = "idEvento") int idEvento){

        eventosDAO = new EventosDAO();
        resultado = eventosDAO.borrarEvento(idEvento);

        return resultado;

    }

    
    @GetMapping("/listarEventos")
    public List<Evento> listarEventos(@RequestParam(value= "ciudad", defaultValue = "") String ciudad){

            eventosDAO = new EventosDAO();
            if(ciudad.equals("")){
            	listaEventos = eventosDAO.obtenerListaEventos();
            } else {
            	listaEventos = eventosDAO.obtenerListaEventos(ciudad);
            }
            

        return listaEventos;
    }

		
		
    @GetMapping("/obtenerEvento")
    public Evento obtenerEvento(@RequestParam(value = "idEvento") int idEvento){

        eventosDAO = new EventosDAO();
        evento = eventosDAO.obtenerEvento(idEvento);

        return evento;
    }

}