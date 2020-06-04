package com.crazyduo.whatever.basic;

import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 控制线程停止，执行五次之后停止
 */
public class ScheduledThreadPoolExecutorTest {
    static ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(15); // no
    static ScheduledFuture<?> t;

    static class MyTask implements Runnable {
        private int attempt = 1;

        public void run() {
            System.out.print(attempt + " ====");
            if (++attempt > 5) {
                t.cancel(false);
            }
        }
    }

    public static void main(String[] args) {
        t = executor.scheduleAtFixedRate(new MyTask(), 0, 1, TimeUnit.SECONDS);
    }
}