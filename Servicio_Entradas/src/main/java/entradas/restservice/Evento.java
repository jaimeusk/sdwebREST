package entradas.restservice;

public class Evento {
    private int id_evento;
    private String artistaEvento;
    private String fechaEvento;
    private String lugarEvento;
    private String ciudadEvento;
    private int numEntradasDisponibles;
    private int tipoEstadio;
    
    public Evento(){}

    public Evento(int id_evento,String artistaEvento,String fechaEvento,String lugarEvento,String ciudadEvento,int numEntradasDisponibles,int tipoEstadio){

        this.id_evento = id_evento;
        this.artistaEvento = artistaEvento;
        this.fechaEvento = fechaEvento;
        this.lugarEvento = lugarEvento;
        this.ciudadEvento = ciudadEvento;
        this.numEntradasDisponibles = numEntradasDisponibles;
        this.tipoEstadio = tipoEstadio;
    }

		
	
    public Evento(String artistaEvento,String fechaEvento,String lugarEvento,String ciudadEvento,int numEntradasDisponibles,int tipoEstadio){

        this.artistaEvento = artistaEvento;
        this.fechaEvento = fechaEvento;
        this.lugarEvento = lugarEvento;
        this.ciudadEvento = ciudadEvento;
        this.numEntradasDisponibles = numEntradasDisponibles;
        this.tipoEstadio = tipoEstadio;
    }
   


    public void setId(int id){
        this.id_evento = id;
    }

    public void setArtista(String artista){
        this.artistaEvento = artista;
    }

    public void setFecha(String fecha){
        this.fechaEvento = fecha;
    }

    public void setLugar(String lugar){
        this.lugarEvento = lugar;
    }

    public void setCiudad(String ciudad){
        this.ciudadEvento = ciudad;
    }

    public void setEntradas(int entradas){
        this.numEntradasDisponibles = entradas;
    }

    public void setTipoEstadio(int tipoEstadio){
        this.tipoEstadio = tipoEstadio;
    }
    
    
    
    
    public int getId(){
        return id_evento;
    }

    public String getArtista(){
        return artistaEvento;
    }

    public String getFecha(){
        return fechaEvento;
    }

    public String getLugar(){
        return lugarEvento;
    }

    public String getCiudad(){
        return ciudadEvento;
    }

    public int getEntradas(){
        return numEntradasDisponibles;
    }

    public int getTipoEstadio(){
        return tipoEstadio;
    }
}