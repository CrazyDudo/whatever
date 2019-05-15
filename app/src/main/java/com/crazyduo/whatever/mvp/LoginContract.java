package com.crazyduo.whatever.mvp;

import com.crazyduo.whatever.mvp.base.BasePresenter;
import com.crazyduo.whatever.mvp.base.BaseView;

public interface LoginContract {

    interface View extends BaseView<Presenter> {

        void onLoginSuccess();
        void onLoginFailed();

    }

    interface Presenter extends BasePresenter {

        void login(String user,String pwd);
    }


}
