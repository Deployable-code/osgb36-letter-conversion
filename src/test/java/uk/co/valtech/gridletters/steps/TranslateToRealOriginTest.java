package uk.co.valtech.gridletters.steps;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import uk.co.valtech.gridletters.domain.OsgbPoint;
import uk.co.valtech.gridletters.domain.Scale;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class TranslateToRealOriginTest {
    private static final OsgbPoint SOME_POINT = new OsgbPoint(1, 2);
    private static final String EMPTY = "";
    private TranslateToRealOrigin instance;

    @Before
    public void setUp() throws Exception {
        instance = new TranslateToRealOrigin();
    }


    @Test
    public void shouldTranslateAnyPointToOrigin() throws Exception {
        OsgbPoint fakeOrigin = mock(OsgbPoint.class);

        instance.process(fakeOrigin, new StringBuilder());

        //Capture argument
        ArgumentCaptor<OsgbPoint> captor = ArgumentCaptor.forClass(OsgbPoint.class);
        verify(fakeOrigin).translateWith(captor.capture());
        OsgbPoint translationPoint = captor.getValue();

        //Test
        assertThat(translationPoint.getX(), is( 2* Scale.KM_500));
        assertThat(translationPoint.getY(), is( Scale.KM_500));
    }



    @Test
    public void shouldNotOutputAnything() throws Exception {
        StringBuilder sb = new StringBuilder();

        instance.process(SOME_POINT, sb);

        assertThat(sb.toString(), is(EMPTY));
    }
}