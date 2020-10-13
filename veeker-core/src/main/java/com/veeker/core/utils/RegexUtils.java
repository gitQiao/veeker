package com.veeker.core.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author ：qiaoliang
 */
public class RegexUtils {

    /**
     * 验证Email
     *
     * @author ：qiaoliang
     * @param email : email地址
     * @return boolean
     * @date 2020-07-28 14:17
     */
    public static boolean checkEmail(String email) {
        String regex = "\\w+@\\w+\\.[a-z]+(\\.[a-z]+)?";
        return Pattern.matches(regex, email);
    }

    /**
     * 验证身份证号码
     *
     * @author ：qiaoliang
     * @param idCard : 居民身份证号码18位，第一位不能为0，最后一位可能是数字或字母，中间16位为数字 \d同[0-9]
     * @return boolean
     */
    public static boolean checkIdCard(String idCard) {
        String regex = "[1-9]\\d{16}[a-zA-Z0-9]{1}";
        return Pattern.matches(regex,idCard);
    }

    /**
     * 验证手机号码（支持国际格式，+86135xxxx...（中国内地），+00852137xxxx...（中国香港））
     *<p>移动的号段：134(0-8)、135、136、137、138、139、147（预计用于TD上网卡）
     *、150、151、152、157（TD专用）、158、159、187（未启用）、188（TD专用）</p>
     *<p>联通的号段：130、131、132、155、156（世界风专用）、185（未启用）、186（3g）</p>
     *<p>电信的号段：133、153、180（未启用）、189</p>
     *
     * @author ：qiaoliang
     * @param mobile : 移动、联通、电信运营商的号码段
     * @return boolean
     */
    public static boolean checkMobile(String mobile) {
        String regex = "(\\+\\d+)?1[3456789]\\d{9}$";
        return Pattern.matches(regex,mobile);
    }

    /**
     * 验证固定电话号码
     * <p><b>国家（地区） 代码 ：</b>标识电话号码的国家（地区）的标准国家（地区）代码。它包含从 0 到 9 的一位或多位数字，
     *  数字之后是空格分隔的国家（地区）代码。</p>
     * <p><b>区号（城市代码）：</b>这可能包含一个或多个从 0 到 9 的数字，地区或城市代码放在圆括号——
     * 对不使用地区或城市代码的国家（地区），则省略该组件。</p>
     * <p><b>电话号码：</b>这包含从 0 到 9 的一个或多个数字 </p>
     *
     * @author ：qiaoliang
     * @param phone : 电话号码，格式：国家（地区）电话代码 + 区号（城市代码） + 电话号码，如：+8602085588447
     * @return boolean
     */
    public static boolean checkPhone(String phone) {
        String regex = "(\\+\\d+)?(\\d{3,4}\\-?)?\\d{7,8}$";
        return Pattern.matches(regex, phone);
    }

    /**
     * 验证整数（正整数和负整数）
     *
     * @author ：qiaoliang
     * @param digit : 一位或多位0-9之间的整数
     * @return boolean
     */
    public static boolean checkDigit(String digit) {
        String regex = "\\-?[1-9]\\d+";
        return Pattern.matches(regex,digit);
    }

    /**
     * 验证整数和浮点数（正负整数和正负浮点数）
     *
     * @author ：qiaoliang
     * @param decimals : 一位或多位0-9之间的浮点数，如：1.23，233.30
     * @return boolean
     * @date 2020-07-28 14:18
     */
    public static boolean checkDecimals(String decimals) {
        String regex = "\\-?[1-9]\\d+(\\.\\d+)?";
        return Pattern.matches(regex,decimals);
    }

    /**
     * 验证空白字符
     *
     * @author ：qiaoliang
     * @param blankSpace : 空白字符，包括：空格、\t、\n、\r、\f、\x0B
     * @return boolean
     */
    public static boolean checkBlankSpace(String blankSpace) {
        String regex = "\\s+";
        return Pattern.matches(regex,blankSpace);
    }

    /**
     * 验证中文
     *
     * @author ：qiaoliang
     * @param chinese : 中文字符
     * @return boolean
     * @date 2020-07-28 14:19
     */
    public static boolean checkChinese(String chinese) {
        String regex = "^[\u4E00-\u9FA5]+$";
        return Pattern.matches(regex,chinese);
    }

