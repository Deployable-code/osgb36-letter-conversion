package uk.co.valtech.gridletters;

import org.apache.commons.lang3.ArrayUtils;
import org.junit.Ignore;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class MainTest {

    //~~~ Functional tests

    @Test
    public void ftestOrigin() throws Exception {
        int eastings = 0 * KM_100;
        int northings = 0 * KM_100;
        String expectedGrid = "SV";

        assertThat("Grid reference of: "+eastings+", "+northings+" failed",
                getGridReference(eastings, northings), is(expectedGrid));
    }

    @Test
    public void ftestFarPoint() throws Exception {
        int eastings = 2 * KM_100;
        int northings = 9 * KM_100;
        String expectedGrid = "NC";

        assertThat("Grid reference of: "+eastings+", "+northings+" failed",
                getGridReference(eastings, northings), is(expectedGrid));
    }

    @Test
    public void ftestNegativePoint() throws Exception {
        int eastings = -1 * KM_100;
        int northings = -1 * KM_100;
        String expectedGrid = "WE";

        assertThat("Grid reference of: "+eastings+", "+northings+" failed",
                getGridReference(eastings, northings), is(expectedGrid));
    }

    @Ignore
    @Test
    public void ftestSpecificPoint10km() throws Exception {
        int eastings = 0 * KM_100 + 1;
        int northings = 0 * KM_100 + 2;
        String expectedGrid = "SV12";

        assertThat("Grid reference of: "+eastings+", "+northings+" failed",
                getGridReference(eastings, northings), is(expectedGrid));
    }

    @Ignore
    @Test
    public void ftestNegativeSpecificPoint10Km() throws Exception {
        int eastings = -1 * KM_100 - 2;
        int northings = -1 * KM_100 - 3;
        String expectedGrid = "WE -2 -3";

        assertThat("Grid reference of: "+eastings+", "+northings+" failed",
                getGridReference(eastings, northings), is(expectedGrid));
    }

    //~~~~ Unit tests


    //Processing steps


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
                    int eastings = xCoord * KM_100;
                    int northings = yCoord * KM_100;

                    assertThat("First grid letter of: "+eastings+", "+northings+" failed",
                            getGridReference(eastings, northings).charAt(letterNumber), is(expectedChar));
                }
            }
        }
    }

    //~~~~~~ Code


    private static class GridMath {

        public static int div(int dividend, int denominator) {
            return (int) Math.floor( (double) dividend / denominator);
        }

        public static  int mod(int dividend, int denominator) {
            int mod = dividend % denominator;
            if (mod < 0) {
                mod += denominator;
            }
            return mod;
        }

    }
    private static class LetterTable {

        private static final char[][] LETTERS = {
                { 'A', 'B', 'C', 'D', 'E' },
                { 'F', 'G', 'H', 'J', 'K' },
                { 'L', 'M', 'N', 'O', 'P' },
                { 'Q', 'R', 'S', 'T', 'U' },
                { 'V', 'W', 'X', 'Y', 'Z' }
        };

        static {
            ArrayUtils.reverse(LETTERS);
        }

        private static char getLetterFor(OsgbPoint point, int scale) {
            int xIndex = GridMath.div(point.getX(), scale);
            int yIndex = GridMath.div(point.getY(), scale);
            return LETTERS[yIndex][xIndex];
        }
    }


    /**
     * Immutable class
     */
    private static class OsgbPoint {
        int x;
        int y;

        private OsgbPoint(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public OsgbPoint translateWith(OsgbPoint offset) {
            int tx = this.x + offset.getX();
            int ty = this.y + offset.getY();
            return new OsgbPoint(tx, ty);
        }

        public OsgbPoint scaleInside(int scale) {
            int rx = GridMath.mod(this.x, scale);
            int ry = GridMath.mod(this.y, scale);
            return new OsgbPoint(rx, ry);
        }
    }

    private static final int KM_10 = 10000;
    private static final int KM_100 = 10*KM_10;
    private static final int KM_500 = 5*KM_100;
    private static final int KM_2500 = 5*KM_500;

    private String getGridReference(int eastings, int northings) {
        OsgbPoint currentPoint = new OsgbPoint(eastings, northings);
        return new OsgbPointToReference().convert(currentPoint);
    }

    private static class OsgbPointToReference {
        private ProcessingStep[] steps;

        private OsgbPointToReference() {
            this(new TranslateToRealOrigin(),
                 new ScaleAndPublishBox500(),
                 new ScaleAndPublishBox100());
        }

        private OsgbPointToReference(ProcessingStep... steps) {
            this.steps = steps;
        }

        private String convert(OsgbPoint currentPoint) {
            StringBuilder sb = new StringBuilder();

            for (ProcessingStep action : steps) {
                currentPoint = action.process(currentPoint, sb);
            }

            return sb.toString();
        }

        private Class<? extends ProcessingStep> getStepAt(int index) {
            return steps[index].getClass();
        }
    }

    private interface ProcessingStep {
        OsgbPoint process(OsgbPoint currentPoint, StringBuilder sb);
    }


    private static class TranslateToRealOrigin implements ProcessingStep {
        @Override
        public OsgbPoint process(OsgbPoint currentPoint, StringBuilder sb) {
            OsgbPoint gridOriginOffset = new OsgbPoint(2*KM_500, KM_500);
            currentPoint = currentPoint.translateWith(gridOriginOffset);
            return currentPoint;
        }
    }


    private static class ScaleAndPublishBox500 implements ProcessingStep {
        @Override
        public OsgbPoint process(OsgbPoint currentPoint, StringBuilder sb) {
            currentPoint = currentPoint.scaleInside(KM_2500);
            sb.append(LetterTable.getLetterFor(currentPoint, KM_500));
            return currentPoint;
        }
    }

    private static class ScaleAndPublishBox100 implements ProcessingStep {
        @Override
        public OsgbPoint process(OsgbPoint currentPoint, StringBuilder sb) {
            currentPoint = currentPoint.scaleInside(KM_500);
            sb.append(LetterTable.getLetterFor(currentPoint, KM_100));
            return currentPoint;
        }
    }
}