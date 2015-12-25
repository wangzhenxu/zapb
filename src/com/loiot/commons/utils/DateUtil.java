package com.loiot.commons.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang.time.DateUtils;

/**
 * 此类提供了关于Date类型的一些常用方法。
 * <p>
 * 
 * @author zhengrunjin
 * @since 0.1
 */
public class DateUtil {

	/**
	 * 默认日期格式，"yyyy-MM-dd HH:mm:ss"。
	 */
	public final static String DEFAULT_LONG_FORMAT = "yyyy-MM-dd HH:mm:ss";

	/**
	 * 默认日期格式，"yyyy-MM-dd"。
	 */
	public final static String DEFAULT_SHORT_FORMAT = "yyyy-MM-dd";
	
	/**
	 * 默认日期格式，"yyyy-MM"。
	 */
	public final static String DEFAULT_YM_FORMAT = "yyyy-MM";

	/**
	 * 返回当前时间。
	 * 
	 * <pre>
	 * 与以下调用的行为完全相同： 
	 * 	new Date();
	 * </pre>
	 * 
	 * @return 当前系统时间。
	 */
	public static Date getNow() {
		return new Date();
	}

	/**
	 * 判断一个目标字符串是否是日期类型。
	 * <p>
	 * 当且仅当目标字符串符合{@link DateUtil#DEFAULT_LONG_FORMAT}、
	 * {@link DateUtil#DEFAULT_SHORT_FORMAT}两种格式时返回true，其他情况返回false。
	 * 
	 * <pre>
	 * 示例如下：
	 *  StringUtils.isDate(null);   //return false;
	 *  StringUtils.isDate(""); //return false;
	 *  StringUtils.isDate("2010/03/07");   //return false;
	 *  StringUtils.isDate("2010/03/07 09:10:33");  //return false;
	 *  StringUtils.isDate("2010-03-07");   //return true;
	 *  StringUtils.isDate("2010-03-07 09:10:33");  //return true;
	 * </pre>
	 * 
	 * @param string
	 *            目标字符串。
	 * @return <li>true 字符串是日期格式</li> <li>false 字符串不是日期格式。</li>
	 */
	public static boolean isDate(String string) {
		DateFormat df = null;
		// 长格式
		try {
			df = new SimpleDateFormat(DateUtil.DEFAULT_LONG_FORMAT);
			df.parse(string);
			return true;
		} catch (ParseException e) {
		}
		// 短格式
		try {
			df = new SimpleDateFormat(DateUtil.DEFAULT_SHORT_FORMAT);
			df.parse(string);
			return true;
		} catch (ParseException e1) {
		}
		return false;
	}

	/**
	 * 转换日期到字符串，并以默认格式进行返回。
	 * <p>
	 * 默认格式请参见{@link DateUtil#DEFAULT_LONG_FORMAT}；转换为自定义格式请参见
	 * {@link DateUtil#toString(Date, String)}。
	 * 
	 * <pre>
	 * 与以下调用的行为完全相同： 
	 * 	DateUtils.toString(date,DateUtils.DEFAULT_LONG_FORMAT);
	 * </pre>
	 * 
	 * @param date
	 *            日期实例。
	 * @return 格式化日期字符串。
	 */
	public static String toString(Date date) {
		return toString(date, DateUtil.DEFAULT_LONG_FORMAT);
	}

	/**
	 * 转换日期到字符串，并以自定义格式进行返回。
	 * <p>
	 * format格式参见{@link SimpleDateFormat} 中日期和时间模式的描述。
	 * <p>
	 * 此方法根据传入的format参数对日期进行格式化，当参数date、format为null是，返回StringUtils.EMPTY。
	 * 
	 * <pre>
	 * 示例如下：
	 * 	DateUtils.toString(DateUtils.toDate("2011-11-1 11:11:00"),"yyyy/MM/dd");	//return "2011/11/1";
	 * 	DateUtils.toString(DateUtils.toDate("2011-11-1 11:11:00"),"yyyy/MM/dd HH:mm:ss");	//return "2011/11/1 11:11:00";
	 * </pre>
	 * 
	 * @param date
	 *            日期实例。
	 * @param format
	 *            自定义格式字符串。
	 * @return 自定义格式化日期字符串。
	 */
	public static String toString(Date date, String format) {

		if (date == null) {
			return StringUtil.EMPTY;
		}
		if (format == null) {
			return StringUtil.EMPTY;
		}

		// 开始转换
		try {
			return new SimpleDateFormat(format).format(date);
		} catch (Exception e) {
		}

		return StringUtil.EMPTY;

	}

