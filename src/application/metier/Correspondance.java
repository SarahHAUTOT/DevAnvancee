package application.metier;

public class Correspondance {
    String text;
    PlageDeMots positionCompare;
    PlageDeMots positionComparant;
    TextComparant textRef;

    public Correspondance(String text, PlageDeMots positionCompare, PlageDeMots positionComparant, TextComparant textRef) {
        this.text = text;
        this.positionCompare = positionCompare;
        this.positionComparant = positionComparant;
        this.textRef = textRef;
    }

    @Override
    public String toString() {
        return String.format(
                "Segment: '%s', Position dans le texte comparé: [%s], Position dans le texte de référence: [%s]",
                text, positionCompare, positionComparant);
    }
}
