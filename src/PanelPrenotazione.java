/*
COPYRIGHT (c) 2013 MySoft snc. All Rights Res.
Classe che definisce l'oggetto Prenotazione, contenente itinerario,
classi e partecipanti, con supporto al calcolo dei costi e all'annullamento.
@author Alex Bucsai
@version 1.00 2026-05-26
*/

import java.awt.*;
import javax.swing.*;

public class PanelPrenotazione extends JPanel {

    private JLabel lblIdPrenotazione;            // etichetta campo ID prenotazione
    private JLabel lblIdItinerario;              // etichetta campo ID itinerario
    private JLabel lblIdAlunni;                  // etichetta campo ID alunni

    private JTextField txtIdPrenotazione;        // campo di testo per l'ID prenotazione
    private JTextField txtIdItinerario;          // campo di testo per l'ID itinerario
    private JTextField txtIdAlunni;              // campo di testo per gli ID alunni separati da virgola

    private JButton btnPrenota;                  // bottone per confermare la prenotazione
    private JButton btnAnnulla;                  // bottone per annullare una prenotazione esistente
    private JButton btnPulisci;                  // bottone per svuotare i campi del form

    private JTextArea areaOutput;                // area di testo in sola lettura per visualizzare le prenotazioni

    /**
     * Costruisce il pannello grafico per la gestione delle prenotazioni.
     * Inizializza il form di inserimento, l'area di output e i listener
     * per i pulsanti Prenota, Annulla e Pulisci.
     * @param prenotazioneService service per la gestione delle prenotazioni
     * @param alunnoService       service per la ricerca degli alunni per ID
     * @param itinerarioService   service per la ricerca degli itinerari per ID
     */
    public PanelPrenotazione(PrenotazioneService prenotazioneService, AlunnoService alunnoService, ItinerarioService itinerarioService) {

        setLayout(new BorderLayout());

        //   PANEL FORM     

        JPanel panelForm = new JPanel();
        panelForm.setBorder(BorderFactory.createTitledBorder("Gestione Prenotazioni"));
        panelForm.setLayout(new GridLayout(5, 2, 10, 10));

        lblIdPrenotazione = new JLabel("ID Prenotazione:");
        lblIdItinerario = new JLabel("ID Itinerario:");
        lblIdAlunni = new JLabel("ID Alunni (separati da virgola):");

        txtIdPrenotazione = new JTextField();
        txtIdItinerario = new JTextField();
        txtIdAlunni = new JTextField();

        btnPrenota = new JButton("Prenota");
        btnAnnulla = new JButton("Annulla prenotazione");
        btnPulisci = new JButton("Pulisci");

        panelForm.add(lblIdPrenotazione);
        panelForm.add(txtIdPrenotazione);

        panelForm.add(lblIdItinerario);
        panelForm.add(txtIdItinerario);

        panelForm.add(lblIdAlunni);
        panelForm.add(txtIdAlunni);

        panelForm.add(btnPrenota);
        panelForm.add(btnAnnulla);

        panelForm.add(new JLabel(""));
        panelForm.add(btnPulisci);

        //   AREA OUTPUT     

        areaOutput = new JTextArea();
        areaOutput.setEditable(false);
        JScrollPane scroll = new JScrollPane(areaOutput);
        scroll.setBorder(BorderFactory.createTitledBorder("Elenco Prenotazioni"));

        add(panelForm, BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);

        //   CARICAMENTO DA SERVICE     
        // FIX: si usa il service già popolato da Main, non FileManager direttamente

        for (Prenotazione p : prenotazioneService.getPrenotazioni()) {
            mostraPrenotazione(p);
        }

        //   EVENTO PRENOTA     

        btnPrenota.addActionListener(e -> {

            try {

                int idPrenotazione = Integer.parseInt(txtIdPrenotazione.getText().trim());
                int idItinerario = Integer.parseInt(txtIdItinerario.getText().trim());
                String[] idAlunniStr = txtIdAlunni.getText().trim().split(",");

                // controlla ID duplicato prenotazione
                if (prenotazioneService.cercaPerId(idPrenotazione) != null) {
                    JOptionPane.showMessageDialog(null, "ID prenotazione già esistente");
                    return;
                }

                // recupera l'itinerario dal service
                Itinerario itinerario = itinerarioService.cercaPerId(idItinerario);
                if (itinerario == null) {
                    JOptionPane.showMessageDialog(null, "Itinerario con ID " + idItinerario + " non trovato");
                    return;
                }

                // crea la prenotazione e aggiunge gli alunni tramite service
                Prenotazione nuova = new Prenotazione(idPrenotazione, itinerario);

                for (String s : idAlunniStr) {
                    String trimmed = s.trim();
                    if (trimmed.isEmpty()) continue;

                    int idAlunno = Integer.parseInt(trimmed);
                    Alunno a = alunnoService.cercaPerId(idAlunno);

                    // notifica e interrompe se l'alunno non esiste nel service
                    if (a == null) {
                        JOptionPane.showMessageDialog(null, "Alunno con ID " + idAlunno + " non trovato");
                        return;
                    }

                    nuova.aggiungiPartecipante(a);
                }

                if (nuova.numeroPartecipanti() == 0) {
                    JOptionPane.showMessageDialog(null, "Inserire almeno un alunno");
                    return;
                }

                // salva nel service (che esegue anche le validazioni)
                prenotazioneService.creaPrenotazione(nuova);

                // verifica che sia stata salvata (creaPrenotazione può rifiutarla)
                if (prenotazioneService.cercaPerId(idPrenotazione) != null) {
                    mostraPrenotazione(nuova);
                    JOptionPane.showMessageDialog(null, "Prenotazione effettuata\nAcconto: " + nuova.calcolaAcconto() + " €");
                    pulisciCampi();
                }

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Inserire valori numerici validi");
            }
        });

        //   EVENTO ANNULLA     

        btnAnnulla.addActionListener(e -> {

            try {

                int idPrenotazione = Integer.parseInt(
                        JOptionPane.showInputDialog(null, "Inserire ID prenotazione da annullare:")
                );

                String motivo = JOptionPane.showInputDialog(null, "Inserire motivo annullamento:");

                // il motivo è obbligatorio per tracciare la causa dell'annullamento
                if (motivo == null || motivo.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Motivo obbligatorio");
                    return;
                }

                Prenotazione p = prenotazioneService.cercaPerId(idPrenotazione);

                if (p == null) {
                    JOptionPane.showMessageDialog(null, "Prenotazione non trovata");
                    return;
                }

                //  annulla tramite service
                prenotazioneService.annullaPrenotazione(idPrenotazione, motivo);

                areaOutput.append("\n*** PRENOTAZIONE " + idPrenotazione + " ANNULLATA ***\n");
                areaOutput.append("Motivo: " + motivo + "\n");
                areaOutput.append("******************************\n");

                JOptionPane.showMessageDialog(null, "Prenotazione annullata");

            } catch (NullPointerException ex) {
                // l'utente ha premuto Annulla nel dialog
            }
        });

        //   EVENTO PULISCI     

        btnPulisci.addActionListener(e -> pulisciCampi());
    }

    //  METODI PRIVATI  

    /**
     * Aggiunge nell'area di output il riepilogo formattato di una prenotazione,
     * mostrando ID, destinazione, numero partecipanti, costo totale, acconto e stato.
     * @param p prenotazione da visualizzare nell'area di output
     */
    private void mostraPrenotazione(Prenotazione p) {
        areaOutput.append("\n========================\n");
        areaOutput.append("ID Prenotazione: " + p.getIdPrenotazione() + "\n");
        areaOutput.append("Destinazione: " + p.getItinerario().getDestinazione().getNome() + "\n");
        areaOutput.append("Partecipanti: " + p.numeroPartecipanti() + "\n");
        areaOutput.append("Costo totale: " + p.calcolaCostoTotale() + " €\n");
        areaOutput.append("Acconto 10%: " + p.calcolaAcconto()     + " €\n");
        areaOutput.append("Stato: " + (p.isAnnullata() ? "ANNULLATA" : "ATTIVA") + "\n");
        areaOutput.append("========================\n");
    }

    /**
     * Svuota tutti i campi di testo del form di inserimento.
     */
    private void pulisciCampi() {
        txtIdPrenotazione.setText("");
        txtIdItinerario.setText("");
        txtIdAlunni.setText("");
    }
}