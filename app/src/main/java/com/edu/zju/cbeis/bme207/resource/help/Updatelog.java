package com.edu.zju.cbeis.bme207.resource.help;

import java.util.ArrayList;
import java.util.List;

public class Updatelog {
    private static String res;
    private static String currentVersion                  =       "0.16.4"        ;
    private static String lastReleaseDate                 =       "2022.05.06"    ;
    private static List<String> detail  = new ArrayList<>();

    public Updatelog(){

    }

    public static String getCurrentVersion() {
        return currentVersion;
    }

    public static String getLastReleaseDate() {
        return lastReleaseDate;
    }

    public static String getRes(){
        init();
        return res;
    }

    private static void init(){
        res = "";
        addHead();

        detail.add("增加批量处理文件夹下所有记录的功能");
        addVersion("0.16.4","2022.05.06");

        detail.add("增加脉搏波检测结果的导出");
        addVersion("0.16.3","2022.04.29");

        detail.add("增加软件的Change Log 功能");
        detail.add("增加对经迈瑞监护仪导出的数据读取的支持");
        detail.add("增加新的波形识别判定规则，调整了波形投票规则");
        addVersion("0.16.2","2022.03.21");

        detail.add("调整UI,增加在软件主页面显示当前打开文件及PE状态功能");
        detail.add("修复多次分析当前打开文件时，导出得到的特征数据会重复写入的问题");
        addVersion("0.16.1","2022.03.20");

        detail.add("调整改写数据上传API接口");
        detail.add("增加对服务器网络地址及端口的配置");
        detail.add("合并了数据导出及上传操作，减少用户点击操作");
        addVersion("0.16","2022.03.15");


        detail.add("增加直接选择已分析的数据特征进行上传");
        detail.add("增加对某一路径下所有已分析的数据特征一次性上传");
        addVersion("0.15","2022.03.14");

        addTail();
    }



    private static void addVersion(String num, String date){
        addH1(num);
        addDate(date);
        addDetail();
    }

    private static void addH1(String num){
        res += "<h1>&emsp;"+num+"</h1>";
    }
    
    private static void addDate(String date){
        res +=  "<p class= \"italic\">&emsp;Released on "+date+"</p>"; 
    }
    
    private static void addDetail(){
        for(int i=0;i<detail.size();i++){
            res +=  "<p class= \"normal\">&emsp;&emsp;*&ensp"+detail.get(i)+"</p>";
        }
        detail.clear();
    }

    private static void addHead(){
        res +=  "<html>"
            +   "<head>"
            +   "<style type=\"text/css\">"
            +   "h1 {text-align: left;font-family:\"Times New Roman\";font-size: 30px}"
            +   "p.normal {text-align: left;font-family:\"Times New Roman\";font-size: 14px;font-style:normal;}"
            +   "p.italic {text-align: left;font-family:\"Times New Roman\";font-size: 14px;font-style:italic;}"
            +   "</style>"
            +   "</head>"
            +   "<body>";
    }

    private static void addTail(){
        res +="<br><br></body></html>";
    }
}
