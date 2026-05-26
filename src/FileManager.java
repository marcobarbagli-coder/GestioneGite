/*
COPYRIGHT (c) 2026 MySoft snc. All Rights Res.
Classe che si occupa delle funzioni di salvataggio e caricamento dati
@author Alex Bucsai, Marco Barbagli
@version 1.0 2026-05-26
*/

// importazione librerie per la gestione di file e liste
import java.io.*;
import java.util.ArrayList;

public class FileManager {

    /**
     * Salva la lista di alunni su file tramite serializzazione
     * @param alunni lista degli alunni da salvare
     * @param nomeFile percorso del file di destinazione
     */
    public static void salvaAlunni(ArrayList<Alunno> alunni, String nomeFile) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(nomeFile)); // creazione stream per il salvataggio su file
            oos.writeObject(alunni);
            oos.close();
            System.out.println("Alunni salvati correttamente");
        } catch (Exception e) {
            System.out.println("Errore salvataggio alunni");
            e.printStackTrace();
        }
    }

    /**
     * Carica la lista di alunni da file tramite deserializzazione
     * Limiti di funzionamento: restituisce lista vuota in caso di errore
     * @param nomeFile percorso del file da cui caricare
     * @return lista degli alunni caricati, oppure lista vuota in caso di errore
     */
    public static ArrayList<Alunno> caricaAlunni(String nomeFile) {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(nomeFile)); // creazione stream per il caricamento da file
            ArrayList<Alunno> alunni = (ArrayList<Alunno>) ois.readObject();
            ois.close();
            System.out.println("Alunni caricati correttamente");
            return alunni;
        } catch (Exception e) {
            System.out.println("Errore caricamento alunni");
            return new ArrayList<>();
        }
    }

    /**
     * Salva la lista di itinerari su file tramite serializzazione
     * @param itinerari lista degli itinerari da salvare
     * @param nomeFile percorso del file di destinazione
     */
    public static void salvaItinerari(ArrayList<Itinerario> itinerari, String nomeFile) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(nomeFile)); // creazione stream per il salvataggio su file
            oos.writeObject(itinerari);
            oos.close();
            System.out.println("Itinerari salvati correttamente");
        } catch (Exception e) {
            System.out.println("Errore salvataggio itinerari");
            e.printStackTrace();
        }
    }

    /**
     * Carica la lista di itinerari da file tramite deserializzazione
     * Limiti di funzionamento: restituisce lista vuota in caso di errore
     * @param nomeFile percorso del file da cui caricare
     * @return lista degli itinerari caricati, oppure lista vuota in caso di errore
     */
    public static ArrayList<Itinerario> caricaItinerari(String nomeFile) {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(nomeFile)); // creazione stream per il caricamento da file
            ArrayList<Itinerario> itinerari = (ArrayList<Itinerario>) ois.readObject();
            ois.close();
            System.out.println("Itinerari caricati correttamente");
            return itinerari;
        } catch (Exception e) {
            System.out.println("Errore caricamento itinerari");
            return new ArrayList<>();
        }
    }

    /**
     * Salva la lista di prenotazioni su file tramite serializzazione
     * @param prenotazioni lista delle prenotazioni da salvare
     * @param nomeFile percorso del file di destinazione
     */
    public static void salvaPrenotazioni(
            ArrayList<Prenotazione> prenotazioni,
            String nomeFile) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(nomeFile)); // creazione stream per il salvataggio su file
            oos.writeObject(prenotazioni);
            oos.close();
            System.out.println("Prenotazioni salvate correttamente");
        } catch (Exception e) {
            System.out.println("Errore salvataggio prenotazioni");
            e.printStackTrace();
        }
    }

    /**
     * Carica la lista di prenotazioni da file tramite deserializzazione
     * Limiti di funzionamento: restituisce lista vuota in caso di errore
     * @param nomeFile percorso del file da cui caricare
     * @return lista delle prenotazioni caricate, oppure lista vuota in caso di errore
     */
    public static ArrayList<Prenotazione> caricaPrenotazioni(String nomeFile) {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(nomeFile)); // creazione stream per il caricamento da file
            ArrayList<Prenotazione> prenotazioni = (ArrayList<Prenotazione>) ois.readObject();
            ois.close();
            System.out.println("Prenotazioni caricate correttamente");
            return prenotazioni;
        } catch (Exception e) {
            System.out.println("Errore caricamento prenotazioni");
            return new ArrayList<>();
        }
    }
}
