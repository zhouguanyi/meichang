package android.zgy.meichang;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.zgy.meichang.list.OrderListAdapter;
import android.zgy.meichang.listdemo.MyListView;
import android.zgy.meichang.mySQLite.ChuMeiOrderDao;
import android.zgy.meichang.mySQLite.ZhiChuOrderDao;
import android.zgy.meichang.mySQLite.myOrder;
import android.zgy.meichang.util.CustomToast;
import android.zgy.meichang.util.UIUtil;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import static android.zgy.meichang.util.DateUtil.Str2Double;
import static android.zgy.meichang.util.DateUtil.getNetworkDate;
import static android.zgy.meichang.util.DateUtil.getPreferenceStr;
import static android.zgy.meichang.util.DateUtil.setPreferenceDouble2Str;

/**
 * Created by ajf-dell on 2017/4/12.
 */

public class ZhiChuActivity extends Activity implements MyListView.ILoadListener {
    private static final String TAG = "ZhiChuActivity";

    private MyListView showDateListView;
    private List<myOrder> mDatas = new ArrayList<myOrder>();
    private List<myOrder> mDatasAdd = new ArrayList<myOrder>();
    private List<myOrder> mDatasAllSize = new ArrayList<myOrder>();
    private List<myOrder> mDatasAddBody = new ArrayList<myOrder>();
    private static int mDatasAddBodySize;

    private EditText mEditTxtZhuChu;
    private EditText mEditTxtYongTu;
    private Button mBtnTianJia;
    private String mStrZhiChu, mStrYongTu;
    private double mDoubleZhiChu;
    private String mDialogMsg;

    private ZhiChuOrderDao ZhiChuOrdersDao;
    //private List<myOrder> orderList;
    private OrderListAdapter adapter;

    private static int mListNumber ; //列表长度
    private static int mListNumberAdd ; //列表每次增加的量

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhichu);

        mEditTxtZhuChu = (EditText) findViewById(R.id.id_edit_text_zhichu);
        mEditTxtYongTu = (EditText) findViewById(R.id.id_edit_text_yongtu);
        mBtnTianJia = (Button) findViewById(R.id.id_button_tianjia);

        showDateListView = (MyListView) findViewById(R.id.list_view);//list
        showDateListView.setOnILoadListener(this);

        ZhiChuOrdersDao = new ZhiChuOrderDao(this);//SQLite
        if (! ZhiChuOrdersDao.isDataExist()){
            ZhiChuOrdersDao.initTable();
        }

        mListNumber = 0;
        mListNumberAdd = 10;

        mDatas = getLoadData0(mListNumber,mListNumberAdd);

        if (mDatas != null){
            adapter = new OrderListAdapter(ZhiChuActivity.this, mDatas);
            showDateListView.setAdapter(adapter);
        }

        addListener(ZhiChuActivity.this);
    }

    private void addListener(final Context context){

        mBtnTianJia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if("".equals(mEditTxtZhuChu.getText().toString())||"".equals(mEditTxtYongTu.getText().toString())){
                    CustomToast.showToast(context,"请检查输入信息，支出和用途不能为空",7000);
                    return;
                }else {
                    mStrZhiChu = mEditTxtZhuChu.getText().toString();
                    mStrYongTu = mEditTxtYongTu.getText().toString();
                    mDoubleZhiChu = Double.valueOf(mStrZhiChu).doubleValue();
                    mDialogMsg =  mStrYongTu + "，支出" + mStrZhiChu + "元";
                }
                UIUtil.showDialog(context, "提示", mDialogMsg , "确定添加",new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();

                                //取数据
                                getPreferenceStr(context);
                                Str2Double();
                                //计算&存数据
                                setPreferenceDouble2Str(context, 0, 0,0,0,mDoubleZhiChu);
                                //存数据库
                                ZhiChuOrdersDao.insertDate(mDialogMsg,
                                        "",
                                        getNetworkDate());
                                refreshOrderList();
                                mEditTxtZhuChu.setText("");
                                mEditTxtYongTu.setText("");
                                CustomToast.showToast(context,"添加成功",7000);
                            }
                        }, "取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        },getResources().getColor(R.color.new_red),
                        getResources().getColor(R.color.new_blue));
            }
        });
    }


    private void refreshOrderList(){
        mListNumber = 0;
        mListNumberAdd = 10;
        mDatas = ZhiChuOrdersDao.getPartDate(0,10);

        Log.e(TAG, "refreshOrderList: " + mDatas);

        adapter.clear();

        adapter.addList(mDatas);

        adapter.notifyDataSetChanged();

        mDatasAllSize = ZhiChuOrdersDao.getAllDate();
        int listSize = mDatasAllSize.size();

        if(mListNumber+mListNumberAdd <= listSize){
            mListNumber = mListNumber+mListNumberAdd;
        }else {
            mListNumber = listSize;
        }

    }


    /**
     * 该方法提供模拟的加载数据
     */
    private void getLoadData(int begin ,int add) {

        mDatasAddBody = getLoadData0(begin,add);
        mDatasAddBodySize = mDatasAddBody.size();
        Log.e(TAG, "==getLoadData mDatasAddBody size(): " + mDatasAddBody.size());
        if (mDatasAddBody != null){
            for (int i = 0; i < mDatasAddBody.size(); i++) {

                myOrder md = new myOrder(mDatasAddBody.get(i).id,
                        mDatasAddBody.get(i).content,
                        mDatasAddBody.get(i).danjia,
                        mDatasAddBody.get(i).date);
                mDatas.add(md);
            }
        }else {
            CustomToast.showToast(ZhiChuActivity.this,"没有更多了",7000);
        }

    }

    private List<myOrder> getLoadData0(int begin ,int add) {
        Log.e(TAG, "getLoadData01111: " + "begin="+begin+ " ,add="+add+" ,mListNumber="+mListNumber  + " ,mListNumberAdd="+mListNumberAdd);
        mDatasAllSize = ZhiChuOrdersDao.getAllDate();
        int listSize = mDatasAllSize.size();
        Log.e(TAG, "getLoadData0 mDatasAllSize="+ listSize);

        if(begin+add <= listSize){
            mListNumber = begin+add;
        }else {
            mListNumber = listSize;
        }

        mDatasAdd = ZhiChuOrdersDao.getPartDate(begin,mListNumber);

        Log.e(TAG, "getLoadData02222: " + "begin="+begin+ " ,add="+add+" ,mListNumber="+mListNumber  + " ,mListNumberAdd="+mListNumberAdd);

        return mDatasAdd;
    }


    //重写回调方法
    public void loadData() {

        //注意之所以使用Handlder，主要是想让下面的
        //操作延迟5秒钟，以体现效果。实际开发中不需要
        Handler mHandler = new Handler();
        mHandler.postDelayed(new Runnable() {

            public void run() {

                //获得加载数据
                Log.e(TAG, "loadData(): "+"mListNumber="+mListNumber  + " ,mListNumberAdd="+mListNumberAdd);
                getLoadData(mListNumber,mListNumberAdd);
                //然后通知MyListView刷新界面
                adapter.notifyDataSetChanged();

                //然后通知加载数据已经完成了
                showDateListView.loadFinish(mDatasAddBodySize);
            }

        }, 900);


    }
}
