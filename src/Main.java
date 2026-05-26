/*
COPYRIGHT (c) 2013 MySoft snc. All Rights Res.
Classe Main che si occupa delle funzioni principali di salvataggio e caricamento dati e avvio della GUI
@author Alex Bucsai, Marco Barbagli
@version 1.0 2026-05-26
*/
import javax.swing.*;

public class Main {

    // Percorsi dei file di persistenza per ciascuna entità gestita dall'applicazione
    private static final String FILE_ALUNNI= "alunni.dat";
    private static final String FILE_ITINERARI= "itinerari.dat";
    private static final String FILE_PRENOTAZIONI= "prenotazioni.dat";

    /*
    Punto di ingresso dell'applicazione. Inizializza i service, carica i dati
    da file, registra il salvataggio automatico alla chiusura e avvia la GUI.
    @param args argomenti da riga di comando (non utilizzati)
    */
    public static void main(String[] args) {

        System.out.println("Avvio applicazione...");

        // Istanza dei service che gestiscono le liste in memoria durante l'esecuzione
        AlunnoService       alunnoService       = new AlunnoService();
        ItinerarioService   itinerarioService   = new ItinerarioService();
        PrenotazioneService prenotazioneService = new PrenotazioneService();

        // Caricamento dei dati persistenti dai file al momento dell'avvio
        alunnoService.setAlunni(
            FileManager.caricaAlunni(FILE_ALUNNI)
        );
        itinerarioService.setItinerari(
            FileManager.caricaItinerari(FILE_ITINERARI)
        );
        prenotazioneService.setPrenotazioni(
            FileManager.caricaPrenotazioni(FILE_PRENOTAZIONI)
        );

        // ShutdownHook: garantisce il salvataggio su file anche in caso di chiusura improvvisa della JVM
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

        // Avvio della GUI sul thread dedicato agli eventi Swing (Event Dispatch Thread)
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