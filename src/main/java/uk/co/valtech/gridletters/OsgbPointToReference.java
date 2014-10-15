package uk.co.valtech.gridletters;

import uk.co.valtech.gridletters.domain.OsgbPoint;
import uk.co.valtech.gridletters.domain.Scale;
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
             new ZoomInside(Scale.KM_2500),
             new PublishBoxLetter(Scale.KM_500),
             new ZoomInside(Scale.KM_500),
             new PublishBoxLetter(Scale.KM_100),
             new ZoomInside(Scale.KM_100),
             new PublishDigits(Scale.KM_10),
             new ZoomInside(Scale.KM_10),
             new PublishDigits(Scale.KM_1),
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
