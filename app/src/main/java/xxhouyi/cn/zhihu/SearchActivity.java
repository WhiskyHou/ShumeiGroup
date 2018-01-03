package xxhouyi.cn.zhihu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.WindowManager;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {
    private List<Question> questionList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        SearchView searchView = (SearchView) findViewById(R.id.search_bar);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                if (!s.equals("all"))
                    Toast.makeText(SearchActivity.this, "无搜索结果", Toast.LENGTH_SHORT).show();
                else
                    showList();
                return false;
            }
            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });
    }


    private void showList(){
        initQuestionList();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view2);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        QuestionAdapter adapter = new QuestionAdapter(questionList);
        recyclerView.setAdapter(adapter);
    }

    private void initQuestionList(){
        for (int i = 0; i < 2; ++i){
            Question q1 = new Question(1, R.drawable.head1, "OKjson", "请问一下这个法线贴图怎么弄", "null");
            questionList.add(q1);
            Question q2 = new Question(2, R.drawable.head2, "jack-wang", "后天的作业可不可以晚点交，谁知道最后期限啊", "null");
            questionList.add(q2);
            Question q3 = new Question(3, R.drawable.head3, "Liuwangshu", "有人能帮我看一下这段代码么，一直报错编译不通过", "null");
            questionList.add(q3);
            Question q4 = new Question(4, R.drawable.head4, "马师傅", "如何评价北工大软件学院的一群光头夜不归宿的现象", "null");
            questionList.add(q4);
            Question q5 = new Question(5, R.drawable.head5, "balabala2333", "下周的体侧有人能帮忙跑一下800么，根据速度下面有详细的红包金额", "null");
            questionList.add(q5);
            Question q6 = new Question(6, R.drawable.head6, "橘子花", "谁帮我做俩期末大作业", "null");
            questionList.add(q6);
            Question q7 = new Question(7, R.drawable.head7, "pkkls", "周末有人去604么", "null");
            questionList.add(q7);
            Question q8 = new Question(8, R.drawable.head8, "不如喝茶", "下学期的课都怎么报，还有各类课都要多少学分啊", "null");
            questionList.add(q8);
            Question q9 = new Question(9, R.drawable.head9, "圈子官方", "你2017年的最后一行代码是什么", "null");
            questionList.add(q9);
            Question q10 = new Question(10, R.drawable.head10, "80s", "谁有放假的具体安排，要订票了……", "null");
            questionList.add(q10);
        }
    }
}
