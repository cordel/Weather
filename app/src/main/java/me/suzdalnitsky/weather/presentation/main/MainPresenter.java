package me.suzdalnitsky.weather.presentation.main;

import android.annotation.SuppressLint;
import android.os.AsyncTask;

import me.suzdalnitsky.weather.R;
import me.suzdalnitsky.weather.domain.interactors.MainInteractor;
import me.suzdalnitsky.weather.domain.models.Weather;
import me.suzdalnitsky.weather.presentation.MainErrorHandler;
import me.suzdalnitsky.weather.presentation.common.MvpTaskPresenter;
import me.suzdalnitsky.weather.presentation.models.Result;

@SuppressLint("StaticFieldLeak")
public class MainPresenter extends MvpTaskPresenter<MainView> {

    private MainInteractor interactor;
    private String cityName = null;
    private MainErrorHandler errorHandler = new MainErrorHandler(commandProcessor);

    public MainPresenter(MainInteractor interactor) {
        this.interactor = interactor;
    }

    @Override
    protected void onFirstViewAttach() {
        commandProcessor.addCommand(
                MainView.verifyPermissionsKey,
                (view) -> view.verifyPermissions(),
                true
        );
        new GetLocalWeatherTask().execute();
    }

    public void onManualLocation(String cityName) {
        new GetWeatherTask().execute(cityName);
    }

    public void refresh() {
        if (cityName == null) {
            new GetLocalWeatherTask().execute();
        } else {
            new GetWeatherTask().execute(cityName);
        }
    }

    private class GetWeatherTask extends AsyncTask<String, Void, Result<Weather>> {

        @Override
        protected void onPreExecute() {
            commandProcessor.addCommand(
                    MainView.setRefreshAvailableKey,
                    (view) -> view.setRefreshAvailable(false)
            );
            commandProcessor.addCommand(
                    MainView.setRefreshingKey,
                    (view) -> view.setRefreshing(true)
            );
        }

        @Override
        protected Result<Weather> doInBackground(String... strings) {
            cityName = strings[0];
            try {
                return Result.success(interactor.getWeather(strings[0]));
            } catch (Exception e) {
                return Result.error(e);
            }
        }

        @Override
        protected void onPostExecute(Result<Weather> result) {
            commandProcessor.addCommand(
                    MainView.setRefreshingKey,
                    (view) -> view.setRefreshing(false)
            );
            commandProcessor.addCommand(
                    MainView.setRefreshAvailableKey,
                    (view) -> view.setRefreshAvailable(true)
            );
            
            if (result.isError()) {
                errorHandler.onError(result.getError());
            } else {
                commandProcessor.addCommand(
                        MainView.showWeatherKey,
                        (view) -> view.showWeather(result.getData())
                );
                commandProcessor.addCommand(
                        MainView.showMessageKey,
                        (view) -> view.showMessage(R.string.snack_refresh_complete),
                        true
                );
                commandProcessor.addCommand(
                        MainView.showStatusKey,
                        (view) -> view.showStatus(false)
                );
            }
        }
    }
    
    private class GetLocalWeatherTask extends AsyncTask<Void, Void, Result<Weather>> {

        @Override
        protected void onPreExecute() {
            commandProcessor.addCommand(
                    MainView.setRefreshAvailableKey,
                    (view) -> view.setRefreshAvailable(false)
            );
            commandProcessor.addCommand(
                    MainView.setRefreshingKey,
                    (view) -> view.setRefreshing(true)
            );
        }

        @Override
        protected Result<Weather> doInBackground(Void... voids) {
            try {
                return Result.success(interactor.getLocalWeather());
            } catch (Exception e) {
                return Result.error(e);
            }
        }

        @Override
        protected void onPostExecute(Result<Weather> result) {
            commandProcessor.addCommand(
                    MainView.setRefreshingKey,
                    (view) -> view.setRefreshing(false)
            );
            commandProcessor.addCommand(
                    MainView.setRefreshAvailableKey,
                    (view) -> view.setRefreshAvailable(true)
            );
            
            if (result.isError()) {
                errorHandler.onError(result.getError());
            } else {
                commandProcessor.addCommand(
                        MainView.showWeatherKey,
                        (view) -> view.showWeather(result.getData())
                );
                commandProcessor.addCommand(
                        MainView.showMessageKey,
                        (view) -> view.showMessage(R.string.snack_refresh_complete),
                        true
                );
                commandProcessor.addCommand(
                        MainView.showStatusKey,
                        (view) -> view.showStatus(true)
                );
            }
        }
    }

}