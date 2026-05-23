public class Alunno {

    private int id;

    private String nome;
    private String cognome;
    private int eta;

    private String classe;
    private int annoCorso;

    private boolean minorenne;
    private boolean autorizzazione;
    private boolean rinunciaMedica;

    // ---------------- COSTRUTTORE ----------------

    public Alunno(int id, String nome, String cognome, int eta, String classe, int annoCorso) {

        this.id = id;
        this.nome = nome;
        this.cognome = cognome;
        this.eta = eta;
        this.classe = classe;
        this.annoCorso = annoCorso;

        this.minorenne = eta < 18;

        this.autorizzazione = false;
        this.rinunciaMedica = false;
    }

    // ---------------- GETTER ----------------

    public int getId() {
        return id;
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

    public boolean isAutorizzazione() {
        return autorizzazione;
    }

    public boolean isRinunciaMedica() {
        return rinunciaMedica;
    }

    // ---------------- SETTER ----------------

    public void setEta(int eta) {
        this.eta = eta;
        this.minorenne = eta < 18;
    }

    public void setAutorizzazione(boolean autorizzazione) {
        this.autorizzazione = autorizzazione;
    }

    public void setRinunciaMedica(boolean rinunciaMedica) {
        this.rinunciaMedica = rinunciaMedica;
    }

    public void setClasse(String classe) {
        this.classe = classe;
    }

    public void setAnnoCorso(int annoCorso) {
        this.annoCorso = annoCorso;
    }

    // ---------------- TO STRING ----------------

    @Override
    public String toString() {

        return "ID: " + id +
               " | " + nome + " " + cognome +
               " | Classe: " + classe +
               " | Età: " + eta +
               " | Minorenne: " + (minorenne ? "SI" : "NO") +
               " | Autorizzazione: " + (autorizzazione ? "SI" : "NO") +
               " | Rinuncia: " + (rinunciaMedica ? "SI" : "NO");
    }
}