package io.github.nrhoffmann.pong.gui;

import javax.swing.*;
import java.awt.*;

class GameTable extends JPanel{
    private Ball ball = new Ball();
    private Timer timer;

    GameTable(Dimension dimension) {
        setPreferredSize(dimension);
        setBackground(Color.DARK_GRAY);

        timer = new Timer(15, e1 -> {
            Graphics g = getGraphics();
            ball.step();
            paint(g);
        });
        timer.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(ball.getColor());
        g2.fillOval(ball.getLocation().x, ball.getLocation().y, ball.getSize(), ball.getSize());
    }

    public Ball getBall() {
        return new Ball();
    }
}
