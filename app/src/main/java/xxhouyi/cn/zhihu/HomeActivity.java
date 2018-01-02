package xxhouyi.cn.zhihu;

import android.content.Intent;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {
    private DrawerLayout drawerLayout;
    private List<Question> questionList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

//        initQuestionList();
//        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
//        LinearLayoutManager manager = new LinearLayoutManager(this);
//        QuestionAdapter adapter = new QuestionAdapter(questionList);
//        recyclerView.setAdapter(adapter);

        //android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        NavigationView navView = (NavigationView) findViewById(R.id.nav_view);
        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        navView.setCheckedItem(R.id.nav_friend);
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.nav_friend:
                        break;
                    case R.id.nav_question:
                        break;
                    case R.id.nav_answer:
                        break;
                    case R.id.nav_setting:
                        setting();
                        break;
                    case R.id.nav_logout:
                        logout();
                        break;
                    default:
                        break;
                }
                return true;
            }
        });

        Button ask = (Button) findViewById(R.id.home_ask);
        ask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, TestActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();

        TextView name = (TextView) findViewById(R.id.nav_name);
        TextView introduction = (TextView) findViewById(R.id.nav_introduction);

        String path= Environment.getExternalStorageDirectory().getAbsolutePath()+"/userinfo.dat";
        ObjectInputStream ois = null;
        File file = new File(path);
        if (file.exists()) {
            try {
                ois = new ObjectInputStream(new FileInputStream(file));
                User user = (User) ois.readObject();
                //Toast.makeText(HomeActivity.this, name.getText(), Toast.LENGTH_SHORT).show();
                name.setText(user.name);
                introduction.setText(user.introduce);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (ois != null) {
                        ois.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void logout(){
        String path= Environment.getExternalStorageDirectory().getAbsolutePath()+"/userinfo.dat";
        File file = new File(path);
        if (file.exists()){
            file.delete();
            Intent intent = new Intent(HomeActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    private void setting(){
        Intent intent = new Intent(HomeActivity.this, SettingActivity.class);
        startActivity(intent);
    }

    private void initQuestionList(){
        for (int i = 0; i < 2; ++i){
            Question q1 = new Question(R.drawable.head1, "OKjson", "请问一下这个法线贴图怎么弄", "null");
            questionList.add(q1);
            Question q2 = new Question(R.drawable.head2, "jack-wang", "后天的作业可不可以晚点交，谁知道最后期限啊", "null");
            questionList.add(q2);
            Question q3 = new Question(R.drawable.head3, "Liuwangshu", "有人能帮我看一下这段代码么，一直报错编译不通过", "null");
            questionList.add(q3);
            Question q4 = new Question(R.drawable.head4, "马师傅", "如何评价北工大软件学院的一群光头夜不归宿的现象", "null");
            questionList.add(q4);
            Question q5 = new Question(R.drawable.head5, "balabala2333", "下周的体侧有人能帮忙跑一下800么，根据速度下面有详细的红包金额", "null");
            questionList.add(q5);
            Question q6 = new Question(R.drawable.head6, "橘子花", "谁帮我做俩期末大作业", "null");
            questionList.add(q6);
            Question q7 = new Question(R.drawable.head7, "pkkls", "周末有人去604么", "null");
            questionList.add(q7);
            Question q8 = new Question(R.drawable.head8, "不如喝茶", "下学期的课都怎么报，还有各类课都要多少学分啊", "null");
            questionList.add(q8);
            Question q9 = new Question(R.drawable.head9, "圈子官方", "你2017年的最后一行代码是什么", "null");
            questionList.add(q9);
            Question q10 = new Question(R.drawable.head10, "80s", "谁有放假的具体安排，要订票了……", "null");
            questionList.add(q10);
        }
    }
}
