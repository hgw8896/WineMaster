package hoo.android.hooutil.utils;

import android.util.Log;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 *
 * @author HOO
 * 
 */
public class DateUtils {

	private static String[] WEEKDAYSCHINESE = { "星期日", "星期一", "星期二", "星期三", "星期四","星期五", "星期六" }; // 1
    private static String[] WEEKDAYSCHINESESIMPLE = { "周日", "周一", "周二", "周三", "周四","周五", "周六" };//2
    private static String[] WEEKDAYSEN = { "Sun.", "Mon.", "Tues.", "Wed.", "Thur.","Fri.", "Sat." };//3
    private static String[] WEEKDAYSEN2 = { "Sun", "Mon", "Tues", "Wed", "Thur","Fri", "Sat" };//4
    private static String[] WEEKDAYSEN3 = { "Sun", "Mon", "Tue", "Wed", "Thu","Fri", "Sat" };//5
    private static String[] WEEKDAYSCHINESESIMPLE2 = { "日", "一", "二", "三", "四","五", "六" };//6
	/*
	 * 从字符串转换成日期 由于如果参数为空的时候会出现找不到源的错误，所以做了修改
	 * @param dateString
	 * @return
	 */
	public static Date StrToDate(String dateString) {
		if (dateString != null && !dateString.equals("")) {
			Date date = null;
			try {
				if (dateString.length() <= 10) {
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					date = sdf.parse(dateString);
				} else {
					SimpleDateFormat sdf = new SimpleDateFormat(
							"yyyy-MM-dd HH:mm:ss");
					date = sdf.parse(dateString);
				}
			} catch (ParseException e) {
				e.printStackTrace();
			}
			return date;
		} else {
			return null;
		}
	}


    // 时间字符串，时间格式，星期是否是中文，星期的位置是否放到最后，week
	/*
	 * yyyy-MM-dd HH:mm:ss(19位)/yyyy-MM-dd(10位)
	 * @return 06月23日 星期一 06:00
	 */
	public static String Str2StrAll2(String dateString,String dateFormat1,String dateFormat2,int weekLang,String[] weeks) {
        StringBuffer sbDate = new StringBuffer();
		if (dateString != null && !dateString.equals("")) {
			Date date1 = null;
            Date date2 = null;
			SimpleDateFormat sdf1 = null;
            SimpleDateFormat sdf2 = null;
			try {
                if (BaseStringUtil.isNotEmpty(dateFormat1)&&BaseStringUtil.isNotEmpty(dateFormat2)){
                    sdf1 = new SimpleDateFormat(dateFormat1);
                    date1 = sdf1.parse(dateString);
                    sdf2 = new SimpleDateFormat(dateFormat2);
                    date2 = sdf2.parse(dateString);
                }
				Calendar cal = Calendar.getInstance();
				cal.setTime(date1);
                sbDate.append(sdf1.format(date1));
                int w = cal.get(Calendar.DAY_OF_WEEK) - 1;// 星期
                if (w < 0) w = 0;
                switch (weekLang){
                    case 1:
                        sbDate.append(WEEKDAYSCHINESE[w]);
                        break;
                    case 2:
                        sbDate.append(WEEKDAYSCHINESESIMPLE[w]);
                        break;
                    case 3:
                        sbDate.append(WEEKDAYSEN[w]);
                        break;
                    case 4:
                        sbDate.append(WEEKDAYSEN2[w]);
                        break;
                    case 5:
                        sbDate.append(WEEKDAYSEN3[w]);
                        break;
                    case 6:
                        sbDate.append(WEEKDAYSCHINESESIMPLE2[w]);
                        break;
                    case 7:
                        if (BaseStringUtil.isNotEmpty(weeks)){
                            sbDate.append(weeks[w]);
                        }else{
                            sbDate.append(WEEKDAYSCHINESE[w]);
                        }
                        break;
                    default:
                        sbDate.append(WEEKDAYSCHINESE[w]);
                        break;
                }
                sbDate.append(sdf1.format(date2));
                return  sbDate.toString();
			} catch (ParseException e) {
				e.printStackTrace();
			}
		} else {
			return null;
		}
		return null;
	}



