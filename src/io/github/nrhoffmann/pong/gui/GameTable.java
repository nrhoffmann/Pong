package io.github.nrhoffmann.pong.gui;

import io.github.nrhoffmann.pong.physics.Vector;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

class GameTable extends JPanel{
    private Ball ball = new Ball();
    private Timer timer;
    private static final Dimension SIZE = new Dimension(1080, 720);
    private final Dimension SIZE;

    GameTable(Dimension dimension) {
        SIZE = dimension;
        setBackground(Color.DARK_GRAY);

        timer = new Timer(5, e1 -> {
            Graphics g = getGraphics();
            ball.tick();
            paintComponent(g);
        });
        timer.start();

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

    public class Ball {
        private Vector vector;
        private Point location;
        private int size;
        private Color color;
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
            int deltax = (int) vector.getSpeedX();
            int deltay = (int) vector.getSpeedY();
            if (location.y + deltay < minY || location.y + deltay > maxY) {
                deltay = Math.max(deltay, minY - location.y); //todo this might be ugly, or it might simulate mass compression
                deltay = Math.min(deltay, maxY - location.y);
                vector.bounceY();
            }
            location.translate(deltax, deltay);
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
