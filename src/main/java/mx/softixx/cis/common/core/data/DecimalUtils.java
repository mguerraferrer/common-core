package mx.softixx.cis.common.core.data;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

import lombok.val;
import lombok.extern.slf4j.Slf4j;
import mx.softixx.cis.common.core.validator.ValidatorUtils;

@Slf4j(topic = "DecimalUtils")
public final class DecimalUtils {
	
	private DecimalUtils() {
		throw new IllegalStateException("This is a utility class and cannot be instantiated");
	}
	
	public static final DecimalFormat FORMATTER_WITH_DECIMAL = new DecimalFormat("###,###,##0.00");
	public static final DecimalFormat FORMATTER_WITHOUT_DECIMAL = new DecimalFormat("###,###,###.##");
	public static final RoundingMode HALF_UP = RoundingMode.HALF_UP;
	
	public static String sanitizeStr(String str) {
		if (isInvalidDecimal(str)) {
			return "0.00";
		}
		return str.replace(",", "").trim();
	}
	
	public static Integer decimals(final String value) {
		try {

			if (ValidatorUtils.isNotEmpty(value)) {
				int pos = value.indexOf(".");
				if (pos <= 0) {
					return 0;
				}

				return value.substring(pos + 1).length();
			} else {
				return 0;
			}

		} catch (IndexOutOfBoundsException e) {
			log.error("#decimals(String) error {}", e.getMessage());
		}
		return null;
	}

	public static Integer decimals(final BigDecimal value) {
		if (ValidatorUtils.isEmpty(value) || value.scale() == 0) {
			return 0;
		}
		
		val bgDecimalPart = value.subtract(value.setScale(0, RoundingMode.FLOOR)).movePointRight(value.scale());
		
		Integer decimalPart = bgDecimalPart.intValue();
		if (decimalPart == 0) {
			return 2;
		}
		
		return decimalPart.toString().length();
	}

	public static String decimalPart(final Double value) {
		if (ValidatorUtils.isEmpty(value)) {
			return ".00";
		}

		val doubleAsString = String.valueOf(value);
		return determineDecimalPart(doubleAsString);
	}

	public static String decimalPart(final BigDecimal value) {
		if (ValidatorUtils.isEmpty(value)) {
			return ".00";
		}
		
		val bigDecimalAsString = String.valueOf(value);
		return determineDecimalPart(bigDecimalAsString);
	}

	private static final boolean isInvalidDecimal(String str) {
		if (ValidatorUtils.isEmpty(str)) {
			return false;
		}
		return str.equals("0") || str.equals("0.0") || str.startsWith(".0");
	}
	
	private static String determineDecimalPart(final String value) {
		if (StringUtils.hasValue(value)) {
			val indexOfDecimal = value.indexOf(".");
			
			val result = value.substring(indexOfDecimal);
			if (StringUtils.isEmpty(result) || result.equals(".0")) {
				return ".00";
			}
			return result;
		}
		return null;
	}

}