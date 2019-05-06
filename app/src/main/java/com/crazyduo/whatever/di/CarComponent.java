package com.crazyduo.whatever.di;

import dagger.Component;
@CarScope
@Component(modules = {MarkCarModule.class})
public interface CarComponent {
    void inject(Car car);
}