import java.util.ArrayList;

import model.Alunno;
public class AlunnoService {

    private ArrayList<Alunno> alunni;

    // ---------------- COSTRUTTORE ----------------

    public AlunnoService() {
        alunni = new ArrayList<>();
    }

    // ---------------- AGGIUNGI ALUNNO ----------------

    public void aggiungiAlunno(Alunno a) {

        if (a == null) {

            System.out.println("Alunno non valido");
            return;
        }

        alunni.add(a);

        System.out.println("Alunno aggiunto");
    }

    // ---------------- MOSTRA ALUNNI ----------------

    public void mostraAlunni() {

        if (alunni.isEmpty()) {

            System.out.println("Nessun alunno presente");
            return;
        }

        for (Alunno a : alunni) {

            System.out.println(a);
        }
    }

    // ---------------- CERCA ALUNNO ----------------

    public Alunno cercaPerId(int id) {

        for (Alunno a : alunni) {

            if (a.getId() == id) {

                return a;
            }
        }

        return null;
    }

    // ---------------- RIMUOVI ALUNNO ----------------

    public void rimuoviAlunno(int id) {

        Alunno a = cercaPerId(id);

        if (a == null) {

            System.out.println("Alunno non trovato");
            return;
        }

        alunni.remove(a);

        System.out.println("Alunno rimosso");
    }

    // ---------------- AUTORIZZAZIONE ----------------

    public void autorizzaAlunno(int id) {

        Alunno a = cercaPerId(id);

        if (a == null) {

            System.out.println("Alunno non trovato");
            return;
        }

        if (!a.isMinorenne()) {

            System.out.println(
                "L'alunno è maggiorenne, autorizzazione non necessaria"
            );

            return;
        }

        a.setAutorizzazione(true);

        System.out.println("Autorizzazione registrata");
    }

    // ---------------- RINUNCIA MEDICA ----------------

    public void rinunciaMedica(int id) {

        Alunno a = cercaPerId(id);

        if (a == null) {

            System.out.println("Alunno non trovato");
            return;
        }

        a.setRinunciaMedica(true);

        System.out.println(
            "Rinuncia medica registrata per "
            + a.getNome()
        );
    }

    // ---------------- CONTA ALUNNI ----------------

    public int numeroAlunni() {

        return alunni.size();
    }

    // ---------------- GETTER E SETTER ----------------

    public ArrayList<Alunno> getAlunni() {
        return alunni;
    }

    public void setAlunni(ArrayList<Alunno> alunni) {
        this.alunni = alunni;
    }
}
