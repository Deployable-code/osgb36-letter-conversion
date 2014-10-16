package ro.ghionoiu.osgb.letters;

import ro.ghionoiu.osgb.letters.domain.OsgbPoint;
import ro.ghionoiu.osgb.letters.domain.Reference;
import ro.ghionoiu.osgb.letters.steps.PublishBoxLetter;
import ro.ghionoiu.osgb.letters.steps.PublishDigits;
import ro.ghionoiu.osgb.letters.steps.TranslateToRealOrigin;
import ro.ghionoiu.osgb.letters.steps.ZoomInside;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static ro.ghionoiu.osgb.letters.domain.Reference.*;

/**
* Created by julianghionoiu on 14/10/2014.
*/
public class OsgbPointToReferenceConverter {
    private final List<ProcessingStep> steps;


    /**
     * Construct a converter from OSGB36 coordinates to the letter representation.
     * The reference parameter will dictate the precision used for the displayed string.
     * For example:
     *   If you use <tt>Reference.BOX_10km</tt>
     *   You will get a 4 letter string such as: SV12
     *
     * @param targetReference the reference the string representation will be based on
     * @see Reference
     */
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

    /**
     * Convert OSGB36 coordinates to the string representation.
     * @param eastings
     * @param northings
     * @return String representation
     */
    public String toGridReference(int eastings, int northings) {
        return convert(new OsgbPoint(eastings,northings));
    }

    /**
     * Convert an OsgbPoint to the string representation
     * @param currentPoint
     * @return
     */
    public String convert(OsgbPoint currentPoint) {
        GridReferenceBuilder builder = new GridReferenceBuilder();

        for (ProcessingStep action : steps) {
            currentPoint = action.process(currentPoint, builder);
        }

        return builder.toString();
    }
}
