package geo;

import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class AngleTest {

    @Test
    void getRelativePointA() {
        Angle angle = Angle.of(new Point(0, 2), new Point(0, 1), new Point(1, 1));

        assertEquals(new Point(0, 1), angle.getRelativePointA());

    }

    @Test
    void getRelativePointB() {
        Angle angle = Angle.of(new Point(0, 2), new Point(0, 1), new Point(1, 1));

        assertEquals(new Point(1, 0), angle.getRelativePointB());
    }
}