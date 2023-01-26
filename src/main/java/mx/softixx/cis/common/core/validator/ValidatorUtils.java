package mx.softixx.cis.common.core.validator;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;
import java.util.regex.Pattern;

import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import lombok.val;
import lombok.extern.slf4j.Slf4j;
import mx.softixx.cis.common.core.data.DoubleUtils;
import mx.softixx.cis.common.core.data.IntegerUtils;
import mx.softixx.cis.common.core.datetime.DateFormatter;
import mx.softixx.cis.common.core.datetime.DateUtils;
import mx.softixx.cis.common.core.datetime.LocalDateTimeUtils;
import mx.softixx.cis.common.core.datetime.LocalDateUtils;
import mx.softixx.cis.common.core.datetime.LocalTimeUtils;
import mx.softixx.cis.common.core.pattern.PatternUtils;

@Slf4j
public final class ValidatorUtils  {

	private ValidatorUtils() {
	}

	// ##### validator-messages_es_MX.properties: La contraseña debe tener una longitud entre
	// 8 y 30 caracteres. Debe contener letras mayúsculas, números y caracteres
	// especiales
	// ##### validator-messages_en_US.properties:
	public static final String INVALID_PASSWORD = "{validator.text.invalid.password}";
	public static final String INVALID_PASSWORD_MESSAGE = "validator.text.invalid.password";

	// ##### validator-messages_es_MX.properties: La contraseña actual no es correcta
	// ##### validator-messages_en_US.properties:
	public static final String INVALID_OLD_PASSWORD = "{validator.text.invalid.old.password}";
	public static final String INVALID_OLD_PASSWORD_MESSAGE = "validator.text.invalid.old.password";

	// ##### validator-messages_es_MX.properties: Las contraseñas no coinciden
	// ##### validator-messages_en_US.properties:
	public static final String INVALID_PASSWORD_MATCHING = "{validator.text.matching.password}";
	public static final String INVALID_PASSWORD_MATCHING_MESSAGE = "validator.text.matching.password";

	// ##### validator-messages_es_MX.properties: La nueva contraseña debe ser diferente a la
	// contraseña actual
	// ##### validator-messages_en_US.properties:
	public static final String INVALID_PASSWORD_DIFFERENT = "{validator.text.different.password}";
	public static final String INVALID_PASSWORD_DIFFERENT_MESSAGE = "validator.text.different.password";

	// ##### validator-messages_es_MX.properties: El cupón especificado no es válido
	// ##### validator-messages_en_US.properties:
	public static final String INVALID_COUPON = "{validator.text.invalid.coupon}";
	public static final String INVALID_COUPON_MESSAGE = "validator.text.invalid.coupon";

	// ##### validator-messages_es_MX.properties: Ya has utilizado este cupón previamente
	// ##### validator-messages_en_US.properties:
	public static final String INVALID_USED_COUPON_MESSAGE = "validator.text.used.coupon";

	// ##### validator-messages_es_MX.properties: Debes iniciar sesión para realizar esta
	// acción
	// ##### validator-messages_en_US.properties:
	public static final String USER_NO_AUTHENTICATED_ACTION = "{text.error.user.no.authenticated.action}";
	public static final String USER_NO_AUTHENTICATED_ACTION_MESSAGE = "text.error.user.no.authenticated.action";

	// ##### validator-messages_es_MX.properties: Ya existe una cuenta con la dirección de
	// correo especificada
	// ##### validator-messages_en_US.properties:
	public static final String USER_ALREADY_EXISTS = "{validator.text.user.already.exists}";
	public static final String USER_ALREADY_EXISTS_MESSAGE = "validator.text.user.already.exists";

	// ##### validator-messages_es_MX.properties: Ya existe una cuenta con el RFC especificado
	// ##### validator-messages_en_US.properties:
	public static final String USER_RFC_ALREADY_EXISTS = "{validator.text.user.rfc.already.exists}";
	public static final String USER_RFC_ALREADY_EXISTS_MESSAGE = "validator.text.user.rfc.already.exists";

	// ##### validator-messages_es_MX.properties: Ya existe una cuenta con la razón social
	// especificada
	// ##### validator-messages_en_US.properties:
	public static final String USER_BNAME_ALREADY_EXISTS = "{validator.text.user.bname.already.exists}";
	public static final String USER_BNAME_ALREADY_EXISTS_MESSAGE = "validator.text.user.bname.already.exists";

	// ##### validator-messages_es_MX.properties: No existe ninguna cuenta activa que corresponda al correo electrónico especificado
	// ##### validator-messages_en_US.properties:
	public static final String USER_UNKNOWN = "{validator.text.user.unknown}";
	public static final String USER_UNKNOWN_MESSAGE = "validator.text.user.unknown";

