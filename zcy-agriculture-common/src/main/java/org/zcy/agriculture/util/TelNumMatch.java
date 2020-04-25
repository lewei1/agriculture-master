package org.zcy.agriculture.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author linlq
 * @date 2019-6-13
 */
public class TelNumMatch {
    /*  　　　　
    2018年3月已知

    中国电信号段
        133,149,153,173,177,180,181,189,199
    中国联通号段
        130,131,132,145,155,156,166,175,176,185,186
    中国移动号段
        134(0-8),135,136,137,138,139,147,150,151,152,157,158,159,178,182,183,184,187,188,198
    其他号段
        14号段以前为上网卡专属号段，如中国联通的是145，中国移动的是147等等。
        虚拟运营商
            电信：1700,1701,1702
            移动：1703,1705,1706
            联通：1704,1707,1708,1709,171
        卫星通信：148(移动) 1349
    */
    static String YD = "^[1]{1}(([3]{1}[4-9]{1})|([5]{1}[012789]{1})|([8]{1}[12378]{1})|([4]{1}[7]{1}))[0-9]{8}$";
    static String LT = "^[1]{1}(([3]{1}[0-2]{1})|([5]{1}[56]{1})|([8]{1}[56]{1}))[0-9]{8}$";
    static String DX = "^[1]{1}(([3]{1}[3]{1})|([5]{1}[3]{1})|([8]{1}[09]{1}))[0-9]{8}$";
//    static String phone = "^[1](([3][0-9])|([4][5,7,9])|([5][^4,6,9])|([6][6])|([7][3,5,6,7,8])|([8][0-9])|([9][8,9]))[0-9]{8}$";
    static String phone = "^1([38][0-9]|4[579]|5[0-3,5-9]|6[6]|7[0135678]|9[89])\\d{8}$";

    String mobPhnNum;

    public TelNumMatch(String mobPhnNum) {
        this.mobPhnNum = mobPhnNum;
    }

    public int matchNum() {
        /**
         * 28. * flag = 1 YD 2 LT 3 DX 29.
         */
        int flag;//存储匹配结果
        // 判断手机号码是否是11位
        if (mobPhnNum.length() == 11) {
            // 判断手机号码是否符合中国移动的号码规则
            if (mobPhnNum.matches(YD)) {
                flag = 1;
            }
            // 判断手机号码是否符合中国联通的号码规则
            else if (mobPhnNum.matches(LT)) {
                flag = 2;
            }
            // 判断手机号码是否符合中国电信的号码规则
            else if (mobPhnNum.matches(DX)) {
                flag = 3;
            }
            // 都不适合，未知֪
            else {
                flag = 4;
            }
        }
        // 不是11位
        else {
            flag = 5;
        }
        return flag;
    }

    //手机号码的有效性验证
    public static boolean isValidPhoneNumber(String number)
    {
        Pattern p = null;
        Matcher m = null;
        boolean flag=false;
        if (StringUtils.isNotBlank(number)) {
            p = Pattern.compile(phone);
            m = p.matcher(number);
        }
        if(number.length()==11 && m.matches())
        {
            flag=true;
        }
        return flag;
    }

    //判断手机号码是否存在
    public static boolean isExistPhoneNumber(String number)
    {
        return false;
    }

    //判断email地址是否有效
    public static boolean isEmail(String email)
    {
        String patternString="^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        return isMatcher(patternString,email);
    }

    //是否是数字和字母
    public static boolean isMatchCharOrNumber(String str)
    {
        String patternString="^[\\d|a-z|A-Z]+$";
        return isMatcher(patternString,str);
    }

    //是否匹配
    public static boolean isMatcher(String patternString,String str)
    {
        boolean isValid=false;
        CharSequence inputStr =str ;
        Pattern pattern =Pattern.compile(patternString,Pattern.CASE_INSENSITIVE);
        Matcher matcher=pattern.matcher(inputStr);
        if(matcher.matches())
        {
            isValid =true;
        }
        return isValid;
    }
}
