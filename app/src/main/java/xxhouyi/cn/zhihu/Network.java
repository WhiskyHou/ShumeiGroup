package xxhouyi.cn.zhihu;

import android.os.Handler;
import android.os.Message;
import java.io.IOException;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by SEELE on 2018/1/1.
 */

public class Network {
    public static int signin = 0;

    public static void postStringWithOkhttp(final String[] name, final String[] content, final String url, final Handler handler){
        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient client = new OkHttpClient();
                RequestBody requestBody = new FormBody.Builder().add(name[0], content[0]).add(name[1], content[1]).build();
                Request request = new Request.Builder().url(url).post(requestBody).build();
                okhttp3.Call call = client.newCall(request);
                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        Message message = new Message();
                        message.what = -1;
                        message.obj = "服务器连接失败";
                        handler.sendMessage(message);
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        if (response.isSuccessful()){
                            String data = response.body().string();
                            Message message = new Message();
                            message.what = 1;
                            message.obj = data;
                            handler.sendMessage(message);
                        }
                    }
                });
            }
        }).start();
    }
}
