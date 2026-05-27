/*
COPYRIGHT (c) 2013 MySoft snc. All Rights Res.
Classe che definisce l'interfaccia con la quale l'utente interagisce
con le liste e gli oggetti Itinerario tramite pannello grafico Swing.
@author Alex Bucsai
@version 1.0 2026-05-26
*/

import java.awt.*;
import javax.swing.*;

public class PanelItinerario extends JPanel {

    private JLabel lblId;                // etichetta campo ID
    private JLabel lblNome;              // etichetta campo nome destinazione
    private JLabel lblRegione;           // etichetta campo regione
    private JLabel lblNazione;           // etichetta campo nazione
    private JLabel lblGiorni;            // etichetta campo durata in giorni
    private JLabel lblTipo;              // etichetta campo tipo itinerario
    private JLabel lblCosto;             // etichetta campo costo unitario
    private JLabel lblMin;               // etichetta campo minimo partecipanti
    private JLabel lblMax;               // etichetta campo massimo partecipanti
    private JLabel lblAnnoCorso;         // etichetta campo anno del corso
    private JLabel lblDescrizione;       // etichetta campo descrizione
    private JLabel lblOptional;          // etichetta campo servizi optional

    private JTextField txtId;            // campo di testo per l'ID itinerario
    private JTextField txtNome;          // campo di testo per il nome della destinazione
    private JTextField txtRegione;       // campo di testo per la regione
    private JTextField txtNazione;       // campo di testo per la nazione
    private JTextField txtGiorni;        // campo di testo per la durata in giorni
    private JTextField txtTipo;          // campo di testo per il tipo di itinerario
    private JTextField txtCosto;         // campo di testo per il costo in euro
    private JTextField txtMin;           // campo di testo per il minimo partecipanti
    private JTextField txtMax;           // campo di testo per il massimo partecipanti
    private JTextField txtAnnoCorso;     // campo di testo per l'anno del corso
    private JTextField txtDescrizione;   // campo di testo per la descrizione
    private JTextField txtOptional;      // campo di testo per i servizi optional

    private JButton btnAggiungi;         // bottone per aggiungere un nuovo itinerario
    private JButton btnPulisci;          // bottone per svuotare i campi del form

    private JTextArea areaOutput;        // area di testo in sola lettura per visualizzare gli itinerari

