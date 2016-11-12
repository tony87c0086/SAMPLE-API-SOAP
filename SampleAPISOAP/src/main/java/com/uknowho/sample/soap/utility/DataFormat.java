package com.uknowho.sample.soap.utility;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.uknowho.sample.soap.config.Configuration;

/**
 * This DataFormat class is Defines Default Format.
 * 
 * Created date <21-Jul-2016>
 * 
 * @version 1.0.1
 * @since 1.0.1
 * 
 * @author <a href="mailto:tony87c0086@hotmail.com"> Xiaoyu Zhang (Tony) </a>
 * 
 */

public class DataFormat {

	private static final Logger logger = LoggerFactory.getLogger(DataFormat.class);

	public static Long getLongFromString(final String stringvalue) {
		Long longvalue = null;
		if (stringvalue != null) {
			try {
				longvalue = new Long(stringvalue);
			} catch (NumberFormatException e) {
				logger.error(e.toString());
				longvalue = null;
			}
		}
		return longvalue;
	}
	
	public static Double getDoublefromString(String stringvalue) {
		Double dblvalue = 0.0;
		if (stringvalue != null) {
			try {
				dblvalue = Double.parseDouble(stringvalue);	
			} catch (NumberFormatException e) {
				logger.error(e.toString());
				dblvalue = 0.0;
			}
		}
		return dblvalue;
	}
	
	public static boolean getBooleanValue(Boolean value) {
		if (value == null) {
			return false;
		} else {
			return value.booleanValue();
		}
	}
	
	public static String getFileLongDate() {
		SimpleDateFormat df = new SimpleDateFormat(Configuration.DATETIME_FORMAT_STRING);
		String formattedValue = null;
		
		formattedValue = df.format(Calendar.getInstance().getTime());
		return formattedValue;
	}
	
	public static String getFileSizeByMB(final String fileSize) {
		String formatSize = null;
		try {
			
			Double sizeCurrency = Configuration.FILE_SIZE_CURRENCY;
			Double size = Double.parseDouble(fileSize);
			
			Double megabyteSize = (size/sizeCurrency);
			
			formatSize = megabyteSize.toString() + " " + Configuration.MEGABYTE_UNIT;
		} catch (Exception e) {
			logger.error(e.toString());
			e.printStackTrace();
		}
		return formatSize;
	}
	
	public static String getFileSizeByMB(final long fileSize) {
		String formatSize = null;
		try {
			formatSize = getFileSizeByMB(DataFormat.convertStringFromLong(fileSize));
		} catch (Exception e) {
			logger.error(e.toString());
			e.printStackTrace();
		}
		return formatSize;
	}
	
	public static String convertStringFromLong(final long longvalue) {
		String string = null;
		try {
			string = ((Long)longvalue).toString();
		} catch (NumberFormatException e) {
			logger.error(e.toString());
			string = null;
		}
		return string;
	}
	
	public static boolean isStringNonEmpty(final String input) {
		boolean valid = false;
		if ((input != null) && !(input.isEmpty())) {
			valid = true;
		}
		return valid;
	}
	
	public static boolean isStringValid(final String input) {
		boolean valid = false;
		if ((input != null) && !(input.isEmpty())) {
			try {
				Pattern stringPattern = Pattern.compile(Configuration.STRING_VALID_PATTERN);
				Matcher stringMatcher = stringPattern.matcher(input);
				
				valid = stringMatcher.matches();
			} catch (Exception e) {
				logger.error(e.toString());
			}
		}
		return valid;
	}
	
	public static boolean isStringEqualNonSensitive(final String input1, final String input2) {
		boolean valid = false;
		if ((input1 != null) && !(input2.isEmpty())) {
			if (input1.equalsIgnoreCase(input2)) {
				valid = true;
			}
		}
		return valid;
	}
	
	public static boolean isStringEqualSensitive(final String input1, final String input2) {
		boolean valid = false;
		if ((input1 != null) && !(input2.isEmpty())) {
			if (input1.equals(input2)) {
				valid = true;
			}
		}
		return valid;
	}
	
	public static <T> boolean isListValid(final List<T> inputList) {
		boolean valid = false;
		try {
			if ((inputList != null) && !(inputList.isEmpty())) {
				valid = true;
			}
		} catch (Exception e) {
			logger.error(e.toString());
			valid = false;
		}
		return valid;
	}
	
	public static boolean isEmailFormatValid(final String email) {
		boolean valid = false;
		
		try {
			if ((email != null) && !(email.isEmpty())) {
				Pattern emailPattern = Pattern.compile(Configuration.EMAIL_WITHOUT_NULL);
				Matcher emailMatcher = emailPattern.matcher(email);
				
				valid = emailMatcher.matches();
			}
		} catch (Exception e) {
			logger.error(e.toString());
			valid = false;
		}
		
		return valid;
	}
	
	public static boolean isIntegerPositiveOrZero(final Integer input) {
		boolean valid = false;
		try {
			if ((input != null) && (input >= 0)) {
				valid = true;
			}
		} catch (Exception e) {
			logger.error(e.toString());
			valid = false;
		}
		return valid;
	}
	
	public static boolean isIntegerPositive(final Integer input) {
		boolean valid = false;
		try {
			if ((input != null) && (input > 0)) {
				valid = true;
			}
		} catch (Exception e) {
			logger.error(e.toString());
			valid = false;
		}
		return valid;
	}
	
	public static boolean isIntegerGreatThan(final Integer input1, final Integer input2) {
		boolean valid = false;
		try {
			if ((input1 != null) && (input2 != null) && (input1 > input2)) {
				valid = true;
			}
		} catch (Exception e) {
			logger.error(e.toString());
			valid = false;
		}
		return valid;
	}
	
	public static boolean isLongEqual(final Long firstID, final Long secondID) {
		boolean valid = false;
		try {
			if ((firstID != null) 
					&& (secondID != null)
					&& (firstID.equals(secondID))) {
				valid = true;
			}
		} catch (Exception e) {
			logger.error(e.toString());
			valid = false;
		}
		return valid;
	}
	
}
