/*
COPYRIGHT (c) 2026 MySoft snc. All Rights Res.
Classe che definisce l'oggetto Classe scolastica, con i relativi alunni e prenotazioni
@author Alex Bucsai
@version 1.0 2026-05-26
*/

// importazione librerie per la gestione delle liste
import java.util.ArrayList;

public class Classe {

    private int id;                                 // identificativo univoco della classe
    private String nome;                            // nome della classe (es. "3A")
    private int anno;                               // anno scolastico di riferimento
    private ArrayList<Alunno> alunni;               // lista degli alunni appartenenti alla classe
    private ArrayList<Prenotazione> prenotazioni;   // lista delle prenotazioni effettuate dalla classe (solo la classe può prenotare, non il singolo alunno)

    //metodo costruttore
    public Classe(int id, String nome, int anno) {
        this.id = id;
        this.nome = nome;
        this.anno = anno;
        this.alunni = new ArrayList<>();
        this.prenotazioni = new ArrayList<>();
    }

    // metodi propri della classe

    public void aggiungiAlunno(Alunno a) {
        alunni.add(a);
    }

    public void rimuoviAlunno(Alunno a) {
        alunni.remove(a);
    }

    //restituisce il numero degli alunni
    public int numeroAlunni() {
        return alunni.size();
    }

    public void aggiungiPrenotazione(Prenotazione p) {
        prenotazioni.add(p);
    }

    public void rimuoviPrenotazione(Prenotazione p) {
        prenotazioni.remove(p);
    }

    // metodi getter e setter

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
        return "Classe[" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", anno=" + anno +
                ", alunni=" + alunni +
                "]";
    }
}