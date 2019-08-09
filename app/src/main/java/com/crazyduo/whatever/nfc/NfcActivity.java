package com.crazyduo.whatever.nfc;

import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.nfc.NfcAdapter;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.Html;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.crazyduo.whatever.R;
import com.crazyduo.whatever.nfc.bean.CardInfo;
import com.crazyduo.whatever.nfc.card.CardManager;
import com.orhanobut.logger.Logger;

import org.xml.sax.XMLReader;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NfcActivity extends AppCompatActivity implements Html.ImageGetter, Html.TagHandler {
    private static final String TAG = "NfcActivity";
    @BindView(R.id.tv_card_name)
    TextView tvCardName;
    @BindView(R.id.tv_card_number)
    TextView tvCardNumber;
    @BindView(R.id.tv_card_balance)
    TextView tvCardBalance;

    private NfcAdapter nfcAdapter; //NFC适配器
    private PendingIntent pendingIntent; //传达意图

    private Resources res;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nfc);
        ButterKnife.bind(this);
        initData();
    }

    private void initData() {
        final Resources res = getResources();
        this.res = res;
        nfcAdapter = NfcAdapter.getDefaultAdapter(this);
        pendingIntent = PendingIntent.getActivity(this, 0, new Intent(this,
                getClass()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0);

        onNewIntent(getIntent());
    }

    @Override
    protected void onPause() {
        super.onPause();

        if (nfcAdapter != null)
            nfcAdapter.disableForegroundDispatch(this);// 取消调度

    }

    @Override
    protected void onResume() {
        super.onResume();

        if (nfcAdapter != null)
            // 这行代码是添加调度，效果是读标签的时候不会弹出候选程序，直接用本程序处理
            nfcAdapter.enableForegroundDispatch(this, pendingIntent,
                    CardManager.FILTERS, CardManager.TECHLISTS);

//		refreshStatus();
    }


    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        try {
            final Parcelable p = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);//获取tag
            Log.e(TAG, "NFCTAG:" + intent.getAction());
            Log.d(TAG, "onNewIntent: =========" + p.toString());
            Logger.d(p);
            showData((p != null) ? CardManager.load(p, res) : null);
        } catch (Exception e) {
            Log.e(TAG, "获取tag异常", e);
        }
    }

    private void showData(String data) {
        if (data == null || data.length() == 0) {

            return;
        }
        CardInfo cardInfo = CardManager.getCardInfo();

        Log.i(TAG, cardInfo.toString());
        tvCardName.setText(cardInfo.getCardName());
        tvCardNumber.setText("卡号:" + cardInfo.getCardNo());
        tvCardBalance.setText(Html.fromHtml("余额:<font color='red'>" + cardInfo.getCardBalance() + "</font>"));
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        refreshStatus();
    }

    private void refreshStatus() {
        final Resources r = this.res;

        final String tip;
        if (nfcAdapter == null)
            tip = r.getString(R.string.tip_nfc_notfound);
        else if (nfcAdapter.isEnabled())
            tip = r.getString(R.string.tip_nfc_enabled);
        else
            tip = r.getString(R.string.tip_nfc_disabled);

        Toast.makeText(this, tip, Toast.LENGTH_SHORT).show();
//        final CharSequence text = mHint.getText();
//        showHint();

    }


    @Override
    public void handleTag(boolean opening, String tag, Editable output,
                          XMLReader xmlReader) {
        if (!opening && "version".equals(tag)) {
            try {
                output.append(getPackageManager().getPackageInfo(
                        getPackageName(), 0).versionName);
            } catch (PackageManager.NameNotFoundException e) {
            }
        }
    }

    @Override
    public Drawable getDrawable(String source) {
        final Resources r = getResources();

        final Drawable ret;
        final String[] params = source.split(",");
        if ("icon_main".equals(params[0])) {
            ret = r.getDrawable(R.drawable.ic_launcher_background);
        } else {
            ret = null;
        }

        if (ret != null) {
            final float f = r.getDisplayMetrics().densityDpi / 72f;
            final int w = (int) (Util.parseInt(params[1], 10, 16) * f + 0.5f);
            final int h = (int) (Util.parseInt(params[2], 10, 16) * f + 0.5f);
            ret.setBounds(0, 0, w, h);
        }

        return ret;
    }
}