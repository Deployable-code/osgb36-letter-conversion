package uk.co.valtech.gridletters.steps;

import uk.co.valtech.gridletters.ProcessingStep;
import uk.co.valtech.gridletters.domain.OsgbPoint;
import uk.co.valtech.gridletters.domain.Scale;

/**
* Created by julianghionoiu on 14/10/2014.
*/
public class TranslateToRealOrigin implements ProcessingStep {

    @Override
    public OsgbPoint process(OsgbPoint currentPoint, StringBuilder sb) {
        OsgbPoint gridOriginOffset = new OsgbPoint(2* Scale.KM_500, Scale.KM_500);
        currentPoint = currentPoint.translateWith(gridOriginOffset);
        return currentPoint;
    }
}
