package com.mondimedia.memory.common;

/**
 * This interface for all the constants used in this app
 *
 * @author <a href="mailto:emad.omara85@gmail.com" target="_top">Emad Omara</a>
 *
 */
public interface Constants {

	/**
	 * Dao Type key
	 */
	public static final String DAO_TYPE_KEY = "dao_type";
	/**
	 * In Memory Dao Type
	 */
	public static final int DAO_TYPE_INMEMORY = 1;
	/**
	 * Hazzel cast Dao Type
	 */
	public static final int DAO_TYPE_HAZZEL_CAST = 2;
	public String REQUEST_FIELD_DELIMITER = "-";
	public String REQUEST_COMMAND_DELIMITER = " ";
	public String ERROR_MSG_ILLEGAL_ARGUMENT = "Illegal argument";
	public String ERROR_MSG_MAL_FORMED_REQUEST = "Malformed request";
	public String COMMAND_PRINTALL = "printall";
	public String COMMAND_PRINT = "print";
	public String COMMAND_UPDATE = "update";
	public String COMMAND_DEL = "del";
	public String COMMAND_ADD = "add";
	public String COMMAND_ORDER_DESC = "desc";
	public String COMMAND_ORDER_ASC = "asc";
	public static final String REGEX_ALPHA_NUMERIC = "[a-zA-Z0-9 ]+";
	public static final int ORDER_ASC_TYPE = 1;
	public static final int ORDER_DESC_TYPE = 2;
}
