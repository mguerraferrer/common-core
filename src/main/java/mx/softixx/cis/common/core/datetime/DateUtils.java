package mx.softixx.cis.common.core.datetime;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DateTimeException;
import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.Date;
import java.util.EnumSet;
import java.util.List;

import lombok.val;
import lombok.extern.slf4j.Slf4j;
import mx.softixx.cis.common.core.data.IntegerUtils;
import mx.softixx.cis.common.core.data.ValueUtils;
import mx.softixx.cis.common.core.validator.ValidatorUtils;

@Slf4j(topic = "DateUtils")
public final class DateUtils {

	private DateUtils() {
	}

	public static final String LAST_TIME_OF_DATE = "23:59:59";
	private static final String LOG_FORMAT_DATE_ERROR = "#formatDate error";

	/**
	 * Format a current {@code java.util.Date} with specific formatter
	 * 
	 * @param pattern String
	 * @return A string date formatted from the Date
	 */
	public static String formatDate(String pattern) {
		return formatDate(new Date(), pattern);
	}

	/**
	 * Formats a {@code java.util.Date} with a specific pattern
	 * 
	 * @param date    java.util.date
	 * @param pattern String
	 * @return A string date formatted from the Date
	 */
	public static String formatDate(Date date, String pattern) {
		val ld = LocalDateUtils.convert(date);
		if (ld != null && pattern != null) {
			return formatDate(ld, pattern);
		}
		return null;
	}

	/**
	 * Formats a {@code java.time.LocalDate} with a specific pattern
	 * 
	 * @param ld      java.time.LocalDate
	 * @param pattern String
	 * @return A string date formatted from the LocalDate
	 */
	public static String formatDate(LocalDate ld, String pattern) {
		try {
			if (ld != null && pattern != null) {
				return ld.format(DateTimeFormatter.ofPattern(pattern));
			}
		} catch (DateTimeException | IllegalArgumentException e) {
			log.error(LOG_FORMAT_DATE_ERROR, e.getMessage());
		}
		return null;
	}

	/**
	 * Formats a {@code java.time.LocalDateTime} with a specific pattern
	 * 
	 * @param ldt     java.time.LocalDateTime
	 * @param pattern String
	 * @return A string date formatted from the LocalDateTime
	 */
	public static String formatDate(LocalDateTime ldt, String pattern) {
		try {
			if (ldt != null && ValidatorUtils.isNotEmpty(pattern)) {
				return ldt.format(DateTimeFormatter.ofPattern(pattern));
			}
		} catch (DateTimeException | IllegalArgumentException e) {
			log.error(LOG_FORMAT_DATE_ERROR, e.getMessage());
		}
		return null;
	}

	/**
	 * Format a current {@code java.util.Date} with specific formatter
	 * 
	 * @param formatter {@link DateFormatter}
	 * @return A string date formatted from the Date
	 * @see DateFormatter
	 */
	public static String formatDate(DateFormatter formatter) {
		return formatDate(new Date(), formatter);
	}

	/**
	 * Formats a {@code java.util.Date} with a specific format
	 * 
	 * @param date      java.util.Date
	 * @param formatter {@link DateFormatter}
	 * @return A string date formatted from the Date
	 * @see DateFormatter
	 */
	public static String formatDate(Date date, DateFormatter formatter) {
		try {
			if (date != null && formatter != null) {
				val sdf = new SimpleDateFormat(formatter.getFormat());
				return sdf.format(date).toLowerCase();
			}
		} catch (IllegalArgumentException e) {
			log.error(LOG_FORMAT_DATE_ERROR, e.getMessage());
		}
		return null;
	}

	/**
	 * Formats {@code java.time.LocalDate} with a specific format
	 * 
	 * @param localDate java.time.LocalDate
	 * @param formatter {@link DateFormatter}
	 * @return A string date formatted from the LocalDate
	 * @see DateFormatter
	 */
	public static String formatDate(LocalDate localDate, DateFormatter formatter) {
		try {
			if (localDate != null) {
				return localDate.format(DateTimeFormatter.ofPattern(formatter.getFormat()));
			}
		} catch (DateTimeException | IllegalArgumentException e) {
			log.error(LOG_FORMAT_DATE_ERROR, e.getMessage());
		}
		return null;
	}

