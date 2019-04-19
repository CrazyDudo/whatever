package com.crazyduo.jmessage.chat;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.crazyduo.jmessage.R;

public class ShowMessageActivity extends AppCompatActivity {
    public static final String EXTRA_MSG_TYPE = "msg_type";

    public static final String EXTRA_IS_GROUP = "isGroup";

    public static final String EXTRA_FROM_USERNAME = "from_username";

    public static final String EXTRA_FROM_APPKEY = "from_appkey";

    public static final String EXTRA_GROUPID = "from_gid";

    public static final String EXTRA_MSGID = "msgid";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_message);
    }
}
