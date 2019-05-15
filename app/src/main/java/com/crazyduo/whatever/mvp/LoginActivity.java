package com.crazyduo.whatever.mvp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.crazyduo.whatever.R;

public class LoginActivity extends AppCompatActivity implements LoginContract.View {

    LoginContract.Presenter presenter;
    @butterknife.BindView(R.id.et_user)
    EditText etUser;
    @butterknife.BindView(R.id.et_pwd)
    EditText etPwd;
    @butterknife.BindView(R.id.btn_login)
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        butterknife.ButterKnife.bind(this);
        // Create the presenter
        new LoginPresenter(this);
    }


    @Override
    public void onLoginSuccess() {
        Toast.makeText(this, "Login success", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLoginFailed() {
        Toast.makeText(this, "Login failed", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setPresenter(LoginContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @butterknife.OnClick({R.id.et_pwd, R.id.btn_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {

            case R.id.btn_login:
                presenter.login(etUser.getText().toString().trim(), etPwd.getText().toString().trim());
                break;
        }
    }
}
