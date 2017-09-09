package android.zou.com.viewpagerdemo.gson;

/**
 * Created by Administrator on 2017/8/8.
 */

public class JokeImg {
    private String img;
    private String title;
    private int width;
    private int height;

    public JokeImg(String img, String title) {
        this.img = img;
        this.title = title;
    }
    public JokeImg(){
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
