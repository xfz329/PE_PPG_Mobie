package com.edu.zju.cbeis.bme207.record.reader;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.edu.zju.cbeis.bme207.record.PERecord;
import com.edu.zju.cbeis.bme207.util.Path2String;

public class MindaryReader extends PPGReader{

    @Override
    public PERecord readFromFile(String path) {
        PERecord rcd = new PERecord();
		String res;
		
        Path2String ps = new Path2String();
        res = ps.process(path);
        JSONObject jobj = JSON.parseObject(res);
        JSONArray data = jobj.getJSONArray("数据");
        int len = data.size();
        List<String> list = new ArrayList<>();
        for (int i =0;i<len;i++){
            JSONObject piece = data.getJSONObject(i);
            JSONArray ppg = piece.getJSONArray("ppg");
            int l =ppg.size();
            for(int j=0;j<l;j++){
                list.add(ppg.get(j).toString());
            }
        }
        
        len = list.size();
        int[] v = new int [len];
        for(int i=0;i<len;i++){
            v[i]=(int)Double.parseDouble(list.get(i));
        } 

        rcd.setFileName(path);
        rcd.setPersonName("test");
        rcd.setSampleTime("2022");
        rcd.setSampleRate(125);
        rcd.setOriginal(v);
//		logger.info("Successfully read file %s",path);
        return rcd;
    }

    public static void main(String[] args){
        MindaryReader c =new MindaryReader();
		String f="D:\\UrgeData\\Desktop\\6.json";
		c.readFromFile(f);
    }
    
}