	/**
	 * Formats a {@code java.time.LocalDateTime} with a specific format
	 * 
	 * @param LocalDateTimeUtils.convert java.time.LocalDateTime
	 * @param formatter     {@link DateFormatter}
	 * @return A string date formatted from the LocalDateTime
	 * @see DateFormatter
	 */
	public static String formatDate(LocalDateTime ldt, DateFormatter formatter) {
		try {
			if (ldt != null) {
				return ldt.format(DateTimeFormatter.ofPattern(formatter.getFormat()));
			}
		} catch (DateTimeException | IllegalArgumentException e) {
			log.error(LOG_FORMAT_DATE_ERROR, e.getMessage());
		}
		return null;
	}

	public static String formatDate(java.sql.Date sqlDate, DateFormatter formatter) {
		if (sqlDate != null && formatter != null) {
			try {
				val sdf = new SimpleDateFormat(formatter.getFormat());
				return sdf.format(sqlDate);
			} catch (IllegalArgumentException e) {
				log.error(LOG_FORMAT_DATE_ERROR, e.getMessage());
			}
		}
		return null;
	}

	/**
	 * Parses a date with a specific format
	 * 
	 * @param dateStr   String date
	 * @param formatter {@link DateFormatter}
	 * @return A Date parsed from the string
	 * @see DateFormatter
	 */
	public static Date parseDate(String dateStr, DateFormatter formatter) {
		if (formatter != null) {
			return parseDate(dateStr, formatter.getFormat());
		}
		return null;
	}

	/**
	 * Parses a date with a specific format
	 * 
	 * @param dateStr String date
	 * @param format  DateFormatter to be used
	 * @return A Date parsed from the string
	 * @see DateFormatter
	 */
	public static Date parseDate(String dateStr, String format) {
		try {
			if (dateStr != null && format != null) {
				val sdf = new SimpleDateFormat(format);
				return sdf.parse(dateStr);
			}
		} catch (IllegalArgumentException | ParseException e) {
			log.error("#parseDate error {}", e);
		}
		return null;
	}

	public static Date parseDateTime(String dateStr, DateFormatter formatter, boolean useLastTime) {
		if (ValidatorUtils.isNotEmpty(dateStr) && ValidatorUtils.isNotEmpty(formatter)
				&& ValidatorUtils.isNotEmpty(useLastTime)) {
			val date = parseDate(dateStr, formatter);
			val time = useLastTime ? parseDate(LAST_TIME_OF_DATE, formatter) : new Date();
			val ldt = LocalDateTimeUtils.convert(date, time);
			return convert(ldt);
		}
		return null;
	}

	public static Date convert(Instant instant) {
		if (instant != null) {
			return Date.from(instant);
		}
		return null;
	}

	public static Date convert(LocalDateTime ldt) {
		if (ldt != null) {
			val instant = ldt.atZone(ZoneId.systemDefault()).toInstant();
			return convert(instant);
		}
		return null;
	}

	public static Date convert(LocalDate ld) {
		if (ld != null) {
			val instant = ld.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
			return convert(instant);
		}
		return null;
	}

	public static Date convert(LocalTime lt) {
		val ldt = LocalDateTimeUtils.convert(lt);
		if (ldt != null) {
			val instant = ldt.atZone(ZoneId.systemDefault()).toInstant();
			return convert(instant);
		}
		return null;
	}

	public static Date convert(Integer year, Integer month, Integer day) {
		return convert(LocalDateUtils.of(year, month, day));
	}

	public static Date convert(java.sql.Timestamp timestamp) {
		if (timestamp != null) {
			return new Date(timestamp.getTime());
		}
		return null;
	}

	public static Date convert(java.sql.Date sqlDate) {
		if (sqlDate != null) {
			return new Date(sqlDate.getTime());
		}
		return null;
	}

	public static Date convert(java.sql.Time sqlTime) {
		if (sqlTime != null) {
			return new Date(sqlTime.getTime());
		}
		return null;
	}

	/**
	 * Returns the UTC date
	 * 
	 * @return the UTC date
	 */
	public static Date dateUTC() {
		val instant = ZonedDateTime.now(ZoneOffset.UTC).toLocalDateTime().atZone(ZoneId.systemDefault()).toInstant();
		return convert(instant);
	}

