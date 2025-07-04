package app.netlify.codelittlestation.codelittlestation.classes;

import javax.print.attribute.standard.PrinterURI;
import java.util.ArrayList;
import java.util.List;

public class strs {
    private strs(){}
    // 不会用字符串切割，就自己写了一个 ^v^
    public static List<String> fenStr(String s, char c){
        List<String>ret = new ArrayList<>();
        StringBuilder strb = new StringBuilder();
        for(int i = 0;i < s.length();i++){
            char c1 = s.charAt(i);
            if(c1 == c){
                ret.add(strb.toString());
            } else{
                strb.append(c1);
            }
        }
        ret.add(strb.toString());
        return ret;
    }
    // 判断是否为文件
    public static boolean check(List<String>l){
        String s = l.get(l.size()-1);
        for(int i = 0;i < s.length();i++){
            char c = s.charAt(i);
            if(c == '.'){
                return true;
            }
        }
        return false;
    }
    public static boolean check(String s){
        return check(fenStr(s,'/'));
    }
    // 求一段后缀
    public static String getHouzhui(String s,int n){
        if(s.length() < n){
            return s;
        }
        StringBuilder ret = new StringBuilder();
        for(int i = s.length()-n;i < s.length();i++){
            char c = s.charAt(i);
            ret.append(c);
        }
        return ret.toString();
    }
    // 返回一段错误的HTML代码
    public static String getErrorHTML(String code,String msg){
        return "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>"+ code + "啦！" + "</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "<h2>" + code + "</h2>\n" +
                "<p>" + msg + "</p>\n" +
                "</body>\n" +
                "</html>";
    }
}
