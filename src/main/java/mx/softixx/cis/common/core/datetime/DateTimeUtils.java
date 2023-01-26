package mx.softixx.cis.common.core.datetime;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

import lombok.val;
import lombok.extern.slf4j.Slf4j;
import mx.softixx.cis.common.core.data.ValueUtils;
import mx.softixx.cis.common.core.message.CustomMessage;
import mx.softixx.cis.common.core.validator.ValidatorUtils;

/**
 * Date & Time utility class
 * 
 * @author Maikel Guerra Ferrer mguerraferrer@gmail.com
 *
 */
@Slf4j(topic = "DateTimeUtils")
public final class DateTimeUtils {

	private DateTimeUtils() {
	}
	
	public static final String DATE_RANGE_SEPARATOR = " - ";
	public static final String LAST_TIME_OF_DATE = "23:59:59";
	public static final String TIME_AM = "AM";
	public static final String TIME_PM = "PM";

	/**
	 * Returns current day
	 * 
	 * @return The day-of-month, from 1 to 31
	 */
	public static Integer currentDay() {
		return LocalDate.now().getDayOfMonth();
	}
	
	/**
	 * Returns the day of month
	 * 
	 * @param date java.util.Date
	 * @return The day of month (from 1 to 31)
	 */
	public static Integer day(Date date) {
		val ld = LocalDateUtils.convert(date);
		if (ld != null) {
			return ld.getDayOfMonth();
		}
		return null;
	}

	/**
	 * Returns current month
	 * 
	 * @return The month-of-year, from 1 to 12
	 */
	public static Integer currentMonth() {
		return LocalDate.now().getMonthValue();
	}
	
	/**
	 * Returns the day of month of the given date
	 * 
	 * @param date java.util.Date
	 * @return The month of te year (from 1 to 12)
	 */
	public static Integer month(Date date) {
		val ldt = LocalDateUtils.convert(date);
		if (ldt != null) {
			return ldt.getMonthValue();
		}
		return null;
	}

	/**
	 * Returns current year
	 * 
	 * @return The actual year
	 */
	public static Integer currentYear() {
		return LocalDate.now().getYear();
	}
	
	/**
	 * Returns de year of the given date
	 * 
	 * @param date java.util.Date
	 * @return The year
	 */
	public static Integer year(final Date date) {
		val ld = LocalDateUtils.convert(date);
		if (ld != null) {
			return ld.getYear();
		}
		return null;
	}

	/**
	 * Returns current year with 2 digits
	 * 
	 * @return The actual year with 2 digits
	 */
	public static String currentYear2D() {
		return Year.now().format(DateTimeFormatter.ofPattern("yy"));
	}
	
	/**
	 * Returns de year (two digits) of the given date
	 * 
	 * @param date java.util.Date
	 * @return The year (two digits)
	 */
	public static String year2D(final Date date) {
		if (date != null) {
			val ld = LocalDateUtils.convert(date);
			if (ld != null) {
				return ld.format(DateTimeFormatter.ofPattern("yy"));
			}
		}
		return null;
	}

	/**
	 * Returns a current timestamp in String format. <br>
	 * <b>Allows only the following formats:</b> <br>
	 * DateFormatter.DATE_TIME_FULL_FORMAT and DateFormatter.DATE_TIME_DB_FULL_FORMAT
	 * 
	 * @param formatter {@link DateFormatter}
	 * @return Current timestamp in String format
	 * @see DateFormatter
	 */
	public static String currentTimestamp(final DateFormatter formatter) {
		try {
			String timestamp = null;
			if (formatter != null && (formatter.equals(DateFormatter.DATE_TIME_FULL_FORMAT)
					|| formatter.equals(DateFormatter.DATE_TIME_DB_FULL_FORMAT))) {
				val dtf = DateTimeFormatter.ofPattern(formatter.getFormat());
				timestamp = dtf.format(LocalDateTime.now());
			}
			return timestamp;
		} catch (DateTimeException | IllegalArgumentException e) {
			log.error("#currentTimeStamp error {}", e);
		}
		return null;
	}

	/**
	 * Convert Date (java.util.date) to Calendar (java.util.calendar)
	 * 
	 * @param date java.util.Date
	 * @return A calendar from date
	 */
	public static Calendar dateToCalendar(final Date date) {
		if (date != null) {
			val calendar = Calendar.getInstance();
			calendar.setTime(date);

			return calendar;
		}
		return null;
	}

	/**
	 * Convert Calendar (java.util.Calendar) to Date (java.util.Date)
	 * 
	 * @param calendar Calendar (java.util.Calendar)
	 * @return A date from calendar
	 */
	public static Date calendarToDate(final Calendar calendar) {
		if (calendar != null) {
			return calendar.getTime();
		}
		return null;
	}