	/**
	 * Converts date to UTC
	 * 
	 * @param date {@code java.util.Date}
	 * @return UTC date
	 */
	public static Date convertToUTC(Date date) {
		return convert(LocalDateTimeUtils.convertToUTC(LocalDateTimeUtils.convert(date)));
	}

	/**
	 * Converts from UTC to {@code Date}
	 * 
	 * @param date {@code java.util.Date}
	 * @return {@code Date} from UTC
	 */
	public static Date convertFromUTC(Date date) {
		val timeZone = TimeZoneUtils.getTimeZone(formatDate(DateFormatter.DATE_TIME_T_FORMAT));
		return convertFromUTC(date, timeZone);
	}

	/**
	 * Converts from UTC to {@code Date} using a given timezone
	 * 
	 * @param date     {@code java.util.Date}
	 * @param timezone String
	 * @return {@code Date} from UTC
	 */
	public static Date convertFromUTC(Date date, String timeZone) {
		try {
			val ldt = LocalDateTimeUtils.convert(date);
			if (ldt != null && timeZone != null) {
				return convert(ldt.atZone(ZoneOffset.UTC).withZoneSameInstant(ZoneId.of(timeZone)).toInstant());
			}
		} catch (DateTimeException e) {
			log.error("#convertFromUTC error", e.getMessage());
		}
		return null;
	}

	public static String sanitizeDate(String dateStr) {
		if (ValidatorUtils.isNotEmpty(dateStr)) {
			return dateStr.toUpperCase();
		}
		return ValueUtils.EMPTY;
	}

	public static boolean hasOverlap(Date sd1, Date ed1, Date sd2, Date ed2) {
		val sLdt = LocalDateTimeUtils.convert(sd1);
		val eLdt = LocalDateTimeUtils.convert(ed1);
		val osLdt = LocalDateTimeUtils.convert(sd2);
		val oeLdt = LocalDateTimeUtils.convert(ed2);
		return LocalDateTimeUtils.hasOverlap(sLdt, eLdt, osLdt, oeLdt);
	}

	/**
	 * Returns the duration (int) between a given date and current date
	 * 
	 * @param date Start inclusive
	 * @param type (String) Has to be either 'days', 'hours', 'minutes',
	 *             'seconds', 'millis' or 'nanos'
	 * @return the duration (int) between a given date and current date
	 */
	public static Integer elapsedTime(Date date, ElapsedTimeType type) {
		return LocalDateTimeUtils.elapsedTime(LocalDateTimeUtils.convert(date), type);
	}

	/**
	 * Returns the duration (int) between a given date and current date
	 * 
	 * @param date Start inclusive
	 * @param type (String) Has to be either 'days', 'hours', 'minutes',
	 *             'seconds', 'millis' or 'nanos'
	 * @return the duration (int) between a given date and current date
	 */
	public static Integer elapsedTime(Date date, Date time, ElapsedTimeType type) {
		return LocalDateTimeUtils.elapsedTime(LocalDateTimeUtils.convert(date, time), type);
	}

	/**
	 * Returns the duration (long) between two dates using
	 * {@code java.time.temporal.ChronoUnit}
	 * 
	 * @param dateOne    Start inclusive
	 * @param dateTwo    End exclusive
	 * @param chronoUnit A {@code ChronoUnit} value
	 * @return A long value that represents the difference between two dates
	 *         determined by the chronoUnit value provided
	 */
	public static Long elapsedTime(Date dateOne, Date dateTwo, ChronoUnit chronoUnit) {
		val ldtOne = LocalDateTimeUtils.convert(dateOne);
		val ldtTwo = LocalDateTimeUtils.convert(dateTwo);
		return LocalDateTimeUtils.elapsedTime(ldtOne, ldtTwo, chronoUnit);
	}
	
	/**
	 * Add years to current date
	 * 
	 * @param years (Long) years
	 * @return The current date plus the indicated years
	 */
	public static Date plusYear(Long years) {
		return plusYear(new Date(), years);
	}
	
