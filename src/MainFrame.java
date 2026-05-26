/*
COPYRIGHT (c) 2013 MySoft snc. All Rights Res.
Classe Main che si occupa di collegare e far in modo che gli altri panel siano collegati tramite un menu
@author Alex Bucsai, Marco Barbagli
@version 1.0 2026-05-26
*/
import java.awt.*;
import javax.swing.*;

public class MainFrame extends JFrame {

    // Service per la gestione dei dati di alunni, itinerari e prenotazioni
    private AlunnoService       alunnoService;
    private ItinerarioService   itinerarioService;
    private PrenotazioneService prenotazioneService;

    // Pulsanti del menu laterale per la navigazione tra le sezioni
    private JButton btnAlunni;
    private JButton btnItinerari;
    private JButton btnPrenotazioni;
    private JButton btnEsci;

    // Pannello centrale che contiene i pannelli delle sezioni, gestito da CardLayout
    private JPanel     panelCentro;
    private CardLayout cardLayout;

    /*
    Costruttore del frame principale. Inizializza i componenti grafici,
    collega i service ai pannelli e registra gli eventi dei pulsanti di navigazione.
    @param alunnoService       service che gestisce la lista degli alunni
    @param itinerarioService   service che gestisce la lista degli itinerari
    @param prenotazioneService service che gestisce la lista delle prenotazioni
    */
    public MainFrame(AlunnoService alunnoService,
                     ItinerarioService itinerarioService,
                     PrenotazioneService prenotazioneService) {

        this.alunnoService       = alunnoService;
        this.itinerarioService   = itinerarioService;
        this.prenotazioneService = prenotazioneService;

        setTitle("Gestione Gite Scolastiche");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Pannello menu laterale con i pulsanti di navigazione disposti in colonna
        JPanel panelMenu = new JPanel();
        panelMenu.setLayout(new GridLayout(4, 1, 10, 10));
        panelMenu.setPreferredSize(new Dimension(200, 0));
        panelMenu.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        btnAlunni       = new JButton("Gestione Alunni");
        btnItinerari    = new JButton("Gestione Itinerari");
        btnPrenotazioni = new JButton("Gestione Prenotazioni");
        btnEsci         = new JButton("Esci");

        panelMenu.add(btnAlunni);
        panelMenu.add(btnItinerari);
        panelMenu.add(btnPrenotazioni);
        panelMenu.add(btnEsci);

        // Pannello centrale con CardLayout: ogni sezione è identificata da una chiave stringa
        cardLayout  = new CardLayout();
        panelCentro = new JPanel(cardLayout);

        panelCentro.add(new PanelAlunni(alunnoService),                                               "alunni");
        panelCentro.add(new PanelItinerario(itinerarioService),                                        "itinerari");
        panelCentro.add(new PanelPrenotazione(prenotazioneService, alunnoService, itinerarioService),  "prenotazioni");

        // La schermata degli alunni viene mostrata all'avvio come vista predefinita
        cardLayout.show(panelCentro, "alunni");

        add(panelMenu,   BorderLayout.WEST);
        add(panelCentro, BorderLayout.CENTER);

        // Ogni pulsante di navigazione mostra il pannello corrispondente tramite CardLayout
        btnAlunni.addActionListener(e ->
            cardLayout.show(panelCentro, "alunni")
        );

        btnItinerari.addActionListener(e ->
            cardLayout.show(panelCentro, "itinerari")
        );

        btnPrenotazioni.addActionListener(e ->
            cardLayout.show(panelCentro, "prenotazioni")
        );

        // Prima di chiudere si chiede conferma all'utente per evitare uscite accidentali
        btnEsci.addActionListener(e -> {
            int scelta = JOptionPane.showConfirmDialog(
                this,
                "Vuoi uscire dal programma?",
                "Conferma uscita",
                JOptionPane.YES_NO_OPTION
            );
            if (scelta == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        });

        setVisible(true);
    }
}