/*
COPYRIGHT (c) 2026 MySoft snc. All Rights Res.
Classe che definisce le operazioni che coinvolgono gli oggetti Alunno
@author Barbagli Marco
@version 1.0 2026-05-26
*/

// importazione librerie per la gestione delle liste
import java.util.ArrayList;

public class AlunnoService {

    private ArrayList<Alunno> alunni; // lista di tutti gli alunni gestiti dal servizio

    /**
     * Costruisce un nuovo AlunnoService con lista alunni inizialmente vuota
     */
    public AlunnoService() {
        alunni = new ArrayList<>();
    }

    // metodi propri della classe

    /**
     * Aggiunge un alunno alla lista, solo se l'oggetto passato è valido
     * @param a alunno da aggiungere; se null, l'operazione viene annullata
     */
    public void aggiungiAlunno(Alunno a) {
        if (a == null) { // se l'alunno non è valido non viene aggiunto
            System.out.println("Alunno non valido");
            return;
        }
        alunni.add(a);
        System.out.println("Alunno aggiunto");
    }

    /**
     * Stampa la lista di tutti gli alunni presenti
     * Caso particolare: se la lista è vuota viene stampato un messaggio informativo
     */
    public void mostraAlunni() {
        if (alunni.isEmpty()) { // se la lista è vuota non c'è nulla da stampare
            System.out.println("Nessun alunno presente");
            return;
        }
        for (Alunno a : alunni) { // scorre e stampa ogni alunno della lista
            System.out.println(a);
        }
    }

    /**
     * Cerca un alunno nella lista in base al suo id univoco
     * Limiti di funzionamento: restituisce null se nessun alunno corrisponde all'id
     * @param id identificativo dell'alunno da cercare
     * @return alunno trovato, oppure null se non presente
     */
    public Alunno cercaPerId(int id) {
        for (Alunno a : alunni) {
            if (a.getId() == id) { // confronto tra id dell'alunno e id cercato
                return a;
            }
        }
        return null;
    }

    /**
     * Rimuove dalla lista l'alunno corrispondente all'id indicato
     * Caso particolare: se l'id non corrisponde a nessun alunno, l'operazione viene annullata
     * @param id identificativo dell'alunno da rimuovere
     */
    public void rimuoviAlunno(int id) {
        Alunno a = cercaPerId(id); // recupero dell'alunno corrispondente all'id
        if (a == null) { // se non esiste un alunno con quell'id
            System.out.println("Alunno non trovato");
            return;
        }
        alunni.remove(a);
        System.out.println("Alunno rimosso");
    }

    /**
     * Registra l'autorizzazione per un alunno minorenne identificato dall'id
     * Caso particolare: se l'alunno è maggiorenne l'autorizzazione non è necessaria
     * Caso particolare: se l'id non corrisponde a nessun alunno, l'operazione viene annullata
     * @param id identificativo dell'alunno da autorizzare
     */
    public void autorizzaAlunno(int id) {
        Alunno a = cercaPerId(id); // recupero dell'alunno corrispondente all'id
        if (a == null) { // se non esiste un alunno con quell'id
            System.out.println("Alunno non trovato");
            return;
        }
        if (!a.isMinorenne()) { // i maggiorenni non necessitano di autorizzazione
            System.out.println("L'alunno è maggiorenne, autorizzazione non necessaria");
            return;
        }
        a.setAutorizzazione(true);
        System.out.println("Autorizzazione registrata");
    }

    /**
     * Registra la rinuncia medica per l'alunno identificato dall'id
     * Caso particolare: se l'id non corrisponde a nessun alunno, l'operazione viene annullata
     * @param id identificativo dell'alunno per cui registrare la rinuncia medica
     */
    public void rinunciaMedica(int id) {
        Alunno a = cercaPerId(id); // recupero dell'alunno corrispondente all'id
        if (a == null) { // se non esiste un alunno con quell'id
            System.out.println("Alunno non trovato");
            return;
        }
        a.setRinunciaMedica(true);
        System.out.println("Rinuncia medica registrata per " + a.getNome());
    }

    // metodi getter e setter

    /**
     * Restituisce il numero totale di alunni presenti nella lista
     * @return numero di alunni
     */
    public int numeroAlunni() {
        return alunni.size();
    }

    /**
     * Restituisce la lista completa degli alunni
     * @return lista degli alunni
     */
    public ArrayList<Alunno> getAlunni() {
        return alunni;
    }

    /**
     * Sostituisce l'intera lista degli alunni con quella fornita
     * @param alunni nuova lista degli alunni
     */
    public void setAlunni(ArrayList<Alunno> alunni) {
        this.alunni = alunni;
    }
}