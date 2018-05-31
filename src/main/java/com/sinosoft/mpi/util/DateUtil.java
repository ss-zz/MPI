package com.sinosoft.mpi.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * 时间的工具类
 *
 */
public class DateUtil {

	private DateUtil() {
	}

	public static String getDatePattern() {
		return "yyyy-MM-dd";
	}

	public static String getDateTimePattern() {
		return DateUtil.getDatePattern() + " HH:mm:ss";
	}

	public static String getDate(Date aDate) {
		SimpleDateFormat df;
		String returnValue = "";

		if (aDate != null) {
			df = new SimpleDateFormat(getDatePattern());
			returnValue = df.format(aDate);
		}

		return (returnValue);
	}

	public static Date convertStringToDate(String aMask, String strDate) throws ParseException {
		SimpleDateFormat df;
		Date date;
		df = new SimpleDateFormat(aMask);
		try {
			date = df.parse(strDate);
		} catch (ParseException pe) {
			throw new ParseException(pe.getMessage(), pe.getErrorOffset());
		}
		return (date);
	}
	
	public static String getTime(Date theTime) {
		return getDateTime(getDateTimePattern(), theTime);
	}

	public static String getTimeNow() {
		return getDateTime(getDateTimePattern(), new Date());
	}

	public static Calendar getToday() throws ParseException {
		Date today = new Date();
		SimpleDateFormat df = new SimpleDateFormat(getDatePattern());
		String todayAsString = df.format(today);
		Calendar cal = new GregorianCalendar();
		cal.setTime(convertStringToDate(todayAsString));
		return cal;
	}

	public static String getDateTime(String aMask, Date aDate) {
		SimpleDateFormat df = null;
		String returnValue = "";
		if (aDate == null) {
			return null;
		} else {
			df = new SimpleDateFormat(aMask);
			returnValue = df.format(aDate);
		}
		return (returnValue);
	}

	public static String convertDateToString(Date aDate) {
		return getDateTime(getDatePattern(), aDate);
	}

	public static Date convertStringToDate(String strDate) throws ParseException {
		Date aDate = null;
		try {
			aDate = convertStringToDate(getDatePattern(), strDate);
		} catch (ParseException pe) {
			pe.printStackTrace();
			throw new ParseException(pe.getMessage(), pe.getErrorOffset());
		}
		return aDate;
	}
}
