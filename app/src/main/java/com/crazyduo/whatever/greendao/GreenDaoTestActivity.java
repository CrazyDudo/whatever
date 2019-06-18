package com.crazyduo.whatever.greendao;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.crazyduo.whatever.R;
import com.crazyduo.whatever.greendao.entity.User;
import com.crazyduo.whatever.greendao.helper.UserHelper;

import java.util.Collections;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GreenDaoTestActivity extends AppCompatActivity {
    private static final String TAG = "GreenDaoTestActivity";
    @BindView(R.id.btn_add)
    Button btnAdd;
    @BindView(R.id.btn_delete)
    Button btnDelete;
    @BindView(R.id.btn_update)
    Button btnUpdate;
    @BindView(R.id.btn_query)
    Button btnQuery;
    @BindView(R.id.tv)
    TextView tv;
    private User user;
    private UserHelper userHelper;
    private List<User> userList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_green_dao_test);
        ButterKnife.bind(this);

        userHelper = new UserHelper();

        user = new User();
    }

    @OnClick({R.id.btn_add, R.id.btn_delete, R.id.btn_update, R.id.btn_query})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_add:

                Random random = new Random();

                user.setId(random.nextLong());
                user.setMemberSex(1);
                user.setMemberLastX("HahaLast");
                user.setMemberId(123456);

                long insert = userHelper.getUserDao().insert(user);
                Log.d(TAG, "onViewClicked:=== " + insert);
                break;
            case R.id.btn_delete:
                userHelper.getUserDao().deleteAll();
                break;
            case R.id.btn_update:
                user.setId(22l);

                user.setMemberId(7891011);
                userHelper.getUserDao().update(user);
                break;
            case R.id.btn_query:
                userList = userHelper.getUserDao().loadAll();

                Collections.reverse(userList);
                tv.setText(userList.get(0).getId().toString());
                Log.d(TAG, "onViewClicked: "+userHelper.getUserDao().loadAll().get(userList.size()-1).getId());
                break;
        }
    }


}
