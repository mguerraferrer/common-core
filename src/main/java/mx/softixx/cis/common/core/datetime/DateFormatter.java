package mx.softixx.cis.common.core.datetime;

public enum DateFormatter {
	// DB DATE
	/**
	 * Format: yyyy-MM-dd
	 */
	DATE_DB_FORMAT("yyyy-MM-dd"),
	/**
	 * Format: yyyy-MM-dd HH:mm
	 */
	DATE_TIME_DB_SIMPLE_FORMAT("yyyy-MM-dd HH:mm"),
	/**
	 * Format: yyyy-MM-dd HH:mm:ss
	 */
	DATE_TIME_DB_FULL_FORMAT("yyyy-MM-dd HH:mm:ss"),
	/**
	 * Format: yyyy-MM-dd'T'HH:mm:ss
	 */
	DATE_TIME_T_FORMAT("yyyy-MM-dd'T'HH:mm:ss"),
	// CUSTOM DATE
	/**
	 * Format: dd/MM/yyyy
	 */
	DATE_SIMPLE_FORMAT("dd/MM/yyyy"),
	/**
	 * Format: dd.MM.yyyy
	 */
	DATE_SIMPLE_DOT_FORMAT("dd.MM.yyyy"),
	/**
	 * Format: dd/MM/yyyy HH:mm
	 */
	DATE_TIME_SIMPLE_FORMAT("dd/MM/yyyy HH:mm"),
	/**
	 * Format: dd/MM/yyyy HH:mm:ss
	 */
	DATE_TIME_FULL_FORMAT("dd/MM/yyyy HH:mm:ss"),
	/**
	 * Format: dd/MM/yyyy hh:mm:ss a
	 */
	DATE_TIME_MERIDIAN_FORMAT("dd/MM/yyyy hh:mm:ss a"),
	// TIME
	/**
	 * Format: hh:mm a
	 */
	T12H("hh:mm a"),
	/**
	 * Format: hh:mm:ss a
	 */
	T12H_FULL("hh:mm:ss a"),
	/**
	 * Format: HH:mm
	 */
	T24H("HH:mm"),
	/**
	 * Format: HH:mm:ss
	 */
	T24H_FULL("HH:mm:ss");

	private String format;

	DateFormatter(final String value) {
		format = value;
	}

	public String getFormat() {
		return format;
	}
}