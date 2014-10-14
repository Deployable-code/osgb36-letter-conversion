package uk.co.valtech.gridletters.steps;

import uk.co.valtech.gridletters.ProcessingStep;
import uk.co.valtech.gridletters.domain.LetterTable;
import uk.co.valtech.gridletters.domain.OsgbPoint;
import uk.co.valtech.gridletters.domain.Scale;

/**
* Created by julianghionoiu on 14/10/2014.
*/
public class ScaleAndPublishBox500 implements ProcessingStep {
    @Override
    public OsgbPoint process(OsgbPoint currentPoint, StringBuilder sb) {
        currentPoint = currentPoint.scaleInside(Scale.KM_2500);
        sb.append(LetterTable.getLetterFor(currentPoint, Scale.KM_500));
        return currentPoint;
    }
}
