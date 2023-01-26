package mx.softixx.cis.common.core.crypto;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.keygen.KeyGenerators;

public final class CryptoUtils {
	
	private CryptoUtils() {		
	}
	
	public static String generateSalt() {
		return BCrypt.gensalt(12);
	}
	
	public static String generateHash() {
		return KeyGenerators.string().generateKey();
	}
	
}