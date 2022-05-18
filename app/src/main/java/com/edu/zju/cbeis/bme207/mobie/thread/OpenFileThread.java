package com.edu.zju.cbeis.bme207.mobie.thread;

import android.os.Handler;
import android.os.Message;

import com.edu.zju.cbeis.bme207.record.PERecord;
import com.edu.zju.cbeis.bme207.record.reader.MindaryReader;
import com.edu.zju.cbeis.bme207.record.reader.MyCSVReader;
import com.edu.zju.cbeis.bme207.record.reader.PPGReader;


public class OpenFileThread extends Thread{
    public final static int UNKNOWN_DATA_FORMAT  = 0x0010;
    public final static int PE_RECORD_UPDATE_FINISHED = 0x0011;
    private Handler handler;
    private String file_path;
    public OpenFileThread(Handler h, String p){
        handler = h;
        file_path = p;
    }

    @Override
    public void run() {
        Message message = Message.obtain();
        PERecord record = null;
        PPGReader reader;
        if(file_path.endsWith(".csv")){
            reader =new MyCSVReader();
            record= reader.readFromFile(file_path);
            message.what = PE_RECORD_UPDATE_FINISHED;
            message.obj = record;
        }
//        else if(file_path.endsWith(".json")){
//            reader =new MindaryReader();
//            record= reader.readFromFile(file_path);
//        }
        else{
            message.what = UNKNOWN_DATA_FORMAT;
        }
        handler.sendMessage(message);

    }
}
