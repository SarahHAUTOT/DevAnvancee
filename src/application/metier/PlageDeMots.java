package application.metier;

public class PlageDeMots {
    int debut;
    int fin;

    public PlageDeMots(int debut, int fin) {
        this.debut = debut;
        this.fin = fin;
    }

    @Override
    public String toString() {
        return String.format("Début : %d, Fin : %d", this.debut, this.fin);
    }

    

    public int getStart() {
        return debut;
    }

    public int getEnd() {
        return fin;
    }
}
