package ro.ghionoiu.osgb.letters.domain;

/**
* Created by julianghionoiu on 14/10/2014.
*/
public final class Units {
    public static final int M_1 = 1;
    public static final int M_10 = 10;
    public static final int M_100 = 10*M_10;
    public static final int KM_1 = 10*M_100;
    public static final int KM_10 = 10*KM_1;
    public static final int KM_100 = 10*KM_10;
    public static final int KM_500 = 5*KM_100;
    public static final int KM_2500 = 5*KM_500;

    private Units() {
        //Utility class
    }


}
