/**
 * 
 */
package picoPlaca;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author gonzaloalban
 *
 */
public class PicoPlacaValidator {

	/**
	 * @param args
	 * @throws ParseException 
	 */
	public static void main(String[] args) throws ParseException {
		SimpleDateFormat formatterDate = new SimpleDateFormat("yyyy-MM-dd,E");
		SimpleDateFormat formatterTime = new SimpleDateFormat("HH:mm");
		Validator in = new Validator();
		System.out.println("Pico y placa Validator");
		
		System.out.println("Enter the plate (XBB-0903)");
		String plate = in.getValidatedPlate();
		
		System.out.println("Enter the date (2017-07-25)");
		Date date = in.getValidatedDate();
		
		System.out.println("Enter the time in 24H format (20:45)");
		Date time = in.getValidatedTime();
		
		System.out.println("Plate: " + plate);
		System.out.println("Date: " + formatterDate.format(date));
		System.out.println("Time: " + formatterTime.format(time));
		if (in.validatePicoPlaca(plate, date, time)) {
			System.out.println("Result: Your car is allowed to road this date!");
		}else {
			System.out.println("Result: Your car is not allowed to road this date!");
		}
	}

}
