package ro.ghionoiu.osgb.letters.steps;

import org.junit.Before;
import org.junit.Test;
import ro.ghionoiu.osgb.letters.GridReferenceBuilder;
import ro.ghionoiu.osgb.letters.domain.OsgbPoint;
import ro.ghionoiu.osgb.letters.domain.Units;

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

        OsgbPoint translatedPoint = instance.process(fakeOrigin, new GridReferenceBuilder());

        assertThat(translatedPoint, is(equalTo(new OsgbPoint(2 * Units.KM_500, Units.KM_500))));
    }

    @Test
    public void shouldNotOutputAnything() throws Exception {
        GridReferenceBuilder builder = new GridReferenceBuilder();

        instance.process(SOME_POINT, builder);

        assertThat(builder.toString(), is(EMPTY));
    }
}