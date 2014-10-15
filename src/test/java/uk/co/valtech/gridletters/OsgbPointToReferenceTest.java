package uk.co.valtech.gridletters;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import uk.co.valtech.gridletters.domain.Scale;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static uk.co.valtech.gridletters.domain.Scale.*;

public class OsgbPointToReferenceTest {
    private OsgbPointToReference instance;

    @Before
    public void setUp() throws Exception {
        instance = new OsgbPointToReference();
    }


    @Test
    public void originShouldBeTranslatedCorrectly() throws Exception {
        int eastings = 0;
        int northings = 0;
        String expectedGrid = "SV00";

        assertThat("Grid reference of: "+eastings+", "+northings+" failed",
                instance.convert(eastings, northings), is(expectedGrid));
    }

    @Test
    public void positivePointFarFromOrigin() throws Exception {
        int eastings  = 3 * KM_2500 + 5 * KM_100 + 9 * KM_10 + 1;
        int northings = 2 * KM_2500 + 2 * KM_100 + 2 * KM_10 + 2;
        String expectedGrid = "TL92";

        assertThat("Grid reference of: "+eastings+", "+northings+" failed",
                instance.convert(eastings, northings), is(expectedGrid));
    }
}