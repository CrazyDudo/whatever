package com.crazyduo.whatever.filedownload;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;

import com.crazyduo.whatever.R;
import com.liulishuo.okdownload.DownloadContext;
import com.liulishuo.okdownload.DownloadContextListener;
import com.liulishuo.okdownload.DownloadListener;
import com.liulishuo.okdownload.DownloadTask;
import com.liulishuo.okdownload.core.Util;
import com.liulishuo.okdownload.core.cause.EndCause;
import com.liulishuo.okdownload.core.listener.DownloadListener2;

import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FileDownloadActivity extends AppCompatActivity {

    private static final String TAG = "FileDownloadActivity";
    //测试下载url
    String url1 = "https://gitee.com/crazydudo/test/blob/master/vanso/vanso1.liteso";
    String url2 = "https://gitee.com/crazydudo/test/blob/master/vanso/vanso2.liteso";
    String url3 = "https://gitee.com/crazydudo/test/blob/master/vanso/vanso2.liteso";

    private final ExecutorService executor = Executors.newCachedThreadPool();
    private final ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();//单线程用于安装vanso

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_download);
        initView();
    }

    private void initView() {
        findViewById(R.id.btn_single_task).setOnClickListener(v -> executor.execute(() -> singleTask()));
        findViewById(R.id.btn_multi_task).setOnClickListener(v -> multiTask());
    }

    /**
     * 多任务下载
     */
    private void multiTask() {
        DownloadContext.Builder builder = new DownloadContext.QueueSet()
                .setParentPathFile(getParentFile(this))
                .setMinIntervalMillisCallbackProcess(150)
                .commit();
        builder.bind(url1);
        builder.bind(url2).addTag(2, "task2");
        builder.bind(url3).setTag(TAG);
//        DownloadTask task = new DownloadTask.Builder(url4, parentFile)
//                .setPriority(10).build();
//        builder.bindSetTask(task);
        builder.setListener(contextListener);
        DownloadContext context = builder.build();
        context.startOnParallel(listener2);
// stop
//        context.stop();
    }

    //整个队列listener
    DownloadContextListener contextListener = new DownloadContextListener() {
        @Override
        public void taskEnd(@NonNull DownloadContext context, @NonNull DownloadTask task, @NonNull EndCause cause, @Nullable Exception realCause, int remainCount) {
            Log.d(TAG, "taskEnd: contextListener================");
        }

        @Override
        public void queueEnd(@NonNull DownloadContext context) {
            Log.d(TAG, "queueEnd:contextListener ==============");
        }
    };

    //单个任务listener
    DownloadListener listener2 = new DownloadListener2() {
        @Override
        public void taskStart(@NonNull DownloadTask task) {

            Log.d(TAG, "taskStart: " + task.getUrl());
        }

        @Override
        public void taskEnd(@NonNull DownloadTask task, @NonNull EndCause cause, @Nullable Exception realCause) {
            Log.d(TAG, "taskEnd: " + task.getUrl());
        }
    };


    /**
     * 单任务下载
     */
    private void singleTask() {
        DownloadTask task = new DownloadTask.Builder(url1, getParentFile(this))
//               .setFilename("vanso1.liteso")
                .setMinIntervalMillisCallbackProcess(30) // 下载进度回调的间隔时间（毫秒）
                .setPassIfAlreadyCompleted(false)// 任务过去已完成是否要重新下载
                .setPriority(10)
                .build();
//        task.enqueue(listener);//异步执行任务
//        task.cancel();// 取消任务
        task.execute(new DownloadListener2() {
            @Override
            public void taskStart(@NonNull DownloadTask task) {
                Log.d(TAG, "taskStart: ==========" + task.getUrl());
            }

            @Override
            public void taskEnd(@NonNull DownloadTask task, @NonNull EndCause cause, @Nullable Exception realCause) {
                Log.d(TAG, "taskEnd: getUrl==========" + task.getUrl());
                Log.d(TAG, "taskEnd: getParentFile==========" + task.getParentFile());
                Log.d(TAG, "taskEnd: getFile==========" + task.getFile());
            }
        });
    }


    public static File getParentFile(@NonNull Context context) {
        final File externalSaveDir = context.getExternalCacheDir();
        if (externalSaveDir == null) {
            return context.getCacheDir();
        } else {
            return externalSaveDir;
        }
    }


}