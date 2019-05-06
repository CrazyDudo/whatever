package com.crazyduo.whatever.dagger2;

import javax.inject.Singleton;

import dagger.Component;
@Singleton
@Component(modules = {PhoneModule.class})
interface PhoneComponent {
   void inject(TestActivity testActivity);
}