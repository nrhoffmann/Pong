package io.github.nrhoffmann.pong.physics;

import java.awt.*;

public class Vector {
    private double theta;
    private int speed;

    public double getTheta() {
        return theta;
    }

    public void setTheta(double theta) {
        this.theta = theta;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void bounceX() {

    }

    public void bounceY() {

    }

    public Point delta() {
        double x = speed * Math.sin(theta);
        double y = speed * Math.cos(theta);

        return new Point((int) x, (int) y);
    }

    public Vector(){
        theta = 110;
        speed = 10;
    }
}
