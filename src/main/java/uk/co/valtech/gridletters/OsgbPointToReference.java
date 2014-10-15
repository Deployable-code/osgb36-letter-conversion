package uk.co.valtech.gridletters;

import uk.co.valtech.gridletters.domain.OsgbPoint;
import uk.co.valtech.gridletters.domain.Scale;
import uk.co.valtech.gridletters.steps.*;
import uk.co.valtech.gridletters.util.GridMath;

/**
* Created by julianghionoiu on 14/10/2014.
*/
class OsgbPointToReference {
    private ProcessingStep[] steps;

    public OsgbPointToReference() {
        this(new TranslateToRealOrigin(),
             new ZoomInside(Scale.KM_2500),
             new PublishBoxLetter(Scale.KM_500),
             new ZoomInside(Scale.KM_500),
             new PublishBoxLetter(Scale.KM_100),
             new ZoomInside(Scale.KM_100),
            (OsgbPoint currentPoint, StringBuilder sb) -> {
                int xIndex = GridMath.div(currentPoint.getX(), Scale.KM_10);
                int yIndex = GridMath.div(currentPoint.getY(), Scale.KM_10);
                System.out.println(xIndex+"-"+yIndex);
                sb.append(xIndex);
                sb.append(yIndex);
                return currentPoint;
            }
        );
    }

    OsgbPointToReference(ProcessingStep... steps) {
        this.steps = steps;
    }

    public String convert(int eastings, int northings) {
        return convert(new OsgbPoint(eastings,northings));
    }

    public String convert(OsgbPoint currentPoint) {
        StringBuilder sb = new StringBuilder();

        for (ProcessingStep action : steps) {
            currentPoint = action.process(currentPoint, sb);
        }

        return sb.toString();
    }
}
