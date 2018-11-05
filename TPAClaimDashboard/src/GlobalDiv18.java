
/*
 * Created on Jul 30, 2008
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */

/**
 * @author kkrishna
 *
 * @(#) ReportCreator.java 	0.1 	09/06/2005
 * Copyright 2004-2005 MindTree Consulting Pvt. Ltd. All Rights Reserved.
 *
 * This Software is the proprietary information of MindTree Consulting Pvt. Ltd.
 * Use is subject to Licence terms.
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Vector;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;

public class GlobalDiv18 {
	//Modified by Pijush
	static String logFileName =
		"C:\\Program Files\\MonthlyReports\\LOG\\";
	static String path =
		"C:\\Program Files\\MonthlyReports\\";
	static String reportName = "GlobalDiv18Report";
	static String output =
		"C:\\Program Files\\MonthlyReports\\ReportOutPut\\GlobalDiv18Report\\";
	//static String output =
	//"/\\livpwfdbg01\\TPair\\Preprocessing\\MonthlyReports\\ReportOutPut\\";
	static String queryInOutput =
		"C:\\Program Files\\MonthlyReports\\ReportOutPut\\MonthlyReportQueries\\";
	static String monthlyReport= "MonthlyReports\\";
	static int recordsGen;
	static String fileNameEnd;
	static String sheetName;
	static ArrayList reportHeader;
	static ArrayList tableHeader;
	static ArrayList tablefooter;

	static Calendar tempCalendar = Calendar.getInstance();
	static Connection connection;
	static transient String database;
	static transient String userName;
	static transient String password;
	static int PAGE_LIMIT;
	static int Icount = 0;
	static int rowcount = 1;
	static String reportPath = System.getProperty("user.dir");
	static final String PROPERTY_FILE_PATH = "prop";
	static FileOutputStream fout;
	static FileInputStream fin;
	static BufferedWriter queryFile;
	private static int totalRecords;
	private static String FeedDataSet;
	static String fileName;
	//static FileOutputStream queryFile;
	static int reportNo;

	static HashMap queryNames = new HashMap();
	private static String displayValuation = " ";

	static HSSFWorkbook workbook;
	static HSSFSheet sheet;
	static HSSFFont fontCaption;
	static HSSFFont fontHeader;
	static HSSFFont boldFont;
	static HSSFFont normalFont;
	static HSSFFont leftFont;
	static HSSFFont topFont;
	static HSSFCellStyle styleHeader;
	static HSSFCellStyle styleCaption;
	static HSSFCellStyle styleAccFor;
	static HSSFCellStyle styleDate;
	static HSSFCellStyle styleNormal;
	static HSSFCellStyle styleBold;
	static HSSFCellStyle styleLeft;
	static HSSFCellStyle styleTop;
	static HSSFDataFormat format;
	static HSSFCellStyle styleNum;
	static HSSFRichTextString emptyString = new HSSFRichTextString("");
	static boolean reportFlag = false;
	static String[][] globalDiv; //  = new String[4][]; 
	static int report;
	static int tpa;

	public static void globalMain() throws Exception {

		globalDiv = new String[2][4];

		globalDiv[0][0] = "CheckRegister_002";
		globalDiv[0][1] = "CheckRegister_009";
		globalDiv[0][2] = "CheckRegister_011";
		globalDiv[0][3] = "CheckRegister_060";
		globalDiv[1][0] = "LossRun_002";
		globalDiv[1][1] = "LossRun_009";
		globalDiv[1][2] = "LossRun_011";
		globalDiv[1][3] = "LossRun_060";

		for (int i = 0; i < globalDiv.length; i++) {
			for (int j = 0; j < 4; j++) {
				System.out.println("GlobalDiv:" + globalDiv[i][j]);
				report = i;
				tpa = j;
				runProcess();

			}
		}

	}
	static void mailProcessing() throws Exception {
		try {
			successMailProcessing();
		} catch (Exception e) {
			ErrorMailProcessing();
			System.err.println("Unable to send sucess mail");

		}
	}

	public static void runProcess() throws Exception {
		try {
			logFileName =
					globalDiv[report][tpa]
					+ "_"
					+ DateRoutine
						.getPrevValuationDate(DateRoutine.getCurrentDate())
						.toString()
					+ ".txt";
			fout = new FileOutputStream(logFileName);
		} catch (IOException e) {
			ErrorMailProcessing();
			System.err.println(
				"Unable to write to the"
					+ queryNames.get(new Integer(reportNo))
					+ " Log file");
			System.exit(-1);
		}
		long startTime = System.currentTimeMillis();
		populateParameters();

		ResultSet rs = exeuteReportGeneration();
		populateData(rs);
		//Modified by Pijush
		//To copy the query file in the output folder.
		String s = readQuery();
		try {
			queryFile =
				new BufferedWriter(
					new FileWriter(queryInOutput + fileNameEnd + ".txt"));
			queryFile.write(s);
			queryFile.close();
		} catch (IOException e) {
			ErrorMailProcessing();
			System.err.println("Unable to write to file");
			System.exit(-1);
		}
		long endTime = System.currentTimeMillis();
		printBanner(startTime, endTime);

		//					try {
		//						successMailProcessing();
		//					} catch (Exception e) {
		//						ErrorMailProcessing();
		//						System.err.println("Unable to send sucess mail");
		//
		//					}
			mailProcessing();
	}

	/**
	 * 
	 */
	private static int getReportNames() {
		// TODO Auto-generated method stub
		int counter = 1;

		String[] details = readFromFile("ReportNames.txt");

		try {

			for (int i = 1; i <= details.length; i++) {

				while (details[i - 1] != null) {
					System.out.println(
						"Report Name: " + i + " " + details[i - 1]);
					queryNames.put(new Integer(i), details[i - 1]);
					counter++;
					break;

				}
			}
		} catch (ArrayIndexOutOfBoundsException e) {

		}

		
		return counter;
	}

	private static void populateData(ResultSet rs) throws Exception {

		int fileId = 1;
		int columCount = 0;
		int numberOfSheets = 0;
		int zeroRecords = 0;
		int i = 0;
		String parameterName = globalDiv[report][tpa] + "Para.txt";
		String[] detials = readFromFile(parameterName);
		sheetName = detials[0].trim();
		PAGE_LIMIT = Integer.parseInt(detials[1].trim());
		workbook = new HSSFWorkbook();
		sheet = workbook.createSheet(sheetName);

		workbook.setSheetName(
			workbook.getSheetIndex(sheetName),
			sheetName + "_" + fileId);
		fontCaption = workbook.createFont();
		fontHeader = workbook.createFont();
		normalFont = workbook.createFont();
		boldFont = workbook.createFont();
		leftFont = workbook.createFont();
		topFont = workbook.createFont();
		styleHeader = workbook.createCellStyle();
		styleCaption = workbook.createCellStyle();
		styleAccFor = workbook.createCellStyle();
		styleNormal = workbook.createCellStyle();
		styleBold = workbook.createCellStyle();
		styleTop = workbook.createCellStyle();
		styleLeft = workbook.createCellStyle();
		styleDate = workbook.createCellStyle();
		styleNum = workbook.createCellStyle();

		format = workbook.createDataFormat();

		normalFont.setFontHeight((short) 9);
		normalFont.setFontName("Arial");
		styleAccFor.setFont(normalFont);

		styleAccFor.setDataFormat(
			format.getFormat(
				"_(* #,##0.00_);_(* (#,##0.00);_(* \"-\"??_);_(@_)"));

		styleDate.setDataFormat(format.getFormat("MM/dd/yyyy"));
		styleDate.setFont(normalFont);
		styleNum.setFont(normalFont);
		styleNum.setDataFormat(format.getFormat("0"));

		int rowcount = 1;
		ResultSetMetaData meta;
		int startRecordData;
		try {
			meta = rs.getMetaData();
			columCount = meta.getColumnCount();
			System.out.println(columCount);
			for (int c = 1; c <= columCount; c++) {
				String sheetName = workbook.getSheetName(0);

				sheet.setColumnWidth((short) c, (short) 3500);

			}

			rowcount = populateHeader(sheet, rowcount, columCount, columCount);

			startRecordData = rowcount;
			zeroRecords = rowcount;
			double totals[] = new double[columCount + 1];
			while (rs.next()) {
				i++;
				prepareRow(rs, sheet, columCount, rowcount);

				rowcount++;

				if (rowcount > PAGE_LIMIT) {

					System.out.println(
						"Data populated : " + fileId * PAGE_LIMIT);
					new PrintStream(fout).println(
						"Data populated : " + fileId * PAGE_LIMIT);

					populateRightBorder(
						sheet,
						startRecordData,
						columCount,
						rowcount++);

					prepareLastRow(sheet, rowcount - 2, columCount - 1);

					populateFooterDetails(sheet, rowcount);

					fileId++;
					sheet = createNewSheet(workbook, fileId, columCount);

					rowcount = 1;
					for (int c = 1; c <= columCount; c++) {

						String sheetName =
							workbook.getSheetName(
								workbook.getActiveSheetIndex());

						sheet.setColumnWidth((short) c, (short) 3500);
					}
					rowcount =
						populateHeader(sheet, rowcount, columCount, columCount);
				}
				recordsGen++;

			}
//			if(zeroRecords == rowcount){
//				HSSFRow rowZ = sheet.createRow((short) rowcount - 1);
//				// Create a cell and put a value in it.
//				HSSFCell cellZ = rowZ.createCell((short) ((short) 4));
// 				HSSFRichTextString ZeroRecord = new HSSFRichTextString("NO RECORDS FOUND FOR THE GIVEN CRITERIA");
//				cellZ.setCellValue(ZeroRecord);
//			}
			populateRightBorder(sheet, startRecordData, columCount, rowcount++);
			prepareLastRow(sheet, rowcount - 2, columCount - 1);
			populateFooterDetails(sheet, rowcount);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		closeExcel(workbook, sheet, columCount);

	}
	/**
	 *
	 * @param workbook
	 */
	/**
	 *
	 * @param workbook
	 */
	private static void closeExcel(
		HSSFWorkbook workbook,
		HSSFSheet sheet,
		int columCount) {

		try {

			fileName = globalDiv[report][tpa] + ".xls";
			System.out.println("File Name : " + fileName);
			new PrintStream(fout).println("File Name : " + fileName);
			String outPutFolder = output;
			boolean success = (new File(outPutFolder)).mkdir();

			//File file = new File(outPutFolder + File.separatorChar + fileName);
			//file = file.getAbsoluteFile();
			FileOutputStream fileOut =
				new FileOutputStream(
					outPutFolder + File.separatorChar + fileName);

			workbook.write(fileOut);
			fileOut.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static HSSFSheet createNewSheet(
		HSSFWorkbook workbook,
		int fileId,
		int columCount) {
		// TODO Auto-generated method stub

		sheet = null;
		HSSFSheet sheet = workbook.createSheet(sheetName + "_" + fileId);

		return sheet;

	}
	/**
	 *
	 * @param rs
	 * @param count
	 * @return
	 * @throws SQLException
	 */
	private static void prepareRow(
		ResultSet rs,
		HSSFSheet sheet,
		int count,
		int rowNo)
		throws ReportGenerationException, SQLException {

		StringBuffer str = new StringBuffer();
		ResultSetMetaData meta;
		HSSFRow row = null;
		HSSFCell cell = null;

		try {
			meta = rs.getMetaData();
		} catch (SQLException sQLException) {
			sQLException.printStackTrace();
			throw new ReportGenerationException(sQLException.getMessage());
		}

		for (int i = 1; i <= count; i++) {

			try {
				if (meta.getColumnType(i) == 3) {

					if (rs.wasNull()) {

						row = sheet.createRow((short) rowNo - 1);
						// Create a cell and put a value in it.
						cell = row.createCell((short) ((short) i - 1));

						cell.setCellValue(emptyString);

					} else if (meta.getColumnName(i).equals("DRAFT_NO")) {

						row = sheet.createRow((short) rowNo - 1);
						// Create a cell and put a value in it.
						cell = row.createCell((short) ((short) i - 1));

						cell.setCellValue(rs.getLong(i));
						cell.setCellStyle(styleNum);
						cell.setCellStyle(styleNum);

					} else {
						row = sheet.createRow((short) rowNo - 1);
						// Create a cell and put a value in it.
						cell = row.createCell((short) ((short) i - 1));

						cell.setCellValue(rs.getDouble(i));

						styleAccFor.setDataFormat((short) 164);

						cell.setCellStyle(styleAccFor);

					}

				} else if (meta.getColumnType(i) == java.sql.Types.DATE) {
					try {
						Date dateValue = rs.getDate(i);
						if (rs.wasNull()) {

							row = sheet.createRow((short) rowNo - 1);
							// Create a cell and put a value in it.
							cell = row.createCell((short) ((short) i - 1));

							cell.setCellValue(emptyString);

						} else {

							row = sheet.createRow((short) rowNo - 1);
							// Create a cell and put a value in it.
							cell = row.createCell((short) ((short) i - 1));

							cell.setCellValue(dateValue);
							cell.setCellStyle(styleDate);

						}

					} catch (SQLException e) {
						e.printStackTrace();
						throw new ReportGenerationException(e.getMessage());
					}
				} else if (meta.getColumnName(i).equals("LOSS_REPORTED_DT")) {

					try {

						if (rs.wasNull()) {

							row = sheet.createRow((short) rowNo - 1);
							// Create a cell and put a value in it.
							cell = row.createCell((short) ((short) i - 1));

							cell.setCellValue(emptyString);

						} else {
							Date dateValue = rs.getDate(i);
							row = sheet.createRow((short) rowNo - 1);
							// Create a cell and put a value in it.
							cell = row.createCell((short) ((short) i - 1));

							cell.setCellValue(dateValue);

							cell.setCellStyle(styleDate);

						}

					} catch (SQLException e) {
						e.printStackTrace();
						throw new ReportGenerationException(e.getMessage());
					}

				} else {
					try {

						row = sheet.createRow((short) rowNo - 1);
						// Create a cell and put a value in it.
						cell = row.createCell((short) ((short) i - 1));

						HSSFRichTextString cellValue =
							new HSSFRichTextString(
								rs.getString(i) == null
									? ""
									: rs.getString(i).trim());
						int integerValue = 0;
						//						//try {
						//
						//							integerValue =
						//								Integer.parseInt(cellValue.toString());
						//							cell.setCellValue(integerValue);
						//							normalFont.setFontHeightInPoints((short) 9);
						//							normalFont.setFontName("Arial");
						//							styleNormal.setFont(normalFont);
						//							cell.setCellStyle(styleNormal);
						//							cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);

						//} catch (NumberFormatException e) {
						cell.setCellValue(cellValue);
						normalFont.setFontHeightInPoints((short) 9);
						normalFont.setFontName("Arial");
						styleNormal.setFont(normalFont);
						cell.setCellStyle(styleNormal);
						cell.setCellType(HSSFCell.CELL_TYPE_STRING);

						//}
						//						cell.setCellValue(cellValue);
						//						normalFont.setFontHeightInPoints((short) 9);
						//						normalFont.setFontName("Arial");
						//						styleNormal.setFont(normalFont);
						//						cell.setCellStyle(styleNormal);
						//						cell.setCellType(HSSFCell.CELL_TYPE_STRING);

					} catch (SQLException e) {
						e.printStackTrace();
						throw new ReportGenerationException(e.getMessage());
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (ReportGenerationException e) {

				e.printStackTrace();
			}

		}

	}

	private static ResultSet exeuteReportGeneration()
		throws ReportGenerationException {
		ResultSet rs = null;
		getConnection();
		String query = readQuery();
		try {
			Statement stmt = connection.createStatement();
			rs = stmt.executeQuery(query);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ReportGenerationException(e.getMessage());
		}
		return rs;
	}

	private static String readQuery() throws ReportGenerationException {

		StringBuffer query = new StringBuffer();
		try {
			String queryNo = globalDiv[report][tpa] + "Query.txt";

			BufferedReader in =
				new BufferedReader(
					new FileReader(
						path
						    + monthlyReport
							+ reportName
							+ File.separatorChar
							+ globalDiv[report][tpa]
							+ File.separatorChar
							+ queryNo));
			String str;
			while ((str = in.readLine()) != null) {
				query.append(" ").append(
					str.replaceAll(
						"----",
						DateRoutine
							.getPrevValuationDate(DateRoutine.getCurrentDate())
							.toString()));
				//System.out.println(str);
			}
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
			throw new ReportGenerationException(e.getMessage());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return query.toString();
	}
	/**
	 *
	 * @param id
	 * @return
	 */
	//	private static WritableWorkbook createFile(int id) throws Exception {
	//
	//		WritableWorkbook workbook = null;
	//		BufferedWriter fileOutput = null;
	//		String fileName =
	//			fileNameEnd
	//				+ "_"
	//				+ DateRoutine
	//					.getPrevValuationDate(DateRoutine.getCurrentDate())
	//					.toString()
	//				+ ".xls";
	//		System.out.println("File Name : " + fileName);
	//		new PrintStream(fout).println("File Name : " + fileName);
	//		String outPutFolder = output + queryNames.get(new Integer(reportNo));
	//		boolean success = (new File(outPutFolder)).mkdir();
	//		File file = new File(outPutFolder + File.separatorChar + fileName);
	//		file = file.getAbsoluteFile();
	//		// c:\temp\dir\filename.txt
	//		try {
	//			workbook = Workbook.createWorkbook(file);
	//		} catch (IOException io) {
	//			io.printStackTrace();
	//			throw new ReportGenerationException(io.getMessage());
	//		}
	//		return workbook;
	//		
	//		
	//		
	//		
	//	}

	//	private static WritableCell dateCell(
	//		int c,
	//		int r,
	//		java.util.Date d,
	//		WritableCellFormat format) {
	//		if (d == null)
	//			return new Blank(c, r, format);
	//		else
	//			return new DateTime(c, r, d, format);
	//	}

	//	private static WritableCellFormat createCustomStaticFormat() {
	//
	//		WritableFont arial9font = new WritableFont(WritableFont.ARIAL, 9);
	//		jxl.write.DateFormat longDateFormat =
	//			new jxl.write.DateFormat("MM/dd/yyyy");
	//		return new WritableCellFormat(arial9font, longDateFormat);
	//	}

	private static Date crateDateFromString(String dateString) {

		tempCalendar.set(
			Integer.parseInt(dateString.substring(0, 4)),
			Integer.parseInt(dateString.substring(5, 7)) - 1,
			Integer.parseInt(dateString.substring(8, 10)),
			0,
			0,
			0);

		return tempCalendar.getTime();
	}

	private static void closeConnection() throws ReportGenerationException {
		try {
			if (!connection.isClosed()) {
				connection.close();
				System.out.println("Connection closed");
				new PrintStream(fout).println("Connection closed");
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new ReportGenerationException(e.getMessage());
		}
	}

	// Measure the elapsed time in different units
	private static double elapsedSeconds(long startCal, long endCal) {
		return (endCal - startCal) / 1000.0;
	}

	private static void printBanner(long startTime, long endTime) {
		System.out.println("");
		new PrintStream(fout).println("");
		System.out.println("Report Creation successfully completed....");
		new PrintStream(fout).println(
			"Report Creation successfully completed....");
		System.out.println("");
		new PrintStream(fout).println("");
		System.out.println("Report generation statistics:");
		new PrintStream(fout).println("Report generation statistics:");
		System.out.println("No of records found in the report : " + recordsGen);
		new PrintStream(fout).println(
			"No of records found in the report : " + recordsGen);
		System.out.println(
			"Time taken for generating report  : "
				+ (int) elapsedSeconds(startTime, endTime)
				+ " Sec");
		new PrintStream(fout).println(
			"Time taken for generating report  : "
				+ (int) elapsedSeconds(startTime, endTime)
				+ " Sec");
		System.out.println("");
		System.out.println("");
	}

	private static void getConnection() throws ReportGenerationException {
		try {
			populateDatabaseDetails();
			Class.forName("COM.ibm.db2.jdbc.app.DB2Driver");
			DriverManager.registerDriver(new COM.ibm.db2.jdbc.app.DB2Driver());
			connection =
				DriverManager.getConnection(
					"jdbc:db2:" + database,
					userName,
					password);
		} catch (ClassNotFoundException classNotFoundException) {
			classNotFoundException.printStackTrace();
			throw new ReportGenerationException(
				classNotFoundException.getMessage());
		} catch (SQLException sQLException) {
			sQLException.printStackTrace();
			throw new ReportGenerationException(sQLException.getMessage());
		}
		System.out.println("Connection Created... ");
		new PrintStream(fout).println("Connection Created... ");
	}
	private static String[] readFromFile(String fileName) {

		String[] detials = new String[40];
		BufferedReader in;
		try {

			if (reportFlag == false) {
				
				in = new BufferedReader(new FileReader(path + fileName));
				//+globalDiv[i][j] + fileName));
				reportFlag = true;

			} else {
				in =
					new BufferedReader(
						new FileReader(
							path
							    + monthlyReport
								+ reportName
								+ File.separatorChar
								+ globalDiv[report][tpa]
								+ File.separatorChar
								+ fileName));

			}

			//			BufferedReader in =
			//				new BufferedReader(new FileReader(path + queryNames.get(new Integer(reportNo))+File.separatorChar+ fileName));
			String str;
			int i = 0;

			while ((str = in.readLine()) != null) {

				detials[i] = str.trim();
				i++;
			}
			in.close();
		} catch (IOException e) {
		}
		return detials;
	}

	private static void populateParameters() {
		String parameterFileName = globalDiv[report][tpa] + "Para.txt";
		reportFlag = true;
		String[] detials = readFromFile(parameterFileName);
		System.out.println(parameterFileName);
		fileNameEnd = detials[0].trim();
		PAGE_LIMIT = Integer.parseInt(detials[1].trim());
	}

	private static void populateDatabaseDetails() {
		reportFlag = false;
		String[] detials = readFromFile("DatabaseDetails.txt");
		database = detials[0].trim();
		userName = detials[1].trim();
		password = detials[2].trim();

	}

	private static void populateHeaderDetails() {
		String headerName =
			globalDiv[report][tpa] + "Header.txt";
		String[] headerDetails = readFromFile(headerName);
		List list = Arrays.asList(headerDetails);
		ArrayList arrayList = new ArrayList(list);
		int arrayListLength = arrayList.size();
		for (int j = 0; j < arrayListLength; ++j) {
			arrayList.remove(null);
			arrayList.remove("");
		}
		tableHeader = arrayList;
	}
	private static void populateFooterDetails(HSSFSheet sheet, int rowNo) {
		reportFlag = false;
		String[] footerDetails = readFromFile("Footer.txt");
		List list = Arrays.asList(footerDetails);
		ArrayList arrayList = new ArrayList(list);
		int arrayListLength = arrayList.size();
		for (int j = 0; j < arrayListLength; ++j) {
			arrayList.remove(null);
			arrayList.remove("");
		}
		tablefooter = arrayList;
		int check = 0;

		while (check < arrayList.size()) {

			HSSFRow row = sheet.createRow((short) rowNo - 1);

			HSSFCell cell = row.createCell((short) 0);
			boldFont.setFontHeightInPoints((short) 9);
			boldFont.setFontName("Arial");
			boldFont.setColor(HSSFColor.LIGHT_ORANGE.index);
			boldFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);

			styleBold.setFont(boldFont);
			HSSFRichTextString footer =
				new HSSFRichTextString(arrayList.get(check).toString());
			cell.setCellValue(footer);
			cell.setCellStyle(styleBold);
			rowNo++;
			check++;
		}

	}

	private static int populateHeader(
		HSSFSheet sheet,
		int rowNo,
		int totalcolumn,
		int counter)
		throws Exception {
		
		populateHeaderDetails();

		fontCaption.setFontHeightInPoints((short) 9);
		fontCaption.setFontName("Arial");
		fontCaption.setColor(HSSFColor.LIGHT_ORANGE.index);
		fontCaption.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		styleHeader.setFont(fontCaption);
		String captionName = globalDiv[report][tpa] + "Caption.txt";
		String[] captionDetails = readFromFile(captionName);
		List list = Arrays.asList(captionDetails);
		ArrayList arrayList = new ArrayList(list);
		int arrayListLength = arrayList.size();
		for (int j = 0; j < arrayListLength; ++j) {
			arrayList.remove(null);
			arrayList.remove("");
		}
		int check = 0;

		while (check < arrayList.size()) {

			String captionLabelOCC = "";
			String captionLabel = arrayList.get(check).toString();
			Date valdt =
				DateRoutine.getPrevValuationDate(DateRoutine.getCurrentDate());
			String valudt = valdt.toString();
			String year = valudt.substring(0, 4);
			String month = valudt.substring(5, 7);

			int mon = Integer.parseInt(month) - 1;
			displayValuation = getMonthForInt(mon) + " " + year;

			if (check == 3) {
				captionLabelOCC =
					arrayList.get(check).toString() + " " + displayValuation;
			} else {
				captionLabelOCC = captionLabel;
			}

			HSSFRow row = sheet.createRow((short) rowNo - 1);
			// Create a cell and put a value in it.
			HSSFCell cell = row.createCell((short) 0);

			HSSFRichTextString captionH =
				new HSSFRichTextString(captionLabelOCC);
			cell.setCellValue(captionH);
			cell.setCellStyle(styleHeader);

			rowNo++;
			check++;
		}

		int column = 1;
		int cellNo = 0;
		rowNo++;
		for (Iterator i = tableHeader.iterator(); i.hasNext();) {

			if (column == 1) {
				fontHeader.setFontHeightInPoints((short) 9);
				fontHeader.setFontName("Arial");
				fontHeader.setColor(HSSFColor.BLUE.index);
				fontHeader.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
				styleCaption.setBorderBottom(HSSFCellStyle.BORDER_THIN);
				styleCaption.setBorderLeft(HSSFCellStyle.BORDER_THIN);
				styleCaption.setBorderTop(HSSFCellStyle.BORDER_THIN);

			} else if (column == totalcolumn) {
				fontHeader.setFontHeightInPoints((short) 9);
				fontHeader.setFontName("Arial");
				fontHeader.setColor(HSSFColor.BLUE.index);
				fontHeader.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
				styleCaption.setBorderBottom(HSSFCellStyle.BORDER_THIN);
				styleCaption.setBorderRight(HSSFCellStyle.BORDER_THIN);
				styleCaption.setBorderTop(HSSFCellStyle.BORDER_THIN);

			} else {
				fontHeader.setFontHeightInPoints((short) 9);
				fontHeader.setFontName("Arial");
				fontHeader.setColor(HSSFColor.BLUE.index);
				fontHeader.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
				styleCaption.setBorderBottom(HSSFCellStyle.BORDER_THIN);
				styleCaption.setBorderTop(HSSFCellStyle.BORDER_THIN);

			}

			styleCaption.setFont(fontHeader);
			HSSFRow row = sheet.createRow((short) rowNo - 1);
			// Create a cell and put a value in it.
			HSSFCell cell = row.createCell((short) cellNo);
			row.setHeight((short) 900);
			HSSFRichTextString captionS =
				new HSSFRichTextString((String) i.next());
			cell.setCellValue(captionS);
			styleCaption.setWrapText(true);
			styleCaption.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			cell.setCellStyle(styleCaption);

			column++;
			cellNo++;

		}

		rowNo++;
		return rowNo;

	}

	private static ArrayList readFile(String fileName) {
		ArrayList details = new ArrayList();
		try {
			BufferedReader in =
				new BufferedReader(
					new FileReader(
						reportPath
							+ File.separatorChar
							+ PROPERTY_FILE_PATH
							+ File.separatorChar
							+ fileName));
			String str;
			while ((str = in.readLine()) != null) {
				details.add(str.trim());
			}
			in.close();
		} catch (IOException e) {
		}
		return details;
	}

	private static void prepareLastRow(
		HSSFSheet sheet,
		int rowNo,
		int totalcolumn)
		throws ReportGenerationException {

		int column = 1;

		for (int i = 0; i <= totalcolumn; ++i) {

			HSSFRow row = sheet.createRow((short) rowNo);

			HSSFCell cell = row.createCell((short) ((short) column - 1));
			topFont.setFontHeightInPoints((short) 9);
			topFont.setFontName("Arial");
			topFont.setColor(HSSFColor.BLUE.index);
			topFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
			styleTop.setBorderTop(HSSFCellStyle.BORDER_MEDIUM);
			styleTop.setFont(boldFont);
			cell.setCellValue(emptyString);
			cell.setCellStyle(styleTop);
			column++;

		}

	}

	private static void populateRightBorder(
		HSSFSheet sheet,
		int rowNo,
		int totalcolumn,
		int endRowNo) {

		for (int i = rowNo; i < endRowNo; i++) {

			HSSFRow row = sheet.createRow((short) i - 1);
			HSSFCell cell = row.createCell((short) totalcolumn);
			leftFont.setFontHeightInPoints((short) 9);
			leftFont.setFontName("Arial");
			leftFont.setColor(HSSFColor.BLUE.index);
			leftFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
			styleLeft.setBorderLeft(HSSFCellStyle.BORDER_THIN);
			styleLeft.setFont(leftFont);
			cell.setCellValue(emptyString);
			cell.setCellStyle(styleLeft);

		}

	}

	private static void successMailProcessing() throws Exception {

		Vector to = new Vector();
		System.out.println("Sending mail...");
		Properties props = new Properties();
		props.setProperty("mail.transport.protocol", "smtp");
		props.setProperty("mail.host", "ODC01EXE");

		Session mailSession = Session.getDefaultInstance(props, null);
		mailSession.setDebug(true);

		try {

			MimeMessage msg = new MimeMessage(mailSession);

			msg.setFrom(
				new InternetAddress("Karthik.Krishnamoorthy@adc.mindtree.com"));

			msg.addRecipient(
				Message.RecipientType.TO,
				new InternetAddress("Karthik.Krishnamoorthy@adc.mindtree.com"));

			String message = "";
			int report = 0;
			if (reportName.equals("TPAINFOSUMMARY")) {
				report = 1;
			} else if (reportName.equals("TPALEVELSUMMARY")) {
				report = 2;
			} else if (reportName.equals("MonthlyLossRun")) {
				report = 3;
			} else if (reportName.equals("OregonClosedClaims")) {
				report = 4;
			} else if (
				reportName.equals("LOSSREPORTEDDATELESSTHANACCIDENTDATE")) {
				report = 5;
			} else if (
				reportName.equals(
					"LOSSREPORTEDDATEGREATERTHANORGNL_VALTN_EFF_DT")) {
				report = 6;
			} else if (reportName.equals("CheckRegisterPenaltyReport")) {
				report = 7;
			} else if (reportName.equals("PenaltyReport")) {
				report = 8;
			} else if (reportName.equals("GlobalDiv18Report")) {
				report = 8;
			}

			switch (report) {
				case 1 :
					System.out.println("TPAINFOSUMMARY");
					msg.setSubject(
						"Montlhy Report "
							+ " "
							+ queryNames.get(new Integer(reportNo))
							+ " - Test Mail");
					message =
						"<font size='2' face='Book Antiqua'> Hello ,<br><br>"
							+ "Monthly report "
							+ queryNames.get(new Integer(reportNo))
							+ " is created for"
							+ " "
							+ displayValuation
							+ " <br>Please access the report using the below link"
							+ "<br> <a href=file:C:\\Documents and Settings\\kkrishna\\Desktop\\Reports\\MontlhyReports\\ReportOutPut\\TPAINFOSUMMARY\\>"
							+ "<br>"
							+ "C:\\Documents and Settings\\kkrishna\\Desktop\\Reports\\MontlhyReports\\ReportOutPut\\TPAINFOSUMMARY</a>"
							+ "<br><br><b>Regards,<br>TPAIR-Support<br>MindTree Ltd<br>India.</b></font>";
					//+ "\\\\livpwfdbg01\\TPair\\Preprocessing\\MonthlyReports\\ReportOutPut\\TPAINFOSUMMARY</a>"

					break;
				case 2 :
					System.out.println("TPALEVELSUMMARY");
					msg.setSubject(
						"Montlhy Report "
							+ " "
							+ queryNames.get(new Integer(reportNo))
							+ " - Test Mail");
					message =
						"<font size='2' face='Book Antiqua'> Hello ,<br><br>"
							+ "Monthly report "
							+ queryNames.get(new Integer(reportNo))
							+ " is created for"
							+ " "
							+ displayValuation
							+ " <br>Please access the report using the below link"
							+ "<br> <a href=C:\\Documents and Settings\\kkrishna\\Desktop\\Reports\\MontlhyReports\\ReportOutPut\\TPALEVELSUMMARY>"
							+ "<br>"
							+ "C:\\Documents and Settings\\kkrishna\\Desktop\\Reports\\MontlhyReports\\ReportOutPut\\TPALEVELSUMMARY\\</a>"

						//+ "\\livpwfdbg01\\TPair\\Preprocessing\\MonthlyReports\\ReportOutPut\\TPALEVELSUMMARY</a>"
	+"<br><br><b>Regards,<br>TPAIR-Support<br>MindTree Ltd<br>India.</b></font>";
					break;
				case 3 :

					System.out.println("MonthlyLossRun");
					msg.setSubject(
						"Monthly Loss Run reports for"
							+ displayValuation
							+ "Valuation");
					message =
						"<font size='2' face='Book Antiqua'> Hello ,<br><br>"
							+ " Monthly Loss  Run  reports for"
							+ " "
							+ displayValuation
							+ "  valuation are available in User Reports site.<br>Please let us know if you have any issues."
							+ "<br><br><b>Regards,<br>TPAIR-Support<br>MindTree Ltd<br>India.</b></font>";
					break;

				case 4 :
					System.out.println("OregonClosedClaims");
					msg.setSubject(
						"Montlhy Report "
							+ " "
							+ queryNames.get(new Integer(reportNo))
							+ " - Test Mail");
					message =
						"<font size='2' face='Book Antiqua'> Hello ,<br><br>"
							+ "Monthly report "
							+ queryNames.get(new Integer(reportNo))
							+ " is created for"
							+ " "
							+ displayValuation
							+ " <br>Please access the report using the below link"
							+ "<br> <a href=file:C:\\Documents and Settings\\kkrishna\\Desktop\\Reports\\MontlhyReports\\ReportOutPut\\OregonClosedClaims\\>"
							+ "<br>"
							+ "C:\\Documents and Settings\\kkrishna\\Desktop\\Reports\\MontlhyReports\\ReportOutPut\\OregonClosedClaims</a>"
							+ "<br><br><b>Regards,<br>TPAIR-Support<br>MindTree Ltd<br>India.</b></font>";
					//+ "\\\\livpwfdbg01\\TPair\\Preprocessing\\MonthlyReports\\ReportOutPut\\TPAINFOSUMMARY</a>"

					break;

				case 5 :
					System.out.println("LOSSREPORTEDDATELESSTHANACCIDENTDATE");
					msg.setSubject(
						"Montlhy Report "
							+ " "
							+ queryNames.get(new Integer(reportNo))
							+ " - Test Mail");
					message =
						"<font size='2' face='Book Antiqua'> Hello ,<br><br>"
							+ "Monthly report "
							+ queryNames.get(new Integer(reportNo))
							+ " is created for"
							+ " "
							+ displayValuation
							+ " <br>Please access the report using the below link"
							+ "<br> <a href=file:C:\\Documents and Settings\\kkrishna\\Desktop\\Reports\\MontlhyReports\\ReportOutPut\\LOSSREPORTEDDATEGREATERTHANORGNL_VALTN_EFF_DT\\>"
							+ "<br>"
							+ "C:\\Documents and Settings\\kkrishna\\Desktop\\Reports\\MontlhyReports\\ReportOutPut\\LOSSREPORTEDDATEGREATERTHANORGNL_VALTN_EFF_DT</a>"
							+ "<br><br><b>Regards,<br>TPAIR-Support<br>MindTree Ltd<br>India.</b></font>";

					break;

				case 6 :
					System.out.println(
						"LOSSREPORTEDDATEGREATERTHANORGNL_VALTN_EFF_DT");
					msg.setSubject(
						"Montlhy Report "
							+ " "
							+ queryNames.get(new Integer(reportNo))
							+ " - Test Mail");
					msg.setSubject(
						"Montlhy Report "
							+ " "
							+ queryNames.get(new Integer(reportNo))
							+ " - Test Mail");
					message =
						"<font size='2' face='Book Antiqua'> Hello ,<br><br>"
							+ "Monthly report "
							+ queryNames.get(new Integer(reportNo))
							+ " is created for"
							+ " "
							+ displayValuation
							+ " <br>Please access the report using the below link"
							+ "<br> <a href=file:C:\\Documents and Settings\\kkrishna\\Desktop\\Reports\\MontlhyReports\\ReportOutPut\\LOSSREPORTEDDATELESSTHANACCIDENTDATE\\>"
							+ "<br>"
							+ "C:\\Documents and Settings\\kkrishna\\Desktop\\Reports\\MontlhyReports\\ReportOutPut\\LOSSREPORTEDDATELESSTHANACCIDENTDATE</a>"
							+ "<br><br><b>Regards,<br>TPAIR-Support<br>MindTree Ltd<br>India.</b></font>";

					break;
				case 7 :

					System.out.println("CheckRegisterPenaltyReport");
					msg.setSubject(
						"Monthly Loss Run reports for"
							+ displayValuation
							+ "Valuation");
					message =
						"<font size='2' face='Book Antiqua'> Hello ,<br><br>"
							+ "The check register "
							+ fileName
							+ " (for Minor Payment Codes 018, 066, 067 and 068) reports for the month of June at"
							+ "<a href = file:C:\\Documents and Settings\\kkrishna\\Desktop\\Reports\\MontlhyReports\\ReportOutPut\\CheckRegisterPenaltyReport\\> "
							+ "<br>"
							+ "C:\\Documents and Settings\\kkrishna\\Desktop\\Reports\\MontlhyReports\\ReportOutPut\\CheckRegisterPenaltyReport</a>"
							+ "<br><br>This report is created for June 2008 valuation.<br>Please let me know if you have any issue."
							+ "<br><br><b>Regards,<br>TPAIR-Support<br>MindTree Ltd<br>India.</b></font>";
					break;
				case 8 :

					System.out.println("PenaltyReport");
					msg.setSubject(
						"Monthly Loss Run reports for"
							+ displayValuation
							+ "Valuation");
					message =
						"<font size='2' face='Book Antiqua'> Hello ,<br><br>"
							+ "Please find the Monthly Penalty Report "
							+ fileName
							+ " at <br> <a href=file:C:\\Documents and Settings\\kkrishna\\Desktop\\Reports\\MontlhyReports\\ReportOutPut\\PenaltyReport\\>"
							+ "<br>"
							+ "C:\\Documents and Settings\\kkrishna\\Desktop\\Reports\\MontlhyReports\\ReportOutPut\\PenaltyReport</a> <br>"
							+ "<br>This report is created for June 2008 valuation.<br>Please let me know if you have any issue."
							+ "<br><br><b>Regards,<br>TPAIR-Support<br>MindTree Ltd<br>India.</b></font>";

					break;

				default :
					System.out.println("Invalid report.");
					break;
			}
			msg.setContent(message, "text/html");
			Transport.send(msg);
		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	private static void ErrorMailProcessing() throws Exception {

		Vector to = new Vector();
		System.out.println("Sending mail...");
		Properties props = new Properties();
		props.setProperty("mail.transport.protocol", "smtp");
		props.setProperty("mail.host", "ODC01EXE");

		Session mailSession = Session.getDefaultInstance(props, null);
		mailSession.setDebug(true);

		try {

			Message msg = new MimeMessage(mailSession);

			msg.setFrom(
				new InternetAddress("Karthik.Krishnamoorthy@adc.mindtree.com"));

			msg.addRecipient(
				Message.RecipientType.TO,
				new InternetAddress("Karthik.Krishnamoorthy@adc.mindtree.com"));

			String message = "Monthly report is created with Error";
			//String message = "
			msg.setSubject("Montlhy Report");
			msg.setContent(message, "text/plain");

			Transport.send(msg);
		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	static String getMonthForInt(int m) {
		String month = "invalid";
		DateFormatSymbols dfs = new DateFormatSymbols();
		String[] months = dfs.getMonths();
		if (m >= 0 && m <= 11) {
			month = months[m];
		}
		return month;
	}

}
