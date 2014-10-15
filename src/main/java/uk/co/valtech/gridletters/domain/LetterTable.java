package uk.co.valtech.gridletters.domain;

import org.apache.commons.lang3.ArrayUtils;
import uk.co.valtech.gridletters.util.GridMath;

/**
* Created by julianghionoiu on 14/10/2014.
*/
public class LetterTable {
    private static final char[][] LETTERS = {
            { 'A', 'B', 'C', 'D', 'E' },
            { 'F', 'G', 'H', 'J', 'K' },
            { 'L', 'M', 'N', 'O', 'P' },
            { 'Q', 'R', 'S', 'T', 'U' },
            { 'V', 'W', 'X', 'Y', 'Z' }
    };

    static {
        ArrayUtils.reverse(LETTERS);
    }

    public static char getLetterFor(OsgbPoint point, int scale) {
        int xIndex = GridMath.div(point.getX(), scale);
        int yIndex = GridMath.div(point.getY(), scale);
        return LETTERS[yIndex][xIndex];
    }
}
