package picoPlaca;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

import org.apache.commons.lang3.StringUtils;

public class Validator {
	
	Scanner sc;
	
	public Validator() {
		sc = new Scanner(System.in);
	}
	
	/**
	 * Get a validated plate
	 */
	public String getValidatedPlate() {
		String plate = null;
		Boolean valid = Boolean.FALSE;
		while (!valid) {
			plate = sc.nextLine();
			//Validating empty plate
			if (StringUtils.isNotEmpty(plate)) {
				//Validating only letters, numbers and -
				if (!plate.matches("^[a-zA-Z0-9-]*$")) {
					System.out.println("Please enter only letters, numbers and -");
				}else {
					//Validating format
					if (!plate.contains("-")) {
						System.out.println("Please enter a valid plate format (XBB-0903)");
					}else {
						String[] parts = plate.split("-");
						if (parts.length > 2) {
							System.out.println("Please enter a valid plate format (XBB-0903)");
						}else {
							String part1 = parts[0];
							String part2 = parts[1];
							if (part1.matches("^[a-zA-Z]*$") && part2.matches("^[0-9]*$")
									&& part1.length() == 3 && (part2.length() == 3 || part2.length() == 4)) {
								valid = Boolean.TRUE;
								System.out.println("Plate entered correctly!");
							}else {
								System.out.println("Please enter a valid plate format (XBB-0903)");
							}
						}
					}
				}
			}else {
				System.out.println("Please enter a plate");
			}
		}
		return plate;
	}
	
	/**
	 * Get a validated date
	 * @throws ParseException 
	 */
	public Date getValidatedDate() {
		String date = null;
		Date validDate = null;
		Boolean valid = Boolean.FALSE;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		formatter.setLenient(Boolean.FALSE);
		while (!valid) {
			date = sc.nextLine();
			//Validating empty date
			if (StringUtils.isNotEmpty(date)) {
				//Validating only numbers and -
				if (!date.matches("^[0-9-]*$")) {
					System.out.println("Please enter only numbers and -");
				}else {
					//Validating format
					if (!date.contains("-")) {
						System.out.println("Please enter a valid date format (2017-07-25)");
					}else {
						String[] parts = date.split("-");
						if (parts.length > 3 || parts.length < 3) {
							System.out.println("Please enter a valid date format (2017-07-25)");
						}else {
							String part1 = parts[0];
							String part2 = parts[1];
							String part3 = parts[2];
							if (part1.matches("^[0-9]*$") && part2.matches("^[0-9]*$") && part3.matches("^[0-9]*$")
									&& part1.length() == 4 && part2.length() == 2 && part3.length() == 2) {
								try {
									validDate = formatter.parse(date);
									valid = Boolean.TRUE;
									System.out.println("Date entered correctly!");
								} catch (Exception e) {
									System.out.println("Unvalid date. Try again please.");
								}
							}else {
								System.out.println("Please enter a valid date format (2017-07-25)");
							}
						}
					}
				}
			}else {
				System.out.println("Please enter a date");
			}
		}
		return validDate;
	}
	
	/**
	 * Get a validated time
	 * @throws ParseException 
	 */
	public Date getValidatedTime() {
		String time = null;
		Date validTime = null;
		Boolean valid = Boolean.FALSE;
		SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
		formatter.setLenient(Boolean.FALSE);
		while (!valid) {
			time = sc.nextLine();
			//Validating empty date
			if (StringUtils.isNotEmpty(time)) {
				//Validating only numbers and :
				if (!time.matches("^[0-9:]*$")) {
					System.out.println("Please enter only numbers and :");
				}else {
					//Validating format
					if (!time.contains(":")) {
						System.out.println("Please enter a valid time format (07:30)");
					}else {
						String[] parts = time.split(":");
						if (parts.length > 2) {
							System.out.println("Please enter a valid time format (07:30)");
						}else {
							String part1 = parts[0];
							String part2 = parts[1];
							if (part1.matches("^[0-9]*$") && part2.matches("^[0-9]*$")
									&& part1.length() == 2 && part2.length() == 2) {
								try {
									validTime = formatter.parse(time);
									valid = Boolean.TRUE;
									System.out.println("Time entered correctly!");
								} catch (Exception e) {
									System.out.println("Unvalid time. Try again please.");
								}
							}else {
								System.out.println("Please enter a valid time format (07:30)");
							}
						}
					}
				}
			}else {
				System.out.println("Please enter a time");
			}
		}
		return validTime;
	}
	
	/**
	 * Validate pico y placa
	 * @param plate
	 * @param date
	 * @param time
	 * @return
	 * @throws ParseException
	 */
	public Boolean validatePicoPlaca(String plate, Date date, Date time) throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
		Boolean allowedToDrive = Boolean.FALSE;
		Date morningLimit1 = formatter.parse("07:00");
		Date morningLimit2 = formatter.parse("09:30");
		Date eveningLimit1 = formatter.parse("16:00");
		Date eveningLimit2 = formatter.parse("19:30");
		//validating time
		if (((time.after(morningLimit1) || time.equals(morningLimit1)) && (time.before(morningLimit2) || time.equals(morningLimit2)))
				|| ((time.after(eveningLimit1) || time.equals(eveningLimit1)) && (time.before(eveningLimit2) || time.equals(eveningLimit2)))) {
			int plateDigit = Integer.valueOf(plate.substring(plate.length() - 1));
			Calendar c = Calendar.getInstance();
			c.setTime(date);
			int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
			
			if (dayOfWeek == 2 && (plateDigit == 1 || plateDigit == 2)) {
				//Monday
				allowedToDrive = Boolean.FALSE;
			}else if (dayOfWeek == 3 && (plateDigit == 3 || plateDigit == 4)) {
				//Tuesday
				allowedToDrive = Boolean.FALSE;
			}else if (dayOfWeek == 4 && (plateDigit == 5 || plateDigit == 6)) {
				//Wednesday
				allowedToDrive = Boolean.FALSE;
			}else if (dayOfWeek == 5 && (plateDigit == 7 || plateDigit == 8)) {
				//Thursday
				allowedToDrive = Boolean.FALSE;
			}else if (dayOfWeek == 6 && (plateDigit == 9 || plateDigit == 0)) {
				//Friday
				allowedToDrive = Boolean.FALSE;
			}else {
				allowedToDrive = Boolean.TRUE;
			}
		}else {
			allowedToDrive = Boolean.TRUE;
		}
		
		return allowedToDrive;
	}

}
