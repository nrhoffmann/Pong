package io.github.nrhoffmann.pong.physics;

import java.awt.*;
import java.util.concurrent.ThreadLocalRandom;

public class Vector {
    private double speedX;
    private double speedY;

    private static ThreadLocalRandom random = ThreadLocalRandom.current();

    public double getSpeedX() {
        return speedX;
    }

    public double getSpeedY() {
        return speedY;
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
        do {
            speedX = random.nextInt(- 5, 6);
        } while (speedX == 0);

        do {
            speedY = random.nextInt(-5, 6);
        } while (speedY == 0);
    }

    public Vector(int speedX, int speedY){
        this.speedX = speedX;
        this.speedY = speedY;
    }
}
