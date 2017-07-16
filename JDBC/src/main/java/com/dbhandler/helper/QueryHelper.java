package com.dbhandler.helper;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import javax.naming.NamingException;

import com.pojo.EmployerEnum;
import com.pojo.GenderEnum;

public class QueryHelper {

	/**
	 * Set statement parameters from a list of values.
	 *
	 * @param stat
	 *            The statement to which data shall be added.
	 * @param values
	 *            The parameter values.
	 * @throws NamingException
	 * @throws SQLException
	 */
	protected static void setParameters(final PreparedStatement stat, final Object[] values)
			throws NamingException, SQLException {
		for (int i = 0; i < values.length; i++) {
			setParameter(stat, i + 1, values[i]);
		}
	}

	/**
	 * Set statement parameters from a list of values.
	 *
	 * @param stat
	 *            The statement to which data shall be added.
	 * @param values
	 *            The parameter values.
	 * @throws NamingException
	 * @throws SQLException
	 */
	protected static void setParameters(final PreparedStatement stat, final Collection<? extends Object> values)
			throws NamingException, SQLException {
		int parameterNo = 1;
		for (final Iterator<? extends Object> iter = values.iterator(); iter.hasNext();) {
			setParameter(stat, parameterNo++, iter.next());
		}
	}

	/**
	 * Prepare the statement with a value.
	 *
	 * @param stat
	 *            The statement to which data shall be added.
	 * @param index
	 *            The parameter number.
	 * @param value
	 *            The parameter value.
	 * @throws NamingException
	 * @throws SQLException
	 */
	static void setParameter(final PreparedStatement pst, final int index, final Object value)
			throws NamingException, SQLException {
		//LOGGER.finest(index + ": " + value);

		if (value == null) {
			pst.setNull(index, java.sql.Types.VARCHAR);
		} else if (value instanceof String) {
			pst.setString(index, (String) value);
		} else if (value instanceof Integer) {
			pst.setInt(index, ((Integer) value).intValue());
		} else if (value instanceof Long) {
			pst.setLong(index, (Long) value);
		} else if (value instanceof Double) {
			pst.setDouble(index, ((Double) value).doubleValue());
		} else if (value instanceof Date) {
			pst.setTimestamp(index, new java.sql.Timestamp(((Date) value).getTime()));
		}/* else if (value instanceof ByteArray) {
			pst.setBytes(index, ((ByteArray) value).getBytes());
		}*/
		// for Enums
		else if (value instanceof GenderEnum || value instanceof EmployerEnum) {
			pst.setString(index, value.toString());
		}
		else {
			throw new IllegalArgumentException("Wrong data type: " + value.getClass());
		}
	}

}
