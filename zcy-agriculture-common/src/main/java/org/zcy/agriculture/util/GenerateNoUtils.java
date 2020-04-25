package org.zcy.agriculture.util;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

public class GenerateNoUtils {
    //Thread safe number increase.
    private static AtomicInteger number = new AtomicInteger(0);

    private static long getNumber() {
        int num = number.incrementAndGet();
        if (num > 9999) {
            number = new AtomicInteger(0);
            num = number.incrementAndGet();
        }
        return num;
    }

    /**
     * 生成订单号 前缀+后缀
     *
     * @param pre 订单号前缀+后缀
     * @return
     */
    public static String gens(String pre, Long museId) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        //生成
        String orderNo = pre + sdf.format(new Date()) + (1 + (int) (Math.random() * 10000)) + museId;
        return orderNo;
    }


    /**
     * 生成订单号 前缀+后缀
     *
     * @param pre 订单号前缀+后缀
     * @return
     */
    public static String gens(String pre, String suf) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        //生成
        String orderNo = pre + sdf.format(new Date()) + (1 + (int) (Math.random() * 10000)) + suf;
        return orderNo;
    }

    /**
     * 生成订单号 前缀
     *
     * @param pre 订单号前缀
     * @return
     */
    public static String gen(String pre) {
        //生成
        //String orderNo = pre + ((int)((Math.random()*9+1)*10000)) + museId + (System.currentTimeMillis() / 1000);
        //return orderNo;

        int code = UUID.randomUUID().toString().hashCode();
        if (code < 0) {
            code = code * -1;
        }

        return pre + code + ((int) (Math.random() * 900) + 100);
    }

    public static String genWorksheetNo(String prefix) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        //format
        DecimalFormat countFormat = new DecimalFormat("0000");

        String randomNum = countFormat.format(new Random().nextInt(10000));

        String dataStr = sdf.format(new Date());

        String suffix = countFormat.format(getNumber());

        return prefix + dataStr + randomNum + suffix;
    }

}
