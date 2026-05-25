//intera classe realizzata da Barbagli Marco

//importazione librerie:
import java.io.Serializable;


public class Destinazione implements Serializable{
    private static int contatore = 1;
    private int id;
    private String nome;
    private String regione;
    private String nazione;

//costruttore:
    public Destinazione(String nome, String regione, String nazione) 
    {
        this.id = contatore;
        contatore++;
        this.nome = nome;
        this.regione = regione;
        this.nazione = nazione;
    }


//metodi getter e setter:
    public int getId() {
        return id;
    }

    public void setNome(String nome){
        this.nome=nome;
    }
    public String getNome() {
        return nome;
    }

    public void setRegione(String regione) {
        this.regione = regione;
    }
    public String getRegione() {
        return regione;
    }

    public void setNazione(String nazione) {
        this.nazione = nazione;
    }
    public String getNazione() {
        return nazione;
    }

    
    @Override
    public String toString() {
        return "Itinerario "+nome + " - " + regione + " - " + nazione; 
    }
}    
