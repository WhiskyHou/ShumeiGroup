package xxhouyi.cn.zhihu;

import java.io.Serializable;

/**
 * Created by SEELE on 2018/1/2.
 */

public class Question implements Serializable {
    public int id;
    public int author_image;
    public String author;
    public String title;
    public String content;
    public int count;

    public Question(int id, int pic, String author, String title, String content){
        this.id = id;
        author_image = pic;
        this.author = author;
        this.title = title;
        this.content = content;
        count = 0;
    }
}
