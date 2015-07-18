package com.yoxi.hudongtui.utils.common;

import java.text.DecimalFormat;
import java.util.List;
import java.util.ArrayList;

/**
 * <pre>
 * 字符串处理工具类.
 * <blockquote>
 * <b>数字格式化样式</b>
 * <table border=0 cellspacing=3 cellpadding=0 width="90%">
 *  <tr bgcolor="#ccccff">
 *      <th align=left>Symbol</th>
 *      <th align=left>Location</th>
 *      <th align=left>Localized</th>
 *      <th align=left>Meaning</th>
 *  </tr>
 *  <tr valign=top>
 *      <td><code>0</code></td>
 *      <td>Number</td>
 *      <td>Yes</td>
 *      <td>Digit</td>
 *  </tr>
 *  <tr valign=top bgcolor="#eeeeff">
 *      <td><code>#</code></td>
 *      <td>Number</td>
 *      <td>Yes</td>
 *      <td>Digit, zero shows as absent</td>
 *  </tr>
 *  <tr valign=top>
 *      <td><code>.</code></td>
 *      <td>Number</td>
 *      <td>Yes</td>
 *      <td>Decimal separator or monetary decimal separator</td>
 *  </tr>
 *  <tr valign=top bgcolor="#eeeeff">
 *      <td><code>-</code></td>
 *      <td>Number</td>
 *      <td>Yes</td>
 *      <td>Minus sign</td>
 *  </tr>
 *  <tr valign=top>
 *      <td><code>,</code></td>
 *      <td>Number</td>
 *      <td>Yes</td>
 *      <td>Grouping separator</td>
 *  </tr>
 *  <tr valign=top bgcolor="#eeeeff">
 *      <td><code>E</code></td>
 *      <td>Number</td>
 *      <td>Yes</td>
 *      <td>Separates mantissa and exponent in scientific notation.
 *          <em>Need not be quoted in prefix or suffix.</em></td>
 *  </tr>
 *  <tr valign=top>
 *      <td><code>;</code></td>
 *      <td>Subpattern boundary</td>
 *      <td>Yes</td>
 *      <td>Separates positive and negative subpatterns</td>
 *  </tr>
 *  <tr valign=top bgcolor="#eeeeff">
 *      <td><code>%</code></td>
 *      <td>Prefix or suffix</td>
 *      <td>Yes</td>
 *      <td>Multiply by 100 and show as percentage</td>
 *  </tr>
 *  <tr valign=top>
 *      <td><code>&#92;u2030</code></td>
 *      <td>Prefix or suffix</td>
 *      <td>Yes</td>
 *      <td>Multiply by 1000 and show as per mille</td>
 *  </tr>
 *  <tr valign=top bgcolor="#eeeeff">
 *      <td><code>&#164;</code> (<code>&#92;u00A4</code>)</td>
 *      <td>Prefix or suffix</td>
 *      <td>No</td>
 *      <td>Currency sign, replaced by currency symbol.  If
 *          doubled, replaced by international currency symbol.
 *          If present in a pattern, the monetary decimal separator
 *          is used instead of the decimal separator.</td>
 *  </tr>
 *  <tr valign=top>
 *      <td><code>'</code></td>
 *      <td>Prefix or suffix</td>
 *      <td>No</td>
 *      <td>Used to quote special characters in a prefix or suffix,
 *          for example, <code>"'#'#"</code> formats 123 to
 *          <code>"#123"</code>.  To create a single quote
 *          itself, use two in a row: <code>"# o''clock"</code>.</td>
 *   </tr>
 * </table>
 * 
 * <b>符号(Symbol)通常是组合在一起使用的，例如下表</b>
 * <table border=0 cellspacing=3 cellpadding=0 width="90%">
 *     <tr bgcolor="#ccccff">
 *         <th align=left>输入</th>
 *         <th align=left>格式</th>
 *         <th align=left>输出</th>
 *     </tr>
 *     <tr valign=top>
 *         <td rowspan=8>1234.123456</td>
 *         <td><code>0</code></td>
 *         <td>1234</td>
 *     </tr>
 *     <tr>
 *         <td><code>0.00</code></td>
 *         <td>1234.12</td>
 *     </tr>
 *     <tr>
 *         <td><code>0.###</code></td>
 *         <td>1234.123</td>
 *     </tr>
 *     <tr>
 *         <td><code>0.0000</code></td>
 *         <td>1234.1235</td>
 *     </tr>
 *     <tr>
 *         <td><code>00000000.00</code></td>
 *         <td>00001234.12</td>
 *     </tr>
 *     <tr>
 *         <td><code>,000.00</code></td>
 *         <td>1,234.12</td>
 *     </tr>
 *     <tr>
 *         <td><code>,00.00000000</code></td>
 *         <td>12,34.12345600</td>
 *     </tr>
 *     <tr>
 *         <td><code>0.00%</code></td>
 *         <td>123412.35%</td>
 *     </tr>
 *     <tr valign=top bgcolor="#eeeeff">
 *         <td rowspan=5>1234.100056</td>
 *         <td><code>0</code></td>
 *         <td>1234</td>
 *     </tr>
 *     <tr valign=top bgcolor="#eeeeff">
 *         <td><code>0.00</code></td>
 *         <td>1234.10</td>
 *     </tr>
 *     <tr valign=top bgcolor="#eeeeff">
 *         <td><code>0.##</code></td>
 *         <td>1234.1</td>
 *     </tr>
 *     <tr valign=top bgcolor="#eeeeff">
 *         <td><code>(人民币),000.00</code></td>
 *         <td>(人民币)1,234.10</td>
 *     </tr>
 *     <tr valign=top bgcolor="#eeeeff">
 *         <td><code>,000.########</code></td>
 *         <td>1,234.100056</td>
 *     </tr>
 * </table>
 * <BR>
 * 注：0与#功能类似，区别在于用0表示位数时，格式化后的首尾的0会显示，而用#表示位数时，首尾的0不显示
 * </blockquote>
 * </pre>
 * 
 * <BR>
 * <DL>
 * <DT><B>JDK版本:</B></DT><BR>
 * <DD>1.4</DD>
 * </DL>
 * 
 * @author 涂传滨，伊新煦，陈雄华，谢计生
 * @version 1.0
 * @since 1.0
 * @see java.text.DecimalFormat
 */
