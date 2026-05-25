//intera classe realizzata da Bucsai Alex


import java.util.ArrayList;

public class Classe {

//attributi:
    private int id;
    private String nome;
    private int anno;

    private ArrayList<Alunno> alunni;
    private ArrayList<Prenotazione> prenotazioni;


//costruttore:
    public Classe(int id, String nome, int anno) {
        this.id = id;
        this.nome = nome;
        this.anno = anno;
        this.alunni = new ArrayList<>();
        this.prenotazioni = new ArrayList<>();
    }


//metodi propri della classe:

    // aggiunta di un alunno
    public void aggiungiAlunno(Alunno a) {
        alunni.add(a);
    }
    // rimuovozione di un alunno
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



//metodi getter e setter:
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

//metodo toString:
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