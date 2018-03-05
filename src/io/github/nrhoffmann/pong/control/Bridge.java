package io.github.nrhoffmann.pong.control;

import java.util.EventListener;

public interface Bridge<T extends EventListener> {
    T listener();
}
