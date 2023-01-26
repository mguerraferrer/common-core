package mx.softixx.cis.common.core.datetime;

import java.time.DateTimeException;
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.EnumSet;

import lombok.val;
import lombok.extern.slf4j.Slf4j;

/**
 * This class should only be used with {@code java.util.Date},
 * {@code java.time.LocalTime} or {@code java.time.LocalDateTime}
 * 
 * @author Maikel Guerra Ferrer - mguerraferrer@gmail.com
 *
 */
@Slf4j(topic = "DurationUtils")
public final class DurationUtils {

	private DurationUtils() {
	}

	/**
	 * Returns the duration between two {@code java.time.LocalDateTime} objects
	 * 
	 * @param ldtOne Start inclusive
	 * @param ldtTwo End exclusive
	 * @return the {@code java.time.Duration} between two dates
	 */
	public static Duration duration(LocalDateTime ldtOne, LocalDateTime ldtTwo) {
		try {
			if (ldtOne != null && ldtTwo != null) {
				return Duration.between(ldtOne, ldtTwo);
			}
		} catch (DateTimeException | ArithmeticException e) {
			log.error("#duration error", e.getMessage());
		}
		return null;
	}

	/**
	 * Returns the duration between two {@code java.time.LocalTime} objects
	 * 
	 * @param ltOne Start inclusive
	 * @param ltTwo End exclusive
	 * @return the {@code java.time.Duration} between two date
	 */
	public static Duration duration(LocalTime ltOne, LocalTime ltTwo) {
		try {
			if (ltOne != null && ltTwo != null) {
				return Duration.between(ltOne, ltTwo);
			}
		} catch (DateTimeException | ArithmeticException e) {
			log.error("#duration error", e.getMessage());
		}
		return null;
	}

	/**
	 * Returns the duration between two dates
	 * 
	 * @param dateOne Start inclusive
	 * @param dateTwo End exclusive
	 * @return the {@code java.time.Duration} between two dates
	 */
	public static Duration duration(Date dateOne, Date dateTwo) {
		return duration(LocalDateTimeUtils.convert(dateOne), LocalDateTimeUtils.convert(dateTwo));
	}

	/**
	 * Returns the duration (int) in days between two dates (LocalDateTime)
	 * 
	 * @param ldtOne Start inclusive
	 * @param ldtTwo End exclusive
	 * @return the duration (int) in days between two dates
	 */
	public static Integer toDays(LocalDateTime ldtOne, LocalDateTime ldtTwo) {
		val duration = duration(ldtOne, ldtTwo);
		if (duration != null) {
			Long durationInDays = duration.toDays();
			return durationInDays.intValue();
		}
		return null;
	}

	/**
	 * Returns the duration (int) in days between two dates
	 * 
	 * @param dateOne Start inclusive
	 * @param dateTwo End exclusive
	 * @return the duration (int) in days between two dates
	 */
	public static Integer toDays(Date dateOne, Date dateTwo) {
		val duration = duration(dateOne, dateTwo);
		if (duration != null) {
			Long durationInDays = duration.toDays();
			return durationInDays.intValue();
		}
		return null;
	}

	/**
	 * Returns the duration (int) in days between two dates without weekends
	 * 
	 * @param dateOne Start inclusive
	 * @param dateTwo End exclusive
	 * @return the duration (int) in days between two dates without weekends
	 */
	public static Integer toDaysWithoutWeekend(LocalDateTime ldtOne, LocalDateTime ldtTwo) {
		if (ldtOne != null && ldtTwo != null) {
			var count = 0;
			val weekend = EnumSet.of(DayOfWeek.SATURDAY, DayOfWeek.SUNDAY);
			while (ldtOne.isBefore(ldtTwo)) {
				if (!weekend.contains(ldtOne.getDayOfWeek())) {
					// It is not weekend
					count++;
				}
				// Increment a day
				ldtOne = ldtOne.plusDays(1);
			}
			return count;
		}
		return null;
	}

	/**
	 * Returns the duration (int) in days between two dates without weekends
	 * 
	 * @param dateOne Start inclusive
	 * @param dateTwo End exclusive
	 * @return the duration (int) in days between two dates without weekends
	 */
	public static Integer toDaysWithoutWeekend(Date dateOne, Date dateTwo) {
		return toDaysWithoutWeekend(LocalDateTimeUtils.convert(dateOne), LocalDateTimeUtils.convert(dateTwo));
	}

	/**
	 * Returns the duration (int) in hours between two dates (LocalDateTime)
	 * 
	 * @param ldtOne Start inclusive
	 * @param ldtTwo End exclusive
	 * @return the duration (int) in hours between two dates
	 */
	public static Integer toHours(LocalDateTime ldtOne, LocalDateTime ldtTwo) {
		val duration = duration(ldtOne, ldtTwo);
		if (duration != null) {
			Long durationInDays = duration.toHours();
			return durationInDays.intValue();
		}
		return null;
	}

	/**
	 * Returns the duration (int) in hours between two dates (LocalTime)
	 * 
	 * @param ltOne Start inclusive
	 * @param ltTwo End exclusive
	 * @return the duration (int) in hours between two dates
	 */
	public static Integer toHours(LocalTime ltOne, LocalTime ltTwo) {
		return toHours(LocalDateTimeUtils.convert(ltOne), LocalDateTimeUtils.convert(ltTwo));
	}

