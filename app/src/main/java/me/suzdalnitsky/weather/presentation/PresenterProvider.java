package me.suzdalnitsky.weather.presentation;

import java.util.NoSuchElementException;

import me.suzdalnitsky.weather.ui.common.MvpPresenter;
import me.suzdalnitsky.weather.ui.common.MvpView;
import me.suzdalnitsky.weather.util.Util;

public class PresenterProvider {

    public MvpPresenter providePresenter(Class<? extends MvpView> viewImplClass) {
        try {
            return Util.find(
                    PresenterBindings.bindings,
                    viewClass -> viewClass.isAssignableFrom(viewImplClass)
            )
                    .invoke();
        } catch (NoSuchElementException e) {
            throw new IllegalStateException("Couldn't find presenter constructor for " + viewImplClass + ". Check PresenterBindings.");
        }
    }
}
