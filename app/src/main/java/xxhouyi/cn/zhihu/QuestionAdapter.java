package xxhouyi.cn.zhihu;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by SEELE on 2018/1/2.
 */

public class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.ViewHolder> {
    private List<Question> questionList;

    static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView author_image;
        TextView author_name;
        TextView title;

        public ViewHolder(View view){
            super(view);
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
        ViewHolder holder = new ViewHolder(view);
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
