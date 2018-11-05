import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Vector;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.hssf.util.Region;

import com.ibm.db2.jcc.DB2Driver;

public class TPAClaimDashboardReportGenerator {
	static String logFileName = "C:\\Eclipse\\workspace\\TPAClaimDashboard\\src\\LOG";
	static String path = "C:\\Eclipse\\workspace\\TPAClaimDashboard\\src\\";
	static String outputPath = "C:\\Eclipse\\workspace\\TPAClaimDashboard\\src\\ReportOutput\\";
	static String output = "";
	static String queryInOutput = "";
	static ArrayList tablefooter;
	static String monthlyReport = "TPA_Claims_Dashboard\\";
	static int Icount = 0;
	static int rowcount = 1;
	static BufferedWriter queryFile;
	private static String displayValuation = " ";
	private static String pDisplayValuation = " ";
	static int reportNo;
	static int recordsGen;
	static String fileNameEnd;
	static String fileMonth;
	static ArrayList reportHeader;
	static ArrayList tableHeader;
	static Calendar tempCalendar = Calendar.getInstance();
	static Connection connection;
	static transient String database;
	static transient String userName;
	static transient String password;
	static String fileName;
	static int PAGE_LIMIT;
	static final String PROPERTY_FILE_PATH = "prop";
	static ArrayList tpaNames;
	static ArrayList tpaIds;
	static String monthDetails;
	static String sheetName;
	static int SHEET_LIMIT;
	static String outPutFolder1;
	static String outPutFolder2;
	static String htmlName;
	static String outFileName;
	static String homePath;
	static ResultSetMetaData meta;
	static ResultSet rs = null;
	static FileOutputStream fout;
	static String reportPath = System.getProperty("user.dir");
	static String reportName;
	static String reportName1;
	static HSSFWorkbook workbook;
	static HSSFSheet sheet;
	static HSSFFont fontCaption;
	static HSSFFont fontHeader;
	static HSSFFont boldFont;
	static HSSFFont normalFont;
	static HSSFFont summaryFont;
	static HSSFFont summaryFont1;
	static HSSFFont summaryFontRatio;
	static HSSFFont tpaSummaryFont;
	static HSSFFont tpaSummaryFont1;
	static HSSFFont tpaSummaryFontRatio;
	static HSSFFont leftFont;
	static HSSFFont topFont;
	static HSSFFont lastRowNormalfont;
	static HSSFFont lastRowNumfont;
	static HSSFCellStyle styleHeader;
	static HSSFCellStyle styleCaption;
	static HSSFCellStyle styleCaption1;
	static HSSFCellStyle styleCaption2;
	static HSSFCellStyle styleAccFor;
	static HSSFCellStyle styleAccForNumbers;
	static HSSFCellStyle styleDate;
	static HSSFCellStyle styleNormal;
	static HSSFCellStyle styleNormalMerge;
	static HSSFCellStyle styleSummary;
	static HSSFCellStyle styleTPASummary;
	static HSSFCellStyle styleSummaryRatio;
	static HSSFCellStyle styleTPASummaryRatio;
	static HSSFCellStyle styleSummary1;
	static HSSFCellStyle styleTPASummary1;
	static HSSFCellStyle styleSummary2;
	static HSSFCellStyle styleTPASummary2;
	static HSSFCellStyle styleBold;
	static HSSFCellStyle styleLeft;
	static HSSFCellStyle styleTop;
	static HSSFCellStyle styleLastRowNormal;
	static HSSFCellStyle styleLastRowNum;
	static HSSFDataFormat format;
	static HSSFDataFormat format1;
	static HSSFCellStyle styleNum;
	static String monthFolderName = "";
	static HSSFRichTextString emptyString = new HSSFRichTextString("");
	static boolean reportFlag = false;

	static List filesNames;
	static String criteria = "";
	static boolean criteriaPopulated = false;
	static String allFileNames = "";
	static HashMap queryNames = new HashMap();
	static String logFile;
	static String ValuationDate = "";
	static HSSFFont fontTotal;
	static HSSFFont fontTotal2;
	static HSSFCellStyle styleTotal;
	static HSSFCellStyle styleTotal2;
	static HSSFCellStyle styleForPctge;
	static HSSFCellStyle styleColor;
	static HSSFRichTextString totals = new HSSFRichTextString("");
	static HSSFRichTextString summary = new HSSFRichTextString("Summary");
	static HSSFRichTextString tpaSummary = new HSSFRichTextString("TPA Summary");
	static String fisrtRowValue = null;
	static int firstRowNum = 0;
	static boolean isMismatch = false;
	static String fisrtRowValue1 = null;
	static int firstRowNum1 = 0;
	static boolean isMismatch1 = false;
	static {
		homePath = "C:\\Eclipse\\workspace\\TPAClaimDashboard\\src\\";
		logFileName = homePath + "TPA_Claims_Dashboard\\LOG\\";
		// path = homePath ";
	}
	static int tpaId;
	static int tpas[] = {
	 5,6,9,10,33,41,43,52,60,70,77,
	 82,84,87,89,91,94,98,101,106,112,
	 124,127,128,130,133,137,143,144,151,
	 152,163,167,168,169,173,182,187,190,192,
	 195,196,199,206,212,219,228,231,234,242,243,
	 246,247,248,250,252,254,255,256,257,263,306,309,503,506,507,600,601
	 };
	//static int tpas[] = { 5, 6, 10, 41, 43, 219, 503 };
	 //static int tpas[] = { 5, 6,10};

	public TPAClaimDashboardReportGenerator() {
	}

	public static void main(String args[]) throws Exception {

		monthFolderName = getValuationFolder();
		output = outputPath + monthFolderName + "\\";
		long l = System.currentTimeMillis();
		printStartBanner();
		reportName = "TPAClaimsDashboardReport";
		System.out.println(reportName);

		System.out.println("TPA Count:" + tpas.length);
		for (int i = 0; i < tpas.length; i++) {
			tpaId = tpas[i];
			System.out.println("Report for TPA :" + tpaId);
		}
		List list = populateQueryName(reportName);
		System.out.println(list.size());
		for (int i = 0; i < list.size(); i++)
			System.out.println(list.get(i));

		exeuteTPAClaimDashboardReport(list, monthFolderName);
		long l1 = System.currentTimeMillis();
		printBanner(l, l1);
		mailProcessing();

	}

	static String getValuationFolder() throws Exception {
		String s = DateRoutine.getPrevValuationDate(DateRoutine.getCurrentDate()).toString();
		int i = Integer.parseInt(s.substring(0, 4));
		int j = Integer.parseInt(s.substring(5, 7));
		int k = Integer.parseInt(s.substring(8, 10));
		Calendar calendar = Calendar.getInstance();
		calendar.set(i, j - 1, k);
		Date date = new Date(calendar.getTimeInMillis());
		monthFolderName = (new SimpleDateFormat("MMM")).format(date);
		return monthFolderName + i;
	}

	static String getValuationDate() throws Exception {
		String date = DateRoutine.getPrevValuationDate(DateRoutine.getCurrentDate()).toString();
		int year = Integer.parseInt(date.substring(0, 4));
		int mon = Integer.parseInt(date.substring(5, 7));
		int dt = Integer.parseInt(date.substring(8, 10));
		Calendar ca1 = Calendar.getInstance();
		ca1.set(year, mon - 1, dt);
		java.util.Date d = new java.util.Date(ca1.getTimeInMillis());
		ValuationDate = (new SimpleDateFormat("MMM")).format(d);
		return ValuationDate + year;
	}

