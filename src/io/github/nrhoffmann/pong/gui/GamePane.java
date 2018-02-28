package io.github.nrhoffmann.pong.gui;

import javax.swing.*;
import java.awt.*;

class GamePane extends JPanel {

    private static final Color BACKGROUND = Color.DARK_GRAY;
    public static final Dimension SIZE = new Dimension(1080, 720);

    private ScoreBoard scoreBoard = new ScoreBoard();
    private Field field = new Field();
    private Checker leftChecker = new LeftChecker();
    private Checker rightChecker = new RightChecker();

    private Ball ball = new Ball(); // todo maybe move this and associated methods to Field
    private Timer timer;

    GamePane() {
        super(new BorderLayout());

        add(scoreBoard, BorderLayout.NORTH);
        add(field, BorderLayout.CENTER);
        add(leftChecker, BorderLayout.WEST);
        add(rightChecker, BorderLayout.EAST);
        timer = new Timer(15, e1 -> {
            Graphics g = getGraphics();
            ball.step();
            paint(g);
        });
        timer.start();

    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(ball.getColor());
        g2.fillOval(ball.getLocation().x, ball.getLocation().y, ball.getSize(), ball.getSize());
    }

    private static class Field extends JPanel { //todo move to its own file?
        Field() {
            setPreferredSize(SIZE);
            setBackground(BACKGROUND);
        }
    }

    private static class LeftChecker extends Checker {
        LeftChecker() {
            setPreferredSize(new Dimension(WIDTH, SIZE.height));
            setBackground(BACKGROUND);
            setBounds(new Rectangle(new Point(0, 0), getPreferredSize()));
        }

        @Override
        boolean check(Ball ball) {
            return getBounds().contains(ball.getLocation());
        }
    }

    private static class RightChecker extends Checker {
        RightChecker() {
            setPreferredSize(new Dimension(WIDTH, SIZE.height));
            setBackground(BACKGROUND);
            setBounds(new Rectangle(new Point(SIZE.width - WIDTH, 0), getPreferredSize()));
        }

        @Override
        boolean check(Ball ball) {
            return getBounds().contains(ball.getLocation());
        }
    }
}
