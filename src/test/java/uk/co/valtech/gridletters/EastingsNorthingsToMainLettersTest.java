package uk.co.valtech.gridletters;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class EastingsNorthingsToMainLettersTest {


    private static final int KM_100 = 100000;

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

        for (int i = 0; i < rows.length; i++) {
            String row = rows[i];
            int yCoord = 15 - i;

            for (int j = 0; j < row.length(); j+=2) {
                int xCoord = -10 + (j/2);
                char expectedChar = row.charAt(j);

                if (expectedChar != '_') {
                    int eastings = xCoord * KM_100;
                    int northings = yCoord * KM_100;

                    assertThat("First grid letter of: "+eastings+", "+northings+" failed",
                            getGridReference(eastings, northings).charAt(0), is(expectedChar));
                }
            }
        }
    }

    @Test
    public void secondLetterInterestingPoints_1() {
        String[] rows = {
            //X: 0 1 2 3 4
                "A B C D E ", // 4
                "F G H J K ", // 3
                "L M N O P ", // 2
                "Q R S T U ", // 1
                "V W X Y Z ", // 0
        };

        for (int i = 0; i < rows.length; i++) {
            String row = rows[i];
            int yCoord = 4 - i;

            for (int j = 0; j < row.length(); j+= 2) {
                int xCoord = j/2;
                char expectedChar = row.charAt(j);

                if (expectedChar != '_') {
                    int eastings = xCoord * KM_100;
                    int northings = yCoord * KM_100;

                    assertThat("First grid letter of: "+eastings+", "+northings+" failed",
                            getGridReference(eastings, northings).charAt(1), is(expectedChar));
                }
            }
        }
    }


    //~~~~~~ Code

    private static final char[][] LETTER_TABLE = {
    //mod X:   3    4    0    1    2
            { 'A', 'B', 'C', 'D', 'E' }, // Y: 3
            { 'F', 'G', 'H', 'J', 'K' }, // Y: 2
            { 'L', 'M', 'N', 'O', 'P' }, // Y: 1
            { 'Q', 'R', 'S', 'T', 'U' }, // Y: 0
            { 'V', 'W', 'X', 'Y', 'Z' }  // Y: 4
    //mod X:   3    4    0    1    2
    };

    //The fake zero coordinates counting from bottom left
    private static final int FAKE_ZERO_X = 2;
    private static final int FAKE_ZERO_Y = 1;


    private String getGridReference(int eastings, int northings) {
        int xCoord = eastings/KM_100;
        int yCoord = northings/KM_100;


        StringBuilder sb = new StringBuilder();
        sb.append(getFirstLetter(indexOf(xCoord), indexOf(yCoord)));

        if ((xCoord >= 0 && xCoord < 5) &&
           (yCoord >= 0 && yCoord < 5)) {

            int adjustedY = 4 - yCoord;
            int adjustedX = xCoord;
            sb.append(LETTER_TABLE[adjustedY][adjustedX]);
        }

        return sb.toString();
    }

    private char getFirstLetter(int indexOfEasting, int indexOfNorthing) {
        int reverseCountNorthing = 4 - indexOfNorthing;
        int adjustedY = fiveMod( - FAKE_ZERO_Y + reverseCountNorthing);
        int adjustedX = fiveMod(FAKE_ZERO_X + indexOfEasting);

        return LETTER_TABLE[adjustedY][adjustedX];
    }

    private int indexOf(double coordinateOnGrid100) {
        int indexOnGrid500 = (int) Math.floor( coordinateOnGrid100 / 5);
        return fiveMod(indexOnGrid500);
    }


    private int fiveMod(int indexOfGrid500) {
        int mod = indexOfGrid500 % 5;
        if (mod < 0) {
            mod += 5;
        }
        return mod;
    }


}