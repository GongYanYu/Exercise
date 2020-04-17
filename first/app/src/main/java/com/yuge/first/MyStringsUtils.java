package com.yuge.first;


public class MyStringsUtils {
    //��ȥ����
    public String rChinese(String str){//remove chinese
        return str.replaceAll("[\u4e00-\u9fa5]","");
    }
    //��ȥ�����ַ�
    public String rChineseMarks(String str) {//remove chinese
        return str.replaceAll("[����������������]", "");
    }
    public String rEnglishMarks(String str){
        return str.replaceAll("[,\\|`~*]", "");
    }
    public String rT(String str){
        return str.replaceAll("[\\t]", "");
    }
}