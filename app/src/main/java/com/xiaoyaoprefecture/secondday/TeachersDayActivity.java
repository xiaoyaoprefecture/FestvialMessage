package com.xiaoyaoprefecture.secondday;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import adapter.MyAdapter;
import bean.Contactsmsg;

import static constant.Constants.contacts;

/**
 * 教师节短信备选界面
 */
public class TeachersDayActivity extends AppCompatActivity {
    ListView mListview;
    FloatingActionButton fab;
    List<String>list=new ArrayList<>();
    MyAdapter adapter;
    String contactname;
    Contactsmsg msg;
    List<Contactsmsg>list_RecyclerView=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spring_festival);
        init();
    }

    /**
     * 初始化数据
     */
    private void init() {
        findView();
        initData();
        setAdapter();
        setListener();
    }
    /**
     * 设置监听事件
     */
    private void setListener() {
        setfabListener();

    }
    /**
     * 自定义适配器
     */
    public class MyAdapter extends BaseAdapter {
       /* List<String> list;
        Context context;
        public MyAdapter(List<String> list,Context context) {
            this.list = list;
            this.context=context;
        }*/

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

                convertView= LayoutInflater.from(TeachersDayActivity.this).inflate(
                        R.layout.choosemessageitem,null,false);

            }
            //备选的短信内容
            TextView tv = (TextView)convertView.findViewById(R.id.mTextView_Choosemessageitem);
            //添加的联系人名单
            RecyclerView mrv= (RecyclerView) convertView.findViewById(R.id.mRecyclerView);
            mrv.setLayoutManager(new GridLayoutManager(TeachersDayActivity.this,4));
            mrv.setAdapter(new MyRecyclerViewAdapter());
            //添加联系人的控件
            Button btn= (Button) convertView.findViewById(R.id.mBtn_Choosemessageitem);
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //这里是添加联系人的操作
                    Intent intent=new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
                    startActivityForResult(intent,contacts);
                }
            });
            tv.setText(list.get(position));
            return convertView;
        }

    }

    /**
     * 获取返回的数据
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //
        if(requestCode==contacts){
            if (resultCode==RESULT_OK){
                Uri contactUri=data.getData();
                Cursor cursor=managedQuery(contactUri,null,null,null,null);
                cursor.moveToFirst();
                contactname =cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                msg=new Contactsmsg(contactname);
                list_RecyclerView.add(msg);
                adapter.notifyDataSetChanged();
            }
        }
    }
    public class MyRecyclerViewAdapter extends RecyclerView.Adapter<ViewHolder>{

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v=LayoutInflater.from(TeachersDayActivity.this).inflate(R.layout.recyclerview_item,null,false);
           ViewHolder vh=new ViewHolder(v) ;
            return vh;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            Log.e("-----------",""+list_RecyclerView.get(position).getName());
            holder.tv_recyclerview.setText(list_RecyclerView.get(position).getName());
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

    /**
     * 给悬浮控件设置监听事件
     */
    private void setfabListener() {
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProgressDialog dialog=new ProgressDialog(TeachersDayActivity.this);
                dialog.setTitle("正在为您发送请等待");
                dialog.setMessage("Sending");
                dialog.setCancelable(true);
                dialog.show();
            }
        });
    }
    /**
     * 给listview设置适配器
     */
    private void setAdapter() {
        adapter=new MyAdapter();
        mListview.setAdapter(adapter);
    }

    /**
     * 初始化list集合的数据源
     */
    private void initData() {
        String []s={"难忘的校园时光，有着难忘的师生情怀；坦诚的学生时代，有着您孜孜不倦的教诲；" +
                "时隔多年，记忆犹新，您，依然是我最尊敬的人，祝老师节日快乐！" ,
                "当幼苗已变成参天大树，当学生已不需要您地扶持，当我们把成功紧握手中，时间已将您的青春带走" +
                        "，岁月爬上了您的额头，我只能远远地眺望，惟有泪千行。教师节到了，" +
                        "请收下学生诚挚地祝福，愿您身体康健，永远幸福吉祥！"
                ,"亲爱的老师，向您表达我最衷心的感谢。在人生旅途上，您为我点燃了希望之光，" +
                "您所做的一切润泽了我的心灵，开阔了我的视野。值此教师节之际，我向您致以崇高的敬意!"
                };
        list.addAll(Arrays.asList(s));
        Log.e("-----------",""+list.size());
    }

    /**
     * 找控件
     */
    private void findView() {
        mListview= (ListView) findViewById(R.id.mListView);
        fab= (FloatingActionButton) findViewById(R.id.mSendMessage);
    }
}
