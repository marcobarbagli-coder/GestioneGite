import java.util.ArrayList;

public class Prenotazione {

    private int id;

    private Itinerario itinerario;

    private ArrayList<Classe> classi;
    private ArrayList<Alunno> partecipanti;

    private double acconto;

    private boolean annullata;
    private String motivoAnnullamento;



    public Prenotazione() {
        classi = new ArrayList<>();
        partecipanti = new ArrayList<>();
        annullata = false;
    }

    public Prenotazione(int id, Itinerario itinerario) {
        this.id = id;
        this.itinerario = itinerario;

        classi = new ArrayList<>();
        partecipanti = new ArrayList<>();

        annullata = false;
    }

    

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

   

    // aggiunge partecipante
    public void aggiungiPartecipante(Alunno a) {
        partecipanti.add(a);
    }

    // rimuove partecipante
    public void rimuoviPartecipante(Alunno a) {
        partecipanti.remove(a);
    }

    // aggiunge classe
    public void aggiungiClasse(Classe c) {
        classi.add(c);
    }

    // calcola costo totale
    public double calcolaCostoTotale() {

        return itinerario.getCosto() *
               partecipanti.size();
    }

    // calcola acconto 10%
    public double calcolaAcconto() {

        acconto = calcolaCostoTotale() * 0.10;

        return acconto;
    }

    // annulla gita
    public void annullaGita(String motivo) {

        annullata = true;
        motivoAnnullamento = motivo;
    }

    // numero partecipanti
    public int numeroPartecipanti() {
        return partecipanti.size();
    }



    @Override
    public String toString() {

        return "Prenotazione ID: " + id +
               "\nDestinazione: " + itinerario.getDestinazione().getNome() +
               "\nPartecipanti: " + partecipanti.size() +
               "\nCosto Totale: " + calcolaCostoTotale() +
               "\nAcconto: " + calcolaAcconto() +
               "\nAnnullata: " + annullata;
    }
}
