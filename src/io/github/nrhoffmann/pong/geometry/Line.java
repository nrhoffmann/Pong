package io.github.nrhoffmann.pong.geometry;

import java.awt.*;

public class Line {
    private double m;
    private double b;

    public Line(Point p1, Point p2){
        m = (p2.y - p1.y) / (p2.x - p1.x);
        b = p2.y - m * p2.x;
    }

    public double solveForY(int x){
        return m * x + b;
    }

    public double solveForX(int y){
        return (y - b) / m;
    }
}
