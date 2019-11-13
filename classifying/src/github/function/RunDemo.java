package github.function;

import github.streamhelp.Help;

import java.util.ArrayList;

public class RunDemo {
    public static void main(String[] args) {
        String path = "D:\\WeChat\\file\\WeChat Files\\y886688q\\FileStorage\\File\\2019-11\\症状.txt";
        ArrayList<String> list = Help.FileReadLines(path);
        for (String s : list) {
            System.out.print(s+"\t"+s.length());
            System.out.println();
        }
    }
}
