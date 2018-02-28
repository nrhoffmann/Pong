package io.github.nrhoffmann.pong.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

class GameTable extends JPanel{
    private Ball ball = new Ball();
    private Timer timer;
    public static final Dimension SIZE = new Dimension(1080, 720);

    GameTable() {
        setPreferredSize(SIZE);
        setBackground(Color.DARK_GRAY);

        timer = new Timer(5, e1 -> {
            Graphics g = getGraphics();
            ball.tick();
            paintComponent(g);
        });
        timer.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(ball.getColor());
        g2.fillOval(ball.getLocation().x, ball.getLocation().y, ball.getSize(), ball.getSize());
        Toolkit.getDefaultToolkit().sync();
    }

    public Ball getBall() {
        return new Ball();
    }
}