	/**
	 * 转换时间刻度到日期格式字符串，并以默认格式进行返回。
	 * <p>
	 * 默认格式请参见{@link DateUtil#DEFAULT_LONG_FORMAT}；转换为自定义格式请参见
	 * {@link DateUtil#toString(long, String)}。
	 * 
	 * <pre>
	 * 与以下调用的行为完全相同： 
	 * 	DateUtils.toString(ticks,DateUtils.DEFAULT_LONG_FORMAT);
	 * </pre>
	 * 
	 * @param ticks
	 *            时间刻度。
	 * @return 格式化日期字符串
	 */
	public static String toString(long ticks) {
		return toString(ticks, DateUtil.DEFAULT_LONG_FORMAT);

	}

	/**
	 * 转换时间刻度到日期格式字符串，并以自定义格式进行返回。
	 * <p>
	 * format格式参见{@link SimpleDateFormat} 中日期和时间模式的描述。
	 * 
	 * <pre>
	 * 示例如下：
	 * 	DateUtils.toString(DateUtils.toDate("2011-11-1 11:11:00").getTime(),"yyyy/MM/dd");	//return "2011/11/1";
	 * 	DateUtils.toString(DateUtils.toDate("2011-11-1 11:11:00").getTime(),"yyyy/MM/dd HH:mm:ss");	//return "2011/11/1 11:11:00";
	 * </pre>
	 * 
	 * @param ticks
	 *            时间刻度。
	 * @param format
	 *            自定义格式字符串。
	 * @return 自定义格式化日期字符串。
	 */
	public static String toString(long ticks, String format) {

		if (format == null) {
			throw new NullPointerException("'format' is null");
		}

		// 开始转换
		try {
			return toString(new Date(ticks), format);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;

	}

	/**
	 * 转换日期格式字符串为日期实例。
	 * <p>
	 * 当字符串不符合默认格式{@link #DEFAULT_SHORT_FORMAT}或{@link #DEFAULT_LONG_FORMAT}或{@link #DEFAULT_YM_FORMAT}
	 * 是返回null，反之转换成Date对象返回。
	 * 
	 * @param dateString
	 *            日期格式字符串。
	 * @return 日期实例。
	 * @exception NullPointerException
	 *                当dateString为空时抛出此异常。
	 */
	public static Date toDate(String dateString) {
		// 字符串为空
		if (dateString == null) {
			throw new NullPointerException("'dateString' is null");
		}
		// 短格式转换，如果失败进行长格式转化
		try {
			return new SimpleDateFormat(DateUtil.DEFAULT_SHORT_FORMAT).parse(dateString);
		} catch (ParseException e) {
		}
		// 长格式转换，如果失败返回null
		try {
			return new SimpleDateFormat(DateUtil.DEFAULT_LONG_FORMAT).parse(dateString);
		} catch (ParseException e) {
		}
		
		// YYYY-MM，如果失败返回null
		try {
			return new SimpleDateFormat(DateUtil.DEFAULT_YM_FORMAT).parse(dateString);
		} catch (ParseException e) {
		}
		
		return null;
	}

	/**
	 * 根据自定义的format转换日期格式字符串到日期实例。
	 * <p>
	 * 此方法转换一个符合format格式的字符串到日期类型。当字符串不符合format格式转换结果将返回null。如果传入的字符串、
	 * 格式化format参数为null，则转换结果 同样也将返回null。
	 * 
	 * <pre>
	 * Examples:
	 * 
	 *  Date date=DateUtils.toDate("20110412","yyyyMMdd");	 
	 *  DateUtils.toString(date,DateUtils.DEFAULT_SHORT_FORMAT);	//return "2011-04-12";
	 * 
	 *  DateUtils.toDate(null,"yyyyMMdd");	//return null;
	 *  DateUtils.toDate("20110412",null);	//return null;
	 *  DateUtils.toDate("2011-04-12","yyyyMMdd");	//return null;
	 *  DateUtils.toDate("2011-04-12","yyyy/MM/dd");	//return null;
	 * </pre>
	 * 
	 * @param dateString
	 *            日期格式字符串。
	 * @param format
	 *            字符串格式。
	 * @return 日期实例或null。
	 */
	public static Date toDate(String dateString, String format) {
		if (dateString == null) {
			return null;
		}
		if (format == null) {
			return null;
		}
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			sdf.setLenient(false); // 严格按照格式检验，如果不符合将抛出ParseException
			return sdf.parse(dateString);
		} catch (ParseException e) {
		}
		return null;
	}

