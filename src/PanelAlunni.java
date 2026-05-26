/*
COPYRIGHT (c) 2013 MySoft snc. All Rights Res.
Classe che definisce l'interfaccia con la quale l'utente interagisce con le liste e gli oggetti Alunno
@author Alex Bucsai
@version 1.0 2026-05-26
*/

import java.awt.*;
import javax.swing.*;

public class PanelAlunni extends JPanel {

    // Etichette descrittive per i campi del form
    private JLabel lblId;
    private JLabel lblNome;
    private JLabel lblCognome;
    private JLabel lblEta;
    private JLabel lblClasse;
    private JLabel lblAnnoCorso;

    // Campi di testo per l'inserimento dei dati dell'alunno
    private JTextField txtId;
    private JTextField txtNome;
    private JTextField txtCognome;
    private JTextField txtEta;
    private JTextField txtClasse;
    private JTextField txtAnnoCorso;

    // Checkbox calcolata automaticamente in base all'età, non modificabile dall'utente
    private JCheckBox chkMinorenne;

    // Pulsanti per confermare l'inserimento o azzerare il form
    private JButton btnAggiungi;
    private JButton btnPulisci;

    // Area di testo sola lettura che mostra l'elenco degli alunni caricati
    private JTextArea areaOutput;

    /*
    Costruttore del pannello. Inizializza il layout, i componenti grafici
    e carica gli alunni già presenti nel service passato come parametro.
    @param alunnoService service che gestisce la lista degli alunni
    */
    public PanelAlunni(AlunnoService alunnoService) {

        setLayout(new BorderLayout());

        // Pannello superiore con form di inserimento dati
        JPanel panelForm = new JPanel();
        panelForm.setBorder(BorderFactory.createTitledBorder("Inserimento Alunno"));
        panelForm.setLayout(new GridLayout(7, 2, 10, 10));

        lblId        = new JLabel("ID:");
        lblNome      = new JLabel("Nome:");
        lblCognome   = new JLabel("Cognome:");
        lblEta       = new JLabel("Età:");
        lblClasse    = new JLabel("Classe:");
        lblAnnoCorso = new JLabel("Anno corso:");

        txtId        = new JTextField();
        txtNome      = new JTextField();
        txtCognome   = new JTextField();
        txtEta       = new JTextField();
        txtClasse    = new JTextField();
        txtAnnoCorso = new JTextField();

        // La checkbox è disabilitata: il valore viene derivato dall'età, non inserito dall'utente
        chkMinorenne = new JCheckBox("Minorenne (calcolato automaticamente dall'età)");
        chkMinorenne.setEnabled(false);

        btnAggiungi = new JButton("Aggiungi");
        btnPulisci  = new JButton("Pulisci");

        // Aggiunta delle coppie etichetta-campo al pannello griglia
        panelForm.add(lblId);        panelForm.add(txtId);
        panelForm.add(lblNome);      panelForm.add(txtNome);
        panelForm.add(lblCognome);   panelForm.add(txtCognome);
        panelForm.add(lblEta);       panelForm.add(txtEta);
        panelForm.add(lblClasse);    panelForm.add(txtClasse);
        panelForm.add(lblAnnoCorso); panelForm.add(txtAnnoCorso);
        panelForm.add(btnAggiungi);  panelForm.add(btnPulisci);

        // Area di output con scroll per visualizzare l'elenco degli alunni
        areaOutput = new JTextArea();
        areaOutput.setEditable(false);
        JScrollPane scroll = new JScrollPane(areaOutput);
        scroll.setBorder(BorderFactory.createTitledBorder("Elenco Alunni"));

        add(panelForm, BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);

        // Caricamento iniziale: si usa il service già popolato da Main, non FileManager direttamente
        for (Alunno a : alunnoService.getAlunni()) {
            mostraAlunno(a);
        }

        // Evento pulsante "Aggiungi": valida i campi, controlla duplicati e salva il nuovo alunno
        btnAggiungi.addActionListener(e -> {

            try {

                int id          = Integer.parseInt(txtId.getText().trim());
                String nome     = txtNome.getText().trim();
                String cognome  = txtCognome.getText().trim();
                int eta         = Integer.parseInt(txtEta.getText().trim());
                String classe   = txtClasse.getText().trim();
                int annoCorso   = Integer.parseInt(txtAnnoCorso.getText().trim());

                // Tutti i campi stringa devono essere compilati prima di procedere
                if (nome.isEmpty() || cognome.isEmpty() || classe.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Compilare tutti i campi");
                    return;
                }

                // Un ID già presente nel service indica un duplicato: si blocca l'inserimento
                if (alunnoService.cercaPerId(id) != null) {
                    JOptionPane.showMessageDialog(null, "ID già esistente");
                    return;
                }

                // Creazione e salvataggio del nuovo alunno nel service
                Alunno nuovo = new Alunno(id, nome, cognome, eta, classe, annoCorso);
                alunnoService.aggiungiAlunno(nuovo);

                mostraAlunno(nuovo);

                JOptionPane.showMessageDialog(null, "Alunno aggiunto correttamente");
                pulisciCampi();

            } catch (Exception ex) {
                // Errore di parsing numerico su ID, età o anno corso
                JOptionPane.showMessageDialog(null, "errore");
            }
        });

        // Evento pulsante "Pulisci": svuota tutti i campi del form
        btnPulisci.addActionListener(e -> pulisciCampi());
    }

    /*
    Aggiunge alla area di output la rappresentazione testuale di un alunno.
    @param a oggetto Alunno da visualizzare
    */
    private void mostraAlunno(Alunno a) {
        areaOutput.append("\n========================\n");
        areaOutput.append("ID: "        + a.getId()      + "\n");
        areaOutput.append("Nome: "      + a.getNome()     + "\n");
        areaOutput.append("Cognome: "   + a.getCognome()  + "\n");
        areaOutput.append("Età: "       + a.getEta()      + "\n");
        areaOutput.append("Classe: "    + a.getClasse()   + "\n");
        // isMinorenne() restituisce true se l'età è inferiore a 18
        areaOutput.append("Minorenne: " + (a.isMinorenne() ? "SI" : "NO") + "\n");
        areaOutput.append("========================\n");
    }

    /*
    Azzera il contenuto di tutti i campi del form e deseleziona la checkbox.
    Da invocare dopo un inserimento andato a buon fine o su richiesta esplicita dell'utente.
    */
    private void pulisciCampi() {
        txtId.setText("");
        txtNome.setText("");
        txtCognome.setText("");
        txtEta.setText("");
        txtClasse.setText("");
        txtAnnoCorso.setText("");
        chkMinorenne.setSelected(false);
    }
}