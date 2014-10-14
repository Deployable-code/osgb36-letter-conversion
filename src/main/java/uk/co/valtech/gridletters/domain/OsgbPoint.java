package uk.co.valtech.gridletters.domain;

/**
 * Immutable class
 */
public class OsgbPoint {
    private int x;
    private int y;

    public OsgbPoint(int x, int y) {
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
