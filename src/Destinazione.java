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

    public Destinazione(String nome, String regione, String nazione) {
        this.id = contatore;
        contatore++;
        this.nome = nome;
        this.regione = regione;
        this.nazione = nazione;
    }

    // metodi getter e setter

    public int getId() {
        return id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setRegione(String regione) {
        this.regione = regione;
    }

    public String getRegione() {
        return regione;
    }

    public void setNazione(String nazione) {
        this.nazione = nazione;
    }

    public String getNazione() {
        return nazione;
    }

    @Override
    public String toString() {
        return "Itinerario " + nome + " - " + regione + " - " + nazione;
    }
}
