package uk.co.valtech.gridletters.steps;

import uk.co.valtech.gridletters.GridReferenceBuilder;
import uk.co.valtech.gridletters.ProcessingStep;
import uk.co.valtech.gridletters.domain.OsgbPoint;
import uk.co.valtech.gridletters.domain.Reference;
import uk.co.valtech.gridletters.util.GridMath;

/**
 * Created by julianghionoiu on 15/10/2014.
 */
public class PublishDigits implements ProcessingStep {


    private Reference reference;

    public PublishDigits(Reference reference) {
        this.reference = reference;
    }


    @Override
    public OsgbPoint process(OsgbPoint currentPoint, GridReferenceBuilder sb) {
        int xIndex = GridMath.div(currentPoint.getX(), reference.getSize());
        int yIndex = GridMath.div(currentPoint.getY(), reference.getSize());
        sb.appendDigitX(xIndex);
        sb.appendDigitY(yIndex);
        return currentPoint;
    }

}
