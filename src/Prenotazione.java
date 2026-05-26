/*
COPYRIGHT (c) 2013 MySoft snc. All Rights Res.
Classe che definisce l'oggetto Prenotazione, contenente itinerario,
classi e partecipanti, con supporto al calcolo dei costi e all'annullamento.
@author Alex Bucsai
@version 1.00 2026-05-26
*/
//importazione librerie:
import java.io.Serializable;
import java.util.ArrayList;

public class Prenotazione implements Serializable {

    //attributi:
    private int id;                              // identificativo univoco della prenotazione
    private Itinerario itinerario;               // itinerario associato alla prenotazione
    private ArrayList<Classe> classi;            // classi scolastiche partecipanti
    private ArrayList<Alunno> partecipanti;      // singoli alunni partecipanti
    private double acconto;                      // acconto calcolato (10% del costo totale)
    private boolean annullata;                   // true se la prenotazione è stata annullata
    private String motivoAnnullamento;           // motivo dell'eventuale annullamento

    /**
     * Inizializza una nuova prenotazione con id e itinerario specificati.
     * Le liste di classi e partecipanti vengono create vuote.
     * Lo stato di annullamento viene impostato a false.
     * @param id         identificativo univoco della prenotazione
     * @param itinerario itinerario associato alla prenotazione
     */
    public Prenotazione(int id, Itinerario itinerario) {
        this.id = id;
        this.itinerario = itinerario;
        classi = new ArrayList<>();
        partecipanti = new ArrayList<>();
        annullata = false;
    }

    //metodi propri della classe:

    /**
     * Aggiunge un alunno alla lista dei partecipanti della prenotazione.
     * @param a alunno da aggiungere
     */
    public void aggiungiPartecipante(Alunno a) {
        partecipanti.add(a);
    }

    /**
     * Rimuove un alunno dalla lista dei partecipanti della prenotazione.
     * Non produce effetti se l'alunno non è presente nella lista.
     * @param a alunno da rimuovere
     */
    public void rimuoviPartecipante(Alunno a) {
        partecipanti.remove(a);
    }

    /**
     * Aggiunge una classe scolastica alla lista delle classi partecipanti.
     * @param c classe da aggiungere
     */
    public void aggiungiClasse(Classe c) {
        classi.add(c);
    }

    /**
     * Calcola il costo totale della prenotazione moltiplicando
     * il costo unitario dell'itinerario per il numero di partecipanti.
     * @return costo totale in euro
     */
    public double calcolaCostoTotale() {
        return itinerario.getCosto() * partecipanti.size();
    }

    /**
     * Calcola e memorizza l'acconto pari al 10% del costo totale.
     * @return valore dell'acconto in euro
     */
    // calcola acconto 10%
    public double calcolaAcconto() {
        acconto = calcolaCostoTotale() * 0.10;
        return acconto;
    }

    /**
     * Imposta la prenotazione come annullata e registra il motivo.
     * @param motivo descrizione del motivo dell'annullamento
     */
    public void annullaGita(String motivo) {
        annullata = true;
        motivoAnnullamento = motivo;
    }

    /**
     * Restituisce il numero corrente di partecipanti alla prenotazione.
     * @return numero intero di alunni presenti nella lista partecipanti
     */
    public int numeroPartecipanti() {
        return partecipanti.size();
    }

    //metodi getter e setter:

    /**
     * @return identificativo univoco della prenotazione
     */
    public int getIdPrenotazione() {
        return id;
    }

    /**
     * @param id nuovo identificativo da assegnare alla prenotazione
     */
    public void setIdPrenotazione(int id) {
        this.id = id;
    }

    /**
     * @return itinerario associato alla prenotazione
     */
    public Itinerario getItinerario() {
        return itinerario;
    }

    /**
     * @param itinerario nuovo itinerario da associare alla prenotazione
     */
    public void setItinerario(Itinerario itinerario) {
        this.itinerario = itinerario;
    }

    /**
     * @return lista delle classi scolastiche partecipanti
     */
    public ArrayList<Classe> getClassi() {
        return classi;
    }

    /**
     * @param classi nuova lista di classi da associare alla prenotazione
     */
    public void setClassi(ArrayList<Classe> classi) {
        this.classi = classi;
    }

    /**
     * @return lista degli alunni partecipanti
     */
    public ArrayList<Alunno> getPartecipanti() {
        return partecipanti;
    }

    /**
     * @param partecipanti nuova lista di alunni da associare alla prenotazione
     */
    public void setPartecipanti(ArrayList<Alunno> partecipanti) {
        this.partecipanti = partecipanti;
    }

    /**
     * @return valore dell'acconto attualmente memorizzato
     */
    public double getAcconto() {
        return acconto;
    }

    /**
     * @param acconto nuovo valore di acconto da impostare
     */
    public void setAcconto(double acconto) {
        this.acconto = acconto;
    }

    /**
     * @return true se la prenotazione è stata annullata, false altrimenti
     */
    public boolean isAnnullata() {
        return annullata;
    }

    /**
     * @return motivo dell'annullamento, oppure null se non annullata
     */
    public String getMotivoAnnullamento() {
        return motivoAnnullamento;
    }

    /**
     * Restituisce una rappresentazione testuale della prenotazione
     * con id, destinazione, numero partecipanti, costo totale,
     * acconto e stato di annullamento.
     * @return stringa descrittiva della prenotazione
     */
    @Override
    public String toString() {
        return "Prenotazione[ ID: " + id +
            "\nDestinazione: " + itinerario.getDestinazione().getNome() +
            "\nPartecipanti: " + partecipanti.size() +
            "\nCosto Totale: " + calcolaCostoTotale() +
            "\nAcconto: " + calcolaAcconto() +
            "\nAnnullata: " + annullata +
            "]";
    }
}