	private static void populateData(Map map) throws Exception {
		String as[] = null;
		String tempSheetName;
		java.util.Set set = map.keySet();
		ArrayList arraylist = new ArrayList();
		arraylist.addAll(set);
		int i = 0;
		as = populateParameters(reportName);
		PAGE_LIMIT = Integer.parseInt(as[1].trim());
		SHEET_LIMIT = Integer.parseInt(as[2].trim());
		workbook = new HSSFWorkbook();
		fontCaption = workbook.createFont();
		fontHeader = workbook.createFont();
		fontTotal = workbook.createFont();
		fontTotal2 = workbook.createFont();
		normalFont = workbook.createFont();
		summaryFont = workbook.createFont();
		tpaSummaryFont = workbook.createFont();
		summaryFont1 = workbook.createFont();
		tpaSummaryFont1 = workbook.createFont();
		summaryFontRatio = workbook.createFont();
		tpaSummaryFontRatio = workbook.createFont();
		boldFont = workbook.createFont();
		leftFont = workbook.createFont();
		topFont = workbook.createFont();
		styleTotal = workbook.createCellStyle();
		styleTotal2 = workbook.createCellStyle();
		styleHeader = workbook.createCellStyle();
		styleCaption = workbook.createCellStyle();
		styleCaption1 = workbook.createCellStyle();
		styleCaption2 = workbook.createCellStyle();
		styleAccFor = workbook.createCellStyle();
		styleAccForNumbers = workbook.createCellStyle();
		styleForPctge = workbook.createCellStyle();
		styleNormal = workbook.createCellStyle();
		styleNormalMerge = workbook.createCellStyle();
		styleTPASummary = workbook.createCellStyle();
		styleSummary = workbook.createCellStyle();
		styleTPASummaryRatio = workbook.createCellStyle();
		styleSummaryRatio = workbook.createCellStyle();
		styleSummary1 = workbook.createCellStyle();
		styleTPASummary1 = workbook.createCellStyle();
		styleSummary2 = workbook.createCellStyle();
		styleTPASummary2 = workbook.createCellStyle();
		styleColor = workbook.createCellStyle();
		styleBold = workbook.createCellStyle();
		styleTop = workbook.createCellStyle();
		styleLeft = workbook.createCellStyle();
		styleDate = workbook.createCellStyle();
		styleNum = workbook.createCellStyle();
		format = workbook.createDataFormat();
		normalFont.setFontHeight((short) 9);
		normalFont.setFontName("Arial");
		styleAccFor.setFont(normalFont);
		styleAccFor.setDataFormat(format.getFormat("_(* #,##0_);_(* (#,##0);_(* \"-\"??_);_(@_)"));

		styleDate.setDataFormat(format.getFormat("MM/dd/yyyy"));
		styleDate.setFont(normalFont);
		styleNum.setFont(normalFont);
		styleNum.setDataFormat(format.getFormat("0"));
		java.sql.Date date = DateRoutine.getPrevValuationDate(DateRoutine.getCurrentDate());
		String s = date.toString();
		String s1 = s.substring(0, 4);
		int k = Integer.parseInt(s1) - 1;
		int j1 = 0;
		for (int j = 0; j < arraylist.size(); j++)
			try {
				int l = 1;
				// name the sheets

				sheetName = prepareSheetName(arraylist, j);
				sheet = workbook.createSheet(sheetName);
				workbook.setSheetName(workbook.getSheetIndex(sheetName), sheetName);
				List listResultSets = (List) map.get(arraylist.get(j));
				for (int jj = 0; jj < listResultSets.size(); jj++) {
					ResultSet resultset = (ResultSet) listResultSets.get(jj);
					ResultSetMetaData resultsetmetadata = resultset.getMetaData();
					i = resultsetmetadata.getColumnCount();
					for (int i1 = 1; i1 <= i; i1++) {
						String s2 = workbook.getSheetName(j);
						sheet.setColumnWidth((short) i1, (short) 4500);
					}

					if (jj == 0) {
						l = populateHeader(sheet, l, i, i, (arraylist.get(j)).toString().replaceAll("Query.txt", ""));
						j1 = l;
					}
					int startRowNo = j1;
					double ad[] = new double[i + 1];
					while (resultset.next()) {
						prepareRow(resultset, sheet, i, l, sheetName);
						l++;
					}
				}
				mergeLastColumns(l);
				if (sheetName.equals("Close_Ratio") || sheetName.equals("Close_Duration")
						|| sheetName.equals("Average_Outstanding") || sheetName.equals("PendingCase_Duration")
						|| sheetName.equals("ClosedCasesAverage_Paid")) {
					populateRightBorder(sheet, j1, i, l++);
					prepareLastRow(sheet, l - 2, i - 1);
				} else {
					prepareLastRowAsTotal(sheet, i, l, j1, sheetName);
					recordsGen++;
					populateRightBorder(sheet, j1, i, l++);
					prepareLastRow(sheet, l - 1, i - 1);
				}
			} catch (SQLException sqlexception) {
				sqlexception.printStackTrace();
			}

		closeExcel(workbook, sheet, i);
	}

	private static String prepareSheetName(ArrayList arraylist, int j) {
		String tempSheetName;
		if (arraylist.get(j).equals("NewSymbols_FeaturesQuery.txt")) {
			tempSheetName = (arraylist.get(j)).toString().replaceAll("Query.txt", "");
			sheetName = tempSheetName.trim();
			// sheetName = "NewSymbols_Features";
		} else if (arraylist.get(j).equals("PendingSymbols_FeaturesQuery.txt")) {
			tempSheetName = (arraylist.get(j)).toString().replaceAll("Query.txt", "");
			sheetName = tempSheetName.trim();

		} else if (arraylist.get(j).equals("ClosedSymbols_FeaturesQuery.txt")) {
			tempSheetName = (arraylist.get(j)).toString().replaceAll("Query.txt", "");
			sheetName = tempSheetName.trim();

		} else if (arraylist.get(j).equals("Close_RatioQuery.txt")) {
			tempSheetName = (arraylist.get(j)).toString().replaceAll("Query.txt", "");
			sheetName = tempSheetName.trim();

		} else if (arraylist.get(j).equals("Close_DurationQuery.txt")) {
			tempSheetName = (arraylist.get(j)).toString().replaceAll("Query.txt", "");
			sheetName = tempSheetName.trim();

		} else if (arraylist.get(j).equals("PaidsQuery.txt")) {
			tempSheetName = (arraylist.get(j)).toString().replaceAll("Query.txt", "");
			sheetName = tempSheetName.trim();
		} else if (arraylist.get(j).equals("ClosedCases_PaidQuery.txt")) {
			tempSheetName = (arraylist.get(j)).toString().replaceAll("Query.txt", "");
			sheetName = tempSheetName.trim();
		} else if (arraylist.get(j).equals("ClosedCasesAverage_PaidQuery.txt")) {
			tempSheetName = (arraylist.get(j)).toString().replaceAll("Query.txt", "");
			sheetName = tempSheetName.trim();
		} else if (arraylist.get(j).equals("OutstandingQuery.txt")) {
			tempSheetName = (arraylist.get(j)).toString().replaceAll("Query.txt", "");
			sheetName = tempSheetName.trim();
		} else if (arraylist.get(j).equals("Average_OutstandingQuery.txt")) {
			tempSheetName = (arraylist.get(j)).toString().replaceAll("Query.txt", "");
			sheetName = tempSheetName.trim();
		} else if (arraylist.get(j).equals("PendingCase_DurationQuery.txt")) {
			tempSheetName = (arraylist.get(j)).toString().replaceAll("Query.txt", "");
			sheetName = tempSheetName.trim();
		} else if (arraylist.get(j).equals("RecoveriesQuery.txt")) {
			tempSheetName = (arraylist.get(j)).toString().replaceAll("Query.txt", "");
			sheetName = tempSheetName.trim();
		}
		return sheetName;
	}

	public static void mergeLastColumns(int l) {
		sheet.addMergedRegion(new Region(firstRowNum - 1, (short) 0, l - 2, (short) 0));
		sheet.addMergedRegion(new Region(firstRowNum1 - 1, (short) 1, l - 2, (short) 1));
		firstRowNum = 5;
		firstRowNum1 = 5;
	}

