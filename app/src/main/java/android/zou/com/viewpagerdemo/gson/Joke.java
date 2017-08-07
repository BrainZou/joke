package android.zou.com.viewpagerdemo.gson;

/**
 * Created by Administrator on 2017/8/7.
 */

public class Joke {
    private String title;
    private String content;

    public Joke(String title,String content){
        this.title = title;
        this.content = content;
    }
    public Joke(){
    }

    public String getTitle() {
        return title;
    }


    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


}
