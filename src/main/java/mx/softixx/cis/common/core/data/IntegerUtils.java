package mx.softixx.cis.common.core.data;

import java.math.BigInteger;

import lombok.extern.slf4j.Slf4j;
import mx.softixx.cis.common.core.validator.ValidatorUtils;

@Slf4j(topic = "IntegerUtils")
public final class IntegerUtils {
	
	private IntegerUtils() {
	}
	
	public static String format(Integer number) {
		if (number != null) {
			return number.toString();
		}
		return null;
	}
	
	public static Integer valueOf(Object obj) {
		if (obj != null) {
			return valueOf(obj.toString());
		}
		return null;
	}
	
	public static Integer valueOf(String integerStr) {
		try {
			if (ValidatorUtils.isNotEmpty(integerStr)) {
				return Integer.valueOf(integerStr);
			}
		} catch (NumberFormatException e) {
			log.error("#valueOf(String) error {}", e.getMessage());
		}
		return null;
	}

	public static Integer valueOf(Double dValue) {
		if (dValue != null) {
			return dValue.intValue();
		}
		return null;
	}

	public static Integer valueOf(Long lValue) {
		if (lValue != null) {
			return lValue.intValue();
		}
		return null;
	}

	public static Integer valueOf(BigInteger bi) {
		if (bi != null) {
			return bi.intValue();
		}
		return null;
	}

	public static Integer valueOf(Number number) {
		if (number != null) {
			return number.intValue();
		}
		return null;
	}
	
}