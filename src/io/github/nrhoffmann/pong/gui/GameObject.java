package io.github.nrhoffmann.pong.gui;

import java.awt.*;

interface GameObject {
    void tick();
    void paint(Graphics2D g2);
}