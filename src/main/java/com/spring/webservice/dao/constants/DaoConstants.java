package com.spring.webservice.dao.constants;

/**
 * Constants for DAO Layer
 * 
 * @author Hemantha
 *
 */
public class DaoConstants {
	/** The SAV e_ o r_ update_ error. */
	public static String SAVE_OR_UPDATE_ERROR = "Unable to Save or Update";

	/** The SAV e_ error. */
	public static String SAVE_ERROR = "Unable to Store the record";

	/** The FIN d_ b y_ i d_ error. */
	public static String FIND_BY_ID_ERROR = "Unable to retrieve the User";

	/** The FIN d_ b y_ query_ error. */
	public static String FIND_BY_QUERY_ERROR = "Unable to retrieve the records";

	/** The FIN d_ b y_ class_ error. */
	public static String FIND_BY_CLASS_ERROR = "Unable to retrieve the records";

	/** The DELET e_ error. */
	public static String DELETE_ERROR = "Unable to Delete";

	/** The MERG e_ error. */
	public static String MERGE_ERROR = "Unable to Merge records";

	/** The ATTAC h_ dirt y_ error. */
	public static String ATTACH_DIRTY_ERROR = "Unable to Save/Update ";

	/** The ATTAC h_ clean_ error. */
	public static String ATTACH_CLEAN_ERROR = "Unable to clean  ";

	/** The N o_ record_ found_ error. */
	public static String NO_RECORD_FOUND_ERROR = "Users not Available";

	public static String UNABLE_TO_RETRIEVE_ERROR = "Unable to Retrieve Users";

	public static String UNABLE_TO_COMMIT_ERROR = "Unable to Commit User";

	public static String UNABLE_TO_UPDATE_DEFAULT_STATUS_ERROR = "Unable to update status as Created";

	/** The INTERNA l_ error. */
	public static String INTERNAL_ERROR = "Internal Error";

	public static String CONCURRENCY_UPDATE_ERROR = "Concurrent Update Exists on this User, check for latest modifications and then perform changes";

	/** The N o_ record_ found_ error. */
	public static String UPDATE_NOT_AVAILABLE = "Update Not Available";

	public static String RECORD_NOT_FOUND = "User Does Not Exists";

	public static String UPDATE_CHECK_STATUS_NAMES = "'Developing','Testing','Published'";

	/** The USE r_ wit h_ setting s_ already_ exist_ error. */
	public static String USER_WITH_SETTINGS_AlREADY_EXIST_ERROR = "PERSIST_1011";

}
