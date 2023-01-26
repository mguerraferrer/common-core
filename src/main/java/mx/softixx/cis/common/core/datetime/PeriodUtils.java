package mx.softixx.cis.common.core.datetime;

import java.time.LocalDate;
import java.time.Period;
import java.util.Date;

import lombok.val;

/**
 * This class should only be used with {@code java.util.Date} or
 * {@code java.time.LocalDate}
 * 
 * @author Maikel Guerra Ferrer - mguerraferrer@gmail.com
 *
 */
public final class PeriodUtils {

	private PeriodUtils() {
	}

	public static Period period(LocalDate ldOne, LocalDate ldTwo) {
		if (ldOne != null && ldTwo != null) {
			return Period.between(ldOne, ldTwo);
		}
		return null;
	}

	public static Integer inDays(LocalDate ldOne, LocalDate ldTwo) {
		val period = period(ldOne, ldTwo);
		if (period != null) {
			return period.getDays();
		}
		return null;
	}

	public static Integer inMonths(LocalDate ldOne, LocalDate ldTwo) {
		val period = period(ldOne, ldTwo);
		if (period != null) {
			return period.getMonths();
		}
		return null;
	}

	public static Integer inYears(LocalDate ldOne, LocalDate ldTwo) {
		val period = period(ldOne, ldTwo);
		if (period != null) {
			return period.getYears();
		}
		return null;
	}

	public static Period period(Date dateOne, Date dateTwo) {
		return period(LocalDateUtils.convert(dateOne), LocalDateUtils.convert(dateTwo));
	}

	public static Integer inDays(Date dateOne, Date dateTwo) {
		val period = period(dateOne, dateTwo);
		if (period != null) {
			return period.getDays();
		}
		return null;
	}

	public static Integer inMonths(Date dateOne, Date dateTwo) {
		val period = period(dateOne, dateTwo);
		if (period != null) {
			return period.getMonths();
		}
		return null;
	}

	public static Integer inYears(Date dateOne, Date dateTwo) {
		val period = period(dateOne, dateTwo);
		if (period != null) {
			return period.getYears();
		}
		return null;
	}

}