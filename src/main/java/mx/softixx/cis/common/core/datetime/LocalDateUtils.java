package mx.softixx.cis.common.core.datetime;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;

import lombok.val;
import lombok.extern.slf4j.Slf4j;
import mx.softixx.cis.common.core.validator.ValidatorUtils;

@Slf4j(topic = "LocalDateUtils")
public final class LocalDateUtils {
	
	private LocalDateUtils() {
	}
	
	/**
	 * Returns a LocalDate (java.time.LocalDate)
	 * 
	 * @param year  Year of date (Integer)
	 * @param month Month of date (Integer)
	 * @param day   Day of date (Integer)
	 * @return LocalDate
	 */
	public static LocalDate of(Integer year, Integer month, Integer day) {
		try {
			if (year != null && month != null && day != null) {
				return LocalDate.of(year, month, day);
			}
		} catch (DateTimeException e) {
			log.error("#of error {}", e.getMessage());
		}
		return null;
	}
	
	/**
	 * Returns a LocalDate (java.time.LocalDate)
	 * 
	 * @param stringDate String date
	 * @param pattern    DateFormatter ({@link DateFormatter})
	 * @return LocalDate
	 */
	public static LocalDate parse(String stringDate, DateFormatter pattern) {
		try {
			if (ValidatorUtils.isNotEmpty(stringDate) && pattern != null) {
				val dateTimeFormatter = DateTimeFormatter.ofPattern(pattern.getFormat());
				return LocalDate.parse(stringDate, dateTimeFormatter);
			}
		} catch (DateTimeException e) {
			log.error("#parse error {}", e.getMessage());
		}
		return null;
	}

	/**
	 * Returns a LocalDate (java.time.LocalDate) from Date (java.util.Date)
	 * 
	 * @param date java.util.Date
	 * @return LocalDate
	 */
	public static LocalDate convert(Date date) {
		try {
			if (date != null) {
				return LocalDate.ofInstant(date.toInstant(), ZoneId.systemDefault());
			}
		} catch (DateTimeException e) {
			log.error("#convert error {}", e.getMessage());
		}
		return null;
	}

	/**
	 * Returns a LocalDate (java.time.LocalDate) from LocalDateTime
	 * (java.time.LocalDateTime)
	 * 
	 * @param ldt LocalDateTime (java.time.LocalDateTime)
	 * @return LocalDate
	 */
	public static LocalDate convert(LocalDateTime ldt) {
		if (ldt != null) {
			return ldt.toLocalDate();
		}
		return null;
	}
	
	public static LocalDate convert(java.sql.Timestamp timestamp) {
		if (timestamp != null) {
			return timestamp.toLocalDateTime().toLocalDate();
		}
		return null;
	}
	
	/**
	 * Returns the UTC {@code LocalDate}
	 * 
	 * @return the UTC {@code LocalDate}
	 */
	public static LocalDate convertToUTC() {
		val zdt = ZonedDateTime.now(ZoneOffset.UTC);
		return zdt.toLocalDate();
	}
	
	/**
	 * Converts {@code LocalDate} to UTC
	 * 
	 * @param ld {@code java.time.LocalDate}
	 * @return {@code LocalDate} in UTC
	 */
	public static LocalDate convertToUTC(LocalDate ld) {
		val ldt = LocalDateTimeUtils.convert(ld);
		if (ldt != null) {
			return ldt.atZone(ZoneId.systemDefault()).withZoneSameInstant(ZoneOffset.UTC).toLocalDate();
		}
		return null;
	}
	
	/**
	 * Converts from UTC to {@code LocalDate} 
	 * 
	 * @param ld {@code java.time.LocalDate}
	 * @return {@code LocalDate} from UTC
	 */
	public static LocalDate convertFromUTC(LocalDate ld) {
		val timeZone = TimeZoneUtils.getTimeZone(DateUtils.formatDate(DateFormatter.DATE_TIME_T_FORMAT));
		return convertFromUTC(ld, timeZone);
	}
	
	/**
	 * Converts from UTC to {@code LocalDate} using a given timezone
	 * 
	 * @param ld {@code java.time.LocalDate}
	 * @param timezone String
	 * @return {@code LocalDate} from UTC
	 */
	public static LocalDate convertFromUTC(LocalDate ld, String timeZone) {
		val ldt = LocalDateTimeUtils.convertFromUTC(LocalDateTimeUtils.convert(ld), timeZone);
		if (ldt != null) {
			return ldt.toLocalDate();
		}
		return null;
	}
	
	public static boolean isBefore(Date dateOne, Date dateTwo) {
		return isBefore(convert(dateOne), convert(dateTwo));
	}

	public static boolean isBefore(LocalDate ldOne, LocalDate ldTwo) {
		if (ldOne != null && ldTwo != null) {
			return ldOne.isBefore(ldTwo);
		}
		return false;
	}

	public static boolean isAfter(Date dateOne, Date dateTwo) {
		return isAfter(convert(dateOne), convert(dateTwo));
	}

