package uk.co.valtech.gridletters.steps;

import uk.co.valtech.gridletters.GridReferenceBuilder;
import uk.co.valtech.gridletters.ProcessingStep;
import uk.co.valtech.gridletters.domain.Reference;
import uk.co.valtech.gridletters.domain.OsgbPoint;

/**
* Created by julianghionoiu on 14/10/2014.
*/
public class ZoomInside implements ProcessingStep {
    private Reference reference;

    public ZoomInside(Reference reference) {
        this.reference = reference;
    }

    @Override
    public OsgbPoint process(OsgbPoint currentPoint, GridReferenceBuilder sb) {
        return currentPoint.zoomInside(reference);
    }
}
