package io.github.nrhoffmann.pong.physics;

import java.awt.*;

public class Vector {
    private double speedX;
    private double speedY;

    public double getSpeedX() {
        return speedX;
    }

    public void setSpeedX(double speedX) {
        this.speedX = speedX;
    }

    public double getSpeedY() {
        return speedY;
    }

    public void setSpeedY(double speedY) {
        this.speedY = speedY;
    }

    public void bounceX() {
        speedX = 0 - speedX;
    }

    public void bounceY() {
        speedY = 0 - speedY;
    }

    public Point delta() {
        return new Point((int) speedX, (int) speedY);
    }

    public Vector(){
        speedX = 0;
        speedY = 0;
    }

    public Vector(int speedX, int speedY){
        this.speedX = speedX;
        this.speedY = speedY;
    }
}
