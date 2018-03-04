package io.github.nrhoffmann.pong.gui;

import io.github.nrhoffmann.pong.physics.Vector;

import java.awt.*;

public class Ball implements GameTable.Object {
    private Color color;
    private Point location;
    private Vector vector;
    private int size;
    private int maxY;
    private int minY;

    Ball() {
        size = 500;
        color = new Color(128, 128, 128);
        location = new Point(GamePane.WIDTH / 2 - size / 2, GamePane.GAME_TABLE_HEIGHT / 2);
        vector = new Vector(1, 0);

        maxY = GamePane.GAME_TABLE_HEIGHT - size;
        minY = 0;
    }

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

    @Override
    public void tick() {
        int deltaX = (int) vector.getSpeedX();
        int deltaY = (int) vector.getSpeedY();

        if (location.y + deltaY < minY || location.y + deltaY > maxY) {
            deltaY = Math.max(deltaY, minY - location.y); //simulates mass compression - could possibly smush oval too
            deltaY = Math.min(deltaY, maxY - location.y);
            vector.bounceY();
        }

        // for(GameTable.Object thing : ) //todo - check for collisions with each thing in parent gametable's list of stuff on table besides self

        location.translate(deltaX, deltaY);
    }

    @Override
    public void paint(Graphics2D g2) {
        g2.setColor(color);
        g2.fillOval(location.x , location.y, size, size);
    }

}
