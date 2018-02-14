import static org.junit.Assert.*;

import org.junit.Test;


public class GregorianDateTest {

	@Test
	public void testNextDate() {
		GregorianDate gd0 = new GregorianDate(1, 1, 1);
		GregorianDate gd1 = new GregorianDate(1, 1, 2);
		assertEquals(gd1.toString(), gd0.nextDate().toString());
		GregorianDate gd2 = new GregorianDate(1, 1, 31);
		GregorianDate gd3 = new GregorianDate(1, 2, 1);
		assertEquals(gd3.toString(), gd2.nextDate().toString());
		GregorianDate gd4 = new GregorianDate(1, 12, 31);
		GregorianDate gd5 = new GregorianDate(2, 1, 1);
		assertEquals(gd5.toString(), gd4.nextDate().toString());
	}

}
