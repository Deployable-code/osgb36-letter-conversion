package ro.ghionoiu.osgb.letters;

import ro.ghionoiu.osgb.letters.domain.OsgbPoint;

/**
* Created by julianghionoiu on 14/10/2014.
*/
public interface ProcessingStep {
    OsgbPoint process(OsgbPoint currentPoint, GridReferenceBuilder sb);
}
