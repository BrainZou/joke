package android.zou.com.viewpagerdemo;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.astuetz.PagerSlidingTabStrip;

import java.util.ArrayList;
import java.util.logging.Handler;

public class MainActivity extends AppCompatActivity {
    private MyPagerAdapter myPagerAdapter;
    private ViewPager viewPager;
    private View pager1,pager2,pager3;
    private ArrayList<View> pagerList;

    private MyFragmentPagerAdapter myFragmentPagerAdapter;
    private ArrayList<Fragment> fragments;
    private Fragment fragment1;
    private Fragment fragment2;
    private Fragment fragment3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initFragment();

    }
    private  void initFragment(){
        fragment1 = new Fragment1();
        fragment3 = new Fragment2();
        fragment2 = new Fragment3();
        fragments = new ArrayList<Fragment>();
        fragments.add(fragment1);
        fragments.add(fragment2);
        fragments.add(fragment3);
        viewPager = (ViewPager) findViewById(R.id.myViewPager);
        myFragmentPagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager(),fragments);
        viewPager.setAdapter(myFragmentPagerAdapter);

        PagerSlidingTabStrip tabs = (PagerSlidingTabStrip) findViewById(R.id.tabs);
        tabs.setViewPager(viewPager);
    }


//    private void initView() {
//
//        viewPager = (ViewPager) findViewById(R.id.myViewPager);
//        LayoutInflater inflate = getLayoutInflater();
//        pager1 = inflate.inflate(R.layout.pager1,null);
//        pager2 = inflate.inflate(R.layout.pager2,null);
//        pager3 = inflate.inflate(R.layout.pager3,null);
//        pagerList = new ArrayList<View>();
//
//        pagerList.add(pager1);
//        pagerList.add(pager2);
//        pagerList.add(pager3);
//
//        myPagerAdapter = new MyPagerAdapter(pagerList);
//
//        viewPager.setAdapter(myPagerAdapter);
//    }
}
