package mx.softixx.cis.common.core.datetime;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public final class ChronoUnitUtils {
	
	private ChronoUnitUtils() {		
	}
	
	public static Long millisBetween(LocalDate ldOne, LocalDate ldTwo) {
		if (ldOne != null && ldTwo != null) {
			return ChronoUnit.MILLIS.between(ldOne, ldTwo);
		}
		return null;
	}
	
	public static Long millisBetween(LocalDateTime ldtOne, LocalDateTime ldtTwo) {
		if (ldtOne != null && ldtTwo != null) {
			return ChronoUnit.MILLIS.between(ldtOne, ldtTwo);
		}
		return null;
	}
	
	public static Long secondsBetween(LocalDate ldOne, LocalDate ldTwo) {
		if (ldOne != null && ldTwo != null) {
			return ChronoUnit.SECONDS.between(ldOne, ldTwo);
		}
		return null;
	}
	
	public static Long secondsBetween(LocalDateTime ldtOne, LocalDateTime ldtTwo) {
		if (ldtOne != null && ldtTwo != null) {
			return ChronoUnit.SECONDS.between(ldtOne, ldtTwo);
		}
		return null;
	}
	
	public static Long minutesBetween(LocalDate ldOne, LocalDate ldTwo) {
		if (ldOne != null && ldTwo != null) {
			return ChronoUnit.MINUTES.between(ldOne, ldTwo);
		}
		return null;
	}
	
	public static Long minutesBetween(LocalDateTime ldtOne, LocalDateTime ldtTwo) {
		if (ldtOne != null && ldtTwo != null) {
			return ChronoUnit.MINUTES.between(ldtOne, ldtTwo);
		}
		return null;
	}
	
	public static Long hoursBetween(LocalDate ldOne, LocalDate ldTwo) {
		if (ldOne != null && ldTwo != null) {
			return ChronoUnit.HOURS.between(ldOne, ldTwo);
		}
		return null;
	}
	
	public static Long hoursBetween(LocalDateTime ldtOne, LocalDateTime ldtTwo) {
		if (ldtOne != null && ldtTwo != null) {
			return ChronoUnit.HOURS.between(ldtOne, ldtTwo);
		}
		return null;
	}
	
	public static Long halfdaysBetween(LocalDate ldOne, LocalDate ldTwo) {
		if (ldOne != null && ldTwo != null) {
			return ChronoUnit.HALF_DAYS.between(ldOne, ldTwo);
		}
		return null;
	}
	
	public static Long halfdaysBetween(LocalDateTime ldtOne, LocalDateTime ldtTwo) {
		if (ldtOne != null && ldtTwo != null) {
			return ChronoUnit.HALF_DAYS.between(ldtOne, ldtTwo);
		}
		return null;
	}
	
	public static Long daysBetween(LocalDate ldOne, LocalDate ldTwo) {
		if (ldOne != null && ldTwo != null) {
			return ChronoUnit.DAYS.between(ldOne, ldTwo);
		}
		return null;
	}
	
	public static Long daysBetween(LocalDateTime ldtOne, LocalDateTime ldtTwo) {
		if (ldtOne != null && ldtTwo != null) {
			return ChronoUnit.DAYS.between(ldtOne, ldtTwo);
		}
		return null;
	}
	
	public static Long weeksBetween(LocalDate ldOne, LocalDate ldTwo) {
		if (ldOne != null && ldTwo != null) {
			return ChronoUnit.WEEKS.between(ldOne, ldTwo);
		}
		return null;
	}
	
	public static Long weeksBetween(LocalDateTime ldtOne, LocalDateTime ldtTwo) {
		if (ldtOne != null && ldtTwo != null) {
			return ChronoUnit.WEEKS.between(ldtOne, ldtTwo);
		}
		return null;
	}
	
	public static Long monthsBetween(LocalDate ldOne, LocalDate ldTwo) {
		if (ldOne != null && ldTwo != null) {
			return ChronoUnit.MONTHS.between(ldOne, ldTwo);
		}
		return null;
	}
	
	public static Long monthsBetween(LocalDateTime ldtOne, LocalDateTime ldtTwo) {
		if (ldtOne != null && ldtTwo != null) {
			return ChronoUnit.MONTHS.between(ldtOne, ldtTwo);
		}
		return null;
	}
	
	public static Long yearsBetween(LocalDate ldOne, LocalDate ldTwo) {
		if (ldOne != null && ldTwo != null) {
			return ChronoUnit.YEARS.between(ldOne, ldTwo);
		}
		return null;
	}
	
	public static Long yearsBetween(LocalDateTime ldtOne, LocalDateTime ldtTwo) {
		if (ldtOne != null && ldtTwo != null) {
			return ChronoUnit.YEARS.between(ldtOne, ldtTwo);
		}
		return null;
	}
	
}