	/**
	 * Returns the text (from properties) that represents the name of the month or
	 * the abbreviated month name
	 * 
	 * @param date         java.util.Date
	 * @param abbreviation Boolean
	 * @return The name of the month or the abbreviated month name
	 */
	public static String month(Date date, boolean abbreviation) {
		val month = month(date);
		return month(month, abbreviation);
	}

	public static String month(Integer month, boolean showAbbreviation) {
		if (month != null) {
			val monthName = switch (month) {
				case 1 -> "date.text.month.january";
				case 2 -> "date.text.month.february";
				case 3 -> "date.text.month.march";
				case 4 -> "date.text.month.april";
				case 5 -> "date.text.month.may";
				case 6 -> "date.text.month.june";
				case 7 -> "date.text.month.july";
				case 8 -> "date.text.month.august";
				case 9 -> "date.text.month.september";
				case 10 -> "date.text.month.october";
				case 11 -> "date.text.month.november";
				case 12 -> "date.text.month.december";
				default -> null;
			};
			
			if (monthName != null && showAbbreviation) {
				return monthName.concat(".short");
			}
		}
		return null;
	}

	/**
	 * Returns java.time.Month that representing of month name
	 * 
	 * @param monthName String
	 * @return A month of the year, such as 'July'
	 */
	public static Month month(String monthName) {
		if (ValidatorUtils.isNotEmpty(monthName)) {
			val month = MonthName.valueOf(monthName.toUpperCase());
			return switch (month) {
				case ENERO, JANUARY, ENE, JAN -> Month.JANUARY;
				case FEBRERO, FEBRUARY, FEB -> Month.FEBRUARY;
				case MARZO, MARCH, MAR -> Month.MARCH;
				case ABRIL, APRIL, ABR, APR -> Month.APRIL;
				case MAYO, MAY -> Month.MAY;
				case JUNIO, JUNE, JUN -> Month.JUNE;
				case JULIO, JULY, JUL -> Month.JULY;
				case AGOSTO, AUGUST, AUG -> Month.AUGUST;
				case SEPTIEMBRE, SEPTEMBER, SEP -> Month.SEPTEMBER;
				case OCTUBRE, OCTOBER, OCT -> Month.OCTOBER;
				case NOVIEMBRE, NOVEMBER, NOV -> Month.NOVEMBER;
				case DICIEMBRE, DECEMBER, DIC, DEC -> Month.DECEMBER;
				default -> null;
			};
		}
		return null;
	}

	/**
	 * Returns the month number from 1 to 12
	 * 
	 * @param monthName String
	 * @return A month of the year
	 */
	public static Integer monthNumber(String monthName) {
		val month = month(monthName);
		if (month != null) {
			return month.getValue();
		}
		return null;
	}

	public static CustomMessage fullDate() {
		return fullDate(new Date());
	}

	public static CustomMessage fullDate( LocalDateTime ldt) {
		val date = DateUtils.convert(ldt);
		return fullDate(date);
	}

	public static CustomMessage fullDate(Date date) {
		val localDate = LocalDateUtils.convert(date);
		if (localDate != null) {
			val dayOfWeek = localDate.getDayOfWeek();
			val day = switch (dayOfWeek) {
				case MONDAY -> "date.text.day.monday";
				case TUESDAY -> "date.text.day.tuesday";
				case WEDNESDAY -> "date.text.day.wednesday";
				case THURSDAY -> "date.text.day.thursday";
				case FRIDAY -> "date.text.day.friday";
				case SATURDAY -> "date.text.day.saturday";
				case SUNDAY -> "date.text.day.sunday";
				default -> null;
			};
			
			val monthOfYear = localDate.getMonth();
			val month = switch (monthOfYear) {
				case JANUARY -> "date.text.month.january";
				case FEBRUARY -> "date.text.month.february";
				case MARCH -> "date.text.month.march";
				case APRIL -> "date.text.month.april";
				case MAY -> "date.text.month.may";
				case JUNE -> "date.text.month.june";
				case JULY -> "date.text.month.july";
				case AUGUST -> "date.text.month.august";
				case SEPTEMBER -> "date.text.month.september";
				case OCTOBER -> "date.text.month.october";
				case NOVEMBER -> "date.text.month.november";
				case DECEMBER -> "date.text.month.december";
				default -> null;	
			};
			
			val dayOfMonth = localDate.getDayOfMonth();
			val year = localDate.getYear();
			
			val params = new String[] { day, String.valueOf(dayOfMonth), month, String.valueOf(year) };
			return CustomMessage.builder().key("date.text.date.full").params(params).build();
		}
		return null;
	}

