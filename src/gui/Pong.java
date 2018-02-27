package gui;

import javax.swing.*;

public class Pong extends JFrame {

    public Pong() {
        super("Pong");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(new GamePanel());
        pack();
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }
}
