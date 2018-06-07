package me.suzdalnitsky.weather.ui.common;

import java.util.HashMap;
import java.util.Map;

public class CommandProcessor<View extends MvpView> {

    private Map<String, ViewCommand<View>> normalCommands = new HashMap<>();
    private Map<String, ViewCommand<View>> executeOnceCommands = new HashMap<>();

    private View view;
    private Boolean isViewAttached = false;

    void attachView(View view) {
        if (!isViewAttached) {
            this.view = view;
            reapplyCommands();
        }
        
        isViewAttached = true;
    }

    void detachView() {
        view = null;
        isViewAttached = false;
    }

    public void addCommand(String key, ViewCommand<View> command) {
        addCommand(key, command, false);
    }

    public void addCommand(String key, ViewCommand<View> command, Boolean shouldExecuteOnce) {
        if (shouldExecuteOnce) {
            if (isViewAttached) {
                command.apply(view);
            } else {
                executeOnceCommands.put(key, command);
            }
        } else {
            normalCommands.put(key, command);
            if (isViewAttached) command.apply(view);
        } 
    }

    private void reapplyCommands() {
        if (!executeOnceCommands.isEmpty()) {
            for (ViewCommand<View> command : executeOnceCommands.values()) {
                command.apply(view);
            }
            executeOnceCommands.clear();
        }

        if (!normalCommands.isEmpty()) {
            for (ViewCommand<View> command : normalCommands.values()) {
                command.apply(view);
            }   
        }
    }
}
