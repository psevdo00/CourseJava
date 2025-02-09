package pozdravlator;

import java.util.Comparator;

public class BirthdayComparator implements Comparator<Birthday> {

	@Override
	public int compare(Birthday o1, Birthday o2) {
		
		int monthCompare = Integer.compare(o1.getMonth(), o2.getMonth());
		
		if (monthCompare != 0) {
			
			return monthCompare;
			
		} 
		
		return Integer.compare(o1.getDay(), o2.getDay());
		
	}

}