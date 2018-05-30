package android.zgy.meichang.util;

import android.content.Context;
import android.util.Log;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by ajf-dell on 2017/4/11.
 */

public class DateUtil {
    private static final String TAG = "DateUtil";

    public static final String DANG_QIAN_HAI_YOU_MEI = "dangqianhaiyoumei";
    public static final String DANG_QIAN_PING_JUN_CHENG_BEN_JIA = "dangqianpingjunchengbenjia";
    public static final String ZONG_GONG_JIN_MEI = "zonggongjinmei";
    public static final String GONG_HUA_FEI = "gonghuafei";
    public static final String ZONG_GONG_CHU_MEI = "zonggongchumei";
    public static final String GONG_SHOU_DAO = "gongshoudao";
    public static final String QI_TA_ZHI_CHU = "qitazhichu";

    public static  String mStr_DANG_QIAN_HAI_YOU_MEI, mStr_DANG_QIAN_PING_JUN_CHENG_BEN_JIA,
            mStr_ZONG_GONG_JIN_MEI,mStr_GONG_HUA_FEI, mStr_ZONG_GONG_CHU_MEI,mStr_GONG_SHOU_DAO,mStr_QI_TA_ZHI_CHU;
    public static  double mDouble_DANG_QIAN_HAI_YOU_MEI, mDouble_DANG_QIAN_PING_JUN_CHENG_BEN_JIA,
            mDouble_ZONG_GONG_JIN_MEI,mDouble_GONG_HUA_FEI, mDouble_ZONG_GONG_CHU_MEI,mDouble_GONG_SHOU_DAO,mDouble_QI_TA_ZHI_CHU;

    /**
     * 取数据
     * @param context
     */
    public static void getPreferenceStr(Context context){
        mStr_DANG_QIAN_HAI_YOU_MEI = PreferenceUtil.getString(context,DANG_QIAN_HAI_YOU_MEI,"");
        mStr_DANG_QIAN_PING_JUN_CHENG_BEN_JIA = PreferenceUtil.getString(context,DANG_QIAN_PING_JUN_CHENG_BEN_JIA,"");
        mStr_ZONG_GONG_JIN_MEI = PreferenceUtil.getString(context,ZONG_GONG_JIN_MEI,"");
        mStr_GONG_HUA_FEI = PreferenceUtil.getString(context,GONG_HUA_FEI,"");
        mStr_ZONG_GONG_CHU_MEI = PreferenceUtil.getString(context,ZONG_GONG_CHU_MEI,"");
        mStr_GONG_SHOU_DAO = PreferenceUtil.getString(context,GONG_SHOU_DAO,"");
        mStr_QI_TA_ZHI_CHU = PreferenceUtil.getString(context,QI_TA_ZHI_CHU,"");

        Log.e(TAG, "getPreferenceStr String: " + mStr_DANG_QIAN_HAI_YOU_MEI + " , " + mStr_DANG_QIAN_PING_JUN_CHENG_BEN_JIA + " , " + mStr_ZONG_GONG_JIN_MEI
                + " , " + mStr_GONG_HUA_FEI + " , " + mStr_ZONG_GONG_CHU_MEI  + " , " + mStr_GONG_SHOU_DAO + " , " + mStr_QI_TA_ZHI_CHU);
        Log.e(TAG, "getPreferenceStr Double: " + mDouble_DANG_QIAN_HAI_YOU_MEI + " , " + mDouble_DANG_QIAN_PING_JUN_CHENG_BEN_JIA + " , " + mDouble_ZONG_GONG_JIN_MEI
                + " , " + mDouble_GONG_HUA_FEI + " , " + mDouble_ZONG_GONG_CHU_MEI  + " , " + mDouble_GONG_SHOU_DAO + " , " + mDouble_QI_TA_ZHI_CHU);
    }

