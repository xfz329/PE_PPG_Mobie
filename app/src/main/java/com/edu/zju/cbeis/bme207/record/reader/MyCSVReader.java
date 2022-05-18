package com.edu.zju.cbeis.bme207.record.reader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.csvreader.CsvReader;
//import com.edu.zju.cbeis.bme207.output.Export2File;
import com.edu.zju.cbeis.bme207.record.PERecord;

public class MyCSVReader extends PPGReader{
	public MyCSVReader() {
	
	}

	public static void main(String[] args) {
		long startTime=System.currentTimeMillis(); 
		MyCSVReader c =new MyCSVReader();
		String f="D:\\UrgeData\\Documents\\Codes\\Graduate\\PE_PPG\\data\\NO\\cj_wavePLETH_2018052514\\cj_wavePLETH_2018052514.csv";
		PERecord rcd = c.readFromFile(f);
		rcd.setTarget();
		rcd.extractFeatures();
//		Export2File ef = new Export2File(rcd);
//		ef.export(true);
		
//		long endTime=System.currentTimeMillis();
//		c.logger.info("Software running time without upload: "+(endTime-startTime)+"ms");
//
//		HttpClientPoolUtil hcpu = HttpClientPoolUtil.getHttpClientPoolUtil();
//		hcpu.post(IPAddress.UPLOAD,rcd);
//
//		endTime=System.currentTimeMillis();
//		c.logger.info("Software running time with upload: "+(endTime-startTime)+"ms");
	}

	@Override
	public PERecord readFromFile(String file) {
		PERecord rcd = new PERecord();
		String[] res;
		String tmp;
		int[] v;
		try {
            CsvReader csvReader = new CsvReader(file);
            String[] strArr = file.split("\\\\");		// for path
            res	=	strArr[strArr.length-1].split("_");
            List<Integer> content = new ArrayList<>();
            csvReader.readHeaders();
            while (csvReader.readRecord()){
				csvReader.getRawRecord();
				tmp = csvReader.get(1);
				if (!tmp.equals(""))
					content.add(Integer.parseInt(tmp));
            }
            csvReader.close();
			v = new int[content.size()];
			for (int i=0;i<content.size();i++)
				v[i] = content.get(i).intValue();
        } catch (IOException e) {
            e.printStackTrace();
//			logger.error("IOException, read file failed!");
            return null;
        }

        rcd.setFileName(file);
        rcd.setPersonName(res[0]);
        rcd.setSampleTime(res[2].substring( 0, 10));
        rcd.setSampleRate(100);
        rcd.setOriginal(v);
//		logger.info("Successfully read file %s",file);
        return rcd;
	}

}
