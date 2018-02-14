import java.awt.Point;

public class Line2 {
    
    Point p1, p2;
    
    void printLength() {
        double length;
        length = Math.sqrt ( Math.pow(p2.getX() - p1.getX(), 2) + Math.pow(p2.getY() - p1.getY(), 2) ) ;
        System.out.println ("Line length is " + length);
    }
    
    void printAngle() {
        double angleInDegrees = Math.atan2 ( p2.getY() - p1.getY() , p2.getX() - p1.getX() ) * 180.0 / Math.PI;
        System.out.println ("Angle is " + angleInDegrees + " degrees");
    }

    public static void main(String[] args) {
        System.out.println ("testing Line2");
		/*
		 * Here you should set myLine to contain a reference to a new line
		 * object. Initialize myLine's p1 to the point (5, 10), and initialize
		 * myLine's p2 to the point (45, 40). Print the line's length, which
		 * should be 50. Print the line's angle, which should be around 36.87
		 * degrees.
		 */
        Line2 nl = new Line2();
        nl.p1 = new Point(5, 10);
        nl.p2 = new Point(45, 40);
        nl.printLength();
        nl.printAngle();
        
    }

}