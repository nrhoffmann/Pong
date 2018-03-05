package io.github.nrhoffmann.pong.gui;

import io.github.nrhoffmann.pong.control.Controller;
import io.github.nrhoffmann.pong.physics.Vector;

import java.awt.*;

public class Paddle implements GameObject {

    final Side SIDE;

    private static final int HEIGHT = 150;
    private static final int WIDTH = 20;
    private static final int MAX_Y = GamePane.GAME_TABLE_HEIGHT - HEIGHT;
    private static final int MIN_Y = 0;

    private Controller input;
    private static final Color color = Color.BLACK;
    private Point location;
    private Point oldLocation;
    private Vector vector = new Vector();

    Paddle(Side side, Controller controller) {
        SIDE = side;
        input = controller;

        int padding = (int) (WIDTH * 0.75);
        location = new Point(padding + (side == Side.LEFT ? 0 : ((GamePane.WIDTH - (padding * 2) - WIDTH))), GamePane.GAME_TABLE_HEIGHT / 2 - HEIGHT / 2);

    }

    public Point getLocation() {
        return location;
    }

    public Rectangle getRectangle() {
        return new Rectangle(location.x, location.y, WIDTH, HEIGHT);
    }

    @Override
    public void tick() {
        oldLocation = new Point(location);

        int newY = location.y + input.nextInput();

        if (newY < MIN_Y) {
            newY = MIN_Y;
        }
        if (newY > MAX_Y) {
            newY = MAX_Y;
        }

        location.y = newY;
    }

    @Override
    public Rectangle changedMask() {
        return new Rectangle(oldLocation.x, oldLocation.y, WIDTH, HEIGHT).union(getRectangle());
    }

    @Override
    public void paint(Graphics2D g2) {
        g2.setColor(color);
        g2.fillRect(location.x, location.y, WIDTH, HEIGHT);
    }
}
