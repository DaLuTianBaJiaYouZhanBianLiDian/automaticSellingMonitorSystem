package com.zyuc.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateFormatUtils {
	
	/**
	 * yyyy-MM-dd HH:mm --> yyyy-MM-dd HH:m0
	 * @param time
	 * @return
	 */
	public static String substringDate(String time){
		return time.substring(0, time.length()-1)+"0";
	}
	/**
	 * yyyy-MM-dd HH:mm:ss
	 * @param date
	 * @return
	 */
	public static String formatComplex(Date date){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(date);
	}
	
	/**
	 * yyyy-MM-dd HH:mm
	 * @param date
	 * @return
	 */
	public static String formatSymbol(Date date){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		return sdf.format(date);
	}
	
	/**
	 * yyyyMMddHHmmss
	 * @param date
	 * @return
	 */
	public static String formatSimple(Date date){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		return sdf.format(date);
	}
	
	/**
	 * HH:mm
	 * @param date
	 * @return
	 */
	public static String formatMin(Date date){
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
		return sdf.format(date);
	}
	
	/**
	 * 判断日期控件选择的时间
	 * @param Period
	 * @param rangetime
	 * @param startCalendar
	 * @param endCalendar
	 * @throws ParseException
	 */
	public static void getQueryTime(String Period, String rangetime, Calendar startCalendar, Calendar endCalendar) throws ParseException {
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
	    if (Period.equals("0")) {
	      String[] times = rangetime.split("-");
	      startCalendar.setTime(sdf.parse(times[0].trim()));
	      endCalendar.setTime(sdf.parse(times[1].trim()));
	    }
	    else if (Period.equals("1")) {
	      // 今天
	      startCalendar.set(Calendar.HOUR_OF_DAY, 0);
	      startCalendar.set(Calendar.MINUTE, 0);
	      startCalendar.set(Calendar.SECOND, 0);

	      endCalendar.set(Calendar.HOUR_OF_DAY, 23);
	      endCalendar.set(Calendar.MINUTE, 59);
	      endCalendar.set(Calendar.SECOND, 59);
	    } else if (Period.equals("2")) {
	      // 昨天
	      startCalendar.add(Calendar.DATE, -1);
	      startCalendar.set(Calendar.HOUR_OF_DAY, 0);
	      startCalendar.set(Calendar.MINUTE, 0);
	      startCalendar.set(Calendar.SECOND, 0);

	      endCalendar.add(Calendar.DATE, -1);
	      endCalendar.set(Calendar.HOUR_OF_DAY, 23);
	      endCalendar.set(Calendar.MINUTE, 59);
	      endCalendar.set(Calendar.SECOND, 59);
	    } else if (Period.equals("3")) {
	      // 本周
	      startCalendar.add(Calendar.DATE, 1 - startCalendar.get(Calendar.DAY_OF_WEEK));
	      startCalendar.set(Calendar.HOUR_OF_DAY, 0);
	      startCalendar.set(Calendar.MINUTE, 0);
	      startCalendar.set(Calendar.SECOND, 0);

	      endCalendar.set(Calendar.HOUR_OF_DAY, 23);
	      endCalendar.set(Calendar.MINUTE, 59);
	      endCalendar.set(Calendar.SECOND, 59);
	    } else if (Period.equals("4")) {
	      // 本月
	      startCalendar.add(Calendar.DATE, 1 - startCalendar.get(Calendar.DAY_OF_MONTH));
	      startCalendar.set(Calendar.HOUR_OF_DAY, 0);
	      startCalendar.set(Calendar.MINUTE, 0);
	      startCalendar.set(Calendar.SECOND, 0);

	      endCalendar.set(Calendar.HOUR_OF_DAY, 23);
	      endCalendar.set(Calendar.MINUTE, 59);
	      endCalendar.set(Calendar.SECOND, 59);
	    } else if (Period.equals("5")) {
	      // 本年
	      startCalendar.add(Calendar.DATE, 1 - startCalendar.get(Calendar.DAY_OF_YEAR));
	      startCalendar.set(Calendar.HOUR_OF_DAY, 0);
	      startCalendar.set(Calendar.MINUTE, 0);
	      startCalendar.set(Calendar.SECOND, 0);

	      endCalendar.set(Calendar.HOUR_OF_DAY, 23);
	      endCalendar.set(Calendar.MINUTE, 59);
	      endCalendar.set(Calendar.SECOND, 59);
	    }
	}
	
	/**
	 * 将字符串的日期('20160102121212')转换格式  yyyy-MM-dd HH:mm:ss
	 * @param max_time
	 * @return
	 */
	public static String getFormatDate(Object max_time) {
		String d = String.valueOf(max_time);
		if(d.length() >= 14){
			return d.substring(0, 4)+"-"+d.substring(4, 6)+"-"+d.substring(6,8)+ " "+
				d.substring(8,10)+":"+d.substring(10,12)+":"+d.substring(12,14);
		}else {
			return d;
		}
	}
	/**
	 * 将时长(毫秒)转换为  时:分:秒
	 * @param ms
	 * @return
	 */
	public static String formatDuration(Object ms) {  
		Long seconds = Long.parseLong(ms+"")/1000;
		Long minutes = 0L;
		Long hours = 0L;
		if(seconds == 60){
			minutes = 1L;
			seconds = 0L;
		}else if(seconds > 60){
			minutes = seconds/60;
			seconds = seconds%60;
			if(minutes == 60){
				hours = 1L;
				minutes = 0L;
			}else if(minutes > 60){
				hours = minutes/60;
				minutes = minutes%60;
				
			}
		}
		String result= (hours >0 ?(hours + ":"):("0:"))
				+ (minutes >0 ?(minutes + ":"):("0:"))
				+ (seconds >0 ?(seconds):("0"));
		return result;
	} 
	/**
	 * 字符串yyyy-MM-dd HH:mm 转换为 Date
	 */
	public static Date parseString2Date(String time) { 
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Calendar calendar = Calendar.getInstance();
		try {
			calendar.setTime(sdf.parse(time));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return calendar.getTime();
	}
	/**
	 * 得到当前时间   前后 dayNum天 , hourNum小时的时间
	 * @param date
	 * @param dayNum
	 * @param hourNum
	 * @return
	 */
	public static Date getOthersDay(Date date, int dayNum, int hourNum) {
		try {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			calendar.add(Calendar.DAY_OF_MONTH, dayNum);
			calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) + hourNum);
			date = calendar.getTime();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return date;
	}
	
	public static void main(String[] args) {
//		String d = "2016-04-18 17:10";
//		System.out.println(parseString2Date(d));
		System.out.println(getOthersDay(new Date(), 0, -2));
	}
}
