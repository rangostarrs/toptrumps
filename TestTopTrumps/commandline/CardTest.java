package commandline;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class CardTest {

	
	private Card cardyCard = new Card ("Lovely Card", 4, 2, 8, 6, 7);
	
	
	
	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void returnHighestStattest() {
		
		
		//checking that the index returned by the highest stat method is the same as on our
		//example card
		assertEquals(2,cardyCard.returnHighestStat(cardyCard));
		
	}
	
	@Test
	void returnStatTest() {
		
		int statIndex = 4;
		assertEquals(7,cardyCard.returnStat(statIndex));
		
		
		
	}

}