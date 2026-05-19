import java.util.ArrayList;

public class Classe {

    private int id;
    private String nome;
    private int anno;

    private ArrayList<Alunno> alunni;
    private ArrayList<Prenotazione> prenotazioni;


    public Classe(int id, String nome, int anno) {
        this.id = id;
        this.nome = nome;
        this.anno = anno;
        this.alunni = new ArrayList<>();
        this.prenotazioni = new ArrayList<>();
    }


    // aggiungi alunno
    public void aggiungiAlunno(Alunno a) {
        alunni.add(a);
    }
    // rimuovi alunno
    public void rimuoviAlunno(Alunno a) {
        alunni.remove(a);
    }
    // restituisce numero alunni
    public int numeroAlunni() {
        return alunni.size();
    }

    // aggiungi prenotazione
    public void aggiungiPrenotazione(Prenotazione p) {
        prenotazioni.add(p);
    }
    // rimuovi prenotazione
    public void rimuoviPrenotazione(Prenotazione p) {
        prenotazioni.remove(p);
    }




    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getAnno() {
        return anno;
    }
    public void setAnno(int anno) {
        this.anno = anno;
    }

    public ArrayList<Alunno> getAlunni() {
        return alunni;
    }
    public void setAlunni(ArrayList<Alunno> alunni) {
        this.alunni = alunni;
    }

    @Override
    public String toString() {
        return "Classe{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", anno=" + anno +
                ", alunni=" + alunni +
                '}';
    }
}