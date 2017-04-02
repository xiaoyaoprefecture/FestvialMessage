package fragment;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.widget.CursorAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.xiaoyaoprefecture.secondday.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import bean.SendMsg;
import constant.Constants;
import db.Smsprovider;

import static constant.Constants.Loader_ID;

/**
 * Created by Administrator on 2017/3/27.
 */

public class SecondFragment extends ListFragment {
    private LayoutInflater mInflater;
    private CursorAdapter mCursorAdapter;
    public DateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm");

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mInflater=LayoutInflater.from(getActivity());
        initLoader();
        setupListAdapter();
    }

    private void setupListAdapter() {
        mCursorAdapter=new CursorAdapter(getActivity(),null,false) {
            @Override
            public View newView(Context context, Cursor cursor, ViewGroup parent) {
                View view=mInflater.inflate(R.layout.recorditem,parent,false);
                return view;
            }

            @Override
            public void bindView(View view, Context context, Cursor cursor) {
               TextView msg  = (TextView) view.findViewById(R.id.mTextView);
                TextView contact  = (TextView) view.findViewById(R.id.mTextView1);
                TextView data  = (TextView) view.findViewById(R.id.mTextView2);
                msg.setText(cursor.getString(cursor.getColumnIndex(Constants.COLUMN_MSG)));
                contact.setText(cursor.getString(cursor.getColumnIndex(Constants.COLUMN_NAME)));

                data.setText(cursor.getString(cursor.getColumnIndex(Constants.COLUMN_DATE)));

            }
        };
        setListAdapter(mCursorAdapter);
    }

    /**
     * 初始化loader
     */
    private void initLoader() {
        getLoaderManager().initLoader(Loader_ID, null, new LoaderManager.LoaderCallbacks<Cursor>() {
            @Override
            public Loader<Cursor> onCreateLoader(int id, Bundle args) {
                CursorLoader loader=new CursorLoader(getActivity(), Smsprovider.URI_SMS_ALL,null,null,null,null);

                return loader;
            }

            @Override
            public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
                if (loader.getId()==Loader_ID){
                    mCursorAdapter.swapCursor(data);
                }
            }

            @Override
            public void onLoaderReset(Loader<Cursor> loader) {
                mCursorAdapter.swapCursor(null);
            }
        });
    }
    /* ListView mlistview;
    LayoutInflater minflater;
    SendMsg mSendMsg=new SendMsg();
    public DateFormat df2=new SimpleDateFormat("yyyy-MM-dd HH:mm");
    List<SendMsg>list=new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_second,container,false);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        minflater=LayoutInflater.from(getActivity());
        mlistview= (ListView) view.findViewById(R.id.mlistview);
        initdata();
        mlistview.setAdapter(new ArrayAdapter<SendMsg>(getActivity(),-1,list){
            @NonNull
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
               if (convertView==null){
                   convertView=minflater.inflate(R.layout.recorditem,null,false);

               }
                TextView tv= (TextView) convertView.findViewById(R.id.mTextView);
                TextView tv1= (TextView) convertView.findViewById(R.id.mTextView1);
                TextView tv2= (TextView) convertView.findViewById(R.id.mTextView2);
                if (mSendMsg.getDate()==null){
                    tv.setText("莫得内容的");
                    tv1.setText("莫得联系人的");
                    tv2.setText("莫得时间的");
                }else {
                    Log.e("---------",mSendMsg.toString());
                    tv.setText(list.get(position).getMsg());
                    tv1.setText(list.get(position).getName());
                    Date date = list.get(position).getDate();
                    Log.e("---------",""+date);
                    String s = df2.format(list.get(position).getDate());
                    tv2.setText(s);
                }

                return convertView;
            }
        });
    }

    *//**
 * 初始化数据
 *//*
    private void initdata() {
        list.add(mSendMsg);
    }

*/
}
