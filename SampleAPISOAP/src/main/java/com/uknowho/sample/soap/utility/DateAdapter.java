package com.uknowho.sample.soap.utility;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.uknowho.sample.soap.config.Configuration;

public class DateAdapter extends XmlAdapter<String, Date> {

	private static final Logger logger = LoggerFactory.getLogger(DateAdapter.class);
	
	private static final SimpleDateFormat DATE_FORMAT = 
			new SimpleDateFormat(Configuration.DATE_FORMAT_STRING);
	
	private static final SimpleDateFormat TIME_FORMAT = 
			new SimpleDateFormat(Configuration.TIME_FORMAT_STRING);
	
	private static final SimpleDateFormat DATETIME_FORMAT = 
			new SimpleDateFormat(Configuration.DATETIME_FORMAT_STRING);

	public static Date parseDate(final String s) {
		return parseDateFormat(s, DATE_FORMAT);
	}
	 
	public static Date parseTime(final String s) {
		return parseDateFormat(s, TIME_FORMAT);
	}
	
	public static Date parseDateTime(final String s) {
		return parseDateFormat(s, DATETIME_FORMAT);
	}
	
	private static Date parseDateFormat(final String s, final SimpleDateFormat format) {
		Date date = null;
		try {
			if (DataFormat.isStringNonEmpty(s)) {
				synchronized (format) {
					date = format.parse(s);
		        }
			}
		} catch (ParseException e) {
			logger.error(e.toString());
		}
		return date;
	}
	
	public static String printDate(final Date d) {
		return printDateFormat(d, DATE_FORMAT);
	}
	
	public static String printTime(final Date d) {
		return printDateFormat(d, TIME_FORMAT);
	}
	 
	public static String printDateTime(final Date d) {
		return printDateFormat(d, DATETIME_FORMAT);
	}
	
	private static String printDateFormat(final Date d, final SimpleDateFormat format) {
		String dateString = null;
		try {
			if (d != null) {
				synchronized (format) {
					dateString = format.format(d);
				}
			}
		} catch (Exception e) {
			logger.error(e.toString());
		}
		return dateString;
	}
	
	public static String getShortDate() {
		return getDateFormat(Calendar.getInstance().getTime(), SimpleDateFormat.SHORT);
	}
	
	public static String getShortDate(final Date date) {
		return getDateFormat(date, SimpleDateFormat.SHORT);
	}
	
	public static String getMediumDate() {
		return getDateFormat(Calendar.getInstance().getTime(), SimpleDateFormat.MEDIUM);
	}
	
	public static String getMediumDate(final Date date) {
		return getDateFormat(date, SimpleDateFormat.MEDIUM);
	}
	
	public static String getLongDate() {
		return getDateFormat(Calendar.getInstance().getTime(), SimpleDateFormat.LONG);
	}
	
	public static String getLongDate(final Date date) {
		return getDateFormat(date, SimpleDateFormat.LONG);
	}
	
	private static String getDateFormat(final Date date, final int pattern) {
		String dateString = null;
		try {
			if (date != null) {
				Locale loc = Locale.getDefault();
				DateFormat df = SimpleDateFormat.getDateInstance(pattern, loc);
				dateString = df.format(date);
			}
		} catch (Exception e) {
			logger.error(e.toString());
		}
		return dateString;
	}
	
	public static XMLGregorianCalendar getXMLGregorianCalendar() {
		return getXMLGregorianCalendar(new Date());
	}
	
	public static XMLGregorianCalendar getXMLGregorianCalendar(final Date date) {
		XMLGregorianCalendar xmlCal = null;
		try {
			if (date != null) {
				GregorianCalendar gCal = new GregorianCalendar();
				gCal.setTime(date);
				xmlCal = DatatypeFactory.newInstance().newXMLGregorianCalendar(gCal);
			}
		} catch (Exception e) {
			logger.error(e.toString());
		}
		return xmlCal;
	}
	
	public static Date getDate() {
		return getDateFormat(DATE_FORMAT);
	}
	
	public static Date getTime() {
		return getDateFormat(TIME_FORMAT);
	}
	
