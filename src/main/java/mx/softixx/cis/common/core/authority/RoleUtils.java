package mx.softixx.cis.common.core.authority;

import java.util.List;

public final class RoleUtils {

	private RoleUtils() {
	}

	/**
	 * ROLE_ROOT
	 */
	public static final String ROLE_ROOT = "ROOT";

	/**
	 * ROLE_ADMIN
	 */
	public static final String ROLE_ADMIN = "ADMIN";

	/**
	 * ROLE_SALES
	 */
	public static final String ROLE_SALES = "SALES";

	/**
	 * ROLE_DOCTOR
	 */
	public static final String ROLE_DOCTOR = "DOCTOR";

	/**
	 * ROLE_NURSE
	 */
	public static final String ROLE_NURSE = "NURSE";

	/**
	 * ROLE_ASSISTANT
	 */
	public static final String ROLE_ASSISTANT = "ASSISTANT";

	/**
	 * List of participant roles. Includes <b><i>ROLE_DOCTOR</b></i>,
	 * <b><i>ROLE_NURSE</b></i> and <b><i>ROLE_ASSISTANT</b></i>
	 */
	public static final List<String> ROLE_PARTICIPANTS = List.of(ROLE_DOCTOR, ROLE_NURSE, ROLE_ASSISTANT);

}