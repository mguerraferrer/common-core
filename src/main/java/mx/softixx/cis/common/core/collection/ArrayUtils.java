package mx.softixx.cis.common.core.collection;

import java.util.Arrays;

public final class ArrayUtils {
	
	private ArrayUtils() {		
	}
	
	public static <T> boolean hasDuplicateValues(T[] array) {
		if (array == null || array.length <= 1) {
			return false;
		}
		return Arrays.stream(array).distinct().count() < array.length;
	}
	
}