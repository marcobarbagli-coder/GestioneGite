/*
COPYRIGHT (c) 2013 MySoft snc. All Rights Res.
Classe che definisce le operazione che coinvolgono gli oggetti Alunno
@aithor Barbagli Marco
@version 1.0
*/

//importazione librerie
import java.util.ArrayList;

public class AlunnoService {

    //attributi
    private ArrayList<Alunno> alunni;

    //costruttore 
    public AlunnoService() {
        alunni = new ArrayList<>();
    }


    //metodi propri della classe

    
    public void aggiungiAlunno(Alunno a) {

        if (a == null) {    //se l'alunno non esiste
            System.out.println("Alunno non valido");
            return;
        }

        alunni.add(a);
        System.out.println("Alunno aggiunto");
    }

    //stampa la lista di alunni esistenti
    public void mostraAlunni() {

        if (alunni.isEmpty()) { //se la lista è vuota
            System.out.println("Nessun alunno presente");
            return;
        }

        for (Alunno a : alunni) {   //stampa lista
            System.out.println(a);
        }
    }

    //ricerca alunno
    public Alunno cercaPerId(int id) {

        for (Alunno a : alunni) {
            
            if (a.getId() == id) {  //se corrisponde all'alunno cercato
                return a;
            }
        }

        return null;
    }

    public void rimuoviAlunno(int id) {

        Alunno a = cercaPerId(id); //alunno corispondente al ID

        if (a == null) { //se non trova l'alunno corrispondente al ID
            System.out.println("Alunno non trovato");
            return;
        }

        alunni.remove(a);
        System.out.println("Alunno rimosso");
    }

    //autorizzazione
    public void autorizzaAlunno(int id) {

        Alunno a = cercaPerId(id); //alunno corispondente al ID

        if (a == null) { //se non trova l'alunno corrispondente al ID
            System.out.println("Alunno non trovato");
            return;
        }

        if (!a.isMinorenne()) { //se è maggiorenne
            System.out.println(
            "L'alunno è maggiorenne, autorizzazione non necessaria");
            return;
        }

        a.setAutorizzazione(true);
        System.out.println("Autorizzazione registrata");
    }

    //set della variabile bool rinunciaMedica
    public void rinunciaMedica(int id) {

        Alunno a = cercaPerId(id);

        if (a == null) { //se non trova l'alunno corrispondente al ID
            System.out.println("Alunno non trovato");
            return;
        }

        a.setRinunciaMedica(true);
        System.out.println("Rinuncia medica registrata per " + a.getNome());
    }


    //metodi getter e setter

    public int numeroAlunni() {
        return alunni.size();
    }

    public ArrayList<Alunno> getAlunni() {
        return alunni;
    }

    public void setAlunni(ArrayList<Alunno> alunni) {
        this.alunni = alunni;
    }
}
