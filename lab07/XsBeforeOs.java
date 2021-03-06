public class XsBeforeOs {

    // Rearrange the elements of values so that all the Xs precede all
    // the Os.
    public static void rearrange(char[] values) {
        int lastXpos = -1;
        for (int k = 0; k < values.length; k++) {
            if (values[k] == 'X') {
            	if(k != lastXpos + 1){
            		values[lastXpos + 1] = 'X';
            		lastXpos += 1;
            		values[k] = 'O';
            	} else{
            		lastXpos += 1;
            	}
            }
            try {
                isOK(values, k);
            } catch (IllegalStateException e) {
                System.err.println("inconsistency at position " + k);
            }
        }
    }

    // Check for consistency. All the Xs in elements 0 to k of values should
    // precede all the Os. Throw an IllegalStateException if this is not
    // consistent.
    public static void isOK (char[] values, int k) {
    	int lastX = 0, lastO = values.length;
    	for(int i = 0; i <= k; i++){
    		if(values[i] == 'X'){
    			lastX = i;
    		} else{
    			lastO = i;
    		}	
    	}
    	if (lastX > lastO){
			throw new IllegalStateException("position of X greater than O");
		}

    }
}
