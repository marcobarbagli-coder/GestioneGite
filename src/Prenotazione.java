/*
COPYRIGHT (c) 2013 MySoft snc. All Rights Res.
Classe che definisce l'oggetto Prenotazione
@aithor Alex Bucsai
@version 1.0
*/

//importazione librerie:
import java.io.Serializable;
import java.util.ArrayList;

public class Prenotazione implements Serializable {

//attributi:
    private int id;

    private Itinerario itinerario;

    private ArrayList<Classe> classi;
    private ArrayList<Alunno> partecipanti;

    private double acconto;

    private boolean annullata;
    private String motivoAnnullamento;


//costruttore:
    public Prenotazione(int id, Itinerario itinerario) {
        this.id = id;
        this.itinerario = itinerario;

        classi = new ArrayList<>();
        partecipanti = new ArrayList<>();

        annullata = false;
    }


//metodi propri della classe:

    public void aggiungiPartecipante(Alunno a) {
        partecipanti.add(a);
    }
    public void rimuoviPartecipante(Alunno a) {
        partecipanti.remove(a);
    }
    public void aggiungiClasse(Classe c) {
        classi.add(c);
    }

    public double calcolaCostoTotale() {
        return itinerario.getCosto() * partecipanti.size();
    }

    // calcola acconto 10%
    public double calcolaAcconto() {
        acconto = calcolaCostoTotale() * 0.10;
        return acconto;
    }

    public void annullaGita(String motivo) {
        annullata = true;
        motivoAnnullamento = motivo;
    }

    public int numeroPartecipanti() {
        return partecipanti.size();
    }

    
//metodi getter e setter:

    public int getIdPrenotazione() {
        return id;
    }
    public void setIdPrenotazione(int id) {
        this.id = id;
    }

    public Itinerario getItinerario() {
        return itinerario;
    }
    public void setItinerario(Itinerario itinerario) {
        this.itinerario = itinerario;
    }

    public ArrayList<Classe> getClassi() {
        return classi;
    }
    public void setClassi(ArrayList<Classe> classi) {
        this.classi = classi;
    }

    public ArrayList<Alunno> getPartecipanti() {
        return partecipanti;
    }
    public void setPartecipanti(ArrayList<Alunno> partecipanti) {
        this.partecipanti = partecipanti;
    }

    public double getAcconto() {
        return acconto;
    }
    public void setAcconto(double acconto) {
        this.acconto = acconto;
    }

    public boolean isAnnullata() {
        return annullata;
    }
    public String getMotivoAnnullamento() {
        return motivoAnnullamento;
    }

    @Override
    public String toString() {

        return "Prenotazione[ ID: " + id + 
                "\nDestinazione: " + itinerario.getDestinazione().getNome() +
                "\nPartecipanti: " + partecipanti.size() +
                "\nCosto Totale: " + calcolaCostoTotale() +
                "\nAcconto: " + calcolaAcconto() +
                "\nAnnullata: " + annullata + 
                "]";
    }


}
