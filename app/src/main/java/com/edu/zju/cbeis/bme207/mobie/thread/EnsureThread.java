package com.edu.zju.cbeis.bme207.mobie.thread;

import android.os.Handler;
import android.os.Message;

import com.edu.zju.cbeis.bme207.record.PERecord;

public class EnsureThread extends Thread{
    public final static int ENSURE_FINISHED = 0x0030;
    private Handler handler;
    private PERecord record;

    public EnsureThread(Handler h, PERecord rcd){
        handler = h;
        record = rcd;
    }

    @Override
    public void run() {
        record.setTarget();
        Message message = Message.obtain();
        message.what =ENSURE_FINISHED;
        message.obj =record;
        handler.sendMessage(message);
    }
}
