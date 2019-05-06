package com.crazyduo.whatever.dagger2;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class PhoneModule {


    public PhoneModule( ) {

    }



    @Singleton
    @Provides
    Phone providePhone() {
        return new Phone("xiaomi");
    }

}



