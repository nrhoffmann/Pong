package gui;

import javax.swing.*;
import java.awt.*;

class Game extends JPanel {

    private static final Color BACKGROUND = Color.DARK_GRAY;
    private static final Dimension SIZE = new Dimension(1080, 720);

    private ScoreBoard scoreBoard = new ScoreBoard();
    private Field field = new Field();
    private Checker leftChecker = new LeftChecker();
    private Checker rightChecker = new RightChecker();

    Game() {
        super(new BorderLayout());

        add(scoreBoard, BorderLayout.NORTH);
        add(field, BorderLayout.CENTER);
        add(leftChecker, BorderLayout.WEST);
        add(rightChecker, BorderLayout.EAST);
    }

    private static class Field extends JPanel {
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
