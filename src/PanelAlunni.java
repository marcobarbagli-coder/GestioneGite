import java.awt.*;
import javax.swing.*;

public class PanelAlunni extends JPanel {

//componenti:

        //labels:
    private JLabel lblId;
    private JLabel lblNome;
    private JLabel lblCognome;

    private JTextField txtId;
    private JTextField txtNome;
    private JTextField txtCognome;

    private JCheckBox chkMinorenne;

    private JButton btnAggiungi;
    private JButton btnPulisci;

    private JTextArea areaOutput;


//costruttore:

    public PanelAlunni(AlunnoService alunnoService) {

//costruzione interfaccia:

        setLayout(new BorderLayout());

        //form
        JPanel panelForm = new JPanel();

        panelForm.setBorder(
                BorderFactory.createTitledBorder("Inserimento Alunno")
        );

        panelForm.setLayout(new GridLayout(5, 2, 10, 10));

        //labels
        lblId = new JLabel("ID:");
        lblNome = new JLabel("Nome:");
        lblCognome = new JLabel("Cognome:");

        //textfields
        txtId = new JTextField();
        txtNome = new JTextField();
        txtCognome = new JTextField();

        //checkbox
        chkMinorenne = new JCheckBox("Minorenne");

        //buttons
        btnAggiungi = new JButton("Aggiungi");
        btnPulisci = new JButton("Pulisci");

        //aggiunta componenti
        panelForm.add(lblId);
        panelForm.add(txtId);

        panelForm.add(lblNome);
        panelForm.add(txtNome);

        panelForm.add(lblCognome);
        panelForm.add(txtCognome);

        panelForm.add(new JLabel(""));

        panelForm.add(chkMinorenne);

        panelForm.add(btnAggiungi);
        panelForm.add(btnPulisci);

        
        //area output:

        areaOutput = new JTextArea();

        areaOutput.setEditable(false);

        JScrollPane scroll = new JScrollPane(areaOutput);

        scroll.setBorder(
                BorderFactory.createTitledBorder("Elenco Alunni")
        );

        //aggiunta pannelli:

        add(panelForm, BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);

        //evento bottone aggiungi:

        btnAggiungi.addActionListener(e -> {

            try {

                int id = Integer.parseInt(txtId.getText());

                String nome = txtNome.getText();
                String cognome = txtCognome.getText();

                boolean minorenne = chkMinorenne.isSelected();

                // controlli
                if (nome.isEmpty() || cognome.isEmpty()) {
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
                        "Nome: " + nome + "\n"
                );
                areaOutput.append(
                        "Cognome: " + cognome + "\n"
                );
                areaOutput.append(
                        "Minorenne: "
                                + (minorenne ? "SI" : "NO")
                                + "\n"
                );
                areaOutput.append(
                        "========================\n"
                );
                JOptionPane.showMessageDialog(
                        null,
                        "Alunno aggiunto correttamente"
                );

                pulisciCampi();

            } catch (Exception ex) {

                JOptionPane.showMessageDialog(
                        null,
                        "ID non valido"
                );
            }
        });

        //evento bottoni pulisci:

        btnPulisci.addActionListener(e -> {

            pulisciCampi();
        });
    }

    public PanelAlunni() {

    }


    //metodo pulizia:

    private void pulisciCampi() {

        txtId.setText("");
        txtNome.setText("");
        txtCognome.setText("");

        chkMinorenne.setSelected(false);
    }
}