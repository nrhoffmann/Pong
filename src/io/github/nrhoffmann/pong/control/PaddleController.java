package io.github.nrhoffmann.pong.control;

abstract public class PaddleController {
    private int input;

    synchronized int getInput() { //todo does this need to be syncronized
        int tmp = input;
        changeInput(tmp);
        return tmp;
    }

    synchronized void changeInput(int amount) {
        input += amount;
    }

}
