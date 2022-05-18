package com.edu.zju.cbeis.bme207.mobie.thread;

import android.os.Handler;
import android.os.Message;

import com.edu.zju.cbeis.bme207.mobie.transformer.Record2LineData;
import com.edu.zju.cbeis.bme207.record.PERecord;
import com.github.mikephil.charting.data.LineData;

public class ConvertThread extends Thread {
    public final static int CONVERT_DATA_FINISHED = 0x0020;
    private Handler handler;
    private PERecord record;

    public ConvertThread(Handler h, PERecord rcd){
        handler = h;
        record =rcd;
    }

    @Override
    public void run() {
        Record2LineData record2LineData = new Record2LineData();
        LineData lineData =record2LineData.convert(record);
        Message message = Message.obtain();
        message.what = CONVERT_DATA_FINISHED;
        message.obj =lineData;
        handler.sendMessage(message);
    }
}
