package com.uknowho.sample.test;

import java.util.Date;

import javax.xml.datatype.XMLGregorianCalendar;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.uknowho.sample.soap.constant.EnumConstant.FrequencyType;
import com.uknowho.sample.soap.utility.DataFormat;
import com.uknowho.sample.soap.utility.DateAdapter;

import junit.framework.TestCase;

/**
 * This UtilityStandardTest class provides test cases for standard package
 * classes.
 * 
 * Created date <21-Feb-2016>
 * 	
 * @version 1.0.1
 * @since 1.0.1
 * 
 * @author <a href="mailto:tony87c0086@hotmail.com"> Xiaoyu Zhang (Tony) </a>
 * 
 */

public class ToolTest extends TestCase {
	
	private static final Logger logger = LoggerFactory.getLogger(ToolTest.class);
	
    @Before
	public void setUp() {
    	logger.trace("UtilityStandardTest loading...");
    	
		System.out.println("Long Min Value : " + Long.MIN_VALUE);
		System.out.println("Long Max Value : " + Long.MAX_VALUE);
	}
    
    /**
	 * Date format test begin
	 */
	@Test
	public void testXMLCalenderToDate() {
		Date today = new Date();
		
		XMLGregorianCalendar xmlCal = DateAdapter.getXMLGregorianCalendar(today);
		System.out.println("XMLGregorianCalendar from Date    : " + xmlCal);
		
		Date todate = DateAdapter.getDate(xmlCal);
		System.out.println("Date from XMLGregorianCalendar    : " + todate);

		assertTrue(true);
	}
	
	@Test
	public void testStringDataFormat() {
		String shortdate = DateAdapter.getShortDate();
		System.out.println("ShortDate String : " + shortdate);
		String mediumdate = DateAdapter.getMediumDate();
		System.out.println("MediumDate String : " + mediumdate);
		String longdate = DateAdapter.getLongDate();
		System.out.println("LongDate String : " + longdate);
		String filelongdate = DataFormat.getFileLongDate();
		System.out.println("LongFileDate String : " + filelongdate);
	}
	
	@Test
	public void testDateandDatetimeFormat() {
		Date d1 = DateAdapter.getDate();
		System.out.println("Date Only : " + d1);
		Date d2 = DateAdapter.getDateTime();
		System.out.println("DateTime Only : " + d2);
	}
	
	@Test
	public void testGetDateFromString() {
		String shortdate = "2013-09-21";
		Date d1 = DateAdapter.parseDate(shortdate);
		assertNotNull(d1);
		System.out.println("Converted Date : " + d1);
	}
	
	@Test
	public void testGetDoubleFromString() {
		String stringval = "568";
		Double d1 = DataFormat.getDoublefromString(stringval);
		assertTrue(d1>0);
		System.out.println("Converted Double : " + d1);
	}
	
	@Test
	public void testGetBooleanValue() {
		Boolean val = null;
		boolean boolval = DataFormat.getBooleanValue(val);
		assertTrue(!boolval);
		
		Boolean val1 = false;
		boolean boolval1 = DataFormat.getBooleanValue(val1);
		assertTrue(!boolval1);
		
		Boolean val2 = true;
		boolean boolval2 = DataFormat.getBooleanValue(val2);
		assertTrue(boolval2);
	}
	/**
	 * Date format test end
	 */
    
	/**
	 * Enum test begin
	 */
	@Test
	public void testFrequencyEnum() {
		for (FrequencyType frequency : FrequencyType.values()) {
			  System.out.println("FrequencyEnum ID is:" + frequency.getID() + 
					  "FrequencyEnum value is:" + frequency.getValue());
		}
		assertTrue(true);
	}
	/**
	 * Enum test end
	 */
}
