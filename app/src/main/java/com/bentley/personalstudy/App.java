package com.bentley.personalstudy;

import android.app.Application;

public class App extends Application {
    private static App instance;

    public static App getGlobalApplicationContext() {
        if (instance == null) {
            throw new IllegalStateException("This Application instant is null.");
        }

        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        instance = null;
    }
}
