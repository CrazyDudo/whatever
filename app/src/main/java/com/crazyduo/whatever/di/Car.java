package com.crazyduo.whatever.di;

import javax.inject.Inject;

class Car {
    @QualifierA
    @Inject
    Engine engineA;

    @QualifierA
    @Inject
    Engine engineB;
//    @QualifierB
//    @Inject
//    Engine engineB;


    public Car() {
//        DaggerCarComponent.builder().build().inject(this);
        DaggerCarComponent.builder()
                .markCarModule(new MarkCarModule())
                .build()
                .inject(this);
    }

    public Engine getEngineA() {
        return engineA;

    }

    public Engine getEngineB() {
        return engineB;
    }
}
