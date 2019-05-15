package com.crazyduo.whatever.mvp;

public class LoginPresenter implements LoginContract.Presenter {

    private LoginContract.View loginView;

    public LoginPresenter(LoginContract.View loginView) {
        this.loginView = loginView;
        loginView.setPresenter(this);
    }

    @Override
    public void start() {

    }

    @Override
    public void login(String user, String pwd) {
        if (user.equals(pwd)) {
            loginView.onLoginSuccess();
        } else {
            loginView.onLoginFailed();
        }
    }
}
