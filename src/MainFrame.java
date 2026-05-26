/*
COPYRIGHT (c) 2013 MySoft snc. All Rights Res.
Classe Main che si occupa di collegare e far in modo che gli altri panel siano collegsti tramite un menu
@aithor Alex Bucsai, Marco  Barbagli 
@version 1.0
*/

import java.awt.*;
import javax.swing.*;

public class MainFrame extends JFrame {

    // ---------------- SERVICE ----------------

    private AlunnoService alunnoService;
    private ItinerarioService itinerarioService;
    private PrenotazioneService prenotazioneService;

    // ---------------- COMPONENTI ----------------

    private JButton btnAlunni;
    private JButton btnItinerari;
    private JButton btnPrenotazioni;
    private JButton btnEsci;

    private JPanel panelCentro;
    private CardLayout cardLayout;

    // ---------------- COSTRUTTORE ----------------

    public MainFrame(AlunnoService alunnoService,
                     ItinerarioService itinerarioService,
                     PrenotazioneService prenotazioneService) {

        this.alunnoService = alunnoService;
        this.itinerarioService = itinerarioService;
        this.prenotazioneService = prenotazioneService;

        setTitle("Gestione Gite Scolastiche");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // ---------------- PANNELLO MENU LATERALE ----------------

        JPanel panelMenu = new JPanel();
        panelMenu.setLayout(new GridLayout(4, 1, 10, 10));
        panelMenu.setPreferredSize(new Dimension(200, 0));
        panelMenu.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        btnAlunni = new JButton("Gestione Alunni");
        btnItinerari = new JButton("Gestione Itinerari");
        btnPrenotazioni = new JButton("Gestione Prenotazioni");
        btnEsci = new JButton("Esci");

        panelMenu.add(btnAlunni);
        panelMenu.add(btnItinerari);
        panelMenu.add(btnPrenotazioni);
        panelMenu.add(btnEsci);

        // ---------------- PANNELLO CENTRALE CON CARDLAYOUT ----------------

        cardLayout = new CardLayout();
        panelCentro = new JPanel(cardLayout);

        panelCentro.add(new PanelAlunni(alunnoService), "alunni");
        panelCentro.add(new PanelItinerario(itinerarioService), "itinerari");
        panelCentro.add(new PanelPrenotazione(prenotazioneService, alunnoService, itinerarioService), "prenotazioni");

        // mostra alunni come schermata iniziale
        cardLayout.show(panelCentro, "alunni");

        // ---------------- AGGIUNTA COMPONENTI ----------------

        add(panelMenu, BorderLayout.WEST);
        add(panelCentro, BorderLayout.CENTER);

        // ---------------- EVENTI BOTTONI ----------------

        btnAlunni.addActionListener(e ->
                cardLayout.show(panelCentro, "alunni")
        );

        btnItinerari.addActionListener(e ->
                cardLayout.show(panelCentro, "itinerari")
        );

        btnPrenotazioni.addActionListener(e ->
                cardLayout.show(panelCentro, "prenotazioni")
        );

        btnEsci.addActionListener(e -> {

            int scelta = JOptionPane.showConfirmDialog(this,"Vuoi uscire dal programma?", "Conferma uscita", JOptionPane.YES_NO_OPTION);

            if (scelta == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        });

        // ---------------- VISIBILITA ----------------

        setVisible(true);
    }
}