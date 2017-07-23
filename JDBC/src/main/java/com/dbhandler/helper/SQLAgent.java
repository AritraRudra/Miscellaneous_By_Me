package com.dbhandler.helper;

import java.io.FileInputStream;
import java.io.IOException;

/** SQL related */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/** Util related */
import java.util.Collections;
import java.util.Map;
import java.util.Properties;
import java.util.WeakHashMap;
/** j.u.l */

import java.util.logging.Level;
import java.util.logging.Logger;

/** SLF4J loggers */
/*
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
*/

import javax.naming.NamingException;

/**
 * @author Aritra
 */
public class SQLAgent {

	private final String className;// = this.getClass().toString();// = SQLAgent.class.getName();
	// java util logging.
	private static final Logger logger = Logger.getLogger(SQLAgent.class.getPackage().getName());
	// slf4j
	//protected static final Logger logger = LoggerFactory.getLogger(SQLAgent.class);

	/**
	 * Contains all open statements, to avoid unnoticed resource leaks.
	 */
	private static final Map<PreparedStatement, Thread> openStatements
		= Collections.synchronizedMap(new WeakHashMap<PreparedStatement, Thread>());

	private static final Properties dbProps;
	// Not a very good way to do I guess
	static {
		dbProps = new Properties();
		try {
			FileInputStream fio = new FileInputStream("db.prop");
			try {
				dbProps.load(fio);
			} finally {
				fio.close();
			}
		} catch (final IOException ioex) {

		}
		JDBC_DRIVER = dbProps.getProperty("db.driver");
		DB_URL = dbProps.getProperty("db.host");
		USER = dbProps.getProperty("db.user");
		PASS = dbProps.getProperty("db.password");
	}
	/**
	 * The instance to be used in this process.
	 */
	private static final SQLAgent instance = new SQLAgent();

	/*
	 * You can set a final variable only in a constructor or in an initializer. Regular methods cannot change the value of variables declared final.
	 * https://stackoverflow.com/a/11583569/1679643
	 */
	private static Connection conn = null;
	/** The JDBC driver. */
	private static final String JDBC_DRIVER;// = "com.mysql.jdbc.Driver";
	/** Database URL. */
	private static final String DB_URL;
	/** Database user.*/
	private static final String USER;
	private static final String PASS;

	/**
	 * Default constructor
	 */
	private SQLAgent() {
		className = this.getClass().toString();
		logger.entering(className, "SQLAgent");
		//logger.trace("Entering {} {}",className, "SQLAgent");
		/*
		try {
			Class.forName(JDBC_DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		*/
		logger.exiting(className, "SQLAgent");
		//logger.trace("Exiting {} {}",className, "SQLAgent");
	}

	public static SQLAgent getInstance() {
		return instance;
	}

	public Connection getConnection() throws SQLException {
		initializeConnection();
		return SQLAgent.conn;
	}

	private void initializeConnection() throws SQLException {
		//System.out.println("BLEH "+JDBC_DRIVER+" "+" "+DB_URL+" "+USER+" "+PASS);
		if(SQLAgent.conn == null){
			SQLAgent.conn = DriverManager.getConnection(DB_URL, USER, PASS);
		}
	}

	private PreparedStatement getPreparedStatement(final String pst) throws SQLException {
		try {
			final PreparedStatement preparedStatement = getConnection().prepareStatement(pst);
			openStatements.put(preparedStatement, Thread.currentThread());
			return preparedStatement;
		} catch (final SQLException sqlEx) {
			logger.log(Level.FINEST, sqlEx.getMessage(), sqlEx);
			//logger.error(sqlEx.getMessage());
			sqlEx.printStackTrace();
			throw sqlEx;
		}
	}

	/**
	 * Close the Statement after it has been used.
	 *
	 * @param stat
	 *            Statement to close
	 */
	void closeSQLStatement(final PreparedStatement pst) {
		if (pst != null) {
			try {
				pst.close();
				openStatements.remove(pst);
			} catch (final SQLException se) {
				logger.log(Level.FINEST, se.getMessage(), se);
			}
		}
	}
	
	/**
	 * @param insertString
	 *            an SQL INSERT statement
	 * @return Number of inserted rows.
	 * @throws SQLException
	 *             if the insertion failed
	 * @throws DbConnectionException
	 *             if a database access error occurs
	 */
	public int dbInsert(final String insertString) throws NamingException, SQLException {
		logger.entering(className, "dbInsert");
		logger.finest(insertString);
		PreparedStatement pstmnt = null;
		try {
			pstmnt = getPreparedStatement(insertString);
			return pstmnt.executeUpdate(insertString);
		} finally {
			closeSQLStatement(pstmnt);
			logger.exiting(className, "dbInsert");
		}
	}

