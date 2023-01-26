package mx.softixx.cis.common.core.collection;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public final class MapUtils {
	
	private MapUtils() {		
	}
	
	public static <K, V> boolean hasDuplicateValues(Map<K, V> map) {
		if (map == null || map.size() <= 1) {
			return false;
		}
		
		return map.values().stream()
						   .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
						   .entrySet().stream().anyMatch(e -> e.getValue() > 1);
	}
	
}