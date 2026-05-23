
public class Alunno {

    private String nome;
    private String cognome;
    private int eta;

    private String classe;
    private int annoCorso;

    private boolean minorenne;

    public Alunno(String nome, String cognome, int eta, String classe, int annoCorso) {
        this.nome = nome;
        this.cognome = cognome;
        this.eta = eta;
        this.classe = classe;
        this.annoCorso = annoCorso;
        this.minorenne = eta < 18;
    }

    public String getNome() {
        return nome;
    }

    public String getCognome() {
        return cognome;
    }

    public int getEta() {
        return eta;
    }

    public String getClasse() {
        return classe;
    }

    public int getAnnoCorso() {
        return annoCorso;
    }

    public boolean isMinorenne() {
        return minorenne;
    }

    public void setEta(int eta) {
        this.eta = eta;
        this.minorenne = eta < 18;
    }

    @Override
    public String toString() {
        return nome + " - " + cognome + " - " + classe;
    }
}
