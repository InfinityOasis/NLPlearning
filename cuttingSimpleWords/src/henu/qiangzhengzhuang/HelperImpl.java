package henu.qiangzhengzhuang;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class HelperImpl implements Helper {
    @Override
    public int getInfo(Connection conn, String str) {
        conn = Jsoup.connect("https://baike.baidu.com/item/" + str).timeout(5000);
//        conn.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3");
//        conn.header("Accept-Encoding", "gzip, deflate, br");
//        conn.header("Accept-Language", "zh-CN,zh;q=0.9");
//        conn.header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/76.0.3809.100 Safari/537.36");
        try {
            Elements ele = conn.get().select("h1.baikeLogo");
//            System.out.println(ele);
            if (ele.size() == 0)
                return 1;
            else
                return 0;
        } catch (IOException e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public ArrayList<String> judgeWordsTest1(Words w, Connection conn, String str) {
        ArrayList<String> list = new ArrayList<>();
        //逆序查询
        String[] str1 = w.cut1(str);
//        for (String s : str1) {
//            System.out.println(s);
//        }
        String s1 = judgeWords(w, conn, list, str1);
//        System.out.println(s1);
        //正序查询剩余字符
        String s2 = str.replace(s1, "");
        String[] str2 = w.cut2(s2);
        judgeWords(w, conn, list, str2);
        return list;
    }

   /* public ArrayList<String> judgeWordsTest2(Words w, Connection conn, String str) {
        ArrayList<String> list = new ArrayList<>();
        //逆序查询
        String[] str2 = w.cut2(str);
//        for (String s : str2) {
//            System.out.println(s);
//        }
        String s2 = judgeWords(w, conn, list, str2);
//        System.out.println(s2);
        //正序查询剩余字符
        String s1 = str.replace(s2, "");
        String[] str1 = w.cut1(s1);
        judgeWords(w, conn, list, str1);
        return list;
    }*/

    //utils
    private String judgeWords(Words w, Connection conn, ArrayList<String> list, String[] str) {
        for (String s : str) {
            int judge = getInfo(conn, s);
            if (judge == 1 && !s.equals(w.getStr1())) {
                if (s.contains("性") && s.indexOf("性") != s.length() - 1) {
                    continue;
                }
                list.add(s);
//                System.out.println(s);
                return s;
            }
        }
        return null;
    }

    @Override
    public void writeToFile(Words w, Connection conn, String str, String filePath, boolean flag) {
        ArrayList<String> list = getFinalList(w, conn, str);
        File file = new File(filePath);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        BufferedWriter bufferedWriter = null;
        try {
            //包含转换编码格式的文件读写
//			OutputStreamWriter write = new OutputStreamWriter(new FileOutputStream(file,flag),"utf-8");
//		    bufferedWriter=new BufferedWriter(write);

            //包含转换编码格式的文件读写
            bufferedWriter = new BufferedWriter(new FileWriter(file, flag));

            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).length() == 0) {
                    continue;
                }
                if ((i + 1) < list.size() && (list.get(i + 1).equals("性") || list.get(i + 1).equals("类"))) {
                    bufferedWriter.write(list.get(i));
                } else
                    bufferedWriter.write(list.get(i) + "\t");

                bufferedWriter.flush();
            }
            bufferedWriter.write("\n");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            if (bufferedWriter != null) {
                try {
                    bufferedWriter.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }

    public ArrayList<String> getFinalList(Words w, Connection conn, String str) {
        ArrayList<String> list = judgeWordsTest1(w, conn, str);
        String str0 = str;
        for (String s : list) {
            System.out.println(s);
            str = str.replace(s, "");
        }
        list.add(str);
        list.set(2, list.get(1));
        list.set(1, str);

        /*ArrayList<String> list2 = judgeWordsTest2(w, conn, str0);
        //调整顺序
        String s = list2.get(0);
        for (int i = 1; i < list2.size(); i++) {
            list2.set(i-1, list2.get(i));
        }
        list2.set(list2.size()-1,s);
        for (String s1 : list2) {
            System.out.println(s1);
        }*/
        return list;
    }

}
