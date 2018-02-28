package io.github.nrhoffmann.pong.gui;

import javax.swing.*;
import java.awt.*;

class PlayingField extends JPanel{
    PlayingField(Dimension dimension) {
        setPreferredSize(dimension);
        setBackground(Color.DARK_GRAY);
    }
}
