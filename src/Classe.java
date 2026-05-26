/*
COPYRIGHT (c) 2026 MySoft snc. All Rights Res.
Classe che definisce l'oggetto Classe scolastica, con i relativi alunni e prenotazioni
@author Alex Bucsai
@version 1.0 2026-05-26
*/

// importazione librerie per la gestione delle liste
import java.util.ArrayList;

public class Classe {

    private int id;                                 // identificativo univoco della classe
    private String nome;                            // nome della classe (es. "3A")
    private int anno;                               // anno scolastico di riferimento
    private ArrayList<Alunno> alunni;               // lista degli alunni appartenenti alla classe
    private ArrayList<Prenotazione> prenotazioni;   // lista delle prenotazioni effettuate dalla classe (solo la classe può prenotare, non il singolo alunno)

    /**
     * Costruisce una nuova Classe con lista alunni e prenotazioni inizialmente vuote
     * @param id identificativo univoco della classe
     * @param nome nome della classe
     * @param anno anno scolastico di riferimento
     */
    public Classe(int id, String nome, int anno) {
        this.id = id;
        this.nome = nome;
        this.anno = anno;
        this.alunni = new ArrayList<>();
        this.prenotazioni = new ArrayList<>();
    }

    // metodi propri della classe

    /**
     * Aggiunge un alunno alla lista della classe
     * @param a alunno da aggiungere
     */
    public void aggiungiAlunno(Alunno a) {
        alunni.add(a);
    }

    /**
     * Rimuove un alunno dalla lista della classe
     * @param a alunno da rimuovere
     */
    public void rimuoviAlunno(Alunno a) {
        alunni.remove(a);
    }

    /**
     * Restituisce il numero di alunni presenti nella classe
     * @return numero di alunni
     */
    public int numeroAlunni() {
        return alunni.size();
    }

    /**
     * Aggiunge una prenotazione alla lista della classe
     * @param p prenotazione da aggiungere
     */
    public void aggiungiPrenotazione(Prenotazione p) {
        prenotazioni.add(p);
    }

    /**
     * Rimuove una prenotazione dalla lista della classe
     * @param p prenotazione da rimuovere
     */
    public void rimuoviPrenotazione(Prenotazione p) {
        prenotazioni.remove(p);
    }

    // metodi getter e setter

    /**
     * Restituisce l'id della classe
     * @return id della classe
     */
    public int getId() {
        return id;
    }

    /**
     * Imposta l'id della classe
     * @param id nuovo id della classe
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Restituisce il nome della classe
     * @return nome della classe
     */
    public String getNome() {
        return nome;
    }

    /**
     * Imposta il nome della classe
     * @param nome nuovo nome della classe
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Restituisce l'anno scolastico della classe
     * @return anno scolastico
     */
    public int getAnno() {
        return anno;
    }

    /**
     * Imposta l'anno scolastico della classe
     * @param anno nuovo anno scolastico
     */
    public void setAnno(int anno) {
        this.anno = anno;
    }

    /**
     * Restituisce la lista degli alunni della classe
     * @return lista degli alunni
     */
    public ArrayList<Alunno> getAlunni() {
        return alunni;
    }

    /**
     * Imposta la lista degli alunni della classe
     * @param alunni nuova lista degli alunni
     */
    public void setAlunni(ArrayList<Alunno> alunni) {
        this.alunni = alunni;
    }

    /**
     * Restituisce una rappresentazione testuale della classe con id, nome, anno e alunni
     * @return stringa descrittiva della classe
     */
    @Override
    public String toString() {
        return "Classe[" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", anno=" + anno +
                ", alunni=" + alunni +
                "]";
    }
}