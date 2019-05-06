package com.crazyduo.whatever.di;

import dagger.Module;
import dagger.Provides;

@Module
public class MarkCarModule {

    public MarkCarModule(){ }

    @QualifierA
    @Provides
    Engine provideEngineA(){
        return new Engine("gearA");
    }

    @QualifierB
    @Provides
    Engine provideEnginB() {
        return new Engine("gearB");
    }
}