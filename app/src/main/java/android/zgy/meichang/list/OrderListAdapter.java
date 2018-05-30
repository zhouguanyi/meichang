package android.zgy.meichang.list;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.zgy.meichang.R;
import android.zgy.meichang.mySQLite.myOrder;


import java.util.List;

/**
 * Created by Jne
 * Date: 2015/1/11.
 */
public class OrderListAdapter extends BaseAdapter {
    private Context context;
    private List<myOrder> orderList;

    public OrderListAdapter(Context context, List<myOrder> orderList) {
        this.context = context;
        this.orderList = orderList;
    }

    @Override
    public int getCount() {
        if(0 == orderList.size()){
            return 0;
        }
        return orderList.size();
    }

    @Override
    public Object getItem(int position) {
        return orderList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        myOrder order = orderList.get(position);
        if (order == null){
            return null;
        }

        ViewHolder holder = null;
        if (view != null){
            holder = (ViewHolder) view.getTag();
        }else {
            view = LayoutInflater.from(context).inflate(R.layout.show_sql_item, null);

            holder = new ViewHolder();
//            holder.mTextViewId = (TextView) view.findViewById(R.id.id_list_text_id);
            holder.mTextViewContent = (TextView) view.findViewById(R.id.id_list_text_content);
            holder.mTextViewDanJia = (TextView) view.findViewById(R.id.id_list_text_danjia);
            holder.mTextViewDate = (TextView) view.findViewById(R.id.id_list_text_date);

            view.setTag(holder);
        }

//        holder.mTextViewId.setText(order.id);
        holder.mTextViewContent.setText(order.content);
        holder.mTextViewDanJia.setText(order.danjia);
        holder.mTextViewDate.setText(order.date);

        return view;
    }

    @Override
    public boolean isEnabled(int position) {
        return true;
    }


    public static class ViewHolder{
//        public TextView mTextViewId;
        public TextView mTextViewContent;
        public TextView mTextViewDanJia;
        public TextView mTextViewDate;
    }
    /**
     * 添加列表项
     * @param item
     */
    public void addItem(myOrder item) {
        orderList.add(0,item);
    }

    public void addList(List<myOrder> list){
        orderList = list;
    }

    /**
     * 清空列表
     */
    public void clear(){
        orderList.clear();
    }
}
