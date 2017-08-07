package android.zou.com.viewpagerdemo;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.zou.com.viewpagerdemo.gson.Joke;

import java.util.List;

/**
 * Created by Administrator on 2017/8/7.
 */

public class JokeAdapter extends ArrayAdapter<Joke> {
    public int resourceId;

    public JokeAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<Joke> objects) {
        super(context, resource, objects);
        resourceId = resource;
    }

        public View getView(int position, View convertView, ViewGroup parent) {
            Joke joke = getItem(position);
            View view = LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
            TextView title = (TextView) view.findViewById(R.id.joke_title);
            TextView content = (TextView) view.findViewById(R.id.joke_content);
            title.setText(joke.getTitle());
            content.setText(joke.getContent());
            return view;
        }

}
