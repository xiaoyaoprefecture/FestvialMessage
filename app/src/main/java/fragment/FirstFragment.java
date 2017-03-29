package fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.Switch;
import android.widget.TextView;

import com.xiaoyaoprefecture.secondday.ChristmasActivity;
import com.xiaoyaoprefecture.secondday.DragonBoatFestivalActivity;
import com.xiaoyaoprefecture.secondday.LanternFestivalActivity;
import com.xiaoyaoprefecture.secondday.MayDayActivity;
import com.xiaoyaoprefecture.secondday.MidautumnfestivalActivity;
import com.xiaoyaoprefecture.secondday.NationalDayActivity;
import com.xiaoyaoprefecture.secondday.R;
import com.xiaoyaoprefecture.secondday.SpringFestivalActivity;
import com.xiaoyaoprefecture.secondday.TanabatafestivalActivity;
import com.xiaoyaoprefecture.secondday.TeachersDayActivity;
import com.xiaoyaoprefecture.secondday.TombsweepingDayActivity;

import java.util.ArrayList;
import java.util.List;

import bean.festivalbean;
import bean.festivallab;

/**
 * Created by Administrator on 2017/3/19.
 * 这个是Tablayout对应的碎片
 */

public class FirstFragment extends Fragment {
    GridView mGridview;
    ArrayAdapter<festivalbean>mAdater;
    LayoutInflater minflater;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_first,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        mGridview= (GridView) view.findViewById(R.id.mGridView);
        minflater=LayoutInflater.from(getActivity());
        mGridview.setAdapter(mAdater=new ArrayAdapter<festivalbean>(getActivity(),-1,
                festivallab.getmInstance().getfestival()){
            @NonNull
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                if (convertView==null){
                    convertView=minflater.inflate(R.layout.festivalitem,parent,false);

                }
                TextView tv= (TextView) convertView.findViewById(R.id.mtextView);
                tv.setText(getItem(position).getName());
                return convertView;
            }
        });
        mGridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //点击节日跳转到节日信息备选的界面
               // Log.e("-------",""+position);
                switch (position){
                    case 0:
                        Intent intent=new Intent(getActivity(), SpringFestivalActivity.class);
                        startActivity(intent);
                        break;
                    case 1:
                        Intent intent1=new Intent(getActivity(), TombsweepingDayActivity.class);
                        startActivity(intent1);
                        break;
                    case 2:
                        Intent intent2=new Intent(getActivity(), MayDayActivity.class);
                        startActivity(intent2);
                        break;
                    case 3:
                        Intent intent3=new Intent(getActivity(), DragonBoatFestivalActivity.class);
                        startActivity(intent3);
                        break;
                    case 4:
                        Intent intent4=new Intent(getActivity(), TanabatafestivalActivity.class);
                        startActivity(intent4);
                        break;
                    case 5:
                        Intent intent5=new Intent(getActivity(), TeachersDayActivity.class);
                        startActivity(intent5);
                        break;
                    case 6:
                        Intent intent6=new Intent(getActivity(), NationalDayActivity.class);
                        startActivity(intent6);
                        break;
                    case 7:
                        Intent intent7=new Intent(getActivity(), LanternFestivalActivity.class);
                        startActivity(intent7);
                        break;
                    case 8:
                        Intent intent8=new Intent(getActivity(), ChristmasActivity.class);
                        startActivity(intent8);
                        break;
                    case 9:
                        Intent intent9=new Intent(getActivity(), MidautumnfestivalActivity.class);
                        startActivity(intent9);
                        break;
                }
            }
        });
    }
}
