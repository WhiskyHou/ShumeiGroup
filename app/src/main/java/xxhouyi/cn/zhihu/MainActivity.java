package xxhouyi.cn.zhihu;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;


public class MainActivity extends AppCompatActivity {
    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler(){
        public void handleMessage(Message msg){
            switch (msg.what){
                case -1:
                    Toast.makeText(MainActivity.this, msg.obj.toString(), Toast.LENGTH_SHORT).show();
                case 1:
                    Toast.makeText(MainActivity.this, msg.obj.toString(), Toast.LENGTH_SHORT).show();
                    if (msg.obj.toString().equals("login success")){
//                        Intent intent = new Intent(MainActivity.this, LaunchActivity.class);
//                        startActivity(intent);
                        saveLogin();
                    }
            }
        }
    };

    String s1;
    String s2;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT)
            setContentView(R.layout.activity_main);
        else
            setContentView(R.layout.activity_main_heng);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // 设置界面背景
        WebView background = (WebView) findViewById(R.id.web_background);
        WebSettings webSettings = background.getSettings();
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setUseWideViewPort(true);
        webSettings.setJavaScriptEnabled(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        background.setBackgroundColor(Color.TRANSPARENT);
        background.loadUrl("http://r6.xxhouyi.cn/r/login.html");

        // 控件
        final EditText text_name = (EditText) findViewById(R.id.text_name);
        final EditText text_pass = (EditText) findViewById(R.id.text_pass);

        // 登录按钮
        Button signin = (Button) findViewById(R.id.signin);
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                s1 = text_name.getText().toString();
                s2 = text_pass.getText().toString();
                String[] name = {"name", "pass"};
                String[] content = {text_name.getText().toString(), text_pass.getText().toString()};
                String url = "http://r6.xxhouyi.cn/login.php";
                Network.postStringWithOkhttp(name, content, url, handler);
            }
        });

        // 注册按钮
        Button signup = (Button) findViewById(R.id.signup);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SignupActivity.class);
                startActivity(intent);
            }
        });
    }

    private void saveLogin(){
        User user = new User(s1, s2);
        String path= Environment.getExternalStorageDirectory().getAbsolutePath()+"/userinfo.dat";
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(new FileOutputStream(path));
            oos.writeObject(user);
            Log.d("============================", "saveLogin: ===================save");
        } catch (Exception e) {
            Log.d("============================", "saveLogin: ===================Error");
            e.printStackTrace();
        }finally{
            try {
                if (oos!=null) {
                    oos.close();
                }
            } catch (IOException e) {
            }

        }
        finish();

    }

}
