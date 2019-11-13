package henu.qiangzhengzhuang;

import org.jsoup.Connection;


public class TestDemo {
    public static void main(String[] args) {
        String str = "类风湿性心脏病";
        String filePath = "D://Txt.txt";
        boolean flag = false;
        Words w = new Words(str);
        HelperImpl helper = new HelperImpl();
        Connection conn = null;
        helper.writeToFile(w,conn,str,filePath,flag);
    }

}
