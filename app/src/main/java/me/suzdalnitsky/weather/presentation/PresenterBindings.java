package me.suzdalnitsky.weather.presentation;

import java.util.HashMap;
import java.util.Map;

import me.suzdalnitsky.weather.di.MainPresenterProvider;
import me.suzdalnitsky.weather.presentation.main.MainView;
import me.suzdalnitsky.weather.ui.common.MvpPresenter;
import me.suzdalnitsky.weather.ui.common.MvpView;
import me.suzdalnitsky.weather.util.NoArgFunction;

public class PresenterBindings {

    /**
     * Bind presenter constructors to your View interfaces.
     */
    final static Map<Class<? extends MvpView>, NoArgFunction<MvpPresenter<? extends MvpView>>> bindings = new HashMap<>();

    static {
        bindings.put(MainView.class, MainPresenterProvider::provide);
    }
}
