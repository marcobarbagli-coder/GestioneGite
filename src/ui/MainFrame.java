
import java.awt.*;
import javax.swing.*;

public class MainFrame extends JFrame {

//componenti:

    private JButton btnItinerari;
    private JButton btnPrenotazioni;
    private JButton btnAlunni;
    private JButton btnEsci;

    private JTextArea areaOutput;

//costruttore:

    public MainFrame() {

        setTitle("Gite Scolastiche");                   // titolo finestra
        setSize(800, 500);                              // dimensioni
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // chiusura programma
        setLocationRelativeTo(null);                    // posizione centro schermo
        setLayout(new BorderLayout());                  // layout principale

//costruzione interfaccia:

    //pannello menu:

        JPanel panelMenu = new JPanel();

        panelMenu.setLayout(new GridLayout(4, 1, 10, 10));
        btnItinerari = new JButton("Gestione Itinerari");
        btnPrenotazioni = new JButton("Gestione Prenotazioni");
        btnAlunni = new JButton("Gestione Alunni");
        btnEsci = new JButton("Esci");

        panelMenu.add(btnItinerari);
        panelMenu.add(btnPrenotazioni);
        panelMenu.add(btnAlunni);
        panelMenu.add(btnEsci);


    //area centrale:

        areaOutput = new JTextArea();
        areaOutput.setEditable(false);
        JScrollPane scroll = new JScrollPane(areaOutput);


    //aggiunta componenti

        add(panelMenu, BorderLayout.WEST);
        add(scroll, BorderLayout.CENTER);


    //evetni bottoni:

        btnItinerari.addActionListener(e -> {

            areaOutput.setText("");

            areaOutput.append(
                " GESTIONE ITINERARI \n\n"
            );

            areaOutput.append(
                "Qui verranno visualizzati gli itinerari.\n"
            );
        });

        btnPrenotazioni.addActionListener(e -> {

            areaOutput.setText("");

            areaOutput.append(
                " GESTIONE PRENOTAZIONI \n\n"
            );

            areaOutput.append(
                "Qui verranno visualizzate le prenotazioni.\n"
            );
        });

        btnAlunni.addActionListener(e -> {

            areaOutput.setText("");

            areaOutput.append(
                " GESTIONE ALUNNI \n\n"
            );

            areaOutput.append(
                "Qui verranno visualizzati gli alunni.\n"
            );
        });

        btnEsci.addActionListener(e -> {

            int scelta = JOptionPane.showConfirmDialog(
                    null,
                    "Vuoi uscire dal programma?",
                    "Conferma",
                    JOptionPane.YES_NO_OPTION
            );

            if (scelta == JOptionPane.YES_OPTION) {

                System.exit(0);
            }
        });


    //visibilita

        setVisible(true);
    }
}