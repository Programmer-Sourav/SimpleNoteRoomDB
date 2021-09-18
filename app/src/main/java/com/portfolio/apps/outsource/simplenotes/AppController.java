package com.portfolio.apps.outsource.simplenotes;

import android.app.Application;
import android.content.Context;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AppController extends Application {
    private static AppController instance;

    ExecutorService executorService = Executors.newFixedThreadPool(4);

    public AppController() {
        instance = this;
    }

    public static Context getContext() {
        return instance;
    }
}
