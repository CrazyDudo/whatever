package com.crazyduo.whatever.handler;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

import com.crazyduo.whatever.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 线程及消息机制
 */
public class ThreadActivity extends AppCompatActivity {
    private static final String TAG = "ThreadActivity";
    @BindView(R.id.btn_update_text)
    Button btnUpdateText;
    private TextView tvText;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            tvText.setText("handleMessage== " + msg.arg1);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread);
        ButterKnife.bind(this);

        tvText = findViewById(R.id.tv_text);

        //可能会报错，原因线程sleep 180 毫秒，若已完成View构建，则报错；否正，不报错。
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//
//                try {
//                    Thread.sleep(180);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                tvText.setText("form new Thread123");
//            }
//        }).start();
    }


    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onResume() {
        super.onResume();
//        //onResume走完之前都可以，子线程更新UI。一旦走完onResume()及View构建完成，子线程更新则会报错。
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                tvText.setText("form new Thread123");
//            }
//        }).start();
    }


    @Override
    protected void onPause() {
        super.onPause();
        new Thread(new Runnable() {
            @Override
            public void run() {
//                tvText.setText("form new Thread123");
//                Message message = new Message();
//                message.obj = message;
//
//                Handler handler = new Handler(getMainLooper());
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        tvText.setText("form new Thread1234");
                    }
                });
            }
        }).start();
    }


    @OnClick(R.id.btn_update_text)
    public void onViewClicked() {
//        updateUI4();
//        updateUI1();
        updateUI2();
    }


    /**
     * 四种子线程更新UI
     * 1 主线程中定义Handler，子线程通过mHandler发送消息，主线程Handler的handleMessage更新UI。
     * 2 用Activity对象的runOnUiThread方法。
     * 3 创建Handler，传入getMainLooper。
     * 4 View.post(Runnable r) 。
     */

    //1 主线程中定义Handler，子线程通过mHandler发送消息，主线程Handler的handleMessage更新UI。
    public void updateUI1() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        tvText.setText("updateUI1==" + System.currentTimeMillis());
                    }
                });

                Message message = new Message();
                message.arg1 = (int) System.currentTimeMillis();
                handler.sendMessage(message);
            }
        }).start();
    }

    //  2 用Activity对象的runOnUiThread方法。
    public void updateUI2() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        tvText.setText("updateUI2==" + System.currentTimeMillis());
                    }
                });

            }
        }).start();
    }


    // 3 创建Handler，传入getMainLooper。
    private void updateUI4() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Handler handler = new Handler(getMainLooper());
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        tvText.setText("updateUI4==" + System.currentTimeMillis());
                    }
                });

            }
        }).start();

    }

    // 4 View.post(Runnable r)
    private void updateUI3() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                tvText.post(new Runnable() {
                    @Override
                    public void run() {
                        tvText.setText("updateUI3==" + System.currentTimeMillis());
                    }
                });
            }
        }).start();

    }
}
