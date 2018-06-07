package me.suzdalnitsky.weather.ui.main;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import java.util.List;

import me.suzdalnitsky.weather.R;
import me.suzdalnitsky.weather.domain.models.Weather;
import me.suzdalnitsky.weather.presentation.main.MainPresenter;
import me.suzdalnitsky.weather.presentation.main.MainView;
import me.suzdalnitsky.weather.ui.EnterCityDialogFragment;
import me.suzdalnitsky.weather.ui.common.MvpAppCompatActivity;
import me.suzdalnitsky.weather.util.Util;

public class MainActivity extends MvpAppCompatActivity implements MainView, EnterCityListener {

    private static final int PERMISSION_REQUEST_CODE = 4324;

    private ConstraintLayout root;
    private List<TextView> statsTextViews;
    private TextView city;
    private TextView condition;
    private TextView temp;
    private TextView windSpeed;
    private TextView pressure;
    private TextView humidity;
    private SwipeRefreshLayout swipeRefreshLayout;
    private TextView status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initToolbar();
        initViews();
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void showWeather(Weather weather) {
        city.setText(weather.getCityName());
        condition.setText(weather.getType());
        temp.setText(
                getString(R.string.template_temp_celsius, weather.getTempCelsius())
        );
        windSpeed.setText(
                getString(R.string.template_wind_speed, weather.getWindSpeed())
        );
        pressure.setText(
                getString(R.string.template_pressure, weather.getPressure())
        );
        humidity.setText(
                getString(R.string.template_humidity, weather.getHumidity())
        );

        for (TextView view : statsTextViews) {
            view.setTextColor(getResources().getColor(R.color.colorAccent));
        }
    }

    @Override
    public void showErrorMessage(int message, int actionText) {
        Snackbar.make(root, message, Snackbar.LENGTH_INDEFINITE)
                .setAction(actionText, v -> showDialog())
                .setActionTextColor(getResources().getColor(R.color.colorError))
                .show();
    }

    @Override
    public void showNoDataAvailable() {
        for (TextView view : statsTextViews) {
            view.setText(R.string.no_data);
            view.setTextColor(getResources().getColor(R.color.colorError));
        }
    }

    @Override
    public void setRefreshing(boolean isRefreshing) {
        swipeRefreshLayout.setRefreshing(isRefreshing);
    }

    @Override
    public void setRefreshAvailable(boolean isAvailable) {
        swipeRefreshLayout.setEnabled(isAvailable);
    }

    @Override
    public void showMessage(int message) {
        Snackbar.make(root, message, Snackbar.LENGTH_SHORT)
                .show();
    }

    @Override
    public void showStatus(boolean isVisible) {
        if (isVisible) {
            status.setVisibility(View.VISIBLE);
        } else {
            status.setVisibility(View.GONE);
        }
    }

    @Override
    public void verifyPermissions() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            return;
        }
        if (ActivityCompat.shouldShowRequestPermissionRationale(
                this, Manifest.permission.ACCESS_FINE_LOCATION
        )) {
            return;
        }
        ActivityCompat.requestPermissions(
                this,
                new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                PERMISSION_REQUEST_CODE
        );
    }

    @Override
    public void onDialogProceed(String cityName) {
        ((MainPresenter) getPresenter()).onManualLocation(cityName);
    }

    private void initToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.app_name);
        toolbar.setNavigationIcon(R.drawable.ic_cloud_queue_black_24dp);
    }

    private void showDialog() {
        getSupportFragmentManager()
                .beginTransaction()
                .add(new EnterCityDialogFragment(), EnterCityDialogFragment.FRAGMENT_TAG)
                .commit();
    }

    private void initViews() {
        root = findViewById(R.id.root);
        city = findViewById(R.id.tv_city);
        condition = findViewById(R.id.tv_condition);
        temp = findViewById(R.id.tv_temp);
        windSpeed = findViewById(R.id.tv_wind_speed);
        pressure = findViewById(R.id.tv_pressure);
        humidity = findViewById(R.id.tv_humidity);
        statsTextViews = Util.listOf(city, condition, temp, windSpeed, pressure, humidity);

        status = findViewById(R.id.tv_status);

        swipeRefreshLayout = findViewById(R.id.swipe_refresh);
        swipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.colorError));
        swipeRefreshLayout.setOnRefreshListener(() -> ((MainPresenter) getPresenter()).refresh());

        FloatingActionButton floatingActionButton = findViewById(R.id.fab);
        floatingActionButton.setOnClickListener(v -> showDialog());
    }

}
