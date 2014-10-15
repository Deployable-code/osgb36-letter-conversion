package uk.co.valtech.gridletters.util;

import org.junit.Test;

import static net.trajano.commons.testing.UtilityClassTestUtil.assertUtilityClassWellDefined;

public class GridMathTest {

    @Test
    public void shouldSatisfyContractForUtilityClass() throws Exception {
        assertUtilityClassWellDefined(GridMath.class);
    }
}