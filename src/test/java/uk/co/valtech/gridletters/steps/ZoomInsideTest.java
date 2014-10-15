package uk.co.valtech.gridletters.steps;

import org.junit.Before;
import org.junit.Test;
import uk.co.valtech.gridletters.domain.OsgbPoint;
import uk.co.valtech.gridletters.domain.Scale;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ZoomInsideTest {
    private static final OsgbPoint POINT_A = new OsgbPoint(1, 2);
    private ZoomInside instance;

    @Before
    public void setUp() throws Exception {
        instance = new ZoomInside(Scale.KM_500);
    }

    @Test
    public void shouldReturnedZoomInsideReceivedPoint() throws Exception {
        ZoomInside instance = new ZoomInside(Scale.KM_500);
        OsgbPoint point = mock(OsgbPoint.class);
        when(point.zoomInside(anyInt())).thenReturn(POINT_A);

        OsgbPoint processedPoint = instance.process(point, new StringBuilder());

        verify(point).zoomInside(Scale.KM_500);
        assertThat(processedPoint, is(POINT_A));
    }
}