package mx.softixx.cis.common.core.data;

import java.math.BigInteger;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public final class BigIntegerUtils {
	
	private BigIntegerUtils() {
		throw new IllegalStateException("Utility class");
	}
	
	public static BigInteger value(Integer number) {
		try {

			if (number != null) {
				return BigInteger.valueOf(number.intValue());
			}

		} catch (Exception e) {
			log.error("BigIntegerUtils#value error {}", e.getMessage());
		}
		return null;
	}

	public static BigInteger bigInteger(String str) {
		try {
			
			if (str != null && !str.isBlank() && !str.isEmpty()) {
				return new BigInteger(str);
			}			

		} catch (Exception e) {
			log.error("BigIntegerUtils#bigInteger error {}", e.getMessage());
		}
		return null;
	}
	
}