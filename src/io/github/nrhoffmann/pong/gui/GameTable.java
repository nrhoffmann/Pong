package io.github.nrhoffmann.pong.gui;

import io.github.nrhoffmann.pong.control.Human;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

class GameTable extends JPanel{
    private Ball ball;
    private ArrayList<Object> stuffOnTable = new ArrayList<>();

    GameTable() {
        setBackground(Color.DARK_GRAY);
        ball = new Ball();
        stuffOnTable.add(ball);
        stuffOnTable.add(new Paddle(0, new Human())); //todo side enum
        stuffOnTable.add(new Paddle(1, new Human())); //todo side enum or something

        new Timer(5, e1 -> {
            Graphics g = getGraphics();
            for(Object o : stuffOnTable){
                o.tick();
            }
            paintComponent(g);
        }).start();

        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                System.out.println(e.getPoint());
            }
        });
    }

    public Ball getBall() {
        return ball;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        for(Object thing : stuffOnTable){
            thing.paint(g2);
        }
        Toolkit.getDefaultToolkit().sync();
    }

    public ArrayList<Object> getStuffOnTable() {
        return stuffOnTable;
    }

    interface Object {
        void tick();
        void paint(Graphics2D g2);
    }

}
