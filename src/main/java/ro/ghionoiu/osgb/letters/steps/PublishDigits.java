package ro.ghionoiu.osgb.letters.steps;

import ro.ghionoiu.osgb.letters.GridReferenceBuilder;
import ro.ghionoiu.osgb.letters.ProcessingStep;
import ro.ghionoiu.osgb.letters.domain.OsgbPoint;
import ro.ghionoiu.osgb.letters.domain.Reference;
import ro.ghionoiu.osgb.letters.util.GridMath;

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
