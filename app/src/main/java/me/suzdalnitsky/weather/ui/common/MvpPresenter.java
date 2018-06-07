package me.suzdalnitsky.weather.ui.common;

import android.support.annotation.CallSuper;

public abstract class MvpPresenter<View extends MvpView> {

    private Boolean isFirstLaunch = true;
    protected CommandProcessor<View> commandProcessor = new CommandProcessor<>();

    protected void onFirstViewAttach() {
    }

    @CallSuper
    protected void attachView(View view) {
        commandProcessor.attachView(view);
        
        if (isFirstLaunch) {
            isFirstLaunch = false;
            onFirstViewAttach();
        }
    }

    @CallSuper
    protected void detachView() {
        commandProcessor.detachView();
    }

    protected void onDestroy() {
    }

}
