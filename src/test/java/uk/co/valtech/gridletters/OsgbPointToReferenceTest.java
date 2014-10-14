package uk.co.valtech.gridletters;

import org.junit.Before;
import org.junit.Test;
import uk.co.valtech.gridletters.domain.OsgbPoint;
import uk.co.valtech.gridletters.domain.Scale;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class OsgbPointToReferenceTest {

    private OsgbPointToReference instance;

    @Before
    public void setUp() throws Exception {
        instance = new OsgbPointToReference();
    }


    @Test
    public void firstLetterOfInterestingPoints() {
        String[] rows = {
         // X: -10        -5         0         5        10        15
                "A _ _ _ _ B _ _ _ _ C _ _ _ _ D _ _ _ _ E _ _ _ _ A ", // Y coord: 15
                "_ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ ",
                "_ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ ",
                "_ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ ",
                "_ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ ",
                "F _ _ _ _ G _ _ _ _ H _ _ _ _ J _ _ _ _ K _ _ _ _ F ", // Y coord: 10
                "_ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ ",
                "_ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ ",
                "_ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ ",
                "_ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ ",
                "L _ _ _ _ M _ _ _ _ N _ _ _ _ O _ _ _ _ P _ _ _ _ L ", // Y coord: 5
                "_ _ _ _ _ _ _ _ _ _ S _ _ _ S _ _ _ _ _ _ _ _ _ _ _ ",
                "_ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ ",
                "_ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ ",
                "_ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ ",
                "Q _ _ _ _ R _ _ _ _ S _ _ _ S T _ _ _ _ U _ _ _ _ Q ", // Y coord: 0
                "_ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ ",
                "_ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ ",
                "_ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ ",
                "_ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ ",
                "V _ _ _ _ W _ _ _ _ X _ _ _ _ Y _ _ _ _ Z _ _ _ _ V ", // Y coord: -5
                "_ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ ",
                "_ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ ",
                "_ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ ",
                "_ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ ",
                "A _ _ _ _ B _ _ _ _ C _ _ _ _ D _ _ _ _ E _ _ _ _ A ", // Y coord: -10
         // X: -10        -5         0         5        10        15
        };
        int yZero = 15;
        int xZero = 10;

        int letterNumber = 0;
        checkTestMatrix(rows, yZero, xZero, letterNumber);
    }
    @Test
    public void secondLetterInterestingPoints_1() {
        String[] rows = {
            //X: 0 1 2 3 4 5
                "Z V W X Y Z V ", // 5
                "E A B C D E A ", // 4
                "K F G H J K F ", // 3
                "P L M N O P L ", // 2
                "U Q R S T U Q ", // 1
                "Z V W X Y Z V ", // 0
                "E A B C D E A ", // -1
        };
        int yZero = 5;
        int xZero = 1;

        int letterNumber = 1;
        checkTestMatrix(rows, yZero, xZero, letterNumber);
    }

    @Test
    public void weTranslateAPointByRunningItThroughASeriesOfSteps() throws Exception {
        OsgbPointToReference engine = new OsgbPointToReference(
                (OsgbPoint currentPoint, StringBuilder sb) ->  {
                    sb.append("x");
                    return currentPoint;
                },
                (OsgbPoint currentPoint, StringBuilder sb) ->  {
                    sb.append(currentPoint.getX()+""+currentPoint.getY());
                    return null;
                });


        OsgbPoint samplePoint = new OsgbPoint(1, 2);
        assertThat(engine.convert(samplePoint), is(equalTo("x12")));
    }

    //~~~~~~~ Testing utils

    private static final int TEST_GRID_X_SPACING = 2;

    private void checkTestMatrix(String[] rows, int yZero, int xZero, int letterNumber) {
        for (int i = 0; i < rows.length; i++) {
            String row = rows[i];
            int yCoord = yZero - i;

            for (int j = 0; j < row.length(); j+= TEST_GRID_X_SPACING) {
                int xCoord = - xZero + (j/2);
                char expectedChar = row.charAt(j);

                if (expectedChar != '_') {
                    int eastings = xCoord * Scale.KM_100;
                    int northings = yCoord * Scale.KM_100;

                    assertThat("First grid letter of: "+eastings+", "+northings+" failed",
                            instance.convert(eastings, northings).charAt(letterNumber), is(expectedChar));
                }
            }
        }
    }
}