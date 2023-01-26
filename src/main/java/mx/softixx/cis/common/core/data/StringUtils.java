package mx.softixx.cis.common.core.data;

import org.springframework.lang.Nullable;

public final class StringUtils {
	
	private StringUtils() {
		throw new IllegalStateException("This is a utility class and cannot be instantiated");
	}
	
	public static final String EMPTY = "";
	
	public static boolean hasValue(@Nullable String str) {
		return (str != null && !str.isBlank() && !str.isEmpty());
	}
	
	public static <T extends CharSequence> boolean hasValue(@Nullable T t) {
	    if (t instanceof String) {
	    	return hasValue(t);
	    }
	    return hasLength(t);
	}
	
	public static boolean isEmpty(@Nullable String str) {
		return !hasValue(str);
	}
	
	public static boolean hasLength(@Nullable String str) {
		return (str != null && !str.isEmpty());
	}
	
	public static boolean hasLength(@Nullable CharSequence str) {
		return (str != null && str.length() > 0);
	}
	
	public static String valueOf(Object obj) {
		if (obj != null) {
			return String.valueOf(obj);
		}
		return null;
	}
	
	public static boolean containsWhitespace(@Nullable String str) {
		return containsWhitespace((CharSequence) str);
	}
	
	public static boolean containsWhitespace(@Nullable CharSequence str) {
		if (!hasLength(str)) {
			return false;
		}
		
		int strLength = str.length();
		for (int i = 0; i < strLength; i++) {
			if (Character.isWhitespace(str.charAt(i))) {
				return true;
			}
		}
		return false;
	}
	
}