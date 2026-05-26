/*
COPYRIGHT (c) 2013 MySoft snc. All Rights Res.
Classe che definisce le operazioni che coinvolgono gli oggetti Itinerario
@author Barbagli Marco
@version 1.0 2026-05-26
*/
import java.util.ArrayList;

public class ItinerarioService {

    // Lista degli itinerari gestiti in memoria durante l'esecuzione
    private ArrayList<Itinerario> itinerari;

    /*
    Costruttore: inizializza la lista degli itinerari vuota.
    */
    public ItinerarioService() {
        itinerari = new ArrayList<>();
    }

    /*
    Aggiunge un itinerario alla lista dopo aver verificato la validità dei dati.
    Limiti: rifiuta itinerari nulli, privi di destinazione o con minimo maggiore del massimo.
    @param i oggetto Itinerario da aggiungere
    */
    public void aggiungiItinerario(Itinerario i) {
        if (i == null) {
            System.out.println("Itinerario non valido");
            return;
        }
        if (i.getDestinazione() == null) {
            System.out.println("Destinazione mancante");
            return;
        }
        // Il numero minimo di partecipanti non può superare il massimo
        if (i.getMinPartecipanti() > i.getMaxPartecipanti()) {
            System.out.println("Errore: minimo maggiore del massimo");
            return;
        }
        itinerari.add(i);
        System.out.println("Itinerario aggiunto correttamente");
    }

    /*
    Stampa su console tutti gli itinerari presenti nella lista.
    */
    public void mostraItinerari() {
        if (itinerari.isEmpty()) {
            System.out.println("Nessun itinerario presente");
            return;
        }
        for (Itinerario i : itinerari) {
            System.out.println(i);
        }
    }

    /*
    Cerca un itinerario tramite il suo identificativo numerico.
    @param id identificativo dell'itinerario da cercare
    @return l'itinerario trovato, oppure null se non esiste
    */
    public Itinerario cercaPerId(int id) {
        for (Itinerario i : itinerari) {
            if (i.getId() == id) {
                return i;
            }
        }
        return null;
    }

    /*
    Stampa su console tutti gli itinerari che corrispondono alla destinazione cercata.
    Il confronto non è sensibile alle maiuscole/minuscole.
    @param nomeDestinazione nome della destinazione da cercare
    */
    public void cercaPerDestinazione(String nomeDestinazione) {
        boolean trovato = false;
        for (Itinerario i : itinerari) {
            if (i.getDestinazione()
                  .getNome()
                  .equalsIgnoreCase(nomeDestinazione)) {
                System.out.println(i);
                trovato = true;
            }
        }
        if (!trovato) {
            System.out.println("Nessun itinerario trovato");
        }
    }

    /*
    Aggiorna la descrizione di un itinerario esistente.
    La modifica è bloccata se l'itinerario ha già una prenotazione attiva.
    @param id               identificativo dell'itinerario da modificare
    @param nuovaDescrizione nuovo testo descrittivo da assegnare
    */
    public void modificaDescrizione(int id, String nuovaDescrizione) {
        Itinerario i = cercaPerId(id);
        if (i == null) {
            System.out.println("Itinerario non trovato");
            return;
        }
        // Un itinerario già prenotato non può essere modificato per garantire la coerenza dei dati
        if (i.isPrenotato()) {
            System.out.println("Impossibile modificare: itinerario prenotato");
            return;
        }
        i.setDescrizione(nuovaDescrizione);
        System.out.println("Descrizione aggiornata");
    }

    /*
    Rimuove un itinerario dalla lista tramite il suo identificativo.
    L'eliminazione è bloccata se esistono prenotazioni associate all'itinerario.
    @param id identificativo dell'itinerario da eliminare
    */
    public void eliminaItinerario(int id) {
        Itinerario i = cercaPerId(id);
        if (i == null) {
            System.out.println("Itinerario non trovato");
            return;
        }
        // Non è possibile eliminare un itinerario con prenotazioni attive
        if (i.isPrenotato()) {
            System.out.println("Impossibile eliminare: esistono prenotazioni");
            return;
        }
        itinerari.remove(i);
        System.out.println("Itinerario eliminato");
    }

    /*
    Restituisce il numero totale di itinerari presenti nella lista.
    @return numero di itinerari
    */
    public int numeroItinerari() {
        return itinerari.size();
    }

    // Getter e setter per la lista degli itinerari

    public ArrayList<Itinerario> getItinerari() {
        return itinerari;
    }

    public void setItinerari(ArrayList<Itinerario> itinerari) {
        this.itinerari = itinerari;
    }
}