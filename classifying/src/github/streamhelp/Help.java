package github.streamhelp;

import java.io.*;
import java.util.ArrayList;

public class Help {
    //read the whole file
    public static String FileReader(String filepath) {
        FileInputStream fi = null;
        String res = "";
        try {
            fi = new FileInputStream(filepath);
            int size = fi.available();
            byte[] array = new byte[size];
            fi.read(array);
            res = new String(array);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fi != null) {
                try {
                    fi.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return res;
    }

    //read the lines of the file
    public static ArrayList<String> FileReadLines(String path) {
        FileInputStream fi = null;
        ArrayList<String> strs = new ArrayList<>();
        String s = null;
        try {
            fi = new FileInputStream(path);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fi));
            //or [ new BufferedReader(new FileReader(path)) ]
            while ((s = bufferedReader.readLine())!= null) {
                strs.add(s);
            }
            bufferedReader.close();
            return strs;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fi != null) {
                    fi.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
