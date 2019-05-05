package com.crazyduo.whatever.rx;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.crazyduo.whatever.R;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class RxTest1Activity extends AppCompatActivity {
    private static final String TAG = "RxTest1Activity";
    Observable swithcer = Observable.just("on", "off", "off", "on");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_test1);
        initView();
    }

    private void initView() {
        findViewById(R.id.btn_switch).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                test1();
//                test2();
//                test3();
//                test4();
                test5();
            }


        });
    }

    private void test5() {
        Observable.interval(1, TimeUnit.MILLISECONDS)
                .onBackpressureDrop()
                .observeOn(Schedulers.newThread())
                .subscribe(new Subscriber<Long>() {
                    @Override
                    public void onStart() {
                        Log.d(TAG, "onStart: ");
                    }

                    @Override
                    public void onCompleted() {
                        Log.d(TAG, "onCompleted: ");
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Long aLong) {
                        Log.d(TAG, "onNext: "+aLong.toString());
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                });

    }

    Observable observable = Observable.range(1, 100);

    class MySubscribe extends Subscriber {

        @Override
        public void onStart() {
            Log.d(TAG, "onStart: ");
            request(1);
        }

        @Override
        public void onCompleted() {
            Log.d(TAG, "onCompleted: ");

        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onNext(Object o) {
            request(1);
            Log.d(TAG, "onNext: " + o.toString());
        }
    }

    //普通情况：被观察者，发起事件，观察者响应。
    //响应式拉取（reactive pull）：观察者request被观察者，发送事件。
    private void test4() {

        observable.observeOn(Schedulers.newThread())
                .subscribe(new MySubscribe());

    }

    //back pressure
    private void test3() {

        Observable.interval(1, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.newThread())
                .observeOn(Schedulers.newThread())
                .subscribe(new Action1<Long>() {

                    @Override
                    public void call(Long aLong) {
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        Log.w("TAG", "---->" + aLong);
                    }
                });
    }


    private void test2() {
        Observable.just("filepath")
                .map(new Func1<String, Bitmap>() {
                    @Override
                    public Bitmap call(String s) {
                        return null;
                    }
                })
                .subscribe(new Subscriber<Bitmap>() {
                    @Override
                    public void onCompleted() {
                        Log.d(TAG, "onCompleted: ");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError: ");
                    }

                    @Override
                    public void onNext(Bitmap bitmap) {
                        Log.d(TAG, "onNext: ");
                    }
                });
    }


    private void test1() {

//                swithcer.subscribe(light);
        Observable.just("on", "on", "off", "off")
                .filter(new Func1<String, Boolean>() {
                    @Override
                    public Boolean call(String s) {

                        Log.d(TAG, "call: " + s);
                        return s != null;
                    }
                })
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {
                        Log.d(TAG, "onCompleted: ");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError: " + e);

                    }

                    @Override
                    public void onNext(String s) {
                        Log.d(TAG, "onNext: " + s);
                    }
                });


    }

    Subscriber light = new Subscriber<String>() {
        @Override
        public void onCompleted() {
            Log.d(TAG, "onCompleted: ");
        }

        @Override
        public void onError(Throwable e) {
            Log.d(TAG, "onError: " + e);
        }

        @Override
        public void onNext(String s) {
            Log.d(TAG, "onNext: " + s);
        }
    };
}
