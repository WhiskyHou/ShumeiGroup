package xxhouyi.cn.zhihu;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignupActivity extends AppCompatActivity {
    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler(){
        public void handleMessage(Message msg){
            switch (msg.what){
                case -1:
                    Toast.makeText(SignupActivity.this, msg.obj.toString(), Toast.LENGTH_SHORT).show();
                    break;
                case 1:
                    Toast.makeText(SignupActivity.this, msg.obj.toString(), Toast.LENGTH_SHORT).show();
                    if (msg.obj.toString().equals("sign up successed"))
                        finish();
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        final EditText nameText = (EditText) findViewById(R.id.text_name_su);
        final EditText passText = (EditText) findViewById(R.id.text_pass_su);
        final EditText typeText = (EditText) findViewById(R.id.text_retype_su);
        Button signup = (Button) findViewById(R.id.signup_su);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = nameText.getText().toString();
                String pass = passText.getText().toString();
                String type = typeText.getText().toString();
                if (!pass.equals(type)){
                    Toast.makeText(SignupActivity.this, "输入密码不一致", Toast.LENGTH_SHORT).show();
                    return;
                }else if (!checkEmaile(name)){
                    Toast.makeText(SignupActivity.this, "邮箱格式不正确", Toast.LENGTH_SHORT).show();
                    return;
                }else if (pass.equals("")){
                    Toast.makeText(SignupActivity.this, "密码不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }else {
                    String[] n = {"name", "pass"};
                    String[] c = {name, pass};
                    String url = "http://r6.xxhouyi.cn/signup.php";
                    Network.postStringWithOkhttp(n, c, url, handler);
                }
            }
        });
    }

    private static boolean checkEmaile(String emaile){
        String RULE_EMAIL = "^\\w+((-\\w+)|(\\.\\w+))*\\@[A-Za-z0-9]+((\\.|-)[A-Za-z0-9]+)*\\.[A-Za-z0-9]+$";
        Pattern p = Pattern.compile(RULE_EMAIL);
        Matcher m = p.matcher(emaile);
        return m.matches();
    }
}