    public static String Str2StrAll(String dateString,String dateFormat,int weekLang,String[] weeks) {
        StringBuffer sbDate = new StringBuffer();
        if (dateString != null && !dateString.equals("")) {
            Date date = null;
            SimpleDateFormat sdf = null;
            try {
                if (BaseStringUtil.isNotEmpty(dateFormat)){
                    sdf = new SimpleDateFormat(dateFormat);
                    date = sdf.parse(dateString);
                }
                Calendar cal = Calendar.getInstance();
                cal.setTime(date);
                sbDate.append(sdf.format(date));
                int w = cal.get(Calendar.DAY_OF_WEEK) - 1;// 星期
                if (w < 0)
                    w = 0;
                switch (weekLang){
                    case 1:
                        sbDate.append(WEEKDAYSCHINESE[w]);
                        break;
                    case 2:
                        sbDate.append(WEEKDAYSCHINESESIMPLE[w]);
                        break;
                    case 3:
                        sbDate.append(WEEKDAYSEN[w]);
                        break;
                    case 4:
                        sbDate.append(WEEKDAYSEN2[w]);
                        break;
                    case 5:
                        sbDate.append(WEEKDAYSEN3[w]);
                        break;
                    case 6:
                        sbDate.append(WEEKDAYSCHINESESIMPLE2[w]);
                        break;
                    case 7:
                        if (BaseStringUtil.isNotEmpty(weeks)){
                            sbDate.append(weeks[w]);
                        }else{
                            sbDate.append(WEEKDAYSCHINESE[w]);
                        }
                        break;
                    default:
                        sbDate.append(WEEKDAYSCHINESE[w]);
                        break;
                }
                return  sbDate.toString();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        } else {
            return null;
        }
        return null;
    }
    public static String StrToStrForMillis(long millis) {
        if (millis>0){
            Calendar cal = Calendar.getInstance();
            cal.setTimeInMillis(millis);

            Date date = cal.getTime();
            String dateString = dateToStringTime(date);
           return dateString;
        } else {
            return null;
        }
    }

    /*
     * 日期转化为字符串yyyy-MM-dd HH:mm:ss
     * @param
     * @return
     */
    public static String dateToStringTime(Date date) {
        if (date != null) {
            String string = null;
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            string = sdf.format(date);
            return string;
        } else {
            return "";
        }
    }

    /**
     * date
     *
     * format : yyyy-MM-dd HH:mm:ss
     * */
    public static String StrToStrForMillis(String strmillis,String format) {
        long millis = 0;
        String string = null;
        if (BaseStringUtil.isNotEmpty(strmillis)){
            millis = Long.parseLong(strmillis);
        }
        if (millis>0){
            Calendar cal = Calendar.getInstance();
            cal.setTimeInMillis(millis);
            Date date = cal.getTime();
            if (date != null) {
                SimpleDateFormat sdf = new SimpleDateFormat(format);
                string = sdf.format(date);
            } else {
                string = "";
            }
        }
        return string;
    }



	public static String Str2StrGetWHM2(String dateString, String week) {
		if (dateString != null && !dateString.equals("")) {
			Date date = null;
			SimpleDateFormat sdf;
			try {
				if (dateString.length() <= 10) {
					sdf = new SimpleDateFormat("yyyy-MM-dd");
					date = sdf.parse(dateString);
				} else {
					sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					date = sdf.parse(dateString);
				}
				Calendar cal = Calendar.getInstance();
				cal.setTime(date);
				int w = cal.get(Calendar.DAY_OF_WEEK) - 1;// 星期
				if (w < 0)
					w = 0;
				int hour = cal.get(Calendar.HOUR);
				int minute = cal.get(Calendar.MINUTE);
				return "" + week + " " + (hour >= 10 ? hour : "0" + hour) + ":"
						+ (minute >= 10 ? minute : "0" + minute);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		} else {
			return null;
		}
		return null;
	}


	// 获取某个日期的时间字符串（时:分:秒）时间戳
	public static String getTimeString(Date date) {
		String time = "";
		if (date != null) {
			String dateStr = dateToStringTime(date);
			time = dateStr.substring(11);
		}
		return time;
	}

	/*
	 * 从字符串转换成日期,格式自定义
	 * 
	 * @param dateString
	 * @param formatString
	 * @return
	 */
	public static Date strToDate(String dateString, String formatString) {
		if (dateString != null && !dateString.equals("")) {
			Date date = null;
			try {
				SimpleDateFormat sdf = new SimpleDateFormat(formatString);
				date = sdf.parse(dateString);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			return date;
		} else {
			return null;
		}
	}


    public static String strToStr(String dateString) {
        if (dateString != null && !dateString.equals("")) {
            Date date = null;
            try {
                String formatString = "yyyy-MM-dd HH:mm:ss";
                SimpleDateFormat sdf = new SimpleDateFormat(formatString);
                date = sdf.parse(dateString);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            int year = cal.get(Calendar.YEAR);
            int month = cal.get(Calendar.MONTH) + 1;
            int day = cal.get(Calendar.DAY_OF_MONTH);
            int hour = cal.get(Calendar.HOUR_OF_DAY);
            int minute = cal.get(Calendar.MINUTE);
            return year+"." + (month >= 10 ? month : "0" + month) + "."
                    + (day >= 10 ? day : "0" + day) + " "+(hour > 9 ? hour : "0" + hour)+":"+(minute > 9 ? minute : "0" + minute);

           // return date;
        } else {
            return null;
        }
    }

	/*
	 * 从日期转换成字符串yyyy-MM-dd
	 * 
	 * @param date
	 * @return
	 */
	public static String dateToString(Date date) {
		if (date != null) {
			String string = null;
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			string = sdf.format(date);
			return string;
		} else {
			return "";
		}
	}



	/*
	 * 得到系统日期
	 *
	 * @return
	 */
	public static Date getDate() {
		Date date = new Date();
		return date;
	}

	/*
	 * 得到系统日期并转换成字符串型
	 * 
	 * @return
	 */
	public static String getDateString() {
		Date aDate = new Date(); // 取得系统时间
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString = sdf.format(aDate);// 用DateFormat对象fmt格式化Date对象aDate并赋值给String变量dateString
		return dateString;
	}

	/*
	 * Get the date of monday in this week
	 * 
	 * @return yyyy-MM-dd
	 */
	public static String getMondayOfThisWeek() {
		Calendar c = Calendar.getInstance();
		int dayofweek = c.get(Calendar.DAY_OF_WEEK) - 1;
		if (dayofweek == 0)
			dayofweek = 7;
		c.add(Calendar.DATE, -dayofweek + 1);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(c.getTime());
	}

	/*
	 * Get the date of friday in this week
	 * 
	 * @return yyyy-MM-dd
	 */
	public static String getFridayOfThisWeek() {
		Calendar c = Calendar.getInstance();
		int dayofweek = c.get(Calendar.DAY_OF_WEEK) - 1;
		if (dayofweek == 0)
			dayofweek = 7;
		c.add(Calendar.DATE, -dayofweek + 5);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(c.getTime());
	}

	/*
	 * Get the date of sunday in this week
	 * 
	 * @return yyyy-MM-dd
	 */
	public static String getSundayOfThisWeek() {
		Calendar c = Calendar.getInstance();
		int dayofweek = c.get(Calendar.DAY_OF_WEEK) - 1;
		if (dayofweek == 0)
			dayofweek = 7;
		c.add(Calendar.DATE, -dayofweek + 7);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(c.getTime());
	}

	/*
	 * 得到本月最后一天
	 * 
	 * @return
	 */
	public static String getLastDateOfMonth() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date dt = getDate();

		if (dt == null)
			return null;
		Calendar cal = Calendar.getInstance();
		cal.setTime(dt);
		int days = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		cal.set(Calendar.DAY_OF_MONTH, days);
		String result = format.format(cal.getTime());
		System.out.println("一个月最后一天" + result);
		return result;
	}

	/*
	 * 得到本月最后一天
	 * 
	 * @return
	 */
	public static String getLastDateOfSomeMonth(Date date) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date dt = date;

		if (dt == null)
			return null;
		Calendar cal = Calendar.getInstance();
		cal.setTime(dt);
		int days = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		cal.set(Calendar.DAY_OF_MONTH, days);
		String result = format.format(cal.getTime());
		System.out.println("一个月最后一天" + result);
		return result;
	}

	/*
	 * 得到本月第一天
	 * 
	 * @return
	 */
	public static String getFristDateOfMonth() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date dt = getDate();

		if (dt == null)
			return null;
		Calendar cal = Calendar.getInstance();
		cal.setTime(dt);
		int days = cal.getActualMinimum(Calendar.DAY_OF_MONTH);
		cal.set(Calendar.DAY_OF_MONTH, days);
		String result = format.format(cal.getTime());
		System.out.println("一个月第一天" + result);
		return result;
	}

	/*
	 * 得到某月第一天
	 * 
	 * @return
	 */
	public static String getFristDateOfSomeMonth(Date date) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date dt = date;

		if (dt == null)
			return null;
		Calendar cal = Calendar.getInstance();
		cal.setTime(dt);
		int days = cal.getActualMinimum(Calendar.DAY_OF_MONTH);
		cal.set(Calendar.DAY_OF_MONTH, days);
		String result = format.format(cal.getTime());
		System.out.println("一个月第一天" + result);
		return result;
	}

