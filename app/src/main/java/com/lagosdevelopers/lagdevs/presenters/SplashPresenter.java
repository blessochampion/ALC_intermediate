package com.lagosdevelopers.lagdevs.presenters;

import com.lagosdevelopers.lagdevs.activities.SplashActivity;
import com.lagosdevelopers.lagdevs.views.SplashView;

import java.io.IOException;

/**
 * Created by blessochampion on 3/15/17.
 */

public class SplashPresenter
{
    private final int SLEEP_TIME = 3000;
    private String NO_VIEW_ATTACHED_ERROR_MESSAGE ="No View Attached";

    SplashView splashView;
    
    public void attachView(SplashView splashView){
        this.splashView =  splashView;
    }

    public void doCountDown() {
        if (splashView == null)
            throw new RuntimeException(NO_VIEW_ATTACHED_ERROR_MESSAGE);

        try {
            Thread.sleep(SLEEP_TIME);
            splashView.launchNextView();
        }catch (Exception e){

        }
    }
}
