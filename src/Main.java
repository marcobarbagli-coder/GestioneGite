import javax.swing.*;

public class Main {

    private static final String FILE_ALUNNI       = "alunni.dat";
    private static final String FILE_ITINERARI    = "itinerari.dat";
    private static final String FILE_PRENOTAZIONI = "prenotazioni.dat";

    public static void main(String[] args) {

        System.out.println("Avvio applicazione...");

        // ---------------- ISTANZA SERVICE ----------------

        AlunnoService alunnoService             = new AlunnoService();
        ItinerarioService itinerarioService     = new ItinerarioService();
        PrenotazioneService prenotazioneService = new PrenotazioneService();

        // ---------------- CARICAMENTO DATI ----------------

        alunnoService.setAlunni(
                FileManager.caricaAlunni(FILE_ALUNNI)
        );
        itinerarioService.setItinerari(
                FileManager.caricaItinerari(FILE_ITINERARI)
        );
        prenotazioneService.setPrenotazioni(
                FileManager.caricaPrenotazioni(FILE_PRENOTAZIONI)
        );


        // ---------------- SALVATAGGIO ALLA CHIUSURA ----------------

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {

            FileManager.salvaAlunni(
                    alunnoService.getAlunni(), FILE_ALUNNI
            );
            FileManager.salvaItinerari(
                    itinerarioService.getItinerari(), FILE_ITINERARI
            );
            FileManager.salvaPrenotazioni(
                    prenotazioneService.getPrenotazioni(), FILE_PRENOTAZIONI
            );

            System.out.println("Dati salvati. Arrivederci!");
        }));

        // ---------------- AVVIO GUI ----------------

        SwingUtilities.invokeLater(() -> {

            try {
                new MainFrame(alunnoService, itinerarioService, prenotazioneService);
            } catch (Exception e) {
                System.err.println("Errore avvio GUI:");
                e.printStackTrace();
            }
        });
    }
}