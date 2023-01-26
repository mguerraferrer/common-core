package mx.softixx.cis.common.core.data;

import java.math.BigDecimal;
import java.math.RoundingMode;

import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "BigDecimalUtils")
public final class BigDecimalUtils {
	
	private BigDecimalUtils() {
		throw new IllegalStateException("This is a utility class and cannot be instantiated");
	}
	
	/**
	 * Default 2 decimal places
	 */
	private static final int DEFAULT_SCALE = 2;
	
	public static BigDecimal parse(String str) {
		try {
			
			if (StringUtils.hasValue(str)) {
				str = DecimalUtils.sanitizeStr(str);
				return round(new BigDecimal(str));
			}
			
		} catch (NumberFormatException e) {
			log.error("#parse error - {}", e.getMessage());
		}
		return null;
	}
	
	public static String format(BigDecimal bd) {
		try {

			if (bd != null) {
				var str = DecimalUtils.FORMATTER_WITH_DECIMAL.format(bd);
				
				if (str.startsWith(".")) {
					str = "0".concat(str);
				}
				
				return str;
			}

		} catch (IllegalArgumentException e) {
			log.error("#format error {}", e.getMessage());
		}
		return null;
	}
	
	public static BigDecimal valueOf(final Double value) {
		if (value != null) {
			return BigDecimal.valueOf(value);
		}
		return null;
	}
	
	public static BigDecimal valueOf(final Long value) {
		if (value != null) {
			return BigDecimal.valueOf(value);
		}
		return null;
	}
	
	public static BigDecimal valueOf(final Integer value) {
		if (value != null) {
			return BigDecimal.valueOf(value);
		}
		return null;
	}
	
	public static BigDecimal round(final Double value) {
		if (value != null) {
			var bd = BigDecimal.valueOf(value);
			return round(bd);
		}
		return null;
	}
	
	public static BigDecimal round(final Integer value) {
		if (value != null) {
			var bd = BigDecimal.valueOf(value);
			return round(bd);
		}
		return null;
	}
	
	public static BigDecimal round(final BigDecimal value) {
		if (value != null) {
			return value.setScale(DEFAULT_SCALE, RoundingMode.HALF_UP);
		}
		return null;
	}
	
	public static boolean isGreaterThanZero(final BigDecimal value) {
		if (value != null) {
			return value.compareTo(BigDecimal.ZERO) > 0;
		}
		return false;
	}
	
}