	// ##### ===== GENERAL ERRORS =====
	/**
	 * <b>validator-messages_es_MX.properties:</b> Ha ocurrido un error inesperado procesando
	 * la solicitud <br>
	 * <b>validator-messages_en_US.properties:</b> An unexpected error occurred while
	 * processing the request
	 */
	public static final String UNEXPECTED_ERROR = "validation.unexpected.error";

	// ##### ===== THROWABLE ERRORS =====
	/**
	 * <b>validator-messages_es_MX.properties:</b> Error interno del servidor <br>
	 * <b>validator-messages_en_US.properties:</b> Internal server error
	 */
	public static final String THROWABLE_INTERNAL_SERVER_ERROR = "{validation.throwable.internal.server.error}";	
	/**
	 * <b>validator-messages_es_MX.properties:</b> Ha ocurrido un error interno en el
	 * servidor. La solicitud ha sido cancelada <br>
	 * <b>validator-messages_en_US.properties:</b> An internal server error has occurred. The
	 * request has been cancelled
	 */
	public static final String THROWABLE_INTERNAL_SERVER_ERROR_MESSAGE = "validation.throwable.internal.server.error.message";
	/**
	 * <b>validator-messages_es_MX.properties:</b> Access denegado <br>
	 * <b>validator-messages_en_US.properties:</b> Resource Denied
	 */
	public static final String THROWABLE_RESOURCE_DENIED = "{validation.throwable.resource.denied}";
	/**
	 * <b>validator-messages_es_MX.properties:</b> No tiene permisos para acceder al recurso
	 * solicitado <br>
	 * <b>validator-messages_en_US.properties:</b> You don't have permission to access the
	 * requested resource
	 */
	public static final String THROWABLE_RESOURCE_DENIED_MESSAGE = "validation.throwable.resource.denied.message";
	/**
	 * <b>validator-messages_es_MX.properties:</b> Recurso no disponible <br>
	 * <b>validator-messages_en_US.properties:</b> Resource not available
	 */
	public static final String THROWABLE_RESOURCE_NOT_AVAILABLE = "{validation.throwable.resource.not.available}";
	/**
	 * <b>validator-messages_es_MX.properties:</b> El recurso al que está intentando acceder
	 * no existe o no está disponible <br>
	 * <b>validator-messages_en_US.properties:</b> The resource you are trying to access does
	 * not exist or is not available
	 */
	public static final String THROWABLE_RESOURCE_NOT_AVAILABLE_MESSAGE = "validation.throwable.resource.not.available.message";
	/**
	 * <b>validator-messages_es_MX.properties:</b> No autorizado <br>
	 * <b>validator-messages_en_US.properties:</b> Unauthorized
	 */
	public static final String THROWABLE_UNAUTHORIZED = "{validation.throwable.unauthorized}";
	/**
	 * <b>validator-messages_es_MX.properties:</b> No está autorizado para realizar esta
	 * acción <br>
	 * <b>validator-messages_en_US.properties:</b> You are not authorized to perform this
	 * action
	 */
	public static final String THROWABLE_UNAUTHORIZED_MESSAGE = "validation.throwable.unauthorized.message";
	/**
	 * <b>validator-messages_es_MX.properties:</b> Error de sesión <br>
	 * <b>validator-messages_en_US.properties:</b> Session error
	 */
	public static final String THROWABLE_SESSION_ERROR = "{validation.throwable.session.error}";
	/**
	 * <b>validator-messages_es_MX.properties:</b> Su sesión ha expirado o no ha podido ser
	 * validada internamente <br>
	 * <b>validator-messages_en_US.properties:</b> Your session has expired or could not be
	 * validated internally
	 */
	public static final String THROWABLE_SESSION_ERROR_MESSAGE = "validation.throwable.session.error.message";

	// ##### ===== UVALIDATOR METHODS =====
	public static final int MIN_DECIMALS = 2;
	public static final int MAX_DECIMALS = 6;

	public static boolean nonNull(List<Object> args) {
		return args.stream().filter(ObjectUtils::isEmpty).count() == 0;
	}
	
	public static boolean nonNull(Object value) {
		return Objects.nonNull(value);
	}
	
	public static boolean isEmpty(Object value) {
		return ObjectUtils.isEmpty(value);
	}

	public static boolean isNotEmpty(Object value) {
		return !isEmpty(value);
	}

