package xxhouyi.cn.zhihu;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Locale;

public class LaunchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


        String path= Environment.getExternalStorageDirectory().getAbsolutePath()+"/userinfo.dat";
        ObjectInputStream ois = null;
        File file = new File(path);
        if (file.exists()){
            try {
                ois = new ObjectInputStream(new FileInputStream(file));
                User user = (User) ois.readObject();
                Toast.makeText(LaunchActivity.this, "欢迎回来："+user.mail.toString(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(LaunchActivity.this, HomeActivity.class);
                startActivity(intent);
            }catch (Exception e){
                e.printStackTrace();
            }finally{
                try {
                    if (ois!=null) {
                        ois.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        else {
            Intent intent = new Intent(LaunchActivity.this, MainActivity.class);
            startActivity(intent);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

        String path= Environment.getExternalStorageDirectory().getAbsolutePath()+"/userinfo.dat";
        ObjectInputStream ois = null;
        File file = new File(path);
        if (file.exists()){
//            try {
//                ois = new ObjectInputStream(new FileInputStream(file));
//                User user = (User) ois.readObject();
//                Intent intent = new Intent(LaunchActivity.this, HomeActivity.class);
//                startActivity(intent);
//            }catch (Exception e){
//                e.printStackTrace();
//            }finally{
//                try {
//                    if (ois!=null) {
//                        ois.close();
//                    }
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
            Intent intent = new Intent(LaunchActivity.this, HomeActivity.class);
            startActivity(intent);
        }
        else {
            Intent intent = new Intent(LaunchActivity.this, MainActivity.class);
            startActivity(intent);
        }
    }
}