	/**
	 * Create an {@link PreparedStatement} from the input statement string and
	 * add the supplied parameters. The execute the Insert, Delete or Update
	 * statement and return the number of rows changed.
	 *
	 * @param statement
	 *            An SQL string containing "?" as placeholders for parameters.
	 * @param values
	 *            A list of parameters. The type of parameters must correspond
	 *            to the type of column types used in the database. The
	 *            following types are implemented:
	 *            <dl>
	 *            <dt><b>null</b>
	 *            <dd>Inserted as a mysql null value (assuming the table is
	 *            defined to allow it)
	 *            <dt>{@link java.lang.String}
	 *            <dd>Shall be used for text and varchar columns.
	 *            <dt>{@link java.lang.Integer}
	 *            <dd>Shall be used for int columns.
	 *            <dt>{@link java.sql.Date}
	 *            <dd>Shall be used for datetime and timestamp columns.
	 *            </dl>
	 * @return The number of rows changed.
	 * @throws NamingException
	 * @throws SQLException
	 */
	public int dbInsert(final String insertString, final Object[] values) throws NamingException, SQLException {
		logger.entering(className, "dbInsert");
		logger.finest(insertString);

		final PreparedStatement pstmnt = getPreparedStatement(insertString);

		try {
			if (values != null) {
				QueryHelper.setParameters(pstmnt, values);
			}
			return pstmnt.executeUpdate();
		} finally {
			closeSQLStatement(pstmnt);
			logger.exiting(className, "dbInsert");
		}
	}

	/**
	 * Create an {@link PreparedStatement} from the input selectString string and
	 * add the supplied parameters. The execute the statement and return the
	 * result.
	 *
	 * @param selectString
	 *            An SQL string containing "?" as placeholders for parameters.
	 * @param value
	 * 			  The value for "? placeholder.		
	 * @return The result of the query.
	 * @throws NamingException
	 * @throws SQLException
	 */
	public ResultSet dbSelectQuery(final String selectString, final Object param) throws NamingException, SQLException {
		final String methodName = "dbSelect";
		logger.entering(className, methodName);
		final PreparedStatement preparedStatement = getPreparedStatement(selectString);
		try {
			QueryHelper.setParameter(preparedStatement, 1, param);
			// do not use this. this is to be used with Statement, not PreparedStatement, Again I did blunder copy-pasting :P 
			// see https://stackoverflow.com/questions/17323772/jdbc-sql-exception-query-executes-correctly-on-the-mysql-prompt-but-gives-error
			//return preparedStatement.executeQuery(selectString);
			return preparedStatement.executeQuery();
		} catch (final SQLException e) {
			closeSQLStatement(preparedStatement);
			throw e;
		} finally {
			logger.exiting(className, methodName);
		}
	}

	/**
	 * Create an {@link PreparedStatement} from the input selectString string and
	 * add the supplied parameters. The execute the statement and return the
	 * result.
	 *
	 * @param selectString
	 *            An SQL string containing "?" as placeholders for parameters.
	 * @param values
	 *            A list of parameters. The type of parameters must correspond
	 *            to the type of column types used in the database. The
	 *            following types are implemented:
	 *            <dl>
	 *            <dt><b>null</b>
	 *            <dd>Inserted as a mysql null value (assuming the table is
	 *            defined to allow it)
	 *            <dt>{@link java.lang.String}
	 *            <dd>Shall be used for text and varchar columns.
	 *            <dt>{@link java.lang.Integer}
	 *            <dd>Shall be used for int columns.
	 *            <dt>{@link java.sql.Date}
	 *            <dd>Shall be used for datetime and timestamp columns.
	 *            </dl>
	 * @return The result of the query.
	 * @throws NamingException
	 * @throws SQLException
	 */
	public ResultSet dbSelectQuery(final String selectString, Object[] params) throws NamingException, SQLException {
		final String methodName = "dbSelect";
		logger.entering(className, methodName);
		final PreparedStatement preparedStatement = getPreparedStatement(selectString);
		try {
			QueryHelper.setParameters(preparedStatement, params);
			return preparedStatement.executeQuery(selectString);
		} catch (final SQLException e) {
			closeSQLStatement(preparedStatement);
			throw e;
		} finally {
			logger.exiting(className, methodName);
		}
	}
	
	/**
	 * Create an {@link PreparedStatement} from the input statement string and
	 * add the supplied parameters. The execute the Insert, Delete or Update
	 * statement and return the number of rows changed.
	 *
	 * @param statement
	 *            An SQL string containing "?" as placeholders for parameters.
	 * @param values
	 *            A list of parameters. The type of parameters must correspond
	 *            to the type of column types used in the database. The
	 *            following types are implemented:
	 *            <dl>
	 *            <dt><b>null</b>
	 *            <dd>Inserted as a mysql null value (assuming the table is
	 *            defined to allow it)
	 *            <dt>{@link java.lang.String}
	 *            <dd>Shall be used for text and varchar columns.
	 *            <dt>{@link java.lang.Integer}
	 *            <dd>Shall be used for int columns.
	 *            <dt>{@link java.sql.Date}
	 *            <dd>Shall be used for datetime and timestamp columns.
	 *            </dl>
	 * @return The number of rows changed.
	 * @throws NamingException
	 * @throws SQLException
	 */
	public int dbUpdate(final String selectString, final Object[] values) throws NamingException, SQLException {
		logger.entering(className, "dbUpdate");
		final PreparedStatement pst = getPreparedStatement(selectString);
		try {
			QueryHelper.setParameters(pst, values);
			return pst.executeUpdate();
		} finally {
			closeSQLStatement(pst);
			logger.exiting(className, "dbUpdate");
		}
	}

}