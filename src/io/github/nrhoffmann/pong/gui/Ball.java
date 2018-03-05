package io.github.nrhoffmann.pong.gui;

import io.github.nrhoffmann.pong.geometry.Line;
import io.github.nrhoffmann.pong.physics.Vector;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;

public class Ball implements GameObject {
    private static final Color COLOR = new Color(128, 128, 128);
    private static final int SIZE = 50;
    private static final int MIN_Y = 0;
    private static final int MAX_Y = GamePane.GAME_TABLE_HEIGHT - SIZE;
    private static final int MIN_X = 0 - SIZE;
    private static final int MAX_X = SIZE + GamePane.WIDTH;
    private final ScoreBoard SCORE_BOARD;

    private static Rectangle defaultLocation = new Rectangle(GamePane.WIDTH / 2 - SIZE / 2, GamePane.GAME_TABLE_HEIGHT / 2 - SIZE / 2, SIZE, SIZE);

    private Rectangle drawLocation;
    private Rectangle oldLocation;
    private Vector vector;
    private List<Paddle> potentiallyCollidablePaddles = new LinkedList<>();
    //private int checkCollisionsPastHere;

    Ball(ScoreBoard scoreBoard) {
        SCORE_BOARD = scoreBoard;
        reset();
    }

    public Vector getVector() {
        return vector;
    }

    public Rectangle getDrawLocation() {
        return drawLocation;
    }

    @Override
    public void tick() {
        int newX = (int) (drawLocation.x + vector.getSpeedX());
        int newY = (int) (drawLocation.y + vector.getSpeedY());

        if (newY < MIN_Y) { // ^^^N
            Line line = new Line(drawLocation.getLocation(), new Point(newX, newY));
            newX = (int) line.solveForX(MIN_Y);
            newY = MIN_Y;
            vector.bounceY();
        } else if (newY > MAX_Y) {
            Line line = new Line(drawLocation.getLocation(), new Point(newX, newY));
            newX = (int) Math.ceil(line.solveForX(MAX_Y));
            newY = MAX_Y;
            vector.bounceY();
        }

        for (Paddle paddle : potentiallyCollidablePaddles) {
            if (new Rectangle(newX, newY, SIZE, SIZE).intersects(paddle.getRectangle())) {
                Line line = new Line(drawLocation.getLocation(), new Point(newX, newY));
                if (paddle.SIDE == Side.LEFT) {
                    newX = (int) paddle.getRectangle().getMaxX();
                    newY = (int) line.solveForY(newX);
                } else {
                    newX = paddle.getRectangle().x - SIZE;
                    newY = (int) line.solveForY(newX);
                }
                vector.bounceX(); //todo bugfix - hit bottom/top of paddle bounces X, should Y
                break; //todo cleanup
            }
        }

        oldLocation = new Rectangle(drawLocation);
        drawLocation.setLocation(newX, newY);

        if(drawLocation.x < MIN_X){
            SCORE_BOARD.HUMAN.increment();
            reset();
        }
        if(drawLocation.x > MAX_X){
            SCORE_BOARD.COMPUTER.increment();
            reset();
        }
    }

    private void reset() {
        drawLocation = new Rectangle(defaultLocation);
        vector = new Vector();
    }

    @Override
    public Rectangle changedMask() {
        return oldLocation.union(drawLocation);
    }

    @Override
    public void paint(Graphics2D g2) {
        g2.setColor(COLOR);
        g2.fillOval(drawLocation.x, drawLocation.y, SIZE, SIZE);
    }

    public void setPotentiallyCollidablePaddles(List<Paddle> potentiallyCollidablePaddles) {
        this.potentiallyCollidablePaddles = potentiallyCollidablePaddles;
        //if (potentiallyCollidablePaddles.get(0).SIDE == Side.LEFT) {

        //}
    }
}
