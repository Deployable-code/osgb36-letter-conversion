package uk.co.valtech.gridletters;

/**
 * Created by julianghionoiu on 15/10/2014.
 */
public class GridReferenceBuilder {
    private StringBuilder sb;

    public GridReferenceBuilder() {
        sb = new StringBuilder();
    }

    public void append(String s) {
        sb.append(s);
    }

    public void append(char c) {
        sb.append(c);
    }

    public void append(int i) {
        sb.append(i);
    }

    @Override
    public String toString() {
        return sb.toString();
    }
}
