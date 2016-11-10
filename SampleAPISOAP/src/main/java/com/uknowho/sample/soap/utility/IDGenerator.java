package com.uknowho.sample.soap.utility;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.uknowho.sample.soap.config.Configuration;

/**
 * This IDGenerator class is Defines ID generator.
 * 
 * Created date <21-Jul-2016>
 * 
 * @version 1.0.1
 * @since 1.0.1
 * 
 * @author <a href="mailto:tony87c0086@hotmail.com"> Xiaoyu Zhang (Tony) </a>
 * 
 */

public class IDGenerator {

	private static final Logger logger = LoggerFactory.getLogger(IDGenerator.class);
	
	private static Random random = null;
	
	public IDGenerator() {
		random = new Random();
	}
	
	public Integer generateIntegerID() {
		Integer intvalue = 0;
		try {
			intvalue = ThreadLocalRandom.current().nextInt(100001, 999999);
		} catch (Exception e) {
			logger.error(e.toString());
			intvalue = 0;
		}
		return intvalue;
	}
	
	public Integer generateIntegerID(Integer min) {
		Integer intvalue = 0;
		try {
			
			if (min == null) {
				min = -2147483647;
			}
			
			intvalue = ThreadLocalRandom.current().nextInt(min, 2147483647);
		} catch (Exception e) {
			logger.error(e.toString());
			intvalue = 0;
		}
		return intvalue;
	}
	
	public Long generateLongID() {
		Long longvalue = 0L;
		try {
			String idVal = null;
			idVal = generateID(Configuration.LONG_RECORDID_LENGTH);
			longvalue =  Long.parseLong(idVal);
		} catch (Exception e) {
			logger.error(e.toString());
			longvalue = 0L;
		}
		return longvalue;
	}
	
	private String generateID(int length) {
		
		String bigString = null;
		Long timeIns = Calendar.getInstance().getTimeInMillis();
		bigString = timeIns.toString();
		int randomlength = length-(bigString.length());
		
		while (randomlength > 0) {
			int requiredLen = randomlength;
			if (randomlength > Configuration.LONG_RECORDID_LENGTH) {
				requiredLen = Configuration.LONG_RECORDID_LENGTH;
			}
			Long randomNum = createRandomNumber(requiredLen);
			bigString = randomNum.toString() + bigString;
			randomlength = randomlength - requiredLen;
		}
		return bigString;
	}
	
	private Long createRandomNumber(int length) {
		Long randomVal = 0L;
		randomVal = getRandomLong(length);
		return randomVal;
	}
	
	private Long getRandomLong(int length) {
		Long randomVal = 0L;
		
		try {
			String number = "";
			int counter = 0;
			
			while (counter++<length) {
				number+= random.nextInt(9);
			}
			randomVal = Long.parseLong(number);
			//If length is short append required digits with zero
			if (randomVal.toString().length() < length) {
				int diff = length - randomVal.toString().length();
				for (int i=0;i<diff;i++) {
					number+="0";
				}
			}
			randomVal = Long.parseLong(number);
		} catch (Exception e) {
			logger.error(e.toString());
			randomVal = 0L;
		}
		return randomVal;
	}
	
	public String decodeRecordIDValue(String recordID) {
		String decodedString = null;
		try {
			String datePart = recordID.substring(recordID.
					length()-Configuration.MILLISECONDS_DATE_LENGTH);

			Date d1 = new Date(Long.parseLong(datePart));
			DateFormat formatter = new SimpleDateFormat(Configuration.DATETIME_FORMAT_STRING);
			
			decodedString = "DateTime:"+formatter.format(d1) + "|RandomValue:" 
					+ recordID.substring(0, recordID.length()
							-Configuration.MILLISECONDS_DATE_LENGTH);
		} catch (Exception e) {
			// Do nothing
		}
		return decodedString;
	}
}
