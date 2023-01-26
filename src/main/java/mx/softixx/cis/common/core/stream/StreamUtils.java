package mx.softixx.cis.common.core.stream;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import lombok.val;

public final class StreamUtils {

	private StreamUtils() {
	}

	/**
	 * <p>
	 * Returns a {@code java.util.Set} with duplicate elements in the provided list
	 * </p>
	 * 
	 * @param <T>  the type of list elements
	 * @param list provided {@code List<T>}
	 * @return {@code java.util.Set} with duplicate elements in the provided list
	 */
	public static <T> Set<T> findDuplicate(List<T> list) {
		// Using Filter & Set.add()
		// The Set.add() returns false if the element was already in the set
		val items = new HashSet<T>();
		return list.stream().filter(n -> !items.add(n)).collect(Collectors.toSet());
	}
	
	public static <T> boolean hasDuplicateValues(List<T> list) {
		val duplicateSet = findDuplicate(list);
		return !duplicateSet.isEmpty();
	}

	public static <T> Stream<T> concatenate(List<T> sourceOne, List<T> sourceTwo) {
		return concatenate(sourceOne.stream(), sourceTwo.stream());
	}

	public static <T> Stream<T> concatenate(Stream<T> streamOne, Stream<T> streamTwo) {
		return Stream.concat(streamOne, streamTwo);
	}

	/**
	 * Returns the min T element in the list
	 * 
	 * @param <T>    must be of type {@code Date}, {@code LocalTime},
	 *               {@code LocalDate} or {@code LocalDateTime}
	 * @param source {@code List<T>}
	 * @return the min T element in the list
	 */
	public static <T> Object findMin(List<T> source) {
		if (source == null || source.isEmpty()) {
			return null;
		}

		val list = source.stream().filter(Objects::nonNull).toList();
		if (!list.isEmpty()) {
			val item = list.get(0);
			if (item instanceof Character) {
				return list.stream().min(Comparator.comparing(String::valueOf)).orElse(null);
			} else if (item instanceof String) {
				return list.stream().min(Comparator.comparing(String::valueOf)).orElse(null);
			} else if (item instanceof Integer) {
				return list.stream().map(Integer.class::cast).min(Integer::compareTo).orElse(null);
			} else if (item instanceof Long) {
				return list.stream().map(Long.class::cast).min(Long::compareTo).orElse(null);
			} else if (item instanceof Date) {
				return list.stream().map(Date.class::cast).min(Date::compareTo).orElse(null);
			} else if (item instanceof LocalTime) {
				return list.stream().map(LocalTime.class::cast).min(LocalTime::compareTo).orElse(null);
			} else if (item instanceof LocalDate) {
				return list.stream().map(LocalDate.class::cast).min(LocalDate::compareTo).orElse(null);
			} else if (item instanceof LocalDateTime) {
				return list.stream().map(LocalDateTime.class::cast).min(LocalDateTime::compareTo).orElse(null);
			}
		}

		return null;
	}

	/**
	 * Returns the max T element in the list
	 * 
	 * @param <T>    must be of type {@code Date}, {@code LocalTime},
	 *               {@code LocalDate} or {@code LocalDateTime}
	 * @param source {@code List<T>}
	 * @return the max T element in the list
	 */
	public static <T> Object findMax(List<T> source) {
		if (source == null || source.isEmpty()) {
			return null;
		}

		val list = source.stream().filter(Objects::nonNull).toList();
		if (!list.isEmpty()) {
			val item = list.get(0);
			if (item instanceof Character) {
				return list.stream().max(Comparator.comparing(String::valueOf)).orElse(null);
			} else if (item instanceof String) {
				return list.stream().max(Comparator.comparing(String::valueOf)).orElse(null);
			} else if (item instanceof Integer) {
				return list.stream().map(Integer.class::cast).max(Integer::compareTo).orElse(null);
			} else if (item instanceof Long) {
				return list.stream().map(Long.class::cast).max(Long::compareTo).orElse(null);
			} else if (item instanceof Date) {
				return list.stream().map(Date.class::cast).max(Date::compareTo).orElse(null);
			} else if (item instanceof LocalTime) {
				return list.stream().map(LocalTime.class::cast).max(LocalTime::compareTo).orElse(null);
			} else if (item instanceof LocalDate) {
				return list.stream().map(LocalDate.class::cast).max(LocalDate::compareTo).orElse(null);
			} else if (item instanceof LocalDateTime) {
				return list.stream().map(LocalDateTime.class::cast).max(LocalDateTime::compareTo).orElse(null);
			}
		}

		return null;
	}

}