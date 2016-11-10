package com.uknowho.sample.soap.constant;

/**
 * This EnumConstant class is Defines Enum Constants.
 * 
 * Created date <21-Jul-2016>
 * 
 * @version 1.0.1
 * @since 1.0.1
 * 
 * @author <a href="mailto:tony87c0086@hotmail.com"> Xiaoyu Zhang (Tony) </a>
 * 
 */

public class EnumConstant {
	
	public enum FrequencyType {
		IMMEDIATELY (100, "Immediately"),
		DAILY (200, "Daily"),
		WEEKLY (300, "Weekly"),
		FORTNIGHTLY (400, "Fortnightly"),
		MONTHLY (500, "Monthly"),
		HALFYEARLY (600, "HalfYearly"),
		YEARLY (700, "Yearly");
		
		private final int id;
		private final String value;
		FrequencyType (int id, String value) {
			this.id = id;
			this.value = value;
		}
		public int getID() {
			return id;
		}
		public String getValue() {
			return value;
		}
	}
	
	public enum WeekDays {
		
		MONDAY (100, "Monday"),
		TUESDAY (200, "Tuesday"),
		WEDNESDAY (300, "Wednesday"),
		THURSDAY (400, "Thursday"),
		FRIDAY (500, "Friday"),
		SATURDAY (600, "Saturday"),
		SUNDAY (700, "Sunday");
		
		private final int key;
		private final String value;
		
		WeekDays (int key, String value) {
			this.key = key;
			this.value = value;
		}
		
		public int getKey() {
			return key;
		}
		
		public String getValue() {
			return value;
		}
	}
	
	public enum Languages {
		
		ENGLISH (100, "English"),
		CHINESE (200, "Chinese");
		
		private final int key;
		private final String value;
		
		Languages (int key, String value) {
			this.key = key;
			this.value = value;
		}
		
		public int getKey() {
			return key;
		}
		
		public String getValue() {
			return value;
		}
	}
	
	public enum Communications {
		
		MOBILE (100, "Mobile"),
		LANDLINE (200, "Landline"),
		EMAIL (300, "Email"),
		FAX (400, "Fax");
		
		private final int key;
		private final String value;
		
		Communications (int key, String value) {
			this.key = key;
			this.value = value;
		}
		
		public int getKey() {
			return key;
		}
		
		public String getValue() {
			return value;
		}
	}
	
}
