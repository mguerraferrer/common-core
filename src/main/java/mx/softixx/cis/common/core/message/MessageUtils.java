package mx.softixx.cis.common.core.message;

import java.io.IOException;
import java.util.Properties;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import lombok.val;
import mx.softixx.cis.common.core.validator.ValidatorUtils;

@Component
public class MessageUtils {

	private final MessageSource messageSource;
	private static MessageSourceAccessor accessor;

	public MessageUtils(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

	@PostConstruct
	private void init() {
		accessor = new MessageSourceAccessor(messageSource, LocaleContextHolder.getLocale());
	}
	
	public static void setAccessor(MessageSourceAccessor msAccessor) {
		accessor = msAccessor;
	}

	public static String getMessage(final String key) {
		return getMessage(key, null);
	}

	public static String getMessage(final String key, @Nullable Object[] params) {
		if (ValidatorUtils.isNotEmpty(key)) {
			return accessor.getMessage(key, params, LocaleContextHolder.getLocale());
		}
		return null;
	}

	public static String getMessage(final CustomMessage customMessage) {
		if (ValidatorUtils.isNotEmpty(customMessage)) {
			return accessor.getMessage(customMessage.getKey(), customMessage.getParams(),
					LocaleContextHolder.getLocale());
		}
		return null;
	}

	public static String getPropertieValue(String activeProfile, String key) {
		try {

			var propertiesFile = "application.properties";
			if (activeProfile != null) {
				propertiesFile = "application-" + activeProfile + ".properties";
			}

			val properties = new Properties();
			properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream(propertiesFile));

			val value = properties.getProperty(key);
			if (value != null) {
				return value;
			}

		} catch (IllegalArgumentException | IOException e) {}
		return null;
	}

}