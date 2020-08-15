package hospital.util;

import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;

import hospital.entity.UserDetail;

public class Util {
	
	public static String makeFullName(UserDetail userDetail) {
		return userDetail.getFirstName() + " " + userDetail.getLastName();
	}
	
	/*
	 * LocalDateTime Utils
	 */
	public static String formatDate(TemporalAccessor date, String format) {
		return DateTimeFormatter.ofPattern(format).format(date);
	}
}
