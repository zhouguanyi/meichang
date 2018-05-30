package android.zgy.meichang.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class PreferenceUtil {

    public static void saveStringMap(Context context, Map<String, String> map){
        SharedPreferences preference = PreferenceManager.getDefaultSharedPreferences(context);
        Editor editor = preference.edit();
        Iterator<Map.Entry<String, String>> entryIt = map.entrySet().iterator();
        while(entryIt.hasNext()){
        	Map.Entry<String, String> entry = entryIt.next();
        	editor.putString(entry.getKey(), entry.getValue());
        }
        editor.commit();
    }

    public static void saveString(Context context, String key, String value){
        SharedPreferences preference = PreferenceManager.getDefaultSharedPreferences(context);
        Editor editor = preference.edit();
        editor.putString(key, value);
        editor.commit();
    }

    public static String getString(Context context, String key, String defValue){
        SharedPreferences preference = PreferenceManager.getDefaultSharedPreferences(context);
        return preference.getString(key, defValue);
    }
    
    public static void removeKey(Context context, String key){
        SharedPreferences preference = PreferenceManager.getDefaultSharedPreferences(context);
        Editor editor = preference.edit();
        editor.remove(key);
        editor.commit();
    }

    public static void saveInt(Context context, String key, int value){
        SharedPreferences preference = PreferenceManager.getDefaultSharedPreferences(context);
        Editor editor = preference.edit();
        editor.putInt(key, value);
        editor.commit();
    }

    public static int getInt(Context context, String key, int defValue){
        SharedPreferences preference = PreferenceManager.getDefaultSharedPreferences(context);
        return preference.getInt(key, defValue);
    }

    public static void saveBoolean(Context context, String key, boolean value){
        SharedPreferences preference = PreferenceManager.getDefaultSharedPreferences(context);
        Editor editor = preference.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }

    public static boolean getBoolean(Context context, String key, boolean defValue){
        SharedPreferences preference = PreferenceManager.getDefaultSharedPreferences(context);
        return preference.getBoolean(key, defValue);
    }

    public static boolean saveArray(Context context, String key, List<String> list) {
        SharedPreferences sp = context.getSharedPreferences(key, context.MODE_PRIVATE);
        SharedPreferences.Editor mEdit1= sp.edit();
        mEdit1.putInt("Status_size",list.size());

        for(int i=0;i<list.size();i++) {
            mEdit1.remove("Status_" + i);
            mEdit1.putString("Status_" + i, list.get(i));
        }
        return mEdit1.commit();
    }

    public static void loadArray(Context context, String key, List<String> list) {

        SharedPreferences mSharedPreference1 = context.getSharedPreferences(key, context.MODE_PRIVATE);
        list.clear();
        int size = mSharedPreference1.getInt("Status_size", 0);
        for(int i=0;i<size;i++) {
            list.add(mSharedPreference1.getString("Status_" + i, null));

        }
    }

}
