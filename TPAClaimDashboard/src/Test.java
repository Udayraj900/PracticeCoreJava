import java.util.Date;

public class Test {

	public static void main(String[] args) throws Exception {
		
		String currentDate = DateRoutine.getCurrentDate().toString();
	//	String prevDate = DateRoutine.getPrevValuationDate((java.sql.Date)currentDate).toString();
		System.out.println(currentDate);
	//	System.out.println(prevDate);
		
		String prevYearCurrentDate = DateRoutine.getPrevYearCurrentDate(DateRoutine.getCurrentDate()).toString();
		System.out.println(prevYearCurrentDate);
		
		System.out.println(DateRoutine.getPrevValuationDate(DateRoutine.getPrevYearCurrentDate(DateRoutine.getCurrentDate())).toString());
	}

}
