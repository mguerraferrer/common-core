package mx.softixx.cis.common.core.datetime;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.TimeZone;

import lombok.val;
import lombok.extern.slf4j.Slf4j;
import mx.softixx.cis.common.core.data.ValueUtils;
import mx.softixx.cis.common.core.validator.ValidatorUtils;

@Slf4j(topic = "TimeZoneUtils")
public final class TimeZoneUtils {

	private TimeZoneUtils() {		
	}
	
	/**
	 * Returns the timezone from a given String date
	 * 
	 * @param date String date to determine the timezone
	 * @return
	 */
	public static String getTimeZone(final String date) {
		try {
			
			val sanitizedDate = DateUtils.sanitizeDate(date);
			if (ValidatorUtils.isNotEmpty(sanitizedDate)) {
				val formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME.withZone(ZoneId.systemDefault());
				val zonedDateTime = ZonedDateTime.parse(sanitizedDate, formatter);
				val timeZone = TimeZone.getTimeZone(zonedDateTime.getZone());
				return Arrays.stream(TimeZone.getAvailableIDs(timeZone.getRawOffset()))
							 .findFirst()
							 .map(item -> item)
							 .orElse(ValueUtils.EMPTY);
			}
			
		} catch (DateTimeParseException e) {
			log.error("#getTimeZone error {}", e.getMessage());
		}
		return null;
	}
	
}