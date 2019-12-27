package com.crazyduo.whatever.basic;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 线程池
 * https://blog.csdn.net/u011974987/article/details/51027795
 */
public class ThreadPool {

    public static void main(String[] args) {
//        cachedThreadPool();
//        fixedThreadPool();
//        scheduledThreadPool();
        singleThreadPool();
    }

    private static void cachedThreadPool() {
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            final int index = i;
            try {
                Thread.sleep(index * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            cachedThreadPool.execute(new Runnable() {

                @Override
                public void run() {
                    System.out.println(index);
                }
            });
        }

    }

    private static void fixedThreadPool() {

        ExecutorService executorService = Executors.newFixedThreadPool(2);

        for (int i = 0; i < 10; i++) {
            final int index = i;
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println(index);
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }


    }

    private static void scheduledThreadPool() {

        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);

        //延时执行
        scheduledExecutorService.schedule(new Runnable() {
            @Override
            public void run() {

                System.out.println("delay");
            }
        }, 3, TimeUnit.SECONDS);

        //周期执行


        scheduledExecutorService.scheduleAtFixedRate(new Runnable() {
            int i = 0;

            @Override
            public void run() {
                i++;
                System.out.println("delay 1s, period 3s,  " + i);
            }
        }, 1, 3, TimeUnit.SECONDS);
    }

    private static void singleThreadPool() {

        ExecutorService executorService = Executors.newSingleThreadExecutor();

        for (int i = 0; i < 10; i++) {
            final int index = i;
            executorService.execute(new Runnable() {
                @Override
                public void run() {

                    try {
                        System.out.println(index);
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }
}
