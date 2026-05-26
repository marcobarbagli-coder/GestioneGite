/*
COPYRIGHT (c) 2013 MySoft snc. All Rights Res.
Classe che definisce l'interfaccia con la quale l'utente interagisce con le liste e gli oggetti Prenotazione
@aithor Alex Bucsai
@version 1.0
*/

import java.awt.*;
import javax.swing.*;

public class PanelPrenotazione extends JPanel {

    private JLabel lblIdPrenotazione;
    private JLabel lblIdItinerario;
    private JLabel lblIdAlunni;

    private JTextField txtIdPrenotazione;
    private JTextField txtIdItinerario;
    private JTextField txtIdAlunni;

    private JButton btnPrenota;
    private JButton btnAnnulla;
    private JButton btnPulisci;

    private JTextArea areaOutput;

    public PanelPrenotazione(PrenotazioneService prenotazioneService, AlunnoService alunnoService, ItinerarioService itinerarioService) {

        setLayout(new BorderLayout());

        // ---------------- PANEL FORM ----------------

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

        // ---------------- AREA OUTPUT ----------------

        areaOutput = new JTextArea();
        areaOutput.setEditable(false);
        JScrollPane scroll = new JScrollPane(areaOutput);
        scroll.setBorder(BorderFactory.createTitledBorder("Elenco Prenotazioni"));

        add(panelForm, BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);

        // ---------------- CARICAMENTO DA SERVICE ----------------
        // FIX: si usa il service già popolato da Main, non FileManager direttamente

        for (Prenotazione p : prenotazioneService.getPrenotazioni()) {
            mostraPrenotazione(p);
        }

        // ---------------- EVENTO PRENOTA ----------------

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

                // FIX: recupera l'itinerario dal service
                Itinerario itinerario = itinerarioService.cercaPerId(idItinerario);
                if (itinerario == null) {
                    JOptionPane.showMessageDialog(null, "Itinerario con ID " + idItinerario + " non trovato");
                    return;
                }

                // FIX: crea la prenotazione e aggiunge gli alunni tramite service
                Prenotazione nuova = new Prenotazione(idPrenotazione, itinerario);

                for (String s : idAlunniStr) {
                    String trimmed = s.trim();
                    if (trimmed.isEmpty()) continue;

                    int idAlunno = Integer.parseInt(trimmed);
                    Alunno a = alunnoService.cercaPerId(idAlunno);

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

                // FIX: salva nel service (che esegue anche le validazioni)
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

        // ---------------- EVENTO ANNULLA ----------------

        btnAnnulla.addActionListener(e -> {

            try {

                int idPrenotazione = Integer.parseInt(
                        JOptionPane.showInputDialog(null, "Inserire ID prenotazione da annullare:")
                );

                String motivo = JOptionPane.showInputDialog(null, "Inserire motivo annullamento:");

                if (motivo == null || motivo.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Motivo obbligatorio");
                    return;
                }

                Prenotazione p = prenotazioneService.cercaPerId(idPrenotazione);

                if (p == null) {
                    JOptionPane.showMessageDialog(null, "Prenotazione non trovata");
                    return;
                }

                // FIX: annulla tramite service
                prenotazioneService.annullaPrenotazione(idPrenotazione, motivo);

                areaOutput.append("\n*** PRENOTAZIONE " + idPrenotazione + " ANNULLATA ***\n");
                areaOutput.append("Motivo: " + motivo + "\n");
                areaOutput.append("******************************\n");

                JOptionPane.showMessageDialog(null, "Prenotazione annullata");

            } catch (NullPointerException ex) {
                // l'utente ha premuto Annulla nel dialog
            }
        });

        // ---------------- EVENTO PULISCI ----------------

        btnPulisci.addActionListener(e -> pulisciCampi());
    }

    // ---------------- METODI PRIVATI ----------------

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

    private void pulisciCampi() {
        txtIdPrenotazione.setText("");
        txtIdItinerario.setText("");
        txtIdAlunni.setText("");
    }
}