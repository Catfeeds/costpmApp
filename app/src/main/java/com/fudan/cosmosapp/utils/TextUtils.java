package com.fudan.cosmosapp.utils;

import java.util.regex.Pattern;

/**
 * Created by Administrator on 2017/8/18 0018.
 */

public class TextUtils {


    public static int EG = 0;
    public static int CN = 1;
    public static int OTHER = 2;

    public static int judgeTextIsChineseOREnglish(String text) {

        if(text == null || text.equals("")){
            return OTHER;
        }else if(isChinese(text)){
            return CN;
        }else if(isEnglish(text)){
            return EG;
        }else {
            return OTHER;
        }
    }

    public static boolean isEnglish(String text){
        return text.matches("[a-zA-Z]+");//true:全文英文
    }


    // 根据Unicode编码完美的判断中文汉字和符号
    private static boolean isChinese(char c) {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
        if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
                ||
                ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_B
                ||
                ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS
                || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION) {
            return true;
        }
        return false;
    }

    // 完整的判断中文汉字和符号
    public static boolean isChinese(String strName) {
        char[] ch = strName.toCharArray();
        for (int i = 0; i < ch.length; i++) {
            char c = ch[i];
            if (isChinese(c)) {
                return true;
            }
        }
        return false;
    }

    // 只能判断部分CJK字符（CJK统一汉字）
    public static boolean isChineseByREG(String str) {
        if (str == null) {
            return false;
        }
        Pattern pattern = Pattern.compile("[\\u4E00-\\u9FBF]+");
        return pattern.matcher(str.trim()).find();
    }

    // 只能判断部分CJK字符（CJK统一汉字）
    public static boolean isChineseByName(String str) {
        if (str == null) {
            return false;
        }
        // 大小写不同：\\p 表示包含，\\P 表示不包含
        // \\p{Cn} 的意思为 Unicode 中未被定义字符的编码，\\P{Cn} 就表示 Unicode中已经被定义字符的编码
        String reg = "\\p{InCJKUnifiedIdeographs}&&\\P{Cn}";
        Pattern pattern = Pattern.compile(reg);
        return pattern.matcher(str.trim()).find();
    }
}
