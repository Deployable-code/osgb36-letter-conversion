package uk.co.valtech.gridletters.steps;

import org.junit.Test;
import uk.co.valtech.gridletters.GridReferenceBuilder;
import uk.co.valtech.gridletters.domain.Reference;
import uk.co.valtech.gridletters.domain.OsgbPoint;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class ZoomInsideTest {

    private static final OsgbPoint SOME_POINT = new OsgbPoint(4, 5);
    private static final Reference SOME_SCALE = Reference.BOX_100km;
    private static final GridReferenceBuilder BUILDER = new GridReferenceBuilder();

    @Test
    public void shouldReturnedZoomInsidePositivePoint() throws Exception {
        ZoomInside instance = new ZoomInside(SOME_SCALE);

        OsgbPoint processedPoint =
                instance.process(SOME_POINT, BUILDER);

        assertThat(processedPoint, is(equalTo(SOME_POINT.zoomInside(SOME_SCALE))));
    }
}