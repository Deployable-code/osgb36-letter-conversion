package uk.co.valtech.gridletters;

import org.junit.Ignore;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class EastingsNorthingsToMainLettersTest {

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

    private static final int KM_100 = 100000;
    private static final int KM_500 = 5*KM_100;
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

    private static class Util {

        public static int fiveDiv(int val) {
            return (int) Math.floor( (double) val / 5);
        }

        public static  int fiveMod(int val) {
            int mod = val % 5;
            if (mod < 0) {
                mod += 5;
            }
            return mod;
        }
    }

    private static class LetterTable {
        private static final char[][] LETTER_TABLE = {
                //mod X:   3    4    0    1    2
                { 'A', 'B', 'C', 'D', 'E' }, // Y: 3
                { 'F', 'G', 'H', 'J', 'K' }, // Y: 2
                { 'L', 'M', 'N', 'O', 'P' }, // Y: 1
                { 'Q', 'R', 'S', 'T', 'U' }, // Y: 0
                { 'V', 'W', 'X', 'Y', 'Z' }  // Y: 4
                //mod X:   3    4    0    1    2
        };


        private final int xZero;
        private final int yZero;

        private LetterTable() {
            this(0,0);
        }

        private LetterTable(int xZero, int yZero) {
            this.xZero = xZero;
            this.yZero = yZero;
        }

        private char getLetterFor(int indexOfEastings, int indexOfNorthings) {
            int reverseCountNorthings = 4 - indexOfNorthings;
            int adjustedY = Util.fiveMod(-yZero + reverseCountNorthings);
            int adjustedX = Util.fiveMod(xZero + indexOfEastings);

            return LETTER_TABLE[adjustedY][adjustedX];
        }
    }

    //The fake zero coordinates counting from bottom left
    private static final int FAKE_ZERO_X = 2;
    private static final int FAKE_ZERO_Y = 1;


    private String getGridReference(int eastings, int northings) {
        StringBuilder sb = new StringBuilder();

        int xIndexOn500k = div(eastings, KM_500);
        int yIndexOn500k = div(northings, KM_500);
        LetterTable tableForOne = new LetterTable(FAKE_ZERO_X, FAKE_ZERO_Y);
        sb.append(tableForOne.getLetterFor(fiveMod(xIndexOn500k), fiveMod(yIndexOn500k)));

        int xIndexOn100k = div(eastings, KM_100);
        int yIndexOn100k = div(northings, KM_100);
        LetterTable tableForTwo = new LetterTable();
        sb.append(tableForTwo.getLetterFor(fiveMod(xIndexOn100k), fiveMod(yIndexOn100k)));

        return sb.toString();
    }

    private int div(int dividend, int denominator) {
        return (int) Math.floor( (double) dividend / denominator);
    }

    private int fiveMod(int val) {
        int mod = val % 5;
        if (mod < 0) {
            mod += 5;
        }
        return mod;
    }


}