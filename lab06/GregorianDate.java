public class GregorianDate extends Date {

    public static int[] monthLengths = {31, 28, 31, 30, 31, 30, 31,
        31, 30, 31, 30, 31};

    public GregorianDate(int year, int month, int dayOfMonth) {
        super(year, month, dayOfMonth);
    }

    @Override
    public int dayOfYear() {
        int rtnValue = 0;
        for (int m = 0; m < month() - 1; m++) {
            rtnValue += monthLengths[m];
        }
        return rtnValue + dayOfMonth();
    }
    
    @Override
    public Date nextDate(){
    	if(monthLengths[month() - 1] == dayOfMonth()){
    		if(month() == 12){
    			return new GregorianDate(year() + 1, 1, 1);
    		}
    		return new GregorianDate(year(), month() + 1, 1);
    	}
    	return new GregorianDate(year(), month(), dayOfMonth() + 1);
    }

}