    public static void Str2Double(){
        mDouble_DANG_QIAN_HAI_YOU_MEI = Double.valueOf(mStr_DANG_QIAN_HAI_YOU_MEI).doubleValue();
        mDouble_DANG_QIAN_PING_JUN_CHENG_BEN_JIA = Double.valueOf(mStr_DANG_QIAN_PING_JUN_CHENG_BEN_JIA).doubleValue();
        mDouble_ZONG_GONG_JIN_MEI = Double.valueOf(mStr_ZONG_GONG_JIN_MEI).doubleValue();
        mDouble_GONG_HUA_FEI = Double.valueOf(mStr_GONG_HUA_FEI).doubleValue();
        mDouble_ZONG_GONG_CHU_MEI = Double.valueOf(mStr_ZONG_GONG_CHU_MEI).doubleValue();
        mDouble_GONG_SHOU_DAO = Double.valueOf(mStr_GONG_SHOU_DAO).doubleValue();
        mDouble_QI_TA_ZHI_CHU = Double.valueOf(mStr_QI_TA_ZHI_CHU).doubleValue();

        Log.e(TAG, "getPreferenceStr2double String: " + mStr_DANG_QIAN_HAI_YOU_MEI + " , " + mStr_DANG_QIAN_PING_JUN_CHENG_BEN_JIA + " , " + mStr_ZONG_GONG_JIN_MEI
                + " , " + mStr_GONG_HUA_FEI + " , " + mStr_ZONG_GONG_CHU_MEI  + " , " + mStr_GONG_SHOU_DAO + " , " + mStr_QI_TA_ZHI_CHU);
        Log.e(TAG, "getPreferenceStr2double Double: " + mDouble_DANG_QIAN_HAI_YOU_MEI + " , " + mDouble_DANG_QIAN_PING_JUN_CHENG_BEN_JIA + " , " + mDouble_ZONG_GONG_JIN_MEI
                + " , " + mDouble_GONG_HUA_FEI + " , " + mDouble_ZONG_GONG_CHU_MEI  + " , " + mDouble_GONG_SHOU_DAO + " , " + mDouble_QI_TA_ZHI_CHU);
    }

    /**
     * 计算&存数据
     * @param context
     * @param jinmei
     * @param huafei
     * @param chumei
     * @param shoudao
     * @param zhichu
     */
    public static void setPreferenceDouble2Str(Context context,double jinmei, double huafei,double chumei,double shoudao,double zhichu){

        mDouble_ZONG_GONG_JIN_MEI = mDouble_ZONG_GONG_JIN_MEI + jinmei;   //进煤
        mDouble_GONG_HUA_FEI = mDouble_GONG_HUA_FEI + huafei; //花费
        mDouble_ZONG_GONG_CHU_MEI = mDouble_ZONG_GONG_CHU_MEI + chumei; //出煤
        mDouble_GONG_SHOU_DAO = mDouble_GONG_SHOU_DAO + shoudao; //收到
        mDouble_QI_TA_ZHI_CHU = mDouble_QI_TA_ZHI_CHU + zhichu; //支出
        mDouble_DANG_QIAN_HAI_YOU_MEI = mDouble_ZONG_GONG_JIN_MEI - mDouble_ZONG_GONG_CHU_MEI;
        mDouble_DANG_QIAN_PING_JUN_CHENG_BEN_JIA = mDouble_GONG_HUA_FEI / mDouble_ZONG_GONG_JIN_MEI;

        PreferenceUtil.saveString(context,DANG_QIAN_HAI_YOU_MEI,mDouble_DANG_QIAN_HAI_YOU_MEI+"");
        PreferenceUtil.saveString(context,DANG_QIAN_PING_JUN_CHENG_BEN_JIA,mDouble_DANG_QIAN_PING_JUN_CHENG_BEN_JIA+"");
        PreferenceUtil.saveString(context,ZONG_GONG_JIN_MEI,mDouble_ZONG_GONG_JIN_MEI+"");
        PreferenceUtil.saveString(context,GONG_HUA_FEI,mDouble_GONG_HUA_FEI+"");
        PreferenceUtil.saveString(context,ZONG_GONG_CHU_MEI,mDouble_ZONG_GONG_CHU_MEI+"");
        PreferenceUtil.saveString(context,GONG_SHOU_DAO,mDouble_GONG_SHOU_DAO+"");
        PreferenceUtil.saveString(context,QI_TA_ZHI_CHU,mDouble_QI_TA_ZHI_CHU+"");

    }

    /**
     * 获取时间，并不是网络时间，用获取网络时间的方式需要开启一个线程获取时间，
     * 同时也存在着一种风险就是由于网络问题，获取不到响应的问题。
     * 还有一个重要的问题件就是这个时间的获取会随着手机时区的改变而改变。
     * @return
     */
    public static String getNetworkDate(){
        SimpleDateFormat dff = new SimpleDateFormat("yyyy-MM-dd");
        dff.setTimeZone(TimeZone.getTimeZone("GMT+08"));
        String ee = dff.format(new Date());
        Log.d(TAG, "getNetworkDate: "+ee );
        return ee;
    }
}
