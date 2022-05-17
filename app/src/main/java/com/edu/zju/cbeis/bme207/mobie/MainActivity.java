package com.edu.zju.cbeis.bme207.mobie;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.edu.zju.cbeis.bme207.record.PERecord;
import com.google.android.material.navigation.NavigationView;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public static  int  GET_FILE = 123;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private PERecord peRecord;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Toast.makeText(MainActivity.this,item.getTitle().toString(),Toast.LENGTH_SHORT).show();
//                String s;
//                switch (item.getItemId()){
//                    case R.id.nav_home:
//                        s="home";
//                        break;
//                    case R.id.nav_view:
//                        s = "view";
//                        break;
//                    case R.id.nav_gallery:
//                        s = "gallery";
//                        break;
//                    default:
//                        s="none";
//                }
//                Log.d("clicked",s );
                open();
                drawerLayout.closeDrawer(navigationView);
                return true;
            }
        });

    }
    @Override
    public void onClick(View v) {

    }
    private void open(){
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*");
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        startActivityForResult(intent,GET_FILE);
        Log.d("Path" ,"onClick: "+ Environment.getExternalStorageDirectory());
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GET_FILE && resultCode == RESULT_OK) {
            Uri uri = data.getData();
            String path =uri.toString().substring(7);
            Log.d("File", "onActivityResult: "+path);
//            fileNames.add(path);
//            try {
//                String decodePath = URLDecoder.decode(path, "UTF-8");
//                tv.append(decodePath+"\n");
//            } catch (UnsupportedEncodingException e) {
//                e.printStackTrace();
//            }
            Toast.makeText(getApplicationContext(),"get new file",Toast.LENGTH_SHORT).show();
            Log.d("test", "onActivityResult: "+uri);
        }
    }
}