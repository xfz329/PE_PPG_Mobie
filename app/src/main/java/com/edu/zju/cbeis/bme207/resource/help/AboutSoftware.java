package com.edu.zju.cbeis.bme207.resource.help;

public class AboutSoftware {
    private static String res;

    public AboutSoftware(){

    }

    public static String getRes(){
        init();
        return res;
    }

    private static void init(){
        res = "";
        addHead();
        addBody();
    }

    private static void addHead(){
        res +=  "<html>"
            +   "<head>"
            +   "<style type=\"text/css\">"
            +   "h1 {text-align: center;font-family:\"Times New Roman\";font-size: 30px}"
            +   "p {text-align: center;font-family:\"Times New Roman\";font-size: 14px}"
            +   "</style>"
            +   "</head>";
    }

    private static void addBody(){
        res +=  "<body>"
            +   "<h1>脉搏波综合分析系统</h1>"
            +   "<br>"
            +   "<p>浙江大学生物医学工程与仪器科学学院</p>"
            +   "<p>陈杭 教授团队研发</p>"
            +   "<br>"
            +   "<p>Copyright © 2004-2020 浙江大学</p>"
            +   "<p>Version "+Updatelog.getCurrentVersion()+" Released on "+Updatelog.getLastReleaseDate()+"</p>"
            +   "</body>"
            +   "</html>";
    }


}
