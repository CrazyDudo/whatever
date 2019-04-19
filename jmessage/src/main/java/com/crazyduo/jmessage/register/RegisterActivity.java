package com.crazyduo.jmessage.register;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.crazyduo.jmessage.R;
import com.orhanobut.logger.Logger;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterActivity extends AppCompatActivity implements RegisterContract.IView {


    @BindView(R.id.edit_username)
    EditText editUsername;
    @BindView(R.id.edit_pwd)
    EditText editPwd;
    @BindView(R.id.btn_register)
    Button btnRegister;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.btn_get_message)
    Button btnGetMessage;
    private RegisterPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        initPresenter();
    }

    private void initPresenter() {
        presenter = new RegisterPresenter(this);
    }

    @Override
    public void onLoading() {

    }

    @Override
    public void onRegisterSuccess() {
        Toast.makeText(this, "onRegisterSuccess", Toast.LENGTH_SHORT).show();
        Logger.d("onRegisterSuccess");
    }

    @Override
    public void onRegisterFailed() {

    }

    @Override
    public void onLoginSuccess() {
        Toast.makeText(this, "onLoginSuccess", Toast.LENGTH_SHORT).show();
        Logger.d("onLoginSuccess");
    }

    @OnClick({R.id.btn_register, R.id.btn_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_register:
                presenter.register(editUsername.getText().toString().trim(), "123456");
                break;

            case R.id.btn_login:
                presenter.login(editUsername.getText().toString().trim(), "123456" );
                break;

            case R.id.btn_get_message:


                break;
        }
    }

}
