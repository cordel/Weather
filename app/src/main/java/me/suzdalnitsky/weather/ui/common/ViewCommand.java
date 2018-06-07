package me.suzdalnitsky.weather.ui.common;

public interface ViewCommand<View> {
    void apply(View t);
}