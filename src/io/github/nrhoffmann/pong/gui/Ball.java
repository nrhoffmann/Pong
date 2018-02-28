package io.github.nrhoffmann.pong.gui;

import io.github.nrhoffmann.pong.physics.Vector;

import java.awt.*;

public class Ball {
    private Vector vector;
    private Point location;
    private int size;
    private Color color;
    private int maxY;
    private int minY;

    public void setColor(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public void setLocation(Point location) {
        this.location = location;
    }

    public Point getLocation() {
        return location;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getSize() {
        return size;
    }

    public void setVector(Vector vector) {
        this.vector = vector;
    }

    public Vector getVector() {
        return vector;
    }

    public void step() {
        int deltax = (int) vector.getSpeedx();
        int deltay = (int) vector.getSpeedy();
        if (location.y + deltay < minY || location.y + deltay > maxY) {
            deltay = Math.max(deltay, minY - location.y); //todo this might be ugly, or it might simulate mass compression
            deltay = Math.min(deltay, maxY - location.y);
            vector.bounceY();
        }
        location.translate(deltax, deltay);
    }

    Ball() {
        size = 50;
        color = new Color(128, 128, 128);
        location = new Point(GamePane.SIZE.width / 2, GamePane.SIZE.height / 2);
        vector = new Vector();

        maxY = GamePane.SIZE.height - size / 2;
        minY = size / 2;
    }
}