	/**
	 * 通过指定毫秒数（以表示自从标准基准时间（称为“历元（epoch）”，即 1970 年 1 月 1 日 00:00:00
	 * GMT）以来的指定毫秒数）得到一个日期实例。
	 * <p>
	 * 
	 * @param millisecond
	 *            毫秒数，其他情况返回计算后的Date对象。
	 * @return 日期实例。
	 */
	public static Date toDate(long millisecond) {
		return new Date(millisecond);
	}

	/**
	 * 添加或减去指定的年数 。
	 * <p>
	 * 当传入日期和years符合计算规则时返回Date对象，其他情况返回null。
	 * <p>
	 * 此方法通过对给定的日期的年份进行操作来获得计算后的日期实例。如果传入的Date对象为null，则返回null。
	 * 如果传入的years大于10000或者小于等于传入日期的年份部分，
	 * 则同样返回null。只有在传入years符合计算规则，并且传入的date实例不为null时，才进行计算。并将计算后的日期实例返回。
	 * 
	 * <pre>
	 * Examples:
	 * 
	 * Date date=DateUtils.toDate("20110412","yyyyMMdd");
	 * 
	 * Date addedYearDate=ateUtils.addYear(date,1);
	 * DateUtils.getYear(addedYearDate);	//return 2012;
	 * 
	 * Date addedYearDate=ateUtils.addYear(date,10000);
	 * DateUtils.getYear(addedYearDate);	//return 12011;
	 * 
	 * Date addedYearDate=ateUtils.addYear(date,-2010);
	 * DateUtils.getYear(addedYearDate);	//return 1;
	 * 
	 * DateUtils.addYear(date,10001);	//return null;
	 * DateUtils.addYear(date,-2011);	//return null;
	 * DateUtils.addYear(date,-2012);	//return null;
	 * </pre>
	 * 
	 * @param date
	 *            给定日期。
	 * @param days
	 *            年数。
	 * @return 新日期实例。
	 */
	public static Date addYear(Date date, int years) {
		if (date == null) {
			return null;
		}
		if (years > 10000) {
			return null;
		}
		if (years < 0 && Math.abs(years) >= DateUtil.getYear(date)) {
			return null;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.YEAR, years);
		return calendar.getTime();
	}

