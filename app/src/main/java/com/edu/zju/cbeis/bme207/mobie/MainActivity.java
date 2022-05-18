package com.edu.zju.cbeis.bme207.mobie;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.graphics.Color;
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

import com.edu.zju.cbeis.bme207.mobie.transformer.Points2Entry;
import com.edu.zju.cbeis.bme207.mobie.transformer.Record2LineData;
import com.edu.zju.cbeis.bme207.record.PERecord;
import com.edu.zju.cbeis.bme207.record.reader.MindaryReader;
import com.edu.zju.cbeis.bme207.record.reader.MyCSVReader;
import com.edu.zju.cbeis.bme207.record.reader.PPGReader;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.google.android.material.navigation.NavigationView;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static  int  GET_FILE = 123;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private PERecord peRecord;
    private LineChart chart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }
        init_line_chart();
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Toast.makeText(MainActivity.this,item.getTitle().toString(),Toast.LENGTH_SHORT).show();
                switch (item.getItemId()){
                    case R.id.open_file:
                        try_open();
                        break;
                    default:
                        break;
                }
                drawerLayout.closeDrawer(navigationView);
                return true;
            }
        });

    }

    private void try_open(){
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*");
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        startActivityForResult(intent,GET_FILE);
        Log.d("Path" ,"onClick: "+ Environment.getExternalStorageDirectory());
    }

    private void open(String file){
        PPGReader reader;
        if(file.endsWith(".csv")){
            reader =new MyCSVReader();
            peRecord = reader.readFromFile(file);
            update_data_from_pe_record();
        }
        if(file.endsWith(".json")){
            reader =new MindaryReader();
            peRecord = reader.readFromFile(file);
        }
        Log.d("open", "open: ");
    }

    private void init_line_chart(){
        chart = findViewById(R.id.chart);
//        chart.setOnChartValueSelectedListener(this);
        chart.getDescription().setEnabled(false);
        chart.setBackgroundColor(Color.WHITE);
//        XAxisValueFormatter xAxisFormatter = new XAxisValueFormatter();
        XAxis xAxis = chart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setGranularity(0f);
//        xAxis.setValueFormatter(xAxisFormatter);

//        MyAxisValueFormatter custom = new MyAxisValueFormatter();
        YAxis leftAxis = chart.getAxisLeft();

        leftAxis.setLabelCount(8, false);
//        leftAxis.setValueFormatter(custom);
        leftAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);
        leftAxis.setSpaceTop(15f);
        leftAxis.setAxisMinimum(-1000f);
        chart.getAxisRight().setEnabled(false);

        chart.setTouchEnabled(true);
        chart.setDragEnabled(true);
        chart.setScaleEnabled(true);
        chart.setPinchZoom(true);
    }

    private void update_data_from_pe_record(){
        chart.setData(new Record2LineData().convert(peRecord));
        chart.invalidate();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GET_FILE && resultCode == RESULT_OK) {
            Uri uri = data.getData();
            String path =uri.toString().substring(7);
            Log.d("File", "onActivityResult: "+path);
            open(path);
            Toast.makeText(getApplicationContext(),"get new file: "+path,Toast.LENGTH_SHORT).show();
            Log.d("test", "onActivityResult: "+uri);
        }
    }
}