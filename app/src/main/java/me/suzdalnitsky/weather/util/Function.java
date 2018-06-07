package me.suzdalnitsky.weather.util;

public interface Function<I, O> {
    O invoke(I i);
}
