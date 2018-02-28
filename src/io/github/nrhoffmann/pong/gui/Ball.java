package io.github.nrhoffmann.pong.gui;

import io.github.nrhoffmann.pong.gui.phy.Velocity;

import java.awt.*;

public class Ball {
    private Velocity velocity;
    private Point location;

    public Velocity getVelocity() {
        return velocity;
    }

    public void setVelocity(Velocity velocity) {
        this.velocity = velocity;
    }

    public Point getLocation() {
        return location;
    }

    public void setLocation(Point location) {
        this.location = location;
    }

    Ball() {
        Point delta = velocity.delta();
        location.translate(delta.x, delta.y);
    }
}
