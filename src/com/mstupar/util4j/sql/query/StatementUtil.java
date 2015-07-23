/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mstupar.util4j.sql.query;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Date;

/**
 *
 * @author Mihailo Stupar
 */
public class StatementUtil {

    /**
     * This method 'fills' parameters to the prepared statement. You need to worry 
     * about order of input parameters for statement.<br/>
     * <br/>
     * <b>Example:</b><br/>
     * <code>
     * PreparedStatement ps = con.prepareStatement(query);<br/>
     * StatementUtil.fill(ps, 10, 3.5, new Date(), null, false , "text");<br/>
     * ps.executeUpdate();<br/>
     * </code>
     * @param statement PreparedStatement which is previously created with SQL query.
     * @param values Values that will be inserted into preparedStatement.<br/>
     * <b>Note: </b><br/>
     * Order of these values must be same as order in SQL query:<br/>
     * <code>
     * INSERT INTO table (columnInt, columnDate) VALUES(?,?) <br/>
     * StatementUtil.fill(ps,25 ,new Date());
     * </code>
     * <br/><br/>
     * If you don't use explicit definition of column order in SQL query: 
     * (<code>INSERT INTO table VALUES(?,?,?)</code>, 
     * order of values in method must be same as order of column in table of 
     * database.
     * <br/><br/>
     * Supported values:
     * <ul>
     * <li>int, Integer</li>
     * <li>double, Double</li>
     * <li>String</li>
     * <li>java.util.Date</li>
     * <li>null</li>
     * <li>long, Long</li>
     * </ul>
     * You also have to worry about number of input parameters.
     */
    public static void fill(PreparedStatement statement, Object... values) {
        try {
            for (int itter = 0; itter < values.length; itter++) {
                int i = itter+1;
                Object value = values[itter];
                if (value == null)
                    statement.setNull(i, Types.NULL);
                else if (value.getClass() == int.class) {
                    statement.setInt(i, (int) value);
                } else if (value instanceof Integer) {
                    statement.setInt(i, (Integer) value);
                } else if (value.getClass() == double.class) {
                    statement.setDouble(i, (double) value);
                } else if (value instanceof Double) {
                    statement.setDouble(i, (double) value);
                } else if (value instanceof String) {
                    statement.setString(i, (String) value);
                } else if (value instanceof Boolean) {
                    statement.setBoolean(i, (Boolean) value);
                } else if (value.getClass() == boolean.class) {
                    statement.setBoolean(i, (boolean) value);
                } else if (value instanceof Date) {
                    java.sql.Date sqlDate = new java.sql.Date(((Date) value).getTime());
                    statement.setDate(i, sqlDate);
                } else if (value.getClass() == long.class) {
                    statement.setLong(i, (long) value);
                } else if (value instanceof Long) {
                    statement.setLong(i, (Long) value);
                }
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);

        }
    }

   
}
