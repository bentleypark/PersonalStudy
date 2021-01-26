package com.bentley.personalstudy.utils;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.startup.Initializer;

import com.bentley.personalstudy.BuildConfig;

import java.util.Collections;
import java.util.List;

import kotlin.Unit;
import timber.log.Timber;

public class TimberInitializer implements Initializer<Unit> {
    @NonNull
    @Override
    public Unit create(@NonNull Context context) {
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
        return Unit.INSTANCE;
    }

    @NonNull
    @Override
    public List<Class<? extends Initializer<?>>> dependencies() {
        return Collections.emptyList();
    }
}
