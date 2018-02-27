package gui;

import javax.swing.*;
import java.awt.*;

class GamePanel extends JPanel {

    private final Paddle paddle;
    private ScoreBoard scoreBoard = new ScoreBoard();

    GamePanel() {
        super(new BorderLayout());

        setBackground(Color.DARK_GRAY);
        setPreferredSize(new Dimension(1080, 720));

        add(scoreBoard, BorderLayout.NORTH);


        PaddleConstraints LeftPaddleConstraints = new PaddleConstraints()
                .setMaxY(720)
                .setMinY(scoreBoard.getPreferredSize().height)
                .setX(50);

        paddle = new Paddle(LeftPaddleConstraints);

        addMouseWheelListener((event) -> {
            SwingUtilities.invokeLater(() -> {
                int steps = event.getWheelRotation();
                while (Math.abs(steps) != 0) {
                    if (steps > 0) {
                        paddle.moveDown();
                        --steps;
                    } else {
                        paddle.moveUp();
                        ++steps;
                    }
                    repaint();
                }
            });
        });
    }
}
