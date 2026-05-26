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

    /**
     * Costruisce un nuovo Alunno con i dati anagrafici e scolastici forniti.
     * Il flag minorenne viene calcolato automaticamente in base all'età.
     * Autorizzazione e rinuncia medica vengono inizializzate a false.
     * @param id identificativo univoco dell'alunno
     * @param nome nome dell'alunno
     * @param cognome cognome dell'alunno
     * @param eta età dell'alunno in anni
     * @param classe classe scolastica di appartenenza
     * @param annoCorso anno del corso scolastico
     */
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

    /**
     * Restituisce l'id univoco dell'alunno
     * @return id dell'alunno
     */
    public int getId() {
        return id;
    }

    /**
     * Restituisce il nome dell'alunno
     * @return nome dell'alunno
     */
    public String getNome() {
        return nome;
    }

    /**
     * Restituisce il cognome dell'alunno
     * @return cognome dell'alunno
     */
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

    /**
     * Restituisce l'età dell'alunno
     * @return età in anni
     */
    public int getEta() {
        return eta;
    }

    /**
     * Imposta la classe scolastica dell'alunno
     * @param classe nuova classe scolastica
     */
    public void setClasse(String classe) {
        this.classe = classe;
    }

    /**
     * Restituisce la classe scolastica dell'alunno
     * @return classe scolastica
     */
    public String getClasse() {
        return classe;
    }

    /**
     * Imposta l'anno del corso scolastico dell'alunno
     * @param annoCorso nuovo anno del corso
     */
    public void setAnnoCorso(int annoCorso) {
        this.annoCorso = annoCorso;
    }

    /**
     * Restituisce l'anno del corso scolastico dell'alunno
     * @return anno del corso
     */
    public int getAnnoCorso() {
        return annoCorso;
    }

    /**
     * Indica se l'alunno è minorenne (età inferiore a 18 anni)
     * @return true se minorenne, false altrimenti
     */
    public boolean isMinorenne() {
        return minorenne;
    }

    /**
     * Imposta lo stato di autorizzazione del genitore per l'alunno minorenne
     * @param autorizzazione true se l'autorizzazione è stata concessa
     */
    public void setAutorizzazione(boolean autorizzazione) {
        this.autorizzazione = autorizzazione;
    }

    /**
     * Indica se l'alunno ha ricevuto l'autorizzazione del genitore
     * @return true se autorizzato, false altrimenti
     */
    public boolean isAutorizzazione() {
        return autorizzazione;
    }

    /**
     * Imposta lo stato della rinuncia medica per l'alunno
     * @param rinunciaMedica true se la rinuncia medica è stata registrata
     */
    public void setRinunciaMedica(boolean rinunciaMedica) {
        this.rinunciaMedica = rinunciaMedica;
    }

    /**
     * Indica se è stata registrata una rinuncia medica per l'alunno
     * @return true se la rinuncia medica è presente, false altrimenti
     */
    public boolean isRinunciaMedica() {
        return rinunciaMedica;
    }

    /**
     * Restituisce una rappresentazione testuale completa dell'alunno
     * @return stringa con id, nome, cognome, classe, età, minorenne, autorizzazione e rinuncia
     */
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