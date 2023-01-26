package mx.softixx.cis.common.core.datetime;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.regex.Pattern;

import lombok.val;
import lombok.extern.slf4j.Slf4j;
import mx.softixx.cis.common.core.data.ValueUtils;
import mx.softixx.cis.common.core.validator.ValidatorUtils;

@Slf4j(topic = "LocalTimeUtils")
public final class LocalTimeUtils {

	private LocalTimeUtils() {
	}

	public static LocalTime convert(Date date) {
		try {
			if (date != null) {
				return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault()).toLocalTime();
			}
		} catch (DateTimeException e) {
			log.error("#convert error", e);
		}
		return null;
	}
	
	public static LocalTime convert(java.sql.Timestamp timestamp) {
		if (timestamp != null) {
			return timestamp.toLocalDateTime().toLocalTime();
		}
		return null;
	}

	public static LocalTime parse(String dateStr) {
		try {
			val format = DateTimeFormatter.ofPattern(DateFormatter.T24H.getFormat());
			return LocalTime.parse(dateStr, format);
		} catch (DateTimeParseException e) {
			log.error("#parse error", e);
		}
		return null;
	}

	public static LocalTime parse(String dateStr, DateFormatter formatter) {
		try {
			if (ValidatorUtils.isNotEmpty(dateStr) && ValidatorUtils.isNotEmpty(formatter)) {
				val format = DateTimeFormatter.ofPattern(formatter.getFormat());
				return LocalTime.parse(sanitizeTime(dateStr), format);
			}
		} catch (DateTimeParseException e) {
			log.error("#parse error", e);
		}
		return null;
	}

	/**
	 * Returns the UTC {@code LocalDate}
	 * 
	 * @return the UTC {@code LocalDate}
	 */
	public static LocalTime convertToUTC() {
		val zdt = ZonedDateTime.now(ZoneOffset.UTC);
		return zdt.toLocalTime();
	}

	/**
	 * Converts {@code LocalTime} to UTC
	 * 
	 * @param lt {@code java.time.LocalTime}
	 * @return {@code LocalTime} in UTC
	 */
	public static LocalTime convertToUTC(LocalTime lt) {
		val ldt = LocalDateTimeUtils.convert(lt);
		if (ldt != null) {
			return ldt.atZone(ZoneId.systemDefault()).withZoneSameInstant(ZoneOffset.UTC).toLocalTime();
		}
		return null;
	}

	public static String sanitizeTime(String dateStr) {
		if (ValidatorUtils.isNotEmpty(dateStr)) {
			val timeStr = dateStr.toUpperCase();
			val hour = timeStr.split(Pattern.quote(":"))[0];
			if (hour.length() < 2) {
				return "0" + timeStr;
			}
			return timeStr;
		}
		return ValueUtils.EMPTY;
	}
	
	public static boolean isBefore(Date dateOne, Date dateTwo) {
		return isBefore(convert(dateOne), convert(dateTwo));
	}

	public static boolean isBefore(LocalTime ltOne, LocalTime ltTwo) {
		if (ltOne != null && ltTwo != null) {
			return ltOne.isBefore(ltTwo);
		}
		return false;
	}

	public static boolean isAfter(Date dateOne, Date dateTwo) {
		return isAfter(convert(dateOne), convert(dateTwo));
	}

	public static boolean isAfter(LocalTime ltOne, LocalTime ltTwo) {
		if (ltOne != null && ltTwo != null) {
			return ltOne.isAfter(ltTwo);
		}
		return false;
	}

	public static boolean isEqual(Date dateOne, Date dateTwo) {
		return isEqual(convert(dateOne), convert(dateTwo));
	}

	public static boolean isEqual(LocalTime ltOne, LocalTime ltTwo) {
		if (ltOne != null && ltTwo != null) {
			return ltOne.equals(ltTwo);
		}
		return false;
	}

	public static boolean isBeforeOrEqual(Date dateOne, Date dateTwo) {
		return isBeforeOrEqual(convert(dateOne), convert(dateTwo));
	}

	public static boolean isBeforeOrEqual(LocalTime ltOne, LocalTime ltTwo) {
		if (ltOne != null && ltTwo != null) {
			return (ltOne.isBefore(ltTwo) || ltOne.equals(ltTwo));
		}
		return false;
	}

	public static boolean isAfterOrEqual(Date dateOne, Date dateTwo) {
		return isAfterOrEqual(convert(dateOne), convert(dateTwo));
	}

	public static boolean isAfterOrEqual(LocalTime ltOne, LocalTime ltTwo) {
		if (ltOne != null && ltTwo != null) {
			return (ltOne.isAfter(ltTwo) || ltOne.equals(ltTwo));
		}
		return false;
	}

	public static boolean isInRange(String startTime, String endTime) {
		val now = DateUtils.formatDate(new Date(), DateFormatter.T24H);
		return isInRange(now, startTime, endTime);
	}

	public static boolean isInRange(String startTime, String endTime, DateFormatter fomatter) {
		val now = DateUtils.formatDate(new Date(), fomatter);
		return isInRange(now, startTime, endTime);
	}

	public static boolean isInRange(String timeToCheck, String startTime, String endTime) {
		val toCheck = parse(timeToCheck);
		val startInterval = parse(startTime);
		val endInterval = parse(endTime);
		return isInRange(toCheck, startInterval, endInterval);
	}

	public static boolean isInRange(String timeToCheck, String startTime, String endTime,
			DateFormatter formatter) {
		val toCheck = parse(timeToCheck, formatter);
		val startInterval = parse(startTime, formatter);
		val endInterval = parse(endTime, formatter);
		return isInRange(toCheck, startInterval, endInterval);
	}

	public static boolean isInRange(Date startDate, Date endDate) {
		return isInRange(new Date(), startDate, endDate);
	}

	public static boolean isInRange(Date dateToCheck, Date startDate, Date endDate) {
		val toCheck = convert(dateToCheck);
		val startInterval = convert(startDate);
		val endInterval = convert(endDate);
		return isInRange(toCheck, startInterval, endInterval);
	}

	public static boolean isInRange(LocalTime toCheck, LocalTime startInterval, LocalTime endInterval) {
		if (toCheck != null && startInterval != null && endInterval != null) {
			return toCheck.compareTo(startInterval) >= 0 && toCheck.compareTo(endInterval) <= 0;
		}
		return false;
	}
	
	public static boolean isPast(LocalTime lt) {
		return isBefore(lt, LocalTime.now());
	}
	
	public static boolean isPastOrPresent(LocalTime lt) {
		return isEqual(lt, LocalTime.now()) || isBefore(lt, LocalTime.now());
	}
	
	public static boolean isFutureOrPresent(LocalTime lt) {
		return isEqual(lt, LocalTime.now()) || isAfter(lt, LocalTime.now());
	}
	
	public static boolean isFuture(LocalTime lt) {
		return isAfter(lt, LocalTime.now());
	}
	
	public static boolean hasOverlap(LocalTime slt1, LocalTime elt1, LocalTime slt2, LocalTime elt2) {
		val sLdt = LocalDateTimeUtils.convert(slt1);
		val eLdt = LocalDateTimeUtils.convert(elt1);
		val osLdt = LocalDateTimeUtils.convert(slt2);
		val oeLdt = LocalDateTimeUtils.convert(elt2);
		return LocalDateTimeUtils.hasOverlap(sLdt, eLdt, osLdt, oeLdt);
	}

}