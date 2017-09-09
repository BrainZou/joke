package android.zou.com.viewpagerdemo;


import android.content.SharedPreferences;
import android.hardware.Camera;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.zou.com.viewpagerdemo.gson.JokeImg;
import android.zou.com.viewpagerdemo.gson.JokeImgs;
import android.zou.com.viewpagerdemo.gson.Jokes;
import android.zou.com.viewpagerdemo.util.HttpUtil;
import android.zou.com.viewpagerdemo.util.Utility;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragment2#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment2 extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private JokeImgs jokeImgs;
    private RecyclerView recyclerView;
    private StaggeredGridLayoutManager layoutManager;
    private SwipeRefreshLayout srlImg;
    private JokeImgAdapter adapter;




    public Fragment2() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Fragment1.
     */
    // TODO: Rename and change types and number of parameters
    public static Fragment2 newInstance(String param1, String param2) {
        Fragment2 fragment = new Fragment2();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.pager2,container,false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        layoutManager = new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL);
/*
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                layoutManager.invalidateSpanAssignments();
            }
        });*/

        layoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);//解决跳到其他列
        recyclerView.setLayoutManager(layoutManager);

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getContext());
        String jokeImgString = prefs.getString("jokeimgs",null);
        adapter = new JokeImgAdapter(getContext());

        if (jokeImgString != null){
            jokeImgs = Utility.handleJokeImgResponse(jokeImgString);
            showJokeImg(jokeImgs);
        }else {
            requetJokeImg();
        }

        recyclerView.setAdapter(adapter);

        srlImg = (SwipeRefreshLayout)view.findViewById(R.id.srlimg);
        srlImg.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                srlImg.setRefreshing(true);
               /* handler  = new android.os.Handler().post(new Runnable() {
                    @Override
                    public void run() {
                        requetJokeImg();
                        srlImg.setRefreshing(false);

                   }

                });*/
                requetJokeImg();
                srlImg.setRefreshing(false);

            }

        });
        return view;
    }
    //发送请求
    private void requetJokeImg(){
        String url = "http://api.laifudao.com/open/tupian.json";
        HttpUtil.sendOkHttpRequest(url, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getContext(),"获取信息失败",Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String responseText = response.body().string();
                final JokeImgs jokeImgs = Utility.handleJokeImgResponse(responseText);

                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (jokeImgs!=null){
                            SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(getContext()).edit();
                            editor.putString("jokeimgs",responseText);
                            editor.apply();
                            showJokeImg(jokeImgs);
                        }else {
                            Toast.makeText(getContext(),"获取信息失败",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });


    }
    //显示
    private void showJokeImg(JokeImgs jokeImgs){
        adapter.jokeImgList=jokeImgs.jokeimglist;
        adapter.notifyDataSetChanged();
    }

}
