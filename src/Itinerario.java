/*
COPYRIGHT (c) 2013 MySoft snc. All Rights Res.
Classe che definisce l'oggetto Itinerario
@author Alex Bucsai
@version 1.0 2026-05-26
*/
import java.io.Serializable;

public class Itinerario implements Serializable {

    // Dati identificativi e descrittivi dell'itinerario
    private int         id;
    private Destinazione destinazione;
    private int         giorni;
    private String      tipo;
    private String      descrizione;

    // Vincoli sul numero di partecipanti ammessi
    private int    minPartecipanti;
    private int    maxPartecipanti;
    private int    numeroPartecipanti;

    // Dati economici e organizzativi
    private double costo;
    private int    annoCorso;
    private String optional;

    // true se esiste almeno una prenotazione attiva per questo itinerario
    private boolean prenotato;

    /*
    Costruttore completo dell'itinerario.
    @param id                  identificativo univoco
    @param destinazione        oggetto Destinazione associato
    @param giorni              durata in giorni
    @param tipo                tipologia dell'itinerario (es. culturale, naturalistico)
    @param descrizione         testo descrittivo
    @param minPartecipanti     numero minimo di partecipanti richiesti
    @param maxPartecipanti     numero massimo di partecipanti ammessi
    @param costo               costo per singolo partecipante
    @param annoCorso           anno scolastico di riferimento
    @param optional            servizi opzionali inclusi
    @param prenotato           stato di prenotazione dell'itinerario
    @param numeroPartecipanti  numero attuale di partecipanti iscritti
    */
    public Itinerario(int id, Destinazione destinazione, int giorni,
                      String tipo, String descrizione, int minPartecipanti,
                      int maxPartecipanti, double costo, int annoCorso,
                      String optional, boolean prenotato, int numeroPartecipanti) {
        this.id                 = id;
        this.destinazione       = destinazione;
        this.giorni             = giorni;
        this.tipo               = tipo;
        this.descrizione        = descrizione;
        this.minPartecipanti    = minPartecipanti;
        this.maxPartecipanti    = maxPartecipanti;
        this.costo              = costo;
        this.annoCorso          = annoCorso;
        this.optional           = optional;
        this.prenotato          = prenotato;
        this.numeroPartecipanti = numeroPartecipanti;
    }

    /*
    Calcola il costo totale dell'itinerario moltiplicando il costo unitario
    per il numero attuale di partecipanti.
    @return costo totale dell'itinerario
    */
    public double calcolaCostoTotaleItinerario() {
        return costo * numeroPartecipanti;
    }

    // Getter e setter

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

    @Override
    public String toString() {
        return "Itinerario[" +
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
               "]";
    }
}