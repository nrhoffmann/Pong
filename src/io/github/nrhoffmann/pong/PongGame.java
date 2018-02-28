package io.github.nrhoffmann.pong;

import io.github.nrhoffmann.pong.gui.Pong;

import javax.swing.*;

public class PongGame {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(Pong::new);
    }
}
