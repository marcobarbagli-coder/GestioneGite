//intera classe realizzata da Bucsai Alex
public class Itinerario {


//attributi:
    private int id;
    private Destinazione destinazione;
    private int giorni;
    private String tipo;
    private String descrizione;
    private int minPartecipanti;
    private int maxPartecipanti;
    private double costo;
    private int annoCorso;
    private String optional;
    private boolean prenotato;
    private int numeroPartecipanti;


//costruttore:
    public Itinerario(int id, Destinazione destinazione, int giorni,
                      String tipo, String descrizione, int minPartecipanti,
                      int maxPartecipanti, double costo, int annoCorso,
                      String optional, boolean prenotato, int numeroPartecipanti) {
        this.id = id;
        this.destinazione = destinazione;
        this.giorni = giorni;
        this.tipo = tipo;
        this.descrizione = descrizione;
        this.minPartecipanti = minPartecipanti;
        this.maxPartecipanti = maxPartecipanti;
        this.costo = costo;
        this.annoCorso = annoCorso;
        this.optional = optional;
        this.prenotato = prenotato;
        this.numeroPartecipanti = numeroPartecipanti;
    }


//metodi propri della classe:
    public double calcolaCostoTotaleItinerario() {
        return costo * numeroPartecipanti;
    }


//metodi getter e setter:
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public Destinazione getDestinazione() {
        return destinazione;
    }
    public void setDestinazione(Destinazione destinazione) {
        this.destinazione = destinazione;
    }

    public int getGiorni() {
        return giorni;
    }
    public void setGiorni(int giorni) {
        this.giorni = giorni;
    }

    public String getTipo() {
        return tipo;
    }
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescrizione() {
        return descrizione;
    }
    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public int getMinPartecipanti() {
        return minPartecipanti;
    }
    public void setMinPartecipanti(int minPartecipanti) {
        this.minPartecipanti = minPartecipanti;
    }

    public int getMaxPartecipanti() {
        return maxPartecipanti;
    }
    public void setMaxPartecipanti(int maxPartecipanti) {
        this.maxPartecipanti = maxPartecipanti;
    }

    public double getCosto() {
        return costo;
    }
    public void setCosto(double costo) {
        this.costo = costo;
    }

    public int getAnnoCorso() {
        return annoCorso;
    }
    public void setAnnoCorso(int annoCorso) {
        this.annoCorso = annoCorso;
    }

    public String getOptional() {
        return optional;
    }
    public void setOptional(String optional) {
        this.optional = optional;
    }

    public boolean isPrenotato() {
        return prenotato;
    }
    public void setPrenotato(boolean prenotato) {
        this.prenotato = prenotato;
    }


//metodo toString:
    @Override
    public String toString() {
        return "Itinerario{" +
                "id=" + id +
                ", destinazione=" + destinazione +
                ", giorni=" + giorni +
                ", tipo='" + tipo + '\'' +
                ", descrizione='" + descrizione + '\'' +
                ", minPartecipanti=" + minPartecipanti +
                ", maxPartecipanti=" + maxPartecipanti +
                ", costo=" + costo +
                ", annoCorso=" + annoCorso +
                ", optional='" + optional + '\'' +
                ", prenotato=" + prenotato +
                '}';
    }
}

