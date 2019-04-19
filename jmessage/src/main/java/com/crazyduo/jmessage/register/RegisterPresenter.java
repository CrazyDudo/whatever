package com.crazyduo.jmessage.register;

import com.orhanobut.logger.Logger;

import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.android.api.event.MessageEvent;
import cn.jpush.im.api.BasicCallback;


public class RegisterPresenter implements RegisterContract.IPresenter {
    private RegisterContract.IView view;

    public RegisterPresenter(RegisterContract.IView view) {
        this.view = view;
    }

    @Override
    public void register(String username, String password) {
        view.onRegisterSuccess();
        JMessageClient.register(username, password, new BasicCallback() {
            @Override
            public void gotResult(int i, String s) {
                Logger.d(i + "===" + s);

            }
        });
    }

    @Override
    public void login(String username, String password) {
        view.onLoginSuccess();
        JMessageClient.login(username, password, new BasicCallback() {
            @Override
            public void gotResult(int i, String s) {
                Logger.d(i + "===" + s);
                if (i == 0) {

                }
            }
        });
    }

    @Override
    public void getMessage(MessageEvent event) {


    }


}
