package com.crazyduo.whatever.dagger2;

import android.content.Context;
import android.location.LocationManager;

import com.crazyduo.whatever.AppApplication;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class CarModule {
    private final AppApplication application;

    public CarModule(AppApplication application) {
        this.application = application;
    }

    @Provides
    @Singleton
    Context ApplicationContext(){
        return application;
    }

    @Provides
    @Singleton
    Car provideCar() {
        return  new Car(application);
    }

    @Provides
    @Singleton
    LocationManager getLocationManager() {

        return (LocationManager) application.getSystemService(Context.LOCATION_SERVICE);
    }
}
