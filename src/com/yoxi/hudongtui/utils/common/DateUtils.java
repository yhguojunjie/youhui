/**
 * 
 * 类描述：日期操作工具类
 */
package com.yoxi.hudongtui.utils.common;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

/**
 * <pre>
 * &lt;b&gt;&lt;font color=&quot;blue&quot;&gt;DateUtils&lt;/font&gt;&lt;/b&gt;
 * </pre>
 * 
 * <pre>
 * &lt;b&gt; --描述说明--&lt;/b&gt;
 * </pre>
 * 
 * <pre>
 * 日期操作工具类
 * </pre>
 * 
 * <pre>
 * &lt;b&gt;--样例--&lt;/b&gt;
 *   DateUtils.method();
 * </pre>
 * 
 * JDK版本：JDK1.5
 * 
 * @author <b>limj</b>
 */
public class DateUtils extends org.apache.commons.lang.time.DateUtils {

	private DateUtils() {

	}

	/** 数据库存储的时间格式串，如yyyymmdd 或yyyymmddHHMiSS */
	public static final int DB_STORE_DATE = 1;

	/** 用连字符-分隔的时间时间格式串，如yyyy-mm-dd 或yyyy-mm-dd HH:Mi:SS */
	public static final int HYPHEN_DISPLAY_DATE = 2;

	/** 用连字符.分隔的时间时间格式串，如yyyy.mm.dd 或yyyy.mm.dd HH:Mi:SS */
	public static final int DOT_DISPLAY_DATE = 3;

	/** 用中文字符分隔的时间格式串，如yyyy年mm月dd 或yyyy年mm月dd HH:Mi:SS */
	public static final int CN_DISPLAY_DATE = 4;

	public static final int DB_STORE_MONTH = 5;

	/** 数据库存储的时间格式串，时间数字串 */
	public static final int DB_STORE_DATE_SPLIT = 1;

	/** 数据库存储的时间格式串，如yyyyMMdd */
	public static final String DB_STORE_DATE_FMT = "yyyyMMdd";

	/** 数据库存储的时间格式串，如yyyyMMddHHMiSS */
	public static final String DB_STORE_DATE_FULL = "yyyyMMddHHmmss";

	/** 数据库存储的时间格式串，如yyyyMM */
	public static final String DB_STORE_DATE_MONTH = "yyyyMM";

	/** 用连字符-分隔的时间格式串 */
	public static final int LINK_DISPLAY_DATE_SPLIT = 2;

	/** 用连字符-分隔的时间格式串，如yyyy-MM-dd */
	public static final String LINK_DISPLAY_DATE = "yyyy-MM-dd";

	/** 用连字符-分隔的时间格式串，如yyyy-MM-dd HH:Mi:SS */
	public static final String LINK_DISPLAY_DATE_FULL = "yyyy-MM-dd HH:mm:ss";

	/** 用连字符-分隔的时间格式串，如yyyy-MM */
	public static final String LINK_DISPLAY_DATE_MONTH = "yyyy-MM";

	/** 用连字符.分隔的时间格式串 */
	public static final int DOT_DISPLAY_DATE_SPLIT = 3;

	/** 用连字符.分隔的时间格式串，如yyyy.MM.dd */
	public static final String DOT_DISPLAY_DATE_FMT = "yyyy.MM.dd";

	/** 用连字符.分隔的时间格式串，如yyyy.MM.dd HH:Mi:SS */
	public static final String DOT_DISPLAY_DATE_FULL = "yyyy.MM.dd HH:mm:ss";

	/** 用连字符.分隔的时间格式串，如yyyy.MM */
	public static final String DOT_DISPLAY_DATE_MONTH = "yyyy.MM";

	/** 用中文字符分隔的时间格式串 */
	public static final int CN_DISPLAY_DATE_SPLIT = 4;
	/** 用中文字符分隔的时间格式串 */
	public static final int CN_DISPLAY_TIME_SPLIT = 5;
	/** 用中文字符分隔的时间格式串，如yyyy年MM月dd日 */
	public static final String CN_DISPLAY_DATE_FMT = "yyyy年MM月dd日";

	/** 用中文字符分隔的时间格式串，如yyyy年MM月dd日 HH时Mi分SS秒 */
	public static final String CN_DISPLAY_TIME_FULL = "yyyy年MM月dd日 HH时mm分ss秒";

	/** 用中文字符分隔的时间格式串，如yyyy年MM月dd日 HH:Mi:SS */
	public static final String CN_DISPLAY_DATE_FULL = "yyyy年MM月dd日 HH:mm:ss";
	/** 用中文字符分隔的时间格式串，如yyyy年MM月 */
	public static final String CN_DISPLAY_DATE_MONTH = "yyyy年MM月";

	/**
	 * 根据字符串返回指定格式的Timestamp时间
	 * 
	 * @param formatType
	 * @param length
	 * @return
	 * @author:wql
	 */