	public static boolean hasErrors(Boolean error, Map<String, String> errorMap) {
		return Boolean.TRUE.equals(error) || isNotEmpty(errorMap);
	}
	
	public static boolean hasErrors(Boolean error) {
		return Boolean.TRUE.equals(error);
	}
	
	public static boolean hasErrors(Map<String, String> errorMap) {
		return isNotEmpty(errorMap);
	}
	
	public static boolean hasNoErrors(Boolean error, Map<String, String> errorMap) {
		return Boolean.FALSE.equals(error) && isEmpty(errorMap);
	}
	
	public static boolean hasNoErrors(Boolean error) {
		return Boolean.FALSE.equals(error);
	}
	
	public static boolean hasNoErrors(Map<String, String> errorMap) {
		return isEmpty(errorMap);
	}

	/**
	 * Evaluates if Object... is non-null
	 * 
	 * @param args Object...
	 * @return True if Object... is non-null, otherwise false
	 */
	public static boolean isNotNullArgs(final Object... args) {
		if (Objects.nonNull(args)) {
			val nonNullArgs = Arrays.stream(args).filter(Objects::nonNull).count();
			return nonNullArgs > 0;
		}
		return false;
	}

	/**
	 * Evaluates if String... is non-null
	 * 
	 * @param args String...
	 * @return True if Object... is non-null, otherwise false
	 */
	public static boolean isNotNullArgs(final String... args) {
		if (Objects.nonNull(args)) {
			val nonNullArgs = Arrays.stream(args).filter(Objects::nonNull).count();
			return nonNullArgs > 0;
		}
		return false;
	}
	
	/**
	 * Evaluates if Object... is non-null
	 * 
	 * @param args Object...
	 * @return True if Object... is non-null, otherwise false
	 */
	public static boolean isNotEmptyArgs(final Object... args) {
		if (Objects.nonNull(args)) {
			val nonNullArgs = Arrays.stream(args)
									.filter(Objects::nonNull)
									.filter(ValidatorUtils::filterArgs)
									.count();
			return nonNullArgs > 0;
		}
		return false;
	}
	
	private static boolean filterArgs(Object obj) {
		if (obj == null) {
			return false;
		}
		
		val str = obj.toString().trim();
		return isNotEmpty(str);
	}
	
	public static boolean isNumeric(final String str) {
		try {
			
			if (isNotEmpty(str)) {
				Integer.parseInt(str);
				return true;
			}

		} catch (NumberFormatException nfe) {
			log.error("ValidatorUtils#isNumeric error {}", nfe.getMessage());
		}
		return false;
	}

	public static boolean isValidExtension(final List<String> extensions, final String ext) {
		val isValid = extensions.stream().filter(e -> e.equalsIgnoreCase(ext)).findAny().orElse(null);
		return isValid != null;
	}
	
	public static boolean validateRequired(final Object obj) {
		if (ObjectUtils.isEmpty(obj)) {
			return false;
		}
		
		if (obj instanceof String) {
			val str = obj.toString().trim();
			return isNotEmpty(str);
		}
		
		return true;
	}
	
	public static boolean validatePattern(final String value, final String regexp) {
		if (!ObjectUtils.isEmpty(value) && !ObjectUtils.isEmpty(regexp)) {
			try {
				
				val isMatch = Pattern.matches(regexp, value);
				if (!isMatch) {
					log.warn("ValidatorUtils#validatePattern warning! - Constraint validation failed: The value {} doesn't match with the regex {}", value, regexp);
				}
				return isMatch;
				
			} catch (Exception e) {
				log.error("ValidatorUtils#validatePattern error {}", e.getMessage());
			}
			return false;
		}
		return true;
	}
	
	public static boolean validateAlphabetic(final String letter) {
		return Pattern.compile(PatternUtils.ALPHABETIC_PATTERN).matcher(letter).matches();
	}

	public static boolean validateNullableAlphabetic(final String letter) {
		if (letter == null) {
			return true;
		} else {
			val vletter = letter.trim();
			if (vletter.isEmpty()) {
				return true;
			}
		}
		return validateAlphabetic(letter);
	}

	public static boolean validateAlphabeticWithSpace(final String letter) {
		return Pattern.compile(PatternUtils.ALPHABETIC_WITH_SPACE_PATTERN).matcher(letter).matches();
	}

	public static boolean validateNullableAlphabeticWithSpace(final String letter) {
		if (letter == null) {
			return true;
		} else {
			val vletter = letter.trim();
			if (vletter.isEmpty()) {
				return true;
			}
		}
		return validateAlphabeticWithSpace(letter);
	}

