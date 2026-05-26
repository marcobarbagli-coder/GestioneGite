/*
COPYRIGHT (c) 2026 MySoft snc. All Rights Res.
Classe che definisce l'oggetto Destinazione
@author Marco Barbagli
@version 1.0 2026-05-26
*/

// importazione librerie per la serializzazione
import java.io.Serializable;

public class Destinazione implements Serializable {

    private static int contatore = 1;   // contatore statico per generare id univoci progressivi
    private int id;                     // identificativo univoco della destinazione
    private String nome;                // nome della destinazione
    private String regione;             // regione di appartenenza
    private String nazione;             // nazione di appartenenza

    /**
     * Costruisce una nuova Destinazione assegnando automaticamente un id univoco
     * @param nome nome della destinazione
     * @param regione regione della destinazione
     * @param nazione nazione della destinazione
     */
    public Destinazione(String nome, String regione, String nazione) {
        this.id = contatore;
        contatore++;
        this.nome = nome;
        this.regione = regione;
        this.nazione = nazione;
    }

    // metodi getter e setter

    /**
     * Restituisce l'id univoco della destinazione
     * @return id della destinazione
     */
    public int getId() {
        return id;
    }

    /**
     * Imposta il nome della destinazione
     * @param nome nuovo nome della destinazione
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Restituisce il nome della destinazione
     * @return nome della destinazione
     */
    public String getNome() {
        return nome;
    }

    /**
     * Imposta la regione della destinazione
     * @param regione nuova regione della destinazione
     */
    public void setRegione(String regione) {
        this.regione = regione;
    }

    /**
     * Restituisce la regione della destinazione
     * @return regione della destinazione
     */
    public String getRegione() {
        return regione;
    }

    /**
     * Imposta la nazione della destinazione
     * @param nazione nuova nazione della destinazione
     */
    public void setNazione(String nazione) {
        this.nazione = nazione;
    }

    /**
     * Restituisce la nazione della destinazione
     * @return nazione della destinazione
     */
    public String getNazione() {
        return nazione;
    }

    /**
     * Restituisce una rappresentazione testuale della destinazione
     * @return stringa con nome, regione e nazione
     */
    @Override
    public String toString() {
        return "Itinerario " + nome + " - " + regione + " - " + nazione;
    }
}
