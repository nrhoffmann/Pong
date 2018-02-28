package io.github.nrhoffmann.pong.physics;

import java.awt.*;

public class Vector {
    private double speedx;
    private double speedy;

    public double getSpeedx() {
        return speedx;
    }

    public void setSpeedx(double speedx) {
        this.speedx = speedx;
    }

    public double getSpeedy() {
        return speedy;
    }

    public void setSpeedy(double speedy) {
        this.speedy = speedy;
    }

    public void bounceX() {
        speedx = 0 - speedx;
    }

    public void bounceY() {
        speedy = 0 - speedy;
    }

    public Point delta() {
        return new Point((int) speedx, (int) speedy);
    }

    public Vector(){
        speedx = 0;
        speedy = 4;
    }
}
