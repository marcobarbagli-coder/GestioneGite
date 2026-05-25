//intera classe realizzata da Barbagli Marco
import java.io.Serializable;
public class Destinazione implements Serializable
{
    private static int contatore = 1;
    private int id;
    private String nome;
    private String regione;
    private String nazione;

    public Destinazione(String nome, String regione, String nazione) 
    {
        this.id = contatore;
        contatore++;
        this.nome = nome;
        this.regione = regione;
        this.nazione = nazione;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getRegione() {
        return regione;
    }

    public String getNazione() {
        return nazione;
    }

    
    public void setNome(String nome){
        this.nome=nome;
    }

    public void setRegione(String regione) {
        this.regione = regione;
    }

    public void setNazione(String nazione) {
        this.nazione = nazione;
    }
    @Override
    public String toString() {
        return nome + " - " + regione + " - " + nazione; 
    }
}    
