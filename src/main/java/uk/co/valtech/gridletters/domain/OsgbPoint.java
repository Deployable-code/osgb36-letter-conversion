package uk.co.valtech.gridletters.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import uk.co.valtech.gridletters.util.GridMath;

import java.util.Objects;

/**
 * Immutable class
 */
public final class OsgbPoint {
    private final int x;
    private final int y;

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

    public OsgbPoint zoomInside(int scale) {
        int rx = GridMath.mod(this.x, scale);
        int ry = GridMath.mod(this.y, scale);
        return new OsgbPoint(rx, ry);
    }

    //~~~ Equals contract

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final OsgbPoint other = (OsgbPoint) obj;
        return Objects.equals(this.x, other.x) && Objects.equals(this.y, other.y);
    }

    //~~~~ Pretty string

    @Override
    public String toString() {
        return "OsgbPoint{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
