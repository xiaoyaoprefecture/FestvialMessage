package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.xiaoyaoprefecture.secondday.R;
import com.xiaoyaoprefecture.secondday.SpringFestivalActivity;

import java.util.List;

/**
 * Created by Administrator on 2017/3/30.
 */

public class MyAdapter extends BaseAdapter {
    List<String> list;
    Context context;
    public MyAdapter(List<String> list,Context context) {
        this.list = list;
        this.context=context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView==null){

            convertView= LayoutInflater.from(context).inflate(
                    R.layout.choosemessageitem,null,false);

        }
        TextView tv = (TextView)convertView.findViewById(R.id.mTextView_Choosemessageitem);
        Button btn= (Button) convertView.findViewById(R.id.mBtn_Choosemessageitem);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //这里是添加联系人的操作
                //Toast.makeText(context,"你点击了我",Toast.LENGTH_LONG).show();
            }
        });
        tv.setText(list.get(position));

        return convertView;
    }

}
