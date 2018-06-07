package me.suzdalnitsky.weather.ui.common;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

public abstract class MvpAppCompatActivity extends AppCompatActivity implements MvpView {

    private MvpPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        presenter = PresenterStore.getInstance().getPresenter(this.getClass());
    }

    @Override
    protected void onStart() {
        super.onStart();

        if (presenter != null) {
            presenter.attachView(this);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (presenter != null) {
            presenter.attachView(this);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        if (presenter != null) {
            presenter.detachView();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();

        if (presenter != null) {
            presenter.detachView();    
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (isFinishing()) {
            presenter.onDestroy();
            PresenterStore.getInstance().destroyPresenter(this.getClass());
        }
    }

    protected MvpPresenter getPresenter() {
        return presenter;
    }
}
