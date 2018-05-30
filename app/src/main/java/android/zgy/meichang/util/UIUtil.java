package android.zgy.meichang.util;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;


/**
 *
 */
public class UIUtil {

    /**
     * Material 风格的 Dialog 控件  >AlertDialog.Builder builder
     */
    public static void showConfirmDialog(Context context,int titleResId ,int msgResId, int positiveBtnResId, DialogInterface.OnClickListener
            positiveBtnListener, int negativeBtnResId, DialogInterface.OnClickListener negativeBtnListener) {
        showConfirmDialog(context,context.getString(titleResId), context.getString(msgResId), context.getString(positiveBtnResId),
                positiveBtnListener, context.getString(negativeBtnResId), negativeBtnListener);
    }
    public static void showConfirmDialog(Context context,String title, String msg, String positiveBtnTxt, DialogInterface.OnClickListener
            positiveBtnListener, String negativeBtnTxt, DialogInterface.OnClickListener negativeBtnListener) {
        android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(context);
        if(title != null){
            builder.setTitle(title);
        }
        if(msg != null){
            builder.setMessage(msg);
        }
        builder.setPositiveButton(positiveBtnTxt, positiveBtnListener);
        builder.setCancelable(false);
        if (negativeBtnTxt != null && negativeBtnListener != null){
            builder.setNegativeButton(negativeBtnTxt, negativeBtnListener);
        }
        builder.create().show();
    }

    /**
     * Material 风格的 Dialog 控件  >AlertDialog dialog
     * 可以改变btn的颜色
     */
    public static void showDialog(Context context, int titleResId, int msgResId, int positiveBtnTxtResId, DialogInterface.OnClickListener
            positiveBtnListener, int negativeBtnTxtResId, DialogInterface.OnClickListener negativeBtnListener, int posiviveBtnColor,int negativeBtnColor){
        showDialog(context,context.getString(titleResId),context.getString(msgResId),context.getString(positiveBtnTxtResId),positiveBtnListener,
                context.getString(negativeBtnTxtResId),negativeBtnListener,posiviveBtnColor,negativeBtnColor);
    }
    public static void showDialog(Context context, String title, String msg, String positiveBtnTxt, DialogInterface.OnClickListener
            positiveBtnListener, String negativeBtnTxt, DialogInterface.OnClickListener negativeBtnListener, int posiviveBtnColor,int negativeBtnColor){
        final AlertDialog dialog = new AlertDialog.Builder(context).create();
        dialog.setCancelable(false);
        if(title != null){
            dialog.setTitle(title);
        }
        if(msg != null){
            dialog.setMessage(msg);
        }
        dialog.setButton(DialogInterface.BUTTON_NEGATIVE,positiveBtnTxt, positiveBtnListener);
        if (negativeBtnTxt != null && negativeBtnListener != null){
            dialog.setButton(DialogInterface.BUTTON_POSITIVE,negativeBtnTxt, negativeBtnListener);
        }
        dialog.show();
        if(posiviveBtnColor != 0){
            Button posiviveButton = dialog.getButton(DialogInterface.BUTTON_POSITIVE);
            posiviveButton.setTextColor(posiviveBtnColor);
        }
        if(negativeBtnColor != 0){
            Button negativeButton = dialog.getButton(DialogInterface.BUTTON_NEGATIVE);
            negativeButton.setTextColor(negativeBtnColor);
        }
    }

    /**
     * 状态栏相关工具类
     *
     */
        public static void setWindowStatusBarColor(Activity activity, int colorResId) {
            try {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    Window window = activity.getWindow();
                    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                    window.setStatusBarColor(activity.getResources().getColor(colorResId));

                    //底部导航栏
                    //window.setNavigationBarColor(activity.getResources().getColor(colorResId));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public static void setWindowStatusBarColor(Dialog dialog, int colorResId) {
            try {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    Window window = dialog.getWindow();
                    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                    window.setStatusBarColor(dialog.getContext().getResources().getColor(colorResId));

                    //底部导航栏
                    //window.setNavigationBarColor(activity.getResources().getColor(colorResId));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


}
