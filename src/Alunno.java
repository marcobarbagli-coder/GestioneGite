/*
COPYRIGHT (c) 2026 MySoft snc. All Rights Res.
Classe che definisce l'oggetto Alunno con i relativi dati anagrafici e scolastici
@author Marco Barbagli
@version 1.0 2026-05-26
*/

// importazione librerie per la serializzazione
import java.io.Serializable;

public class Alunno implements Serializable {

    private int id;                 // identificativo univoco dell'alunno
    private String nome;            // nome dell'alunno
    private String cognome;         // cognome dell'alunno
    private int eta;                // età dell'alunno in anni
    private String classe;          // classe scolastica di appartenenza (es. "3A")
    private int annoCorso;          // anno del corso scolastico
    private boolean minorenne;      // true se l'alunno ha meno di 18 anni
    private boolean autorizzazione; // true se il genitore ha fornito autorizzazione (richiesta solo per minorenni)
    private boolean rinunciaMedica; // true se è stata registrata una rinuncia medica

    // metodo costruttore
    public Alunno(int id, String nome, String cognome, int eta, String classe, int annoCorso) {
        this.id = id;
        this.nome = nome;
        this.cognome = cognome;
        this.eta = eta;
        this.classe = classe;
        this.annoCorso = annoCorso;
        this.minorenne = eta < 18;      // calcolo automatico della minore età
        this.autorizzazione = false;    // nessuna autorizzazione al momento della creazione
        this.rinunciaMedica = false;    // nessuna rinuncia medica al momento della creazione
    }

    // metodi getter e setter

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getCognome() {
        return cognome;
    }

    /**
     * Imposta l'età dell'alunno e aggiorna automaticamente il flag minorenne
     * @param eta nuova età dell'alunno in anni
     */
    public void setEta(int eta) {
        this.eta = eta;
        this.minorenne = eta < 18; // aggiornamento automatico del flag in base alla nuova età
    }

 
    public int getEta() {
        return eta;
    }

    public void setClasse(String classe) {
        this.classe = classe;
    }


    public String getClasse() {
        return classe;
    }

    public void setAnnoCorso(int annoCorso) {
        this.annoCorso = annoCorso;
    }

    public int getAnnoCorso() {
        return annoCorso;
    }

    public boolean isMinorenne() {
        return minorenne;
    }

    public void setAutorizzazione(boolean autorizzazione) {
        this.autorizzazione = autorizzazione;
    }

    public boolean isAutorizzazione() {
        return autorizzazione;
    }

    public void setRinunciaMedica(boolean rinunciaMedica) {
        this.rinunciaMedica = rinunciaMedica;
    }

    public boolean isRinunciaMedica() {
        return rinunciaMedica;
    }

    @Override
    public String toString() {
        return "ID: " + id +
                " | " + nome + " " + cognome +
                " | Classe: " + classe +
                " | Età: " + eta +
                " | Minorenne: " + (minorenne ? "SI" : "NO") +
                " | Autorizzazione: " + (autorizzazione ? "SI" : "NO") +
                " | Rinuncia: " + (rinunciaMedica ? "SI" : "NO");
    }
}