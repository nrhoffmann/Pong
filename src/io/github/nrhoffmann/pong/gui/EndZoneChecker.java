package io.github.nrhoffmann.pong.gui;

import java.awt.*;

class EndZoneChecker {
    enum Result {RIGHT, LEFT, NONE}

    private static int END_ZONE_DEPTH = 20;

    private Rectangle leftEndZone;
    private Rectangle rightEndZone;

    EndZoneChecker(GameTable gameTable) {
        leftEndZone = computeLeftEndZone(gameTable);
        rightEndZone = computeRightEndZone(gameTable);
    }

    private Rectangle computeLeftEndZone(GameTable gameTable) {
        Dimension size = computeSize(gameTable);
        Point nwCorner = new Point(0, 0);

        return new Rectangle(nwCorner, size);
    }

    private Rectangle computeRightEndZone(GameTable gameTable) {
        Dimension size = computeSize(gameTable);
        Point nwCorner = new Point(gameTable.getPreferredSize().width, 0);
        nwCorner.translate(-END_ZONE_DEPTH, 0);

        return new Rectangle(nwCorner, size);
    }

    private Dimension computeSize(GameTable gameTable) {
        return new Dimension(END_ZONE_DEPTH, gameTable.getPreferredSize().height);
    }

    public Result check(Ball ball) {
//        Rectangle ballBoundaries  = ball.getLocation();
//
//        if (leftEndZone.intersects(ballBoundaries))
//            return Result.LEFT;
//
//        if (rightEndZone.intersects(ballBoundaries))
//            return Result.RIGHT;

        return Result.NONE;
    }
}
