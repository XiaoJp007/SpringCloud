/*******************************************************************************
 * Project Key : CPPII
 * Create on 2013-10-11 下午1:38:24
 * Copyright (c) 2008 - 2011.深圳市腾云在线科技控股有限公司版权所有. 
 * 注意：本内容仅限于深圳市腾云在线科技控股有限公司内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
package com.imooc.order.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

/**
 * 
 * <P>金额数据处理工具类</P>
 * 
 * @version 1.0
 * @author 林仙龙（15361632946） 2013-10-11 下午3:20:16
 */
public final class DecimalUtil {
	private DecimalUtil() {
		/* EMPTY */
	}

	public static final BigDecimal ON_HUNDRED = new BigDecimal(100);
	private static final String PATTERN = "0.00";
	private static final String PATTERN_WITHOUT_SCALE = "0";
	private static final DecimalFormat FORMAT_WITHOUT_SCALE = new DecimalFormat(PATTERN_WITHOUT_SCALE);
	private static final DecimalFormat FORMAT = new DecimalFormat(PATTERN);
	static {
		FORMAT.setRoundingMode(RoundingMode.HALF_UP);
	}

	/**
	 * 小数点后位数
	 */
	private static final int SCALE = 2;

	/**
	 * <p>转换double类型数据到BigDecimal类型.</p>
	 * 
	 * @param value Double类型的数据
	 * @return BigDecimal类型数据
	 * @author 林仙龙（15361632946） 2013-10-11 下午3:20:16
	 */
	public static BigDecimal format(final double value) {
		return (new BigDecimal(FORMAT.format(value)));
	}

	/**
	 * <p>转换long类型数据到BigDecimal类型.</p>
	 * 
	 * @param value Double类型的数据
	 * @return BigDecimal类型数据
	 * @author 林仙龙（15361632946） 2013-10-11 下午3:20:16
	 */
	public static BigDecimal format(final long value) {
		return (new BigDecimal(FORMAT.format(value)));
	}

	public static BigDecimal formatWithoutScale(final BigDecimal value) {
		return (new BigDecimal(FORMAT_WITHOUT_SCALE.format(value)));
	}

	/**
	 * <p>格式化BigDecimal类型的数值.</p>
	 * 
	 * @param value BigDecimal类型的数据
	 * @return BigDecimal类型数据
	 * @author 林仙龙（15361632946） 2013-10-11 下午3:20:16
	 */
	public static BigDecimal format(final BigDecimal value) {
		return value.setScale(SCALE, BigDecimal.ROUND_HALF_UP);
	}

	/**
	 * <p>格式化BigDecimal类型的数值.</p>
	 * 
	 * @param value BigDecimal类型的数据
	 * @return BigDecimal类型数据
	 * @author 林仙龙（15361632946） 2013-10-11 下午3:20:16
	 */
	public static BigDecimal format(final BigDecimal value, int scale) {
		return value.setScale(scale, BigDecimal.ROUND_HALF_UP);
	}

	/**
	 * <p>BigDecimal加法运算.</p>
	 * 
	 * @param d1 加数
	 * @param d2 加数
	 * @return BigDecimal类型
	 * @author 林仙龙（15361632946） 2013-10-11 下午3:20:16
	 */
	public static BigDecimal add(final BigDecimal d1, final BigDecimal d2) {
		return format(d1.add(d2));
	}

	/**
	 * <p>BigDecimal加法运算.</p>
	 * 
	 * @param d1 加数
	 * @param d2 加数
	 * @return BigDecimal类型
	 * @author 林仙龙（15361632946） 2013-10-11 下午3:20:16
	 */
	public static BigDecimal add(final BigDecimal d1, final BigDecimal d2, int scale) {
		return format(d1.add(d2), scale);
	}

	/**
	 * <p>多个BigDecimal类型的数据相加.</p>
	 * 
	 * @param v1
	 * @param vs
	 * @return
	 * @author 林仙龙（15361632946） 2013-10-11 下午3:20:16
	 */
	public static BigDecimal add(final BigDecimal v1, final BigDecimal... vs) {
		BigDecimal sum = BigDecimal.ZERO;
		for (final BigDecimal v : vs) {
			sum = add(sum, v);
		}
		return add(v1, sum);
	}

	/**
	 * <p>多个BigDecimal类型的数据连减.</p>
	 * 
	 * @param v1
	 * @param vs
	 * @return
	 * @author 林仙龙（15361632946） 2013-10-11 下午3:20:16
	 */
	public static BigDecimal subtract(final BigDecimal v1, final BigDecimal... vs) {
		final BigDecimal sum = add(BigDecimal.ZERO, vs);
		return subtract(v1, sum);
	}

	/**
	 * <p>BigDecimal减法运算.</p>
	 * 
	 * @param d1 运算数
	 * @param d2 运算数
	 * @return BigDecimal类型
	 * @author 林仙龙（15361632946） 2013-10-11 下午3:20:16
	 */
	public static BigDecimal subtract(final BigDecimal d1, final BigDecimal d2) {
		return format(d1.subtract(d2));
	}

