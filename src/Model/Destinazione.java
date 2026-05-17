//intera classe realizzata da Barbagli Marco 
public class Destinazione 
{
    private static int contatore = 1;
    private int id;
    private String nome;
    private String regione;
    private String nazione;

    public Destinazione(int id, String nome, String regione, String nazione) 
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

    
    public setNome(String Nome){
        this.nome=nome;
    }

    public  setRegione(String regione) {
        this.regione = regione;
    }

    public  setNazione(String nazione) {
        this.nazione = nazione;
    }
    @Override
    public String toString() {
        return nome + " - " + regione + " - " + nazione; 
    }
}    
