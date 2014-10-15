package uk.co.valtech.gridletters;

import uk.co.valtech.gridletters.domain.OsgbPoint;
import uk.co.valtech.gridletters.steps.PublishBoxLetter;
import uk.co.valtech.gridletters.steps.PublishDigits;
import uk.co.valtech.gridletters.steps.TranslateToRealOrigin;
import uk.co.valtech.gridletters.steps.ZoomInside;

import static uk.co.valtech.gridletters.domain.Reference.*;

/**
* Created by julianghionoiu on 14/10/2014.
*/
class OsgbPointToReference {
    private final ProcessingStep[] steps;

    public OsgbPointToReference() {
        this.steps = new ProcessingStep[] {
             new TranslateToRealOrigin(),
             new ZoomInside(BOX_2500km),
             new PublishBoxLetter(BOX_500km),
             new ZoomInside(BOX_500km),
             new PublishBoxLetter(BOX_100km),
             new ZoomInside(BOX_100km),
             new PublishDigits(BOX_10km),
             new ZoomInside(BOX_10km),
             new PublishDigits(BOX_1km),
             new ZoomInside(BOX_1km),
             new PublishDigits(BOX_100m),
             new ZoomInside(BOX_100m),
             new PublishDigits(BOX_10m),
             new ZoomInside(BOX_10m),
             new PublishDigits(BOX_1m),
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
