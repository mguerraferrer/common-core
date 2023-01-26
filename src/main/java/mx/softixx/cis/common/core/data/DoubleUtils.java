package mx.softixx.cis.common.core.data;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.regex.Pattern;

import lombok.val;
import lombok.extern.slf4j.Slf4j;
import mx.softixx.cis.common.core.validator.ValidatorUtils;

@Slf4j(topic = "DoubleUtils")
public class DoubleUtils {

	private DoubleUtils() {
		throw new IllegalStateException("This is a utility class and cannot be instantiated");
	}

	public static Double parse(String str) {
		try {

			if (StringUtils.hasValue(str)) {
				val sanitizedValue = DecimalUtils.sanitizeStr(str);
				return Double.parseDouble(sanitizedValue);
			}

		} catch (NumberFormatException e) {
			log.error("#parse error - {}", e.getMessage());
		}
		return null;
	}
	
	public static Double parseWithDecimal(String str) {
		try {
			
			val doubleValue = parse(str);
			if (ValidatorUtils.isNotEmpty(doubleValue)) {
				val dValue = DecimalUtils.FORMATTER_WITH_DECIMAL.format(doubleValue);
				val sanitizedValue = DecimalUtils.sanitizeStr(dValue);
				return Double.parseDouble(sanitizedValue);
			}
			
		} catch (NumberFormatException e) {
			log.error("#parseWithDecimal error - {}", e.getMessage());
		}
		return null;
	}
	
	public static Double parseWithoutDecimal(String str) {
		try {

			val doubleValue = parse(str);
			if (ValidatorUtils.isNotEmpty(doubleValue)) {
				val dValue = DecimalUtils.FORMATTER_WITHOUT_DECIMAL.format(doubleValue);
				val sanitizedValue = DecimalUtils.sanitizeStr(dValue);
				return Double.parseDouble(sanitizedValue);
			}

		} catch (NumberFormatException e) {
			log.error("#parseWithoutDecimal error - {}", e.getMessage());
		}
		return null;
	}

	public static Double valueOf(Integer i) {
		if (i != null) {
			return i.doubleValue();
		}
		return null;
	}

	public static Double valueOf(BigDecimal bd) {
		if (bd != null) {
			return bd.doubleValue();
		}
		return null;
	}

	public static String format(Double d) {
		try {

			if (d != null) {
				var str = DecimalUtils.FORMATTER_WITH_DECIMAL.format(d);
				if (str.startsWith(".")) {
					str = "0".concat(str);
				}
				return str;
			}

		} catch (IllegalArgumentException e) {
			log.error("UDouble#dValue(Double) error {}", e.getMessage());
		}
		return null;
	}
	
	public static Double format(BigDecimal bd) {
		try {
			
			if (bd != null) {
				val d = bd.doubleValue();
				val dValue = DecimalUtils.FORMATTER_WITH_DECIMAL.format(d);
				val sanitizedValue = DecimalUtils.sanitizeStr(dValue);
				return Double.parseDouble(sanitizedValue);
			}
			
		} catch (NumberFormatException e) {
			log.error("#parseWithDecimal error - {}", e.getMessage());
		}
		return null;
	}

	public static String integerPart(final Double value) {
		try {

			if (ValidatorUtils.isNotEmpty(value)) {
				val doubleAsString = String.valueOf(value);
				val indexOfDecimal = doubleAsString.indexOf(".");

				var dValue = Double.valueOf(doubleAsString.substring(0, indexOfDecimal).replace(",", ""));

				val df = new DecimalFormat("#,###");
				return df.format(dValue);
			}

		} catch (Exception e) {
			log.error("UDouble#integerPart error {}", e.getMessage());
		}
		return null;
	}

	public static Double round(final Double value) {
		val bd = BigDecimalUtils.round(value);
		return valueOf(bd);
	}

	public static Double roundCommision(final Double value) {
		try {

			if (value != null) {
				// ##### Rounding initially to 2 decimal places
				val dVal = round(value);

				val strVal = format(dVal);
				if (strVal != null) {
					val strSplitted = strVal.split(Pattern.quote("."));

					int intVal = IntegerUtils.valueOf(strSplitted[0]);
					int result = intVal % 10;

					do {
						intVal++;
						result = intVal % 10;
					} while (result != 0);

					return valueOf(intVal);
				}
			}

		} catch (Exception e) {
			log.error("#roundCommision error {}", e.getMessage());
		}
		return null;
	}

}
