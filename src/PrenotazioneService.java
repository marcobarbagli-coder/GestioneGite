
/**
 * Classe per la gestione delle prenotazioni delle gite scolastiche.
 * Fornisce operazioni di creazione, ricerca, annullamento e modifica
 * delle prenotazioni associate a itinerari e partecipanti.
 * @author Barbagli Marco
 * @version 1.00 2026-05-26
 */

/*
COPYRIGHT (c) 2013 MySoft snc. All Rights Res.
Classe che definisce le operazione che coinvolgono gli oggetti prenotazione
@aithor Barbagli Marco
@version 1.0
*/


import java.util.ArrayList;
public class PrenotazioneService {

    private ArrayList<Prenotazione> prenotazioni; // lista di tutte le prenotazioni attive

    //COSTRUTTORE

    /**
     * Inizializza il servizio creando una lista vuota di prenotazioni.
     */
    public PrenotazioneService() {
        prenotazioni = new ArrayList<>();
    }

    // CREA PRENOTAZIONE

    /**
     * Crea e registra una nuova prenotazione dopo aver verificato
     * la validità dell'oggetto, dell'itinerario, del numero di partecipanti
     * e delle autorizzazioni per gli alunni minorenni.
     * Stampa l'acconto richiesto se la prenotazione va a buon fine.
     * Limiti: non gestisce prenotazioni duplicate per lo stesso itinerario.
     * @param p la prenotazione da registrare; ignorata se null o non valida
     */
    public void creaPrenotazione(Prenotazione p) {
        if (p == null) {
            System.out.println("Prenotazione non valida");
            return;
        }
        if (p.getItinerario() == null) {
            System.out.println("Itinerario mancante");
            return;
        }
        // controllo numero partecipanti
        int numero = p.numeroPartecipanti();
        if (numero < p.getItinerario().getMinPartecipanti()) {
            System.out.println("Numero partecipanti troppo basso");
            return;
        }
        if (numero > p.getItinerario().getMaxPartecipanti()) {
            System.out.println("Numero partecipanti troppo alto");
            return;
        }
        // controllo autorizzazioni minorenni
        for (Alunno a : p.getPartecipanti()) {
            if (a.isMinorenne() && !a.isAutorizzazione()) {
                System.out.println(
                    "Autorizzazione mancante per: "
                    + a.getNome()
                );
                return;
            }
        }
        // imposta itinerario prenotato
        p.getItinerario().setPrenotato(true);
        prenotazioni.add(p);
        System.out.println("Prenotazione completata");
        System.out.println("Acconto richiesto: "
                + p.calcolaAcconto() + " €");
    }

    //MOSTRA PRENOTAZIONI

    /**
     * Stampa tutte le prenotazioni presenti nella lista.
     * Se la lista è vuota, notifica l'assenza di prenotazioni.
     */
    public void mostraPrenotazioni() {
        if (prenotazioni.isEmpty()) {
            System.out.println("Nessuna prenotazione presente");
            return;
        }
        for (Prenotazione p : prenotazioni) {
            System.out.println(p);
        }
    }

    //CERCA PRENOTAZIONE

    /**
     * Cerca una prenotazione nella lista in base all'identificativo univoco.
     * @param id identificativo della prenotazione da cercare
     * @return la prenotazione corrispondente all'id, oppure null se non trovata
     */
    public Prenotazione cercaPerId(int id) {
        for (Prenotazione p : prenotazioni) {
            if (p.getIdPrenotazione() == id) {
                return p;
            }
        }
        return null;
    }

    //ANNULLA PRENOTAZIONE

    /**
     * Annulla una prenotazione esistente registrando il motivo dell'annullamento.
     * Se la prenotazione non viene trovata, notifica l'errore senza modificare lo stato.
     * @param id     identificativo della prenotazione da annullare
     * @param motivo descrizione del motivo dell'annullamento
     */
    public void annullaPrenotazione(int id, String motivo) {
        Prenotazione p = cercaPerId(id);
        if (p == null) {
            System.out.println("Prenotazione non trovata");
            return;
        }
        p.annullaGita(motivo);
        System.out.println("Prenotazione annullata");
    }

    // RIMUOVI ALUNNO 

    /**
     * Rimuove un alunno dalla lista dei partecipanti di una prenotazione
     * e stampa il nuovo costo totale aggiornato.
     * Non esegue alcuna modifica se la prenotazione o l'alunno non vengono trovati.
     * @param idPrenotazione identificativo della prenotazione da modificare
     * @param a              alunno da rimuovere dalla lista dei partecipanti
     */
    public void rimuoviAlunno(int idPrenotazione, Alunno a) {
        Prenotazione p = cercaPerId(idPrenotazione);
        if (p == null) {
            System.out.println("Prenotazione non trovata");
            return;
        }
        if (!p.getPartecipanti().contains(a)) {
            System.out.println("Alunno non presente");
            return;
        }
        p.rimuoviPartecipante(a);
        System.out.println("Alunno rimosso");
        // nuovo saldo
        double nuovoCosto =
            p.calcolaCostoTotale();
        System.out.println(
            "Nuovo costo totale: "
            + nuovoCosto + " €"
        );
    }

    //NUMERO PRENOTAZIONI 

    /**
     * Restituisce il numero totale di prenotazioni attualmente registrate.
     * @return numero intero di prenotazioni presenti nella lista
     */
    public int numeroPrenotazioni() {
        return prenotazioni.size();
    }

    // GETTER E SETTER 

    /**
     * Restituisce la lista completa delle prenotazioni.
     * @return ArrayList contenente tutte le prenotazioni registrate
     */
    public ArrayList<Prenotazione> getPrenotazioni() {
        return prenotazioni;
    }

    /**
     * Sostituisce l'intera lista delle prenotazioni con quella fornita.
     * @param prenotazioni nuova lista di prenotazioni da impostare
     */
    public void setPrenotazioni(ArrayList<Prenotazione> prenotazioni) {
        this.prenotazioni = prenotazioni;
    }
}