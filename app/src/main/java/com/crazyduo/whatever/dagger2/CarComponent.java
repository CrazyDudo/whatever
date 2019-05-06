package com.crazyduo.whatever.dagger2;

import android.location.LocationManager;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
//用这个标注标识是一个连接器
@Component( modules = CarModule.class)
public interface CarComponent {
    //这个连接器要注入的对象。这个inject标注的意思是，我后面的参数对象里面有标注为@Inject的属性，这个标注的属性是需要这个连接器注入进来的。
//    void carInject(TestActivity activity);
    LocationManager getLocationManager();
}