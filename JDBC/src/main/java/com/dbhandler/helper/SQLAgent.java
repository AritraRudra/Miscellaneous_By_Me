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
//import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.naming.NamingException;

/**
 * @author Aritra
 */
public class SQLAgent {

	private final String className;// = this.getClass().toString();// = SQLAgent.class.getName();
	// java util logging.
	private static final Logger logger = Logger.getLogger(SQLAgent.class.getPackage().getName());
	// slff4j
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
		/*
		try {
			Class.forName(JDBC_DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		*/
		logger.exiting(className, "SQLAgent");
	}

	public static SQLAgent getInstance() {
		return instance;
	}

	public Connection getConnection() throws SQLException {
		initializeConnection();
		return SQLAgent.conn;
	}

	private void initializeConnection() throws SQLException {
		System.out.println("BLEH "+JDBC_DRIVER+" "+" "+DB_URL+" "+USER+" "+PASS);
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
	 * For single param
	 * @param queryString
	 * @param paramObj
	 * @return
	 * @throws NamingException
	 * @throws SQLException
	 */
	public ResultSet dbSelectQuery(final String selectString, final Object param) throws NamingException, SQLException {
		final String methodName = "dbSelect";
		logger.entering(className, methodName);
		final PreparedStatement preparedStatement = getPreparedStatement(selectString);
		try {
			QueryHelper.setParameter(preparedStatement, 1, param);
			return preparedStatement.executeQuery(selectString);
		} catch (final SQLException e) {
			closeSQLStatement(preparedStatement);
			throw e;
		} finally {
			logger.exiting(className, methodName);
		}
	}

	/**
	 * 
	 * @param queryString
	 * @param paramObj
	 * @return
	 * @throws NamingException
	 * @throws SQLException
	 */
	public ResultSet dbSelect(final String selectString, Object[] params) throws NamingException, SQLException {
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

}