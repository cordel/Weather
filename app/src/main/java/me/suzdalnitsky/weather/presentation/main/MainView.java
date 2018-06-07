package me.suzdalnitsky.weather.presentation.main;

import android.support.annotation.StringRes;

import me.suzdalnitsky.weather.domain.models.Weather;
import me.suzdalnitsky.weather.ui.common.MvpView;

public interface MainView extends MvpView {

    String showWeatherKey = "showWeatherKey";
    String showErrorKey = "showErrorKey";
    String showMessageKey = "showMessageKey";
    String showNoDataAvailableKey = "showNoDataAvailableKey";
    String setRefreshingKey = "setRefreshingKey";
    String setRefreshAvailableKey = "setRefreshAvailableKey";
    String showStatusKey = "showStatusKey";
    String verifyPermissionsKey = "verifyPermissionsKey"; 

    void showWeather(Weather weather);
    void showErrorMessage(@StringRes int message, @StringRes int actionText);
    void showMessage(@StringRes int message);
    void showNoDataAvailable();
    void setRefreshing(boolean isRefreshing);
    void setRefreshAvailable(boolean isAvailable);
    void showStatus(boolean isVisible);
    void verifyPermissions();
    
}
