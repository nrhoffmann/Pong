package io.github.nrhoffmann.pong.gui;

import io.github.nrhoffmann.pong.control.PaddleController;
import io.github.nrhoffmann.pong.physics.Vector;

import java.awt.*;

public class Paddle implements GameObject {

    final Side SIDE;
    private PaddleController input;
    private Color color;
    private Point location;
    private Vector vector;
    private int height;
    private int width;
    private int arc;
    private int maxY;
    private int minY;

    Paddle(Side side, PaddleController controller) {
        SIDE = side;

        input = controller;
        height = 200;
        width = 50;
        arc = 25;
        color = new Color(0, 0, 0);

        int padding = (int) (width * 0.75);
        location = new Point(padding + (side == Side.LEFT ? 0 : ((GamePane.WIDTH - (padding * 2) - width))), GamePane.GAME_TABLE_HEIGHT / 2 + height / 2);

        vector = new Vector(0, 0);

        maxY = GamePane.GAME_TABLE_HEIGHT - height;
        minY = 0;
    }

    @Override
    public void tick() {

    }

    @Override
    public void paint(Graphics2D g2) {
        g2.setColor(color);
        g2.fillRoundRect(location.x, location.y, width, height, arc, arc);
    }
}
