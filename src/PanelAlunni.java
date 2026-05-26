/*
COPYRIGHT (c) 2013 MySoft snc. All Rights Res.
Classe che definisce l'interfaccia con la quale l'utente interagisce con le liste e gli oggetti Alunno
@aithor Alex Bucsai
@version 1.0
*/

import java.awt.*;
import javax.swing.*;

public class PanelAlunni extends JPanel {

    private JLabel lblId;
    private JLabel lblNome;
    private JLabel lblCognome;
    private JLabel lblEta;
    private JLabel lblClasse;
    private JLabel lblAnnoCorso;

    private JTextField txtId;
    private JTextField txtNome;
    private JTextField txtCognome;
    private JTextField txtEta;
    private JTextField txtClasse;
    private JTextField txtAnnoCorso;

    private JCheckBox chkMinorenne;

    private JButton btnAggiungi;
    private JButton btnPulisci;

    private JTextArea areaOutput;

    public PanelAlunni(AlunnoService alunnoService) {

        setLayout(new BorderLayout());

        // ---------------- PANEL FORM ----------------

        JPanel panelForm = new JPanel();
        panelForm.setBorder(BorderFactory.createTitledBorder("Inserimento Alunno"));
        panelForm.setLayout(new GridLayout(7, 2, 10, 10));

        lblId = new JLabel("ID:");
        lblNome = new JLabel("Nome:");
        lblCognome = new JLabel("Cognome:");
        lblEta = new JLabel("Età:");
        lblClasse = new JLabel("Classe:");
        lblAnnoCorso = new JLabel("Anno corso:");

        txtId = new JTextField();
        txtNome = new JTextField();
        txtCognome  = new JTextField();
        txtEta = new JTextField();
        txtClasse = new JTextField();
        txtAnnoCorso = new JTextField();

        chkMinorenne = new JCheckBox("Minorenne (calcolato automaticamente dall'età)");
        chkMinorenne.setEnabled(false);

        btnAggiungi = new JButton("Aggiungi");
        btnPulisci  = new JButton("Pulisci");

        panelForm.add(lblId);        
        panelForm.add(txtId);

        panelForm.add(lblNome);      
        panelForm.add(txtNome);

        panelForm.add(lblCognome);   
        panelForm.add(txtCognome);

        panelForm.add(lblEta);       
        panelForm.add(txtEta);

        panelForm.add(lblClasse);    
        panelForm.add(txtClasse);

        panelForm.add(lblAnnoCorso); 
        panelForm.add(txtAnnoCorso);

        panelForm.add(btnAggiungi);  
        panelForm.add(btnPulisci);

        // ---------------- AREA OUTPUT ----------------

        areaOutput = new JTextArea();
        areaOutput.setEditable(false);
        JScrollPane scroll = new JScrollPane(areaOutput);
        scroll.setBorder(BorderFactory.createTitledBorder("Elenco Alunni"));

        add(panelForm, BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);

        // ---------------- CARICAMENTO DA SERVICE ----------------
        // FIX: si usa il service già popolato da Main, non FileManager direttamente

        for (Alunno a : alunnoService.getAlunni()) {
            mostraAlunno(a);
        }

        // ---------------- EVENTO AGGIUNGI ----------------

        btnAggiungi.addActionListener(e -> {

            try {

                int id = Integer.parseInt(txtId.getText().trim());
                String nome = txtNome.getText().trim();
                String cognome = txtCognome.getText().trim();
                int eta = Integer.parseInt(txtEta.getText().trim());
                String classe = txtClasse.getText().trim();
                int annoCorso = Integer.parseInt(txtAnnoCorso.getText().trim());

                if (nome.isEmpty() || cognome.isEmpty() || classe.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Compilare tutti i campi");
                    return;
                }

                // FIX: controlla ID duplicato
                if (alunnoService.cercaPerId(id) != null) {
                    JOptionPane.showMessageDialog(null, "ID già esistente");
                    return;
                }

                // FIX: crea l'oggetto e lo salva nel service
                Alunno nuovo = new Alunno(id, nome, cognome, eta, classe, annoCorso);
                alunnoService.aggiungiAlunno(nuovo);

                mostraAlunno(nuovo);

                JOptionPane.showMessageDialog(null, "Alunno aggiunto correttamente");
                pulisciCampi();

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null,"errore");
            }
        });

        // ---------------- EVENTO PULISCI ----------------

        btnPulisci.addActionListener(e -> pulisciCampi());
    }

    // ---------------- METODI PRIVATI ----------------

    private void mostraAlunno(Alunno a) {
        areaOutput.append("\n========================\n");
        areaOutput.append("ID: " + a.getId() + "\n");
        areaOutput.append("Nome: " + a.getNome() + "\n");
        areaOutput.append("Cognome: " + a.getCognome() + "\n");
        areaOutput.append("Età: "+ a.getEta() + "\n");
        areaOutput.append("Classe: " + a.getClasse()  + "\n");
        areaOutput.append("Minorenne: " + (a.isMinorenne() ? "SI" : "NO") + "\n");
        areaOutput.append("========================\n");
    }

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