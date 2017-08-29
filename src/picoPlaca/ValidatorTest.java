package picoPlaca;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

public class ValidatorTest {

	@Test
	public void testValidator() throws ParseException {
		Validator in = new Validator();
		SimpleDateFormat formatterDate = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat formatterTime = new SimpleDateFormat("HH:mm");
		String plate = "XBB-0922";
		Date date = formatterDate.parse("2017-08-28");
		Date time = formatterTime.parse("07:30");
		assertEquals(Boolean.FALSE, in.validatePicoPlaca(plate, date, time));
	}

}
