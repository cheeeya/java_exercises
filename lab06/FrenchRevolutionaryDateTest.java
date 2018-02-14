import static org.junit.Assert.*;

import org.junit.Test;


public class FrenchRevolutionaryDateTest {

	@Test
	public void testNextDate() {
		FrenchRevolutionaryDate frd = new FrenchRevolutionaryDate(1,1,1);
		FrenchRevolutionaryDate frd1 = new FrenchRevolutionaryDate(1,1,2);
		assertEquals(frd1.toString(), frd.nextDate().toString());
		FrenchRevolutionaryDate frd2 = new FrenchRevolutionaryDate(1,12,30);
		FrenchRevolutionaryDate frd3 = new FrenchRevolutionaryDate(1,13,1);
		assertEquals(frd3.toString(), frd2.nextDate().toString());
		FrenchRevolutionaryDate frd4 = new FrenchRevolutionaryDate(1,13,5);
		FrenchRevolutionaryDate frd5 = new FrenchRevolutionaryDate(2,1,1);
		assertEquals(frd5.toString(), frd4.nextDate().toString());
		
		
		
	}

}
