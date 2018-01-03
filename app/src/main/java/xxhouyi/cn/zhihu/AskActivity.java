package xxhouyi.cn.zhihu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class AskActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ask);

        EditText title = (EditText) findViewById(R.id.ask_title);
        EditText content = (EditText) findViewById(R.id.ask_content);

        Button post = (Button) findViewById(R.id.ask_post);
        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(AskActivity.this, "问题提交成功", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}
