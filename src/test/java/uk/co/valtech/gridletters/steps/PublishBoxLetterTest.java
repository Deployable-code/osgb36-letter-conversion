package uk.co.valtech.gridletters.steps;

import org.junit.Test;
import uk.co.valtech.gridletters.GridReferenceBuilder;
import uk.co.valtech.gridletters.domain.OsgbPoint;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class PublishBoxLetterTest {


    @Test
    public void secondLetterInterestingPoints_1() {
        int scale = 2;
        PublishBoxLetter instance = new PublishBoxLetter(scale);

        String[] rows = {
            //X: 0 2 4 6 8
                "          ",
                "A B C D E ", // 8
                "          ",
                "F G H J K ", // 6
                "          ",
                "L M N O P ", // 4
                "          ",
                "Q R S T U ", // 2
                "          ",
                "V W X Y Z ", // 0
        };

        checkEntireMatrix(instance, rows, scale);
    }

    private void checkEntireMatrix(PublishBoxLetter instance, String[] rows, int gridSpacing) {
        for (int i = 1; i < rows.length; i+= gridSpacing) {
            String row = rows[i];
            int yCoord = rows.length -i;

            for (int j = 0; j < row.length(); j+= gridSpacing) {
                int xCoord = j;
                char expectedChar = row.charAt(j);

                //Test point
                GridReferenceBuilder builder = new GridReferenceBuilder();
                OsgbPoint point = new OsgbPoint(xCoord,yCoord);
                instance.process(point, builder);
                assertThat(builder.toString(), is(equalTo(expectedChar+"")));
            }
        }
    }
}