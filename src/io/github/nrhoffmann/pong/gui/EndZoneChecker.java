package io.github.nrhoffmann.pong.gui;

import java.awt.*;

class EndZoneChecker {
    enum Result {RIGHT, LEFT, NONE}

    private static int END_ZONE_DEPTH = 20;

    private Rectangle leftEndZone;
    private Rectangle rightEndZone;

    EndZoneChecker(PlayingField playingField) {
        leftEndZone = computeLeftEndZone(playingField);
        rightEndZone = computeRightEndZone(playingField);
    }

    private Rectangle computeLeftEndZone(PlayingField playingField) {
        Dimension size = computeSize(playingField);
        Point nwCorner = new Point(0, 0);

        return new Rectangle(nwCorner, size);
    }

    private Rectangle computeRightEndZone(PlayingField playingField) {
        Dimension size = computeSize(playingField);
        Point nwCorner = new Point(playingField.getPreferredSize().width, 0);
        nwCorner.translate(-END_ZONE_DEPTH, 0);

        return new Rectangle(nwCorner, size);
    }

    private Dimension computeSize(PlayingField playingField) {
        return new Dimension(END_ZONE_DEPTH, playingField.getPreferredSize().height);
    }

    public Result check(Ball ball) {
        Point ballLocation = ball.getLocation();

        if (leftEndZone.contains(ballLocation))
            return Result.LEFT;

        if (rightEndZone.contains(ballLocation))
            return Result.RIGHT;

        return Result.NONE;
    }
}
