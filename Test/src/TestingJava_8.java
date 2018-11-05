import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class TestingJava_8 {

	
	public static void main(String[] args) {
		//getTimeZones();
		
		List<String> list = new ArrayList<>();
		
		list.add("ram");
		list.add("Sam");
		list.add("Chai");
		list.add("Pappi");
		list.add("Avi");
		list.add("Vishnu");
		list.add("raj");
		
	list.forEach(System.out::println);
	getTimeZones();
	
	}

	private static void getTimeZones() {
		Set<String> allZones = ZoneId.getAvailableZoneIds();
		LocalDateTime dt = LocalDateTime.now();

		// Create a List using the set of zones and sort it.
		List<String> zoneList = new ArrayList<String>(allZones);
		Collections.sort(zoneList);

		

		for (String s : zoneList) {
		    ZoneId zone = ZoneId.of(s);
		    ZonedDateTime zdt = dt.atZone(zone);
		    ZoneOffset offset = zdt.getOffset();
		    int secondsOfHour = offset.getTotalSeconds() % (60 * 60);
		    String out = String.format("%35s %10s%n", zone, offset);

		    // Write only time zones that do not have a whole hour offset
		    // to standard out.
		    if (secondsOfHour != 0) {
		        System.out.printf(out);
		    }
		    
		}
	}

	

}
