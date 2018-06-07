package me.suzdalnitsky.weather.presentation;

import android.support.annotation.StringRes;

import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import me.suzdalnitsky.weather.R;
import me.suzdalnitsky.weather.data.location.LocationIsNotAccurateException;
import me.suzdalnitsky.weather.data.location.LocationNotAvailableException;
import me.suzdalnitsky.weather.data.location.LocationNotPermittedException;
import me.suzdalnitsky.weather.data.network.client.HttpRequestException;
import me.suzdalnitsky.weather.presentation.main.MainView;
import me.suzdalnitsky.weather.ui.common.CommandProcessor;

public class MainErrorHandler {

    private CommandProcessor<MainView> commandProcessor;

    public MainErrorHandler(CommandProcessor<MainView> commandProcessor) {
        this.commandProcessor = commandProcessor;
    }

    public void onError(Throwable throwable) {
        if (throwable instanceof LocationNotPermittedException) {
            showError(R.string.snack_location_is_not_permitted_title);
        }
        if (throwable instanceof LocationNotAvailableException) {
            showError(R.string.snack_location_is_not_available_title);
        }
        if (throwable instanceof LocationIsNotAccurateException) {
            showError(R.string.snack_location_is_not_accurate_title);
        }
        if (throwable instanceof HttpRequestException) {
            int status = ((HttpRequestException) throwable).getStatusCode();

            if (status == HttpURLConnection.HTTP_NOT_FOUND) {
                showError(R.string.snack_city_not_found_title, R.string.snack_retry_action);
            }
        }
        if (throwable instanceof SocketTimeoutException || throwable instanceof UnknownHostException) {
            showError(R.string.snack_connection_is_not_available_title, R.string.snack_retry_action);
        }
    }

    private void showError(@StringRes int text) {
        showError(text, R.string.snack_enter_city);
    }

    private void showError(@StringRes int messageText, @StringRes int actionText) {
        commandProcessor.addCommand(
                MainView.showErrorKey,
                (view) -> view.showErrorMessage(
                        messageText,
                        actionText
                ),
                true
        );
        commandProcessor.addCommand(
                MainView.showNoDataAvailableKey,
                MainView::showNoDataAvailable
        );
    }
}