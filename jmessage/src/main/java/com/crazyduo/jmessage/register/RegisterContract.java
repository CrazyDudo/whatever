package com.crazyduo.jmessage.register;

import cn.jpush.im.android.api.event.MessageEvent;

public interface RegisterContract {
    interface IPresenter {
        void register(String username, String password);

        void login(String username, String password);

        void getMessage(MessageEvent event);
    }

    interface IView {

        void onLoading();

        void onRegisterSuccess();

        void onRegisterFailed();

        void onLoginSuccess();

    }

}
