package uk.co.valtech.gridletters;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import uk.co.valtech.gridletters.domain.Scale;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class OsgbPointToReferenceTest {
    private OsgbPointToReference instance;

    @Before
    public void setUp() throws Exception {
        instance = new OsgbPointToReference();
    }

    //~~~~~~~~~ Only 100Km boxes

    @Test
    public void originShouldBeTranslatedCorrectly() throws Exception {
        int eastings = 0 * Scale.KM_100;
        int northings = 0 * Scale.KM_100;
        String expectedGrid = "SV";

        assertThat("Grid reference of: "+eastings+", "+northings+" failed",
                instance.convert(eastings, northings), is(expectedGrid));
    }

    @Test
    public void positivePointFarFromOrigin_100k() throws Exception {
        int eastings = 2 * Scale.KM_100 + Scale.KM_2500;
        int northings = 9 * Scale.KM_100;
        String expectedGrid = "NC";

        assertThat("Grid reference of: "+eastings+", "+northings+" failed",
                instance.convert(eastings, northings), is(expectedGrid));
    }

    @Test
    public void negativePoint_100k() throws Exception {
        int eastings = -1 * Scale.KM_100;
        int northings = -1 * Scale.KM_100;
        String expectedGrid = "WE";

        assertThat("Grid reference of: "+eastings+", "+northings+" failed",
                instance.convert(eastings, northings), is(expectedGrid));
    }

    //~~~~~~~~~ Only 10Km boxes

    @Ignore
    @Test
    public void positivePointCloseToOrigin_10k() throws Exception {
        int eastings = 0 * Scale.KM_100 + 1;
        int northings = 0 * Scale.KM_100 + 2;
        String expectedGrid = "SV12";

        assertThat("Grid reference of: "+eastings+", "+northings+" failed",
                instance.convert(eastings, northings), is(expectedGrid));
    }

    @Ignore
    @Test
    public void negativePoint_10k() throws Exception {
        int eastings = -1 * Scale.KM_100 - 2;
        int northings = -1 * Scale.KM_100 - 3;
        String expectedGrid = "WE -2 -3";

        assertThat("Grid reference of: "+eastings+", "+northings+" failed",
                instance.convert(eastings, northings), is(expectedGrid));
    }
}