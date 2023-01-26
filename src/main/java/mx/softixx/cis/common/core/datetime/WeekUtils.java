package mx.softixx.cis.common.core.datetime;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;

import lombok.val;
import mx.softixx.cis.common.core.validator.ValidatorUtils;

public final class WeekUtils {
	
	private WeekUtils() {		
	}
	
	public static DayOfWeek dayOfWeek(WeekDay day) {
		if (day != null) {
			return switch (day) {
				case LUNES, MONDAY -> DayOfWeek.MONDAY;
				case MARTES, TUESDAY -> DayOfWeek.TUESDAY;
				case MIERCOLES, MIÉRCOLES, WEDNESDAY -> DayOfWeek.WEDNESDAY;
				case JUEVES, THURSDAY -> DayOfWeek.THURSDAY;
				case VIERNES, FRIDAY -> DayOfWeek.FRIDAY;
				case SABADO, SÁBADO, SATURDAY -> DayOfWeek.SATURDAY;
				case DOMINGO, SUNDAY -> DayOfWeek.SUNDAY;
				default -> null;
			};
		}
		return null;
	}

	public static String dayOfWeek(Date date) {
		val localDate = LocalDateUtils.convert(date);
		if (localDate != null) {
			return localDate.getDayOfWeek().name();
		}
		return null;
	}

	public static DayOfWeek getDayOfWeek(Date date) {
		val localDate = LocalDateUtils.convert(date);
		if (localDate != null) {
			return localDate.getDayOfWeek();
		}
		return null;
	}

	public static Date dayOfWeek(Date date, String type, WeekDay day) {
		return dayOfWeek(LocalDateUtils.convert(date), type, day);
	}

	public static Date dayOfWeek(LocalDate localDate, String type, WeekDay day) {
		if (localDate != null && ValidatorUtils.isNotEmpty(type) && day != null) {
			if (type.equalsIgnoreCase("First")) {
				return DateUtils.convert(localDate.with(TemporalAdjusters.firstInMonth(dayOfWeek(day))));
			} else if (type.equalsIgnoreCase("Second")) {
				return DateUtils.convert(localDate.with(TemporalAdjusters.firstInMonth(dayOfWeek(day))).plusDays(7));
			} else if (type.equalsIgnoreCase("Third")) {
				return DateUtils.convert(localDate.with(TemporalAdjusters.firstInMonth(dayOfWeek(day))).plusDays(14));
			} else if (type.equalsIgnoreCase("Last")) {
				return DateUtils.convert(localDate.with(TemporalAdjusters.lastInMonth(dayOfWeek(day))));
			}
		}
		return null;
	}
	
	public static String[] daysOfWeek() {
		val monday = "date.text.day.monday";
		val tuesday = "date.text.day.tuesday";
		val wednesday = "date.text.day.wednesday";
		val thursday = "date.text.day.thursday";
		val friday = "date.text.day.friday";
		val saturday = "date.text.day.saturday";
		val sunday = "date.text.day.sunday";
		return new String[] { monday, tuesday, wednesday, thursday, friday, saturday, sunday };
	}

	public static LocalDate nextWeek(final Date date) {
		val localDate = LocalDateUtils.convert(date);
		if (localDate != null) {
			val dayOfWeek = localDate.getDayOfWeek().getValue();
			return switch (dayOfWeek) {
				case 1 -> localDate.with(TemporalAdjusters.next(DayOfWeek.MONDAY));
				case 2 -> localDate.with(TemporalAdjusters.next(DayOfWeek.TUESDAY));
				case 3 -> localDate.with(TemporalAdjusters.next(DayOfWeek.WEDNESDAY));
				case 4 -> localDate.with(TemporalAdjusters.next(DayOfWeek.THURSDAY));
				case 5 -> localDate.with(TemporalAdjusters.next(DayOfWeek.FRIDAY));
				case 6 -> localDate.with(TemporalAdjusters.next(DayOfWeek.SATURDAY));
				case 7 -> localDate.with(TemporalAdjusters.next(DayOfWeek.SUNDAY));
				default -> null;
			};
		}
		return null;
	}

	public static LocalDate previousDay(Date date, DayOfWeek dayOfWeek) {
		val localDate = LocalDateUtils.convert(date);
		if (localDate != null && dayOfWeek != null) {
			return localDate.with(TemporalAdjusters.previous(dayOfWeek));
		}
		return null;
	}

	public static LocalDate nextDay(Date date, DayOfWeek dayOfWeek) {
		val localDate = LocalDateUtils.convert(date);
		if (localDate != null && dayOfWeek != null) {
			return localDate.with(TemporalAdjusters.next(dayOfWeek));
		}
		return null;
	}

	public static Integer compareDayOfWeek(Date dateOne, Date dateTwo) {
		val dowOne = getDayOfWeek(dateOne);
		val dowTwo = getDayOfWeek(dateTwo);
		if (dowOne != null && dowTwo != null) {
			return compareDayOfWeek(dowOne, dowTwo);
		}
		return null;
	}

	public static Integer compareDayOfWeek(DayOfWeek dowOne, DayOfWeek dowTwo) {
		if (dowOne != null && dowTwo != null) {
			return dowOne.compareTo(dowTwo);
		}
		return null;
	}

	public static boolean isValidWeekDay(DayOfWeek dow) {
		if (dow != null) {
			val currentDay = DayOfWeek.from(LocalDate.now());
			return currentDay.compareTo(dow) == 0;
		}
		return false;
	}
	
}