	/**
	 * Returns the duration (int) in hours between two dates
	 * 
	 * @param dateOne Start inclusive
	 * @param dateTwo End exclusive
	 * @return the duration (int) in hours between two dates
	 */
	public static Integer durationInHours(Date dateOne, Date dateTwo) {
		return toHours(LocalDateTimeUtils.convert(dateOne), LocalDateTimeUtils.convert(dateTwo));
	}

	/**
	 * Returns the duration (int) in minutes between two dates (LocalDateTime)
	 * 
	 * @param ldtOne Start inclusive
	 * @param ldtTwo End exclusive
	 * @return the duration (int) in minutes between two dates
	 */
	public static Integer toMinutes(LocalDateTime ldtOne, LocalDateTime ldtTwo) {
		val duration = duration(ldtOne, ldtTwo);
		if (duration != null) {
			Long durationInDays = duration.toMinutes();
			return durationInDays.intValue();
		}
		return null;
	}

	/**
	 * Returns the duration (int) in minutes between two dates (LocalTime)
	 * 
	 * @param ltOne Start inclusive
	 * @param ltTwo End exclusive
	 * @return the duration (int) in minutes between two dates
	 */
	public static Integer toMinutes(LocalTime ltOne, LocalTime ltTwo) {
		return toHours(LocalDateTimeUtils.convert(ltOne), LocalDateTimeUtils.convert(ltTwo));
	}

	/**
	 * Returns the duration (int) in minutes between two dates
	 * 
	 * @param dateOne Start inclusive
	 * @param dateTwo End exclusive
	 * @return the duration (int) in minutes between two dates
	 */
	public static Integer toMinutes(Date dateOne, Date dateTwo) {
		return toMinutes(LocalDateTimeUtils.convert(dateOne), LocalDateTimeUtils.convert(dateTwo));
	}

	/**
	 * Returns the duration (int) in seconds between two dates (LocalDateTime)
	 * 
	 * @param ldtOne Start inclusive
	 * @param ldtTwo End exclusive
	 * @return the duration (int) in seconds between two dates
	 */
	public static Integer toSeconds(LocalDateTime ldtOne, LocalDateTime ldtTwo) {
		val duration = duration(ldtOne, ldtTwo);
		if (duration != null) {
			Long durationInDays = duration.toSeconds();
			return durationInDays.intValue();
		}
		return null;
	}

	/**
	 * Returns the duration (int) in seconds between two dates (LocalTime)
	 * 
	 * @param ltOne Start inclusive
	 * @param ltTwo End exclusive
	 * @return the duration (int) in seconds between two dates
	 */
	public static Integer toSeconds(LocalTime ltOne, LocalTime ltTwo) {
		return toSeconds(LocalDateTimeUtils.convert(ltOne), LocalDateTimeUtils.convert(ltTwo));
	}

	/**
	 * Returns the duration (int) in seconds between two dates
	 * 
	 * @param dateOne Start inclusive
	 * @param dateTwo End exclusive
	 * @return the duration (int) in seconds between two dates
	 */
	public static Integer toSeconds(Date dateOne, Date dateTwo) {
		return toSeconds(LocalDateTimeUtils.convert(dateOne), LocalDateTimeUtils.convert(dateTwo));
	}

	/**
	 * Returns the duration (int) in milliseconds between two dates (LocalDateTime)
	 * 
	 * @param ldtOne Start inclusive
	 * @param ldtTwo End exclusive
	 * @return the duration (int) in milliseconds between two dates
	 */
	public static Integer toMillis(LocalDateTime ldtOne, LocalDateTime ldtTwo) {
		val duration = duration(ldtOne, ldtTwo);
		if (duration != null) {
			Long durationInDays = duration.toMillis();
			return durationInDays.intValue();
		}
		return null;
	}

	/**
	 * Returns the duration (int) in milliseconds between two dates (LocalTime)
	 * 
	 * @param ltOne Start inclusive
	 * @param ltTwo End exclusive
	 * @return the duration (int) in milliseconds between two dates
	 */
	public static Integer toMillis(LocalTime ltOne, LocalTime ltTwo) {
		return toMillis(LocalDateTimeUtils.convert(ltOne), LocalDateTimeUtils.convert(ltTwo));
	}

	/**
	 * Returns the duration (int) in milliseconds between two dates
	 * 
	 * @param dateOne Start inclusive
	 * @param dateTwo End exclusive
	 * @return the duration (int) in milliseconds between two dates
	 */
	public static Integer toMillis(Date dateOne, Date dateTwo) {
		return toMillis(LocalDateTimeUtils.convert(dateOne), LocalDateTimeUtils.convert(dateTwo));
	}
	
	public static Integer elapsedTime(Duration duration, ElapsedTimeType type) {
		if (duration != null && type != null) {
			var durationValue = switch (type) {
				case DAYS -> duration.toDays();
				case HOURS -> duration.toHours();
				case MINUTES -> duration.toMinutes();
				case SECONDS -> duration.toSeconds();
				case MILLIS -> duration.toMillis();
				case NANOS -> duration.toNanos();
				default -> null;
			};
			
			if (durationValue != null) {
				return durationValue.intValue();
			}
		}
		return 0;
	}

}