	public static boolean validateLength(final String letter, final Integer min, final Integer max) {
		if (letter == null) {
			return false;
		} else {
			return (letter.length() >= min && letter.length() <= max);
		}
	}

	public static boolean validateLength(final String letter, final Integer max) {
		if (letter == null) {
			return false;
		} else {
			return letter.length() <= max;
		}
	}

	public static boolean validateNumber(final String number) {
		if (number == null) {
			return false;
		}
		return Pattern.compile(PatternUtils.NUMBER_PATTERN).matcher(number).matches();
	}

	public static boolean validateNullableNumber(final String number) {
		if (number == null) {
			return true;
		} else {
			val vnumber = number.trim();
			if (vnumber.isEmpty()) {
				return true;
			}
		}
		return validateNumber(number);
	}

	public static boolean validateNumberWithSpace(final String number) {
		return Pattern.compile(PatternUtils.NUMBER_WITH_SPACE_PATTERN).matcher(number).matches();
	}

	public static boolean validateNullableNumberWithSpace(final String number) {
		if (number == null) {
			return true;
		} else {
			val vnumber = number.trim();
			if (vnumber.isEmpty()) {
				return true;
			}
		}
		return validateNumberWithSpace(number);
	}

	public static boolean validateIntNumber(final Integer number) {
		if (number == null) {
			return false;
		}

		val pattern = Pattern.compile(PatternUtils.NUMBER_PATTERN);
		val matcher = pattern.matcher(IntegerUtils.format(number));
		return matcher.matches();
	}

	public static boolean validateIntNullableNumber(final Integer number) {
		if (number == null) {
			return true;
		} else {
			val vnumber = IntegerUtils.format(number);
			if (vnumber.isEmpty()) {
				return true;
			}
		}
		return validateIntNumber(number);
	}

	public static boolean validateDate(final String date) {
		return dateValidator(date);
	}

	public static boolean validateNullableDate(final String date) {
		if (date == null) {
			return true;
		} else {
			val vdate = date.trim();
			if (vdate.isEmpty()) {
				return true;
			}
			return dateValidator(vdate);
		}
	}

	private static boolean dateValidator(final String date) {
		Date formattedDate = null;
		try {

			val format = DateFormatter.DATE_SIMPLE_FORMAT.getFormat();
			val sdf = new SimpleDateFormat(format);
			formattedDate = sdf.parse(date);

		} catch (Exception e) {
			return false;
		}
		return formattedDate != null;
	}

	public static boolean validateDateTime(final String date) {
		return dateValidator(date);
	}

	public static boolean validateNullableDateTime(final String date) {
		if (date == null) {
			return true;
		} else {
			val vdate = date.trim();
			if (vdate.isEmpty()) {
				return true;
			}
			return dateTimeValidator(vdate);
		}
	}

	private static boolean dateTimeValidator(final String date) {
		Date formattedDate = null;
		try {

			val format = DateFormatter.DATE_TIME_FULL_FORMAT.getFormat();
			val sdf = new SimpleDateFormat(format);
			formattedDate = sdf.parse(date);

		} catch (Exception e) {
			return false;
		}
		return formattedDate != null;
	}
	
	public static boolean validateFutureOrPresent(final String date, final String format, boolean validateTime, boolean dateUtc) {
		if (isEmpty(date) || isEmpty(format)) {
			return true;
		}
		return futureOrPresentValidator(date, format, validateTime, dateUtc);
	}
	
	private static boolean futureOrPresentValidator(final String date, final String format, boolean validateTime, boolean dateUtc) {
		var parsedDate = DateUtils.parseDate(date, format);
		if (parsedDate != null) {
			var compareDate = new Date();
			if (dateUtc) {
				compareDate = DateUtils.dateUTC();
				parsedDate = DateUtils.convertToUTC(parsedDate);
			}
			
			if (!validateTime) {
				return LocalDateUtils.isAfterOrEqual(parsedDate, compareDate);
			}
			return LocalDateTimeUtils.isAfterOrEqual(parsedDate, compareDate);
		}
		return false;
	}
	
	public static boolean validatePastOrPresent(final String date, final String format, boolean validateTime, boolean dateUtc) {
		if (isEmpty(date) || isEmpty(format)) {
			return true;
		}
		return pastOrPresentValidator(date, format, validateTime, dateUtc);
	}
	
	private static boolean pastOrPresentValidator(final String date, final String format, boolean validateTime, boolean dateUtc) {
		var parsedDate = DateUtils.parseDate(date, format);
		if (parsedDate != null) {
			var compareDate = new Date();
			if (dateUtc) {
				compareDate = DateUtils.dateUTC();
				parsedDate = DateUtils.convertToUTC(parsedDate);
			}
			
			if (!validateTime) {
				return LocalDateUtils.isBeforeOrEqual(parsedDate, compareDate);
			}
			return LocalDateTimeUtils.isBeforeOrEqual(parsedDate, compareDate); 
		}
		return false;
	}

