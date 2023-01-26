package mx.softixx.cis.common.core.datetime;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import lombok.val;
import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "LocalDateTimeUtils")
public final class LocalDateTimeUtils {

	private LocalDateTimeUtils() {
	}

	public static LocalDateTime convert(LocalDate ld, LocalTime lt) {
		if (ld != null && lt != null) {
			return LocalDateTime.of(ld, lt);
		}
		return null;
	}

	public static LocalDateTime convert(LocalDate ld) {
		return convert(ld, true);
	}
	
	public static LocalDateTime convert(LocalDate ld, boolean useCurrentTime) {
		if (ld != null) {
			return useCurrentTime ? ld.atTime(LocalTime.now()) : ld.atTime(LocalTime.MIN);
		}
		return null;
	}

	public static LocalDateTime convert(LocalTime lt) {
		if (lt != null) {
			return lt.atDate(LocalDate.now());
		}
		return null;
	}

	public static LocalDateTime convert(Date date) {
		val ld = LocalDateUtils.convert(date);
		val lt = LocalTimeUtils.convert(date);

		if (ld != null && lt != null) {
			return LocalDateTime.of(ld, lt);
		}
		return null;
	}

	public static LocalDateTime convert(Date date, Date time) {
		val ld = LocalDateUtils.convert(date);
		val lt = LocalTimeUtils.convert(time);

		if (ld != null && lt != null) {
			return LocalDateTime.of(ld, lt);
		}
		return null;
	}

	public static LocalDateTime convert(java.sql.Timestamp timestamp) {
		if (timestamp != null) {
			return timestamp.toLocalDateTime();
		}
		return null;
	}

	public static LocalDateTime parse(String date, DateFormatter formatter) {
		try {
			if (date != null && formatter != null) {
				val format = DateTimeFormatter.ofPattern(formatter.getFormat());
				return LocalDateTime.parse(date, format);
			}
		} catch (DateTimeParseException e) {
			log.error("#parse error", e.getMessage());
		}
		return null;
	}

	/**
	 * Returns the UTC {@code LocalDateTime}
	 * 
	 * @return the UTC {@code LocalDateTime}
	 */
	public static LocalDateTime convertToUTC() {
		try {

			val zdt = ZonedDateTime.now(ZoneOffset.UTC);
			return zdt.toLocalDateTime();

		} catch (Exception e) {
			log.error("DateTimeUtils#localDateTimeUTC error > {}", e.getMessage());
		}
		return null;
	}

	/**
	 * Converts {@code LocalDateTime} to UTC
	 * 
	 * @param ldt {@code java.time.LocalDateTime}
	 * @return {@code LocalDateTime} in UTC
	 */
	public static LocalDateTime convertToUTC(LocalDateTime ldt) {
		if (ldt != null) {
			return ldt.atZone(ZoneId.systemDefault()).withZoneSameInstant(ZoneOffset.UTC).toLocalDateTime();
		}
		return null;
	}

	/**
	 * Converts from UTC to {@code LocalDateTime}
	 * 
	 * @param ldt {@code java.time.LocalDateTime}
	 * @return {@code LocalDateTime} from UTC
	 */
	public static LocalDateTime convertFromUTC(LocalDateTime ldt) {
		val timeZone = TimeZoneUtils.getTimeZone(DateUtils.formatDate(DateFormatter.DATE_TIME_T_FORMAT));
		return convertFromUTC(ldt, timeZone);
	}

	/**
	 * Converts from UTC to {@code LocalDateTime} using a given timezone
	 * 
	 * @param ldt      {@code java.time.LocalDateTime}
	 * @param timezone String
	 * @return {@code LocalDateTime} from UTC
	 */
	public static LocalDateTime convertFromUTC(LocalDateTime ldt, String timeZone) {
		if (ldt != null && timeZone != null) {
			return ldt.atZone(ZoneOffset.UTC).withZoneSameInstant(ZoneId.of(timeZone)).toLocalDateTime();
		}
		return null;
	}

	public static boolean isBefore(Date dateOne, Date dateTwo) {
		return isBefore(convert(dateOne), convert(dateTwo));
	}

	public static boolean isBefore(LocalDateTime ldtOne, LocalDateTime ldtTwo) {
		if (ldtOne != null && ldtTwo != null) {
			return ldtOne.isBefore(ldtTwo);
		}
		return false;
	}

	public static boolean isAfter(Date dateOne, Date dateTwo) {
		return isAfter(convert(dateOne), convert(dateTwo));
	}

	public static boolean isAfter(LocalDateTime ldtOne, LocalDateTime ldtTwo) {
		if (ldtOne != null && ldtTwo != null) {
			return ldtOne.isAfter(ldtTwo);
		}
		return false;
	}

	public static boolean isEqual(Date dateOne, Date dateTwo) {
		return isEqual(convert(dateOne), convert(dateTwo));
	}

	public static boolean isEqual(LocalDateTime ldtOne, LocalDateTime ldtTwo) {
		if (ldtOne != null && ldtTwo != null) {
			return ldtOne.equals(ldtTwo);
		}
		return false;
	}

	public static boolean isBeforeOrEqual(Date dateOne, Date dateTwo) {
		return isBeforeOrEqual(convert(dateOne), convert(dateTwo));
	}

