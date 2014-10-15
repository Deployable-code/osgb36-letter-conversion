package ro.ghionoiu.osgb.letters;

/**
 * Created by julianghionoiu on 15/10/2014.
 */
public class GridReferenceBuilder {
    private StringBuilder letters;
    private StringBuilder digitsX;
    private StringBuilder digitsY;

    public GridReferenceBuilder() {
        letters = new StringBuilder();
        digitsX = new StringBuilder();
        digitsY = new StringBuilder();
    }

    public void appendLetter(char c) {
        letters.append(c);
    }

    public void appendDigitX(int i) {
        digitsX.append(i);
    }

    public void appendDigitY(int i) {
        digitsY.append(i);
    }

    @Override
    public String toString() {
        return letters.toString() + digitsX.toString() + digitsY.toString();
    }
}
