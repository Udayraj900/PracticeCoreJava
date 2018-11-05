

/**
 * This class contains various Utility Functions which are used in TPAIR
 */
import java.math.*;
import java.util.*;
import java.util.Date;
import java.text.*;
import java.sql.*;
import java.io.*;
public class UtilityFunctions {

/**
 * UtilityFunctions constructor comment.
 */
public UtilityFunctions() {
	super();
}
/**
 * This method add two decimal values & returns addition
 * @return java.lang.String
 * @param value1 java.lang.String
 * @param value2 java.lang.String
 */
public static String addBigDecimal(String value1, String value2) {

	BigDecimal bValue1 = new BigDecimal(value1);
	BigDecimal bValue2 = new BigDecimal(value2);
	return (bValue1.add(bValue2)).toString();

}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 * @param value1 java.lang.String
 * @param value2 java.lang.String
 * @param action char
 */
public static java.lang.String calcBigDecimal(String value1,String value2, char action) {
	BigDecimal bValue1 = new BigDecimal(value1);
	BigDecimal bValue2 = new BigDecimal(value2);
	if ( action == '+'){return (bValue1.add(bValue2)).toString();}
	if ( action == '-'){return (bValue1.subtract(bValue2)).toString();}
	if ( action == '*'){return (bValue1.multiply(bValue2)).toString();}
	if ( action == '/'){return (bValue1.divide(bValue2,3,bValue2.ROUND_UP)).toString();}
	return null;

}
 /**
 * This method converts String date in MM/DD/CCYY format to CCYY-MM-DD format
 * @author	Deepak Swain
 * @param displayDate - date in MM/DD/YYYY format
 * @return date in CCYY-MM-DD format
 */
public static String changeDisplayToSQLDate(String displayDate) {
	// set the valuation dates to display format and store in this bean
		String sqlDate = displayDate.substring(6,10) + "-" + displayDate.substring(0,2) + "-" + displayDate.substring(3,5);
	return sqlDate;
}
/**
 * This method converts String date in CCYY-MM-DD format to MM/DD/CCYY format
 * @author	Deepak Swain
 * @param sqlDate - date in CCYY-MM-DD format
 * @return date in MM/DD/YYYY format
 */

public static String changeSQLToDisplayDate(String sqlDate) {
	// set the valuation dates to display format and store in this bean
		String displayDate = sqlDate.substring(5,7) + "/" + sqlDate.substring(8,10) + "/" + sqlDate.substring(0,4);
	return displayDate;
}
/**
 * Converts Java SQL Date to MM/DD/CCYY String
 * @return String
 * @param date Date
 */
public static String changeSQLToDisplayDate(java.util.Date sqlDate) {
	// set the valuation dates to display format and store in this bean
	int year = sqlDate.getYear() + 1900;
	int month = sqlDate.getMonth() + 1;
	int day = sqlDate.getDate();
		String displayDate = month + "/" + day + "/" + year;
	return displayDate;
}
/**
 * This method was created in VisualAge.
 * @return boolean
 * @param newFile java.lang.String
 */
public static boolean checkForAbortFile(String newFile) {
	File abortFile = new File(newFile);
	return abortFile.exists();
}
public static int compareDate(String date1 , String date2 )
{
	java.util.GregorianCalendar c1 = UtilityFunctions.getCalendar(date1);
	java.util.GregorianCalendar c2 = UtilityFunctions.getCalendar(date2);
	if ( c1.after(c2) )
	{
		return 1 ;
	}else if ( c1.before(c2))
	{
		return -1 ;
	}else
	{
		return 0 ;
	}
}
public static int compareDate(java.sql.Date date1 , String date2 )
{
	java.util.GregorianCalendar c1 = UtilityFunctions.getCalendar(date1);
	java.util.GregorianCalendar c2 = UtilityFunctions.getCalendar(date2);
	if ( c1.after(c2) )
	{
		return 1 ;
	}else if ( c1.before(c2))
	{
		return -1 ;
	}else
	{
		return 0 ;
	}
}
public static int compareDate(java.sql.Date date1 , java.sql.Date date2 )
{
	java.util.GregorianCalendar c1 = UtilityFunctions.getCalendar(date1);
	java.util.GregorianCalendar c2 = UtilityFunctions.getCalendar(date2);
	if ( c1.after(c2) )
	{
		return 1 ;
	}else if ( c1.before(c2))
	{
		return -1 ;
	}else
	{
		return 0 ;
	}
}
/**
 * This method was created in VisualAge.
 * @return double
 * @param t2 java.sql.Timestamp
 * @param t1 java.sql.Timestamp
 */
public static double computeInterval(Timestamp t2, Timestamp t1) {
	long sec1, sec2, nan1, nan2;
	sec2 = t2.getTime();
	sec1 = t1.getTime();
	nan2 = t2.getNanos();
	nan1 = t1.getNanos();
	return ((((double)(sec2 - sec1))/1000) + (((double)(nan2 - nan1))/1000000000));
}
/**
 * Converts Cobol Financials into a String w/ sign and decimal point.
 * @return java.lang.String
 */
public static String convertCobolFinancial(String val) {
	if (val.trim().equals("") == false)	{
	StringBuffer num = new StringBuffer(val.trim());
	boolean isNegative = false;
	String lastDigit = null;
	int lastIndex = num.length() - 1;
	char lastChar = num.charAt(lastIndex);

	switch (lastChar){
		case '{' :
			lastChar = '0';
			break;
		case 'A' :
			lastChar = '1';
			break;
		case 'B' :
			lastChar = '2';
			break;
		case 'C' :
			lastChar = '3';
			break;
		case 'D' :
			lastChar = '4';
			break;
		case 'E' :
			lastChar = '5';
			break;
		case 'F' :
			lastChar = '6';
			break;
		case 'G' :
			lastChar = '7';
			break;
		case 'H' :
			lastChar = '8';
			break;
		case 'I' :
			lastChar = '9';
			break;
		case '}' :
			lastChar = '0';
			isNegative = true;
			break;
		case 'J' :
			lastChar = '1';
			isNegative = true;
			break;
		case 'K' :
			lastChar = '2';
			isNegative = true;
			break;
		case 'L' :
			lastChar = '3';
			isNegative = true;
			break;
		case 'M' :
			lastChar = '4';
			isNegative = true;
			break;
		case 'N' :
			lastChar = '5';
			isNegative = true;
			break;
		case 'O' :
			lastChar = '6';
			isNegative = true;
			break;
		case 'P' :
			lastChar = '7';
			isNegative = true;
			break;
		case 'Q' :
			lastChar = '8';
			isNegative = true;
			break;
		case 'R' :
			lastChar = '9';
			isNegative = true;
			break;
	}

	num.setCharAt(lastIndex, lastChar);

	if (isNegative){
		num.insert(0, '-');
	}

	return num.toString();
	}
	else
	{
		return "0";
	}
}
/**
 * Insert the method's description here.
 * Creation date: (3/13/00 12:20:47 PM)
 * @return java.lang.String
 * @param dateToConv java.sql.Date
 */
public static String dateToRawDate(java.sql.Date dateToConv) {
	//This method returns date in Raw date format
	String sqlDateinString = dateToConv.toString();
	String finDate = sqlDateinString .substring(0,4)+sqlDateinString .substring(5,7)+sqlDateinString .substring(8,10);
	return finDate ;
}
/**
 *
 * @return java.lang.String
 * @param rawDate java.lang.String
 */
public static String expandStringDate(String rawDate) {
	String date = rawDate.substring(0,4) + "-" + rawDate.substring(4,6) + "-" + rawDate.substring(6,8);
	return date;
}
/**
 * Returns difference (in months) between two dates (SQL Dates)
 * @return int
 * @param startDate Date
 * @param endDate Date
 */
public static int getAging(java.util.Date startDate, java.util.Date endDate) {

	int aging = 0;
	int startYear = startDate.getYear() + 1900;
	int startMonth = startDate.getMonth();
	int endYear = endDate.getYear() + 1900;
	int endMonth = endDate.getMonth();

	if (startYear > endYear){
		aging = 0;
	} else if (startYear == endYear){
		if (startMonth >= endMonth){
			aging = 0;
		} else {
			aging = endMonth - startMonth;
		}
	} else {
		int years = 0;
		int months = 0;

		if (endMonth < startMonth){
			years = (endYear - startYear) - 1;
			months = 12 - (startMonth - endMonth);
		} else {
			years = endYear - startYear;
			months = endMonth - startMonth;
		}

		aging = (years * 12) + months;
	}

	return aging;
}
/**
 * This method was created in VisualAge.
 * @return int
 * @param con java.sql.Connection
 * @param program java.lang.String
 * @param user java.lang.String
 */
public static int getAuditTrailId(Connection con, String program, String user) throws Exception {

	// prepare the CALL statement
	CallableStatement stmt;
	String sql = "Call SPAUDITTRAILID" + "(?,?,?)";
	stmt = con.prepareCall (sql);

	// register output parameters
	stmt.registerOutParameter(3,Types.INTEGER);

	//Set the output parameters
	int auditTrlId = 0;

	stmt.setString (1, program);
	stmt.setString (2,user);
	stmt.setInt (3,auditTrlId);

	// call the stored procedure
	System.out.println ("\n  Calling SPAUDITTRAILID stored procedure");
	stmt.execute ();
	System.out.println ("\n  Returned SPAUDITTRAILID from stored procedure");

	// retrive ouput parameters
	auditTrlId = stmt.getInt(3);
	stmt.close ();

	return auditTrlId;
}
/**
 * Changes a CCYY-MM-DD String date into a Gregorian Calendar object.
 * @return GregorianCalendar
 */
public static java.util.GregorianCalendar getCalendar(String date) {

	String year = date.substring(0,4);
	String month = date.substring(5,7);
	String day = date.substring(8,10);
	java.util.GregorianCalendar calendar = new java.util.GregorianCalendar(Integer.parseInt(year), Integer.parseInt(month) - 1, Integer.parseInt(day));

	return calendar;
}
/**
 * Changes a SQL Date into a Gregorian Calendar object.
 * @return GregorianCalendar
 */
public static java.util.GregorianCalendar getCalendar(java.sql.Date date) {

	int year = date.getYear() + 1900;
	int month = date.getMonth();
	int day = date.getDay();
	java.util.GregorianCalendar calendar = new java.util.GregorianCalendar(year, month, day);

	return calendar;
}
/**
 * Insert the method's description here.
 * Creation date: (7/31/00 10:51:52 AM)
 * @return java.lang.String
 * @param param java.lang.String
 */
public static String getCaseString(String caseNo) {
String returnString = "";
caseNo = caseNo.trim();
int numzeros = 0;
int length = caseNo.trim().length();
if (length < 8)	{
	numzeros = 8 - length;
	if (numzeros == 1)
	{
		returnString = " " + caseNo;
		return returnString;
	}

	if (numzeros == 2)
	{
		returnString = "  " + caseNo;
		return returnString;
	}

	if (numzeros == 3)
	{
		returnString = "   " + caseNo;
		return returnString;
	}

	if (numzeros == 4)
	{
		returnString = "    " + caseNo;
		return returnString;
	}

	if (numzeros == 5)
	{
		returnString = "     " + caseNo;
		return returnString;
	}

	if (numzeros == 6)
	{
		returnString = "      " + caseNo;
		return returnString;
	}

	if (numzeros == 7)
	{
		returnString = "       " + caseNo;
		return returnString;
	}


}

return caseNo;
}
/**
 * Insert the method's description here.
 * Creation date: (8/16/00 3:58:03 PM)
 * @return java.lang.String
 * @param value java.util.Vector
 * @param withQuote boolean
 */
public static String getCommaSepString(Vector value, boolean withQuote) {
	String formattedStr = "";
	for (Enumeration e = value.elements() ; e.hasMoreElements() ;) {
		if (withQuote)
			formattedStr += "'" + e.nextElement() + "',";
		else
			formattedStr += e.nextElement() + ",";
	}
	formattedStr = formattedStr.substring(0,formattedStr.length()-1);
	return formattedStr;
}
/**
 * Method :
 * System : TPAIR
 * @return java.lang.String
 */
public static String getCurrentDate() {

	Calendar calendar = Calendar.getInstance();
	int month = calendar.get(calendar.MONTH)+1;
	String sMonth = String.valueOf(month);
	if ( month < 10 )sMonth= "0" + String.valueOf(month);

	int day = calendar.get(calendar.DAY_OF_MONTH);
	String sDay = String.valueOf(day);
	if ( day < 10 )sDay= "0" + String.valueOf(day);

	String currentDate = java.lang.String.valueOf(sMonth) + "/"+
						java.lang.String.valueOf(sDay)+ "/"+
						java.lang.String.valueOf(calendar.get(calendar.YEAR));

	return currentDate;



}
/**
 * This method was created in VisualAge.
 * @return java.sql.Timestamp
 */
public static java.sql.Timestamp getCurrentTimeStamp() {
	//java.util.GregorianCalendar gc = new java.util.GregorianCalendar();
	//java.sql.Timestamp ts = new java.sql.Timestamp(gc.get(java.util.Calendar.YEAR)-1900,gc.get(java.util.Calendar.MONTH),gc.get(java.util.Calendar.DATE),gc.get(java.util.Calendar.HOUR),gc.get(java.util.Calendar.MINUTE),gc.get(java.util.Calendar.SECOND),gc.get(java.util.Calendar.MILLISECOND));
	long t = (new java.util.Date()).getTime();
	java.sql.Timestamp ts = new java.sql.Timestamp(t);
	return ts;
}
/**
 * This method was created in VisualAge.
 * @return java.sql.Date
 * @param strDate java.lang.String
 */
public static java.sql.Date getDateOrNull(String date) {
	try	{
	if (date.trim().equals("00000000") == true)
		return null;
	if (date.length() == 8){
		int year = Integer.parseInt(date.substring(0,4)) - 1900;
		int month = Integer.parseInt(date.substring(4,6)) - 1;
		int day = Integer.parseInt(date.substring(6,8));

		java.sql.Date sqlDate = new java.sql.Date(year, month, day);

		return sqlDate;
	} else {
		int year = Integer.parseInt(date.substring(0,4)) - 1900;
		int month = Integer.parseInt(date.substring(5,7)) - 1;
		int day = Integer.parseInt(date.substring(8,10));

		java.sql.Date sqlDate = new java.sql.Date(year, month, day);

		return sqlDate;
	}
	}catch(Exception ex)	{
			return null;
	}
}
/**
 * Insert the method's description here.
 * Creation date: (5/16/00 3:05:44 PM)
 * @return int
 * @param firstDt java.lang.String
 * @param secondDt java.lang.String
 */
public static int getDifInMonths(String firstDt, String secondDt) {
	int firstYr = new Integer(firstDt.substring(0,4)).intValue();
	int firstMo = new Integer(firstDt.substring(5,7)).intValue();

	int secYr = new Integer(secondDt.substring(0,4)).intValue();
	int secMo = new Integer(secondDt.substring(5,7)).intValue();

	int firstVal = 12*firstYr + firstMo;
	int secVal = 12*secYr + secMo;

	return (firstVal - secVal);


}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 * @param value1 java.lang.String
 */
public static String getFormatedNumber(String value1 , boolean showDecimal) {
	Double dValue = new Double(value1);
	//String pattern= "###,###,###,###.##";
	String pattern= "###,###,###,###";
	if (showDecimal){
			pattern = "###,###,###,##0.00";
	}

	DecimalFormat decimalFormat = new DecimalFormat(pattern);
	return ( decimalFormat.format(dValue));

}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 * @param value java.lang.String
 */
public static String getFormattedDollar(String value) {
	Double dValue = new Double(value);
	String pattern = "###,###,###,##0.00";
	DecimalFormat decimalFormat = new DecimalFormat(pattern);
	return "$" + decimalFormat.format(dValue);
}
/**
 * Format a decimal (String representation) as a 3 space percentage.
 * @return java.lang.String
 * @param value java.lang.String
 */
public static String getFormattedPercent(String value) {
	Double dValue = new Double(value);
	String pattern = "#0.000";
	DecimalFormat decimalFormat = new DecimalFormat(pattern);
	return decimalFormat.format(dValue) + "%";
}
/**
 * System : TPAIR
 * Author   :  Sanjay M.S.
 * Creation date: (3/15/00 4:35:34 PM)
 * @return java.lang.String
 * @param value java.lang.String
 * @param pattern java.lang.String
 * Desc : This method will return formatted string depending on pattern passed
 */
public static String getFormattedString(String value, String pattern) {
	DecimalFormat decimalFormat = new DecimalFormat(pattern);
	Double dValue = new Double(value);
	value = decimalFormat.format(dValue);
	return value;
}
 /**
 * This method takes date and range as input parameters and returns a set of "last date
 * of the month". If range is negative, it returns collection of dates less than the input date.
 * If range is positive, it returns collection of dates greater than the input date.
 * Example: If you give "07/01/1999", 2 as parameters then it returns "07/31/1999"
 * and "08/31/1999". If you give "07/01/1999", -2 as parameters then it returns "06/30/1999"
 * and "05/31/1999".
 * @author	Deepak Swain
 * @param date - date in mm/dd/yyyy format
 * @param range - no of months range. This can be +ve or -ve.
 * @return Vector of string date collection in mm/dd/yyyy format
 */

public static Vector getLastDateOfMonthCollection(String date, int range) {

	//get the month, day and year part from the date
	Integer i = new Integer(date.substring(0, 2));
	int month =  i.intValue();
	i = new Integer(date.substring(3, 5));
	int day = i.intValue();
	i = new Integer(date.substring(6, 10));
	int year = i.intValue();

	//Create a calendar with the given date
	java.util.Calendar calendar = new java.util.GregorianCalendar(year,month-1,day);
	java.util.Vector dtCollection = new java.util.Vector();
	String lastDate = "";

	//If range is negative, get the collection of dates less than the input date.
	if (range < 0)
	{

		for (int j = -1; j >=range; j--)
		{
			calendar.set(java.util.Calendar.DATE,1);
			calendar.add(java.util.Calendar.DATE, -1);
			java.sql.Date sqlDate = new java.sql.Date(calendar.get(java.util.Calendar.YEAR)-1900,calendar.get(java.util.Calendar.MONTH),calendar.get(java.util.Calendar.DATE));
			//lastDate = (calendar.get(java.util.Calendar.MONTH) + 1) + "/" + calendar.get(java.util.Calendar.DATE) + "/" + calendar.get(java.util.Calendar.YEAR);
			lastDate = UtilityFunctions.changeSQLToDisplayDate(sqlDate.toString());
			dtCollection.addElement(lastDate);
		}
	}
	//If range is positive, get the collection of dates greater than the input date.
	else if (range > 0 )
	{
		for (int j = 1; j <=range; j++)
		{
			calendar.set(java.util.Calendar.DATE,1);
			calendar.set(java.util.Calendar.MONTH,month);
			calendar.add(java.util.Calendar.DATE, -1);
			java.sql.Date sqlDate = new java.sql.Date(calendar.get(java.util.Calendar.YEAR)-1900,calendar.get(java.util.Calendar.MONTH),calendar.get(java.util.Calendar.DATE));
			//lastDate = (calendar.get(java.util.Calendar.MONTH) + 1) + "/" + calendar.get(java.util.Calendar.DATE) + "/" + calendar.get(java.util.Calendar.YEAR);
			lastDate = UtilityFunctions.changeSQLToDisplayDate(sqlDate.toString());
			dtCollection.addElement(lastDate);

			if (month == 12)
			{
				month = 1;
				year += 1;
				calendar.set(java.util.Calendar.YEAR,year);
			}
			else
			{
				month += 1;

			}

		}
	}



	return dtCollection;
}
public static String getMjcString(short mjcInt) {

	String mjcString;

	short mjcNum = mjcInt;

	if ( mjcNum > 99 ){
		mjcString = String.valueOf(mjcInt);
	} else if ( mjcNum > 9 ){
		mjcString = "0" +	String.valueOf(mjcInt);
	} else {
		mjcString = "00" + String.valueOf(mjcInt);
	}

	return mjcString;
}
/**
 * This method was created in VisualAge.
 * @return java.sql.Date
 * @param valtnDt java.sql.Date
 */
public static java.sql.Date getNextValtlnDt(java.sql.Date valtnDt) {
	String valtnDtToStr = valtnDt.toString();

	int year = new Integer(valtnDtToStr.substring(0,4)).intValue();
	int month = new Integer(valtnDtToStr.substring(5,7)).intValue();
	int day  = new Integer(valtnDtToStr.substring(8,10)).intValue();

	if (month== 12)	{
		month= 01;
		year = year + 1;
		day = 31;
	}
	else
	{
	 month= month + 1;

		if ((month== 9) || (month== 4) || (month== 6) || (month== 11))	{
			day = 30;
		}else
		{
			if (month== 2){
						if ((year% 100)!= 0 && ((year % 4) == 0))
							day =  29;
						else
						if (((year%100) == 0)  && ((year% 400) == 0))
							day = 29;
						else
							day = 28;
			}
			else
			{
				day = 31;
			}
		}

}
month= month- 1;

java.sql.Date returnDate = new java.sql.Date(year-1900,month,day);
return returnDate;
}
/**
 * Insert the method's description here.
 * Creation date: (1/28/00 6:58:35 PM)
 * @return java.lang.String
 */
public static String getNoCommaNum(String value1 , boolean showDecimal) {


	Double dValue = new Double(value1);

	String pattern= "############";
	if (showDecimal){
			pattern = "###########0.00";
	}

	DecimalFormat decimalFormat = new DecimalFormat(pattern);
	return ( decimalFormat.format(dValue));

}


/**
 * This method was created in VisualAge.
 * (Value2/Value1 ) * 100
 * @return java.lang.String
 * @param value1 java.lang.String
 * @param value2 java.lang.String
 */
public static String getPercent(String value1,String value2) {
	BigDecimal bdValue1 = new java.math.BigDecimal(value1);
	BigDecimal bdValue2 = new java.math.BigDecimal(value2);

	if ((bdValue1.compareTo(new java.math.BigDecimal(0))==0) &&
		(bdValue2.compareTo(new java.math.BigDecimal(0))==0)){
		//If both the values are Zero, then return zero. [03/13/2000]
		return "0";
	}
	BigDecimal bdHundred = new java.math.BigDecimal("100.00");
	BigDecimal bdDivision = bdValue2.divide(bdValue1,6,bdValue2.ROUND_UP);
	String tValue = (bdDivision.multiply(bdHundred).setScale(4,0)).toString();

	return(tValue);



}
/**
 * This method was created in VisualAge.
 * @return java.math.BigDecimal
 */
public static BigDecimal getPercentageVariance(BigDecimal currentValue, BigDecimal avgValue) {
	BigDecimal finalValue;
	if ((currentValue.compareTo(new java.math.BigDecimal(0))==0) &&
		(avgValue.compareTo(new java.math.BigDecimal(0))==0)){
		//If both the values are Zero, then return zero. [03/13/2000]
		return new java.math.BigDecimal("0");
	}
	try{
		BigDecimal bdDiff = currentValue.subtract(avgValue);
		BigDecimal bdHundred = new java.math.BigDecimal("100.00");
		BigDecimal bdDivision = bdDiff.divide(avgValue, 4, bdDiff.ROUND_UP);
		//BigDecimal bdDivision = bdDiff.divide(currentValue, 4, bdDiff.ROUND_UP);
		finalValue= bdDivision.multiply(bdHundred).setScale(2,0);
	}catch (ArithmeticException ex){
		//If divide by zero error then return 999.99
		return new java.math.BigDecimal("999.99");
	}
	return finalValue;
}
/**
 * Insert the method's description here.
 * (val1 * 100)/val2
 * Creation date: (03/21/2000 5:03:05 PM)
 * @return java.math.BigDecimal
 * @param val1 java.math.BigDecimal
 * @param val2 java.math.BigDecimal
 */
public static BigDecimal getPercentBigDec(BigDecimal val1, BigDecimal val2) {
	BigDecimal bdFinal;
	if ((val1.compareTo(new java.math.BigDecimal(0))==0) &&
		(val2.compareTo(new java.math.BigDecimal(0))==0)){
		//If both the values are Zero, then return zero. [03/13/2000]
		return new java.math.BigDecimal("0");
	}
	try{
		BigDecimal bdHundred = new java.math.BigDecimal("100.00");
		BigDecimal bdMulti = val1.multiply(bdHundred);
		bdFinal = bdMulti.divide(val2,2,4);
	}catch (ArithmeticException ex){
		//If divide by zero error then return 999.99
		return new java.math.BigDecimal("999.99");
	}
	return bdFinal;
}
/**
 * This method returns previous months valuation date(i.e. previous months
 * last date.
 * @author	Deepak Swain
 * Creation date: (6/26/00 3:04:27 PM)
 * @param valtnDt java.sql.Date - Current valaution date (Last date of a month)
 * @return java.sql.Date - Previous month valaution date
 */
public static java.sql.Date getPrevMnthValtnDt(java.sql.Date valtnDt) {
	String valtnDtToStr = valtnDt.toString();
	int year = new Integer(valtnDtToStr.substring(0, 4)).intValue();
	int month = new Integer(valtnDtToStr.substring(5, 7)).intValue();
	int day = new Integer(valtnDtToStr.substring(8, 10)).intValue();
	if (month == 01) {
		month = 12;
		year = year - 1;
		day = 31;
	} else {
		month = month - 1;
		if ((month == 9) || (month == 4) || (month == 6) || (month == 11)) {
			day = 30;
		} else {
			if (month == 2) {
				if ((year % 100) != 0 && ((year % 4) == 0))
					day = 29;
				else
					if (((year % 100) == 0) && ((year % 400) == 0))
						day = 29;
					else
						day = 28;
			} else {
				day = 31;
			}
		}
	}
	month = month - 1;
	java.sql.Date returnDate = new java.sql.Date(year - 1900, month, day);
	return returnDate;
}
/**
 * This method was created in VisualAge.
 * @return java.sql.Date
 * @param valtnDt java.sql.Date
 */
public static java.sql.Date getPrevValtlnDt(java.sql.Date valtnDt) {
	String valtnDtToStr = valtnDt.toString();

	int year = new Integer(valtnDtToStr.substring(0,4)).intValue();
	int month = new Integer(valtnDtToStr.substring(5,7)).intValue();
	int day  = new Integer(valtnDtToStr.substring(8,10)).intValue();

		/*if (month== 01)	{
			month= 12;
			year = year - 1;
			day = 31;
		}
		else
		{
		 month= month - 1;

			if ((month== 9) || (month== 4) || (month== 6) || (month== 11))	{
				day = 30;
			}else
			{
				if (month== 2){
							if ((year% 100)!= 0 && ((year % 4) == 0))
								day =  29;
							else
							if (((year%100) == 0)  && ((year% 400) == 0))
								day = 29;
							else
								day = 28;
				}
				else
				{
					day = 31;
				}
			}

	}*/
	month= month - 1;
	day = day - 1;
	java.sql.Date returnDate = new java.sql.Date(year-1900,month,day);

	return returnDate;
	}
/**
	Converts a String date (CCYYMMDD or CCYY-MM-DD) into SQL Date format
 * @param date String
 * @return java.sql.Date
 */
public static java.sql.Date getSQLDate(String date) {

	try	{
	if (date.length() == 8){
		int year = Integer.parseInt(date.substring(0,4)) - 1900;
		int month = Integer.parseInt(date.substring(4,6)) - 1;
		int day = Integer.parseInt(date.substring(6,8));

		java.sql.Date sqlDate = new java.sql.Date(year, month, day);

		return sqlDate;
	} else {
		int year = Integer.parseInt(date.substring(0,4)) - 1900;
		int month = Integer.parseInt(date.substring(5,7)) - 1;
		int day = Integer.parseInt(date.substring(8,10));

		java.sql.Date sqlDate = new java.sql.Date(year, month, day);

		return sqlDate;
	}
	}catch(Exception ex)	{
			java.sql.Date sqlDate = new java.sql.Date(0000, 00, 00);
			return sqlDate;
	}
}
/**
	Converts a String date (CCYYMMDD) into CCYY-MM-DD String format
 * @param date String
 * @return String
 */
public static String getStringDate(String date) {

	String newDate = date.substring(0,4) + "-" + date.substring(4,6) + "-" + date.substring(6,8);
	return newDate;
}
/**
 * Returns todays date in SQL date format
 * This method was created in VisualAge.
 * @return java.sql.Date
 */
public static java.sql.Date getTodaysDate() {

	java.util.Date todayDate = new java.util.Date();
	java.sql.Date sqlDate = new java.sql.Date(todayDate.getTime());

	//java.util.Calendar calendar = java.util.Calendar.getInstance();

	//int year = calendar.get(calendar.YEAR)-1900;
	//int month = calendar.get(calendar.MONTH);
	//int day = calendar.get(calendar.DAY_OF_MONTH);

	//java.sql.Date sqlDate = new java.sql.Date(year, month, day);

	return sqlDate;
}
public static String getTpaIdString(short tpaIdInt) {

	String tpaIdString;

	short tpaNum = tpaIdInt;

	if ( tpaNum > 99 ){
		tpaIdString = String.valueOf(tpaIdInt);
	} else if ( tpaNum > 9 ){
		tpaIdString = "0" +	String.valueOf(tpaIdInt);
	} else {
		tpaIdString = "00" + String.valueOf(tpaIdInt);
	}

	return tpaIdString;
}
/**
 * This method formats the numbers, inserting the leading zeros and the sign.
 * Creation date: (5/15/00 5:29:01 PM)
 * @return java.lang.String
 * @param parmString java.lang.String
 * @param parmType char
 * @param parmLength int
 */
public static String padZeros(String parmString, char parmType, int parmLength) {

	boolean negativeNumber = false;
	negativeNumber = parmString.substring(0,1).equals("-");

	if (parmString.substring(0,1).equals("-"))
		parmString = parmString.substring(1);

	if (parmString.length() != parmLength){
		while (parmString.length() < parmLength){
			parmString = "0" +parmString;
		}

		if (parmType == 'A') {
			if (negativeNumber)
				parmString = "-" + parmString;
			else
				parmString = "+" + parmString;
		}
	}

	return parmString;

}
/**
 * Insert the method's description here.
 * Creation date: (2/10/00 10:51:44 AM)
 * Method :
 * System : TPAIR Version 1.0
 * @return java.lang.String
 * @param length int
 */
public static String replicateStringOfChars(char charType, int length) {
	char chars[] = new char[length];
	for (int i=0 ; i < length; i++){
		chars[i]=charType;
	}
	return new String(chars);
}
/**
 * Insert the method's description here.
 * Creation date: (8/29/00 4:34:18 PM)
 * @return java.util.Vector
 * @param codeString java.lang.String
 * @param delChar char
 */
public static Vector returnTokens(String codeString, char delChar) {
	Vector codeVector = new Vector();
	StringBuffer s = new StringBuffer();
	char [] charArray = codeString.toCharArray();
	for ( int cntr = 0 ; cntr < charArray.length ; cntr++){
		if ( charArray[cntr] == delChar ){
			codeVector.addElement(s);
			s = new StringBuffer();
		}else{
			s = s.append(charArray[cntr]);
		}
	}
	if ( s.length() >0 ){
		codeVector.addElement(s);
	}

	return codeVector;
}
/**
 * This method returns month value formatted into YY/MM
 * Created By : Sanjay M.S.
 * Creation date: (5/11/00 11:29:38 AM)
 * @return java.lang.String
 * @param monthValue int
 */
public static String returnYrMthBreakup(short monthValue) {
	if ( monthValue == 0 ){
		return "0 / 0 ";
	}
	if ( monthValue < 12){
		return "0 / " + String.valueOf(monthValue);
	}else{
		return String.valueOf(monthValue / 12 ) + " / "+ String.valueOf(monthValue % 12);
	}
}
/**
 Validate a CCYYMMDD String Date as a valid Date.
 * @param date String
 * @return boolean
 */
public static boolean validateDate(String date) {

	if ( date.trim().length() != 8 )
	{
		return false ;
	}

	/* modified on 01/02/2001 to consider value zeroes in benefcry_birth_dt_1/2/3/4/5 as valid data*/
	if ( date.equals("00000000"))
	{
		return true ;
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
	GregorianCalendar dateInccyymmddFormat = new GregorianCalendar(year, month, day);

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
/**
 Validate a SQL Date as a valid Date.
 * @param date java.sql.Date
 * @return boolean
 */
public static boolean validateDate(java.sql.Date date) {

	int year = date.getYear() + 1900;
	int month = date.getMonth();
	int day = date.getDay();

	java.util.GregorianCalendar calendar = new java.util.GregorianCalendar();
	calendar.setLenient(false);
	try {
		calendar.set(year, month, day);
	} catch ( Exception e ){
		return false;
	}
	return true;
}
/**
 * This method validates the file date, which is in ccyy-mm-dd format
 * Creation date: (7/5/00 7:11:21 PM)
 * @return boolean
 * @param fileDate java.lang.String
 */

public static boolean validateFileDate(String fileDate) {

	int orgYy,orgMm,yy,mm,dd=0;
	if(fileDate.length() != 10){
		return false;
	}
	if(fileDate.equals("0000-00-00"))
	{
		return false;
	}
	else
	{
		try
		{
			orgYy=Integer.parseInt(fileDate.substring(0,4));
			orgMm=Integer.parseInt(fileDate.substring(5,7));
			dd=Integer.parseInt(fileDate.substring(8,10));
			yy=orgYy-1900;
			mm=orgMm-1;
		}
		catch(NumberFormatException e)
		{
			return false;
		}

		java.sql.Date trydt=new java.sql.Date(yy,mm,dd);
		String checkDate=trydt.toString();

		int tryYy=Integer.parseInt(checkDate.substring(0,4));
		int tryMm=Integer.parseInt(checkDate.substring(5,7));
		int tryDd=Integer.parseInt(checkDate.substring(8,10));

		if((tryYy!=orgYy)||(tryMm!=orgMm)||(tryDd!=dd))
		{
			return false;
		}
		else
		{
			return true;
		}
	}

}
/**
 * Insert the method's description here.
 * Creation date: (02/23/2001 4:54:22 PM)
 * @return java.math.BigDecimal
 */
public BigDecimal bigDecConverter() {
	return null;
}/**
 * Insert the method's description here.
 * Creation date: (02/23/2001 4:54:22 PM)
 * @return java.math.BigDecimal
 */
public static BigDecimal bigDecConverter(String number) {
	try
	{
		BigDecimal bigDec = new BigDecimal(number.trim());
		return bigDec;
	}
	catch(NumberFormatException e){
		return new BigDecimal("0.0");
	}
}/**
 * This method takes a string and creates a string (left or right justified) of a specified
 * size from it.
 * @return java.lang.String
 */
public static String bufferString(String in, int totalSize, boolean rightBuffer) {
	// Bigger than the size we want, trim it
	if (in.length() > totalSize)
		return in.substring(0, totalSize - 1);
	else
		// The exact size, return it as it is.
		if (in.length() == totalSize)
			return in;
		// Smaller
		else {
			// Spaces for padding
			StringBuffer sbSpaces = new StringBuffer("");
			for (int i = 0; i < (totalSize - in.length()); i++)
				sbSpaces.append(" ");
			// Right align the string - that's what they asked for
			if (rightBuffer == true)
				return sbSpaces.toString() + in;
			// Normal left aligned
			else
				return in + sbSpaces.toString();
		}
}/* System : TPAIR Version 1.0
*  Desc : This method will append chars to passed string. If field is to be right align , it will append
*              chars to left of passed string & vice versa. For center it will append at both ends.
 * @return java.lang.String
 * @param str java.lang.String
 * @param int
 * @param char
 */
public static String buildDataField(String str,int len, char align) {

	StringBuffer sb = new StringBuffer();
	str = str.trim();
	int remain = 0;
	if (str.length() == len) {
		return str;
	}
	else {

		if ( align == 'C'){
			if ( str.length() == 0){
				remain = len;
			}else{
				remain = ((len - str.length()) / 2);
			}
		}else{
			remain = len - str.length();
		}

		char chars[] = new char[remain];
		for (int i = 0; i < remain; i++) {
			chars[i] = ' ';
		}

		switch (align){
			case 'L' :
				sb = sb.append(str);
				sb = sb.append(new String(chars));
				break;
			case 'R' :
				sb = sb.append(new String(chars));
				sb = sb.append(str);
				break;
			case 'C' :
				if ( str.length() == 0){
					sb = sb.append(new String(chars));;
				}else{
					sb = sb.append(new String(chars));
					sb = sb.append(str);
					sb = sb.append(new String(chars));
				}

				break;
		}
			return sb.toString();
	}
}/**
 * The method returns a boolean false value if the
 * paramater passed to it has value between 1 and -1.
 * Else it returns a boolean true value.
 * @param parmDollarValue BigDecimal
 */
public static boolean checkForDollarRange(BigDecimal parmDollarValue) {

	if (parmDollarValue.doubleValue() > -1 && parmDollarValue.doubleValue() < 1) {
		return false;
	}
	return true;
}	/**
	 * This method is used to Check Integer
	 * @param paramInt String
	 * @return boolean
	 */
	public static boolean checkInteger(String paramInt) {
		boolean isInt = true;
		try {
			int intVar = Integer.parseInt(paramInt);
		} catch (NumberFormatException nF) {
			isInt = false;
		}
		return isInt;
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
 * Convert to Cobol Financial from BigDecimal
 * @author	Deepak Swain
 * @param val java.math.BigDecimal
 * @param discardFraction boolean
 * @return java.lang.String
 */
public static String convert2RawFinancial(BigDecimal val, boolean discardFraction) {
	if (val == null) return null;

	StringBuffer strVal;
	if (discardFraction)
		strVal = new StringBuffer(val.abs().toBigInteger().toString());
	else
		strVal = new StringBuffer(val.abs().unscaledValue().toString());

	int lastIndex = strVal.length() - 1;
	char lastChar = strVal.charAt(lastIndex);

	if (val.signum() == -1){ //Negative
		switch (lastChar){
			case '0' :
				lastChar = '}';
				break;
			case '1' :
				lastChar = 'J';
				break;
			case '2' :
				lastChar = 'K';
				break;
			case '3' :
				lastChar = 'L';
				break;
			case '4' :
				lastChar = 'M';
				break;
			case '5' :
				lastChar = 'N';
				break;
			case '6' :
				lastChar = 'O';
				break;
			case '7' :
				lastChar = 'P';
				break;
			case '8' :
				lastChar = 'Q';
				break;
			case '9' :
				lastChar = 'R';
				break;
		}
	}else{ //Positive or zero
		switch (lastChar){
			case '0' :
				lastChar = '{';
				break;
			case '1' :
				lastChar = 'A';
				break;
			case '2' :
				lastChar = 'B';
				break;
			case '3' :
				lastChar = 'C';
				break;
			case '4' :
				lastChar = 'D';
				break;
			case '5' :
				lastChar = 'E';
				break;
			case '6' :
				lastChar = 'F';
				break;
			case '7' :
				lastChar = 'G';
				break;
			case '8' :
				lastChar = 'H';
				break;
			case '9' :
				lastChar = 'I';
				break;
		}
	}

	strVal.setCharAt(lastIndex, lastChar);

	return strVal.toString();

}/**
* This method is used to modify the claim status.
* If clmStatus is "C" then return "CLOSED"
* If clmStatus is "O" then return "OPEN"
* @return String
* @param String clmStatus
*/

//public static String getClaimStatusDesc(String clmStatus) {
//	if (clmStatus.equals(TpairConstants.CLAIM_STATUS_O)) {
//		return TpairConstants.CLAIM_STATUS_OPEN_DESC;
//	} else
//		if (clmStatus.equals(TpairConstants.CLAIM_STATUS_C)) {
//			return TpairConstants.CLAIM_STATUS_CLOSED_DESC;
//		}
//	return null;
//}
/**
 * This method will pad '0' to passed client code string
 * @return java.lang.String
 * @param clientCd java.lang.String
 */
public static String getClientString(String clientCd) {

	// maximum length for Client Code String
	int totalClientCdLen = 9;

	// Return if client string length is equals to total length
	// or client string length is more than total length

	if ((clientCd.trim().equals(""))
		|| (clientCd.length() == totalClientCdLen)
		|| (clientCd.length() > totalClientCdLen))
		return clientCd;

	StringBuffer sb = new StringBuffer();

	// Append remaining chars to string buffer

	for (int i = 1; i <= (totalClientCdLen - clientCd.length()); i++) {
		sb.append("0");
	}

	// Append client String to padded string & return
	sb.append(clientCd);
	clientCd = sb.toString();
	sb = null;
	return clientCd;
}/**
 * Insert the method's description here.
 * Creation date: (7/31/00 10:51:52 AM)
 * @return java.lang.String
 * @param param java.lang.String
 */
public static String getFmtdAccountNo(int accountNo) {

	String parmString = String.valueOf(accountNo);
	if (parmString.length() < 4) {
		return padChars(parmString, 4, '0');
	} else {
		return parmString;
	}

//    return null;
}/**
 * Insert the method's description here.
 * Creation date: (7/31/00 10:51:52 AM)
 * @return java.lang.String
 * @param param java.lang.String
 */
public static String getFmtdAigvsBranchNo(short branchNo) {

	String parmString = String.valueOf(branchNo);
	if (parmString.length() < 3) {
		return padChars(parmString, 3, '0');
	} else {
		return parmString;
	}

//    return null;
}/**
 * Insert the method's description here.
 * Creation date: (7/31/00 10:51:52 AM)
 * @return java.lang.String
 * @param param java.lang.String
 */
public static String getFmtdAigvsClaimNo(int claimNo) {

	String parmString = String.valueOf(claimNo);
	if (parmString.length() < 6) {
		return padChars(parmString, 6, '0');
	} else {
		return parmString;
	}

//    return null;
}/**
* Format a decimal (String representation) as percentage depending on the pattern passed.
* @return java.lang.String
* @param pattern java.lang.String
* @param value java.lang.String
*/
public static String getFormattedPercent(String value, String pattern) {
	Double dValue = new Double(value);
	DecimalFormat decimalFormat = new DecimalFormat(pattern);
	return decimalFormat.format(dValue) + "%";
}/**
 * Converts the month string e.g. January to the corresponding
 * gregorian calendar month e.g. GregorianCalendar.JANUARY
 * @return int
 * @param mth java.lang.String
 */
public static int getGregMonth(String mth) {
	Integer iInt = new Integer(0);
	int nMth = 13;
	String normMonth = mth.substring(0,3).toUpperCase();

	try {
		return (iInt.parseInt(mth));
	} catch (NumberFormatException ex) {
		switch (normMonth.charAt(0)){
			case 'J':
				if (normMonth.charAt(1) == 'A')
					nMth = GregorianCalendar.JANUARY;
				else
					if (normMonth.charAt(2) == 'N')
						nMth = GregorianCalendar.JUNE;
					else
						nMth = GregorianCalendar.JULY;
				break;
			case 'F':
				nMth = GregorianCalendar.FEBRUARY;
				break;
			case 'M':
				if (normMonth.charAt(2) == 'R')
					nMth = GregorianCalendar.MARCH;
				else
					nMth = GregorianCalendar.MAY;
				break;
			case 'A':
				if (normMonth.charAt(1) == 'P')
					nMth = GregorianCalendar.APRIL;
				else
					nMth = GregorianCalendar.AUGUST;
				break;
			case 'S':
				nMth = GregorianCalendar.SEPTEMBER;
				break;
			case 'O':
				nMth = GregorianCalendar.OCTOBER;
				break;
			case 'N':
				nMth = GregorianCalendar.NOVEMBER;
				break;
			case 'D':
				nMth = GregorianCalendar.DECEMBER;
				break;
			default:
				nMth = -1;
				break;
		}
		return nMth;
	}
}/**
 * Insert the method's description here.
 * Creation date: (10/30/2002 2:03:48 PM)
 * @return java.lang.String
 * @param invoicePrefix java.lang.String
 * @param valuationDate java.sql.Date
 * @param contractNo int
 */
public static String getInvoiceNo(
	String invoicePrefix,
	java.sql.Date valuationDate,
	int contractNo) {
	return invoicePrefix
		+ valuationDate.toString().substring(5, 7)
		+ valuationDate.toString().substring(2, 4)
		+ padContractNo(contractNo);
}/**
 * Insert the method's description here.
 * Creation date: (02/27/2001 10:01:20 AM)
 * @return short
 * @param stateCd short
 */
public static short getMappedState(short stateCd) {
	short mappedState;

	switch (stateCd)
	{
		case 50:
			mappedState = 31;
			break;
		case 51:
			mappedState = 31;
			break;
		case 53:
			mappedState = 12;
			break;
		case 56:
			mappedState = 12;
			break;
		default :
			mappedState = stateCd;
	}
	return mappedState;
}/**
 * returns ((value1-value2)/value1)*100
 * @return java.math.BigDecimal
 */
public static BigDecimal getPercentVariance(BigDecimal value1, BigDecimal value2, int scale) {
	BigDecimal finalValue;
	if ((value1.compareTo(new java.math.BigDecimal(0))==0) &&
		(value2.compareTo(new java.math.BigDecimal(0))==0)){
		//If both the values are Zero, then return zero. [03/13/2000]
		return new java.math.BigDecimal("0");
	}
	try{
		BigDecimal bdDiff = value1.subtract(value2);
		BigDecimal bdHundred = new java.math.BigDecimal("100");
		BigDecimal bdMultiply = bdDiff.multiply(bdHundred);
		finalValue = bdMultiply.divide(value1, scale, BigDecimal.ROUND_HALF_UP);
	}catch (ArithmeticException ex){
		//If divide by zero error then return 999.999
		return new java.math.BigDecimal("999.999");
	}
	return finalValue;
}public static String getRegionString(short region) {

	String regionString;

	//short regionNum = mjcInt;

	if ( region > 99 ){
		regionString = String.valueOf(region);
	} else if ( region > 9 ){
		regionString = "0" +	String.valueOf(region);
	} else {
		regionString = "00" + String.valueOf(region);
	}

	return regionString;
}/**
 Creates a SQL Date format from Year and Month (CCYY/YY  and MM )
 * @param Year  String
 * @param Month String
 * @return java.sql.Date Valuation Date
 */
public static java.sql.Date getValuationDate(String year , String month) {

	try	{
	if (year.length() == 2){
		int ccyy ;
		int yy = Integer.parseInt(year) ;
		if ( yy > 50 )
		{
			ccyy = yy + 1900 ;
		}else
		{
			ccyy = yy + 2000 ;
		}
		int mm = Integer.parseInt(month) ;
		int dd = UtilityFunctions.lastDateOfMonth(month,new String(year)) ;

		java.sql.Date sqlDate = new java.sql.Date(ccyy - 1900 , mm - 1 , dd);

		return sqlDate;
	} else {
		int ccyy = Integer.parseInt(year);
		int mm   = Integer.parseInt(month);
		int dd   = UtilityFunctions.lastDateOfMonth(month,year) ;

		java.sql.Date sqlDate = new java.sql.Date(ccyy - 1900 , mm - 1 , dd);

		return sqlDate;
	}
	}catch(Exception ex)	{
			java.sql.Date sqlDate = new java.sql.Date(0000, 00, 00);
			return sqlDate;
	}
}public static boolean isDateForSameMonth(java.sql.Date date1 , java.sql.Date date2 )
{
	java.util.GregorianCalendar c1 = UtilityFunctions.getCalendar(date1);
	java.util.GregorianCalendar c2 = UtilityFunctions.getCalendar(date2);
	if ( c1.get(Calendar.YEAR) == c2.get(Calendar.YEAR))
	{
		if (c1.get(Calendar.MONTH) == c2.get(Calendar.MONTH))
		return true;
	}
	return false;
}/**
 * This method will return Last Date of month when month and year is Passed .
 * @param month java.lang.String
 * @param year java.lang.String
 * @return int date
*/
public static int lastDateOfMonth(String month, String year) throws Exception{
	Integer monInt = new Integer(0);
	Integer yrInt = new Integer(0);
	try {
		monInt = new Integer(month);
		yrInt = new Integer(year);
	}catch(Exception exception )
	{
		exception.printStackTrace() ;
		throw new Exception("Paramter Value passed to lastDateOfMonth is not Proper \n" + exception ) ;
	}

	int mon = monInt.intValue();
	int yr = yrInt.intValue();
	if ((mon == 9) || (mon == 4) || (mon == 6) || (mon == 11))
		return 30;
	if (mon == 2)
		if (((yr % 100) != 0) && ((yr % 4) == 0))
			return 29;
		else
			if (((yr % 100) == 0) && ((yr % 400) == 0))
				return 29;
			else
				return 28;
	return 31;
}/**
 * return max of the two double values
 */
public static double max(double val1, double val2) {
	if (val1 > val2)
		return val1;
	else
		return val2;
}/**
 * return min of the two double values
 */
public static double min(double val1, double val2) {
	if (val1 < val2)
		return val1;
	else
		return val2;
}/**
 * returns double value multiplied by -1
 */
public static double negate(double val) {
	return (val) * (-1);
}/**
 * This method will pad chars to passed string
 * Author : Sanjay M. S.
 * Creation date: (11/30/00 2:32:14 PM)
 * @return java.lang.String
 * @param passedValue java.lang.String
 * @param length int
 * @param padChar char
 */
public static String padChars(String passedValue, int totLength, char padChar) {

	// Return if passed string length is equals to total length passed
	// or passed string length is more than total length passed

	if (( passedValue.length() == totLength) || ( passedValue.length() > totLength))
		return passedValue;

	StringBuffer sb = new StringBuffer();

	// Append remaining chars to string buffer

	for (int i=1 ; i <= (totLength - passedValue.length()) ; i++){
		sb.append(padChar);
	}
	// Append passed String to padded string & return
	sb.append(passedValue);
	passedValue = sb.toString();
	sb=null;
	return passedValue;
}/**
 * Insert the method's description here.
 * Creation date: (06/28/2002 10:59:13 AM)
 * @return java.lang.String
 * @param contractNo int
 */
public static String padContractNo(int contractNo) {
	String parmString = Integer.toString(contractNo);
	if (parmString.length() != 6){
		while (parmString.length() < 6){
			parmString = "0" +parmString;
		}
	}
	return parmString;
}public static String padStateId(int stateId) {

	if (stateId < 10) {
		return ("0" + String.valueOf(stateId));
	} else {
		return String.valueOf(stateId);
	}

}public static String padStateId(short tpaIdInt) {

	String tpaIdString;

	short tpaNum = tpaIdInt;

	if ( tpaNum > 99 ){
		tpaIdString = String.valueOf(tpaIdInt);
	} else if ( tpaNum > 9 ){
		tpaIdString = "0" +	String.valueOf(tpaIdInt);
	} else {
		tpaIdString = "00" + String.valueOf(tpaIdInt);
	}

	return tpaIdString;
}/**
 * This method is used to pad symbol number with leading zeroes.
 * @param symbolNo int
 * @return String
 */
public static String padSymbolNo(int symbolNo) {

	String symbolNoString;

	if (symbolNo > 99 ){
		symbolNoString = String.valueOf(symbolNo);
	} else if ( symbolNo > 9 ){
		symbolNoString = "0" +	String.valueOf(symbolNo);
	} else {
		symbolNoString = "00" + String.valueOf(symbolNo);
	}

	return symbolNoString;
}/**
 * This method is used to pad symbol number with leading zeroes.
 * @param symbolNo short
 * @return String
 */
public static String padSymbolNo(short symbolNo) {

	String symbolNoString;

	if (symbolNo > 99 ){
		symbolNoString = String.valueOf(symbolNo);
	} else if ( symbolNo > 9 ){
		symbolNoString = "0" +	String.valueOf(symbolNo);
	} else {
		symbolNoString = "00" + String.valueOf(symbolNo);
	}

	return symbolNoString;
}/**
  * This method takes a string and returns a string with values right of the passed token
  * for eg if we passed "IL/37", it returns 37
  * Tomcy - 09/26/2002
  */

public static String parseString(String inStr, String token) throws Exception {
	return (inStr.substring(inStr.indexOf(token) + 1));
}/**
	 * This method accept comma separated number in string format and
	 * returns double without comma.
	 * @return double
	 * @param strVal String
*/
	public static double removeComma(String strVal) {
		StringBuffer strBuf = new StringBuffer(strVal);
		while (strVal.indexOf(",") >= 0 ) {
			strBuf.deleteCharAt(strVal.indexOf(","));
			strVal=strBuf.toString();
		}
		return Double.parseDouble(strVal);
	}

/**
 * This method will construct BETWEEN Query String
 * Creation date: (12/17/01 4:17:06 PM)
 * @return java.lang.String
 * @param newIterator java.util.Iterator
 * @param columnName java.lang.String
 */
public static String returnBetweenQueryString(Iterator newIterator, String columnName,char type) {
	StringBuffer queryBuffer = new StringBuffer("");
	int cntr = 0;
	while (newIterator.hasNext()){
		queryBuffer.append(" AND ");
		queryBuffer.append(columnName);
		queryBuffer.append(" BETWEEN ");
		if ( type != 'N') queryBuffer.append("'");
		queryBuffer.append(newIterator.next().toString());
		if ( type != 'N') queryBuffer.append("'");
		queryBuffer.append(" AND ");
		if ( type != 'N') queryBuffer.append("'");
		queryBuffer.append(newIterator.next().toString());
		if ( type != 'N') queryBuffer.append("'");
	}

	return queryBuffer.toString();

}/**
 * This method will contruct IN Query String
 * Creation date: (12/17/01 4:17:06 PM)
 * @return java.lang.String
 * @param newIterator java.util.Iterator
 * @param columnName java.lang.String
 * @param type java.lang.char
 */
public static String returnInQueryString(Iterator newIterator, String columnName,char type) {
	StringBuffer queryBuffer = new StringBuffer("");
	int cntr = 0;
	while (newIterator.hasNext()){
		cntr++;
		if ( cntr == 1)	queryBuffer.append(" AND "+columnName+" IN (");
		if ( cntr > 1 ) queryBuffer.append(",");
		if ( type == 'N'){
			queryBuffer.append(newIterator.next().toString());
		}else{
			queryBuffer.append("'"+newIterator.next().toString()+"'");
		}
	}

	if ( cntr > 0 ) 	queryBuffer.append(")") ;

	return queryBuffer.toString();

}/**
 * This method validates the file date, which is in ccyy-mm-dd format
 * Creation date: (7/5/00 7:11:21 PM)
 * @return boolean
 * @param fileDate java.lang.String
 */

public static boolean validateParamDate1(String fileDate) {

	int orgYy,orgMm,yy,mm,dd=0;
	if(fileDate.length() != 10){
		return false;
	}
	if(fileDate.equals("0000-00-00"))
	{
		return false;
	}
	else
	{
		try
		{
			orgMm=Integer.parseInt(fileDate.substring(0,2));
			dd=Integer.parseInt(fileDate.substring(3,5));
			orgYy=Integer.parseInt(fileDate.substring(6,10));
			yy=orgYy-1900;
			mm=orgMm-1;
		}
		catch(NumberFormatException e)
		{
			return false;
		}

		java.sql.Date trydt=new java.sql.Date(yy,mm,dd);
		String checkDate=trydt.toString();

		int tryYy=Integer.parseInt(checkDate.substring(0,4));
		int tryMm=Integer.parseInt(checkDate.substring(5,7));
		int tryDd=Integer.parseInt(checkDate.substring(8,10));

		if((tryYy!=orgYy)||(tryMm!=orgMm)||(tryDd!=dd))
		{
			return false;
		}
		else
		{
			return true;
		}
	}

}/**
	 * This method validates the ClientCd
	 * Client Cd can not be any space
	 * Client Cd should be Integer.
	 * @param clientCd String
	 * @return boolean
	 */
public static boolean verifyClient(String clientCd) {
	if (clientCd.trim().equalsIgnoreCase("")) {
		return false;
	} else {
		if (!checkInteger(clientCd)) {
			return false;
		} else {
			return true;
		}
	}
}
/**
 * Checks special charecters in name.
 * @param str
 * @return
 */
public static boolean isSpecialCharPresent(String str) {
	String expression = "!@#$%'^&*()~,'<>/?;:|\"[]{}+=`*";
	
	//if(str!=null)
		
	for(int i=0;i<str.length();i++){
		if(expression.indexOf(str.charAt(i),0) != -1){
		//The SubStr is not present in the allowed char set, hence is not a alpha num str.
			System.out.println("not valid");
			return true;
		}
	}
	return false;
}

public static int checkNoOfAlphaChars(String str) {
	String alphaChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	int numOfalphaChars = 0;
	for(int i=0;i<str.length();i++){
		if(alphaChars.indexOf(str.toUpperCase().charAt(i),0) != -1){
			numOfalphaChars++;
		}
	}
	return numOfalphaChars;
}

public static boolean isNumeric(String str) {
	String numChars = "0123456789";
	for(int i=0;i<str.length();i++){
		if(numChars.indexOf(str.toUpperCase().charAt(i),0) == -1){
			return false;
		}
	}
	return true;
}


}