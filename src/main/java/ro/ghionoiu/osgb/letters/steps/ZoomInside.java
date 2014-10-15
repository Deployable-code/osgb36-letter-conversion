package ro.ghionoiu.osgb.letters.steps;

import ro.ghionoiu.osgb.letters.GridReferenceBuilder;
import ro.ghionoiu.osgb.letters.ProcessingStep;
import ro.ghionoiu.osgb.letters.domain.Reference;
import ro.ghionoiu.osgb.letters.domain.OsgbPoint;

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
