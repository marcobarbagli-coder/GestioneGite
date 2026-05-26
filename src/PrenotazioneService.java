import java.util.ArrayList;

public class PrenotazioneService {

    private ArrayList<Prenotazione> prenotazioni;

    // ---------------- COSTRUTTORE ----------------

    public PrenotazioneService() {
        prenotazioni = new ArrayList<>();
    }

    // ---------------- CREA PRENOTAZIONE ----------------

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

    // ---------------- MOSTRA PRENOTAZIONI ----------------

    public void mostraPrenotazioni() {

        if (prenotazioni.isEmpty()) {

            System.out.println("Nessuna prenotazione presente");
            return;
        }

        for (Prenotazione p : prenotazioni) {
            System.out.println(p);
        }
    }

    // ---------------- CERCA PRENOTAZIONE ----------------

    public Prenotazione cercaPerId(int id) {

        for (Prenotazione p : prenotazioni) {

            if (p.getIdPrenotazione() == id) {
                return p;
            }
        }

        return null;
    }

    // ---------------- ANNULLA PRENOTAZIONE ----------------

    public void annullaPrenotazione(int id, String motivo) {

        Prenotazione p = cercaPerId(id);

        if (p == null) {

            System.out.println("Prenotazione non trovata");
            return;
        }

        p.annullaGita(motivo);

        System.out.println("Prenotazione annullata");
    }

    // ---------------- RIMUOVI ALUNNO ----------------

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

    // ---------------- NUMERO PRENOTAZIONI ----------------

    public int numeroPrenotazioni() {
        return prenotazioni.size();
    }

    // ---------------- GETTER E SETTER ----------------

    public ArrayList<Prenotazione> getPrenotazioni() {
        return prenotazioni;
    }

    public void setPrenotazioni(ArrayList<Prenotazione> prenotazioni) {
        this.prenotazioni = prenotazioni;
    }
}