	/**
	 * <p>BigDecimal减法运算.</p>
	 * 
	 * @param d1 运算数
	 * @param d2 运算数
	 * @return BigDecimal类型
	 * @author 林仙龙（15361632946） 2013-10-11 下午3:20:16
	 */
	public static BigDecimal subtract(final BigDecimal d1, final BigDecimal d2, final int scale) {
		return format(d1.subtract(d2), scale);
	}

	private static int compareBigDecimal(final BigDecimal v1, final BigDecimal v2) {
		return (format(v1).compareTo(format(v2)));
	}

	/**
	 * 
	 * <p>大于.</p>
	 * 
	 * @param v1
	 * @param v2
	 * @return
	 * @author 林仙龙（15361632946） 2013-10-11 下午3:20:16
	 */
	public static boolean gt(final BigDecimal v1, final BigDecimal v2) {
		return compareBigDecimal(v1, v2) > 0;
	}

	/**
	 * <p>大于等于.</p>
	 * 
	 * @param v1
	 * @param v2
	 * @return
	 * @author 林仙龙（15361632946） 2013-10-11 下午3:20:16
	 */
	public static boolean ge(final BigDecimal v1, final BigDecimal v2) {
		return compareBigDecimal(v1, v2) >= 0;
	}

	/**
	 * <p>等于.</p>
	 * 
	 * @param v1
	 * @param v2
	 * @return
	 * @author 林仙龙（15361632946） 2013-10-11 下午3:20:16
	 */
	public static boolean eq(final BigDecimal v1, final BigDecimal v2) {
		return compareBigDecimal(v1, v2) == 0;
	}

	/**
	 * <p>小于.</p>
	 * 
	 * @param v1
	 * @param v2
	 * @return
	 * @author 林仙龙（15361632946） 2013-10-11 下午3:20:16
	 */
	public static boolean lt(final BigDecimal v1, final BigDecimal v2) {
		return compareBigDecimal(v1, v2) < 0;
	}

	/**
	 * <p>小于等于.</p>
	 * 
	 * @param v1
	 * @param v2
	 * @return
	 * @author 林仙龙（15361632946） 2013-10-11 下午3:20:16
	 */
	public static boolean le(final BigDecimal v1, final BigDecimal v2) {
		return compareBigDecimal(v1, v2) <= 0;
	}

	/**
	 * <p>不等于.</p>
	 * 
	 * @param v1
	 * @param v2
	 * @return
	 * @author 林仙龙（15361632946） 2013-10-11 下午3:20:16
	 */
	public static boolean ne(final BigDecimal v1, final BigDecimal v2) {
		return compareBigDecimal(v1, v2) != 0;
	}

	/**
	 * <p>除法操作.</p>
	 * 
	 * @param v1
	 * @param v2
	 * @return
	 * @author 林仙龙（15361632946） 2013-10-11 下午3:20:16
	 */
	public static BigDecimal divide(final BigDecimal v1, final BigDecimal v2) {
		return format(v1.divide(v2));
	}

	/**
	 * <p>除法操作 被除数大于除数(四舍五入) 解决 精度问题</p>
	 * 
	 * @param v1
	 * @param v2
	 * @return
	 * @author 林仙龙（15361632946） 2013-10-11 下午3:20:16
	 */
	public static BigDecimal divideForHalfEven(final BigDecimal v1, final BigDecimal v2) {
		return v1.divide(v2, SCALE, RoundingMode.HALF_EVEN);
	}

	/**
	 * <p>除法操作.</p>
	 * 
	 * @param v1
	 * @param v2
	 * @return
	 * @author 林仙龙（15361632946） 2013-10-11 下午3:20:16
	 */
	public static BigDecimal divide(final BigDecimal v1, final BigDecimal v2, final int scale) {
		return format(v1.divide(v2), scale);
	}

	/** 乘法操作 */
	public static BigDecimal multiply(final BigDecimal v1, final BigDecimal v2) {
		return format(v1.multiply(v2));
	}

	/** 乘法操作 */
	public static BigDecimal multiply(final BigDecimal v1, final BigDecimal v2, final int scale) {
		return format(v1.multiply(v2), scale);
	}

	public static void main(String[] args) {
		System.out.println(DecimalUtil.divideForHalfEven(new BigDecimal(200), new BigDecimal(283)));
		BigDecimal[] aa = new BigDecimal(10).divideAndRemainder(new BigDecimal(3));
		System.out.println(aa);
	}

	/**
	 * 
	 * <p>取余运算</p>
	 * 
	 * @param v1
	 * @param v2
	 * @return
	 * @author 叶新东（18124509759） 2018-1-24 下午5:33:11
	 */
	public static BigDecimal devideAndRemainder(final BigDecimal v1, final BigDecimal v2) {
		return v1.divideAndRemainder(v2)[1];
	}
}