	/**
	 * Add years to a date
	 * 
	 * @param date  Given date
	 * @param years (Long) years
	 * @return The given date plus the indicated years
	 */
	public static Date plusYear(Date date, Long years) {
		val ldt = LocalDateUtils.convert(date);
		if (ldt != null && years != null) {
			val nextDate = ldt.plusYears(years);
			return convert(nextDate);
		}
		return null;
	}

	/**
	 * Subtracts years from current date
	 * 
	 * @param years (Long) years
	 * @return The current date minus the indicated years
	 */
	public static Date minusYear(Long years) {
		return minusYear(new Date(), years);
	}
	
	/**
	 * Subtracts years from a date
	 * 
	 * @param years (Long) years
	 * @return The given date minus the indicated years
	 */
	public static Date minusYear(Date date, Long years) {
		val ldt = LocalDateUtils.convert(date);
		if (ldt != null && years != null) {
			val beforeDate = ldt.minusYears(years);
			return convert(beforeDate);
		}
		return null;
	}

	/**
	 * Adds months to current date
	 * 
	 * @param months (Long) months
	 * @return The current date plus the indicated months
	 */
	public static Date plusMonth(Long months) {
		return plusMonth(new Date(), months);
	}
	
	/**
	 * Add months to a date
	 * 
	 * @param date   Given date
	 * @param months (Long) months
	 * @return The given date plus the indicated months
	 */
	public static Date plusMonth(Date date, Long months) {
		val ldt = LocalDateUtils.convert(date);
		if (ldt != null && months != null) {
			val nextDate = ldt.plusMonths(months);
			return convert(nextDate);
		}
		return null;
	}

	/**
	 * Subtracts months from current date
	 * 
	 * @param months (Long) months
	 * @return The current date minus the indicated months
	 */
	public static Date minusMonth(Long months) {
		return minusMonth(new Date(), months);
	}
	
	/**
	 * Subtracts months from a date
	 * 
	 * @param months (Long) months
	 * @return The given date minus the indicated months
	 */
	public static Date minusMonth(Date date, Long months) {
		val ldt = LocalDateUtils.convert(date);
		if (ldt != null && months != null) {
			val beforeDate = ldt.minusMonths(months);
			return convert(beforeDate);
		}
		return null;
	}

	/**
	 * Add days to current date
	 * 
	 * @param days (Long) days
	 * @return The current date plus the indicated days
	 */
	public static Date plusDays(Long days) {
		return plusDays(new Date(), days);
	}
	
	/**
	 * Add days to a date
	 * 
	 * @param date Given date
	 * @param days (Long) days
	 * @return The given date plus the indicated days
	 */
	public static Date plusDays(Date date, Long days) {
		val ldt = LocalDateTimeUtils.convert(date);
		if (ldt != null && days != null) {
			val nextDate = ldt.plusDays(days);
			return convert(nextDate);
		}
		return null;
	}

	/**
	 * Subtracts days from current date
	 * 
	 * @param days (Long) days
	 * @return The current date minus the indicated days
	 */
	public static Date minusDays(Long days) {
		return minusDays(new Date(), days);
	}
	
	/**
	 * Subtracts days from a date
	 * 
	 * @param days (Long) days
	 * @return The given date minus the indicated days
	 */
	public static Date minusDays(Date date, Long days) {
		val ldt = LocalDateUtils.convert(date);
		if (ldt != null && days != null) {
			val beforeDate = ldt.minusDays(days);
			return convert(beforeDate);
		}
		return null;
	}

	/**
	 * Add hours to current date
	 * 
	 * @param hours (Long) hours
	 * @return The current date plus the indicated hours
	 */
	public static Date plusHours(Long hours) {
		return plusHours(new Date(), hours);
	}
		
	/**
	 * Add hours to a date
	 * 
	 * @param date  Given date
	 * @param hours (Long) hours
	 * @return The given date plus the indicated hours
	 */
	public static Date plusHours(Date date, Long hours) {
		val ldt = LocalDateTimeUtils.convert(date);
		if (ldt != null && hours != null) {
			val nextTime = ldt.plusHours(hours);
			return convert(nextTime);
		}
		return null;
	}

	/**
	 * Subtracts hours from current date
	 * 
	 * @param hours (Long) hours
	 * @return The current date minus the indicated hours
	 */
	public static Date minusHours(Long hours) {
		return minusHours(new Date(), hours);
	}
	
