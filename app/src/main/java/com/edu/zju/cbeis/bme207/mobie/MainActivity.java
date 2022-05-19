package com.edu.zju.cbeis.bme207.mobie;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.edu.zju.cbeis.bme207.mobie.thread.ConvertThread;
import com.edu.zju.cbeis.bme207.mobie.thread.EnsureThread;
import com.edu.zju.cbeis.bme207.mobie.thread.OpenFileThread;
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
    private Handler handler;
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }
        ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},1);
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
                    case R.id.ensure:
                        try_ensure();
                        break;
                    default:
                        break;
                }
                drawerLayout.closeDrawer(navigationView);
                return true;
            }
        });

        handler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(@NonNull Message msg) {
                switch (msg.what){
                    case OpenFileThread.UNKNOWN_DATA_FORMAT:
                        Toast.makeText(MainActivity.this,"暂时不支持.csv以外的数据格式",Toast.LENGTH_SHORT).show();
                        break;
                    case OpenFileThread.PE_RECORD_UPDATE_FINISHED:
                        peRecord = (PERecord) msg.obj;
                        update_data_from_pe_record();
                        break;
                    case ConvertThread.CONVERT_DATA_FINISHED:
                        LineData lineData = (LineData)msg.obj;
                        chart.setData(lineData);
                        chart.invalidate();
                    case EnsureThread.ENSURE_FINISHED:
                        update_data_from_pe_record();
                        break;
                    default:
                        break;
                }
                return false;
            }
        });

        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!drawerLayout.isDrawerOpen(navigationView)) {
                    drawerLayout.openDrawer(navigationView);
                }
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
    private void try_ensure(){
        if(peRecord!= null){
            EnsureThread ensureThread = new EnsureThread(handler,peRecord);
            ensureThread.start();
        }
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
        ConvertThread convertThread = new ConvertThread(handler,peRecord);
        convertThread.start();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GET_FILE && resultCode == RESULT_OK) {
            Uri uri = data.getData();
            String path =getDataColumn(this, uri, null, null);
            Log.d("File", "onActivityResult: "+path+" "+uri);
            OpenFileThread openFileThread = new OpenFileThread(handler,path);
            openFileThread.start();
            Toast.makeText(getApplicationContext(),"get new file: "+path,Toast.LENGTH_SHORT).show();
        }
    }

    private static String getDataColumn(Context context, Uri uri, String selection, String[] selectionArgs) {
        Cursor cursor = null;
        final String column = "_data";
        final String[] projection = { column };
        try {
            try{
                cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs, null);
            }catch (Exception e) {

            }
            if (cursor != null && cursor.moveToFirst()) {
                final int column_index = cursor.getColumnIndexOrThrow(column);
                return cursor.getString(column_index);
            }
        } finally {
            if (cursor != null)
                cursor.close();
        }
        return null;
    }

}