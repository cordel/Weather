package me.suzdalnitsky.weather.ui.common;

import java.util.HashMap;
import java.util.Map;

import me.suzdalnitsky.weather.presentation.PresenterProvider;

public class PresenterStore {

    private static volatile PresenterStore instance;
    private static final Object lock = new Object();

    private final Map<Class<? extends MvpView>, MvpPresenter> presenters = new HashMap<>();
    private final PresenterProvider presenterProvider = new PresenterProvider();

    static PresenterStore getInstance() {
        if (instance == null) {
            synchronized (lock) {
                if (instance == null) {
                    instance = new PresenterStore();
                }
            }
        }
        return instance;
    }

    MvpPresenter getPresenter(Class<? extends MvpView> viewImplClass) {
        if (presenters.get(viewImplClass) == null) {
            presenters.put(viewImplClass, presenterProvider.providePresenter(viewImplClass));
        }

        return presenters.get(viewImplClass);
    }

    void destroyPresenter(Class<? extends MvpView> viewClass) {
        presenters.remove(viewClass);
    }
}
