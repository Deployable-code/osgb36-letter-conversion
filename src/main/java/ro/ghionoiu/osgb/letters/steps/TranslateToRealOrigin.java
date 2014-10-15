package ro.ghionoiu.osgb.letters.steps;

import ro.ghionoiu.osgb.letters.GridReferenceBuilder;
import ro.ghionoiu.osgb.letters.ProcessingStep;
import ro.ghionoiu.osgb.letters.domain.OsgbPoint;
import ro.ghionoiu.osgb.letters.domain.Units;

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
