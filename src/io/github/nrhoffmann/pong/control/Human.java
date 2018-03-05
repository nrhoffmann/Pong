package io.github.nrhoffmann.pong.control;

import java.util.LinkedList;
import java.util.Queue;
import java.util.function.IntConsumer;

public class Human implements Controller, IntConsumer {
    private final Queue<Integer> BUFFER = new LinkedList<>();

    @Override
    public int nextInput() {
        if (BUFFER.isEmpty())
            return 0;

        return BUFFER.remove();
    }

    @Override
    public void accept(int x) {
        BUFFER.add(x);
    }
}