	/**
	 * 添加或减去指定的月份数 。
	 * <p>
	 * 当传入日期和months符合计算规则时返回Date对象，其他情况返回null。
	 * <p>
	 * 此方法通过对给定的日期的月份进行操作来获得计算后的日期实例。如果传入的Date对象为null，则返回null。
	 * 如果传入的months大于10000或者小于-10000，
	 * 则同样返回null。只有在传入months符合计算规则，并且传入的date实例不为null时，才进行计算。并将计算后的日期实例返回。
	 * 
	 * <pre>
	 * Examples:
	 * 
	 * Date date=DateUtils.toDate("20110412","yyyyMMdd");
	 * 
	 * Date addedMonthDate=DateUtils.addMonth(date,1);
	 * DateUtils.getMonth(addedMonthDate);	//return 5;
	 * 
	 * Date addedMonthDate=DateUtils.addMonth(date,10000);
	 * DateUtils.getMonth(addedMonthDate);	//return 8;	 备注：10000%12,余4,再加上当前日期的4月份等于8
	 * 
	 * Date addedMonthDate=DateUtils.addMonth(date,-1);
	 * DateUtils.getMonth(addedMonthDate);	//return 3;
	 * 
	 * Date addedMonthDate=DateUtils.addMonth(date,-12);
	 * DateUtils.getMonth(addedMonthDate);	//return 4;
	 * 
	 * DateUtils.addMonth(date,10001);	//return null;
	 * DateUtils.addMonth(date,-10001);	//return null;
	 * </pre>
	 * 
	 * @param date
	 *            给定日期。
	 * @param days
	 *            月份数。
	 * @return 新日期实例。
	 */
	public static Date addMonth(Date date, int months) {
		if (date == null) {
			return null;
		}
		if (months > 10000) {
			return null;
		}
		if (months < 0 && Math.abs(months) > 10000) {
			return null;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, months);
		return calendar.getTime();
	}

	/**
	 * 添加或减去指定的天数 。
	 * <p>
	 * 当date参数为空时返回null，其他情况返回计算后的Date对象。
	 * 
	 * <pre>
	 * 示例如下：
	 * 	Date date=DateUtils.toDate("2011-01-21")
	 * 	DateUtils.addDays(date,10);		//return "2011-01-31";
	 * 	DateUtils.addDays(date,-10);	//return "2011-01-11";
	 * </pre>
	 * 
	 * @param date
	 *            给定日期。
	 * @param days
	 *            天数。
	 * @return 新日期实例。
	 */
	public static Date addDays(Date date, int days) {
		if (date == null) {
			return null;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, days);
		return calendar.getTime();
	}

	/**
	 * 添加或减去指定的小时数 。
	 * <p>
	 * 当date参数为空时返回null，其他情况返回计算后的Date对象。
	 * 
	 * <pre>
	 * 示例如下：
	 * 	Date date=DateUtils.toDate("2011-01-21 20:00:00")
	 * 	DateUtils.addHours(date,10);	//return "2011-01-22 06:00:00";
	 * 	DateUtils.addHours(date,-10);	//return "2011-01-21 10:00:00";
	 * </pre>
	 * 
	 * @param date
	 *            给定日期。
	 * @param hours
	 *            小时数。
	 * @return 新日期实例。
	 */
	public static Date addHours(Date date, int hours) {
		if (date == null) {
			return null;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.HOUR, hours);
		return calendar.getTime();
	}

	/**
	 * 添加或减去指定的分钟数 。
	 * <p>
	 * 当date参数为空时返回null，其他情况返回计算后的Date对象。
	 * 
	 * <pre>
	 * 示例如下：
	 * 	Date date=DateUtils.toDate("2011-01-21 20:30:00")
	 * 	DateUtils.addMinutes(date,10);	//return "2011-01-21 20:40:00";
	 * 	DateUtils.addMinutes(date,-10);	//return "2011-01-21 20:20:00";
	 * </pre>
	 * 
	 * @param date
	 *            给定日期。
	 * @param minutes
	 *            分钟数。
	 * @return 新日期实例。
	 */
	public static Date addMinutes(Date date, int minutes) {
		if (date == null) {
			return null;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MINUTE, minutes);
		return calendar.getTime();
	}

	/**
	 * 添加或减去指定的秒数 。
	 * <p>
	 * 当date参数为空时返回null，其他情况返回计算后的Date对象。
	 * 
	 * <pre>
	 * 示例如下：
	 * 	Date date=DateUtils.toDate("2011-01-21 20:30:40")
	 * 	DateUtils.addSeconds(date,10);	//return "2011-01-21 20:30:50";
	 * 	DateUtils.addSeconds(date,-10);	//return "2011-01-21 20:30:30";
	 * </pre>
	 * 
	 * @param date
	 *            给定日期。
	 * @param seconds
	 *            秒数。
	 * @return 新日期实例。
	 */
	public static Date addSeconds(Date date, int seconds) {
		if (date == null) {
			return null;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.SECOND, seconds);
		return calendar.getTime();
	}