	public static boolean validateDecimal(final String number) {
		return decimalValidator(number);
	}

	public static boolean validateNullableDecimal(final String number) {
		if (number == null) {
			return true;
		} else {
			val vnumber = number.trim();
			if (vnumber.isEmpty()) {
				return true;
			}
		}
		return decimalValidator(number);
	}

	public static boolean validateDecimal(final Double number) {
		val dnum = DoubleUtils.format(number);
		return decimalValidator(dnum);
	}

	public static boolean validateNullableDecimal(final Double number) {
		if (number == null) {
			return true;
		} else {
			val vnumber = DoubleUtils.format(number).trim();
			if (vnumber.isEmpty()) {
				return true;
			}
		}
		return validateDecimal(number);
	}

	private static boolean decimalValidator(final String number) {
		if (number != null) {
			try (val scanner = new Scanner(number)) {

				if (scanner.hasNextDouble()) {
					int pos = number.indexOf(".");
					if (pos > 0) {
						int decimal = number.substring(pos + 1).length();
						return decimal <= MAX_DECIMALS;
					}
					return true;
				}

			} catch (Exception e) {
				log.error("ValidatorUtils#decimalValidator error {}", e);
			}
		}
		return false;
	}

	public static boolean validateEmail(final String email) {
		return Pattern.compile(PatternUtils.EMAIL_PATTERN).matcher(email).matches();
	}

	public static boolean validateNullableEmail(final String email) {
		if (email == null) {
			return true;
		} else {
			val vemail = email.trim();
			if (vemail.isEmpty()) {
				return true;
			}
			return validateEmail(vemail);
		}
	}

	public static boolean validateTime12H(final String time) {
		if (isEmpty(time)) {
			return false;
		}
		
		val occurrences = StringUtils.countOccurrencesOf(time, ":");
		val formatter = occurrences == 1 ? DateFormatter.T12H : DateFormatter.T12H_FULL;
		
		return timeValidator(time, formatter);
	}
	
	public static boolean validateNullableTime12H(final String time) {
		if (isEmpty(time)) {
			return true;
		}
		return validateTime12H(time);
	}
	
	public static boolean validateTime24H(final String time) {
		if (isEmpty(time)) {
			return false;
		}
		
		val occurrences = StringUtils.countOccurrencesOf(time, ":");
		val formatter = occurrences == 1 ? DateFormatter.T24H : DateFormatter.T24H_FULL;
		
		return timeValidator(time, formatter);
	}

	public static boolean validateNullableTime24H(String time) {
		if (isEmpty(time)) {
			return true;
		}
		return validateTime24H(time);
	}

	private static boolean timeValidator(final String time, final DateFormatter formatter) {
		val localTime = LocalTimeUtils.parse(time, formatter);
		return localTime != null;
	}

	public static boolean validateRFC(final String rfc) {
		return Pattern.compile(PatternUtils.RFC_PATTERN).matcher(rfc).matches();
	}

	public static boolean validateNullableRFC(final String rfc) {
		if (rfc == null) {
			return true;
		} else {
			val vrfc = rfc.trim();
			if (vrfc.isEmpty()) {
				return true;
			}
			return validateRFC(vrfc);
		}
	}

	public static boolean validateRFC12(final String rfc) {
		return Pattern.compile(PatternUtils.RFC12_PATTERN).matcher(rfc).matches();
	}

	public static boolean validateRFC13(final String rfc) {
		return Pattern.compile(PatternUtils.RFC13_PATTERN).matcher(rfc).matches();
	}

	public static boolean validateCURP(final String curp) {
		return Pattern.compile(PatternUtils.CURP_PATTERN).matcher(curp).matches();
	}

	public static boolean validateNullableCURP(final String curp) {
		if (curp == null) {
			return true;
		} else {
			val vcurp = curp.trim();
			if (vcurp.isEmpty()) {
				return true;
			}
			return validateCURP(vcurp);
		}
	}

	public static boolean validatePhoneMx(final String phone) {
		return Pattern.compile(PatternUtils.PHONE_MX_PATTERN).matcher(phone).matches();
	}

	public static boolean validateZipCodeMx(final String zipCode) {
		return Pattern.compile(PatternUtils.ZIP_CODE_MX_PATTERN).matcher(zipCode).matches();
	}
	
}