import java.util.*;
/* Timer.java implements a simple stopwatch/timer type class based
 * on Java wall-clock time.
 *
 * RUNNING() == true  <==>  start() was called with no corresponding
 *                          call to stop()
 *
 * All times are given in units of msec.
 */

public class Timer {

    /* Initializes Timer to 0 msec */
    public Timer() {
        reset();
    }

    /* Starts the timer. Accumulates time across multiple calls to start. */
    public void start() {
        running = true;
        tStart = System.currentTimeMillis();
        tFinish = tStart;
    }

    /* Stops the timer.
     * Returns the time elapsed since the last matching call to start(), or
     * 0 if no such matching call was made. */
    public long stop() {
        tFinish = System.currentTimeMillis();
        if (running) {
            running = false;
            long diff = tFinish - tStart;
            tAccum += diff;
            return diff;
        }
        return 0;
    }

    /*
     * If RUNNING()     ==>     Returns the time since last call to start()
     * If !RUNNING()    ==>     Returns total elapsed time
     */
    public long elapsed() {
        if( running )
            return System.currentTimeMillis() - tStart;

        return tAccum;
    }

    /* Stops timing if currently RUNNING() and resets the accumulated
     * time to 0. */
    public void reset() {
        running = false;
        tStart = tFinish = 0;
        tAccum = 0;
    }

    private boolean running;
    private long tStart;
    private long tFinish;
    private long tAccum;    // total time
    
    
    public static void main(String[] arg) {
    	// experiment 1//
    	/*
    	ArrayList<Integer> lst1 = new ArrayList<Integer>(10);
    	LinkedList<Integer> lst2 = new LinkedList<Integer>();
    	int i = 0;
    	Timer time = new Timer();
 
    	time.start();
    	while (i < 6000000) {
    		lst1.add(i);
    		i++;
    	}
    	double t1 = time.stop();
    	time.reset();
    	
    	i = 0;
    	time.start();
    	while (i < 6000000) {
    		lst2.add(i);
    		i++;
    	}
    	double t2 = time.stop();
    	time.reset();
    	System.out.println("1  "+(double) t1/1000+" s   " + (double) t2/1000+" s");
    		 		
    	
    	i = 0;
    	time.start();
    	while(i<lst1.size()/80) {
    		lst1.get(i);
    		i++;
    	}
    	double t3 = time.stop();
    	time.reset();
    	
    	i = 0;
    	time.start();
    	while(i<lst2.size()/80) {
    		lst2.get(i);
    		i++;
    	}
    	double t4 = time.stop();
    	time.reset();
  
    	System.out.println("2  "+(double) t3/1000+" s   "+(double) t4/1000+" s");
    	 
    	i = 0;
    	time.start();
    	while(i<lst1.size()) {
    		lst1.remove(0);
    		i++;
    	}
    	double t5 = time.stop();
    	time.reset();
    	
    	time.start();
    	while(i<lst2.size()){
    		lst2.remove();
    		i++;
    	}
    	double t6 = time.stop();
    	time.reset();
    	
    	
    	System.out.println("3 " + (double) t5/1000 + " s  " + (double) t6/1000 +" s");*/
    	
    	
    	LinkedList<Integer> list = new LinkedList<Integer>();
    	LinkedList<Integer> list2 = new LinkedList<Integer>();
    	
    	
    	Timer time = new Timer();
    	int i = 0;
    	time.start();
    	while(i<5000000){
    		list.add(i);
    		i++;
    	}
    	double t = time.stop();
    	time.reset();
    
    	i = 0;
    	time.start();
    	while(i<10000000){
    		list2.add(i);
    		i++;
    	}
    	double t1 = time.stop();
    	time.reset();
    	
    	i = 4999999;
    	time.start();
    	list.remove(i);
    	double t2 = time.stop();
    	time.reset();
    	
    	i = 9999999;
    	time.start();
    	list2.remove(i);
    	double t3 = time.stop();
    	time.reset();
    	
    	System.out.println(t/1000 + "  " + t1/1000);
    	System.out.println(t2/1000 + "  " + t3/1000);
    }

}
