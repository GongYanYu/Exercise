package com.yuge.first;


public class MyStringsUtils {
    //除去中文
    public String rChinese(String str){//remove chinese
        return str.replaceAll("[\u4e00-\u9fa5]","");
    }
    //除去中文字符
    public String rChineseMarks(String str) {//remove chinese
        return str.replaceAll("[，。‘“；：、·]", "");
    }
    public String rEnglishMarks(String str){
        return str.replaceAll("[,\\|`~*]", "");
    }
    public String rT(String str){
        return str.replaceAll("[\\t]", "");
    }
}