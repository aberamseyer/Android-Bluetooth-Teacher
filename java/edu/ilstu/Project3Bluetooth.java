package edu.ilstu;

import android.app.Application;
import android.content.Context;

/**
 * Created by Abe on 12/3/2016.
 */

public class Project3Bluetooth extends Application {

    private static Context context;

    public void onCreate() {
        super.onCreate();
        Project3Bluetooth.context = getApplicationContext();
    }

    public static Context getAppContext() {
        return Project3Bluetooth.context;
    }
}