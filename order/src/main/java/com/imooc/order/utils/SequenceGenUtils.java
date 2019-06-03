/*******************************************************************************
 * Project Key : CPPII
 * Create on 2013-10-11 下午3:40:09
 * Copyright (c) 2008 - 2011.深圳市腾云在线科技控股有限公司版权所有. 
 * 注意：本内容仅限于深圳市腾云在线科技控股有限公司内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
package com.imooc.order.utils;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.springframework.util.Assert;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

/**
 * <P>序列生成工具</P>
 *
 * @author 林仙龙（15361632946） 2013-10-11 下午3:40:09
 * @version 1.0
 */
public class SequenceGenUtils {

    private final static String YY_MM_DD = "yyMMdd";
    private static final AtomicLong longGenerator = new AtomicLong(System.nanoTime());

    private static final String FORMAT_YMDHMS = "yyyyMMddHHmmss";

    /**
     * <p>根据用户ID 是生成费用项</p>
     *
     * @param uid
     * @return
     * @author 林仙龙（15361632946） 2016-10-13 下午8:38:49
     */
    public static String createExpensesNo(Long uid) {
        DateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        String date = format.format(new Date());
        return "P" + date + "" + StringUtils.leftPad(uid + "", 8, "0") + getRandomString(6);
    }

    /**
     * <p>根据用户ID 是生成订单号</p>
     *
     * @param uid
     * @return
     * @author 林仙龙（15361632946） 2016-10-13 下午8:38:49
     */
    public static String createOrderNo(Long uid) {
        DateFormat format = new SimpleDateFormat("yyyyMMdd");
        String date = format.format(new Date());
        return date + "" + StringUtils.leftPad(uid + "", 8, "0") + getRandomString(6);
    }

    /**
     * <p>用于生成数据库序列：取得下一个全局唯一的sequence_id(18位长度).</p>
     *
     * @param platform        平台标示,可为空默认值为100
     * @param currentSequence 当前数据库中的序列号
     * @return
     * @author 林仙龙（15361632946） 2013-10-11 下午3:45:15
     */
    public static long getNextId(final String platform, final Long currentSequence) {
        final StringBuilder seq = new StringBuilder(18);
        if (StringUtils.isBlank(platform)) {
            seq.append("100");
        } else {
            seq.append(platform);
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat(YY_MM_DD);
        seq.append(dateFormat.format(new Date()));
        String sequ = new DecimalFormat("00000000").format(currentSequence);
        seq.append(sequ);
        return Long.valueOf(seq.toString());
    }

    /**
     * <p>用于生成数据库序列：取得下一个全局唯一的sequence_id(18位长度).</p>
     *
     * @param platform        平台标示,可为空默认值为100
     * @param currentSequence 当前数据库中的序列号
     * @return String 类型
     * @author 林仙龙（15361632946） 2013-10-11 下午3:45:15
     */
    public static String getNextIdForString(final String platform, final Long currentSequence) {
        final StringBuilder seq = new StringBuilder(18);
        if (StringUtils.isBlank(platform)) {
            seq.append("100");
        } else {
            seq.append(platform);
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat(YY_MM_DD);
        seq.append(dateFormat.format(new Date()));
        String sequ = new DecimalFormat("00000000").format(currentSequence);
        seq.append(sequ);
        return seq.toString();
    }

    /**
     * <p>用于生成数据库序列：取得下一个全局唯一的sequence_id(18位长度).</p>
     *
     * @param currentSequence 当前数据库中的序列号
     * @return String 类型
     * @author 林仙龙（15361632946） 2013-10-11 下午3:45:15
     */
    public static String getNextIdForString(final Long currentSequence) {
        return getNextIdForString("100", currentSequence);
    }

    /**
     * <p>依据序列创建序列</p>
     *
     * @param platform
     * @param currentSequence
     * @return
     * @author 林仙龙（15361632946） 2014-5-29 下午6:20:38
     */
    public static String getNextStrId(final String platform, final Long currentSequence) {
        Assert.isTrue(platform.length() > 0, "平台标示只能为小于0位");
        final StringBuilder seq = new StringBuilder(18);
        seq.append(platform);
        SimpleDateFormat dateFormat = new SimpleDateFormat(YY_MM_DD);
        seq.append(dateFormat.format(new Date()));
        String sequ = new DecimalFormat("00000000").format(currentSequence);
        seq.append(sequ);
        return seq.toString();
    }

    /**
     * <p>用于生成数据库序列：取得下一个全局唯一的sequence_id(18位长度).</p>
     *
     * @param currentSequence 当前数据库中的序列号
     * @return
     * @author 林仙龙（15361632946） 2013-10-11 下午3:45:15
     */
    public static long getNextId(final Long currentSequence) {
        return getNextId(null, currentSequence);
    }

    /**
     * <p>依据原子时间生成递增序列,长度16位:貌似linux和windows下生成的时间长度不一致</p>
     *
     * @return
     * @author 林仙龙（15361632946） 2013-10-11 下午3:45:15
     */
    public static long getSequence() {
        return longGenerator.incrementAndGet();
    }

    /**
     * <p>随机获取UUID字符串(无中划线),长度32位</p>
     *
     * @return
     * @author 林仙龙（15361632946） 2013-10-11 下午3:45:15
     */
    public static String getUUID() {
        String uuid = UUID.randomUUID().toString();
        return uuid.substring(0, 8) + uuid.substring(9, 13) + uuid.substring(14, 18) + uuid.substring(19, 23)
                + uuid.substring(24);
    }

    /**
     * <p>随机生成六位</p>
     *
     * @param length 表示生成字符串的长度
     * @return
     * @version V1.0
     * @author 林仙龙
     * @date 2014-9-29下午3:48:28
     */
    public static String getRandomString(int length) {
        String base = "0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }

    public static String[] chars = new String[]{"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n",
            "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5", "6", "7", "8",
            "9", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T",
            "U", "V", "W", "X", "Y", "Z"};

    /**
     * <p>获取短的uuid</p>
     *
     * @return
     */
    public static String generateShortUuid() {
        StringBuffer shortBuffer = new StringBuffer();
        String uuid = UUID.randomUUID().toString().replace("-", "");
        for (int i = 0; i < 16; i++) {
            String str = uuid.substring(i * 2, i * 2 + 2);
            int x = Integer.parseInt(str, 16);
            shortBuffer.append(chars[x % 0x3E]);
        }
        return shortBuffer.toString();
    }

    public static synchronized String getUniqueKey() {
        Random random = new Random();
        int number = random.nextInt(9000000) + 100000;

        return DateFormatUtils.format(new Date(), FORMAT_YMDHMS) + String.valueOf(number);
    }
}
