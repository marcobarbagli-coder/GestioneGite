/*
COPYRIGHT (c) 2013 MySoft snc. All Rights Res.
Classe che definisce l'interfaccia con la quale l'utente interagisce con le liste e gli oggetti Prenotazione
@aithor Alex Bucsai
@version 1.0
*/

import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

public class PanelPrenotazione extends JPanel {

    // ---------------- COMPONENTI ----------------

    private JLabel lblIdPrenotazione;
    private JLabel lblClasse;
    private JLabel lblDestinazione;
    private JLabel lblPartecipanti;

    private JTextField txtIdPrenotazione;
    private JTextField txtClasse;
    private JTextField txtDestinazione;
    private JTextField txtPartecipanti;

    private JButton btnPrenota;
    private JButton btnAnnulla;
    private JButton btnPulisci;

    private JTextArea areaOutput;

    // ---------------- COSTRUTTORE ----------------

    public PanelPrenotazione(PrenotazioneService prenotazioneService, AlunnoService alunnoService, ItinerarioService itinerarioService) {

        setLayout(new BorderLayout());

        // ---------------- PANEL FORM ----------------

        JPanel panelForm = new JPanel();

        panelForm.setBorder(
                BorderFactory.createTitledBorder("Gestione Prenotazioni")
        );

        panelForm.setLayout(new GridLayout(6, 2, 10, 10));

        // label
        lblIdPrenotazione = new JLabel("ID Prenotazione:");
        lblClasse = new JLabel("Classe:");
        lblDestinazione = new JLabel("Destinazione:");
        lblPartecipanti = new JLabel("Numero Partecipanti:");

        // textfield
        txtIdPrenotazione = new JTextField();
        txtClasse = new JTextField();
        txtDestinazione = new JTextField();
        txtPartecipanti = new JTextField();

        // bottoni
        btnPrenota = new JButton("Prenota");
        btnAnnulla = new JButton("Annulla");
        btnPulisci = new JButton("Pulisci");

        // aggiunta componenti
        panelForm.add(lblIdPrenotazione);
        panelForm.add(txtIdPrenotazione);

        panelForm.add(lblClasse);
        panelForm.add(txtClasse);

        panelForm.add(lblDestinazione);
        panelForm.add(txtDestinazione);

        panelForm.add(lblPartecipanti);
        panelForm.add(txtPartecipanti);

        panelForm.add(btnPrenota);
        panelForm.add(btnAnnulla);

        panelForm.add(new JLabel(""));
        panelForm.add(btnPulisci);

        // ---------------- AREA OUTPUT ----------------

        areaOutput = new JTextArea();

        areaOutput.setEditable(false);

        JScrollPane scroll = new JScrollPane(areaOutput);

        scroll.setBorder(
                BorderFactory.createTitledBorder("Elenco Prenotazioni")
        );

        // ---------------- AGGIUNTA COMPONENTI ----------------

        add(panelForm, BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);


        // ===== AGGIUNTA: caricamento prenotazioni =====

        ArrayList<Prenotazione> lista = prenotazioneService.getPrenotazioni();

        for (Prenotazione p : lista) {

            areaOutput.append(
                    "\n========================\n"
            );

            areaOutput.append(
                    "ID Prenotazione: "
                            + p.getIdPrenotazione() + "\n"
            );

            areaOutput.append(
                    "Classe: "
                            + p.getClassi() + "\n"
            );

            /* 
            areaOutput.append(
                    "Destinazione: "
                            + p.getDestinazione() + "\n"
            );
            */

            areaOutput.append(
                    "Partecipanti: "
                            + p.getPartecipanti() + "\n"
            );

            areaOutput.append(
                    "========================\n"
            );
        }


        // ---------------- EVENTO PRENOTA ----------------

        btnPrenota.addActionListener(e -> {

            try {

                int id = Integer.parseInt(
                        txtIdPrenotazione.getText()
                );

                String classe = txtClasse.getText();

                String destinazione =
                        txtDestinazione.getText();

                int partecipanti = Integer.parseInt(
                        txtPartecipanti.getText()
                );

                // controlli
                if (classe.isEmpty()
                        || destinazione.isEmpty()) {

                    JOptionPane.showMessageDialog(
                            null,
                            "Compilare tutti i campi"
                    );

                    return;
                }

                // acconto esempio
                double costoPersona = 200;

                double totale =
                        costoPersona * partecipanti;

                double acconto =
                        totale * 0.10;

                // output
                areaOutput.append(
                        "\n========================\n"
                );

                areaOutput.append(
                        "ID Prenotazione: "
                                + id + "\n"
                );

                areaOutput.append(
                        "Classe: "
                                + classe + "\n"
                );

                areaOutput.append(
                        "Destinazione: "
                                + destinazione + "\n"
                );

                areaOutput.append(
                        "Partecipanti: "
                                + partecipanti + "\n"
                );

                areaOutput.append(
                        "Costo Totale: "
                                + totale + " €\n"
                );

                areaOutput.append(
                        "Acconto 10%: "
                                + acconto + " €\n"
                );

                areaOutput.append(
                        "STATO: PRENOTATA\n"
                );

                areaOutput.append(
                        "========================\n"
                );

                JOptionPane.showMessageDialog(
                        null,
                        "Prenotazione effettuata"
                );

                pulisciCampi();

            } catch (Exception ex) {

                JOptionPane.showMessageDialog(
                        null,
                        "Inserire dati validi"
                );
            }
        });

        // ---------------- EVENTO ANNULLA ----------------

        btnAnnulla.addActionListener(e -> {

            String motivo = JOptionPane.showInputDialog(
                    null,
                    "Inserire motivo annullamento"
            );

            if (motivo != null
                    && !motivo.isEmpty()) {

                areaOutput.append(
                        "\n*** PRENOTAZIONE ANNULLATA ***\n"
                );

                areaOutput.append(
                        "Motivo: " + motivo + "\n"
                );

                areaOutput.append(
                        "******************************\n"
                );

                JOptionPane.showMessageDialog(
                        null,
                        "Prenotazione annullata"
                );
            }
        });

        // ---------------- EVENTO PULISCI ----------------

        btnPulisci.addActionListener(e -> {

            pulisciCampi();
        });
    }

    // ---------------- METODO PULIZIA ----------------

    private void pulisciCampi() {

        txtIdPrenotazione.setText("");
        txtClasse.setText("");
        txtDestinazione.setText("");
        txtPartecipanti.setText("");
    }
}