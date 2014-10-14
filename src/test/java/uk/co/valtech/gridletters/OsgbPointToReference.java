package uk.co.valtech.gridletters;

/**
* Created by julianghionoiu on 14/10/2014.
*/
class OsgbPointToReference {
    private ProcessingStep[] steps;

    public OsgbPointToReference() {
        this(new TranslateToRealOrigin(),
             new ScaleAndPublishBox500(),
             new ScaleAndPublishBox100());
    }

    OsgbPointToReference(ProcessingStep... steps) {
        this.steps = steps;
    }

    public String convert(int eastings, int northings) {
        return convert(new OsgbPoint(eastings,northings));
    }

    public String convert(OsgbPoint currentPoint) {
        StringBuilder sb = new StringBuilder();

        for (ProcessingStep action : steps) {
            currentPoint = action.process(currentPoint, sb);
        }

        return sb.toString();
    }
}
