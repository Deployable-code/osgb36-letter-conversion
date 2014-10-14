package uk.co.valtech.gridletters;

/**
* Created by julianghionoiu on 14/10/2014.
*/
public interface ProcessingStep {
    OsgbPoint process(OsgbPoint currentPoint, StringBuilder sb);
}
