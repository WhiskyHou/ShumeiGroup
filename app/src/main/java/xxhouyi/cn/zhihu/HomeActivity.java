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
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class HomeActivity extends AppCompatActivity {
    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

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

//        public boolean onOptionsItemSelected(MenuItem item){
//        switch (item.getItemId()){
//            case android.R.id.home:
//                drawerLayout.openDrawer(GravityCompat.START);
//                break;
//        }
//        return true;
//    }

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
}
