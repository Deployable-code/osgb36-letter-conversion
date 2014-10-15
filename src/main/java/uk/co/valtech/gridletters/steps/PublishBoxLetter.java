package uk.co.valtech.gridletters.steps;

import uk.co.valtech.gridletters.ProcessingStep;
import uk.co.valtech.gridletters.domain.LetterTable;
import uk.co.valtech.gridletters.domain.OsgbPoint;
import uk.co.valtech.gridletters.domain.Scale;

/**
* Created by julianghionoiu on 14/10/2014.
*/
public class PublishBoxLetter implements ProcessingStep {
    private int scale;

    public PublishBoxLetter(int scale) {
        this.scale = scale;
    }

    @Override
    public OsgbPoint process(OsgbPoint currentPoint, StringBuilder sb) {
        sb.append(LetterTable.getLetterFor(currentPoint, scale));
        return currentPoint;
    }
}
