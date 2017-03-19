package com.xiaoyaoprefecture.secondday;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import fragment.FirstFragment;

/**
 * 玩一玩节日短信
 */
public class MainActivity extends AppCompatActivity {
    TabLayout mTabLayout;
    ViewPager mViewPager;
    String []Title=new String []{"节日短信","发送记录","爱的回忆"};//tablayout的内容
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    /**
     * 初始化
     */
    private void init() {
        findView();
        ViewPagerSetAdapter();
        //将viewpager与tablayout关联起来
        mTabLayout.setupWithViewPager(mViewPager);
    }

    /**
     * 给ViewPager设置适配器
     */
    private void ViewPagerSetAdapter() {
        mViewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return new FirstFragment();
            }

            @Override
            public int getCount() {
                return Title.length;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return Title[position];
            }
        });

    }

    /**
     * 找控件
     */
    private void findView() {
        mTabLayout= (TabLayout) findViewById(R.id.mTabLayout);
        mViewPager= (ViewPager) findViewById(R.id.mViewPager);
    }
}
