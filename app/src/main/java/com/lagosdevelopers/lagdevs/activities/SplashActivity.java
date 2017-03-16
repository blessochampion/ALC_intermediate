package com.lagosdevelopers.lagdevs.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.lagosdevelopers.lagdevs.R;
import com.lagosdevelopers.lagdevs.presenters.SplashPresenter;
import com.lagosdevelopers.lagdevs.views.SplashView;

public class SplashActivity extends AppCompatActivity implements SplashView {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        addFullScreenParameters();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        startCountDown();

    }

    private void addFullScreenParameters() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    private void startCountDown() {
        final SplashPresenter presenter = new SplashPresenter();
        presenter.attachView(this);

        new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    presenter.doCountDown();
                } catch (Exception e) {

                }
            }
        }).start();

    }

    @Override
    public void launchNextView() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {

               Intent bookService = new Intent(SplashActivity.this, DevelopersActivity.class);
                startActivity(bookService);
            }
        });

    }

}