	public static CustomMessage shortDate() {
		val localDate = LocalDateUtils.convert(new Date());
		if (localDate != null) {
			val day = localDate.getDayOfMonth();
			val dayOfMonth = day >= 10 ? String.valueOf(day) : "0" + day;
			
			val month = localDate.getMonthValue();
			val monthStr = month >= 10 ? String.valueOf(month) : "0" + month;
			
			val year = localDate.getYear();
			
			val params = new String[] { dayOfMonth, monthStr, String.valueOf(year) };
			return CustomMessage.builder().key("date.text.date.short").params(params).build();
		}
		return null;
	}

	public static String firstLastDayOfMonthRange() {
		return firstLastDayOfMonthRange(DateFormatter.DATE_SIMPLE_FORMAT.getFormat(), DATE_RANGE_SEPARATOR);
	}

	public static String firstLastDayOfMonthRange(String format, String separator) {
		if (ValidatorUtils.isNotEmpty(format) && ValidatorUtils.isNotEmpty(separator)) {
			val firstDay = DateUtils.convert(LocalDateUtils.firstDayOfMonth());
			val lastDay = DateUtils.convert(LocalDateUtils.lastDayOfMonth());
			if (firstDay != null && lastDay != null) {
				val sdf = new SimpleDateFormat(format);
				return sdf.format(firstDay) + separator + sdf.format(lastDay);
			}
		}
		return ValueUtils.EMPTY;
	}

	public static String firstActualDayOfMonthRange() {
		return firstActualDayOfMonthRange(DateFormatter.DATE_SIMPLE_FORMAT.getFormat(), DATE_RANGE_SEPARATOR);
	}

	public static String firstActualDayOfMonthRange(String format, String separator) {
		if (ValidatorUtils.isNotEmpty(format) && ValidatorUtils.isNotEmpty(separator)) {
			val firstDay = DateUtils.convert(LocalDateUtils.firstDayOfMonth());
			val today = DateUtils.convert(LocalDateUtils.convert(new Date()));
			if (firstDay != null && today != null) {
				val sdf = new SimpleDateFormat(format);
				return sdf.format(firstDay) + separator + sdf.format(today);
			}
		}
		return ValueUtils.EMPTY;
	}
	
	/**
	 * Returns the duration (int) between a given date (Timestamp) and current date
	 * 
	 * @param timestamp Start inclusive
	 * @param type      (String) Has to be either 'days', 'hours', 'minutes',
	 *                  'seconds', 'millis' or 'nanos'
	 * @return the duration (int) between a given date and current date
	 */
	public static Integer elapsedTime(Timestamp timestamp, ElapsedTimeType type) {
		return LocalDateTimeUtils.elapsedTime(LocalDateTimeUtils.convert(timestamp), type);
	}
	
	/**
	 * Returns true if the given year is a leap year
	 * 
	 * @param year Four-number year
	 * @return true/false
	 */
	public static boolean isLeapYear(Integer year) {
		if (year != null) {
			return Year.isLeap(year);
		}
		return false;
	}

	public static String formatDate(java.sql.Timestamp timestamp, DateFormatter formatter) {
		if (timestamp != null && formatter != null) {
			try {
				val sdf = new SimpleDateFormat(formatter.getFormat());
				return sdf.format(timestamp);
			} catch (IllegalArgumentException e) {
				log.error("#formatDate(java.sql.Timestamp) error {}", e);
			}
		}
		return ValueUtils.EMPTY;
	}

	public static java.sql.Date sqlDate(final Date date) {
		try {

			if (date != null) {
				return new java.sql.Date(date.getTime());
			}

		} catch (Exception e) {
			log.error("#sqlDate(Date) error {}", e);
		}
		return null;
	}

	public static java.sql.Time sqlTime(Date date) {
		if (date != null) {
			return new java.sql.Time(date.getTime());
		}
		return null;
	}

	public static java.sql.Timestamp timestamp(Date javaDate) {
		if (javaDate != null) {
			return new java.sql.Timestamp(javaDate.getTime());
		}
		return null;
	}

	public static java.sql.Timestamp timestamp(LocalDate javaLd) {
		if (ValidatorUtils.isNotEmpty(javaLd)) {
			return java.sql.Timestamp.valueOf(javaLd.atStartOfDay());
		}
		return null;
	}

	public static java.sql.Timestamp timestamp(LocalDateTime javaLdt) {
		if (ValidatorUtils.isNotEmpty(javaLdt)) {
			return java.sql.Timestamp.valueOf(javaLdt);
		}
		return null;
	}

}