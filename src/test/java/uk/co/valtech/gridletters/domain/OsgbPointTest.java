package uk.co.valtech.gridletters.domain;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class OsgbPointTest {


    @Test
    public void translationShouldShiftPointByOffset() throws Exception {
        OsgbPoint instance = new OsgbPoint(1, 1);


        OsgbPoint translated = instance.translateWith(
                new OsgbPoint(1,2));

        assertThat(translated, is(equalTo(new OsgbPoint(2,3))));
    }


    @Test
    public void zoomShouldTranslateTheCoordinatesRelativeToScale() throws Exception {
        OsgbPoint original = new OsgbPoint(9, 9);

        OsgbPoint zoomed = original.zoomInside(5);

        assertThat(zoomed, is(equalTo(new OsgbPoint(4,4))));
    }

    @Test
    public void fulfillsEqualsContract() {
        EqualsVerifier.forClass(OsgbPoint.class).verify();
    }
}