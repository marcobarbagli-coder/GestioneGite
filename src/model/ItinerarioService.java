import java.util.ArrayList;


public class ItinerarioService {

    private ArrayList<Itinerario> itinerari;

   

    public ItinerarioService() {
        itinerari = new ArrayList<>();
    }

   

    public void aggiungiItinerario(Itinerario i) {

        if (i == null) {
            System.out.println("Itinerario non valido");
            return;
        }

        if (i.getDestinazione() == null) {
            System.out.println("Destinazione mancante");
            return;
        }

        if (i.getMinPartecipanti() > i.getMaxPartecipanti()) {
            System.out.println("Errore: minimo maggiore del massimo");
            return;
        }

        itinerari.add(i);

        System.out.println("Itinerario aggiunto correttamente");
    }

   

    public void mostraItinerari() {

        if (itinerari.isEmpty()) {
            System.out.println("Nessun itinerario presente");
            return;
        }

        for (Itinerario i : itinerari) {
            System.out.println(i);
        }
    }

    

    public Itinerario cercaPerId(int id) {

        for (Itinerario i : itinerari) {

            if (i.getId() == id) {
                return i;
            }
        }

        return null;
    }

   

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

   

    public void modificaDescrizione(int id, String nuovaDescrizione) {

        Itinerario i = cercaPerId(id);

        if (i == null) {
            System.out.println("Itinerario non trovato");
            return;
        }

        // controllo prenotazioni
        if (i.isPrenotato()) {
            System.out.println("Impossibile modificare: itinerario prenotato");
            return;
        }

        i.setDescrizione(nuovaDescrizione);

        System.out.println("Descrizione aggiornata");
    }

   

    public void eliminaItinerario(int id) {

        Itinerario i = cercaPerId(id);

        if (i == null) {
            System.out.println("Itinerario non trovato");
            return;
        }

        // controllo prenotazioni
        if (i.isPrenotato()) {
            System.out.println("Impossibile eliminare: esistono prenotazioni");
            return;
        }

        itinerari.remove(i);

        System.out.println("Itinerario eliminato");
    }

    // ---------------- NUMERO ITINERARI ----------------

    public int numeroItinerari() {
        return itinerari.size();
    }

    // ---------------- GETTER E SETTER ----------------

    public ArrayList<Itinerario> getItinerari() {
        return itinerari;
    }

    public void setItinerari(ArrayList<Itinerario> itinerari) {
        this.itinerari = itinerari;
    }
}