	public static boolean isAfter(LocalDate ldOne, LocalDate ldTwo) {
		if (ldOne != null && ldTwo != null) {
			return ldOne.isAfter(ldTwo);
		}
		return false;
	}

	public static boolean isEqual(Date dateOne, Date dateTwo) {
		return isEqual(convert(dateOne), convert(dateTwo));
	}

	public static boolean isEqual(LocalDate ldOne, LocalDate ldTwo) {
		if (ldOne != null && ldTwo != null) {
			return ldOne.isEqual(ldTwo);
		}
		return false;
	}

	public static boolean isBeforeOrEqual(Date dateOne, Date dateTwo) {
		return isBeforeOrEqual(convert(dateOne), convert(dateTwo));
	}

	public static boolean isBeforeOrEqual(LocalDate ldOne, LocalDate ldTwo) {
		if (ldOne != null && ldTwo != null) {
			return ldOne.isBefore(ldTwo) || ldOne.isEqual(ldTwo);
		}
		return false;
	}

	public static boolean isAfterOrEqual(Date dateOne, Date dateTwo) {
		return isAfterOrEqual(convert(dateOne), convert(dateTwo));
	}

	public static boolean isAfterOrEqual(LocalDate ldOne, LocalDate ldTwo) {
		if (ldOne != null && ldTwo != null) {
			return ldOne.isAfter(ldTwo) || ldOne.isEqual(ldTwo);
		}
		return false;
	}

	public static boolean isInRange(String startDate, String endDate) {
		val now = DateUtils.formatDate(new Date(), DateFormatter.DATE_SIMPLE_FORMAT);
		return isInRange(now, startDate, endDate);
	}

	public static boolean isInRange(String startDate, String endDate, DateFormatter fomatter) {
		val now = DateUtils.formatDate(new Date(), fomatter);
		return isInRange(now, startDate, endDate);
	}

	public static boolean isInRange(String dateToCheck, String startDate, String endDate) {
		val toCheck = parse(dateToCheck, DateFormatter.DATE_SIMPLE_FORMAT);
		val startInterval = parse(startDate, DateFormatter.DATE_SIMPLE_FORMAT);
		val endInterval = parse(endDate, DateFormatter.DATE_SIMPLE_FORMAT);
		return isInRange(toCheck, startInterval, endInterval);
	}

	public static boolean isInRange(String dateToCheck, String startDate, String endDate,
			DateFormatter formatter) {
		val toCheck = parse(dateToCheck, formatter);
		val startInterval = parse(startDate, formatter);
		val endInterval = parse(endDate, formatter);
		return isInRange(toCheck, startInterval, endInterval);
	}

	public static boolean isInRange(Date dateToCheck, Date startDate, Date endDate) {
		val toCheck = convert(dateToCheck);
		val startInterval = convert(startDate);
		val endInterval = convert(endDate);
		return isInRange(toCheck, startInterval, endInterval);
	}

	public static boolean isInRange(LocalDate toCheck, LocalDate startInterval, LocalDate endInterval) {
		if (toCheck != null && startInterval != null && endInterval != null) {
			return isAfterOrEqual(toCheck, startInterval) && isBeforeOrEqual(toCheck, endInterval);
		}
		return false;
	}
	
	public static boolean isPast(LocalDate ld) {
		return isBefore(ld, LocalDate.now());
	}
	
	public static boolean isPastOrPresent(LocalDate ld) {
		return isEqual(ld, LocalDate.now()) || isBefore(ld, LocalDate.now());
	}
	
	public static boolean isFutureOrPresent(LocalDate ld) {
		return isEqual(ld, LocalDate.now()) || isAfter(ld, LocalDate.now());
	}
	
	public static boolean isFuture(LocalDate ld) {
		return isAfter(ld, LocalDate.now());
	}
	
	public static boolean hasOverlap(LocalDate sld1, LocalDate eld1, LocalDate sld2, LocalDate eld2) {
		val sLdt = LocalDateTimeUtils.convert(sld1);
		val eLdt = LocalDateTimeUtils.convert(eld1);
		val osLdt = LocalDateTimeUtils.convert(sld2);
		val oeLdt = LocalDateTimeUtils.convert(eld2);
		return LocalDateTimeUtils.hasOverlap(sLdt, eLdt, osLdt, oeLdt);
	}
	
	public static LocalDate firstDayOfMonth() {
		return YearMonth.from(LocalDateTime.now()).atDay(1);
	}

	public static LocalDate lastDayOfMonth() {
		return YearMonth.from(LocalDateTime.now()).atEndOfMonth();
	}
	
	public static LocalDate lastDayOfMonth(Integer year, Integer month) {
		if (year != null && month != null) {
			return lastDayOfMonth(LocalDate.of(year, month, 1));
		}
		return null;
	}
	
	public static LocalDate lastDayOfMonth(LocalDate ld) {
		try {
			if (ld != null) {
				return ld.withDayOfMonth(1).with(TemporalAdjusters.lastDayOfMonth());
			}
		} catch (DateTimeException | ArithmeticException e) {
			log.error("#lastDayOfMonth error {}", e);
		}
		return null;
	}
	
}