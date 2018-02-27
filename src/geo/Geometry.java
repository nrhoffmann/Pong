package geo;

import java.awt.*;

public class Geometry {

    private Geometry() {
    }

    public double degree(Angle angle) {
        Point a = angle.getRelativePointA();
        Point b = angle.getRelativePointB();

        double measureAngle = Math.toDegrees(Math.atan2(a.y - b.y, a.x - b.x));

        return measureAngle < 0 ?
                measureAngle + 360 :
                measureAngle;
    }
}