	/*
	 * 得到本年年份
	 * 
	 * @return
	 */
	public static String getYear() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy");
		Date dt = getDate();

		if (dt == null)
			return null;
		Calendar cal = Calendar.getInstance();
		cal.setTime(dt);
		String result = format.format(cal.getTime());
		return result;
	}
	
	public static String getYear(Date dt) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy");
//		Date dt = getDate();

		if (dt == null)
			return null;
		Calendar cal = Calendar.getInstance();
		cal.setTime(dt);
		String result = format.format(cal.getTime());
		return result;
	}
	
	public static String getYear(String dt) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy");
//		Date dt = getDate();
		if (dt == null)
			return null;
		Calendar cal = Calendar.getInstance();
		cal.setTime(StrToDate(dt));
		String result = format.format(cal.getTime());
		return result;
	}
	public static String getMonthDay(String dt) {
		SimpleDateFormat format = new SimpleDateFormat("MM.dd");
//		Date dt = getDate();
		if (dt == null)
			return null;
		Calendar cal = Calendar.getInstance();
		cal.setTime(StrToDate(dt));
		cal.getTime().getMonth();
		cal.getTime().getDay();
		String result = format.format(cal.getTime());
		return result;
	}

	/*
	 * 得到本年第一天
	 * 
	 * @return
	 */
	public static String getFirstDayOfYear() {
		return getYear() + "-01-01";
	}

	/*
	 * 得到本年最后一天
	 * 
	 * @return
	 */
	public static String getLastDayOfYear() {
		return getYear() + "-12-31";
	}

	/*
	 * 计算两个日期之间的天数
	 * 
	 * @return
	 * @throws java.text.ParseException
	 */
	public static int getDayCountOfTwoDate(String date1, String date2)
			throws ParseException {
		int index = date1.indexOf(" ");
		if (index != -1) {
			date1 = date1.substring(0, index);
		}
		index = date2.indexOf(" ");
		if (index != -1) {
			date2 = date2.substring(0, index);
		}
		SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd");
		Date bdate = myFormatter.parse(date1);
		Date edate = myFormatter.parse(date2);
		Calendar aCalendar = Calendar.getInstance();
		aCalendar.setTime(bdate);
		int day1 = aCalendar.get(Calendar.DAY_OF_YEAR);
		aCalendar.setTime(edate);
		int day2 = aCalendar.get(Calendar.DAY_OF_YEAR);

		return Math.abs(day1 - day2);
	}


	/*
	 * 根据日期参数返回此日期的前一天，且时间为00:00:00
	 * 
	 * @return String
	 */
	public static Date getYesterday(String nowDate) {
		if (nowDate != null && !"".equals(nowDate)) {
			final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			try {
				Date date = sdf.parse(nowDate);
				if (date == null) {
					return null;
				}
				Calendar cal = Calendar.getInstance();
				cal.setTime(date);
				cal.add(Calendar.DAY_OF_MONTH, -1);
				return cal.getTime();
			} catch (ParseException e) {
				return null;
			}
		} else {
			return null;
		}
	}

	/*
	 * 根据日期参数返回此日期的后一天，且时间为00:00:00
	 * 
	 * @return String
	 */
	public static Date getTomorrowDay(String nowDate) {
		if (nowDate != null && !"".equals(nowDate)) {
			final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			try {
				Date date = sdf.parse(nowDate);
				if (date == null) {
					return null;
				}
				Calendar cal = Calendar.getInstance();
				cal.setTime(date);
				cal.add(Calendar.DAY_OF_MONTH, +1);
				return cal.getTime();
			} catch (ParseException e) {
				return null;
			}
		} else {
			return null;
		}
	}

	/*
	 * 根据日期参数返回此日期的前后dayNum天的那一天，且时间为00:00:00，dayNum正数为后dayNum天，负数为前dayNum天
	 * 
	 * @return String
	 */
	public static Date getPonitDay(String nowDate, int dayNum) {
		if (nowDate != null && !"".equals(nowDate)) {
			final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			try {
				Date date = sdf.parse(nowDate);
				if (date == null) {
					return null;
				}
				Calendar cal = Calendar.getInstance();
				cal.setTime(date);
				cal.add(Calendar.DAY_OF_MONTH, dayNum);
				return cal.getTime();
			} catch (ParseException e) {
				return null;
			}
		} else {
			return null;
		}
	}

	/*
	 * 根据日期参数返回此时间hNum小时后的时间
	 * 
	 * @return String
	 */
	public static Date getPonitTime(String nowDate, int hNum) {
		if (nowDate != null && !"".equals(nowDate)) {
			String nowTime = nowDate.substring(11, 16);
			String hour = nowTime.substring(0, 2);
			int newTime = Integer.parseInt(hour) + hNum;
			int dayNum = (int) (newTime / 24);
			String newTime1 = newTime % 24 + "";
			newTime1 = newTime1 + ":" + nowTime.substring(3, 5);
			final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			try {
				Date date = sdf.parse(nowDate);
				if (date == null) {
					return null;
				}
				Calendar cal = Calendar.getInstance();
				cal.setTime(date);

				cal.add(Calendar.DAY_OF_MONTH, dayNum);
				return strToDate(dateToString(cal.getTime()).substring(0, 10)
						+ " " + newTime1 + ":00", "yyyy-MM-dd HH:mm:ss");
			} catch (ParseException e) {
				return null;
			}
		} else {
			return null;
		}
	}

	/*
	 * 根据日期参数返回此时间的半个小时后的时间
	 * 
	 * @return String
	 */
	public static String getTimeAddHalfHour(String nowDate) {
		if (nowDate != null && !"".equals(nowDate)) {

			Date nowDate1 = strToDate(nowDate, "yyyy-MM-dd hh:mm:ss");
			long Time = (nowDate1.getTime() / 1000) + 60 * 30;
			nowDate1.setTime(Time * 1000);
			SimpleDateFormat formatter = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss");
			String mydate = formatter.format(nowDate1);

			return mydate;
		} else {
			return null;
		}

	}

	/*
	 * 根据日期参数返回此时间的多少分钟后的时间
	 * 
	 * @return String
	 */
	public static String getTimeAddMinute(String nowDate, int minute) {
		if (nowDate != null && !"".equals(nowDate)) {

			Date nowDate1 = strToDate(nowDate, "yyyy-MM-dd hh:mm:ss");
			Log.e("Mydateutil", "nowDate1:" + nowDate1 + "   nowDate："
                    + nowDate + "   minute：" + minute);
			long Time = (nowDate1.getTime() / 1000) + 60 * minute;
			nowDate1.setTime(Time * 1000);
			SimpleDateFormat formatter = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss");
			String mydate = formatter.format(nowDate1);

			return mydate;
		} else {
			return null;
		}
	}

	/*
	 * 根据日期参数返回此时间的多少分钟后的时间
	 * 
	 * @return String
	 */
	public static String getTimeDelMinute(String nowDate, int minute) {
		if (nowDate != null && !"".equals(nowDate)) {

			Date nowDate1 = strToDate(nowDate, "yyyy-MM-dd hh:mm:ss");
			Log.e("Mydateutil", "nowDate1:" + nowDate1 + "   nowDate："
                    + nowDate + "   minute：" + minute);
			long Time = (nowDate1.getTime() / 1000) - 60 * minute;
			nowDate1.setTime(Time * 1000);
			SimpleDateFormat formatter = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss");
			String mydate = formatter.format(nowDate1);
			return mydate;
		} else {
			return null;
		}
	}
	
	/*
	 * 根据日期参数返回此时间的多少分钟后的时间
	 * 
	 * @return String
	 */
	public static String getTimeDelSecond(String nowDate, int second) {
		if (nowDate != null && !"".equals(nowDate)) {

			Date nowDate1 = strToDate(nowDate, "yyyy-MM-dd hh:mm:ss");
			Log.e("Mydateutil", "nowDate1:" + nowDate1 + "   nowDate："
                    + nowDate + "   second：" + second);
			long Time = (nowDate1.getTime() / 1000) + second;
			nowDate1.setTime(Time * 1000);
			SimpleDateFormat formatter = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss");
			String mydate = formatter.format(nowDate1);
			return mydate;
		} else {
			return null;
		}
	}
	

	/*
	 * 比较日期
	 * 
	 * @param date1
	 * @param date2
	 * @return result 为0相等，小于0 date1小于date2
	 */
	public static int compareTo(String date1, String date2) {

		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		try {
			c1.setTime(df.parse(date1));
			c2.setTime(df.parse(date2));

		} catch (ParseException e) {
			System.err.println("格式不正确");
		}
		int result = c1.compareTo(c2);
		return result;
	}

	/*
	 * 得到所传时间参数的上一个月的同一时间，格式为yyyy-MM-dd
	 * 
	 * @param date
	 * @return
	 */
	public static String getPreMonthDate(String date) {
		try {
			if (date != null && !"".equals(date) && date.length() >= 10) {
				int current_year = Integer.parseInt(date.substring(0, 4));
				int current_month = Integer.parseInt(date.substring(5, 7));
				int previous_month = current_month - 1;
				if (previous_month == 0) {
					previous_month = 12;
					current_year = current_year - 1;
				}
				String previous_month_str = String.valueOf(previous_month);
				if (previous_month_str.length() == 1) {
					previous_month_str = "0" + previous_month_str;
				}
				String preMonthDate = current_year + "-" + previous_month_str
						+ "-" + date.substring(8, 10);
				return preMonthDate;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/*
	 * 得到所传时间参数的下一个月的同一时间，格式为yyyy-MM-dd
	 * 
	 * @param date
	 * @return
	 */
	public static String getNextMonthDate(String date) {
		try {
			if (date != null && !"".equals(date) && date.length() >= 10) {
				int current_year = Integer.parseInt(date.substring(0, 4));
				int current_month = Integer.parseInt(date.substring(5, 7));
				int next_month = current_month + 1;
				if (next_month == 13) {
					next_month = 1;
					current_year = current_year + 1;
				}
				String next_month_str = String.valueOf(next_month);
				if (next_month_str.length() == 1) {
					next_month_str = "0" + next_month_str;
				}
				String nextMonthDate = current_year + "-" + next_month_str
						+ "-" + date.substring(8, 10);
				return nextMonthDate;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/*
	 * 根据日期参数返回此日期的上个月的此天，且时间为00:00:00
	 * 
	 * @return String
	 */
	public static Date getLastMonth(String nowDate) {
		if (nowDate != null && !"".equals(nowDate)) {
			final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			try {
				Date date = sdf.parse(nowDate);
				if (date == null) {
					return null;
				}
				Calendar cal = Calendar.getInstance();
				cal.setTime(date);
				cal.add(Calendar.MONTH, -1);
				return cal.getTime();
			} catch (ParseException e) {
				return null;
			}
		} else {
			return null;
		}
	}

	/*
	 * 根据日期参数返回此日期的下个月的此天，且时间为00:00:00
	 * 
	 * @return String
	 */
	public static Date getNextMonth(String nowDate) {
		if (nowDate != null && !"".equals(nowDate)) {
			final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			try {
				Date date = sdf.parse(nowDate);
				if (date == null) {
					return null;
				}
				Calendar cal = Calendar.getInstance();
				cal.setTime(date);
				cal.add(Calendar.MONTH, +1);
				return cal.getTime();
			} catch (ParseException e) {
				return null;
			}
		} else {
			return null;
		}
	}

	/*
	 * 功能：得到参数时间的月底 格式为：xxxx-yy-zz (eg: 2007-12-31)
	 * 
	 * @return String
	 * @author pure
	 **/
	public static String getMonthEnd(Date nowDate) {
		int x; // 日期属性：年
		int y; // 日期属性：月
		Calendar localTime = Calendar.getInstance();
		localTime.setTime(nowDate); // 当前日期
		String strY = null;
		String strZ = null;
		boolean leap = false;
		x = localTime.get(Calendar.YEAR);
		y = localTime.get(Calendar.MONTH) + 1;
		if (y == 1 || y == 3 || y == 5 || y == 7 || y == 8 || y == 10
				|| y == 12) {
			strZ = "31";
		}
		if (y == 4 || y == 6 || y == 9 || y == 11) {
			strZ = "30";
		}
		if (y == 2) {
			leap = DateUtils.leapYear(x);
			if (leap) {
				strZ = "29";
			} else {
				strZ = "28";
			}
		}
		strY = y >= 10 ? String.valueOf(y) : ("0" + y);
		return x + "-" + strY + "-" + strZ;
	}

	/*
	 * 功能：得到参数时间的月初 格式为：xxxx-yy-zz (eg: 2007-12-01)
	 * 
	 * @return String
	 * @author pure
	 */
	public static String thisMonth(Date nowDate) {
		int x; // 日期属性：年
		int y; // 日期属性：月
		Calendar localTime = Calendar.getInstance();
		localTime.setTime(nowDate); // 当前日期
		String strY = null;
		x = localTime.get(Calendar.YEAR);
		y = localTime.get(Calendar.MONTH) + 1;
		strY = y >= 10 ? String.valueOf(y) : ("0" + y);
		return x + "-" + strY + "-01";
	}

	/*
	 * 功能：得到参数时间的年份年初 格式为：xxxx-yy-zz (eg: 2007-01-01)<br>
	 * 
	 * @return String
	 * @author pure
	 */
	public static String thisYear(Date nowDate) {
		int x; // 日期属性：年
		int y; // 日期属性：月
		Calendar localTime = Calendar.getInstance();
		localTime.setTime(nowDate); // 当前日期
		x = localTime.get(Calendar.YEAR);
		return x + "-01" + "-01";
	}

	/*
	 * 功能：得到参数时间的年份年底 格式为：xxxx-yy-zz (eg: 2007-12-31)<br>
	 * 
	 * @return String
	 * @author pure
	 */
	public static String thisYearEnd(Date nowDate) {
		int x; // 日期属性：年
		int y; // 日期属性：月
		Calendar localTime = Calendar.getInstance();
		localTime.setTime(nowDate); // 当前日期
		x = localTime.get(Calendar.YEAR);
		return x + "-12" + "-31";
	}

	/*
	 * 功能：判断输入年份是否为闰年
	 * 
	 * @param year
	 * @return 是：true 否：false
	 * @author pure
	 */
	public static boolean leapYear(int year) {
		boolean leap;
		if (year % 4 == 0) {
			if (year % 100 == 0) {
				if (year % 400 == 0)
					leap = true;
				else
					leap = false;
			} else
				leap = true;
		} else
			leap = false;
		return leap;
	}

	/*
	 * 功能：得到时间参数的下一年
	 * 
	 * @param
	 * @return 是：true 否：false
	 * @author pure
	 */
	public static Date nextYear(Date currentDate) {
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(currentDate);
		cal.add(GregorianCalendar.YEAR, 1);// 在年上加1
		return cal.getTime();
	}

	/*
	 * 日期比较两个时间之间差多少秒
	 * 
	 * @param date
	 * @param date1
	 * @return
	 */
	public static long compareToSecond(String date, String date1) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			return (df.parse(date).getTime() - df.parse(date1).getTime()) / 1000;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	public static long compareToDate(String date, String date1) {
		Calendar cal1 = Calendar.getInstance();
		Calendar cal2 = Calendar.getInstance();
		try {
			cal1.setTime(StrToDate(date));
			cal2.setTime(StrToDate(date1));
			return cal1.compareTo(cal2);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	/*
	 * 计算时间差
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 * @throws Exception
	 */
	public static String getTimeToRub(String date1, String date2) {
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		DecimalFormat df = new DecimalFormat("#.##");
		double result = 0l;
		try {
			Date start = sdf.parse(date1);
			Date end = sdf.parse(date2);
			long cha = end.getTime() - start.getTime();
			result = cha * 1.0 / (1000 * 60 * 60);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return df.format(result);
	}


}
