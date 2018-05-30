package android.zgy.meichang;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.zgy.meichang.util.PreferenceUtil;

import java.text.DecimalFormat;

import static android.zgy.meichang.util.DateUtil.DANG_QIAN_HAI_YOU_MEI;
import static android.zgy.meichang.util.DateUtil.DANG_QIAN_PING_JUN_CHENG_BEN_JIA;
import static android.zgy.meichang.util.DateUtil.GONG_HUA_FEI;
import static android.zgy.meichang.util.DateUtil.GONG_SHOU_DAO;
import static android.zgy.meichang.util.DateUtil.QI_TA_ZHI_CHU;
import static android.zgy.meichang.util.DateUtil.ZONG_GONG_CHU_MEI;
import static android.zgy.meichang.util.DateUtil.ZONG_GONG_JIN_MEI;
import static android.zgy.meichang.util.DateUtil.getPreferenceStr;
import static android.zgy.meichang.util.DateUtil.mDouble_DANG_QIAN_HAI_YOU_MEI;
import static android.zgy.meichang.util.DateUtil.mDouble_DANG_QIAN_PING_JUN_CHENG_BEN_JIA;
import static android.zgy.meichang.util.DateUtil.mDouble_GONG_HUA_FEI;
import static android.zgy.meichang.util.DateUtil.mDouble_GONG_SHOU_DAO;
import static android.zgy.meichang.util.DateUtil.mDouble_QI_TA_ZHI_CHU;
import static android.zgy.meichang.util.DateUtil.mDouble_ZONG_GONG_CHU_MEI;
import static android.zgy.meichang.util.DateUtil.mDouble_ZONG_GONG_JIN_MEI;
import static android.zgy.meichang.util.DateUtil.mStr_DANG_QIAN_HAI_YOU_MEI;
import static android.zgy.meichang.util.DateUtil.mStr_DANG_QIAN_PING_JUN_CHENG_BEN_JIA;
import static android.zgy.meichang.util.DateUtil.mStr_GONG_HUA_FEI;
import static android.zgy.meichang.util.DateUtil.mStr_GONG_SHOU_DAO;
import static android.zgy.meichang.util.DateUtil.mStr_QI_TA_ZHI_CHU;
import static android.zgy.meichang.util.DateUtil.mStr_ZONG_GONG_CHU_MEI;
import static android.zgy.meichang.util.DateUtil.mStr_ZONG_GONG_JIN_MEI;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    public static long exitTime = 0;
    private TextView mTextDanQianHaiYouMei;
    private TextView mTextDanQianPingJunChengBenJia;
    private TextView mTextZongGongJinMei;
    private TextView mTextZongGongChuMei;
    private TextView mTextQiTaZhiChu;
    private Button mBtnJinMei;
    private Button mBtnChuMei;
    private Button mBtnZhiChu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextDanQianHaiYouMei = (TextView) findViewById(R.id.id_text_dangqianhaiyoumei);
        mTextDanQianPingJunChengBenJia = (TextView) findViewById(R.id.id_text_dangqianpingjunchengbenjia);
        mTextZongGongJinMei = (TextView) findViewById(R.id.id_text_zonggongjinmei);
        mTextZongGongChuMei = (TextView) findViewById(R.id.id_text_zonggongchumei);
        mTextQiTaZhiChu = (TextView) findViewById(R.id.id_text_qitazhichu);
        mBtnChuMei = (Button) findViewById(R.id.id_button_chumei);
        mBtnJinMei = (Button) findViewById(R.id.id_button_jinmei);
        mBtnZhiChu = (Button) findViewById(R.id.id_button_zhichu);

        addListener(MainActivity.this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        initView(MainActivity.this);
    }

    private void addListener(final Context context){
        mBtnJinMei.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, JinMeiActivity.class);
                startActivity(intent);
                //finish();
            }
        });

        mBtnChuMei.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ChuMeiActivity.class);
                startActivity(intent);
                //finish();
            }
        });

        mBtnZhiChu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ZhiChuActivity.class);
                startActivity(intent);
                //finish();
            }
        });
    }

    @SuppressLint("SetTextI18n")
    private void initView(Context context){
        //取数据
        getPreferenceStr(context);

        //初始化数据
        if("".equals(mStr_DANG_QIAN_HAI_YOU_MEI)){
            mStr_DANG_QIAN_HAI_YOU_MEI = "0";
            PreferenceUtil.saveString(context,DANG_QIAN_HAI_YOU_MEI,mStr_DANG_QIAN_HAI_YOU_MEI);
        }if("".equals(mStr_DANG_QIAN_PING_JUN_CHENG_BEN_JIA)){
            mStr_DANG_QIAN_PING_JUN_CHENG_BEN_JIA = "0";
            PreferenceUtil.saveString(context,DANG_QIAN_PING_JUN_CHENG_BEN_JIA,mStr_DANG_QIAN_PING_JUN_CHENG_BEN_JIA);
        }if("".equals(mStr_ZONG_GONG_JIN_MEI)){
            mStr_ZONG_GONG_JIN_MEI = "0";
            PreferenceUtil.saveString(context,ZONG_GONG_JIN_MEI,mStr_ZONG_GONG_JIN_MEI);
        }if("".equals(mStr_GONG_HUA_FEI)){
            mStr_GONG_HUA_FEI = "0";
            PreferenceUtil.saveString(context,GONG_HUA_FEI,mStr_GONG_HUA_FEI);
        }if("".equals(mStr_ZONG_GONG_CHU_MEI)){
            mStr_ZONG_GONG_CHU_MEI = "0";
            PreferenceUtil.saveString(context,ZONG_GONG_CHU_MEI,mStr_ZONG_GONG_CHU_MEI);
        }if("".equals(mStr_GONG_SHOU_DAO)){
            mStr_GONG_SHOU_DAO = "0";
            PreferenceUtil.saveString(context,GONG_SHOU_DAO,mStr_GONG_SHOU_DAO);
        }if("".equals(mStr_QI_TA_ZHI_CHU)){
            mStr_QI_TA_ZHI_CHU = "0";
            PreferenceUtil.saveString(context,QI_TA_ZHI_CHU,mStr_QI_TA_ZHI_CHU);
        }
        //setText
        DecimalFormat df = new DecimalFormat("#,##0.00");
        mTextDanQianHaiYouMei.setText(df.format(Double.valueOf(mStr_DANG_QIAN_HAI_YOU_MEI).doubleValue()) + "吨");
        mTextDanQianPingJunChengBenJia.setText(df.format(Double.valueOf(mStr_DANG_QIAN_PING_JUN_CHENG_BEN_JIA).doubleValue()) + "元/吨");
        mTextZongGongJinMei.setText(df.format(Double.valueOf(mStr_ZONG_GONG_JIN_MEI).doubleValue()) + "吨，共花费"
                + df.format(Double.valueOf(mStr_GONG_HUA_FEI).doubleValue()) + "元");
        mTextZongGongChuMei.setText(df.format(Double.valueOf(mStr_ZONG_GONG_CHU_MEI).doubleValue()) + "吨，共收到"
                + df.format(Double.valueOf(mStr_GONG_SHOU_DAO).doubleValue()) + "元");
        mTextQiTaZhiChu.setText(df.format(Double.valueOf(mStr_QI_TA_ZHI_CHU).doubleValue()) + "元");

        Log.e(TAG, "getPreferenceStr2double String: " + mStr_DANG_QIAN_HAI_YOU_MEI + " , " + mStr_DANG_QIAN_PING_JUN_CHENG_BEN_JIA + " , " + mStr_ZONG_GONG_JIN_MEI
                + " , " + mStr_GONG_HUA_FEI + " , " + mStr_ZONG_GONG_CHU_MEI  + " , " + mStr_GONG_SHOU_DAO + " , " + mStr_QI_TA_ZHI_CHU);
        Log.e(TAG, "getPreferenceStr2double Double: " + mDouble_DANG_QIAN_HAI_YOU_MEI + " , " + mDouble_DANG_QIAN_PING_JUN_CHENG_BEN_JIA + " , " + mDouble_ZONG_GONG_JIN_MEI
                + " , " + mDouble_GONG_HUA_FEI + " , " + mDouble_ZONG_GONG_CHU_MEI  + " , " + mDouble_GONG_SHOU_DAO + " , " + mDouble_QI_TA_ZHI_CHU);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTime) > 3200) {
                Toast.makeText(getApplicationContext(), R.string.again_according_to_the_exit, Toast.LENGTH_SHORT).show();//再按一次退出
                exitTime = System.currentTimeMillis();
            } else {
                finish();
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}
