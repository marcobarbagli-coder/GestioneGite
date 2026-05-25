import java.awt.*;
import javax.swing.*;

public class PanelItinerario extends JPanel {

    //componenti:

    private JLabel lblId;
    private JLabel lblDestinazione;
    private JLabel lblGiorni;
    private JLabel lblTipo;
    private JLabel lblCosto;

    private JTextField txtId;
    private JTextField txtDestinazione;
    private JTextField txtGiorni;
    private JTextField txtTipo;
    private JTextField txtCosto;

    private JButton btnAggiungi;
    private JButton btnPulisci;

    private JTextArea areaOutput;

    //costruttore:

    public PanelItinerario(ItinerarioService itinerarioService) {

        setLayout(new BorderLayout());

        // ---------------- PANEL FORM ----------------

        JPanel panelForm = new JPanel();

        panelForm.setBorder(
                BorderFactory.createTitledBorder("Inserimento Itinerario")
        );

        panelForm.setLayout(new GridLayout(6, 2, 10, 10));

        // label
        lblId = new JLabel("ID:");
        lblDestinazione = new JLabel("Destinazione:");
        lblGiorni = new JLabel("Giorni:");
        lblTipo = new JLabel("Tipo:");
        lblCosto = new JLabel("Costo:");

        // textfield
        txtId = new JTextField();
        txtDestinazione = new JTextField();
        txtGiorni = new JTextField();
        txtTipo = new JTextField();
        txtCosto = new JTextField();

        // bottoni
        btnAggiungi = new JButton("Aggiungi");
        btnPulisci = new JButton("Pulisci");

        // aggiunta componenti
        panelForm.add(lblId);
        panelForm.add(txtId);

        panelForm.add(lblDestinazione);
        panelForm.add(txtDestinazione);

        panelForm.add(lblGiorni);
        panelForm.add(txtGiorni);

        panelForm.add(lblTipo);
        panelForm.add(txtTipo);

        panelForm.add(lblCosto);
        panelForm.add(txtCosto);

        panelForm.add(btnAggiungi);
        panelForm.add(btnPulisci);

        // ---------------- AREA OUTPUT ----------------

        areaOutput = new JTextArea();

        areaOutput.setEditable(false);

        JScrollPane scroll = new JScrollPane(areaOutput);

        scroll.setBorder(
                BorderFactory.createTitledBorder("Elenco Itinerari")
        );

        // ---------------- AGGIUNTA PANNELLI ----------------

        add(panelForm, BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);

        // ---------------- EVENTO AGGIUNGI ----------------

        btnAggiungi.addActionListener(e -> {

            try {

                int id = Integer.parseInt(txtId.getText());

                String destinazione = txtDestinazione.getText();

                int giorni = Integer.parseInt(
                        txtGiorni.getText()
                );

                String tipo = txtTipo.getText();

                double costo = Double.parseDouble(
                        txtCosto.getText()
                );

                // controlli
                if (destinazione.isEmpty()
                        || tipo.isEmpty()) {

                    JOptionPane.showMessageDialog(
                            null,
                            "Compilare tutti i campi"
                    );

                    return;
                }

                // output
                areaOutput.append(
                        "\n========================\n"
                );

                areaOutput.append(
                        "ID: " + id + "\n"
                );

                areaOutput.append(
                        "Destinazione: "
                                + destinazione + "\n"
                );

                areaOutput.append(
                        "Giorni: "
                                + giorni + "\n"
                );

                areaOutput.append(
                        "Tipo: "
                                + tipo + "\n"
                );

                areaOutput.append(
                        "Costo: "
                                + costo + " €\n"
                );

                areaOutput.append(
                        "========================\n"
                );

                JOptionPane.showMessageDialog(
                        null,
                        "Itinerario aggiunto"
                );

                pulisciCampi();

            } catch (Exception ex) {

                JOptionPane.showMessageDialog(
                        null,
                        "Inserire dati validi"
                );
            }
        });

        // ---------------- EVENTO PULISCI ----------------

        btnPulisci.addActionListener(e -> {

            pulisciCampi();
        });
    }

    public PanelItinerario() {

    }

    // ---------------- METODO PULISCI ----------------

    private void pulisciCampi() {

        txtId.setText("");
        txtDestinazione.setText("");
        txtGiorni.setText("");
        txtTipo.setText("");
        txtCosto.setText("");
    }
}
