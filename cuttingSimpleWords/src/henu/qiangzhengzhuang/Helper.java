package henu.qiangzhengzhuang;

import org.jsoup.Connection;

import java.util.ArrayList;

public interface Helper {
    //get connection and info
    int getInfo(Connection conn, String str);

    // select in the cutting words
    ArrayList<String> judgeWordsTest1(Words w, Connection conn, String str);
//    ArrayList<String> judgeWordsTest2(Words w, Connection conn, String str);
    //add to file
    void writeToFile(Words w, Connection conn, String str, String filePath, boolean flag);

    //get Final List
    ArrayList<String> getFinalList(Words w, Connection conn, String str);

}