	public static boolean isBeforeOrEqual(LocalDateTime ldtOne, LocalDateTime ldtTwo) {
		if (ldtOne != null && ldtTwo != null) {
			return (ldtOne.isBefore(ldtTwo) || ldtOne.equals(ldtTwo));
		}
		return false;
	}

	public static boolean isAfterOrEqual(Date dateOne, Date dateTwo) {
		return isAfterOrEqual(convert(dateOne), convert(dateTwo));
	}

	public static boolean isAfterOrEqual(LocalDateTime ldtOne, LocalDateTime ldtTwo) {
		if (ldtOne != null && ldtTwo != null) {
			return (ldtOne.isAfter(ldtTwo) || ldtOne.equals(ldtTwo));
		}
		return false;
	}

	public static boolean isInRange(String startDateTime, String endDateTime) {
		val now = DateUtils.formatDate(new Date(), DateFormatter.DATE_TIME_FULL_FORMAT);
		return isInRange(now, startDateTime, endDateTime);
	}

	public static boolean isInRange(String startDateTime, String endDateTime, DateFormatter fomatter) {
		val now = DateUtils.formatDate(new Date(), fomatter);
		return isInRange(now, startDateTime, endDateTime);
	}

	public static boolean isInRange(String checkDate, String start, String end) {
		val toCheck = parse(checkDate, DateFormatter.DATE_TIME_FULL_FORMAT);
		val startInterval = parse(start, DateFormatter.DATE_TIME_FULL_FORMAT);
		val endInterval = parse(end, DateFormatter.DATE_TIME_FULL_FORMAT);
		return isInRange(toCheck, startInterval, endInterval);
	}

	public static boolean isInRange(String checkDate, String start, String end, DateFormatter formatter) {
		val toCheck = parse(checkDate, formatter);
		val startInterval = parse(start, formatter);
		val endInterval = parse(end, formatter);
		return isInRange(toCheck, startInterval, endInterval);
	}

	public static boolean isInRange(Date dateTimeToCheck, Date startDateTime, Date endDateTime) {
		val toCheck = convert(dateTimeToCheck);
		val startInterval = convert(startDateTime);
		val endInterval = convert(endDateTime);
		return isInRange(toCheck, startInterval, endInterval);
	}

	public static boolean isInRange(LocalDateTime toCheck, LocalDateTime startInterval, LocalDateTime endInterval) {
		if (toCheck != null && startInterval != null && endInterval != null) {
			return isAfterOrEqual(toCheck, startInterval) && isBeforeOrEqual(toCheck, endInterval);
		}
		return false;
	}

	public static boolean isPast(LocalDateTime ldt) {
		return isBefore(ldt, LocalDateTime.now());
	}

	public static boolean isPastOrPresent(LocalDateTime ldt) {
		return isEqual(ldt, LocalDateTime.now()) || isBefore(ldt, LocalDateTime.now());
	}
	
	public static boolean isFutureOrPresent(LocalDateTime ldt) {
		return isEqual(ldt, LocalDateTime.now()) || isAfter(ldt, LocalDateTime.now());
	}
	
	public static boolean isFuture(LocalDateTime ldt) {
		return isAfter(ldt, LocalDateTime.now());
	}

	public static boolean hasOverlap(LocalDateTime sldt1, LocalDateTime eldt1, LocalDateTime sldt2,
			LocalDateTime eldt2) {
		return (isAfterOrEqual(sldt1, sldt2) && isBeforeOrEqual(sldt1, eldt2))
				|| (isAfterOrEqual(eldt2, eldt1) && isBeforeOrEqual(sldt2, eldt1));
	}

	/**
	 * Returns the duration (int) between a given date (LocalDateTime) and current
	 * date
	 * 
	 * @param ldt  Start inclusive
	 * @param type (String) Has to be either 'days', 'hours', 'minutes', 'seconds',
	 *             'millis' or 'nanos'
	 * @return the duration (int) between a given date and current date
	 */
	public static Integer elapsedTime(LocalDateTime ldt, ElapsedTimeType type) {
		val duration = DurationUtils.duration(ldt, LocalDateTime.now());
		return DurationUtils.elapsedTime(duration, type);
	}

	/**
	 * Returns the duration (long) between two dates using
	 * {@code java.time.temporal.ChronoUnit}
	 * 
	 * @param from       Start inclusive
	 * @param to         End exclusive
	 * @param chronoUnit A {@code ChronoUnit} value
	 * @return A long value that represents the difference between two dates
	 *         determined by the chronoUnit value provided
	 */
	public static Long elapsedTime(LocalDateTime from, LocalDateTime to, ChronoUnit chronoUnit) {
		if (from != null && to != null && chronoUnit != null) {
			return switch (chronoUnit) {
			case YEARS -> ChronoUnit.YEARS.between(from, to);
			case MONTHS -> ChronoUnit.MONTHS.between(from, to);
			case WEEKS -> ChronoUnit.WEEKS.between(from, to);
			case DAYS -> ChronoUnit.DAYS.between(from, to);
			case HOURS -> ChronoUnit.HOURS.between(from, to);
			case MINUTES -> ChronoUnit.MINUTES.between(from, to);
			case SECONDS -> ChronoUnit.SECONDS.between(from, to);
			case MILLIS -> ChronoUnit.MILLIS.between(from, to);
			case NANOS -> ChronoUnit.NANOS.between(from, to);
			default -> null;
			};
		}
		return null;
	}

}