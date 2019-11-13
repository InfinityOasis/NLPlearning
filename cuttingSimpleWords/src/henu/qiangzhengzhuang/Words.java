package henu.qiangzhengzhuang;

public class Words {
    private String str;
    private String str1;

    public Words(String str){
        this.str = str;
        str1 = str;
    }

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
        str1 = str;
    }

    public String getStr1() {
        return str1;
    }

    public String[] cut1(String str){
        String[] strs = new String[str.length()];
        for (int i = 0; i < str.length(); i++) {
            strs[i] = str.substring(0, str.length() -i );
        }
        return strs;
    }

    public String[] cut2(String str){
        String[] strs = new String[str.length()];
        for (int i = 0; i < str.length(); i++) {
            strs[i] = str.substring(i, str.length());
        }
        /*for (String s : strs) {
            System.out.println(s);
        }*/
        return strs;
    }

}

