package uk.co.valtech.gridletters;

import uk.co.valtech.gridletters.domain.Boundary;
import uk.co.valtech.gridletters.domain.OsgbPoint;
import uk.co.valtech.gridletters.domain.Units;
import uk.co.valtech.gridletters.steps.PublishBoxLetter;
import uk.co.valtech.gridletters.steps.PublishDigits;
import uk.co.valtech.gridletters.steps.TranslateToRealOrigin;
import uk.co.valtech.gridletters.steps.ZoomInside;

/**
* Created by julianghionoiu on 14/10/2014.
*/
class OsgbPointToReference {
    private final ProcessingStep[] steps;

    public OsgbPointToReference() {
        this.steps = new ProcessingStep[] {
             new TranslateToRealOrigin(),
             new ZoomInside(Boundary.BOX_2500km),
             new PublishBoxLetter(Units.KM_500),
             new ZoomInside(Boundary.BOX_500km),
             new PublishBoxLetter(Units.KM_100),
             new ZoomInside(Boundary.BOX_100km),
             new PublishDigits(Units.KM_10),
             new ZoomInside(Boundary.BOX_10km),
             new PublishDigits(Units.KM_1),
             new ZoomInside(Boundary.BOX_1km),
             new PublishDigits(Units.M_100),
             new ZoomInside(Boundary.BOX_100m),
             new PublishDigits(Units.M_10),
             new ZoomInside(Boundary.BOX_10m),
             new PublishDigits(Units.M_1),
        };
    }

    public String convert(int eastings, int northings) {
        return convert(new OsgbPoint(eastings,northings));
    }

    public String convert(OsgbPoint currentPoint) {
        GridReferenceBuilder builder = new GridReferenceBuilder();

        for (ProcessingStep action : steps) {
            currentPoint = action.process(currentPoint, builder);
        }

        return builder.toString();
    }
}
