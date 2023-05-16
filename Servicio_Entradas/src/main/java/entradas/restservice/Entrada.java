package entradas.restservice;

public class Entrada {
    private int id_compra;
    private int id_eventoAsociado;
    private String dniComprador;
    private String nombreComprador;
    private String artistaEvento;
    private String fechaEvento;
    private String lugarEvento;
    private String ciudadEvento;
    private int cantidadEntradasCompradas;
    private int numGrada;
    

    public Entrada(int id_compra,int id_eventoAsociado,String dniComprador,String nombreComprador,String artistaEvento,String fechaEvento,String lugarEvento,String ciudadEvento,int cantidadEntradasCompradas,int numGrada){

        this.id_compra = id_compra;
        this.id_eventoAsociado = id_eventoAsociado;
        this.dniComprador = dniComprador;
        this.nombreComprador = nombreComprador;
        this.artistaEvento = artistaEvento;
        this.fechaEvento = fechaEvento;
        this.lugarEvento = lugarEvento;
        this.ciudadEvento = ciudadEvento;
        this.cantidadEntradasCompradas = cantidadEntradasCompradas;
        this.numGrada = numGrada;
    }


    public Entrada(int id_eventoAsociado,String dniComprador,String nombreComprador,String artistaEvento,String fechaEvento,String lugarEvento,String ciudadEvento,int cantidadEntradasCompradas,int numGrada){

        //this.id_compra = id_compra;
        this.id_eventoAsociado = id_eventoAsociado;
        this.dniComprador = dniComprador;
        this.nombreComprador = nombreComprador;
        this.artistaEvento = artistaEvento;
        this.fechaEvento = fechaEvento;
        this.lugarEvento = lugarEvento;
        this.ciudadEvento = ciudadEvento;
        this.cantidadEntradasCompradas = cantidadEntradasCompradas;
        this.numGrada = numGrada;
    }


    public int getIdCompra(){
        return id_compra;
    }


    public int getIdEventoAsociado(){
        return id_eventoAsociado;
    }

    public String getDniComprador(){
        return dniComprador;
    }

    public String getNombreComprador(){
        return nombreComprador;
    }

    public String getArtistaEvento(){
        return artistaEvento;
    }

    public String getFechaEvento(){
        return fechaEvento;
    }

    public String getLugarEvento(){
        return lugarEvento;
    }

    public String getCiudadEvento(){
        return ciudadEvento;
    }

    public int getCantidadEntradasCompradas(){
        return cantidadEntradasCompradas;
    }

    public int getNumGrada(){
        return numGrada;
    }
}
