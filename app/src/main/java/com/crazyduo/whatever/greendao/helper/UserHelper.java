package com.crazyduo.whatever.greendao.helper;

import com.crazyduo.whatever.greendao.GreenDaoManager;
import com.crazyduo.whatever.greendao.entity.User;
import com.crazyduo.whatever.greendao.gen.UserDao;

public class UserHelper {

    public UserDao getUserDao()
    {
        return GreenDaoManager.getInstance().getmDaoSession().getUserDao();
    }

    public User getUser(Long id)
    {
        return getUserDao().load(id);
    }
}
