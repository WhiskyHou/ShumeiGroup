package xxhouyi.cn.zhihu;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.List;
import static android.content.ContentValues.TAG;


/**
 * Created by SEELE on 2018/1/2.
 */

public class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.ViewHolder> {
    private List<Question> questionList;

    static class ViewHolder extends RecyclerView.ViewHolder{
        View questionView;
        ImageView author_image;
        TextView author_name;
        TextView title;

        public ViewHolder(View view){
            super(view);
            questionView = view;
            author_image = (ImageView) view.findViewById(R.id.question_author_image);
            author_name = (TextView) view.findViewById(R.id.question_author_name);
            title = (TextView) view.findViewById(R.id.question_title);
        }
    }

    public QuestionAdapter(List<Question> list){
        questionList = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.question_item, parent, false);
        final ViewHolder holder = new ViewHolder(view);
        holder.questionView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = holder.getAdapterPosition();
                Question question = questionList.get(pos);
//                Question question = questionList.get(pos);
//                Intent intent = new Intent(view.getContext(), AnswerActivity.class);
//                intent.putExtra("question",tag.title.getText());
//                view.getContext().startActivity(intent);
                Log.d(TAG, "onClick: ======================"+questionList.get(pos).title);
                Toast.makeText(v.getContext(), question.id,Toast.LENGTH_SHORT).show();
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Question question = questionList.get(position);
        holder.author_image.setImageResource(question.author_image);
        holder.author_name.setText(question.author);
        holder.title.setText(question.title);
    }

    @Override
    public int getItemCount() {
        return questionList.size();
    }
}
