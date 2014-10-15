package uk.co.valtech.gridletters.steps;

import org.apache.commons.lang3.ArrayUtils;
import uk.co.valtech.gridletters.GridReferenceBuilder;
import uk.co.valtech.gridletters.ProcessingStep;
import uk.co.valtech.gridletters.domain.Reference;
import uk.co.valtech.gridletters.domain.OsgbPoint;
import uk.co.valtech.gridletters.util.GridMath;

/**
* Created by julianghionoiu on 14/10/2014.
*/
public class PublishBoxLetter implements ProcessingStep {
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

    private Reference reference;

    public PublishBoxLetter(Reference reference) {
        this.reference = reference;
    }

    @Override
    public OsgbPoint process(OsgbPoint currentPoint, GridReferenceBuilder sb) {
        int xIndex = GridMath.div(currentPoint.getX(), reference.getSize());
        int yIndex = GridMath.div(currentPoint.getY(), reference.getSize());
        sb.appendLetter(LETTERS[yIndex][xIndex]);
        return currentPoint;
    }
}
