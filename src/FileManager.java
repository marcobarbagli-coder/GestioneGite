/*
COPYRIGHT (c) 2013 MySoft snc. All Rights Res.
Classe che si occupa delle funzioni di salvataggio e caricamento dati
@aithor Alex Bucsai, Marco  Barbagli 
@version 1.0
*/

import java.io.*;
import java.util.ArrayList;

public class FileManager {

    // ---------------- SALVATAGGIO ALUNNI ----------------

    public static void salvaAlunni(
            ArrayList<Alunno> alunni,
            String nomeFile) {

        try {

            ObjectOutputStream oos =
                    new ObjectOutputStream(
                            new FileOutputStream(nomeFile)
                    );

            oos.writeObject(alunni);

            oos.close();

            System.out.println(
                    "Alunni salvati correttamente"
            );

        } catch (Exception e) {

            System.out.println(
                    "Errore salvataggio alunni"
            );

            e.printStackTrace();
        }
    }

    // ---------------- CARICAMENTO ALUNNI ----------------

    public static ArrayList<Alunno> caricaAlunni(
            String nomeFile) {

        try {

            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(nomeFile));

            ArrayList<Alunno> alunni = (ArrayList<Alunno>) ois.readObject();

            ois.close();

            System.out.println(
                    "Alunni caricati correttamente"
            );

            return alunni;

        } catch (Exception e) {

            System.out.println(
                    "Errore caricamento alunni"
            );

            return new ArrayList<>();
        }
    }

    // ---------------- SALVATAGGIO ITINERARI ----------------

    public static void salvaItinerari(
            ArrayList<Itinerario> itinerari,
            String nomeFile) {

        try {

            ObjectOutputStream oos =
                    new ObjectOutputStream(
                            new FileOutputStream(nomeFile)
                    );

            oos.writeObject(itinerari);

            oos.close();

            System.out.println(
                    "Itinerari salvati correttamente"
            );

        } catch (Exception e) {

            System.out.println(
                    "Errore salvataggio itinerari"
            );

            e.printStackTrace();
        }
    }

    // ---------------- CARICAMENTO ITINERARI ----------------

    public static ArrayList<Itinerario> caricaItinerari(
            String nomeFile) {

        try {

            ObjectInputStream ois =
                    new ObjectInputStream(
                            new FileInputStream(nomeFile)
                    );

            ArrayList<Itinerario> itinerari =
                    (ArrayList<Itinerario>) ois.readObject();

            ois.close();

            System.out.println(
                    "Itinerari caricati correttamente"
            );

            return itinerari;

        } catch (Exception e) {

            System.out.println(
                    "Errore caricamento itinerari"
            );

            return new ArrayList<>();
        }
    }

    // ---------------- SALVATAGGIO PRENOTAZIONI ----------------

    public static void salvaPrenotazioni(
            ArrayList<Prenotazione> prenotazioni,
            String nomeFile) {

        try {

            ObjectOutputStream oos =
                    new ObjectOutputStream(
                            new FileOutputStream(nomeFile)
                    );

            oos.writeObject(prenotazioni);

            oos.close();

            System.out.println(
                    "Prenotazioni salvate correttamente"
            );

        } catch (Exception e) {

            System.out.println(
                    "Errore salvataggio prenotazioni"
            );

            e.printStackTrace();
        }
    }

    // ---------------- CARICAMENTO PRENOTAZIONI ----------------

    public static ArrayList<Prenotazione> caricaPrenotazioni(
            String nomeFile) {

        try {

            ObjectInputStream ois =
                    new ObjectInputStream(
                            new FileInputStream(nomeFile)
                    );

            ArrayList<Prenotazione> prenotazioni =
                    (ArrayList<Prenotazione>) ois.readObject();

            ois.close();

            System.out.println(
                    "Prenotazioni caricate correttamente"
            );

            return prenotazioni;

        } catch (Exception e) {

            System.out.println(
                    "Errore caricamento prenotazioni"
            );

            return new ArrayList<>();
        }
    }
}
