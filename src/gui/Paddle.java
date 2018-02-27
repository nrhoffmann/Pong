package gui;

import java.awt.*;

public class Paddle {
    private static final int WIDTH = 20, HEIGHT = 100, STEPS = 60;

    private final PaddleConstraints constraints;
    private int increment;
    private int currentY;

    Paddle(PaddleConstraints constraints) {
        this.constraints = constraints;
        int space = (constraints.getMaxY() - constraints.getMinY());

        currentY = space / 2 + constraints.getMinY();
        increment = space / STEPS;
    }
    
    public void paint(Graphics graphics) {
        graphics.setColor(Color.WHITE);
        graphics.fillRect(constraints.getX(), currentY, WIDTH, HEIGHT);
    }

    public void moveUp() {
        move(-1);
    }

    public void moveDown() {
        move(1);
    }

    private void move(int delta) {
        int oldY = currentY;

        currentY += increment * delta;

        if (! valid(currentY, constraints))
            currentY = oldY;
    }

    private static boolean valid(int currentY , PaddleConstraints constraints) {
        return currentY >= constraints.getMinY() &&
                currentY + HEIGHT <= constraints.getMaxY();
    }
}
