package xxhouyi.cn.zhihu;

/**
 * Created by SEELE on 2018/1/2.
 */

public class Question {
    public int author_image;
    public String author;
    public String title;
    public String content;
    public int count;

    public Question(int id, String author, String title, String content){
        author_image = id;
        this.author = author;
        this.title = title;
        this.content = content;
        count = 0;
    }
}
