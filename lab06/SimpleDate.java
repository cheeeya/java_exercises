import java.util.*;

public class SimpleDate implements Comparable<SimpleDate>{
	private int month, day;
	
	public SimpleDate(int m, int d){
		month = m;
		day = d;
	}
	
	public int compareTo(SimpleDate d){
		if(this.month == d.month){
			return this.day - d.day;
		}
		return this.month - d.month;
	}
	
	public String toString(){
		return month + "/" + day;
	}
	
	public static void main (String [ ] args) {
	    SimpleDate [ ] dArray = new SimpleDate [4];
	    dArray[0] = new SimpleDate (5, 2); // 5/2
	    dArray[1] = new SimpleDate (2, 9); // 2/9
	    dArray[2] = new SimpleDate (6, 3); // 6/3
	    dArray[3] = new SimpleDate (1, 11); // 1/11
	    Arrays.sort (dArray);
	    for (int k=0; k<dArray.length; k++) {
	        System.out.println(dArray [k]);
	    }
	    // should print the dates in chronological order:
	    // 1/11, 2/9, 5/2, 6/3
	}
}
