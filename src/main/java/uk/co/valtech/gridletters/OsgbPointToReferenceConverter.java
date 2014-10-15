package uk.co.valtech.gridletters;

import uk.co.valtech.gridletters.domain.OsgbPoint;
import uk.co.valtech.gridletters.domain.Reference;
import uk.co.valtech.gridletters.steps.PublishBoxLetter;
import uk.co.valtech.gridletters.steps.PublishDigits;
import uk.co.valtech.gridletters.steps.TranslateToRealOrigin;
import uk.co.valtech.gridletters.steps.ZoomInside;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static uk.co.valtech.gridletters.domain.Reference.*;

/**
* Created by julianghionoiu on 14/10/2014.
*/
class OsgbPointToReferenceConverter {

    private final List<ProcessingStep> steps;


    public OsgbPointToReferenceConverter(Reference targetReference) {

        Reference[] orderOfReferences = {
                BOX_2500km, BOX_500km, BOX_100km, BOX_10km, BOX_1km, BOX_100m, BOX_10m
        };

        Map<Reference, ProcessingStep> actionMap = new HashMap<>();
        actionMap.put(BOX_2500km, new PublishBoxLetter(BOX_500km));
        actionMap.put(BOX_500km,  new PublishBoxLetter(BOX_100km));
        actionMap.put(BOX_100km,  new PublishDigits(BOX_10km));
        actionMap.put(BOX_10km,   new PublishDigits(BOX_1km));
        actionMap.put(BOX_1km,    new PublishDigits(BOX_100m));
        actionMap.put(BOX_100m,   new PublishDigits(BOX_10m));
        actionMap.put(BOX_10m,    new PublishDigits(BOX_1m));

        //Create the steps
        steps = new LinkedList<>();
        steps.add(new TranslateToRealOrigin());

        for (Reference reference : orderOfReferences) {
            //Break on target
            if (reference.equals(targetReference)) {
                break;
            }

            //Add the actions
            steps.add(new ZoomInside(reference));
            steps.add(actionMap.get(reference));
        }
    }

    public String toGridReference(int eastings, int northings) {
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
