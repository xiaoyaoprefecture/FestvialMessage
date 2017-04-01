package adapter;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.xiaoyaoprefecture.secondday.ChristmasActivity;
import com.xiaoyaoprefecture.secondday.MayDayActivity;
import com.xiaoyaoprefecture.secondday.R;
import com.xiaoyaoprefecture.secondday.SpringFestivalActivity;

import java.util.List;

import bean.Contactsmsg;

import static constant.Constants.contacts;

/**
 * Created by Administrator on 2017/3/30.
 */

public class MyAdapter extends BaseAdapter {
    List<String> list;
    List<String>list_RecyclerView;
    Activity activity;
    private Callback callback;
    String content=null;
    ListView mListView;

    public MyAdapter(List<String> list,Activity activity,List<String>list_RecyclerView,ListView mListView) {
        this.list = list;
        this.activity=activity;
        this.list_RecyclerView=list_RecyclerView;
        this.mListView=mListView;
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

            convertView= LayoutInflater.from(activity).inflate(
                    R.layout.choosemessageitem,null,false);

        }
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
        //备选的短信内容
        TextView tv = (TextView)convertView.findViewById(R.id.mTextView_Choosemessageitem);
        //添加的联系人名单
        RecyclerView mrv= (RecyclerView) convertView.findViewById(R.id.mRecyclerView);
        mrv.setLayoutManager(new GridLayoutManager(activity,4));
        mrv.setAdapter(new MyRecyclerViewAdapter());
        //添加联系人的控件
        Button btn= (Button) convertView.findViewById(R.id.mBtn_Choosemessageitem);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //这里是添加联系人的操作
                Intent intent=new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
                activity.startActivityForResult(intent,contacts);

            }
        });
        content=list.get(position);
        tv.setText(content);

        callback.getdata(content);
        return convertView;
    }
       public interface Callback{
           public void getdata(String content);
       }

    public void Getnumber(Callback callback){
        this.callback=callback;

    }
    /**
     * 自定义RecyclerView的适配器
     */
        public class MyRecyclerViewAdapter extends RecyclerView.Adapter<ViewHolder>{

            @Override
            public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View v=LayoutInflater.from(activity).inflate(R.layout.recyclerview_item,null,false);
                ViewHolder vh=new ViewHolder(v) ;
                return vh;
            }

            @Override
            public void onBindViewHolder(ViewHolder holder, int position) {
                holder.tv_recyclerview.setText(list_RecyclerView.get(position));
            }

            @Override
            public int getItemCount() {
                return list_RecyclerView.size();
            }
        }
    /**
     * 自定义viewholder，找到每个item的界面元素
     */
    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView tv_recyclerview;
        public ViewHolder(View itemView) {
            super(itemView);
            tv_recyclerview= (TextView) itemView.findViewById(R.id.mRecyclerView_TextView);

        }
    }
}
