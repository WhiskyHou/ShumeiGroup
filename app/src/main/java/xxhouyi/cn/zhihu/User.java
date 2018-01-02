package xxhouyi.cn.zhihu;

import java.io.Serializable;

/**
 * Created by SEELE on 2018/1/2.
 */

public class User implements Serializable {
    public int id;
    public String mail;
    public String pass;
    public String name;
    public String phone;
    public String introduce;
    public String school;

    public User(String mail, String pass){
        id = -1;
        this.mail = mail;
        this.pass = pass;
        name = "NULL";
        phone = "";
        introduce = "introduction";
        school = "NULL";
    }
}
