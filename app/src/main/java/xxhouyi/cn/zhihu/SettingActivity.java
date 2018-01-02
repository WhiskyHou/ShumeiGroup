package xxhouyi.cn.zhihu;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SettingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        final EditText name_t = (EditText) findViewById(R.id.text_name_set);
        final EditText phone_t = (EditText) findViewById(R.id.text_phone_set);
        final EditText introduction_t = (EditText) findViewById(R.id.text_introduction_set);
        final EditText school_t = (EditText) findViewById(R.id.text_school_set);

        Button button = (Button) findViewById(R.id.check_set);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = name_t.getText().toString();
                String phone = phone_t.getText().toString();
                String introduction = introduction_t.getText().toString();
                String school = school_t.getText().toString();

                User user = null;

                // read
                String path= Environment.getExternalStorageDirectory().getAbsolutePath()+"/userinfo.dat";
                ObjectInputStream ois = null;
                File file = new File(path);
                if (file.exists()){
                    try {
                        ois = new ObjectInputStream(new FileInputStream(file));
                        user = (User) ois.readObject();
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

                // delete
                if (file.exists()){
                    file.delete();
                }

                // save
                user.name = name;
                user.phone= phone;
                user.school = school;
                user.introduce = introduction;
                ObjectOutputStream oos = null;
                try {
                    oos = new ObjectOutputStream(new FileOutputStream(path));
                    oos.writeObject(user);
                    Toast.makeText(SettingActivity.this, "设置成功", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
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
        });
    }
}
