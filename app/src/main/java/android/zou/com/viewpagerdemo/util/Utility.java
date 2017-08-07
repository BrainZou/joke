package android.zou.com.viewpagerdemo.util;

import android.text.TextUtils;
import android.zou.com.viewpagerdemo.gson.Joke;
import android.zou.com.viewpagerdemo.gson.Jokes;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/8/7.
 */

public class Utility {
    public static Jokes handleJokeResponse(String response){
        if (!TextUtils.isEmpty(response)){
            try {response = response.replaceAll("<br/><br/>","\n");
                Jokes jokes = new Jokes();
                jokes.jokelist = new ArrayList<Joke>();
                JSONArray jsonArray = new JSONArray(response);
                for(int i=0;i<jsonArray.length();i++){
                    JSONObject jokeObject = jsonArray.getJSONObject(i);
                    Joke joke = new Joke();
                    joke.setTitle(jokeObject.getString("title"));
                    joke.setContent(jokeObject.getString("content"));
                    jokes.jokelist.add(joke);
                    //joke.save();
                }
            return jokes;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
