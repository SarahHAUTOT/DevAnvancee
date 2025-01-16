package application.metier;

public class Correspondance {
    String text;
    PlageDeMots positionCompare;
    PlageDeMots positionComparant;
    TextComparant textRef;

    public Correspondance(String text, PlageDeMots positionCompare, PlageDeMots positionComparant,
            TextComparant textRef) {
        this.text = text;
        this.positionCompare = positionCompare;
        this.positionComparant = positionComparant;
        this.textRef = textRef;
    }

    @Override
    public String toString() {
        return String.format("Segment: '%s', comparé: [%s], %s: [%s]", "text", positionCompare, this.textRef.nom,
                positionComparant);
    }

    public PlageDeMots getComparedRange() {
        return positionCompare;
    }

    public PlageDeMots getReferenceRange() {
        return positionComparant;
    }

    public String getTexte()
    {
        return this.text;
    }

    public TextComparant getTexteComparant()
    {
        return this.textRef;
    }
}
