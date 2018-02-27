package gui;

import javax.swing.*;

abstract class Checker extends JPanel {
    final int WIDTH = 20;

    boolean check(Ball ball) {
        return getBounds().contains(ball.getLocation());
    }
}
