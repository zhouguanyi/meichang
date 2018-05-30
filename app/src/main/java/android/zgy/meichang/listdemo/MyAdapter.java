package android.zgy.meichang.listdemo;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.zgy.meichang.R;

public class MyAdapter extends ArrayAdapter {

    private List<MyData>  Datas;
    private LayoutInflater inflater;

    public MyAdapter(Context context, List data) {
        super(context, -1, data);
        inflater = LayoutInflater.from(context);
        Datas = data;
    }


    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder vh = null;

        if(convertView == null){

            convertView = inflater.inflate(R.layout.item, parent, false);
            vh = new ViewHolder();
            vh.tv = (TextView) convertView.findViewById(R.id.text_view);

            convertView.setTag(vh);
        }else{
            vh = (ViewHolder) convertView.getTag();
        }

        vh.tv.setText(Datas.get(position).getTxt());

        return convertView;
    }

    class ViewHolder{

        TextView tv;
    }
}