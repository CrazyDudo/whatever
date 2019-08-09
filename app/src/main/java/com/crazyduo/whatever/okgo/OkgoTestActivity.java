package com.crazyduo.whatever.okgo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;

import com.crazyduo.whatever.R;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.Callback;
import com.lzy.okgo.model.Progress;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class OkgoTestActivity extends AppCompatActivity {
    private static final String TAG = "OkgoTestActivity";
    @BindView(R.id.btn_post)
    Button btnPost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_okgo_test);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_post)
    public void onViewClicked() {

        OkGo.<String>post("https://st.exadigm.com/IFSS/servlet/servlets.receipt")
                .isMultipart(true)
                .retryCount(3)
                .params("token", "1234567890123456789012 ")
                .params("passwd", "Pwd1234")
                .params("emailto", "ruandong@xgd.com")
                .execute(new Callback<String>() {
                    @Override
                    public void onStart(Request<String, ? extends Request> request) {
                        Log.d(TAG, "onStart: " + request);
                    }

                    @Override
                    public void onSuccess(Response<String> response) {
                        Log.d(TAG, "onSuccess: " + response.body());
                    }

                    @Override
                    public void onCacheSuccess(Response<String> response) {

                    }

                    @Override
                    public void onError(Response<String> response) {
                        Log.d(TAG, "onError: " + response);
                    }

                    @Override
                    public void onFinish() {

                    }

                    @Override
                    public void uploadProgress(Progress progress) {

                    }

                    @Override
                    public void downloadProgress(Progress progress) {

                    }

                    @Override
                    public String convertResponse(okhttp3.Response response) throws Throwable {
                        return null;
                    }
                });
    }
}
