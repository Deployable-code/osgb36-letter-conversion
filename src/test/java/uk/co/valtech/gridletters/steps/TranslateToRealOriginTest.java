package uk.co.valtech.gridletters.steps;

import org.junit.Before;
import org.junit.Test;
import uk.co.valtech.gridletters.domain.OsgbPoint;
import uk.co.valtech.gridletters.domain.Scale;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

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
        OsgbPoint fakeOrigin = new OsgbPoint(0,0);

        OsgbPoint translatedPoint = instance.process(fakeOrigin, new StringBuilder());

        assertThat(translatedPoint, is(equalTo(new OsgbPoint(2 * Scale.KM_500, Scale.KM_500))));
    }

    @Test
    public void shouldNotOutputAnything() throws Exception {
        StringBuilder sb = new StringBuilder();

        instance.process(SOME_POINT, sb);

        assertThat(sb.toString(), is(EMPTY));
    }
}