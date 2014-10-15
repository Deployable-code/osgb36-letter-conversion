package uk.co.valtech.gridletters.domain;

import static uk.co.valtech.gridletters.domain.Units.*;

/**
* Created by julianghionoiu on 14/10/2014.
*/
public enum Boundary {
    BOX_2500km(KM_2500),
    BOX_500km(KM_500),
    BOX_100km(KM_100),
    BOX_10km(KM_10),
    BOX_1km(KM_1),
    BOX_100m(M_100),
    BOX_10m(M_10),
    BOX_1m(M_1),
    ;

    int size;

    Boundary(int size) {
        this.size = size;
    }

    public int getSize() {
        return size;
    }
}