	public static Timestamp getTimeStampFromString(String timeString) {
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		format.setLenient(false);
		// 要转换字符串 str_test
		String str_test = timeString;
		Timestamp ts = null;
		try {
			ts = new Timestamp(format.parse(str_test).getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return ts;
	}

	public static Timestamp getTimestampNow() {
		return new Timestamp((new Date()).getTime());
	}

	/**
	 * 获取指定时间的周一
	 * 
	 * @param date
	 * @return
	 * @throws Exception
	 */
	public static Date getMonday(Date date) throws Exception {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		return cal.getTime();
	}

	/**
	 * 获取指定时间的周日
	 * 
	 * @param date
	 * @return
	 * @throws Exception
	 */
	public static Date getSunday(Date date) throws Exception {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		System.out.println(cal.get(RANGE_WEEK_SUNDAY));
		cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		return cal.getTime();
	}

	/**
	 * 得到本周周一
	 * 
	 * @return
	 */
	public static Date getMondayOfThisWeek() {
		Calendar calendar = Calendar.getInstance();
		int dayofweek = calendar.get(Calendar.DAY_OF_WEEK) - 1;
		if (dayofweek == 0)
			dayofweek = 7;
		calendar.add(Calendar.DATE, -dayofweek + 1);
		return calendar.getTime();
	}

	/**
	 * 获取给定日期的yyyy-MM-dd 00:00:00的时间
	 * 
	 * @param date
	 * @return
	 * @throws Exception
	 */
	public static Date getCurrentDayStart(Date date) throws Exception {
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
		String formatDate = format.format(date);
		return format.parse(formatDate);
	}

	/**
	 * 获取给定日期的yyyy-MM-dd 23:59:59的时间
	 * 
	 * @param date
	 * @return
	 * @throws Exception
	 */
	public static Date getCurrentDayEnd(Date date) throws Exception {
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd 23:59:59");
		String formatDate = format.format(date);
		return format.parse(formatDate);
	}

	/**
	 * 返回当前时间大于前面一个时间的分钟数
	 * 
	 * @param before
	 * @param now
	 * @return
	 */
	public static long minuteBetween(Date before, Date now) {
		if (now == null || before == null) {
			return 0;
		}
		if (now.after(before)) {
			long differ = now.getTime() - before.getTime();
			return differ / 60000;
		}
		return 0;
	}

	/**
	 * 中信微信 跟当前日期相差的描述
	 * 
	 * @param before
	 * @param now
	 * @return
	 */
	public static String zxWxBetweenDescribe(Date before, Date now) {
		long minute = minuteBetween(before, now);
		if (minute <= 1) {// 一分钟以内
			return "刚刚";
		} else if (minute < 60) {// 一小时以内
			return minute + "分钟前";
		} else if (minute < 1440) {// 一天以内 60 * 24
			return minute / 60 + "小时前";
		} else if (minute < 2880) {// 2天以内 60 * 24 * 2
			return "昨天";
		} else if (minute < 4320) {// 3天以内 60 * 24 * 3
			return "前天";
		} else {
			return getDateStr(before, "yyyy-MM-dd HH:mm");
		}
	}

	/**
	 * 根据相应的长度，及格式化分隔符返回日期类型
	 * 
	 * @param formatType
	 * @param length
	 * @return
	 * @author:limj
	 */
	private static String getFormatType(int formatSplitType, int length) {
		String formatStr = null;
		switch (formatSplitType) {
		case LINK_DISPLAY_DATE_SPLIT:
			switch (length) {
			case 14:
				formatStr = LINK_DISPLAY_DATE_FULL;
				break;
			default:
				formatStr = LINK_DISPLAY_DATE;
			}
			break;
		case DOT_DISPLAY_DATE_SPLIT:
			switch (length) {
			case 14:
				formatStr = DOT_DISPLAY_DATE_FULL;
				break;
			default:
				formatStr = DOT_DISPLAY_DATE_FMT;
			}
			break;
		case CN_DISPLAY_DATE_SPLIT:
			switch (length) {
			case 14:
				formatStr = CN_DISPLAY_DATE_FULL;
				break;
			default:
				formatStr = CN_DISPLAY_DATE_FMT;
			}
			break;
		case CN_DISPLAY_TIME_SPLIT:
			switch (length) {
			case 14:
				formatStr = CN_DISPLAY_TIME_FULL;
				break;
			default:
				formatStr = CN_DISPLAY_DATE_FMT;
			}
			break;
		default:
			switch (length) {
			case 14:
				formatStr = DB_STORE_DATE_FULL;
				break;
			default:
				formatStr = DB_STORE_DATE_FMT;
			}
			break;
		}
		return formatStr;
	}

	/**
	 * 得到格式化时间串
	 * 
	 * @param date
	 *            指定时间
	 * @param formatType
	 *            时间格式的类型
	 * @return 指定时间的格式化时间串
	 */
	private static String getDateStr(Date date, String formatStr) {
		SimpleDateFormat fomate = new SimpleDateFormat(formatStr);
		return fomate.format(date);
	}

	/**
	 * 将日期格式串(数字字符串)转换为各种显示的格式
	 * 
	 * @param dateStr
	 *            最小6位，最大14位的数据库存储格式时间串如:20041212
	 * @param formatType
	 *            时间格式的类型
	 * @return 格式化的时间串
	 */
	private static String toDisplayStr(String dateStr, String formatStr) {
		dateStr = dateStr.replaceAll("[^0-9]", "");
		SimpleDateFormat fomateDate = null;
		switch (dateStr.length()) {
		case 4:
			fomateDate = new SimpleDateFormat("yyyy");
			break;
		case 6:
			fomateDate = new SimpleDateFormat("yyyyMM");
			break;
		case 8:
			fomateDate = new SimpleDateFormat("yyyyMMdd");
			break;
		case 10:
			fomateDate = new SimpleDateFormat("yyyyMMddHH");
			break;
		case 12:
			fomateDate = new SimpleDateFormat("yyyyMMddHHmm");
			break;
		case 14:
			fomateDate = new SimpleDateFormat("yyyyMMddHHmmss");
			break;
		}
		try {
			return getDateStr(fomateDate.parse(dateStr), formatStr);
		} catch (ParseException e) {
			return StringUtils.toVisualString(dateStr);
		}
	}

	/**
	 * 时间字符格式化检验
	 * 
	 * @param dateStr
	 * @return
	 * @author:chenxy
	 */
	private static boolean checkDateStr(String dateStr) {
		if (dateStr != null) {
			String dateStrCheck = dateStr.replaceAll("[^0-9]", "");
			if (dateStrCheck.length() >= 4 && dateStrCheck.length() <= 14 && dateStrCheck.length() % 2 == 0) {
				return true;
			}
		}
		return false;
	}

	/** 获取当前日期字符串 yyyyMMdd */
	public static String getCurrentDate() {
		Date date = new Date();
		return getDateStr(date, DB_STORE_DATE_FMT);
	}

	/** 获取当前日期+时间字符串 yyyyMMddHHmmss */
	public static String getCurrentTime() {
		Date date = new Date();
		return getDateStr(date, DB_STORE_DATE_FULL);
	}

	/**
	 * 日期+时间字符串 yyyyMMddHHmmss转为日期
	 * 
	 * @param date
	 * @return
	 */
	public static Date converDate(String date) {
		try {
			return getDate(date, LINK_DISPLAY_DATE_FULL);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 根据格式化类型将时间类型的数据格式化，并以字符串形式返回。
	 * 
	 * @param object
	 * @param formatType
	 * @return
	 * @author:chenxy
	 */
	public static String getDateStr(Object object, String formatStr) {
		if (object == null) {
			return null;
		}
		String resultDateStr = null;
		if (object instanceof java.util.Date) {
			resultDateStr = getDateStr((Date) object, formatStr);
		} else if (object instanceof String) {
			String dateStr = (String) object;
			if (!checkDateStr(dateStr)) {
				return null;
			}
			resultDateStr = toDisplayStr(dateStr, formatStr);
		} else if (object instanceof Integer) {
			String dateStr = ((Integer) object).toString();
			if (!checkDateStr(dateStr)) {
				return null;
			}
			resultDateStr = toDisplayStr(dateStr, formatStr);
		}
		return resultDateStr;
	}

	/**
	 * 根据格式化类型将时间类型的数据格式化，并以字符串形式返回。
	 * 
	 * @param object
	 * @param formatType
	 * @return
	 * @author:chenxy
	 */
	public static String getDateStrByStr(String dateStr, int formatSliptType) {
		if (!checkDateStr(dateStr)) {
			return dateStr;
		}
		dateStr = dateStr.replaceAll("[^0-9]", "");
		String formateType = getFormatType(formatSliptType, dateStr.length());
		return toDisplayStr(dateStr, formateType);
	}

	/**
	 * 转化Date类型
	 * 
	 * @param dateStr
	 * @return
	 * @author:chenxy
	 */
	public static Date changeStrToDate(String dateStr) {
		if (!checkDateStr(dateStr)) {
			return null;
		}
		dateStr = dateStr.replaceAll("[^0-9]", "");
		String formatStr = getFormatType(DB_STORE_DATE_SPLIT, dateStr.length());
		SimpleDateFormat fomateDate = new SimpleDateFormat(formatStr);
		try {
			return fomateDate.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 获取某个分钟之后的时间
	 * 
	 * @param dateStr
	 *            String 时间字符串
	 * @param later
	 *            long 分钟
	 * @return
	 * @author:chenxy
	 */
	public static Date getLaterDate(String dateStr, long later) {
		Date date = changeStrToDate(dateStr);
		date.setTime(date.getTime() + later * 60 * 1000);
		return date;
	}
	/**
	 * 获取某个秒之后的时间
	 * 
	 * @param dateStr
	 *            Date 时间
	 * @param later
	 *            long miao
	 * @return
	 * @author:chenxy
	 */
	public static Date getLaterDatess(Date dateStr, long later) {
		Date date  = new Date(dateStr.getTime() + later * 1000);
		return date;
	}

	public static void convertDate(String datestr) {
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd HH时mm分ss秒");
			Date date = format.parse(datestr);
			System.out.println(date.toString());
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	/**
	 * 获取某个分钟之后的时间
	 * 
	 * @param date
	 *            Date 时间
	 * @param later
	 *            long 分钟
	 * @return
	 * @author:chenxy
	 */
	public static Date getLaterDate(Date date, long later) {
		date.setTime(date.getTime() + later * 60 * 1000);
		return date;
	}

	public static Date getCurrentDate(String formatStr) {
		try {
			SimpleDateFormat format = new SimpleDateFormat(formatStr);
			String result = format.format(new Date());
			return format.parse(result);
		} catch (ParseException e) {
		}
		return null;
	}

	/**
	 * 获取某个分钟之后的时间字符串
	 * 
	 * @param dateStr
	 *            String 时间字符串
	 * @param later
	 *            int 分钟
	 * @param formatStr
	 *            String 格式化字符串类型
	 * @return
	 * @author:chenxy
	 */
	public static String getLaterDateStr(String dateStr, long later, String formatStr) {
		Date date = getLaterDate(dateStr, later);
		return getDateStr(date, formatStr);
	}

	/**
	 * 获取某个分钟之前的时间
	 * 
	 * @param dateStr
	 *            String 时间字符串
	 * @param later
	 *            long 分钟
	 * @return
	 * @author:zhangliang
	 */
	public static Date getBeforeDate(String dateStr, long later) {
		Date date = changeStrToDate(dateStr);
		date.setTime(date.getTime() - later * 60 * 1000);
		return date;
	}

	/**
	 * 获取某个分钟之前的时间字符串
	 * 
	 * @param dateStr
	 *            String 时间字符串
	 * @param later
	 *            int 分钟
	 * @param formatStr
	 *            String 格式化字符串类型
	 * @return
	 * @author:zhangliang
	 */
	public static String getBeforeDateStr(String dateStr, long later, String formatStr) {
		Date date = getBeforeDate(dateStr, later);
		return getDateStr(date, formatStr);
	}

	public static int getDaysBetween(Date date1, Date date2) {
		Calendar c1 = Calendar.getInstance();
		c1.setTime(date1);
		Calendar c2 = Calendar.getInstance();
		c2.setTime(date2);
		if (c1.after(c2)) {
			Calendar swap = c1;
			c1 = c2;
			c2 = swap;
		}
		int days = c2.get(Calendar.DAY_OF_YEAR) - c1.get(Calendar.DAY_OF_YEAR);
		int y2 = c2.get(Calendar.YEAR);
		if (c1.get(Calendar.YEAR) != y2) {
			c1 = (Calendar) c1.clone();
			do {
				days += c1.getActualMaximum(Calendar.DAY_OF_YEAR);
				c1.add(Calendar.YEAR, 1);
			} while (c1.get(Calendar.YEAR) != y2);
		}
		return days;
	}

	public static Date currentDate() {
		return new Date();
	}

	/**
	 * 得到精确到秒的格式化当前时间串
	 * 
	 * @param formatType
	 *            时间格式的类型{@link #DB_STORE_DATE}, {@link #EN_HTML_DISPLAY_DATE},
	 *            {@link #CN_HTML_DISPLAY_DATE}
	 * @return 当前时间格式化时间串
	 */
	public static String getCurrTimeStr(int formatType) {
		return getTimeStr(new Date(), formatType);
	}

	/**
	 * 根据传入的OutFormat，DateStr进行格式化
	 * 
	 * @param DateStr
	 *            时间字符串,OutFormat输出格式字符串
	 * @return 根据格式字符串格式后的字符串类型
	 */
	public static String ToDisplayStrByFormarStr(String DateStr, String OutFormat) {
		OutFormat = OutFormat.trim();
		if (!"yyyyMMdd".equals(OutFormat) && !"yyyyMMddHHmmss".equals(OutFormat) && !"yyyy-MM-dd".equals(OutFormat)
				&& !"yyyy-MM-dd HH:mm:ss".equals(OutFormat) && !"yyyy.MM.dd".equals(OutFormat)
				&& !"yyyy.MM.dd HH:mm:ss".equals(OutFormat) && !"yyyy年MM月dd日".equals(OutFormat)
				&& !"yyyy年MM月dd日 HH:mm:ss".equals(OutFormat) && !"yyyy年MM月dd日 HH时mm分ss秒".equals(OutFormat)) {
			throw new IllegalArgumentException("时间格式化字符串不是合法的值。");
		} else {
			String formatStr = null;
			if ("yyyyMMdd".equals(OutFormat) || "yyyyMMddHHmmss".equals(OutFormat)) {
				formatStr = toDisplayStr(DateStr, DB_STORE_DATE);
			}
			if ("yyyy-MM-dd".equals(OutFormat) || "yyyy-MM-dd HH:mm:ss".equals(OutFormat)) {
				formatStr = toDisplayStr(DateStr, HYPHEN_DISPLAY_DATE);
			}
			if ("yyyy.MM.dd".equals(OutFormat) || "yyyy.MM.dd HH:mm:ss".equals(OutFormat)) {
				formatStr = toDisplayStr(DateStr, DOT_DISPLAY_DATE);
			}
			if ("yyyy年MM月dd日".equals(OutFormat) || "yyyy年MM月dd日 HH:mm:ss".equals(OutFormat)
					|| "yyyy年MM月dd日 HH时mm分ss秒".equals(OutFormat)) {
				formatStr = toDisplayStr(DateStr, CN_DISPLAY_DATE);
			}
			return formatStr;
		}
	}

	/**
	 * 得到精确到秒的格式化时间串
	 * 
	 * @param date
	 *            指定时间
	 * @param formatType
	 *            时间格式的类型{@link #DB_STORE_DATE}, {@link #EN_HTML_DISPLAY_DATE},
	 *            {@link #CN_HTML_DISPLAY_DATE}
	 * @return 指定时间的格式化时间串
	 */
	public static String getTimeStr(Date date, int formatType) {
		if (formatType < DB_STORE_DATE || formatType > CN_DISPLAY_DATE) {
			throw new IllegalArgumentException("时间格式化类型不是合法的值。");
		} else {
			String formatStr = null;
			switch (formatType) {
			case DB_STORE_DATE:
				formatStr = "yyyyMMddHHmmss";
				break;
			case HYPHEN_DISPLAY_DATE:
				formatStr = "yyyy'-'MM'-'dd HH:mm:ss";
				break;
			case DOT_DISPLAY_DATE:
				formatStr = "yyyy.MM.dd HH:mm:ss";
				break;
			case CN_DISPLAY_DATE:
				formatStr = "yyyy'年'MM'月'dd HH:mm:ss";
				break;
			}
			SimpleDateFormat sdf = new SimpleDateFormat(formatStr);
			return sdf.format(date);
		}
	}

	/**
	 * 得到精确到天的当前格式化日期串
	 * 
	 * @param formatType
	 *            时间格式的类型{@link #DB_STORE_DATE}, {@link #EN_HTML_DISPLAY_DATE},
	 *            {@link #CN_HTML_DISPLAY_DATE}
	 * @return
	 */
	public static String getCurrDateStr(int formatType) {
		return getDateStr(new Date(), formatType);
	}

	/**
	 * 得到精确到天的指定时间格式化日期串
	 * 
	 * @param date
	 *            指定时间
	 * @param formatType
	 *            时间格式的类型{@link #DB_STORE_DATE}, {@link #EN_HTML_DISPLAY_DATE},
	 *            {@link #CN_HTML_DISPLAY_DATE}
	 * @return 指定时间格式化日期串
	 */
	public static String getDateStr(Date date, int formatType) {
		if (formatType < DB_STORE_DATE || formatType > CN_DISPLAY_DATE) {
			throw new IllegalArgumentException("时间格式化类型不是合法的值。");
		} else {
			String formatStr = null;
			switch (formatType) {
			case DB_STORE_DATE:
				formatStr = "yyyyMMdd";
				break;
			case HYPHEN_DISPLAY_DATE:
				formatStr = "yyyy-MM-dd";
				break;
			case DOT_DISPLAY_DATE:
				formatStr = "yyyy.MM.dd";
				break;
			case CN_DISPLAY_DATE:
				formatStr = "yyyy'年'MM'月'dd";
				break;
			}
			SimpleDateFormat sdf = new SimpleDateFormat(formatStr);
			return sdf.format(date);
		}
	}

	/**
	 * 得到精确到月的当前时间格式化年月串
	 * 
	 * @param formatType
	 *            时间格式的类型{@link #DB_STORE_DATE}, {@link #EN_HTML_DISPLAY_DATE},
	 *            {@link #CN_HTML_DISPLAY_DATE}
	 * @return 精确到月当前时间格式化年月串
	 */
	public static String getYearMonthStr(int formatType) {
		return getYearMonthStr(new Date(), formatType);
	}

	/**
	 * 得到精确到月的指定时间格式化年月串
	 * 
	 * @param date
	 *            指定的时间
	 * @param formatType
	 *            时间格式的类型{@link #DB_STORE_DATE}, {@link #EN_HTML_DISPLAY_DATE},
	 *            {@link #CN_HTML_DISPLAY_DATE}
	 * @return 精确到月当前时间格式化年月串
	 */
	public static String getYearMonthStr(Date date, int formatType) {
		if (formatType < DB_STORE_DATE || formatType > CN_DISPLAY_DATE) {
			throw new IllegalArgumentException("时间格式化类型不是合法的值。");
		} else {
			String formatStr = null;
			switch (formatType) {
			case DB_STORE_DATE:
				formatStr = "yyyyMM";
				break;
			case HYPHEN_DISPLAY_DATE:
				formatStr = "yyyy-MM";
				break;
			case DOT_DISPLAY_DATE:
				formatStr = "yyyy.MM";
				break;
			case CN_DISPLAY_DATE:
				formatStr = "yyyy'年'MM'月'";
				break;
			}
			SimpleDateFormat sdf = new SimpleDateFormat(formatStr);
			return sdf.format(date);
		}
	}

	/**
	 * 将数据库存储的日期格式串转换为各种显示的格式
	 * 
	 * @param dateStr
	 *            最小6位，最大14位的数据库存储格式时间串如:20041212
	 * @param formatType
	 *            时间格式的类型{@link #DB_STORE_DATE}, {@link #EN_HTML_DISPLAY_DATE},
	 *            {@link #CN_HTML_DISPLAY_DATE}
	 * @return 格式化的时间串
	 */
	public static String toDisplayStr(String dateStr, int formatType) {
		if (formatType < DB_STORE_DATE || formatType > CN_DISPLAY_DATE) {
			throw new IllegalArgumentException("时间格式化类型不是合法的值。");
		}
		if (dateStr == null || dateStr.length() < 6 || dateStr.length() > 14 || formatType == DB_STORE_DATE) {
			return StringUtils.toVisualString(dateStr);
		} else {
			char[] charArr = null;
			switch (formatType) {
			case HYPHEN_DISPLAY_DATE:
				charArr = new char[] { '-', '-', ' ', ':', ':' };
				break;
			case DOT_DISPLAY_DATE:
				charArr = new char[] { '.', '.', ' ', ':', ':' };
				break;
			case CN_DISPLAY_DATE:
				charArr = new char[] { '年', '月', ' ', ':', ':' };
				break;
			default:
				charArr = new char[] { '-', '-', ' ', ':', ':' };
			}
			try {
				SimpleDateFormat sdf_1 = null;
				SimpleDateFormat sdf_2 = null;
				switch (dateStr.length()) {
				case 6:
					sdf_1 = new SimpleDateFormat("yyyyMM");
					sdf_2 = new SimpleDateFormat("yyyy'" + charArr[0] + "'MM");
					break;
				case 8:
					sdf_1 = new SimpleDateFormat("yyyyMMdd");
					sdf_2 = new SimpleDateFormat("yyyy'" + charArr[0] + "'MM'" + charArr[1] + "'dd");
					break;
				case 10:
					sdf_1 = new SimpleDateFormat("yyyyMMddHH");
					sdf_2 = new SimpleDateFormat("yyyy'" + charArr[0] + "'MM'" + charArr[1] + "'dd'" + "+charArr[2]"
							+ "'HH");
					break;
				case 12:
					sdf_1 = new SimpleDateFormat("yyyyMMddHHmm");
					sdf_2 = new SimpleDateFormat("yyyy'" + charArr[0] + "'MM'" + charArr[1] + "'dd'" + charArr[2]
							+ "'HH'" + charArr[3] + "'mm");
					break;
				case 14:
					sdf_1 = new SimpleDateFormat("yyyyMMddHHmmss");
					sdf_2 = new SimpleDateFormat("yyyy'" + charArr[0] + "'MM'" + charArr[1] + "'dd'" + charArr[2]
							+ "'HH'" + charArr[3] + "'mm'" + charArr[4] + "'ss");
					break;
				default:
					return dateStr;
				}
				return sdf_2.format(sdf_1.parse(dateStr));
			} catch (ParseException ex) {
				return dateStr;
			}
		}
	}

	/**
	 * 将数据库存储的日期格式串转换为各种显示的格式
	 * 
	 * @param dateStr
	 *            最小6位，最大14位的数据库存储格式时间串如:20041212
	 * @param formatType
	 *            时间格式的类型{@link #DB_STORE_DATE}, {@link #EN_HTML_DISPLAY_DATE},
	 *            {@link #CN_HTML_DISPLAY_DATE}
	 * @return 格式化的时间串
	 */
	public static String toDisplayStrCn(String dateStr, int dateLen) {
		String formatdate = dateStr.replaceAll("/", "").replaceAll(":", "").replaceAll("-", "").trim();
		String temp_str = "";
		char[] c = formatdate.toCharArray();
		boolean bb = false;
		for (int j = 0; j < c.length; j++) {
			if (!Character.isDigit(c[j])) {
				bb = true;
				break;
			}
		}
		if (bb) {
			return dateStr;
		}
		formatdate = formatdate.replaceAll(" ", "");
		if (formatdate == null || "".equals(formatdate.trim()))
			return "";
		switch (dateLen) {
		case 4:
			if (formatdate.length() >= dateLen)
				temp_str = formatdate.substring(0, dateLen) + "年";
			else
				temp_str = formatdate.substring(0, formatdate.length()) + "年";
			break;
		case 6:
			if (formatdate.length() >= dateLen) {
				temp_str = formatdate.substring(0, 4) + "年";
				temp_str = temp_str + Integer.valueOf(formatdate.substring(4, 6)) + "月";
			} else
				temp_str = formatdate.substring(0, formatdate.length()) + "年";
			break;
		case 8:
			if (formatdate.length() >= dateLen) {
				temp_str = formatdate.substring(0, 4) + "年";
				temp_str = temp_str + Integer.valueOf(formatdate.substring(4, 6)) + "月";
				temp_str = temp_str + Integer.valueOf(formatdate.substring(6, 8)) + "日";
			} else if (formatdate.length() >= 6) {
				temp_str = formatdate.substring(0, 4) + "年";
				temp_str = temp_str + Integer.valueOf(formatdate.substring(4, 6)) + "月";
			} else {
				temp_str = formatdate.substring(0, formatdate.length()) + "年";
			}
			break;
		case 12:
			if (formatdate.length() >= dateLen) {
				temp_str = formatdate.substring(0, 4) + "年";
				temp_str = temp_str + Integer.valueOf(formatdate.substring(4, 6)) + "月";
				temp_str = temp_str + Integer.valueOf(formatdate.substring(6, 8)) + "日";
				temp_str = temp_str + Integer.valueOf(formatdate.substring(8, 10)) + "时";
				temp_str = temp_str + Integer.valueOf(formatdate.substring(10, 12)) + "分";
			} else if (formatdate.length() >= 10) {
				temp_str = formatdate.substring(0, 4) + "年";
				temp_str = temp_str + Integer.valueOf(formatdate.substring(4, 6)) + "月";
				temp_str = temp_str + Integer.valueOf(formatdate.substring(6, 8)) + "日";
				temp_str = temp_str + Integer.valueOf(formatdate.substring(8, 10)) + "时";
			} else if (formatdate.length() >= 8) {
				temp_str = formatdate.substring(0, 4) + "年";
				temp_str = temp_str + Integer.valueOf(formatdate.substring(4, 6)) + "月";
				temp_str = temp_str + Integer.valueOf(formatdate.substring(6, 8)) + "日";
			} else if (formatdate.length() >= 6) {
				temp_str = formatdate.substring(0, 4) + "年";
				temp_str = temp_str + Integer.valueOf(formatdate.substring(4, 6)) + "月";
			} else {
				temp_str = formatdate.substring(0, formatdate.length()) + "年";
			}
			break;
		case 14:
			if (formatdate.length() >= dateLen) {
				temp_str = formatdate.substring(0, 4) + "年";
				temp_str = temp_str + Integer.valueOf(formatdate.substring(4, 6)) + "月";
				temp_str = temp_str + Integer.valueOf(formatdate.substring(6, 8)) + "日";
				temp_str = temp_str + Integer.valueOf(formatdate.substring(8, 10)) + "时";
				temp_str = temp_str + Integer.valueOf(formatdate.substring(10, 12)) + "分";
				temp_str = temp_str + Integer.valueOf(formatdate.substring(12, 14)) + "秒";
			} else if (formatdate.length() >= 12) {
				temp_str = formatdate.substring(0, 4) + "年";
				temp_str = temp_str + Integer.valueOf(formatdate.substring(4, 6)) + "月";
				temp_str = temp_str + Integer.valueOf(formatdate.substring(6, 8)) + "日";
				temp_str = temp_str + Integer.valueOf(formatdate.substring(8, 10)) + "时";
				temp_str = temp_str + Integer.valueOf(formatdate.substring(10, 12)) + "分";
			} else if (formatdate.length() >= 10) {
				temp_str = formatdate.substring(0, 4) + "年";
				temp_str = temp_str + Integer.valueOf(formatdate.substring(4, 6)) + "月";
				temp_str = temp_str + Integer.valueOf(formatdate.substring(6, 8)) + "日";
				temp_str = temp_str + Integer.valueOf(formatdate.substring(8, 10)) + "时";
			} else if (formatdate.length() >= 8) {
				temp_str = formatdate.substring(0, 4) + "年";
				temp_str = temp_str + Integer.valueOf(formatdate.substring(4, 6)) + "月";
				temp_str = temp_str + Integer.valueOf(formatdate.substring(6, 8)) + "日";
			} else if (formatdate.length() >= 6) {
				temp_str = formatdate.substring(0, 4) + "年";
				temp_str = temp_str + Integer.valueOf(formatdate.substring(4, 6)) + "月";
			} else {
				temp_str = formatdate.substring(0, formatdate.length()) + "年";
			}
			break;
		default:
			return dateStr;
		}
		return temp_str;
	}

	/**
	 * 将显示格式的时间字符串转换为数据库存储的类型
	 * 
	 * @param dateStr
	 *            最小4位，最大19位。显示的时间格式时间串如:2004-12-12
	 * @return 数据库存储的时间字符串
	 */
	public static String toStoreStr(String dateStr) {
		if (dateStr == null || dateStr.trim().equals("")) {
			return "";
		}
		StringBuffer strBuf = new StringBuffer();
		for (int i = 0; i < dateStr.length(); i++) {
			if (dateStr.charAt(i) >= '0' && dateStr.charAt(i) <= '9') {
				strBuf.append(dateStr.charAt(i));
			}
		}
		return strBuf.toString();
	}

	/**
	 * 将生日存储的时间格式转化为年龄（周岁，小数点后不计）
	 * 
	 * @param birthdayStr
	 *            生日字段 "yyyymmdd"
	 * @return 年龄
	 */
	public static String birthdayToAge(String birthdayStr) {
		if (birthdayStr == null || birthdayStr.length() < 6) {
			return "";
		} else {
			int birthYear = Integer.parseInt(birthdayStr.substring(0, 4));
			int birthMonth = Integer.parseInt(birthdayStr.substring(4, 6));
			Calendar cal = new GregorianCalendar();
			int currYear = cal.get(Calendar.YEAR);
			int currMonth = cal.get(Calendar.MONTH);
			int age = currYear - birthYear;
			age -= (currMonth < birthMonth) ? 1 : 0;
			return "" + age;
		}
	}

	/**
	 * @param dateTimeStr
	 *            String 格式化的时间串
	 * @param formatType
	 *            数据格式类型 {@link #DB_STORE_DATE}, {@link #EN_HTML_DISPLAY_DATE},
	 *            {@link #CN_HTML_DISPLAY_DATE}
	 * @param detal
	 *            int 增加或减少的时间
	 * @param field
	 *            int 参见Calendar中关于时间字段属性的定义
	 * @return String 返回的
	 */
	public static String add(String dateTimeStr, int formatType, int detal, int field) {
		if (dateTimeStr == null || dateTimeStr.length() < 6) {
			return dateTimeStr;
		} else {
			try {
				String formatStr = null;
				switch (formatType) {
				case DB_STORE_DATE:
					formatStr = "yyyyMMddHHmmss";
					break;
				case HYPHEN_DISPLAY_DATE:
					formatStr = "yyyy-MM-dd HH:mm:ss";
					break;
				case DOT_DISPLAY_DATE:
					formatStr = "yyyy.MM.dd HH:mm:ss";
					break;
				case CN_DISPLAY_DATE:
					formatStr = "yyyy'年'MM'月' HH：mm：ss";
					break;
				default:
					formatStr = "yyyyMMddHHmmss";
					break;
				}

				formatStr = formatStr.substring(0, dateTimeStr.length());
				SimpleDateFormat sdf = new SimpleDateFormat(formatStr);
				Date d = sdf.parse(dateTimeStr);
				GregorianCalendar g = new GregorianCalendar();
				g.setTime(d);
				g.add(field, detal);
				d = g.getTime();
				return sdf.format(d);
			} catch (ParseException ex) {
				ex.printStackTrace();
				return dateTimeStr;
			}
		}
	}

	// /**
	// * @param date Date 时间
	// * @param detal int 增加的时间
	// * @param field int 参见Calendar中关于时间字段属性的定义
	// * @return Date
	// */
	// public static Date add(Date date, int detal, int field)
	// {
	// Calendar g = new GregorianCalendar();
	// g.setTime(date);
	// g.add(field, detal);
	// return g.getTime();
	// }
	/**
	 * 日期、时间格式化
	 * 
	 * @param date
	 *            Date 将要被格式化的日期对象
	 * @param outFmt
	 *            String 返回样式，参照类说明，如：yyyy年MM月dd日
	 * @return String 格式化后的日期、时间字符串，data为null时返回null，outFmt非法时返回yyyyMMdd格式
	 */
	public static String getDateFormat(Date date, String outFmt) {
		if (null == date) {
			return null;
		}
		if (null == outFmt || "".equals(outFmt.trim())) { // outFmt非法
			outFmt = "yyyyMMdd";
		}

		String retu = null;
		SimpleDateFormat dateFormat = null;
		try {
			dateFormat = new SimpleDateFormat(outFmt);
		} catch (IllegalArgumentException iaex) { // outFmt非法
			dateFormat = new SimpleDateFormat("yyyyMMdd");
		}
		retu = dateFormat.format(date);

		dateFormat = null;

		return retu;
	}

	/**
	 * 把日期时间对象格式化为yyyyMMdd样式
	 * 
	 * @param date
	 *            Date 将要被格式化的日期对象
	 * @return String 格式化后的日期、时间字符串，如：20041001
	 */
	public static String getDateFormat(Date date) {
		return getDateFormat(date, "yyyyMMdd");
	}

	/**
	 * 把系统当前日期时间格式化为指定的样式
	 * 
	 * @param outFmt
	 *            String 返回样式，参照类说明，如：yyyy年MM月dd日
	 * @return String 格式化后的日期、时间字符串，如：2004年10月01日
	 */
	public static String getDateFormat(String outFmt) {
		return getDateFormat(new Date(), outFmt);
	}

	/**
	 * 把系统当前日期时间格式化为默认样式yyyyMMdd
	 * 
	 * @return String 格式化后的日期、时间字符串，如：20041001
	 */
	public static String getDateFormat() {
		return getDateFormat(new Date(), "yyyyMMdd");
	}

	/**
	 * 日期、时间格式化
	 * 
	 * @param millis
	 *            long the number of milliseconds（毫秒） since January 1, 1970,
	 *            00:00:00 GMT.
	 * @param outFmt
	 *            String 返回样式，参照类说明，如：yyyy年MM月dd日
	 * @return String 格式化后的日期、时间字符串
	 */
	public static String getDateFormat(long millis, String outFmt) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(millis);

		String retu = getDateFormat(calendar.getTime(), outFmt);
		calendar = null;
		return retu;
	}

	/**
	 * 日期、时间格式化
	 * 
	 * @param datestr
	 *            String 存在一定格式的日期、时间字符串，如：20041001、200410011503
	 * @param inFmt
	 *            String 对datestr参数格式说明，参照类说明，如：yyyyMMdd、yyyyMMddHHmm
	 * @param outFmt
	 *            String 返回样式，参照类说明，如：yyyy年MM月dd日
	 * @throws ParseException
	 *             当datestr不能格式化为inFmt格式时抛出此异常
	 * @return String 格式化后的日期、时间字符串，如：2004年10月01日、2004年10月01日 <BR>
	 *         输出样式outFmt非法时，使用yyyyMMdd格式输出
	 */
	public static String getDateFormat(String datestr, String inFmt, String outFmt) throws ParseException {
		if (null == datestr || "".equals(datestr.trim())) {
			return datestr;
		}

		if (null == inFmt || "".equals(inFmt.trim())) {
			return datestr;
		}

		if (null == outFmt || "".equals(outFmt.trim())) { // 输出样式非法
			outFmt = "yyyyMMdd";
		}

		java.util.Date inDate = getDate(datestr, inFmt);

		if (null == inDate) { // 根据inFmt分析datestr时抛出异常
			return datestr;
		}

		String retu = getDateFormat(inDate, outFmt);
		inDate = null;
		return retu;
	}

	/**
	 * 把日期时间字符串，按inFmt样式转化为日期对象，然后格式化为默认样式yyyyMMdd
	 * 
	 * @param datestr
	 *            String 存在一定格式的日期、时间字符串，如：20041001、200410011503
	 * @param inFmt
	 *            String 对datestr参数格式说明，参照类说明，如：yyyyMMdd、yyyyMMddHHmm
	 * @throws ParseException
	 *             当datestr不能格式化为inFmt格式时抛出此异常
	 * @return String 格式化后的日期、时间字符串，如：20041001、20041001
	 */
	public static String getDateFormat(String datestr, String inFmt) throws ParseException {
		return getDateFormat(datestr, inFmt, "yyyyMMdd");
	}

	/**
	 * 根据inFmt的样式，日期时间字符串转化为日期时间对象
	 * 
	 * @param datestr
	 *            String 日期时间字符串，如：20041001、2004年10月01日 15:03
	 * @param inFmt
	 *            String 对datestr参数格式说明，参照类说明，如yyyyMMdd、yyyy年MM月dd日 HH:mm
	 * @throws ParseException
	 *             当datestr不能格式化为inFmt格式时抛出此异常
	 * @return Date 日期时间对象，格式inFmt非法时，使用yyyyMMdd格式
	 */
	public static Date getDate(String datestr, String inFmt) throws ParseException {
		if (null == datestr || "".equals(datestr.trim())) {
			return null;
		}

		if (null == inFmt || "".equals(inFmt.trim())) { // inFmt非法
			inFmt = "yyyyMMdd";
		}

		java.util.Date inDate = null;

		// 依据inFmt格式把日期字符串转化为日期对象
		SimpleDateFormat inDateFormat = new SimpleDateFormat(inFmt);
		inDateFormat.setLenient(false);
		inDate = inDateFormat.parse(datestr);

		inDateFormat = null;
		return inDate;
	}

	/**
	 * 对日期时间对象进行调整，实现如昨天是几号，去年的今天星期几等. <BR>
	 * 例子：
	 * 
	 * <pre>
	 * &lt;blockquote&gt;
	 * 计算去年今天星期几
	 * Date date = DateUtils.addDate(new Date(),Calendar.YEAR,-1);
	 * System.out.println(DateUtils.getDateFormat(date,&quot;E&quot;));
	 * 打印60天后是什么日期，并显示为 yyyy-MM-dd 星期
	 * Date date = DateUtils.addDate(new Date(),Calendar.DATE,60);
	 * System.out.println(DateUtils.getDateFormat(date,&quot;yyyy-MM-dd E&quot;));
	 * &lt;/blockquote&gt;
	 * </pre>
	 * 
	 * @param date
	 *            Date 需要调整的日期时间对象
	 * @param CALENDARFIELD
	 *            int 对日期时间对象以什么单位进行调整：
	 * 
	 *            <pre>
	 * &lt;blockquote&gt;
	 * 年 Calendar.YEAR
	 * 月 Calendar.MONTH
	 * 日 Calendar.DATE
	 * 时 Calendar.HOUR
	 * 分 Calendar.MINUTE
	 * 秒 Calendar.SECOND
	 * &lt;/blockquote&gt;
	 * </pre>
	 * 
	 * @param amount
	 *            int 调整数量，>0表向后调整（明天，明年），<0表向前调整（昨天，去年）
	 * @return Date 调整后的日期时间对象
	 */
	public static Date addDate(Date date, int CALENDARFIELD, int amount) {
		if (null == date) {
			return date;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(CALENDARFIELD, amount);
		return calendar.getTime();
	}

	/**
	 * 对日期时间对象进行调整.
	 * 
	 * @param datestr
	 *            String 需要调整的日期时间字符串，它的格式为yyyyMMdd
	 * @param CALENDARFIELD
	 *            int 对日期时间对象以什么单位进行调整
	 * @param amount
	 *            int 调整数量
	 * @throws ParseException
	 *             当datestr不能格式化为yyyyMMdd格式时抛出此异常
	 * @return Date 调整后的日期时间对象
	 * @see #addDate(java.util.Date, int, int)
	 */
	public static Date addDate(String datestr, int CALENDARFIELD, int amount) throws ParseException {
		return addDate(getDate(datestr, "yyyyMMdd"), CALENDARFIELD, amount);
	}

	/**
	 * 根据出生日期，计算出在某一个日期的年龄
	 * 
	 * @param birthday
	 *            Date 出生日期时间对象
	 * @param date2
	 *            Date 计算日期对象
	 * @return int 返回date2那一天出生日期为birthday的年龄，如果birthday大于date2则返回-1
	 */
	public static int getAge(Date birthday, Date date2) {
		if (null == birthday || null == date2) {
			return -1;
		}

		if (birthday.after(date2)) { // birthday大于date2
			return -1;
		}

		int ibdYear = StringUtils.getInt(getDateFormat(birthday, "yyyy"), -1);
		int idate2Year = StringUtils.getInt(getDateFormat(date2, "yyyy"), -1);

		if (ibdYear < 0 || idate2Year < 0) {
			return -1;
		}
		if (ibdYear > idate2Year) {
			return -1;
		}

		return idate2Year - ibdYear + 1;
	}

	/**
	 * 根据出生日期，计算出当前的年龄
	 * 
	 * @param birthday
	 *            Date 出生日期时间对象
	 * @return int 返回出生日期为birthday的年龄，如果birthday大于当前系统日期则返回-1
	 */
	public static int getAge(Date birthday) {
		return getAge(birthday, new Date());
	}

	/**
	 * 根据出生日期，计算出当前的年龄
	 * 
	 * @param birthdaystr
	 *            String 出生日期时间字符串，其格式一定为yyyyMMdd
	 * @throws ParseException
	 *             当datestr不能格式化为yyyyMMdd格式时抛出此异常
	 * @return int 返回出生日期为birthday的年龄，如果birthday大于当前系统日期则返回-1
	 */
	public static int getAge(String birthdaystr) throws ParseException {
		return getAge(getDate(birthdaystr, "yyyyMMdd"));
	}

	/**
	 * 得到14位的当前格式化时间
	 * 
	 * @param formatType
	 *            时间格式的类型{@link #DB_STORE_DATE}, {@link #EN_HTML_DISPLAY_DATE},
	 *            {@link #CN_HTML_DISPLAY_DATE}
	 * @return java.lang.String
	 */
	public static String getCurrTime(int formatType) {
		if (formatType < DB_STORE_DATE || formatType > CN_DISPLAY_DATE) {
			throw new IllegalArgumentException("时间格式化类型不是合法的值。");
		} else {
			String formatStr = null;
			switch (formatType) {
			case DB_STORE_DATE:
				formatStr = "yyyyMMddHHmmss";
				break;
			case HYPHEN_DISPLAY_DATE:
				formatStr = "yyyy'-'MM'-'dd HH:mm:ss";
				break;
			case DOT_DISPLAY_DATE:
				formatStr = "yyyy.MM.dd HH:mm:ss";
				break;
			case CN_DISPLAY_DATE:
				formatStr = "yyyy'年'MM'月'dd HH:mm:ss";
				break;
			}
			SimpleDateFormat sdf = new SimpleDateFormat(formatStr);
			return sdf.format(new Date());
		}
	}

	/**
	 * 得到8位的当前格式化日期
	 * 
	 * @param formatType
	 *            时间格式的类型{@link #DB_STORE_DATE}, {@link #EN_HTML_DISPLAY_DATE},
	 *            {@link #CN_HTML_DISPLAY_DATE}
	 * @return java.lang.String
	 */
	public static String getCurrDate(int formatType) {
		if (formatType < DB_STORE_DATE || formatType > CN_DISPLAY_DATE) {
			throw new IllegalArgumentException("时间格式化类型不是合法的值。");
		} else {
			String formatStr = null;
			switch (formatType) {
			case DB_STORE_DATE:
				formatStr = "yyyyMMdd";
				break;
			case HYPHEN_DISPLAY_DATE:
				formatStr = "yyyy-MM-dd";
				break;
			case DOT_DISPLAY_DATE:
				formatStr = "yyyy.MM.dd";
				break;
			case CN_DISPLAY_DATE:
				formatStr = "yyyy'年'MM'月'dd";
				break;
			}
			SimpleDateFormat sdf = new SimpleDateFormat(formatStr);
			return sdf.format(new Date());
		}
	}

	/**
	 * 得到6位的当前格式化年月
	 * 
	 * @param formatType
	 *            时间格式的类型{@link #DB_STORE_DATE}, {@link #EN_HTML_DISPLAY_DATE},
	 *            {@link #CN_HTML_DISPLAY_DATE}
	 * @return java.lang.String
	 */
	public static String getYearMonth(int formatType) {
		if (formatType < DB_STORE_DATE || formatType > CN_DISPLAY_DATE) {
			throw new IllegalArgumentException("时间格式化类型不是合法的值。");
		} else {
			String formatStr = null;
			switch (formatType) {
			case DB_STORE_DATE:
				formatStr = "yyyyMM";
				break;
			case HYPHEN_DISPLAY_DATE:
				formatStr = "yyyy-MM";
				break;
			case DOT_DISPLAY_DATE:
				formatStr = "yyyy.MM";
				break;
			case CN_DISPLAY_DATE:
				formatStr = "yyyy'年'MM'月'";
				break;
			}
			SimpleDateFormat sdf = new SimpleDateFormat(formatStr);
			return sdf.format(new Date());
		}
	}

	/**
	 * 获取时间所对应的毫秒
	 * 
	 * @param datetime
	 *            String 时间字符串
	 * @param inFmt
	 *            String 时间格式化串
	 * @return long 毫秒数
	 * @throws ParseException
	 */
	public static long getTimeValue(String datetime, String inFmt) throws ParseException {
		return getDate(datetime, inFmt).getTime();
	}

	/**
	 * 获取时间字符串
	 * 
	 * @param milliSeconds
	 *            long 从1970年到现在的毫
	 * @param formatType
	 *            int 时间类型
	 * @return String 时间字符串
	 */
	public static String getTimeStr(long milliSeconds, int formatType) {
		Date d = new Date(milliSeconds);
		return getTimeStr(d, formatType);
	}

	/**
	 * 对日期时间对象进行调整
	 * 
	 * @param date
	 * @param CALENDARFIELD
	 * 
	 *            <pre>
	 * 年 Calendar.YEAR
	 * 月 Calendar.MONTH
	 * 日 Calendar.DATE
	 * 时 Calendar.HOUR
	 * 分 Calendar.MINUTE
	 * 秒 Calendar.SECOND
	 * </pre>
	 * 
	 * @param amount
	 *            调整数量，>0表向后调整（明天，明年），<0表向前调整（昨天，去年）
	 * @return
	 * @author:chenxy
	 */
	public static Date transpositionDate(String dateStr, int CALENDARFIELD, int amount) {
		if (null == dateStr) {
			return null;
		}
		return transpositionDate(changeStrToDate(dateStr), CALENDARFIELD, amount);
	}

	/**
	 * 对日期时间对象进行调整
	 * 
	 * @param date
	 * @param CALENDARFIELD
	 * 
	 *            <pre>
	 * 年 Calendar.YEAR
	 * 月 Calendar.MONTH
	 * 日 Calendar.DATE
	 * 时 Calendar.HOUR
	 * 分 Calendar.MINUTE
	 * 秒 Calendar.SECOND
	 * </pre>
	 * 
	 * @param amount
	 *            调整数量，>0表向后调整（明天，明年），<0表向前调整（昨天，去年）
	 * @return
	 * @author:chenxy
	 */
	public static Date transpositionDate(Date date, int CALENDARFIELD, int amount) {
		if (null == date) {
			return date;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(CALENDARFIELD, amount);
		return calendar.getTime();
	}

	/**
	 * 对日期时间对象进行调整
	 * 
	 * @param date
	 * @param CALENDARFIELD
	 * 
	 *            <pre>
	 * 年 Calendar.YEAR
	 * 月 Calendar.MONTH
	 * 日 Calendar.DATE
	 * 时 Calendar.HOUR
	 * 分 Calendar.MINUTE
	 * 秒 Calendar.SECOND
	 * </pre>
	 * 
	 * @param amount
	 *            调整数量，>0表向后调整（明天，明年），<0表向前调整（昨天，去年）
	 * @return
	 * @author:chenxy
	 */
	public static String transpositionDateStr(String dateStr, int CALENDARFIELD, int amount) {
		if (null == dateStr) {
			return dateStr;
		}
		return changeDateToStr(transpositionDate(dateStr, CALENDARFIELD, amount), DB_STORE_DATE_FULL);
	}

	/**
	 * 得到格式化时间串
	 * 
	 * @param date
	 *            指定时间
	 * @param formatType
	 *            时间格式的类型
	 * @return 指定时间的格式化时间串
	 */
	public static String changeDateToStr(Date date, String formatStr) {
		SimpleDateFormat fomate = new SimpleDateFormat(formatStr);
		return fomate.format(date);
	}

	/**
	 * 根据时间跨距，取得对应的行政月。例如：9-26到10-26 返回的月份为10月
	 * 
	 * @day 从那天开始(例如：26)
	 * @format 时间格式的类型：YYMMDD、YYMMDDHHmMSS
	 * @return 指定的格式化时间
	 */
	public static String getStrationTime(int day, String formatStr) {
		Calendar cal = Calendar.getInstance();
		Date date = cal.getTime();
		Calendar g = new GregorianCalendar();
		g.setTime(date);
		if (cal.get(Calendar.DATE) > day) {
			g.add(Calendar.MONTH, 1);
		}
		Date d = g.getTime();
		return getDateStr(d, formatStr);
	}

	/**
	 * 比较两个时间的大小。time1在time2之后， 返回true否则返回false
	 * 
	 * @param time1
	 * @param time2
	 * @return
	 */
	public static boolean compareTime(Date time1, Date time2) {
		boolean flag = time1.after(time2);
		return flag;
	}

	/**
	 * 获得当天零点的时间点
	 * 
	 * @return
	 */
	public static Date getCurrentDateZero() {
		Calendar c1 = Calendar.getInstance(TimeZone.getTimeZone("GMT+08:00"));
		int year = c1.get(Calendar.YEAR);
		int month = c1.get(Calendar.MONTH);
		int date = c1.get(Calendar.DAY_OF_MONTH);
		Calendar c2 = Calendar.getInstance(TimeZone.getTimeZone("GMT+08:00"));
		c2.set(year, month, date, 0, 0, 0);
		return c2.getTime();
	}

	/**
	 * 格式化时间
	 * 
	 * ~1分钟以内：刚刚 ~1小时以内：？分钟前 ~1天以内：？小时前 ~2天以内：昨天 ~3天以内：前天 ~4天及以上：年-月-日 时:分
	 * 
	 * @param time
	 * @return
	 */
	public static String formatDateTime(Date date) {
		String formatTime = null;
		if (date == null) {
			return "";
		}
		SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm");
		String timeStr = format.format(date);

		Calendar current = Calendar.getInstance();

		Calendar today = Calendar.getInstance(); // 今天
		today.set(Calendar.YEAR, current.get(Calendar.YEAR));
		today.set(Calendar.MONTH, current.get(Calendar.MONTH));
		today.set(Calendar.DAY_OF_MONTH, current.get(Calendar.DAY_OF_MONTH));
		// Calendar.HOUR——12小时制的小时数 Calendar.HOUR_OF_DAY——24小时制的小时数
		today.set(Calendar.HOUR_OF_DAY, 0);
		today.set(Calendar.MINUTE, 0);
		today.set(Calendar.SECOND, 0);

		Calendar yesterday = Calendar.getInstance(); // 昨天
		yesterday.set(Calendar.YEAR, current.get(Calendar.YEAR));
		yesterday.set(Calendar.MONTH, current.get(Calendar.MONTH));
		yesterday.set(Calendar.DAY_OF_MONTH, current.get(Calendar.DAY_OF_MONTH) - 1);
		yesterday.set(Calendar.HOUR_OF_DAY, 0);
		yesterday.set(Calendar.MINUTE, 0);
		yesterday.set(Calendar.SECOND, 0);

		Calendar yesterdayBefore = Calendar.getInstance(); // 前天
		yesterdayBefore.set(Calendar.YEAR, current.get(Calendar.YEAR));
		yesterdayBefore.set(Calendar.MONTH, current.get(Calendar.MONTH));
		yesterdayBefore.set(Calendar.DAY_OF_MONTH, current.get(Calendar.DAY_OF_MONTH) - 2);
		yesterdayBefore.set(Calendar.HOUR_OF_DAY, 0);
		yesterdayBefore.set(Calendar.MINUTE, 0);
		yesterdayBefore.set(Calendar.SECOND, 0);

		Calendar compareDate = Calendar.getInstance();
		compareDate.setTime(date);

		if (compareDate.after(today)) {

			// 一分钟内
			today.set(Calendar.HOUR_OF_DAY, current.get(Calendar.HOUR_OF_DAY));
			today.set(Calendar.MINUTE, current.get(Calendar.MINUTE) - 1);
			today.set(Calendar.SECOND, current.get(Calendar.SECOND));
			if (compareDate.after(today)) {
				formatTime = "刚刚";
			} else {
				// 一个小时内
				today.set(Calendar.HOUR_OF_DAY, current.get(Calendar.HOUR_OF_DAY) - 1);
				today.set(Calendar.MINUTE, current.get(Calendar.MINUTE));
				today.set(Calendar.SECOND, current.get(Calendar.SECOND));

				if (compareDate.after(today)) {
					String minuteStr = timeStr.split(":")[1];
					int len = current.get(Calendar.MINUTE) - Integer.parseInt(minuteStr);
					if (len < 0) {
						len = current.get(Calendar.MINUTE) + 60 - Integer.parseInt(minuteStr);
					}
					formatTime = len + "分钟前";
				} else {

					String[] tempStr = timeStr.split(" ");
					String hourStr = tempStr[1].split(":")[0];
					int len = current.get(Calendar.HOUR_OF_DAY) - Integer.parseInt(hourStr);
					formatTime = len + "小时前";
				}
			}

		} else if (compareDate.before(today) && compareDate.after(yesterday)) {
			formatTime = "昨天 " + timeStr.split(" ")[1];
		} else if (compareDate.before(yesterday) && compareDate.after(yesterdayBefore)) {
			formatTime = "前天 " + timeStr.split(" ")[1];
		} else {
			int index = timeStr.indexOf("-") + 1;
			formatTime = timeStr.substring(index, timeStr.length());
		}

		return formatTime;
	}

	/**
	 * 获取本周的周数据
	 * 
	 * @return
	 */
	public static Map<String, Integer> getWeekDataOfThisWeek() {
		Map<String, Integer> map = new HashMap<String, Integer>();
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		int week = cal.get(Calendar.WEEK_OF_YEAR);
		// 如果7天前的周数大于当日周数，表示当日在年尾，并且当日周数被计算在下一年，此时要在得到的年份是+1
		cal.add(Calendar.DAY_OF_MONTH, -7);
		if (week < cal.get(Calendar.WEEK_OF_YEAR)) {
			year += 1;
		}
		map.put("year", year);
		map.put("week", week);
		return map;
	}

	/**
	 * 获取上周的周数据
	 * 
	 * @return
	 */
	public static Map<String, Integer> getWeekDataOfLastWeek() {
		Map<String, Integer> map = new HashMap<String, Integer>();
		Calendar cal = Calendar.getInstance();
		// SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		// try {
		// cal.setTime(sdf.parse("2014-01-03"));
		// } catch (ParseException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// 获取到上周的周数
		cal.add(Calendar.DAY_OF_MONTH, -7);
		int year = cal.get(Calendar.YEAR);
		int week = cal.get(Calendar.WEEK_OF_YEAR);
		// 如果上上周的周数大于上周周数，表示上周在年尾，并且上周周数被计算在下一年，此时要在得到的年份是+1
		cal.add(Calendar.DAY_OF_MONTH, -7);
		if (week < cal.get(Calendar.WEEK_OF_YEAR)) {
			year += 1;
		}
		map.put("year", year);
		map.put("week", week);
		return map;
	}
	
	
	/**
	 * 比较long型时间是否在当天前一天
	 * @param lastAttendTime
	 * @return
	 */
	public  static boolean compareDay(String lastAttendTime){
		
		 Calendar c1 = Calendar.getInstance(TimeZone.getTimeZone("GMT+08:00"));  
		 int year = c1.get(Calendar.YEAR);
		 int month = c1.get(Calendar.MONTH);
		 int date = c1.get(Calendar.DAY_OF_MONTH);
		 
		 //起始时间
		 Calendar c2 = Calendar.getInstance(TimeZone.getTimeZone("GMT+08:00"));
		 c2.set(year, month, date, 0, 0, 0);
		 //上次参与的时间
		 Date lastAttTime = new Date(Long.valueOf(lastAttendTime));
		 //判断当前时间
		 if(lastAttTime.before(c2.getTime())){
			return true;
		 }
		 
		return false;
	}
	
	/**
	 * 判断当前是否一天的第一次
	 * @param lastUpDateTime 上次更新的时间
	 * @return
	 */
	public static boolean ifDayZeroClock(Long lastUpDateTime){
		 Calendar c1 = Calendar.getInstance(TimeZone.getTimeZone("GMT+08:00"));  
		 int year = c1.get(Calendar.YEAR);
		 int month = c1.get(Calendar.MONTH);
		 int date = c1.get(Calendar.DAY_OF_MONTH);
		 Calendar c2 = Calendar.getInstance(TimeZone.getTimeZone("GMT+08:00"));
		 c2.set(year, month, date, 0, 0, 0);
		 if(System.currentTimeMillis() >= c2.getTimeInMillis() && (lastUpDateTime < c2.getTimeInMillis())){
			 return true;
		 }else {
			 return false;
		 }
	}

	public static void main(String[] args) throws Exception {
		// System.out.println(DateUtils.changeStrToDate("2014-04-17"));
		// System.out.println(DateUtils.formatDateTime(new Date()));
		//
		// System.out.println(DateUtils.getCurrentDate());

//		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
//		Date d = df.parse("2014-06-17 14:57");
//		// System.out.println(DateUtils.formatDateTime(d));
//		// Date before = new Date();
//
//		Date now = new Date();
//		String between = zxWxBetweenDescribe(d, now);
//		System.out.println(between);

		Date date = new Date();
		System.out.println(date);
		System.out.println(DateUtils.getLaterDatess(date, 120));
		// System.out.println(formatDateTime(d));
	}
	
	/**
	 * 将日期往前或者往后加几天或者几个月或者几年，或者几个小时或者几分钟或者几秒
	 * 
	 * @param strn 例如将日期往后加3天：Date date =
	 *            DateUtil.dateAdd(Calendar.DAY_OF_MONTH,-3,new Date())
	 *            例如将日期往前加3天：Date date =
	 *            DateUtil.dateAdd(Calendar.DAY_OF_MONTH,3,new Date())
	 * @return
	 */
	public static Date dateAdd(int field, int amount, Date date) {
		Calendar cal = new GregorianCalendar();
		cal.setTimeInMillis(date.getTime());
		cal.add(field, amount);
		date = new Date(cal.getTimeInMillis());
		return date;
	}

}