	private static void prepareRow(ResultSet rs, HSSFSheet sheet, int count, int rowNo, String sheetNames)
			throws ReportGenerationException, SQLException {
		HSSFRow row = null;
		HSSFCell cell = null;
		ResultSetMetaData meta;
		String currentCellVal = null;
		boolean firstColMerged = false;
		try {
			meta = rs.getMetaData();
		} catch (SQLException sQLException) {
			sQLException.printStackTrace();
			throw new ReportGenerationException(sQLException.getMessage());
		}
		boolean isSummary = false;
		boolean isTPASummary = false;

		for (int i = 1; i <= count; i++) {

			try {
				row = sheet.createRow((short) rowNo - 1);
				cell = row.createCell((short) ((short) i - 1));
				if ((i == 1) || (i == 2)) {

					currentCellVal = rs.getString(i) == null ? "" : rs.getString(i).trim();
					HSSFRichTextString cellValue = new HSSFRichTextString(currentCellVal);
					cell.setCellValue(cellValue);
					normalFont.setFontHeightInPoints((short) 9);
					normalFont.setFontName("Arial");
					styleNormalMerge.setFont(normalFont);
					styleNormalMerge.setAlignment(HSSFCellStyle.ALIGN_LEFT);
					styleNormalMerge.setVerticalAlignment(HSSFCellStyle.VERTICAL_TOP);
					cell.setCellStyle(styleNormalMerge);
					cell.setCellType(HSSFCell.CELL_TYPE_STRING);

					if (i == 1) {
						if (rowNo == 5) {
							fisrtRowValue = currentCellVal;
							firstRowNum = rowNo;
						}
						if (!currentCellVal.equals(fisrtRowValue)) {
							sheet.addMergedRegion(
									new Region(firstRowNum - 1, (short) (i - 1), rowNo - 2, (short) (i - 1)));
							fisrtRowValue = currentCellVal;
							firstRowNum = rowNo;
							firstColMerged = true;
						}
					} else if (i == 2) {
						if (rs.getString(i).trim().equals("ZZTPASummary")) {
							styleTPASummary.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
							styleTPASummary.setFillForegroundColor(HSSFColor.LIGHT_ORANGE.index);
							styleTPASummary.setFillBackgroundColor(HSSFColor.LIGHT_ORANGE.index);
							tpaSummaryFont.setBoldweight((short) 700);
							styleTPASummary.setFont(tpaSummaryFont);
							cell.setCellStyle(styleTPASummary);
							cell.setCellType(HSSFCell.CELL_TYPE_STRING);
							cell.setCellValue(tpaSummary);

						}
						if (rowNo == 5) {
							fisrtRowValue1 = currentCellVal;
							firstRowNum1 = rowNo;
						}
						if (!currentCellVal.equals(fisrtRowValue1) || firstColMerged) {
							sheet.addMergedRegion(
									new Region(firstRowNum1 - 1, (short) (i - 1), rowNo - 2, (short) (i - 1)));
							fisrtRowValue1 = currentCellVal;
							firstRowNum1 = rowNo;
						}
					}

				} else if (i == 3) {
					if ((rs.getString(i).trim().equals("zSummary") || rs.getString(i).trim().equals("ZSummary"))
							&& (rs.getString(i).trim() != null)) {

						styleSummary.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
						styleSummary.setFillForegroundColor(HSSFColor.LIGHT_CORNFLOWER_BLUE.index);
						styleSummary.setFillBackgroundColor(HSSFColor.LIGHT_CORNFLOWER_BLUE.index);
						summaryFont.setBoldweight((short) 700);
						styleSummary.setFont(summaryFont);
						cell.setCellStyle(styleSummary);
						cell.setCellType(HSSFCell.CELL_TYPE_STRING);

						cell.setCellValue(summary);
						isSummary = true;

					}

					else if (rs.getString(i).trim().equals("ZZTPASummary")) {

						styleTPASummary.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
						styleTPASummary.setFillForegroundColor(HSSFColor.LIGHT_ORANGE.index);
						styleTPASummary.setFillBackgroundColor(HSSFColor.LIGHT_ORANGE.index);
						tpaSummaryFont.setBoldweight((short) 700);
						styleTPASummary.setFont(tpaSummaryFont);
						cell.setCellStyle(styleTPASummary);
						cell.setCellType(HSSFCell.CELL_TYPE_STRING);
						cell.setCellValue(emptyString);
						isTPASummary = true;

					} else {

						HSSFRichTextString cellValue = new HSSFRichTextString(
								rs.getString(i) == null ? "" : rs.getString(i).trim());
						cell.setCellValue(cellValue);
						normalFont.setFontHeightInPoints((short) 9);
						normalFont.setFontName("Arial");
						styleNormal.setFont(normalFont);
						cell.setCellStyle(styleNormal);
						cell.setCellType(HSSFCell.CELL_TYPE_STRING);
					}

				} else {
					if (sheetNames.equals("Paids") || sheetNames.equals("ClosedCases_Paid")
							|| sheetNames.equals("ClosedCasesAverage_Paid") || sheetNames.equals("Outstanding")
							|| sheetNames.equals("Average_Outstanding") || sheetNames.equals("Recoveries")) {
						double cellValue = rs.getDouble(i);
						cell.setCellValue(cellValue);
						if (isSummary) {
							summaryFont.setFontHeightInPoints((short) 9);
							summaryFont.setFontName("Arial");
							summaryFont.setBoldweight((short) 700);
							styleSummary.setDataFormat((short) 5);
							styleSummary.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
							styleSummary.setFillForegroundColor(HSSFColor.LIGHT_CORNFLOWER_BLUE.index);
							styleSummary.setFillBackgroundColor(HSSFColor.LIGHT_CORNFLOWER_BLUE.index);
							styleSummary.setFont(summaryFont);
							cell.setCellStyle(styleSummary);
							cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
						} else if (isTPASummary) {
							tpaSummaryFont.setFontHeightInPoints((short) 9);
							tpaSummaryFont.setFontName("Arial");
							tpaSummaryFont.setBoldweight((short) 700);
							styleTPASummary.setDataFormat((short) 5);
							styleTPASummary.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
							styleTPASummary.setFillForegroundColor(HSSFColor.LIGHT_ORANGE.index);
							styleTPASummary.setFillBackgroundColor(HSSFColor.LIGHT_ORANGE.index);
							tpaSummaryFont.setBoldweight((short) 700);
							styleTPASummary.setFont(tpaSummaryFont);
							cell.setCellStyle(styleTPASummary);
							cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
						}

						else {
							normalFont.setFontHeightInPoints((short) 9);
							normalFont.setFontName("Arial");
							styleAccFor.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
							styleAccFor.setDataFormat((short) 5);
							styleAccFor.setFont(normalFont);
							cell.setCellStyle(styleAccFor);
							cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
						}
					} else if ((sheetNames.equals("NewSymbols_Features"))
							|| (sheetNames.equals("PendingSymbols_Features"))
							|| (sheetNames.equals("ClosedSymbols_Features"))
							|| (sheetNames.equals("PendingCase_Duration")) || (sheetNames.equals("Close_Duration"))) {
						int cellValue = rs.getInt(i);
						cell.setCellValue(cellValue);
						if (isSummary) {
							summaryFont.setFontHeightInPoints((short) 9);
							summaryFont.setFontName("Arial");
							summaryFont.setBoldweight((short) 700);
							styleSummary1
									.setDataFormat(format.getFormat("_(* #,##0_);_(* (#,##0);_(* \"-\"??_);_(@_)"));
							styleSummary1.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
							styleSummary1.setFillForegroundColor(HSSFColor.LIGHT_CORNFLOWER_BLUE.index);
							styleSummary1.setFillBackgroundColor(HSSFColor.LIGHT_CORNFLOWER_BLUE.index);
							styleSummary1.setFont(summaryFont);
							cell.setCellStyle(styleSummary1);
							cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
						} else if (isTPASummary) {
							tpaSummaryFont.setFontHeightInPoints((short) 9);
							tpaSummaryFont.setFontName("Arial");
							tpaSummaryFont.setBoldweight((short) 700);
							styleTPASummary1
									.setDataFormat(format.getFormat("_(* #,##0_);_(* (#,##0);_(* \"-\"??_);_(@_)"));
							styleTPASummary1.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
							styleTPASummary1.setFillForegroundColor(HSSFColor.LIGHT_ORANGE.index);
							styleTPASummary1.setFillBackgroundColor(HSSFColor.LIGHT_ORANGE.index);
							tpaSummaryFont.setBoldweight((short) 700);
							styleTPASummary.setFont(tpaSummaryFont);
							cell.setCellStyle(styleTPASummary1);
							cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
						} else {
							cell.setCellValue(cellValue);
							normalFont.setFontHeightInPoints((short) 9);
							normalFont.setFontName("Arial");
							styleAccForNumbers.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
							styleAccForNumbers
									.setDataFormat(format.getFormat("_(* #,##0_);_(* (#,##0);_(* \"-\"??_);_(@_)"));
							styleAccForNumbers.setFont(normalFont);
							cell.setCellStyle(styleAccForNumbers);
							cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
						}
					} else if ((sheetNames.equals("Close_Ratio"))) {
						if (i <= 18) {
							double cellValue = (rs.getDouble(i) / 100);
							cell.setCellValue(cellValue);
							if (isSummary) {
								summaryFont1.setFontHeightInPoints((short) 9);
								summaryFont1.setFontName("Arial");
								summaryFont1.setBoldweight((short) 700);
								styleSummary2.setDataFormat(
										format.getFormat("_(* #,##0.00%_);_(* (#,##0.00%);_(* \"-\"??_);_(@_)"));
								styleSummary2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
								styleSummary2.setFillForegroundColor(HSSFColor.LIGHT_CORNFLOWER_BLUE.index);
								styleSummary2.setFillBackgroundColor(HSSFColor.LIGHT_CORNFLOWER_BLUE.index);
								styleSummary2.setFont(summaryFont1);
								cell.setCellStyle(styleSummary2);
								cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
							} else if (isTPASummary) {
								tpaSummaryFont1.setFontHeightInPoints((short) 9);
								tpaSummaryFont1.setFontName("Arial");
								tpaSummaryFont1.setBoldweight((short) 700);
								styleTPASummary2.setDataFormat(
										format.getFormat("_(* #,##0.00%_);_(* (#,##0.00%);_(* \"-\"??_);_(@_)"));
								styleTPASummary2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
								styleTPASummary2.setFillForegroundColor(HSSFColor.LIGHT_ORANGE.index);
								styleTPASummary2.setFillBackgroundColor(HSSFColor.LIGHT_ORANGE.index);
								styleTPASummary2.setFont(tpaSummaryFont1);
								cell.setCellStyle(styleTPASummary2);
								cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
							} else {
								normalFont.setFontHeightInPoints((short) 9);
								normalFont.setFontName("Arial");
								styleForPctge.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
								styleForPctge.setDataFormat(
										format.getFormat("_(* #,##0.00%_);_(* (#,##0.00%);_(* \"-\"??_);_(@_)"));
								styleForPctge.setFont(normalFont);
								cell.setCellStyle(styleForPctge);
							}
						} else {
							
							int cellValue = rs.getInt(i);
							cell.setCellValue(cellValue);
							
							if (isSummary) {
								summaryFontRatio.setFontHeightInPoints((short) 9);
								summaryFontRatio.setFontName("Arial");
								summaryFontRatio.setBoldweight((short) 700);
								styleSummaryRatio.setDataFormat(format.getFormat("_(* #,##0_);_(* (#,##0);_(* \"-\"??_);_(@_)"));		
								styleSummaryRatio.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
								styleSummaryRatio.setFillForegroundColor(HSSFColor.LIGHT_CORNFLOWER_BLUE.index);
								styleSummaryRatio.setFillBackgroundColor(HSSFColor.LIGHT_CORNFLOWER_BLUE.index);
								styleSummaryRatio.setFont(summaryFontRatio);
								cell.setCellStyle(styleSummaryRatio);
								cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
							} else if (isTPASummary) {
								tpaSummaryFontRatio.setFontHeightInPoints((short) 9);
								tpaSummaryFontRatio.setFontName("Arial");
								tpaSummaryFontRatio.setBoldweight((short) 700);
								styleTPASummaryRatio.setDataFormat(format.getFormat("_(* #,##0_);_(* (#,##0);_(* \"-\"??_);_(@_)"));		
								styleTPASummaryRatio.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
								styleTPASummaryRatio.setFillForegroundColor(HSSFColor.LIGHT_ORANGE.index);
								styleTPASummaryRatio.setFillBackgroundColor(HSSFColor.LIGHT_ORANGE.index);
								styleTPASummaryRatio.setFont(tpaSummaryFontRatio);
								cell.setCellStyle(styleTPASummaryRatio);
								cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
								}
								else {
							normalFont.setFontHeightInPoints((short) 9);
							normalFont.setFontName("Arial");
							styleAccForNumbers.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
							styleAccForNumbers.setDataFormat(format.getFormat("_(* #,##0_);_(* (#,##0);_(* \"-\"??_);_(@_)"));		
							styleAccForNumbers.setFont(normalFont);
							cell.setCellStyle(styleAccForNumbers);
							cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
								}
						}
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
				throw new ReportGenerationException(e.getMessage());
			}
		}
	}

	private static void prepareLastRow(HSSFSheet hssfsheet, int i, int j) throws ReportGenerationException {
		int k = 1;
		for (int l = 0; l <= j; l++) {
			HSSFRow hssfrow = hssfsheet.createRow((short) i);
			HSSFCell hssfcell = hssfrow.createCell((short) ((short) k - 1));
			topFont.setFontHeightInPoints((short) 9);
			topFont.setFontName("Arial");
			topFont.setColor((short) 12);
			topFont.setBoldweight((short) 700);
			styleTop.setBorderTop((short) 2);
			styleTop.setFont(boldFont);
			hssfcell.setCellValue(emptyString);
			hssfcell.setCellStyle(styleTop);
			k++;
		}

	}

	private static void populateRightBorder(HSSFSheet hssfsheet, int i, int j, int k) {
		for (int l = i; l <= k; l++) {
			HSSFRow hssfrow = hssfsheet.createRow((short) l - 1);
			HSSFCell hssfcell = hssfrow.createCell((short) ((short) j));
			leftFont.setFontHeightInPoints((short) 9);
			leftFont.setFontName("Arial");
			leftFont.setColor((short) 12);
			leftFont.setBoldweight((short) 700);
			styleLeft.setBorderLeft((short) 1);
			styleLeft.setFont(leftFont);
			hssfcell.setCellValue(emptyString);
			hssfcell.setCellStyle(styleLeft);
		}

	}

	private static int populateHeader(HSSFSheet hssfsheet, int i, int j, int k, String s) throws Exception {
		populateHeaderDetails(s);
		fontCaption.setFontHeightInPoints((short) 12);
		fontCaption.setFontName("Arial");
		fontCaption.setColor((short) 52);
		fontCaption.setBoldweight((short) 700);
		styleHeader.setFont(fontCaption);
		java.sql.Date date = DateRoutine.getPrevValuationDate(DateRoutine.getCurrentDate());
		String s1 = date.toString();
		String s2 = s1.substring(0, 4);
		String s3 = s1.substring(5, 7);
		int l = Integer.parseInt(s3) - 1;

		int i1 = Integer.parseInt(s2) - 1;
		int j1 = Integer.parseInt(s3);
		displayValuation = getMonthForInt(l) + " " + s2;
		pDisplayValuation = getMonthForInt(l) + " " + i1;
		System.out.println(i1 + ": --- : " + l + ": --- : ");
		int k1 = 0;
		String reportCaption = generateReportCaptions(s);

		HSSFRow hssfrow = hssfsheet.createRow((short) i - 1);
		HSSFCell hssfcell = hssfrow.createCell((short) 0);
		HSSFRichTextString hssfrichtextstring = new HSSFRichTextString(reportCaption);
		hssfcell.setCellValue(hssfrichtextstring);
		hssfcell.setCellStyle(styleHeader);
		HSSFRow hssfrow1 = null;
		HSSFCell hssfcell1 = null;
		i++;
		k1++;
		int l1 = 1;
		int i2 = 0;
		i++;
		// prepare first Header row

		for (int col = 1; col <= tableHeader.size(); col++) {
			if (l1 == 1) {
				fontHeader.setFontHeightInPoints((short) 10);
				fontHeader.setFontName("Arial");
				// fontHeader.setColor((short)12);
				fontHeader.setBoldweight((short) 600);
				styleCaption1.setBorderBottom((short) 1);
				styleCaption1.setBorderLeft((short) 1);
				styleCaption1.setBorderTop((short) 1);
			} else if (l1 == 2 || l1 == 3) {
				fontHeader.setFontHeightInPoints((short) 10);
				fontHeader.setFontName("Arial");
				// fontHeader.setColor((short)12);
				fontHeader.setBoldweight((short) 600);
				styleCaption1.setBorderBottom((short) 1);
				styleCaption.setBorderLeft((short) 1);
				styleCaption1.setBorderTop((short) 1);
			}

			else if (l1 == j) {
				fontHeader.setFontHeightInPoints((short) 10);
				fontHeader.setFontName("Arial");
				// fontHeader.setColor((short)12);
				fontHeader.setBoldweight((short) 600);
				styleCaption2.setBorderBottom((short) 1);
				styleCaption2.setBorderRight((short) 1);
				styleCaption2.setBorderTop((short) 1);
				styleCaption2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
				styleCaption2.setFillForegroundColor(HSSFColor.LIGHT_CORNFLOWER_BLUE.index);
				styleCaption2.setFillBackgroundColor(HSSFColor.LIGHT_CORNFLOWER_BLUE.index);
			} else {
				fontHeader.setFontHeightInPoints((short) 10);
				fontHeader.setFontName("Arial");
				// fontHeader.setColor((short)12);
				fontHeader.setBoldweight((short) 600);
				styleCaption.setBorderBottom((short) 1);
				styleCaption.setBorderTop((short) 1);
			}
			styleCaption.setFont(fontHeader);
			hssfrow1 = hssfsheet.createRow((short) i - 1);
			hssfcell1 = hssfrow1.createCell((short) i2);
			if (l1 == 1 || l1 == 2 || l1 == 3) {
				HSSFRichTextString hssfrichtextstring1 = new HSSFRichTextString("");
				hssfcell1.setCellValue(hssfrichtextstring1);
				styleCaption1.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
				styleCaption1.setFillForegroundColor(HSSFColor.WHITE.index);
				styleCaption1.setWrapText(true);
				styleCaption1.setAlignment((short) 2);
				hssfcell1.setCellStyle(styleCaption1);

			} else {
				HSSFRichTextString hssfrichtextstring1 = firstHeaderRowCreation(s, l1);
				hssfcell1.setCellValue(hssfrichtextstring1);
				styleCaption2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
				styleCaption2.setFillForegroundColor(HSSFColor.LIGHT_CORNFLOWER_BLUE.index);
				styleCaption2.setFillBackgroundColor(HSSFColor.LIGHT_CORNFLOWER_BLUE.index);
				styleCaption2.setWrapText(true);
				hssfrow1.setHeight((short) 700);
				styleCaption2.setAlignment((short) 2);
				hssfcell1.setCellStyle(styleCaption2);
			}

			l1++;
			i2++;
		}

		firstHeaderRowMerger(hssfsheet, s);
		i++;
		i2 = 0;
		l1 = 1;
		for (Iterator iterator = tableHeader.iterator(); iterator.hasNext();) {
			if (l1 == 1) {
				fontHeader.setFontHeightInPoints((short) 9);
				fontHeader.setFontName("Arial");
				// fontHeader.setColor((short)12);
				fontHeader.setBoldweight((short) 700);
				styleCaption.setBorderBottom((short) 1);
				styleCaption.setBorderLeft((short) 1);
				styleCaption.setBorderTop((short) 1);
				styleCaption.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
				styleCaption.setFillForegroundColor(HSSFColor.CORNFLOWER_BLUE.index);
			} else if (l1 == j) {
				fontHeader.setFontHeightInPoints((short) 9);
				fontHeader.setFontName("Arial");
				// fontHeader.setColor((short)12);
				fontHeader.setBoldweight((short) 700);
				styleCaption.setBorderBottom((short) 1);
				styleCaption.setBorderRight((short) 1);
				styleCaption.setBorderTop((short) 1);
				styleCaption.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
				styleCaption.setFillForegroundColor(HSSFColor.CORNFLOWER_BLUE.index);
			} else {
				fontHeader.setFontHeightInPoints((short) 9);
				fontHeader.setFontName("Arial");
				// fontHeader.setColor((short)12);
				fontHeader.setBoldweight((short) 700);
				styleCaption.setBorderBottom((short) 1);
				styleCaption.setBorderTop((short) 1);
				styleCaption.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
				styleCaption.setFillForegroundColor(HSSFColor.CORNFLOWER_BLUE.index);
			}
			styleCaption.setFont(fontHeader);
			hssfrow1 = hssfsheet.createRow((short) i - 1);
			hssfcell1 = hssfrow1.createCell((short) i2);
			hssfrow1.setHeight((short) 900);
			HSSFRichTextString hssfrichtextstring1 = new HSSFRichTextString((String) iterator.next());
			hssfcell1.setCellValue(hssfrichtextstring1);
			styleCaption.setWrapText(true);
			styleCaption.setAlignment((short) 2);
			hssfcell1.setCellStyle(styleCaption);
			l1++;
			i2++;
		}

		return ++i;
	}

	private static void firstHeaderRowMerger(HSSFSheet hssfsheet, String s) {
		for (int firstRow = 1; firstRow <= tableHeader.size(); firstRow++) {
			int ColNoTemp = 3;
			int rowNoTemp = 2;
			int lastColNo = 7;
			int lastColNo1 = 5;
			hssfsheet.addMergedRegion(new Region(rowNoTemp, (short) 0, rowNoTemp, (short) 2));
			if (s.trim().equals("NewSymbols_Features")) {
				hssfsheet.addMergedRegion(new Region(rowNoTemp, (short) ColNoTemp, rowNoTemp, (short) lastColNo));

			} else if (s.trim().equals("PendingSymbols_Features")) {
				hssfsheet.addMergedRegion(new Region(rowNoTemp, (short) ColNoTemp, rowNoTemp, (short) lastColNo));

			} else if (s.trim().equals("ClosedSymbols_Features")) {
				hssfsheet.addMergedRegion(new Region(rowNoTemp, (short) ColNoTemp, rowNoTemp, (short) lastColNo));
				ColNoTemp += 5;
				lastColNo += 5;
				hssfsheet.addMergedRegion(new Region(rowNoTemp, (short) ColNoTemp, rowNoTemp, (short) lastColNo));
				ColNoTemp += 5;
				lastColNo += 5;
				hssfsheet.addMergedRegion(new Region(rowNoTemp, (short) ColNoTemp, rowNoTemp, (short) lastColNo));

			} else if (s.trim().equals("Close_Ratio")) {
				hssfsheet.addMergedRegion(new Region(rowNoTemp, (short) ColNoTemp, rowNoTemp, (short) lastColNo));
				ColNoTemp += 5;
				lastColNo += 5;
				hssfsheet.addMergedRegion(new Region(rowNoTemp, (short) ColNoTemp, rowNoTemp, (short) lastColNo));
				ColNoTemp += 5;
				lastColNo += 5;
				hssfsheet.addMergedRegion(new Region(rowNoTemp, (short) ColNoTemp, rowNoTemp, (short) lastColNo));
				ColNoTemp += 5;
				lastColNo += 5;
				hssfsheet.addMergedRegion(new Region(rowNoTemp, (short) ColNoTemp, rowNoTemp, (short) lastColNo));
			} else if (s.trim().equals("Close_Duration")) {
				hssfsheet.addMergedRegion(new Region(rowNoTemp, (short) ColNoTemp, rowNoTemp, (short) lastColNo));
				ColNoTemp += 5;
				lastColNo += 5;
				hssfsheet.addMergedRegion(new Region(rowNoTemp, (short) ColNoTemp, rowNoTemp, (short) lastColNo));
				ColNoTemp += 5;
				lastColNo += 5;
				hssfsheet.addMergedRegion(new Region(rowNoTemp, (short) ColNoTemp, rowNoTemp, (short) lastColNo));

			} else if (s.trim().equals("Paids")) {
				hssfsheet.addMergedRegion(new Region(rowNoTemp, (short) ColNoTemp, rowNoTemp, (short) lastColNo));
				ColNoTemp += 5;
				lastColNo += 5;
				hssfsheet.addMergedRegion(new Region(rowNoTemp, (short) ColNoTemp, rowNoTemp, (short) lastColNo));
				ColNoTemp += 5;
				lastColNo += 5;
				hssfsheet.addMergedRegion(new Region(rowNoTemp, (short) ColNoTemp, rowNoTemp, (short) lastColNo));
				ColNoTemp += 5;
				lastColNo += 5;
				hssfsheet.addMergedRegion(new Region(rowNoTemp, (short) ColNoTemp, rowNoTemp, (short) lastColNo));
			} else if (s.trim().equals("ClosedCases_Paid")) {
				hssfsheet.addMergedRegion(new Region(rowNoTemp, (short) ColNoTemp, rowNoTemp, (short) lastColNo));
				ColNoTemp += 5;
				lastColNo += 5;
				hssfsheet.addMergedRegion(new Region(rowNoTemp, (short) ColNoTemp, rowNoTemp, (short) lastColNo));
				ColNoTemp += 5;
				lastColNo += 5;
				hssfsheet.addMergedRegion(new Region(rowNoTemp, (short) ColNoTemp, rowNoTemp, (short) lastColNo));
				ColNoTemp += 5;
				lastColNo += 5;
				hssfsheet.addMergedRegion(new Region(rowNoTemp, (short) ColNoTemp, rowNoTemp, (short) lastColNo));
			} else if (s.trim().equals("ClosedCasesAverage_Paid")) {
				hssfsheet.addMergedRegion(new Region(rowNoTemp, (short) ColNoTemp, rowNoTemp, (short) lastColNo));
				ColNoTemp += 5;
				lastColNo += 5;
				hssfsheet.addMergedRegion(new Region(rowNoTemp, (short) ColNoTemp, rowNoTemp, (short) lastColNo));
				ColNoTemp += 5;
				lastColNo += 5;
				hssfsheet.addMergedRegion(new Region(rowNoTemp, (short) ColNoTemp, rowNoTemp, (short) lastColNo));
				ColNoTemp += 5;
				lastColNo += 5;
				hssfsheet.addMergedRegion(new Region(rowNoTemp, (short) ColNoTemp, rowNoTemp, (short) lastColNo));
			} else if (s.trim().equals("Outstanding")) {
				hssfsheet.addMergedRegion(new Region(rowNoTemp, (short) ColNoTemp, rowNoTemp, (short) lastColNo1));
				ColNoTemp += 3;
				lastColNo1 += 3;
				hssfsheet.addMergedRegion(new Region(rowNoTemp, (short) ColNoTemp, rowNoTemp, (short) lastColNo1));
				ColNoTemp += 3;
				lastColNo1 += 3;
				hssfsheet.addMergedRegion(new Region(rowNoTemp, (short) ColNoTemp, rowNoTemp, (short) lastColNo1));
				ColNoTemp += 3;
				lastColNo1 += 3;
				hssfsheet.addMergedRegion(new Region(rowNoTemp, (short) ColNoTemp, rowNoTemp, (short) lastColNo1));
			} else if (s.trim().equals("Average_Outstanding")) {
				hssfsheet.addMergedRegion(new Region(rowNoTemp, (short) ColNoTemp, rowNoTemp, (short) lastColNo1));
				ColNoTemp += 3;
				lastColNo1 += 3;
				hssfsheet.addMergedRegion(new Region(rowNoTemp, (short) ColNoTemp, rowNoTemp, (short) lastColNo1));
				ColNoTemp += 3;
				lastColNo1 += 3;
				hssfsheet.addMergedRegion(new Region(rowNoTemp, (short) ColNoTemp, rowNoTemp, (short) lastColNo1));
				ColNoTemp += 3;
				lastColNo1 += 3;
				hssfsheet.addMergedRegion(new Region(rowNoTemp, (short) ColNoTemp, rowNoTemp, (short) lastColNo1));

			} else if (s.trim().equals("PendingCase_Duration")) {
				hssfsheet.addMergedRegion(new Region(rowNoTemp, (short) ColNoTemp, rowNoTemp, (short) lastColNo1));

			} else if (s.trim().equals("Recoveries")) {
				hssfsheet.addMergedRegion(new Region(rowNoTemp, (short) ColNoTemp, rowNoTemp, (short) lastColNo));
			}
		}
	}

	public static HSSFRichTextString firstHeaderRowCreation(String s, int l1) {
		HSSFRichTextString hssfrichtextstring1 = null;
		if (s.trim().equals("NewSymbols_Features")) {
			hssfrichtextstring1 = new HSSFRichTextString("New Symbols");
		} else if (s.trim().equals("PendingSymbols_Features")) {
			hssfrichtextstring1 = new HSSFRichTextString("Pending Symbols");
		} else if (s.trim().equals("ClosedSymbols_Features")) {
			if (l1 == 4 || l1 == 5 || l1 == 6 || l1 == 7 || l1 == 8)
				hssfrichtextstring1 = new HSSFRichTextString("Closed Symbols (incl CWP)");
			else if (l1 == 9 || l1 == 10 || l1 == 11 || l1 == 12 || l1 == 13)
				hssfrichtextstring1 = new HSSFRichTextString("IML Closed Symbols");
			else if (l1 == 14 || l1 == 15 || l1 == 16 || l1 == 17 || l1 == 18)
				hssfrichtextstring1 = new HSSFRichTextString("CWP Symbol Count");
		} else if (s.trim().equals("Close_Ratio")) {
			if (l1 == 4 || l1 == 5 || l1 == 6 || l1 == 7 || l1 == 8)
				hssfrichtextstring1 = new HSSFRichTextString("Closing Ratio (includes CWP)");
			else if (l1 == 9 || l1 == 10 || l1 == 11 || l1 == 12 || l1 == 13)
				hssfrichtextstring1 = new HSSFRichTextString("Closing Ratio (excludes CWP)");
			else if (l1 == 14 || l1 == 15 || l1 == 16 || l1 == 17 || l1 == 18)
				hssfrichtextstring1 = new HSSFRichTextString("Closing Ratio CWP");
			else if (l1 == 19 || l1 == 20 || l1 == 21 || l1 == 22)
				hssfrichtextstring1 = new HSSFRichTextString("Number Of Reopened Claims");
		} else if (s.trim().equals("Close_Duration")) {
			if (l1 == 4 || l1 == 5 || l1 == 6 || l1 == 7 || l1 == 8)
				hssfrichtextstring1 = new HSSFRichTextString("Closed Duration");
			else if (l1 == 9 || l1 == 10 || l1 == 11 || l1 == 12 || l1 == 13)
				hssfrichtextstring1 = new HSSFRichTextString("IML Close Duration");
			else if (l1 == 14 || l1 == 15 || l1 == 16 || l1 == 17 || l1 == 18)
				hssfrichtextstring1 = new HSSFRichTextString("CWP Close Duration");
		} else if (s.trim().equals("Paids")) {
			if (l1 == 4 || l1 == 5 || l1 == 6 || l1 == 7 || l1 == 8)
				hssfrichtextstring1 = new HSSFRichTextString("Ind Paid");
			else if (l1 == 9 || l1 == 10 || l1 == 11 || l1 == 12 || l1 == 13)
				hssfrichtextstring1 = new HSSFRichTextString("Medical Paid");
			else if (l1 == 14 || l1 == 15 || l1 == 16 || l1 == 17 || l1 == 18)
				hssfrichtextstring1 = new HSSFRichTextString("Expense Paid");
			else if (l1 == 19 || l1 == 20 || l1 == 21 || l1 == 22 || l1 == 23)
				hssfrichtextstring1 = new HSSFRichTextString("IML Paid");
		} else if (s.trim().equals("ClosedCases_Paid")) {
			if (l1 == 4 || l1 == 5 || l1 == 6 || l1 == 7 || l1 == 8)
				hssfrichtextstring1 = new HSSFRichTextString("Ind Closed Paid");
			else if (l1 == 9 || l1 == 10 || l1 == 11 || l1 == 12 || l1 == 13)
				hssfrichtextstring1 = new HSSFRichTextString("Medical Closed Paid");
			else if (l1 == 14 || l1 == 15 || l1 == 16 || l1 == 17 || l1 == 18)
				hssfrichtextstring1 = new HSSFRichTextString("Expense Closed Paid");
			else if (l1 == 19 || l1 == 20 || l1 == 21 || l1 == 22 || l1 == 23)
				hssfrichtextstring1 = new HSSFRichTextString("IML Paid");
		} else if (s.trim().equals("ClosedCasesAverage_Paid")) {
			if (l1 == 4 || l1 == 5 || l1 == 6 || l1 == 7 || l1 == 8)
				hssfrichtextstring1 = new HSSFRichTextString("Ind Closed Average Paid");
			else if (l1 == 9 || l1 == 10 || l1 == 11 || l1 == 12 || l1 == 13)
				hssfrichtextstring1 = new HSSFRichTextString("Average Closed Medical Paid");
			else if (l1 == 14 || l1 == 15 || l1 == 16 || l1 == 17 || l1 == 18)
				hssfrichtextstring1 = new HSSFRichTextString("Expense Paid");
			else if (l1 == 19 || l1 == 20 || l1 == 21 || l1 == 22 || l1 == 23)
				hssfrichtextstring1 = new HSSFRichTextString("IML Paid");
		} else if (s.trim().equals("Outstanding")) {
			if (l1 == 4 || l1 == 5 || l1 == 6)
				hssfrichtextstring1 = new HSSFRichTextString("Ind OS");
			else if (l1 == 7 || l1 == 8 || l1 == 9)
				hssfrichtextstring1 = new HSSFRichTextString("Medical OS");
			else if (l1 == 10 || l1 == 11 || l1 == 12)
				hssfrichtextstring1 = new HSSFRichTextString("Expense OS");
			else if (l1 == 13 || l1 == 14 || l1 == 15)
				hssfrichtextstring1 = new HSSFRichTextString("IML OS");
		} else if (s.trim().equals("Average_Outstanding")) {
			if (l1 == 4 || l1 == 5 || l1 == 6)
				hssfrichtextstring1 = new HSSFRichTextString("Ind Average OS");
			else if (l1 == 7 || l1 == 8 || l1 == 9)
				hssfrichtextstring1 = new HSSFRichTextString("Medical Average OS");
			else if (l1 == 10 || l1 == 11 || l1 == 12)
				hssfrichtextstring1 = new HSSFRichTextString("Expense Average OS");
			else if (l1 == 13 || l1 == 14 || l1 == 15)
				hssfrichtextstring1 = new HSSFRichTextString("IML Average OS");
		} else if (s.trim().equals("PendingCase_Duration")) {
			if (l1 == 4 || l1 == 5 || l1 == 6)
				hssfrichtextstring1 = new HSSFRichTextString("Pending Duration");
		} else if (s.trim().equals("Recoveries")) {
			if (l1 == 4 || l1 == 5 || l1 == 6 || l1 == 7 || l1 == 8)
				hssfrichtextstring1 = new HSSFRichTextString("Recoveries");
		}
		return hssfrichtextstring1;
	}

	public static String generateReportCaptions(String s) {
		String reportCaption = "";
		if (s.trim().equals("NewSymbols_Features")) {
			reportCaption = "NewSymbols/Features";
		} else if (s.trim().equals("PendingSymbols_Features")) {
			reportCaption = "PendingSymbols/Features";
		} else if (s.trim().equals("ClosedSymbols_Features")) {
			reportCaption = "ClosedSymbols/Features";
		} else if (s.trim().equals("Close_Ratio")) {
			reportCaption = "Closing Ratio";
		} else if (s.trim().equals("Close_Duration")) {
			reportCaption = "Closed Symbol Average Duration";
		} else if (s.trim().equals("Paids")) {
			reportCaption = "Paids";
		} else if (s.trim().equals("ClosedCases_Paid")) {
			reportCaption = "Closed Paids";
		} else if (s.trim().equals("ClosedCasesAverage_Paid")) {
			reportCaption = "Closed Average Paids";
		} else if (s.trim().equals("Outstanding")) {
			reportCaption = "Outstanding ";
		} else if (s.trim().equals("Average_Outstanding")) {
			reportCaption = "Average Outstanding ";
		} else if (s.trim().equals("PendingCase_Duration")) {
			reportCaption = "Pending Duration";
		} else if (s.trim().equals("Recoveries")) {
			reportCaption = "Recoveries";
		}
		return reportCaption;
	}

	private static void closeExcel(HSSFWorkbook hssfworkbook, HSSFSheet hssfsheet, int i) throws Exception {
		try {
			fileName = fileNameEnd + "_" + getValuationFile() + ".xls";
			System.out.println("File Name : " + fileName);
			fout = new FileOutputStream(logFileName + fileNameEnd
					+ DateRoutine.getPrevValuationDate(DateRoutine.getCurrentDate()).toString() + ".txt");
			System.out.println("fout :" + fout);
			(new PrintStream(fout)).println("File Name : " + fileName);
			String s = output + fileNameEnd;
			boolean flag = (new File(output)).mkdir();
			boolean flag1 = (new File(s)).mkdir();
			System.out.println("outPutFolder:" + s + File.separatorChar + fileName);
			FileOutputStream fileoutputstream = new FileOutputStream(s + File.separatorChar + fileName);
			hssfworkbook.write(fileoutputstream);
			fileoutputstream.close();
		} catch (IOException ioexception) {
			ioexception.printStackTrace();
		}
	}

	static String getValuationFile() throws Exception {
		String s = DateRoutine.getPrevValuationDate(DateRoutine.getCurrentDate()).toString();
		int i = Integer.parseInt(s.substring(0, 4));
		int j = Integer.parseInt(s.substring(5, 7));
		int k = Integer.parseInt(s.substring(8, 10));
		return UtilityFunctions.padZeros((new StringBuffer(String.valueOf(j))).toString(), 'Z', 2) + i;
	}

	private static ResultSet exeuteReportGeneration(String s) throws ReportGenerationException {
		ResultSet rs = null;
		getConnection();
		try {
			Statement statement = connection.createStatement();
			rs = statement.executeQuery(s);
		} catch (SQLException sqlexception) {
			sqlexception.printStackTrace();
			throw new ReportGenerationException(sqlexception.getMessage());
		} finally {
			return rs;
		}
	}

	private static void closeConnection() throws ReportGenerationException {
		try {
			if (!connection.isClosed())
				connection.close();
		} catch (SQLException sqlexception) {
			sqlexception.printStackTrace();
			throw new ReportGenerationException(sqlexception.getMessage());
		}
	}

	private static double elapsedSeconds(long l, long l1) {
		return (double) (l1 - l) / 1000D;
	}

	private static void printBanner(long l, long l1) {
		System.out.println("");
		System.out.println("=========================================================");
		System.out.println("               Report generation statistics              ");
		System.out.println("=========================================================");
		System.out.println("");
		System.out.println("Report Creation successfully completed....");
		System.out.println("No of rows in report : " + recordsGen);
		System.out.println("Time taken for generating report  : " + (int) elapsedSeconds(l, l1) + " Sec");
		System.out.println("");
		System.out.println("=========================================================");
	}

	private static void getConnection() throws ReportGenerationException {
		try {
			populateDatabaseDetails();
			Class.forName("com.ibm.db2.jcc.DB2Driver");
			DriverManager.registerDriver(new DB2Driver());
			connection = DriverManager.getConnection("jdbc:db2://t4udbtpairp.aig.net:60170/" + database, userName,
					password);
			System.out.println("DATABASE connected....");
		} catch (ClassNotFoundException classnotfoundexception) {
			classnotfoundexception.printStackTrace();
			throw new ReportGenerationException(classnotfoundexception.getMessage());
		} catch (SQLException sqlexception) {
			sqlexception.printStackTrace();
			throw new ReportGenerationException(sqlexception.getMessage());
		}
	}

	private static String[] readFromFile(String s) {
		String as[] = new String[40];
		try {
			BufferedReader bufferedreader = new BufferedReader(new FileReader(s));
			String s1;
			for (int i = 0; (s1 = bufferedreader.readLine()) != null; i++)
				as[i] = s1.trim();

			bufferedreader.close();
		} catch (IOException ioexception) {
			ioexception.printStackTrace();
		}
		return as;
	}

	private static List readFromQueryName(String s) {
		ArrayList arraylist = new ArrayList();
		try {
			BufferedReader bufferedreader = new BufferedReader(new FileReader(s));
			String s1;
			while ((s1 = bufferedreader.readLine()) != null)
				arraylist.add(s1.trim());
			bufferedreader.close();
		} catch (IOException ioexception) {
			ioexception.printStackTrace();
		}
		return arraylist;
	}

	private static String[] populateParameters(String s) {
		String s1 = path + monthlyReport + s + File.separatorChar;
		String s2 = s1 + s + "Para.txt";
		System.out.println(s2 + "-----");
		String as[] = readFromFile(s2);
		fileNameEnd = as[0].trim();
		PAGE_LIMIT = Integer.parseInt(as[1].trim());
		return as;
	}

	private static List populateQueryName(String s) throws Exception {
		String s1 = path + monthlyReport + s;
		List list = readFromQueryName(s1 + "QueryNames.txt");
		return list;
	}

	private static void populateDatabaseDetails() {
		String as[] = readFromFile(path + "DatabaseDetails.txt");
		database = as[0].trim();
		userName = as[1].trim();
		password = as[2].trim();
	}

	private static void exeuteTPAClaimDashboardReport(List list, String monthFolderName1) throws Exception {
		List list1 = list;
		String s = null;
		boolean flag = false;
		LinkedHashMap linkedHashmap = new LinkedHashMap();
		LinkedHashMap linkedHashmap1 = new LinkedHashMap();
		List listResultsets = new ArrayList();
		for (int i = 0; i < list1.size(); i++) {
			String s1 = (String) list1.get(i);
			s = (String) list1.get(i);
			listResultsets = exeuteTPAClaimDashboardQueryLists(s1, monthFolderName1);
			linkedHashmap.put(s1, listResultsets);

		}

		populateData(linkedHashmap);

		closeConnection();
		System.out.println("No of rows for TPA " + s + " : " + recordsGen);
	}

	private static List exeuteTPAClaimDashboardQueryLists(String ss1, String monthFolderName1)
			throws ReportGenerationException {

		List listRs = new ArrayList();
		System.out.println();
		System.out.println("**************" + ss1 + "**************");
		for (int tpaNo = 0; tpaNo < tpas.length; tpaNo++) {
			String s1 = getQuery(ss1, tpas[tpaNo]);
			System.out.println(s1);
			ResultSet rs11 = exeuteReportGeneration(s1);
			if (rs11 != null) {
				listRs.add(rs11);
			}
		}
		return listRs;
	}

	private static List exeuteTPAClaimDashboardQuery(String s, String monthFolderName1)
			throws ReportGenerationException {
		List listRs = new ArrayList();
		System.out.println();
		System.out.println("**************" + s + "**************");
		String s1 = getQuery(s);
		System.out.println("------------------------------");
		System.out.println(s1);
		queryInOutput = homePath + "TPA_Claims_Dashboard\\ReportOutput\\Query\\" + monthFolderName1 + "\\";
		try {
			boolean success = (new File(queryInOutput)).mkdir();
			queryFile = new BufferedWriter(new FileWriter(queryInOutput + s));
			queryFile.write(s1);
			queryFile.close();
		} catch (IOException e) {
			e.printStackTrace();
			(new PrintStream(fout)).println("Exception: " + e.getStackTrace());
			System.err.println("Unable to write to file");
			System.exit(-1);
		}
		System.out.println("------------------------------");
		ResultSet rs = exeuteReportGeneration(s1);
		listRs.add(rs);
		System.out.println(" *****exeuteReportGeneration *******");
		return listRs;

	}

	private static String getQuery(String s, int i) {
		StringBuffer stringbuffer = new StringBuffer();
		StringBuffer stringbuffer1 = new StringBuffer();
		StringBuffer stringbuffer2 = new StringBuffer();
		System.out.println("query Name & Path : " + path + monthlyReport + reportName + File.separatorChar + s);
		try {
			BufferedReader bufferedreader = new BufferedReader(
					new FileReader(path + monthlyReport + reportName + File.separatorChar + s));
			for (String s1 = ""; (s1 = bufferedreader.readLine()) != null;) {
				String s2 = s1.replaceAll("----",
						DateRoutine.getPrevValuationDate(DateRoutine.getCurrentDate()).toString());
				stringbuffer.append(s2);
			}
			bufferedreader.close();
			String str1 = stringbuffer.toString();
			stringbuffer1.append(" ").append(str1.replaceAll("~~~~", (new StringBuffer(String.valueOf(i))).toString()));

			String str3;
			str3 = stringbuffer1.toString();
			stringbuffer2.append(" ").append(str3.replaceAll("####",
					DateRoutine.getPrevValuationDate(DateRoutine.getPrevYearCurrentDate(DateRoutine.getCurrentDate()))
							.toString()));

		} catch (FileNotFoundException filenotfoundexception) {
			filenotfoundexception.printStackTrace();
		} catch (IOException ioexception) {
			ioexception.printStackTrace();
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return stringbuffer2.toString();
	}

	private static String getQuery(String s) {
		StringBuffer stringbuffer = new StringBuffer();
		StringBuffer stringbuffer1 = new StringBuffer();
		StringBuffer stringbuffer2 = new StringBuffer();
		System.out.println("query Name & Path ::: " + path + monthlyReport + reportName + File.separatorChar + s);
		try {
			BufferedReader bufferedreader = new BufferedReader(
					new FileReader(path + monthlyReport + reportName + File.separatorChar + s));
			for (String s1 = ""; (s1 = bufferedreader.readLine()) != null;) {
				String s2 = s1.replaceAll("----",
						DateRoutine.getPrevValuationDate(DateRoutine.getCurrentDate()).toString());
				stringbuffer.append(s2);
			}
			bufferedreader.close();
			String str1 = stringbuffer.toString();
			stringbuffer1.append(" ")
					.append(str1.replaceAll("~~~~", (new StringBuffer(String.valueOf(tpaId))).toString()));

			String str3;
			str3 = stringbuffer1.toString();
			stringbuffer2.append(" ").append(str3.replaceAll("####",
					DateRoutine.getPrevValuationDate(DateRoutine.getPrevYearCurrentDate(DateRoutine.getCurrentDate()))
							.toString()));

		} catch (FileNotFoundException filenotfoundexception) {
			filenotfoundexception.printStackTrace();
		} catch (IOException ioexception) {
			ioexception.printStackTrace();
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return stringbuffer2.toString();
	}

	private static void populateHeaderDetails(String headerName) {
		String headerPath = path + monthlyReport + reportName + File.separatorChar;
		String headerDetails[] = readFromFile(headerPath + headerName + "Header.txt");

		List list = Arrays.asList(headerDetails);
		ArrayList arraylist = new ArrayList(list);
		int i = arraylist.size();
		System.out.print("arrayListLength:" + i);
		for (int j = 0; j < i; j++) {
			arraylist.remove(null);
			arraylist.remove("");
		}

		tableHeader = arraylist;
	}

	private static void printStartBanner() {
		System.out.println("");
		System.out.println("=========================================================");
		System.out.println("               Report generation  started......          ");
		System.out.println("               Please wait............                   ");
		System.out.println("=========================================================");
	}

	static String getMonthForInt(int i) {
		String s = "invalid";
		DateFormatSymbols dateformatsymbols = new DateFormatSymbols();
		String as[] = dateformatsymbols.getMonths();
		if (i >= 0 && i <= 11)
			s = as[i];
		return s;
	}

	static void mailProcessing() throws Exception {
		try {
			successMailProcessing();
		} catch (Exception exception) {
			ErrorMailProcessing(exception);
			System.err.println("Unable to send sucess mail");
		}
	}

	private static void successMailProcessing() throws Exception {
		Vector vector = new Vector();
		System.out.println("Sending mail...");
		Properties properties = new Properties();
		properties.setProperty("mail.transport.protocol", "smtp");
		properties.setProperty("mail.host", "usmxrelay.aig.com");
		Session session = Session.getDefaultInstance(properties, null);
		session.setDebug(true);
		try {
			MimeMessage mimemessage = new MimeMessage(session);
			mimemessage.setFrom(new InternetAddress("TPAIRSupport@aig.com"));
			String s = "";
			byte byte0 = 0;
			String s1 = "";

			if (reportName.equals("TPAClaimsDashboardReport")) {
				byte0 = 11;
				s1 = "TPAClaimsDashboardReport";
				mimemessage.addRecipient(javax.mail.Message.RecipientType.TO,
						new InternetAddress("Nick.Bongiovanni@aig.com"));
				mimemessage.addRecipient(javax.mail.Message.RecipientType.TO,
						new InternetAddress("Wendy.Boyd@AIG.com"));
				mimemessage.addRecipient(javax.mail.Message.RecipientType.TO,
						new InternetAddress("Doreen.Quance@AIG.com"));
				mimemessage.addRecipient(javax.mail.Message.RecipientType.TO,
						new InternetAddress("Suzanne.Egan@aig.com"));
				mimemessage.addRecipient(javax.mail.Message.RecipientType.TO,
						new InternetAddress("Brett.Maxedon@AIG.com"));
				mimemessage.addRecipient(javax.mail.Message.RecipientType.TO,
						new InternetAddress("Jonathan.Meersman@aig.com"));
				mimemessage.addRecipient(javax.mail.Message.RecipientType.TO,
						new InternetAddress("Jane.Jardiel@aig.com"));
				mimemessage.addRecipient(javax.mail.Message.RecipientType.CC,
						new InternetAddress("TPAIRFusionSupport@chartisinsurance.com"));
				mimemessage.addRecipient(javax.mail.Message.RecipientType.CC,
						new InternetAddress("FUSIONDATAMANAGEMENT@chartisinsurance.com"));
			}
			switch (byte0) {
			case 11:
				System.out.println("TPA Claims Dashboard Report");
				String s2 = "TPA Claims Dashboard Report for " + displayValuation + " Valuation";
				mimemessage.setSubject(s2);
				System.out.println("Subject:" + s2);
				s = "<font size='2' face='Book Antiqua'> Hello ,<br> <br>Please access the TPA Claims Dashboard Report using below link:<br> <a href=file:"
						+ output + "TPAClaimsDashboard\\>" + "<br>" + output + "TPAClaimsDashboard\\</a>"
						+ "<br><br><b> File Name: </b>" + fileName + "<br>"
						+ "<br> <b> Criteria:</b> This report is created for " + displayValuation + "valuation.<br>"
						+ "<br>" + "Please revert in case of any questions." + "<br>"
						+ "<br><br><b>Regards,<br>TPAIR-Support<br>India<br></font>";
				System.out.println("===========");
				System.out.println("message" + s);
				/* Create body part for the message */
				BodyPart messageBodyPart1 = new MimeBodyPart();
				messageBodyPart1.setContent(s, "text/html");

				/* Create the multipart */

				MimeBodyPart messageBodyPart2 = new MimeBodyPart();
				DataSource source = new FileDataSource(output + "TPAClaimsDashboard\\" + fileName);
				messageBodyPart2.setDataHandler(new DataHandler(source));
				messageBodyPart2.setFileName(fileName);

				Multipart multipart = new MimeMultipart();
				multipart.addBodyPart(messageBodyPart1);
				multipart.addBodyPart(messageBodyPart2);
				mimemessage.setContent(multipart);
				break;

			default:
				System.out.println("Invalid report");
				break;
			}

			Transport.send(mimemessage);
			System.out.println("Email Sent");
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

	private static void ErrorMailProcessing(Exception exception) throws Exception {
		Vector vector = new Vector();
		System.out.println("Sending mail...");
		Properties properties = new Properties();
		properties.setProperty("mail.transport.protocol", "smtp");
		properties.setProperty("mail.host", "usmxrelay.aig.com");
		Session session = Session.getDefaultInstance(properties, null);
		session.setDebug(true);
		try {
			MimeMessage mimemessage = new MimeMessage(session);
			mimemessage.setFrom(new InternetAddress("AIG-FUSION-TPAIR@adc.mindtree.com"));
			mimemessage.addRecipient(javax.mail.Message.RecipientType.TO,
					new InternetAddress("Girisaranyan.Ashok@aig.com"));
			String s = "TPA Claims Dashboard Report is created with Error." + new String(exception.getMessage());
			mimemessage.setSubject("TPA Claims Dashboard Report is created with Error.");
			mimemessage.setContent(s, "text/plain");
			Transport.send(mimemessage);
		} catch (Exception exception1) {
			exception1.printStackTrace();
		}
	}

	private static void prepareLastRowAsTotal(HSSFSheet sheet, int count, int rowNo, int firstRow, String sheetName1) {
		HSSFRow row = null;
		HSSFCell cell = null;
		String alpha = null;
		for (int i = 1; i <= count; i++) {
			if (i == 1) {
				row = sheet.createRow((short) rowNo - 1);
				// Create a cell and put a value in it.
				cell = row.createCell((short) ((short) i - 1));
				fontTotal.setFontHeightInPoints((short) 9);
				fontTotal.setFontName("Arial");
				fontTotal.setColor(HSSFColor.BLACK.index);
				fontTotal.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
				fontTotal.setBoldweight((short) 700);
				styleTotal.setBorderTop(HSSFCellStyle.BORDER_THIN);
				styleTotal.setFont(fontTotal);
				styleTotal.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
				styleTotal.setFillForegroundColor(HSSFColor.CORNFLOWER_BLUE.index);
				cell.setCellStyle(styleTotal);
				cell.setCellValue("Grand Total");

			} else if ((i == 2) || (i == 3)) {
				row = sheet.createRow((short) rowNo - 1);
				// Create a cell and put a value in it.
				cell = row.createCell((short) ((short) i - 1));
				cell.setCellValue("");
				styleTotal.setBorderTop(HSSFCellStyle.BORDER_THIN);
				styleTotal.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
				styleTotal.setFillForegroundColor(HSSFColor.CORNFLOWER_BLUE.index);
				cell.setCellStyle(styleTotal);

			} else {
				row = sheet.createRow((short) rowNo - 1);
				// Create a cell and put a value in it.
				cell = row.createCell((short) ((short) i - 1));
				alpha = getAlphabet(i, count);
				// String formula = "SUM(" + alpha + firstRow + ":" + alpha + (rowNo - 1) +
				// ")/2";
				String formula1 = "SUM(" + alpha + firstRow + ":" + alpha + (rowNo - 1) + ")/3";
				// String formula2 = "SUM(" + alpha + firstRow + ":" + alpha + (rowNo - 1) +
				// ")";
				// if (sheetName1.equals("ClosedCases_Paid") ||
				// sheetName1.equals("ClosedCasesAverage_Paid")) {
				//
				// fontTotal.setFontHeightInPoints((short) 9);
				// fontTotal.setFontName("Arial");
				// fontTotal.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
				// fontTotal.setBoldweight((short) 700);
				// styleTotal.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
				// styleTotal.setBorderTop(HSSFCellStyle.BORDER_THIN);
				// styleTotal.setDataFormat((short) 5);
				// styleTotal.setFont(fontTotal);
				// cell.setCellStyle(styleTotal);
				// cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
				// // if (sheetName1.equals("ClosedCasesAverage_Paid") ||
				// // sheetName1.equals("Average_Outstanding")) {
				// // cell.setCellFormula(formula2);
				// // } else
				// cell.setCellFormula(formula);

				// } else

				if ((sheetName1.equals("NewSymbols_Features")) || (sheetName1.equals("PendingSymbols_Features"))
						|| (sheetName1.equals("ClosedSymbols_Features")) || (sheetName1.equals("Paids"))
						|| (sheetName1.equals("Recoveries")) || (sheetName1.equals("Outstanding"))
						|| (sheetName1.equals("ClosedCases_Paid"))) {

					fontTotal2.setFontHeightInPoints((short) 9);
					fontTotal2.setFontName("Arial");
					fontTotal2.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
					fontTotal2.setBoldweight((short) 700);
					styleTotal2.setBorderTop(HSSFCellStyle.BORDER_THIN);
					styleTotal.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
					styleTotal2.setDataFormat(format.getFormat("_(* #,##0_);_(* (#,##0);_(* \"-\"??_);_(@_)"));
					styleTotal2.setFont(fontTotal2);
					styleTotal2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
					styleTotal2.setFillForegroundColor(HSSFColor.CORNFLOWER_BLUE.index);
					cell.setCellStyle(styleTotal2);
					cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
					cell.setCellFormula(formula1);

				}

			}
		}

	}

	private static String getAlphabet(int num, int limit) {
		String[] alphaArray = new String[68];

		String alpha = null;
		for (int i = 1; i <= limit; i++) {
			int k = i - 1;
			if ((i > 0) && (i <= 26)) {
				int j = i;
				i = i + 64;
				alphaArray[k] = "" + (char) i;

				i = j;
				k++;
			} else if ((i > 26) && (i <= 52)) {
				int j = i;
				i = i - 26;
				i = i + 64;
				alphaArray[k] = "A" + (char) i;

				i = j;
				k++;
			} else {
				int j = i;
				i = i - 52;
				i = i + 64;

				alphaArray[k] = "B" + (char) i;

				i = j;
				k++;
			}
		}
		for (int i = 0; i < limit; i++) {
			if ((num - 1) == i) {
				alpha = alphaArray[i];
			}

		}
		return alpha;
	}

}