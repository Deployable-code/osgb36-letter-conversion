package ro.ghionoiu.osgb.letters.util;

/**
* Created by julianghionoiu on 14/10/2014.
*/
public final class GridMath {

    private GridMath() {
        //Utility class
    }

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