	/**
	 * Subtracts hours from a date
	 * 
	 * @param hours (Long) hours
	 * @return The given date minus the indicated hours
	 */
	public static Date minusHours(Date date, Long hours) {
		val ldt = LocalDateTimeUtils.convert(date);
		if (ldt != null && hours != null) {
			val beforeDate = ldt.minusHours(hours);
			return convert(beforeDate);
		}
		return null;
	}

	/**
	 * Add minutes to current date
	 * 
	 * @param minutes (Long) minutes
	 * @return The current date plus the indicated minutes
	 */
	public static Date plusMinutes(Long minutes) {
		return plusMinutes(new Date(), minutes);
	}
	
	/**
	 * Add minutes to a date
	 * 
	 * @param date    Given date
	 * @param minutes (Long) minutes
	 * @return The given date plus the indicated minutes
	 */
	public static Date plusMinutes(Date date, Long minutes) {
		val ldt = LocalDateTimeUtils.convert(date);
		if (ldt != null && minutes != null) {
			val nextTime = ldt.plusMinutes(minutes);
			return convert(nextTime);
		}
		return null;
	}

	/**
	 * Subtracts minutes from current date
	 * 
	 * @param minutes (Long) minutes
	 * @return The current date minus the indicated minutes
	 */
	public static Date minusMinutes(Long minutes) {
		return minusMinutes(new Date(), minutes);
	}
	
	/**
	 * Subtracts minutes from a date
	 * 
	 * @param minutes (Long) minutes
	 * @return The given date minus the indicated minutes
	 */
	public static Date minusMinutes(Date date, Long minutes) {
		val ldt = LocalDateTimeUtils.convert(date);
		if (ldt != null && minutes != null) {
			val beforeDate = ldt.minusMinutes(minutes);
			return convert(beforeDate);
		}
		return null;
	}

	/**
	 * Add seconds to current date
	 * 
	 * @param seconds (Long) seconds
	 * @return The current date plus the indicated seconds
	 */
	public static Date plusSeconds(Long seconds) {
		return plusSeconds(new Date(), seconds);
	}
	
	/**
	 * Add seconds to a date
	 * 
	 * @param date    Given date
	 * @param seconds (Long) seconds
	 * @return The given date plus the indicated seconds
	 */
	public static Date plusSeconds(Date date, Long seconds) {
		val ldt = LocalDateTimeUtils.convert(date);
		if (ldt != null && seconds != null) {
			val nextTime = ldt.plusSeconds(seconds);
			return convert(nextTime);
		}
		return null;
	}

	/**
	 * Subtracts seconds from current date
	 * 
	 * @param seconds (Long) seconds
	 * @return The current date minus the indicated seconds
	 */
	public static Date minusSeconds(Long seconds) {
		return minusSeconds(new Date(), seconds);
	}
	
	/**
	 * Subtracts seconds from a date
	 * 
	 * @param seconds (Long) seconds
	 * @return The given date minus the indicated seconds
	 */
	public static Date minusSeconds(Date date, Long seconds) {
		val ldt = LocalDateTimeUtils.convert(date);
		if (ldt != null && seconds != null) {
			val beforeDate = ldt.minusSeconds(seconds);
			return convert(beforeDate);
		}
		return null;
	}
	
	/**
	 * Returns true if the date represents a weekend
	 * 
	 * @param date
	 * @return
	 */
	public static boolean isWeekend(Date date) {
		val localDate = LocalDateUtils.convert(date);
		if (localDate != null) {
			val weekend = EnumSet.of(DayOfWeek.SATURDAY, DayOfWeek.SUNDAY);
			return weekend.contains(localDate.getDayOfWeek());
		}
		return false;
	}

	public static Integer hour(Date date) {
		val time = formatDate(date, DateFormatter.T12H);
		if (time != null) {
			val splitted = time.split(":");
			return IntegerUtils.valueOf(splitted[0]);
		}
		return null;
	}
	
	/**
	 * Sorts a list of date
	 * 
	 * @param dateList
	 * @return
	 */
	public static List<Date> sortDate(List<Date> dateList) {
		if (dateList != null) {
			return dateList.stream().sorted().toList();
		}
		return Collections.emptyList();
	}

}