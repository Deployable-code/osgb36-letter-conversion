package uk.co.valtech.gridletters.steps;

import org.junit.Test;
import uk.co.valtech.gridletters.domain.OsgbPoint;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class ZoomInsideTest {

    private static final OsgbPoint SOME_POINT = new OsgbPoint(4, 5);
    private static final int SOME_SCALE = 2;
    private static final StringBuilder BUFFER = new StringBuilder();

    @Test
    public void shouldReturnedZoomInsidePositivePoint() throws Exception {
        ZoomInside instance = new ZoomInside(SOME_SCALE);

        OsgbPoint processedPoint =
                instance.process(SOME_POINT, BUFFER);

        assertThat(processedPoint, is(equalTo(SOME_POINT.zoomInside(2))));
    }
}