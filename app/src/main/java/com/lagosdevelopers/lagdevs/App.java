package com.lagosdevelopers.lagdevs;

import android.app.Application;
import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

//import com.facebook.FacebookSdk;
//import com.facebook.appevents.AppEventsLogger;

/**
 * Created by blessochampion on 5/1/16.
 */
public class App extends Application {

    private static App sInstance;
    private static Context sContext;


    @Override
    public void onCreate() {

        super.onCreate();
        sInstance = this;
        setAPPContext(getApplicationContext());
    }


    public static Context getAppContext() {

        return sContext;

    }

    private void setAPPContext(Context appContext) {

        sContext = appContext;

    }


    public static App getInstance() {

        return AppHelper.INSTANCE;

    }
    public RequestQueue getRequestQueue() {

        return AppHelper.REQUEST_QUEUE;

    }

    private static class AppHelper {

        private static App INSTANCE = sInstance;
        private static RequestQueue REQUEST_QUEUE = Volley.newRequestQueue(getAppContext());

    }

}
