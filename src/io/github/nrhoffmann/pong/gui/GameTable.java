package io.github.nrhoffmann.pong.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

class GameTable extends JPanel{
    private Ball ball;

    GameTable() {
        setBackground(Color.DARK_GRAY);
        ball = new Ball();

        new Timer(5, e1 -> {
            Graphics g = getGraphics();
            ball.tick();
            paintComponent(g);
        }).start();

        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                System.out.println(e.getPoint());
            }
        });
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
        return ball;
    }

    public class Ball {
        private Color color;
        private Point location;
        private Vector vector;
        private int size;
        private int maxY;
        private int minY;

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

        void tick() {
            int deltaX = (int) vector.getSpeedX();
            int deltaY = (int) vector.getSpeedY();
            if (location.y + deltaY < minY || location.y + deltaY > maxY) {
                deltaY = Math.max(deltaY, minY - location.y); //todo this might be ugly, or it might simulate mass compression
                deltaY = Math.min(deltaY, maxY - location.y);
                vector.bounceY();
            }
            location.translate(deltaX, deltaY);
        }

        Ball() {
            size = 500;
            color = new Color(128, 128, 128);
            location = new Point(GameTable.this.SIZE.width / 2 - size / 2, GameTable.this.SIZE.height / 2);
            vector = new Vector();

            maxY = GameTable.this.SIZE.height - size;
            minY = 0;
        }
    }
}
