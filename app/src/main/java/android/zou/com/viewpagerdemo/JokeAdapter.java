package android.zou.com.viewpagerdemo;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.zou.com.viewpagerdemo.gson.Joke;

import java.util.List;

/**
 * Created by Administrator on 2017/8/7.
 */

public class JokeAdapter extends ArrayAdapter<Joke> {
    public int resourceId;
    private Context context;

    public JokeAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<Joke> objects) {
        super(context, resource, objects);
        resourceId = resource;
        this.context=context;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        Joke joke = getItem(position);//获取当前项的joke实例
        View view = LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
        TextView title = (TextView) view.findViewById(R.id.joke_title);
        final TextView content = (TextView) view.findViewById(R.id.joke_content);
        Button share = (Button) view.findViewById(R.id.share);
        title.setText(joke.getTitle());
        content.setText(joke.getContent());
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent textIntent = new Intent(Intent.ACTION_SEND);
                textIntent.setType("text/plain");
                textIntent.putExtra(Intent.EXTRA_TEXT,content.getText().toString());
                context.startActivity(Intent.createChooser(textIntent, "分享"));
            }
        });
        return view;
    }



}
