package geo;

import java.awt.*;

public class Angle {
    private Point a;
    private Point b;
    private Point vertex;

    private Angle(Point a, Point vertex, Point b) {

        this.a = a;
        this.vertex = vertex;
        this.b = b;
    }

    public Point getPointA() {
        return a;
    }

    public Point getRelativePointA() {
        Point a = getPointA();
        a.translate(-vertex.x, -vertex.y);

        return a;
    }

    public Point getPointB() {
        return b;
    }

    public Point getRelativePointB() {
        Point b = getPointB();
        b.translate(-vertex.x, -vertex.y);

        return b;
    }

    public Point getVertex() {
        return vertex;
    }

    public static Angle of(Point a, Point vertex, Point b) {
        return new Angle(a, vertex, b);
    }
}