public class StringUtils extends org.apache.commons.lang.StringUtils {

	/** 字符长度 */
	public static final int CHAR_LEN = 1;
	/** 字节长度 */
	public static final int BYTE_LEN = 2;

	private StringUtils() {
	}

	/**
	 * 两个字符串都不为空的判断
	 * 
	 * @param src
	 * @param dest
	 * @return
	 */
	public static boolean areNotBlank(String src, String dest) {
		if (isNotBlank(src) && isNotBlank(dest)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 数字字符串转化为整数
	 * 
	 * @param intStr
	 *            String 待转化的数字字符串
	 * @param intDef
	 *            int 当intStr为空或空字符串时返回的缺省值
	 * @return int 返回由数字字符串转化成的数字，当intStr为空或空字符串时，返回缺省值intDef
	 */
	public static int getInt(String intStr, int intDef) {
		if (null == intStr || "".equals(intStr.trim())) {
			return intDef;
		}

		int intRetu = intDef;

		Double db = new Double(intStr);
		intRetu = db.intValue();
		return intRetu;
	}

	/**
	 * 数字字符串转化为整数，在转化时发生异常，则返回0
	 * 
	 * @param intStr
	 *            String 待转化的数字字符串
	 * @return int 返回由数字字符串转化成的整数，当intStr为空或空字符串时，返回0
	 */
	public static int getInt(String intStr) {
		return getInt(intStr, 0);
	}

	/**
	 * 数字字符串转化为双精数字
	 * 
	 * @param dbstr
	 *            String 待转化的数字字符串
	 * @param dbDef
	 *            double 当dbstr为空或空字符串时返回的缺省值
	 * @return double 返回由数字字符串转化成的数字，当dbstr为空或空字符串时，则返回缺省值dbDef
	 */
	public static double getDouble(String dbstr, double dbDef) {

		if (null == dbstr || "".equals(dbstr.trim())) {
			return dbDef;
		}
		double dbRetu = dbDef;
		Double db = new Double(dbstr);
		dbRetu = db.doubleValue();
		return dbRetu;
	}

	/**
	 * 数字字符串转化为双精数字，在转化时发生异常，则返回0
	 * 
	 * @param dbstr
	 *            String 待转化的数字字符串
	 * @return double 返回由数字字符串转化成的数字，当dbstr为空或空字符串时，则返回0。
	 */
	public static double getDouble(String dbstr) {
		return getDouble(dbstr, 0);
	}

	/**
	 * 数字字符串转化为长整型
	 * 
	 * @param longstr
	 *            String 待转化的数字字符串
	 * @param longDef
	 *            long 当longstr为空或空字符串时返回的缺省值
	 * @return long 返回由数字字符串转化成的数字，当longstr为空或空字符串时，则返回缺省值longDef
	 */
	public static long getLong(String longstr, long longDef) {
		if (null == longstr || "".equals(longstr.trim())) {
			return longDef;
		}
		long longRetu = longDef;

		Double db = new Double(longstr);
		longRetu = db.longValue();

		return longRetu;
	}

	/**
	 * 数字字符串转化为长整型，在转化时发生异常，则返回0
	 * 
	 * @param longstr
	 *            String 待转化的数字字符串
	 * @return long 返回由数字字符串转化成的数字，当longstr为空或空字符串时，则返回0。
	 */
	public static long getLong(String longstr) {
		return getLong(longstr, 0);
	}

	/**
	 * 字符串转化为布尔型
	 * 
	 * @param booleanstr
	 *            String 待转化的字符串
	 * @param booleanDef
	 *            boolean 当字符串为空或为null时返回的值
	 * @return boolean 返回转化化的布尔值，只有当booleanstr为字符串“true”(忽略大小写)时才返回true， <BR>
	 *         如果在转化时发生异常（只有为null时），则返回缺省值booleanDef
	 */
	public static boolean getBoolean(String booleanstr, boolean booleanDef) {
		if (null == booleanstr) {
			return booleanDef;
		}
		boolean booleanRetu = booleanDef;
		if ("true".equalsIgnoreCase(booleanstr.trim())) {
			booleanRetu = true;
		}

		return booleanRetu;
	}

	/**
	 * 字符串转化为布尔型. <BR>
	 * 只有当booleanstr为字符串“true”(忽略大小写)时才返回true，其它都返回false, 包括booleanstr为null
	 * 
	 * @param booleanstr
	 *            String 待转化的字符串
	 * @return boolean 返回转化化的布尔值，
	 */
	public static boolean getBoolean(String booleanstr) {
		return getBoolean(booleanstr, false);
	}

	/**
	 * 双精数字格式化，返回字符串
	 * 
	 * @param db
	 *            double 待格式化的双精数字
	 * @param fmt
	 *            String 格式化样式，参见类说明， <BR>
	 *            fmt非法时，db将按原样转化为字符串后返回。
	 * @return String 格式化后的数字，以字符串方式返回
	 */
	public static String getNumFormat(double db, String fmt) {
		String retu = "";
		if (null == fmt || "".equals(fmt.trim())) {
			return Double.toString(db);
		}

		try {
			DecimalFormat decimalformat = new DecimalFormat(fmt);
			retu = decimalformat.format(db);
			decimalformat = null;
		} catch (IllegalArgumentException iaex) {
			// iaex.printStackTrace();
			retu = Double.toString(db);
		}
		return retu;
	}

	/**
	 * 双精数字格式化，把入参中的双精数格式化为带两位小数的数字字符串
	 * 
	 * @param db
	 *            double 待格式化的双精数字
	 * @return String 格式化为两位小数的数字字符串
	 */
	public static String getNumFormat(double db) {
		return getNumFormat(db, "0.00");
	}

	/**
	 * 数字字符串格式化，返回字符串
	 * 
	 * @param numStr
	 *            String 待格式化的数字字符串， <BR>
	 *            如果numStr参数不是的数字则不进行格式化，按原样返回
	 * @param fmt
	 *            String 格式化样式，参见类说明 <BR>
	 *            fmt非法时，将把numStr按原样返回。
	 * @return String 格式化后的字符串
	 */
	public static String getNumFormat(String numStr, String fmt) {
		double db = getDouble(numStr, 0);
		return getNumFormat(db, fmt);
	}

	/**
	 * 数字字符串格式化，把入参中的数字字符串格式化为带两位小数的数字字符串
	 * 
	 * @param numStr
	 *            String 待格式化的数字字符串， <BR>
	 *            如果numStr参数不是的数字则不进行格式化，按原样返回
	 * @return String 格式化为两位小数的数字字符串
	 */
	public static String getNumFormat(String numStr) {
		return getNumFormat(numStr, "0.00");
	}

	/**
	 * 普通字符串转化为网页中可显示的，如回车转化为&lt;br&gt;.
	 * 
	 * @param str
	 *            String 待转化的字符串
	 * @return String 转化后的字符串
	 */
	public static String htmlEncode(String str) {
		String retu = null;
		if (null == str || "".equals(str.trim())) {
			retu = str;
		} else {
			String html = str;
			html = replaceString(html, "&", "&amp;");
			html = replaceString(html, "<", "&lt;");
			html = replaceString(html, ">", "&gt;");
			html = replaceString(html, "\r\n", "\n");
			html = replaceString(html, "\n", "<br>");
			html = replaceString(html, "\t", "    ");
			html = replaceString(html, " ", "&nbsp;");
			html = replaceString(html, "\"", "&quot;");
			retu = html;
			html = null;
		}
		return retu;
	}

	/**
	 * 字符串替换，把str字符串中的所有oldStr子串替换为newStr字串
	 * 
	 * @param sourceStr
	 *            String 将被替换的字符串，为null时不执行任何替换操作
	 * @param oldStr
	 *            String 将被替换的子串，为null或为空字符串时不执行替换操作
	 * @param newStr
	 *            String 将被替换成的子串，为null时不执行替换操作
	 * @param isIgnoreCase
	 *            boolean 是否忽略大小写，true表忽略大小写，false表大小写敏感。
	 * @return String 替换后的字符串
	 */
	public static String replaceString(String sourceStr, String oldStr,
			String newStr, boolean isIgnoreCase) {
		if (sourceStr == null || oldStr == null || oldStr.equals("")) {
			return null;
		}
		String str_RetStr = sourceStr;
		int pos = str_RetStr.indexOf(oldStr);
		while (pos != -1) {
			str_RetStr = str_RetStr.substring(0, pos) + newStr
					+ str_RetStr.substring(pos + oldStr.length());
			pos = str_RetStr.indexOf(oldStr, pos + newStr.length());
		}
		return str_RetStr;
	}

	/**
	 * 字符串替换，把str字符串中的所有oldStr子串替换为newStr字串(大小写敏感)
	 * 
	 * @param sourceStr
	 *            String 将被替换的字符串，为null时不执行任何替换操作
	 * @param oldStr
	 *            String 将被替换的子串，为null或为空字符串时不执行替换操作
	 * @param newStr
	 *            String 将被替换成的子串，为null时不执行替换操作
	 * @return String 替换后的字符串
	 */
	public static String replaceString(String sourceStr, String oldStr,
			String newStr) {
		return replaceString(sourceStr, oldStr, newStr, false);
	}

	/**
	 * 字符串按指定分隔符分解为一个数组（大小写敏感）. <BR>
	 * 例子： <BR>
	 * String[] array = StringUtils.splictString("AA/BBB/CCC//DDDD//" , "/" ); <BR>
	 * 结果： <BR>
	 * array[0]="AA" array[1]="BBB" array[2]="CCC" array[3]="DDDD"
	 * 
	 * @param sourceStr
	 *            String 源字符串
	 * @param splitStr
	 *            String 分隔符
	 * @return String[] 字符串数组, <BR>
	 *         源字符串为null或为空字符串时，返回长度为1的数组，元素值为空字符串 <BR>
	 *         分隔符为null或为空字符串时，返回长度为1的数组，元素值为源字符串
	 */
	public static String[] splitString(String sourceStr, String splitStr,
			boolean isTrim) {
		if (sourceStr == null || splitStr == null) {
			return null;
		}

		if (isTrim) // 去掉开始和结束的分隔符
		{
			// 开头的间隔符去掉
			while (sourceStr.indexOf(splitStr) == 0) {
				sourceStr = sourceStr.substring(splitStr.length());
			}

			// 尾部的间隔符去掉
			while (sourceStr.indexOf(splitStr,
					sourceStr.length() - splitStr.length()) > -1) {
				sourceStr = sourceStr.substring(0, sourceStr.length()
						- splitStr.length());
			}
		}
		if (sourceStr.equals("") || splitStr.equals("")) {
			return null;
		}
		return splitString(sourceStr, splitStr);
	}

	/**
	 * 功能简述 :把字符串放入一个数组
	 * 
	 * @param sourceStr
	 *            要被放入的字符串
	 * @param splitStr
	 *            间隔符
	 * @return 转换后的数组,sourceStr或splitStr ＝ null 或 "" 返回null
	 */
	public static String[] splitString(String sourceStr, String splitStr) {
		if (sourceStr == null || splitStr == null) {
			return null;
		}
		if (sourceStr.equals("") || splitStr.equals("")) {
			return null;
		}
		int int_ArraySize = subStringCount(sourceStr, splitStr);
		// 如果为-1则返回
		if (int_ArraySize == -1) {
			return null;
		}

		sourceStr += splitStr;

		String[] str_RetArr = new String[int_ArraySize + 1];
		int int_pos = sourceStr.indexOf(splitStr);
		int int_ArrayPos = 0;
		while (int_pos != -1) {
			str_RetArr[int_ArrayPos++] = sourceStr.substring(0, int_pos);
			sourceStr = sourceStr.substring(int_pos + splitStr.length());
			int_pos = sourceStr.indexOf(splitStr);
		}

		return str_RetArr;
	}

	/**
	 * 功能简述 :把字符串放入一个数组
	 * 
	 * @param sourceStr
	 *            要被放入的字符串
	 * @param splitStr
	 *            间隔符
	 * @return 转换后的List,sourceStr或splitStr ＝ null 或 "" 返回元素个数为0的List
	 */
	@SuppressWarnings("unchecked")
	public static List splitString2List(String sourceStr, String splitStr) {
		String[] tempArr = splitString(sourceStr, splitStr);
		List resultList = new ArrayList();
		if (tempArr != null) {
			for (int i = 0; i < tempArr.length; i++) {
				resultList.add(tempArr[i]);
			}
		}
		return resultList;
	}

	/**
	 * 功能简述 :把字符串放入一个数组
	 * 
	 * @param sourceStr
	 *            要被放入的字符串
	 * @param splitStr
	 *            间隔符
	 * @return 转换后的List,sourceStr或splitStr ＝ null 或 "" 返回元素个数为0的List
	 */
	@SuppressWarnings("unchecked")
	public static List splitString2List(String sourceStr, String splitStr,
			boolean isTrim) {
		String[] tempArr = splitString(sourceStr, splitStr, isTrim);
		List resultList = new ArrayList();
		if (tempArr != null) {
			for (int i = 0; i < tempArr.length; i++) {
				resultList.add(tempArr[i]);
			}
		}
		return resultList;
	}

	/**
	 * 功能简述 :在一个字符串中查找字符串个数(不区分大小写)
	 * 
	 * @param sourceStr
	 *            要被查询的字符串
	 * @param subStr
	 *            要查找的字符串
	 * @return 找到的个数
	 * */
	public static int subStringCount(String sourceStr, String subStr) {
		// 如果是空指针则返回-1
		if (sourceStr == null || subStr == null) {
			return -1;
		}
		if (sourceStr.equals("") || subStr.equals("")) {
			return -1;
		}
		int result = 0;
		int int_pos = sourceStr.toUpperCase().indexOf(subStr.toUpperCase());
		while (int_pos != -1) {
			result++;
			int_pos = sourceStr.toUpperCase().indexOf(subStr.toUpperCase(),
					int_pos + subStr.length());
		}

		return result;
	}

	/**
	 * 用指定的分隔符把字符串数组合并成一个字符串. 数组为null或长度为0时返回空字符串 <BR>
	 * 例子： <BR>
	 * String[] array = {"1",null,"","3"}; <BR>
	 * StringUtils.arrayToString(array,"|"); <BR>
	 * 返回结果：1|||3
	 * 
	 * @param array
	 *            String[] 待合并的数组
	 * @param splitStr
	 *            String 数组各元素合并后，它们之间的分隔符，为null时用空字符串代替
	 * @return String 合并后的字符串
	 */
	public static String arrayToString(String[] array, String splitStr) {
		if (null == array || 0 == array.length) {
			return "";
		}
		if (null == splitStr) {
			splitStr = "";
		}
		StringBuffer strBuf = new StringBuffer("");
		int Len = array.length;
		for (int i = 0; i < Len - 1; i++) {
			strBuf.append((null == array[i]) ? "" : array[i]).append(splitStr);
		}
		strBuf.append((null == array[Len - 1]) ? "" : array[Len - 1]);

		return strBuf.toString();
	}

	/**
	 * 用默认分隔符 | 把字符串数组合并成一个字符串. 数组为null或长度为0时返回空字符串
	 * 
	 * @param array
	 *            String[] 待合并的数组
	 * @return String 合并后的字符串
	 */
	public static String arrayToString(String[] array) {
		return arrayToString(array, "|");
	}

	/**
	 * 判断字符串是否为null或为空字符串（包括只含空格的字符串）
	 * 
	 * @param str
	 *            String 待检查的字符串
	 * @return boolean 如果为null或空字符串（包括只含空格的字符串）则返回true，否则返回false
	 */
	public static boolean isNullBlank(String str) {
		return (null == str || "".equals(str.trim())) ? true : false;
	}

	/**
	 * 从字符串中得到指定位置的子串（按分隔符分隔，大小写敏感）. <BR>
	 * 例子： <BR>
	 * StringUtils.decomposeString("a||b|c|d","|",1); <BR>
	 * StringUtils.decomposeString("a||b|c|d","|",2); <BR>
	 * StringUtils.decomposeString("a||b|c|d","|",3); <BR>
	 * StringUtils.decomposeString("a||b|c|d","|",4); <BR>
	 * StringUtils.decomposeString("a||b|c|d","|",5); <BR>
	 * 返回结果： <BR>
	 * a,空字符串,b,c,d
	 * 
	 * @param sourceStr
	 *            String 源字符串
	 * @param splitStr
	 *            String 分隔符
	 * @param pos
	 *            int 位置，从1开始
	 * @return String 返回指定位置的子串。 <BR>
	 *         当分隔符为null或为空字符串时返回源字符串； <BR>
	 *         当源字符串为null或为空字符串时返回空字符串； <BR>
	 *         当指定位置小于1或大于分隔符个数-1时返回空字符串。
	 */
	public static String decomposeString(String sourceStr, String splitStr,
			int pos) {
		String retu = "";
		if (null == sourceStr || "".equals(sourceStr.trim())) {
			return "";
		}

		if (pos <= 0) {
			return "";
		}

		if (null == splitStr || "".equals(splitStr)) {
			return sourceStr;
		}

		sourceStr = sourceStr + splitStr;
		String tmpStr = sourceStr;

		int splitLen = splitStr.length();
		int tmpLen = tmpStr.length();

		for (int i = 0; i < pos - 1; i++) {
			int tmpPosi = tmpStr.indexOf(splitStr);
			if (tmpPosi < 0 || tmpPosi + splitLen >= tmpLen) {
				tmpStr = splitStr;
				break;
			} else {
				tmpStr = tmpStr.substring(tmpPosi + splitLen);
			}
		}
		retu = tmpStr.substring(0, tmpStr.indexOf(splitStr));
		return retu;
	}

	/**
	 * 从字符串中得到指定位置的子串（按分隔符 | 分隔）.
	 * 
	 * @param sourceStr
	 *            String 源字符串
	 * @param pos
	 *            int 位置，从1开始
	 * @return String 返回指定位置的子串。 <BR>
	 *         当分隔符为null或为空字符串时返回源字符串； <BR>
	 *         当源字符串为null或为空字符串时返回空字符串； <BR>
	 *         当指定位置小于1或大于分隔符个数-1时返回空字符串。
	 */
	public static String decomposeString(String sourceStr, int pos) {
		return decomposeString(sourceStr, "|", pos);
	}

	/**
	 * 删除指定的前导、后导子串（大小写敏感）. <br>
	 * 例子： <br>
	 * StringUtils.trim("and and username = '123' and password = 'abc' and ",
	 * "and "); <br>
	 * 结果：username = '123' and password = 'abc'
	 * 
	 * @param sourceStr
	 *            String 待删除的字符串
	 * @param removeChar
	 *            char 子串
	 * @return String 处理后的字符串
	 */
	public static String trim(String sourceStr, char removeChar) {
		if (sourceStr == null) {
			return null;
		}
		sourceStr = sourceStr.trim();
		int loInt_begin = 0, loInt_end = 0;
		int loInt_len = sourceStr.length();
		for (int i = 0; i < loInt_len; i++) {
			if (sourceStr.charAt(i) == removeChar) {
				loInt_begin++;
			} else {
				break;
			}
		}
		for (int i = 0; i < loInt_len; i++) {
			if (sourceStr.charAt(loInt_len - 1 - i) == removeChar) {
				loInt_end++;
			} else {
				break;
			}
		}
		return sourceStr.substring(loInt_begin, loInt_len - loInt_end);
	}

	/**
	 * 删除指定的前导、后导子串（大小写敏感）. <br>
	 * 例子： <br>
	 * StringUtils.trim("and and username = '123' and password = 'abc' and ",
	 * "and "); <br>
	 * 结果：username = '123' and password = 'abc'
	 * 
	 * @param sourceStr
	 *            String 待删除的字符串
	 * @param removeChar
	 *            char 子串
	 * @param end
	 *            true 只trim末尾，false 只trim头部
	 * @return String 处理后的字符串
	 */
	public static String trim(String sourceStr, char removeChar, boolean end) {
		if (sourceStr == null) {
			return null;
		}
		sourceStr = sourceStr.trim();
		int loInt_begin = 0, loInt_end = 0;
		int loInt_len = sourceStr.length();
		for (int i = 0; i < loInt_len; i++) {
			if (sourceStr.charAt(i) == removeChar) {
				loInt_begin++;
			} else {
				break;
			}
		}
		for (int i = 0; i < loInt_len; i++) {
			if (sourceStr.charAt(loInt_len - 1 - i) == removeChar) {
				loInt_end++;
			} else {
				break;
			}
		}
		if (end) {
			return sourceStr.substring(0, loInt_len - loInt_end);
		} else {
			return sourceStr.substring(loInt_begin);
		}
	}

	/**
	 * <pre>
	 * 将字符串截短到特定的长度,一个中文是两个字节的长度，但是是1个字符的长度，例：
	 * 1)truncate("软件公司",2,CHAR_LEN) = "软件"
	 * 2)truncate("软件公司",2,BYTE_LEN) = "软"
	 * 3)truncate("dragonsoft",2,CHAR_LEN) = "dr"
	 * 4)truncate("dragonsoft",2,BYTE_LEN) = "dr"
	 * </pre>
	 * 
	 * @param str
	 *            String 需要截的字符串
	 * @param len
	 *            int 截到的目标长度
	 * @param lenType
	 *            int 长度类型,字符长度{@link CHAR_LEN},字符长度{@link BYTE_LEN}
	 * @return String
	 */
	public static String truncate(String str, int len, int lenType) {
		if (str == null) {
			return str;
		}
		// 字符长度
		if (lenType == CHAR_LEN) {
			if (str.length() <= len) {
				return str;
			} else {
				return str.substring(0, len);
			}
		} else // 字节长度
		{
			StringBuffer sb = new StringBuffer();
			int temp = 0;

			for (int i = 0; i < str.length(); i++) {
				if (i < len) // 是否到指定的长度
				{
					temp = ("" + str.charAt(i)).getBytes().length;
					if (i + temp <= len) {
						sb.append(str.charAt(i));
					} else {
						break;
					}
				} else {
					break;
				}
			}
			return sb.toString();
		}
	}

	/**
	 * 从源字符串中从第一个字符开始取出给定长度的字串. <BR>
	 * 源字符串长度大于len时，尾巴追加一个appendStr串
	 * 
	 * @param sourceStr
	 *            String 源字符串
	 * @param len
	 *            int 取出的长度
	 * @param appendStr
	 *            String 追加的字符串（常用的appendStr为...）
	 * @return String 取出的子串
	 */
	public static String substring(String sourceStr, int len, String appendStr) {
		if (null == sourceStr || "".equals(sourceStr)) {
			return sourceStr;
		}
		if (len <= 0) {
			return "";
		}

		if (null == appendStr) {
			appendStr = "";
		}

		int sourceLen = sourceStr.length();
		if (len >= sourceLen) {
			return sourceStr;
		} else {
			return sourceStr.substring(0, len) + appendStr;
		}
	}

	/**
	 * 产生指定长度随机字符串（包括字母及数字）.
	 * 
	 * @param length
	 *            int 随机字符串长度
	 * @return String 随机字符串
	 */
	public static String random(int length) {
		String retu = "";
		int d1, d2;
		char[] letters = initLetters();

		for (int i = 0; i < length; i++) {
			d1 = ((int) (Math.random() * 10) % 2);
			if (d1 == 0) { // use a letter
				d2 = ((int) (Math.random() * 100) % 52);
				retu += letters[d2];
			} else if (d1 == 1) { // use a number
				retu += (int) (Math.random() * 10);
			}
		}
		return retu;
	}

	/**
	 * 产生纯字母的随机字符串
	 * 
	 * @param length
	 *            int 随机字符串长度
	 * @return String 随机字符串
	 */
	public static String randomString(int length) {
		String retu = "";
		int d2;
		char[] letters = initLetters();

		for (int i = 0; i < length; i++) {
			d2 = ((int) (Math.random() * 100) % 52);
			retu += letters[d2];
		}
		return retu;
	}

	/**
	 * 产生纯数字随机字符串
	 * 
	 * @param length
	 *            int 随机字符串长度
	 * @return String 随机字符串
	 */
	public static String randomNumber(int length) {
		String retu = "";

		for (int i = 0; i < length; i++) {
			retu += (int) (Math.random() * 10);
		}
		return retu;
	}

	/**
	 * 内部方法，产生字母数组
	 * 
	 * @return char[]
	 */
	private static char[] initLetters() {
		char[] ca = new char[52];
		for (int i = 0; i < 26; i++) {
			ca[i] = (char) (65 + i);
		}
		for (int i = 26; i < 52; i++) {
			ca[i] = (char) (71 + i);
		}
		return ca;
	}

	/**
	 * 对字符串进行加减密处理.
	 * 
	 * @param source
	 *            String 待加减密处理的字符串
	 * @param flag
	 *            boolean true表进行加密，false表进行减密
	 * @return String 处理后的字符串. <BR>
	 *         source为空则返回原字符串source， <BR>
	 *         如果source中含有中文字符则对这一中文字符不处理.
	 */
	public static String encrypt(String source, boolean flag) {
		if (null == source || "".equals(source.trim())) {
			return source;
		}
		String LS_KEY1 = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890abcdefghijklmnopqrstuvwxyz~!@#$%^&*(),.;[]{}";
		String LS_KEY2 = "&*()AD;[BFLCGH]{PQ}EI!JKRYZ1MSTXNO23UV,.Wcd45ef6lm7g@h0a#$nij8ob%^ptvz9ku~qrswxy";

		char[] ch_source = source.toCharArray();

		StringBuffer strBuf = new StringBuffer(ch_source.length);

		int pos;
		for (int i = 0, Len = ch_source.length; i < Len; i++) {
			if (flag) { // 加密
				pos = LS_KEY1.indexOf(ch_source[i]);
				if (pos >= 0) {
					strBuf.append(LS_KEY2.substring(pos, pos + 1));
				} else {
					strBuf.append(ch_source[i]);
				}
			} else { // 减密
				pos = LS_KEY2.indexOf(ch_source[i]);
				if (pos >= 0) {
					strBuf.append(LS_KEY1.substring(pos, pos + 1));
				} else {
					strBuf.append(ch_source[i]);
				}
			}
		}

		return strBuf.toString();

	}

	/**
	 * 将null字符串转换为空串，方便HTML的显示
	 * 
	 * @param sourceStr
	 *            待处理的字符串
	 * @return String 处理的的字符串
	 */
	public static String toVisualString(String sourceStr) {
		if (sourceStr == null || sourceStr.equals("")) {
			return "";
		} else {
			return sourceStr;
		}
	}

	/**
	 * 将字段填充到指定的长度
	 * 
	 * @param sourceStr
	 *            String 操作字符串
	 * @param length
	 *            int 指定长度
	 * @param withChar
	 *            char 填充的字符
	 * @param isPadToEnd
	 *            boolean 是否填充在字符的尾部 true ：是 ,false:填充在头部
	 * @return String
	 */
	public static String pad(String sourceStr, int length, char withChar,
			boolean isPadToEnd) {
		if (sourceStr == null) {
			return null;
		}
		if (sourceStr.length() >= length) {
			return sourceStr;
		}

		StringBuffer sb = new StringBuffer(sourceStr);
		for (int i = 0; i < length - sourceStr.length(); i++) {
			if (isPadToEnd) {
				sb.append(withChar);
			} else {
				sb.insert(0, withChar);
			}
		}
		return sb.toString();

	}

	public static String toUtf8String(String s) {
		String strTitle = s.substring(0, s.lastIndexOf("."));
		String strExct = s.substring(s.lastIndexOf("."), s.length());
		StringBuffer sb = new StringBuffer();

		for (int i = 0; i < strTitle.length(); i++) {
			char c = strTitle.charAt(i);
			if (c >= 0 && c <= 255) {
				if (sb.toString().length() < 147) {
					sb.append(c);
				} else {
					sb.append("[1]");
					break;
				}
			} else {
				byte[] b;
				try {
					b = (new Character(c).toString()).getBytes("UTF-8"); // "utf-8"
				} catch (Exception ex) {
					b = new byte[0];
				}
				if (sb.toString().length() < 140) {
					for (int j = 0; j < b.length; j++) {
						int k = b[j];
						if (k < 0) {
							k += 256;
						}
						sb.append("%" + Integer.toHexString(k).toUpperCase());
					}
				} else {
					sb.append("[1]");
					break;
				}
			}
		}
		return sb.toString() + strExct;
	}

	public static String join(Object[] array, String separator) {
		return org.apache.commons.lang.StringUtils.join(array, separator);
	}

	/**
	 * 替换字符串特殊符号为空
	 * 
	 * @param str
	 * @return
	 */
	public static String replaceCharacterToBlank(String str) {

		return str.replaceAll("：", "").replaceAll(":", "").replaceAll("；", "")
				.replaceAll(";", "").replaceAll("\"", "").replaceAll("\'", "")
				.replaceAll("“", "").replaceAll("”", "").replaceAll("‘", "")
				.replaceAll("’", "").replaceAll("&", "").replaceAll("&", "")
				.replaceAll("【", "").replaceAll("】", "").replaceAll("——", "")
				.replaceAll("-", "").replaceAll("-", "").replaceAll("！", "")
				.replaceAll("`", "").replaceAll("#", "").replaceAll("$", "")
				.replaceAll("~", "").replaceAll("￥", "").replaceAll("=", "")
				.replaceAll("|", "").replaceAll("《", "").replaceAll("<", "")
				.replaceAll("》", "").replaceAll(">", "").replaceAll("，", "")
				.replaceAll(",", "").replaceAll("……", "").replaceAll("%", "")
				.replaceAll("@", "").replaceAll("、", "")
				.replaceAll("\\u0028", "").replaceAll("\\u0029", "")
				.replaceAll("\\u002B", "").replaceAll("\\u007B", "")
				.replaceAll("}", "").replaceAll("（", "").replaceAll("）", "")
				.replaceAll("·", "").replaceAll("。", "");
	}

	public static void main(String[] args) {
		// String aa = ",,,aa,aa,aa,,,,";
		// System.out.println("array:"+splitString(aa,",").length);
		// System.out.println("array:"+splitString(aa,",",true).length);
		System.out.println(truncate("aaasdf", 2, BYTE_LEN));
		System.out.println(truncate("aaasd", 4, CHAR_LEN));
		System.out.println(truncate("dragonasd", 6, BYTE_LEN));
		System.out.println(truncate("dragonasd", 6, CHAR_LEN));
	}
}
