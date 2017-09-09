package android.zou.com.viewpagerdemo;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.zou.com.viewpagerdemo.gson.Joke;
import android.zou.com.viewpagerdemo.gson.Jokes;
import android.zou.com.viewpagerdemo.util.HttpUtil;
import android.zou.com.viewpagerdemo.util.Utility;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragment1#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment1 extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private List<Joke> jokelist = new ArrayList<Joke>();
    private SwipeRefreshLayout srl;
    private boolean handler;
    private LinearLayout list_view;




    public Fragment1() {
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
    public static Fragment1 newInstance(String param1, String param2) {
        Fragment1 fragment = new Fragment1();
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

    private void showJokeInfo(Jokes jokes) {
        for(Joke joke : jokes.jokelist){
            View view = LayoutInflater.from(getContext()).inflate(R.layout.joke_item,list_view,false);
            TextView content = (TextView) view.findViewById(R.id.joke_content);
            TextView title = (TextView) view.findViewById(R.id.joke_title);
            content.setText(joke.getContent());
            title.setText(joke.getTitle());
            list_view.addView(view);
            // init();
        }
        list_view.setVisibility(View.VISIBLE);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.pager1,container,false);
       list_view = (LinearLayout) view.findViewById(R.id.list_view);

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getContext());
        String jokeString = prefs.getString("jokes",null);
        list_view.setVisibility(View.INVISIBLE);
        list_view.removeAllViews();
        if (jokeString != null){
            Jokes jokes = Utility.handleJokeResponse(jokeString);
            showJokeInfo(jokes);
        }else {
            requetJoke();
        }

        srl = (SwipeRefreshLayout)view.findViewById(R.id.srl);
        srl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                srl.setRefreshing(true);
               /* handler  = new android.os.Handler().post(new Runnable() {
                    @Override
                    public void run() {
                        list_view.removeAllViews();
                        requetJoke();
                        srl.setRefreshing(false);
                    }
                });*/

                list_view.removeAllViews();
                requetJoke();
                srl.setRefreshing(false);
            }
        });
        return view;
    }



    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    private void requetJoke(){
        String url = "http://api.laifudao.com/open/xiaohua.json";
        //String url = "http://api.laifudao.com/open/tupian.json";
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
                final Jokes jokes = Utility.handleJokeResponse(responseText);

                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (jokes!=null){
                            SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(getContext()).edit();
                            editor.putString("jokes",responseText);
                            editor.apply();
                            showJokeInfo(jokes);
                        }else {
                            Toast.makeText(getContext(),"获取信息失败",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

    }




}
