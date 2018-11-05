
/*
 * Created on Jul 24, 2008
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */

/**
 * @author kkrishna
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
import java.sql.*;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.*;
//Added For 23rd March Release
import java.text.ParseException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
//End Of Addition




public class DateRoutine {
	/**
	 * DateRoutine constructor comment.
	 */
	public DateRoutine() {
		super();
	}
	public static int compareDate(String parmDate1, String parmDate2) throws java.lang.Exception {
		int ccyy1 = Integer.parseInt(parmDate1.substring(0,4));
		int month1 = Integer.parseInt(parmDate1.substring(5,7));
		int day1 = Integer.parseInt(parmDate1.substring(8,10));
		
		int ccyy2 = Integer.parseInt(parmDate2.substring(0,4));
		int month2 = Integer.parseInt(parmDate2.substring(5,7));
		int day2 = Integer.parseInt(parmDate2.substring(8,10));

		if (ccyy1 > ccyy2) {
			return 1;
		} else {
			if (ccyy1 < ccyy2) {
				return -1;
			} else {
				if (month1 > month2) {
					return 1;
				} else {
					if (month1 < month2) {
						return -1;
					} else {
						if (day1 > day2) {
							return 1;
						} else {
							if (day1 < day2) {
								return -1;
							} else {
								return 0;
							}
						}
					}
				}
			}
		}
	}
	
	
	/**
	 * Added By Mindtree For 23rd March Release
	 * Creation date: (02/28/07)
	 * @return boolean
	 * @param parmDate1 java.lang.String
	 * @param parmDate2 java.lang.String
	 * @exception java.lang.Exception The exception description.
	 */
	public static boolean compareNewDate(String parmDate1, String parmDate2) throws java.lang.Exception,ParseException {
		DateFormat df = new SimpleDateFormat ("yyyy-MM-dd");
		// Get Date 1
		java.util.Date d1 =   df.parse(parmDate1);
		// Get Date 2
		java.util.Date  d2 =  df.parse(parmDate2);
		if (d1.equals(d2)){
			return true;
		}
		else if (d1.before(d2))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	//End Of Addition
	
	/**
	 * Insert the method's description here.
	 * Creation date: (7/17/02 11:17:49)
	 * @return boolean
	 * @param dt1 java.sql.Date
	 * @param Dt2 java.sql.Date
	 * @exception java.lang.Exception The exception description.
	 */
	public static boolean equalsWithNull(java.sql.Date dt1, java.sql.Date dt2) throws java.lang.Exception {
		// If both are null then they are equal
		if ((dt1 == null) && (dt2 == null))
			return true;
		// If only one is null then (bth would have returned already) 
		// they are NOT equal
		if ((dt1 == null) || (dt2 == null))
			return true;
		// or else - regular compare
		if (dt1.compareTo(dt2) == 0){
			return true;
		} else {
			return false;
		}
			
	}
	public static java.sql.Date getCurrentDate() {
		return new java.sql.Date(Calendar.getInstance().getTime().getTime());
	}
	
	/**
	* Newly Added By Mindtree For 22nd-June Release
	* Creation date: (04/06/07)
	* @return java.sql.Date
	*/
	
	public static java.sql.Date getPreviousDate()
	{
		Calendar modCal = new GregorianCalendar();
		modCal.add(Calendar.DATE, -1); 
		return new java.sql.Date(modCal.getTime().getTime());
	}
	/**
	 * returns the current timestamp
	 * Creation date: (01/29/2001 4:46:28 PM)
	 * @return java.sql.Timestamp
	 */
	public static java.sql.Timestamp getCurrentTimeStamp() {
		long t = (new java.util.Date()).getTime();
		Timestamp currentTimeStamp = new java.sql.Timestamp(t);
		return currentTimeStamp;
	}
	/**
	 * Insert the method's description here.
	 * Creation date: (10/17/01 1:57:41 PM)
	 * @return java.sql.Date
	 * @param timeStamp java.sql.Timestamp
	 */
	public static java.sql.Date getDateFromTimeStamp(Timestamp timeStamp) throws Exception{
		return getSqlDate(timeStamp.toString().substring(0,10));
	}
	/**
	 * converts String date depending upon the length to MM/DD/CCYY format
	 * For e.g. "2001-01-29" is converted to 01/29/2001
	 */
	public static String getDisplayDate(String sqlDate) {
		if (sqlDate == null) {
			return " ";
		}

		int length = sqlDate.trim().length();
		
		if (length == 0) {
			return sqlDate;
		} else {
			if (length == 6) {
				return sqlDate.substring(2,4) + "/" + sqlDate.substring(4) + "/" + sqlDate.substring(0,2);
			} else {
				if (length == 8) {
					if (sqlDate.charAt(2) == '-' || sqlDate.charAt(2) == '/') {
						return sqlDate.substring(3,5) + "/" + sqlDate.substring(6) + "/" + sqlDate.substring(0,2);
					} else {
						return sqlDate.substring(4,6) + "/" + sqlDate.substring(6) + "/" + sqlDate.substring(0,4);
					}
				} else {
					if (length == 10) {
						return sqlDate.substring(5,7) + "/" + sqlDate.substring(8,10) + "/" + sqlDate.substring(0,4);
					} else {
						return sqlDate;
					}
				}
			}
		}
		//return sqlDate.substring(5,7) + "/" + sqlDate.substring(8,10) + "/" + sqlDate.substring(0,4);
	}
	public static String getDisplayDate(java.sql.Date parmDate) {		
		if(parmDate.toString().equals("2-11-30")) {
			return "00/00/0000";
		} else {				
			String stringDate = parmDate.toString();
			return stringDate.substring(5,7) + "/" + stringDate.substring(8,10) + "/" + stringDate.substring(0,4);
		}	
	}
public static java.sql.Date getFirstDateOfMonth(java.sql.Date parmDate)
	throws Exception {
	int year = Integer.parseInt(parmDate.toString().substring(0, 4));
	int month = Integer.parseInt(parmDate.toString().substring(5, 7));

	java.util.GregorianCalendar calendar =
		new java.util.GregorianCalendar(year, month - 1, 1);
	return new java.sql.Date(calendar.getTime().getTime());
}/**
* For the given date returns the previous valuation date
* Creation date: (01/29/2001 5:02:54 PM)
* @return java.sql.Date
*/
	public static java.sql.Date getLastDateOfMonth(java.sql.Date parmDate) throws java.lang.Exception {
		int year = Integer.parseInt(parmDate.toString().substring(0,4));
		int month = Integer.parseInt(parmDate.toString().substring(5,7));
		int day = getLastDayOfMonth(year,month);
		return new java.sql.Date(new java.util.GregorianCalendar (year,(month-1),day).getTime().getTime());
	}
	public static int getLastDayOfCurrentMonth() throws Exception {
		java.util.Calendar calendar = Calendar.getInstance();
		return getLastDayOfMonth(calendar.get(calendar.YEAR),calendar.get(calendar.MONTH)+1);
	}
	public static int getLastDayOfMonth(int parmYr, int parmMth) throws java.lang.Exception {
		if ((parmMth == 9) || (parmMth == 4) || (parmMth == 6) || (parmMth == 11)) {
			return 30;
		} else {
			if (parmMth == 2) {
				if (((parmYr % 100) != 0) && ((parmYr % 4) == 0)) {
					return 29;
				} else {
					if (((parmYr % 100) == 0) && ((parmYr % 400) == 0)) {
						return 29;
					} else {
						return 28;
					}
				}
			} else {
				return 31;
			}
		}
	}
	public static int getLastDayOfMonth(java.sql.Date parmDate) throws java.lang.Exception {
		int year = Integer.parseInt(parmDate.toString().substring(0,4));
		int month = Integer.parseInt(parmDate.toString().substring(5,7));
		return getLastDayOfMonth(year,month);
	}
/**
 * This method returns the month and year
 * from the passed timestamp object in string format.
 * @return String 
 * @param timestamp Timestamp	 
 * @throws Exception
 */
public static String getMonthYearFromTimeStamp(Timestamp timestamp)
	throws Exception {
	return timestamp.toString().substring(5, 7)
		+ "/"
		+ timestamp.toString().substring(0, 4);
}
public static java.sql.Date getPrevValuationDate(java.sql.Date parmDate)
	throws Exception {

	int yy = Integer.parseInt(parmDate.toString().substring(0, 4));
	int mm = Integer.parseInt(parmDate.toString().substring(5, 7));
	int dd = Integer.parseInt(parmDate.toString().substring(8, 10));

	if (mm == 1) {
		mm = 12;
		dd = 31;
		yy--;
	} else {
		mm--;
		dd = getLastDayOfMonth(yy, (mm));
	}

	return new java.sql.Date(
		new java.util.GregorianCalendar(yy, (mm - 1), dd).getTime().getTime());
}
	/**
	 * Converts a String date (CCYYMMDD(8),YYMMDD(6) or CCYY-MM-DD(10)) into SQL Date format
	 */
	public static java.sql.Date getSqlDate(String parmString) throws java.lang.Exception {
		try 
		{
			int year = 0; int month = 0; int day = 0;
			if (parmString == null || parmString.trim().length() == 0) {
				return null;
			}
			
			if (parmString.trim().equals("00000000") == true) {
				return null;
			}

			if (parmString.trim().equals("0000-00-00") == true) {
				return null;
			}
			
			if (parmString.length() == 8){
				year = Integer.parseInt(parmString.substring(0,4));
				month = Integer.parseInt(parmString.substring(4,6));
				day = Integer.parseInt(parmString.substring(6));
				return new java.sql.Date(new java.util.GregorianCalendar (year,(month-1),day).getTime().getTime());
				//return sqlDate;
			}
			 
			if (parmString.length() == 6) {
				year = Integer.parseInt(parmString.substring(0,2));
				month = Integer.parseInt(parmString.substring(2,4));
				day = Integer.parseInt(parmString.substring(4));
				if (year < 50) {
					return new java.sql.Date(new java.util.GregorianCalendar (year+2000,month-1,day).getTime().getTime());
					//return sqlDate;
				} else {
					return new java.sql.Date(new java.util.GregorianCalendar (year+1900,month-1,day).getTime().getTime());
					//return sqlDate;
				}
			} 
			
			if (parmString.length() == 10) {
				year = Integer.parseInt(parmString.substring(0,4));
				month = Integer.parseInt(parmString.substring(5,7));
				day = Integer.parseInt(parmString.substring(8,10));
				return new java.sql.Date(new java.util.GregorianCalendar (year,(month-1),day).getTime().getTime());
				//return sqlDate;
			} else {
				return null;
			}
		} catch (NumberFormatException e) {
			return null;
		}
	}
	/**
	 * This method formats a given string date to CCYY-MM-DD Format.
	 * The input can be "mm/dd/ccyy" [10 chars] or "ccyymmdd" [8 chars]
	 * For e.g. it converts "01/29/2001" or "20000129" to 2000-01-29.
	 */
	public static String getStringDate(String parmString) throws java.lang.Exception {
		if (parmString.trim().length() == 10) {
			return parmString.substring(6,10) + "-" + parmString.substring(0,2) + "-" + parmString.substring(3,5);
			//return stringDate;
		}
		return parmString.substring(0,4) + "-" + parmString.substring(4,6) + "-" + parmString.substring(6,8);
		//return stringDate;
	}
	public static String getStringDate(java.sql.Date parmDate) {
		String stringDate = parmDate.toString();
		return stringDate.substring(0,4) + "-" + stringDate.substring(5,7) + "-" + stringDate.substring(8,10);
	}
public static boolean validateDate(String date) {

	if (date.trim().length() != 8) {
		return false;
	}

	/* modified on 01/02/2001 to consider value zeroes in benefcry_birth_dt_1/2/3/4/5 as valid data*/
	if (date.equals("00000000")) {
		return true;
	}

	/*end of modn.*/
	//Get todays date in calendar format

	GregorianCalendar calendar = new GregorianCalendar();

	//extract year,month and day from this calendar and create a calendar from this 
	//data to compare this date with the date provided for validation
	//month is Java starts from 0.Hence subtract 1 from month of date to be validated 
	//to compare and validate with proper date

	int year = calendar.get(Calendar.YEAR);
	int month = calendar.get(Calendar.MONTH);
	int day = calendar.get(Calendar.DATE);
	GregorianCalendar todaysDate = new GregorianCalendar(year, month, day);

	//get year,month and date from the string to be validated and create a
	//calendar for this data and compare this date with the current date
	//for future date.Also validate year,month and date in the process
	//for invalid date

	year = new Integer(Integer.parseInt(date.substring(0, 4))).intValue();
	month = new Integer(Integer.parseInt(date.substring(4, 6))).intValue() - 1;
	day = new Integer(Integer.parseInt(date.substring(6, 8))).intValue();
	GregorianCalendar dateInccyymmddFormat =
		new GregorianCalendar(year, month, day);

	//setLenient is set to false.This is done to ensure that the date
	//is in proper format.When setLenient is set to false and when we
	//parse date to get some value (year in this case) if the date is
	//not in proper format it will throw a IllegalArgumentException

	try {
		dateInccyymmddFormat.setLenient(false);
		int parseParm = dateInccyymmddFormat.get(dateInccyymmddFormat.YEAR);
	} catch (IllegalArgumentException e) {
		return false;
	}
	return true;
}
public static boolean validateDate(java.sql.Date parmDate) {

	int year = Integer.parseInt(parmDate.toString().substring(0, 4));
	int month = Integer.parseInt(parmDate.toString().substring(5, 7));
	int day = Integer.parseInt(parmDate.toString().substring(8, 10));

	java.util.GregorianCalendar calendar = new java.util.GregorianCalendar();
	calendar.setLenient(false);
	try {
		calendar.set(year, month, day);
	} catch (Exception e) {
		return false;
	}
	return true;
}
public static boolean validateFileDate(String fileDate) {

	int orgYy, orgMm, yy, mm, dd = 0;
	if (fileDate.length() != 10) {
		return false;
	}
	if (fileDate.equals("0000-00-00")) {
		return false;
	} else {
		try {
			orgYy = Integer.parseInt(fileDate.substring(0, 4));
			orgMm = Integer.parseInt(fileDate.substring(5, 7));
			dd = Integer.parseInt(fileDate.substring(8, 10));
			yy = orgYy - 1900;
			mm = orgMm - 1;
		} catch (NumberFormatException e) {
			return false;
		}

		java.sql.Date trydt =
			new java.sql.Date(
				new java.util.GregorianCalendar(yy, (mm - 1), dd).getTime().getTime());
		String checkDate = trydt.toString();

		int tryYy = Integer.parseInt(checkDate.substring(0, 4));
		int tryMm = Integer.parseInt(checkDate.substring(5, 7));
		int tryDd = Integer.parseInt(checkDate.substring(8, 10));

		if ((tryYy != orgYy) || (tryMm != orgMm) || (tryDd != dd)) {
			return false;
		} else {
			return true;
		}
	}

}
public static boolean validateParamDate1(String fileDate) {

	int yy, mm, dd = 0;
	if (fileDate.length() != 10) {
		return false;
	}
	if (fileDate.equals("0000-00-00")) {
		return false;
	} else {
		try {
			mm = Integer.parseInt(fileDate.substring(0, 2));
			dd = Integer.parseInt(fileDate.substring(3, 5));
			yy = Integer.parseInt(fileDate.substring(6, 10));
		} catch (Exception e) {
			return false;
		}

		java.sql.Date trydt =
			new java.sql.Date(
				new java.util.GregorianCalendar(yy, (mm - 1), dd).getTime().getTime());
		String checkDate = trydt.toString();

		int tryYy = Integer.parseInt(checkDate.substring(0, 4));
		int tryMm = Integer.parseInt(checkDate.substring(5, 7));
		int tryDd = Integer.parseInt(checkDate.substring(8, 10));

		if ((tryYy != yy) || (tryMm != mm) || (tryDd != dd)) {
			return false;
		} else {
			return true;
		}
	}

}
/**
 * Insert the method's description here.
 * Creation date: (12/12/02 5:20:34 PM)
 */
public static boolean validateParmDate2(String fileDate) {
	int orgYy, orgMm, yy, mm, dd = 0;
	if (fileDate.length() != 10) {
		return false;
	}
	if (fileDate.equals("0000-00-00")) {
		return false;
	} else {
		try {
			orgYy = Integer.parseInt(fileDate.substring(0, 4));
			orgMm = Integer.parseInt(fileDate.substring(5, 7));
			dd = Integer.parseInt(fileDate.substring(8, 10));
			yy = orgYy;
			mm = orgMm;
		} catch (NumberFormatException e) {
			return false;
		}

		java.sql.Date trydt =
			new java.sql.Date(
				new java.util.GregorianCalendar(yy, (mm - 1), dd).getTime().getTime());
		String checkDate = trydt.toString();

		int tryYy = Integer.parseInt(checkDate.substring(0, 4));
		int tryMm = Integer.parseInt(checkDate.substring(5, 7));
		int tryDd = Integer.parseInt(checkDate.substring(8, 10));

		if ((tryYy != orgYy) || (tryMm != orgMm) || (tryDd != dd)) {
			return false;
		} else {
			return true;
		}
	}
}
	private static long DAY_TO_SECS_CONVERTER = 86400000;/**
* The function returns true if the second date is one day after the first date..
 * Creation date: (12/16/04 6:36:17 AM)
 * @return boolean
 * @param paramDate1 java.sql.Date
 * @param paramDate2 java.sql.Date
 */
public static boolean compareDatesForOneDayDifference(java.sql.Date paramDate1, java.sql.Date paramDate2) {
		 long s=paramDate1.getTime();
		 long p=paramDate2.getTime();
		 // 86400000 = 24(hrs a day)*60(mins an hr)*60(secs a min)*1000(for nanosecs)
		 long r=(p-s)/DAY_TO_SECS_CONVERTER;
		 return(r==1);
	
}public static int compareTimeStamp(
	java.sql.Timestamp parmTs1,
	java.sql.Timestamp parmTs2)
	throws java.lang.Exception {

	if (parmTs1.after(parmTs2)) {
		return 1;
	} else {
		if (parmTs1.before(parmTs2)) {
			return -1;
		} else {
			return 0;
		}
	}
}/**
 * This method creats an java.sql.Date object from the date object passed 
 * which is after/ before no of Days as per the noOfDays value passed.
 */
 
public static java.sql.Date getSqlDateByNumberofDays (java.sql.Date parmDate,int noOfDays) {
	return new java.sql.Date(parmDate.getTime()+ noOfDays*24*60*60*1000);
}
/**
	 * This method validates a given string in the date format MM/dd/yyyy.
	 */
public static boolean validateCmdLineDate(String Date) throws Exception{

	String dateFormat = "MM/dd/yyyy";
	SimpleDateFormat sdf = new SimpleDateFormat(dateFormat); 
	sdf.setLenient(false);
	java.util.Date utChgPrcsDate = sdf.parse(Date);
				
	int dateFormatLength = dateFormat.length();
		 
	if ((Date.charAt(2) != '/')
		|| (Date.charAt(5) != '/')
		|| (Date.length() != dateFormatLength)
		|| (!Character.isDigit(Date.charAt(6)))
		|| (!Character.isDigit(Date.charAt(7)))
		|| (!Character.isDigit(Date.charAt(8)))
		|| (!Character.isDigit(Date.charAt(9)))
		) { 
			return true;						
		}
return false;
}
public static java.sql.Date getPrevYearCurrentDate(java.sql.Date parmDate)
		throws Exception {

		int yy = Integer.parseInt(parmDate.toString().substring(0, 4));
		int mm = Integer.parseInt(parmDate.toString().substring(5, 7));
		int dd = Integer.parseInt(parmDate.toString().substring(8, 10));

		if (mm == 1) {
			mm = 12;
			dd = 31;
			yy--;
		} else {
			mm--;
			//dd = getLastDayOfMonth(yy, (mm));
		
		}

		return new java.sql.Date(
			new java.util.GregorianCalendar((yy-1), mm, dd).getTime().getTime());
	}
}