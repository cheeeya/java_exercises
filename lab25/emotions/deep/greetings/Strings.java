package greetings;

import java.awt.Point;
import java.util.ArrayList;
import java.util.*;

public class Strings {
	
	public static String fastMultiply(String s, int N) {
	    StringBuilder results = new StringBuilder(s); // create a StringBuilder from s
	    for (int i = 0; i < N; i++) {
	        results.append(s); // use StringBuilder's fast append
	    }
	    return results.toString(); // turn the StringBuilder back into a String
	}
	
	public static String multiply(String s, int N) {
	    String results = s;
	    for (int i = 0; i < N; i++) {
	        results += s;
	    }
	    return results;
	}

	public static void main(String[] args) {
		ArrayList<? extends Map> list = new ArrayList<HashMap>();
		Map<String, Integer> map = new HashMap<String, Integer>();
		list.add(map);
		
	}

}
