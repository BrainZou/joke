package android.zou.com.viewpagerdemo;

import android.content.Context;
import android.nfc.Tag;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.zou.com.viewpagerdemo.gson.JokeImg;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by Administrator on 2017/8/8.
 */

public class JokeImgAdapter extends RecyclerView.Adapter<JokeImgAdapter.ViewHolder> {
    public List<JokeImg> jokeImgList;
    private Context mContext;

    static class ViewHolder extends RecyclerView.ViewHolder{

        ImageView jokeItemImg;
        TextView jokeItemTitle;
        public ViewHolder(View itemView) {
            super(itemView);
            jokeItemImg = (ImageView) itemView.findViewById(R.id.joke_item_img);
            jokeItemTitle = (TextView) itemView.findViewById(R.id.joke_item_title);
        }
    }
    public JokeImgAdapter(Context context){
        this.mContext = context;
    }
    public JokeImgAdapter(Context context,List<JokeImg> jokeImgList){
        this.mContext = context;
        this.jokeImgList = jokeImgList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.jokeimg_item,parent,false);
        ViewHolder holder = new ViewHolder(view);
        Log.e("1", "onCreateViewHolder: ");
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        JokeImg jokeimg = jokeImgList.get(position);
        holder.jokeItemTitle.setText(jokeimg.getTitle());
        Glide.with(mContext).load(jokeimg.getImg()).into(holder.jokeItemImg);
    }

    @Override
    public int getItemCount() {
        return jokeImgList==null?0:jokeImgList.size();
    }


}
