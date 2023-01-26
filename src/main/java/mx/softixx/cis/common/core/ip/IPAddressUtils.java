package mx.softixx.cis.common.core.ip;

import inet.ipaddr.AddressConversionException;
import inet.ipaddr.AddressStringException;
import inet.ipaddr.IPAddressString;
import lombok.val;
import mx.softixx.cis.common.core.validator.ValidatorUtils;

public final class IPAddressUtils {

	private IPAddressUtils() {
	}

	public static boolean isIpInRange(String clientIp, String startIpRange, String endIpRange) {
		if (ValidatorUtils.isNotEmpty(clientIp) && ValidatorUtils.isNotEmpty(startIpRange)
				&& ValidatorUtils.isNotEmpty(endIpRange)) {
			try {
				
				val lower = new IPAddressString(startIpRange).toAddress();
				val upper = new IPAddressString(endIpRange).toAddress();
				val addr = new IPAddressString(clientIp).toAddress();
				val range = lower.spanWithRange(upper);
				return range.contains(addr);
				
			} catch (AddressStringException | AddressConversionException e) {
				return false;
			}
		}
		return true;
	}

}