	/**
	 * 添加或减去指定时间。
	 * <p>
	 * 这个方法根据传入的参数数量，依次添加或减去
	 * 年数、月数、天数、小时数、分钟数、秒数。当date参数为空时返回null，其他情况返回计算后的Date对象。
	 * <p>
	 * 此方法使用Calendar进行计算，为给定日期添加时间。is参数是动态数组，根据传入的长度对日期进行计算。当传入正数则在对应的时间域上添加时间，
	 * 传入负数则在对应的时间域上减去时间。 此方法没有规定临界值，不同于DateUtils.addYear、DateUtils.addMonth。
	 * 一切临界值和计算公式都由Calendar处理。
	 * 
	 * <pre>
	 * 示例如下：
	 * Date date=DateUtils.toDate("2011/04/12 12:30:30","yyyy/MM/dd HH:mm:ss");
	 * 
	 * Date addedDate=DateUtils.addTime(date, 10,3);
	 * 	DateUtils.toString(addedDate,"yyyy/MM/dd HH:mm:ss"); //return "2021/07/12 12:30:30";
	 * 
	 *  addedDate=DateUtils.addTime(date, -10,3);
	 * 	DateUtils.toString(addedDate,"yyyy/MM/dd HH:mm:ss"); //return "2001/07/12 12:30:30";
	 * 
	 *  addedDate=DateUtils.addTime(date, 10,3,10,10,20,20);
	 *  DateUtils.toString(addedDate,"yyyy/MM/dd HH:mm:ss"); //return "2021/07/22 22:50:50";
	 * </pre>
	 * 
	 * @param date
	 *            给定日期。
	 * @param is
	 *            时间参数列表。
	 * @return 新日期实例。
	 * @since commons v0.2
	 */
	public static Date addTime(Date date, int... is) {
		if (date == null) {
			return null;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		if (is.length > 0) {
			calendar.add(Calendar.YEAR, is[0]);
		}
		if (is.length > 1) {
			calendar.add(Calendar.MONTH, is[1]);
		}
		if (is.length > 2) {
			calendar.add(Calendar.DATE, is[2]);
		}
		if (is.length > 3) {
			calendar.add(Calendar.HOUR, is[3]);
		}
		if (is.length > 4) {
			calendar.add(Calendar.MINUTE, is[4]);
		}
		if (is.length > 5) {
			calendar.add(Calendar.SECOND, is[5]);
		}
		return calendar.getTime();
	}

	/**
	 * 返回给定日期是的年部分。
	 * <p>
	 * 如果提取失败返回-1。
	 * 
	 * <pre>
	 * 示例如下：
	 * 	Date date=DateUtils.toDate("2011-02-21")
	 * 	DateUtils.getYear(date);	//return 2011;
	 * 	DateUtils.getYear(null);	//return -1;
	 * </pre>
	 * 
	 * @param date
	 *            给定日期。
	 * @return 年部分整数。
	 * @since commons v0.2
	 */
	public static int getYear(Date date) {
		if (date == null) {
			return -1;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.YEAR);
	}

	/**
	 * 返回给定日期是的月部分。
	 * <p>
	 * 如果提取失败返回-1。
	 * 
	 * <pre>
	 * 示例如下：
	 * 	Date date=DateUtils.toDate("2011-02-21")
	 * 	DateUtils.getMonth(date);	//return 2;
	 * 	DateUtils.getMonth(null);	//return -1;
	 * </pre>
	 * 
	 * @param date
	 *            给定日期。
	 * @return 月部分整数。
	 * @since commons v0.2
	 */
	public static int getMonth(Date date) {
		if (date == null) {
			return -1;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.MONTH) + 1;
	}

	/**
	 * 返回给定日期是的天部分。
	 * <p>
	 * 如果提取失败返回-1。
	 * 
	 * <pre>
	 * 示例如下：
	 * 	Date date=DateUtils.toDate("2011-02-21")
	 * 	DateUtils.getDay(date);	//return 21;
	 * 	DateUtils.getDay(null);	//return -1;
	 * </pre>
	 * 
	 * @param date
	 *            给定日期。
	 * @return 天部分整数。
	 * @since commons v0.2
	 */
	public static int getDay(Date date) {
		if (date == null) {
			return -1;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * 返回给定日期是的小时部分。
	 * <p>
	 * 如果提取失败返回-1。
	 * 
	 * <pre>
	 * 示例如下：
	 * 	Date date=DateUtils.toDate("2011-02-21 23:59:59")
	 * 	DateUtils.getHours(date);	//return 23;
	 * 	DateUtils.getHours(null);	//return -1;
	 * </pre>
	 * 
	 * @param date
	 *            给定日期。
	 * @return 小时部分整数。
	 * @since commons v0.2
	 */
	public static int getHours(Date date) {
		if (date == null) {
			return -1;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.HOUR_OF_DAY);
	}

	/**
	 * 返回给定日期是的分钟部分。
	 * <p>
	 * 如果提取失败返回-1。
	 * 
	 * <pre>
	 * 示例如下：
	 * 	Date date=DateUtils.toDate("2011-02-21 23:59:59")
	 * 	DateUtils.getMinutes(date);	//return 59;
	 * 	DateUtils.getMinutes(null);	//return -1;
	 * </pre>
	 * 
	 * @param date
	 *            给定日期。
	 * @return 分钟部分整数。
	 * @since commons v0.2
	 */
	public static int getMinutes(Date date) {
		if (date == null) {
			return -1;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.MINUTE);
	}

	/**
	 * 返回给定日期是的秒部分。
	 * <p>
	 * 如果提取失败返回-1。
	 * 
	 * <pre>
	 * 示例如下：
	 * 	Date date=DateUtils.toDate("2011-02-21 23:59:59")
	 * 	DateUtils.getSeconds(date);	//return 59;
	 * 	DateUtils.getSeconds(null);	//return -1;
	 * </pre>
	 * 
	 * @param date
	 *            给定日期。
	 * @return 秒部分整数。
	 * @since commons v0.2
	 */
	public static int getSeconds(Date date) {
		if (date == null) {
			return -1;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.SECOND);
	}

	/**
	 * 返回给定日期是一年当中的第几天。
	 * 
	 * <pre>
	 * 示例如下：
	 * 	Date date=DateUtils.toDate("2011-02-21")
	 * 	DateUtils.getDayOfYear(date);	//return 52;
	 * </pre>
	 * 
	 * @param date
	 *            给定日期。
	 * @return 返回给定日期是一年当中的第几天。
	 */
	public static int getDayOfYear(Date date) {
		if (date == null) {
			throw new NullPointerException("'date' is null.");
		}
		SimpleDateFormat sdf = new SimpleDateFormat("D");
		String day = sdf.format(date);
		return Integer.parseInt(day);
	}

	/**
	 * 返回给定日期是一个月当中的第几天。
	 * 
	 * <pre>
	 * 示例如下：
	 * 	Date date=DateUtils.toDate("2011-02-21")
	 * 	DateUtils.getDayOfMonth(date);	//return 21;
	 * </pre>
	 * 
	 * @param date
	 *            给定日期。
	 * @return 返回给定日期是一个月当中的第几天。
	 */
	public static int getDayOfMonth(Date date) {
		if (date == null) {
			throw new NullPointerException("'date' is null.");
		}
		SimpleDateFormat sdf = new SimpleDateFormat("d");
		String day = sdf.format(date);
		return Integer.parseInt(day);
	}

	/**
	 * 返回给定日期是一周当中的第几天。
	 * <p>
	 * 返回天数从星期日算起，以下是对应列表:
	 * <ul>
	 * <li>星期日 return 1;</li>
	 * <li>星期一 return 2;</li>
	 * <li>星期二 return 3;</li>
	 * <li>星期三 return 4;</li>
	 * <li>星期四 return 5;</li>
	 * <li>星期五 return 6;</li>
	 * <li>星期六 return 7;</li>
	 * </ul>
	 * 
	 * <pre>
	 * 示例如下：
	 * 	Date date=DateUtils.toDate("2011-02-21")
	 * 	DateUtils.getDayOfWeek(date);	//return 2;
	 * 	Date date=DateUtils.toDate("2011-04-16")
	 * 	DateUtils.getDayOfWeek(date);	//return 7;
	 * </pre>
	 * 
	 * @param date
	 *            给定日期。
	 * @return 返回给定日期是一周当中的第几天。
	 */
	public static int getDayOfWeek(Date date) {
		if (date == null) {
			throw new NullPointerException("'date' is null.");
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.DAY_OF_WEEK);
	}
	
	
    
      
    /**  
     * @param date1 需要比较的时间 不能为空(null),需要正确的日期格式  
     * @param date2 被比较的时间  为空(null)则为当前时间  
     * @param stype 返回值类型   0为多少天，1为多少个月，2为多少年  
     * @return  
     */ 
    public static int compareDate(String date1,String date2,int stype){  
        int n = 0;  
          
        String[] u = {"天","月","年"};  
        String formatStyle = stype==1?"yyyy-MM":"yyyy-MM-dd";  
          
        date2 = date2==null?DateUtil.getCurrentDate():date2;  
          
        DateFormat df = new SimpleDateFormat(formatStyle);  
        Calendar c1 = Calendar.getInstance();  
        Calendar c2 = Calendar.getInstance();  
        try {  
            c1.setTime(df.parse(date1));  
            c2.setTime(df.parse(date2));  
        } catch (Exception e3) {  
            System.out.println("wrong occured");  
        }  
        //List list = new ArrayList();  
        while (!c1.after(c2)) {                     // 循环对比，直到相等，n 就是所要的结果  
            //list.add(df.format(c1.getTime()));    // 这里可以把间隔的日期存到数组中 打印出来  
            n++;  
            if(stype==1){  
                c1.add(Calendar.MONTH, 1);          // 比较月份，月份+1  
            }  
            else{  
                c1.add(Calendar.DATE, 1);           // 比较天数，日期+1  
            }  
        }  
          
        n = n-1;  
          
        if(stype==2){  
            n = (int)n/365;  
        }     
          
        System.out.println(date1+" -- "+date2+" 相差多少"+u[stype]+":"+n);        
        return n;  
    }  
    /**  
     * 得到当前日期  
     * @return  
     */ 
    public static String getCurrentDate() {  
        Calendar c = Calendar.getInstance();  
        Date date = c.getTime();  
        SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd");  
        return simple.format(date);  
    }  
    /**
     * 根据天（返回，指定日期的开始时间)
     * @param date
     * @return
     */
    public static Date getCurrDateStartTime(Date date){
        String startTime = DateUtil.toString(new Date(),DateUtil.DEFAULT_SHORT_FORMAT)+" 00:00:00";
        return DateUtil.toDate(startTime,DateUtil.DEFAULT_LONG_FORMAT);
    }
    /**
     * 根据天（返回，指定日期的结束时间)
     * @param date
     * @return
     */
    public static Date getCurrDateEndTime(Date date){
        String endTime = DateUtil.toString(new Date(),DateUtil.DEFAULT_SHORT_FORMAT)+" 23:59:59";
        return DateUtil.toDate(endTime,DateUtil.DEFAULT_LONG_FORMAT);
    }
    
    public static void main(String[] args) {  
        String date = "2006-06-12";  
          
      /*  DateUtil.compareDate(date, null, 0);  
        DateUtil.compareDate(date, null, 1);  */
        //DateUtil.compareDate(date, null, 2);  
          
        date = "2006-07-03";          
       /* DateUtil.compareDate(date, null, 0);  
        DateUtil.compareDate(date, null, 1);  */
      //  DateUtil.compareDate(date, null, 2);  
      /*  DateUtil.compareDate(date, "2009-06-01", 0);  
        DateUtil.compareDate(date, "2009-06-01", 1);  */
        //DateUtil.compareDate(date, "2009-06-01", 2); 
        
        
    }  
} 