    /**
     * 验证日期（年月日）
     *
     * @author ：qiaoliang
     * @param birthday : 日期，格式：1994-07-12，或1994.07.12
     * @return boolean
     */
    public static boolean checkBirthday(String birthday) {
        String regex = "[1-9]{4}([-./])\\d{1,2}\\1\\d{1,2}";
        return Pattern.matches(regex,birthday);
    }

    /**
     * 验证URL地址
     *
     * @author ：qiaoliang
     * @param url : url
     * @return boolean
     */
    public static boolean checkURL(String url) {
        String regex = "(https?://(w{3}\\.)?)?\\w+\\.\\w+(\\.[a-zA-Z]+)*(:\\d{1,5})?(/\\w*)*(\\??(.+=.*)?(&.+=.*)?)?";
        return Pattern.matches(regex, url);
    }

    /**
     * 获取网址 URL 的一级域名
     *
     * @author ：qiaoliang
     * @param url : url
     * @return java.lang.String
     */
    public static String getDomain(String url) {
        Pattern p = Pattern.compile("(?<=http://|\\.)[^.]*?\\.(com|cn|net|org|biz|info|cc|tv)", Pattern.CASE_INSENSITIVE);
        // 获取完整的域名
        // Pattern p=Pattern.compile("[^//]*?\\.(com|cn|net|org|biz|info|cc|tv)", Pattern.CASE_INSENSITIVE);
        Matcher matcher = p.matcher(url);
        matcher.find();
        return matcher.group();
    }

    /**
     * 匹配中国邮政编码
     *
     * @author ：qiaoliang
     * @param postcode : 邮政编码
     * @return boolean
     */
    public static boolean checkPostcode(String postcode) {
        String regex = "[1-9]\\d{5}";
        return Pattern.matches(regex, postcode);
    }

    /**
     * 匹配IP地址(简单匹配，格式，如：192.168.1.1，127.1.0.0，没有匹配IP段的大小)
     *
     * @author ：qiaoliang
     * @param ipAddress : IPv4标准地址
     * @return boolean
     */
    public static boolean checkIpAddress(String ipAddress) {
        String regex = "[1-9](\\d{1,2})?\\.(0|([1-9](\\d{1,2})?))\\.(0|([1-9](\\d{1,2})?))\\.(0|([1-9](\\d{1,2})?))";
        return Pattern.matches(regex, ipAddress);
    }

    /**
     * 判断字符串是否符合正则表达式
     *
     * @author : qiaoliang
     * @param str 字符串
     * @param regex 正则表达式
     * @return Boolean
     */
    public static boolean find(String str, String regex) {
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(str);
        boolean b = m.find();
        return b;
    }

    /**
     * 判断输入的字符串是否符合Email格式.
     *
     * @author : qiaoliang
     * @param email 传入的字符串
     * @return Boolean
     */
    public static boolean isEmail(String email) {
        if (email == null || email.length() < 1 || email.length() > 256) {
            return false;
        }
        Pattern pattern = Pattern.compile("^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$");
        return pattern.matcher(email).matches();
    }

    /**
     * 判断输入的字符串是否为纯汉字
     *
     * @author ：qiaoliang
     * @param value : 传入的字符串
     * @return boolean
     */
    public static boolean isChinese(String value) {
        Pattern pattern = Pattern.compile("[\u0391-\uFFE5]+$");
        return pattern.matcher(value).matches();
    }

    /**
     * 判断是否为浮点数，包括double和float
     *
     * @author ：qiaoliang
     * @param value : 传入的字符串
     * @return boolean
     */
    public static boolean isDouble(String value) {
        Pattern pattern = Pattern.compile("^[-\\+]?\\d+\\.\\d+$");
        return pattern.matcher(value).matches();
    }

    /**
     * 判断是否为整数
     *
     * @author ：qiaoliang
     * @param value : 传入的字符串
     * @return boolean
     */
    public static boolean isInteger(String value) {
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]+$");
        return pattern.matcher(value).matches();
    }

    /**
     * 判断是否是字母
     *
     * @author ：qiaoliang
     * @param value : 传入的字符串
     * @return boolean
     */
    public static boolean checkChar(String value) {
        Pattern pattern = Pattern.compile("[a-z|A-Z]+");
        return pattern.matcher(value).matches();
    }

}
