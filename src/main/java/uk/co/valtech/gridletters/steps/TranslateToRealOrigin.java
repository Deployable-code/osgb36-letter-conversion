package uk.co.valtech.gridletters.steps;

import uk.co.valtech.gridletters.GridReferenceBuilder;
import uk.co.valtech.gridletters.ProcessingStep;
import uk.co.valtech.gridletters.domain.OsgbPoint;
import uk.co.valtech.gridletters.domain.Units;

/**
* Created by julianghionoiu on 14/10/2014.
*/
public class TranslateToRealOrigin implements ProcessingStep {

    @Override
    public OsgbPoint process(OsgbPoint currentPoint, GridReferenceBuilder sb) {
        OsgbPoint gridOriginOffset =
                new OsgbPoint(2* Units.KM_500, Units.KM_500);
        currentPoint = currentPoint.translateWith(gridOriginOffset);
        return currentPoint;
    }
}