    /**
     * Costruisce il pannello grafico per la gestione degli itinerari.
     * Inizializza il form di inserimento, l'area di output e i listener
     * per i pulsanti Aggiungi e Pulisci.
     * Carica al momento della costruzione gli itinerari già presenti nel service.
     * @param itinerarioService service per la gestione degli itinerari
     */
    public PanelItinerario(ItinerarioService itinerarioService) {

        setLayout(new BorderLayout());

        //   PANEL FORM     

        JPanel panelForm = new JPanel();
        panelForm.setBorder(BorderFactory.createTitledBorder("Inserimento Itinerario"));
        panelForm.setLayout(new GridLayout(13, 2, 10, 10));

        lblId = new JLabel("ID:");
        lblNome = new JLabel("Nome destinazione:");
        lblRegione = new JLabel("Regione:");
        lblNazione = new JLabel("Nazione:");
        lblGiorni = new JLabel("Giorni:");
        lblTipo = new JLabel("Tipo:");
        lblCosto = new JLabel("Costo (€):");
        lblMin = new JLabel("Min partecipanti:");
        lblMax = new JLabel("Max partecipanti:");
        lblAnnoCorso = new JLabel("Anno corso:");
        lblDescrizione = new JLabel("Descrizione:");
        lblOptional = new JLabel("Optional:");

        txtId = new JTextField();
        txtNome = new JTextField();
        txtRegione = new JTextField();
        txtNazione = new JTextField();
        txtGiorni = new JTextField();
        txtTipo = new JTextField();
        txtCosto = new JTextField();
        txtMin = new JTextField();
        txtMax = new JTextField();
        txtAnnoCorso = new JTextField();
        txtDescrizione = new JTextField();
        txtOptional  = new JTextField();

        btnAggiungi = new JButton("Aggiungi");
        btnPulisci = new JButton("Pulisci");

        panelForm.add(lblId);
        panelForm.add(txtId);

        panelForm.add(lblNome);
        panelForm.add(txtNome);

        panelForm.add(lblRegione);
        panelForm.add(txtRegione);

        panelForm.add(lblNazione);
        panelForm.add(txtNazione);

        panelForm.add(lblGiorni);
        panelForm.add(txtGiorni);

        panelForm.add(lblTipo);
        panelForm.add(txtTipo);

        panelForm.add(lblCosto);
        panelForm.add(txtCosto);

        panelForm.add(lblMin);
        panelForm.add(txtMin);

        panelForm.add(lblMax);
        panelForm.add(txtMax);

        panelForm.add(lblAnnoCorso);
        panelForm.add(txtAnnoCorso);

        panelForm.add(lblDescrizione);
        panelForm.add(txtDescrizione);

        panelForm.add(lblOptional);
        panelForm.add(txtOptional);

        panelForm.add(btnAggiungi);
        panelForm.add(btnPulisci);

        //   AREA OUTPUT     

        areaOutput = new JTextArea();
        areaOutput.setEditable(false);
        JScrollPane scroll = new JScrollPane(areaOutput);
        scroll.setBorder(BorderFactory.createTitledBorder("Elenco Itinerari"));

        add(panelForm, BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);

        //  CARICAMENTO DA SERVICE 
        //  si usa il service già popolato da Main, non FileManager direttamente

        for (Itinerario i : itinerarioService.getItinerari()) {
            mostraItinerario(i);
        }

        //   EVENTO AGGIUNGI     

        btnAggiungi.addActionListener(e -> {

            try {

                int id = Integer.parseInt(txtId.getText().trim());
                String nome = txtNome.getText().trim();
                String regione = txtRegione.getText().trim();
                String nazione = txtNazione.getText().trim();
                int giorni = Integer.parseInt(txtGiorni.getText().trim());
                String tipo = txtTipo.getText().trim();
                double costo = Double.parseDouble(txtCosto.getText().trim());
                int min = Integer.parseInt(txtMin.getText().trim());
                int max = Integer.parseInt(txtMax.getText().trim());
                int annoCorso = Integer.parseInt(txtAnnoCorso.getText().trim());
                String descrizione = txtDescrizione.getText().trim();
                String optional = txtOptional.getText().trim();

                // i campi testuali principali sono obbligatori
                if (nome.isEmpty() || regione.isEmpty() || nazione.isEmpty() || tipo.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Compilare tutti i campi obbligatori");
                    return;
                }

                // crea Destinazione e Itinerario, poi salva nel service
                Destinazione dest = new Destinazione(nome, regione, nazione);
                Itinerario nuovo = new Itinerario(id, dest, giorni, tipo, descrizione, min, max, costo, annoCorso, optional,
                        false, 0);

                itinerarioService.aggiungiItinerario(nuovo);

                // aggiungiItinerario stampa eventuali errori (min>max, ecc.)
                // verifichiamo che sia stato effettivamente aggiunto
                if (itinerarioService.cercaPerId(id) != null) {
                    mostraItinerario(nuovo);
                    JOptionPane.showMessageDialog(null, "Itinerario aggiunto correttamente");
                    pulisciCampi();
                }

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Inserire valori numerici validi nei campi numerici");
            }
        });

        //   EVENTO PULISCI     

        btnPulisci.addActionListener(e -> pulisciCampi());
    }

    /**
     * Costruttore vuoto richiesto per istanziare PanelItinerario senza service.
     */
    public PanelItinerario() {}

    //   METODI PRIVATI     

    /**
     * Aggiunge nell'area di output il riepilogo formattato di un itinerario,
     * mostrando ID, destinazione, regione, nazione, giorni, tipo, costo
     * e limiti di partecipanti.
     * @param i itinerario da visualizzare nell'area di output
     */
    private void mostraItinerario(Itinerario i) {
        areaOutput.append("\n========================\n");
        areaOutput.append("ID: " + i.getId() + "\n");
        areaOutput.append("Destinazione: " + i.getDestinazione().getNome() + "\n");
        areaOutput.append("Regione: " + i.getDestinazione().getRegione() + "\n");
        areaOutput.append("Nazione: " + i.getDestinazione().getNazione() + "\n");
        areaOutput.append("Giorni: " + i.getGiorni() + "\n");
        areaOutput.append("Tipo: " + i.getTipo() + "\n");
        areaOutput.append("Costo: " + i.getCosto() + " €" + "\n");
        areaOutput.append("Min/Max: " + i.getMinPartecipanti() + " / " + i.getMaxPartecipanti() + "\n");
        areaOutput.append("========================\n");
    }

    /**
     * Svuota tutti i campi di testo del form di inserimento.
     */
    private void pulisciCampi() {
        txtId.setText("");
        txtNome.setText("");
        txtRegione.setText("");
        txtNazione.setText("");
        txtGiorni.setText("");
        txtTipo.setText("");
        txtCosto.setText("");
        txtMin.setText("");
        txtMax.setText("");
        txtAnnoCorso.setText("");
        txtDescrizione.setText("");
        txtOptional.setText("");
    }
}