package app.netlify.codelittlestation.codelittlestation.classes;

import java.io.IOException;
import java.util.Date;

public class log {
    private log(){}
    private static String path = "存放日志的目录";
    private static String getFileName(){
        Date d = new Date();
        return (1900 + d.getYear())+"-"+(1+d.getMonth())+".log";
    }
    private static String getStr_geshihuaLog(String j,String s){
        Date d = new Date();
        String min = String.valueOf(d.getMinutes());
        if(min.length() == 1){
            min = "0"+min;
        }
        return (1900+d.getYear())+"-"+(1+d.getMonth())+"-"+d.getDate()+" "+d.getHours()+":"+min+" ["+j+"] "+s;
    }
    public static void console(String msg){
        System.out.println(msg);
    }
    public static void console(String j,String s){
        console(getStr_geshihuaLog(j,s));
    }
    public static void file(String msg) throws IOException {
        fileIO.write(path+getFileName(),msg+"\n",true);
    }
    public static void file(String j,String s) throws IOException {
        file(getStr_geshihuaLog(j,s));
    }
    public static void Log(String msg) throws IOException {
        console(msg);
        file(msg);
    }
    public static void Log(String j,String s) throws IOException {
        console(j,s);
        file(j,s);
    }
    public static void INFOLog(String msg) throws IOException {
        Log("INFO",msg);
    }
}
