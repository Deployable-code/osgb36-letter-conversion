package uk.co.valtech.gridletters.steps;

import uk.co.valtech.gridletters.ProcessingStep;
import uk.co.valtech.gridletters.domain.OsgbPoint;

/**
* Created by julianghionoiu on 14/10/2014.
*/
public class ZoomInside implements ProcessingStep {
    private int scale;

    public ZoomInside(int scale) {
        this.scale = scale;
    }

    @Override
    public OsgbPoint process(OsgbPoint currentPoint, StringBuilder sb) {
        return currentPoint.zoomInside(scale);
    }
}
