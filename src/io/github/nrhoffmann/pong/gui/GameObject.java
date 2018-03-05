package io.github.nrhoffmann.pong.gui;

import java.awt.*;

interface GameObject {
    void tick();
    Rectangle changedMask();
    void paint(Graphics2D g2);
}