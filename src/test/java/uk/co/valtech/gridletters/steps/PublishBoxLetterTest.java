package uk.co.valtech.gridletters.steps;

import org.junit.Test;
import uk.co.valtech.gridletters.GridReferenceBuilder;
import uk.co.valtech.gridletters.domain.OsgbPoint;
import uk.co.valtech.gridletters.domain.Reference;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class PublishBoxLetterTest {


    @Test
    public void secondLetterInterestingPoints_1() {
        Reference reference = Reference.BOX_10m;
        PublishBoxLetter instance = new PublishBoxLetter(reference);

        String[] rows = {
                "ABCDE",
                "FGHJK",
                "LMNOP",
                "QRSTU",
                "VWXYZ",
        };
        int multiplicationFactor = 10;

        for (int i = 0; i < rows.length; i++) {
            String row = rows[i];
            int coordY = multiplicationFactor * (rows.length - i - 1);

            for (int j = 0; j < row.length(); j++) {
                char expectedChar = row.charAt(j);
                int coordX = multiplicationFactor * j;

                //Test point
                GridReferenceBuilder builder = new GridReferenceBuilder();
                OsgbPoint point = new OsgbPoint(coordX,coordY);
                instance.process(point, builder);
                assertThat(builder.toString(), is(equalTo(expectedChar+"")));
            }
        }
    }

}