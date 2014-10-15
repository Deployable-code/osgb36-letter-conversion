package uk.co.valtech.gridletters.steps;

import uk.co.valtech.gridletters.GridReferenceBuilder;
import uk.co.valtech.gridletters.ProcessingStep;
import uk.co.valtech.gridletters.domain.OsgbPoint;
import uk.co.valtech.gridletters.util.GridMath;

/**
 * Created by julianghionoiu on 15/10/2014.
 */
public class PublishDigits implements ProcessingStep {


    private int scale;

    public PublishDigits(int scale) {
        this.scale = scale;
    }


    @Override
    public OsgbPoint process(OsgbPoint currentPoint, GridReferenceBuilder sb) {
        int xIndex = GridMath.div(currentPoint.getX(), scale);
        int yIndex = GridMath.div(currentPoint.getY(), scale);
        sb.appendDigitX(xIndex);
        sb.appendDigitY(yIndex);
        return currentPoint;
    }

}
