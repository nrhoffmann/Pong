package io.github.nrhoffmann.pong.control;

import java.awt.event.MouseWheelListener;
import java.util.LinkedList;
import java.util.List;
import java.util.function.IntConsumer;

public class MouseWheelBridge implements Bridge<MouseWheelListener> {
    private final List<IntConsumer> CONSUMERS = new LinkedList<>();

    public void addConsumer(IntConsumer consumer) {
        CONSUMERS.add(consumer);
    }

    @Override
    public MouseWheelListener listener() {
        return event -> {
            for (IntConsumer consumer : CONSUMERS) {
                consumer.accept(event.getUnitsToScroll());
            }
        };
    }
}