	public static Date getDateTime() {
		return getDateFormat(DATETIME_FORMAT);
	}
	
	public static Date getDate(final XMLGregorianCalendar xmlGCal) {
		return xmlGCal.toGregorianCalendar().getTime();
	}

	private static Date getDateFormat(final SimpleDateFormat format) {
		Date curdatetime = null;
		try {
			String date1= format.format(new Date());
			curdatetime = format.parse(date1);
		} catch (ParseException e) {
			logger.error(e.toString());
			e.printStackTrace();
		}
		return curdatetime;
	}
	
	public static String getMonthName(final Date date) {
		return getDisplayName(date, Calendar.MONTH);
	}
	
	public static String getYearName(final Date date) {
		return getDisplayName(date, Calendar.YEAR);
	}
	
	private static String getDisplayName(final Date date, final int calendarIndex) {
		String name = null;
		try {
			if (date != null) {
				Calendar cal = Calendar.getInstance();
				cal.setTime(date);
				name = cal.getDisplayName(calendarIndex, Calendar.LONG, Locale.getDefault());
			}
		} catch (Exception e) {
			logger.error(e.toString());
		}
		return name;
	}
	
	public static Integer getDayOfWeek(final Date date) {
		return getCalendar(date, Calendar.DAY_OF_WEEK);
	}
	
	public static Integer getDayOfMonth(final Date date) {
		return getCalendar(date, Calendar.DAY_OF_MONTH);
	}
	
	public static Integer getDayOfYear(final Date date) {
		return getCalendar(date, Calendar.DAY_OF_YEAR);
	}
	
	public static Integer getMonth(final Date date) {
		return getCalendar(date, Calendar.MONTH);
	}
	
	public static Integer getYear(final Date date) {
		return getCalendar(date, Calendar.YEAR);
	}
	
	private static Integer getCalendar(final Date date, final int calendarIndex) {
		Integer calendar = null;
		try {
			if (date != null) {
				Calendar cal = Calendar.getInstance();
				cal.setTime(date);
				calendar = cal.get(calendarIndex);
			}
		} catch (Exception e) {
			logger.error(e.toString());
		}
		return calendar;
	}
	
	public static boolean isTimeAfter(Date starttime, 
			Date endtime) {
		boolean valid = false;
		try {
			if ((starttime != null) && (endtime != null) 
					&& (starttime.after(endtime))) {
				valid = true;
			}
		} catch (Exception e) {
			logger.error(e.toString());
			valid = false;
		}
		return valid;
	}
	
	public static boolean isTimeBetweenDateTimes(String format, 
			String startTime, 
			String endTime) throws ParseException {
		boolean valid = false;
		
		try {
			SimpleDateFormat parser = new SimpleDateFormat(format);
			Date dailyStart = parser.parse(startTime);
			Date dailyEnd = parser.parse(endTime);
			
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(getDateTime());
			String hours = Integer.toString(calendar.get(Calendar.HOUR_OF_DAY));
			String minutes = Integer.toString(calendar.get(Calendar.MINUTE));
			
			String formatCurrentTime = hours + ":" + minutes;
			
		    Date currentTime = parser.parse(formatCurrentTime);
		    if (currentTime.after(dailyStart) && currentTime.before(dailyEnd)) {
		    	valid = true;
		    }
		} catch (ParseException e) {
			logger.error(e.toString());
			valid = false;
		}
		return valid;
	}
	
	public static Date addHourToDate(final int hour) {
		Date date = null;
		try {
			Calendar cal = Calendar.getInstance();
			cal.setTime(DateAdapter.getDateTime());
			cal.add(Calendar.HOUR, hour);
			date = cal.getTime();
		} catch (Exception e) {
			logger.error(e.toString());
		}
		return date;
	}
	
	public static Date addMinuteToDate(final int minute) {
		Date date = null;
		try {
			Calendar cal = Calendar.getInstance();
			cal.setTime(DateAdapter.getDateTime());
			cal.add(Calendar.MINUTE, minute);
			date = cal.getTime();
		} catch (Exception e) {
			logger.error(e.toString());
		}
		return date;
	}
	
	@Override
	public Date unmarshal(String v) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String marshal(Date v) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
