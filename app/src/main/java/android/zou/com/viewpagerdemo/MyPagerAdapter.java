package android.zou.com.viewpagerdemo;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Administrator on 2017/8/6.


public class MyPagerAdapter extends PagerAdapter {

    private List<View> pagerlist;

    public MyPagerAdapter (List<View> pagerlist){
        this.pagerlist = pagerlist;
    }


    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        //return super.instantiateItem(container, position);
        container.addView(pagerlist.get(position));
        return pagerlist.get(position);
    }
    @Override
    public int getCount() {
        return pagerlist.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
//        super.destroyItem(container, position, object);
        container.removeView(pagerlist.get(position));
    }
}
 */