/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mstupar.util4j.sql.query;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


/**
 *
 * @author Mihailo Stupar
 */
public class MySql {

    public static class query {

        private String text;
        private boolean isFormat;

        public query() {
            text = "";
            isFormat = false;
        }
        /**
         * <b>Example:</b><br/> 
         * {@code SELECT() }
         *
         * @return {@code SELECT }
         */
        public query SELECT() {
            if (isFormat)
                text += "\nSELECT ";
            else 
                text += " SELECT ";
            return this;
        }
        /**
         * <b>Example:</b><br/> 
         * {@code {@code INSERT() }
         *
         * @return INSERT }
         */
        public query INSERT() {
            if (isFormat)
                text += "\nINSERT ";
            else 
                text += " INSERT ";
            return this;
        }
        /**
         * <b>Example:</b><br/> 
         * {@code UPDATE() }
         *
         * @return {@code UPDATE }
         */
        public query UPDATE() {
            if (isFormat)
                text += "\nUPDATE ";
            else 
                text += " UPDATE ";
            return this;
        }
        /**
         * <b>Example:</b><br/> 
         * {@code DELETE() }
         *
         * @return {@code DELETE }
         */
        public query DELETE() {
            if (isFormat)
                text += "\nDELETE ";
            else 
                text += " DELETE ";
            return this;
        }
        /**
         * <b>Note:</b><br/>
         * This method does <b>NOT ADD</b> brackets around column names.
         * <br/><br/>
         * <b>Example:</b><br/> 
         * {@code COLUMNS("Column1","Column2","column3") }
         *
         * @return {@code Column1,Column2,column3 }
         */
        public query COLUMNS(String... columns) {
            if (columns == null || columns.length == 0) {
                return this;
            }
            if (columns.length == 1) {
                text += columns[0] + " ";
                return this;
            }
            for (int i = 0; i < columns.length - 1; i++) {
                text += columns[i] + ",";
            }
            text += columns[columns.length - 1] + " ";
            return this;
        }
        /**
         * <b>Note:</b><br/>
         * This method <b>ADD</b> brackets around column names.
         * <br/><br/>
         * <b>Example:</b><br/> 
         * {@code COLUMNS〱ノ("Column1","Column2","Column3") }
         *
         * @return {@code (Column1,Column2,Column3) }
         */
        public query COLUMNS〱ノ(String ... columns) {
            text+= "(";
            if (columns == null || columns.length == 0) {
                text+=")";
                return this;
            }
            if (columns.length == 1) {
                text += columns[0] + ") ";
                return this;
            }
            for (int i = 0; i < columns.length - 1; i++) {
                text += columns[i] + ",";
            }
            text += columns[columns.length - 1] + ") ";
            return this;
        }
        /**
         * Open the bracket<br/>
         * <b>Example:</b><br/> 
         * {@code OPEN() }
         *
         * @return {@code ( }
         * @see _〱_()
         */
        public query OPEN() {
            text += " ( ";
            return this;
        }
        /**
         * Closes the bracket<br/>
         * <b>Example:</b><br/> 
         * {@code CLOSE() }
         *
         * @return {@code ) }
         * @see _ノ_()
         */
        public query CLOSE() {
            text += " ) ";
            return this;
        }
         /**
         * <b>Example:</b><br/> 
         * {@code FROM() }
         *
         * @return {@code FROM }
         */
        public query FROM() {
            if (isFormat)
                text += "\nFROM ";
            else 
                text += " FROM ";
            return this;
        }
        /**
         * <b>Example:</b><br/> 
         * {@code FROM("Table1") }
         *
         * @return {@code FROM Table1 }
         */
        public query FROM(String table) {
            if (isFormat)
                text += "\nFROM " + table + " ";
            else
                text += "FROM " + table + " ";
            return this;
        }
        /**
         * <b>Note:</b><br/>
         * This method does <b>NOT ADD</b> brackets around table names.
         * <br/><br/>
         * <b>Example:</b><br/> 
         * {@code TABLES("Table1","Table2","Table3") }
         *
         * @return {@code Table1,Table2,Table3 }
         */
        public query TABLES(String... tables) {
            if (tables == null || tables.length == 0) {
                return this;
            }
            if (tables.length == 1) {
                text += tables[0] + " ";
                return this;
            }
            for (int i = 0; i < tables.length - 1; i++) {
                text += tables[i] + ",";
            }
            text += tables[tables.length - 1] + " ";
            return this;
        }
       /**
         * <b>Note:</b><br/>
         * This method <b>ADD</b> brackets around table names.
         * <br/><br/>
         * <b>Example:</b><br/> 
         * {@code TABLES〱ノ("Table1","Table2","Table3") }
         *
         * @return {@code (Table1,Table2,Table3) }
         */
        public query TABLES〱ノ(String... tables) {
            text+= "(";
            if (tables == null || tables.length == 0) {
                text+=")";
                return this;
            }
            if (tables.length == 1) {
                text += tables[0] + ") ";
                return this;
            }
            for (int i = 0; i < tables.length - 1; i++) {
                text += tables[i] + ",";
            }
            text += tables[tables.length - 1] + ") ";
            return this;
        }
         /**
         * <b>Example:</b><br/> 
         * {@code TABLE("Table1") }
         *
         * @return {@code Table1 }
         */
        public query TABLE(String table) {
            text += " " + table + " ";
            return this;
        }
        /**
         * <b>Example:</b><br/> 
         * {@code ALL() }
         *
         * @return {@code * }
         */
        public query ALL() {
            text += " * ";
            return this;
        }
        /**
         * <b>Example:</b><br/> 
         * {@code COUNT("Column1") }
         *
         * @return {@code COUNT(Column1) }
         */
        public query COUNT(String column) {
            text += "COUNT(" + column + ") ";
            return this;
        }
        /**
         * Comma sign.<br/>
         * <b>Example:</b><br/> 
         * {@code _ｧ_() }
         * 
         * @return {@code  , }
         * @see COMMA()
         */
        public query _ｧ_() {
            text += ",";
            return this;
        }
         /**
         * Comma sign.<br/>
         * <b>Example:</b><br/> 
         * {@code COMMA() }
         *
         * @return {@code  , }
         * @see _ｧ_()
         */
        public query COMMA() {
            text += ",";
            return this;
        }
        /**
         * <b>Example:</b><br/> 
         * {@code COLUMN("Column1") }
         * 
         * @return {@code  Column1 }
         */
        public query COLUMN(String column) {
            text += column + " ";
            return this;
        }
       /**
        * 
         * <b>Example:</b><br/> 
         * {@code WHERE() }
         *
         * @return {@code WHERE }
         */
        public query WHERE() {
            if (isFormat)
                text += "\nWHERE ";
            else
                text += " WHERE ";
            return this;
        }
        /**
         * <b>Note:</b><br/>
         * Add input text to the query in same format.
         * <br/><br/>
         * <b>Example:</b><br/> 
         * {@code TEXT("Select * asd asd" }
         *
         * @return {@code Select * asd asd }
         */
        public query TEXT(String input) {
            text += " " + input + " ";
            return this;
        }
        /**
         * <b>Example:</b><br/> 
         * {@code MAX("Column1") }
         *
         * @return {@code MAX(Column1) }
         */
        public query MAX(String column) {
            text += " MAX(" + column + ") ";
            return this;
        }
        /**
         * <b>Example:</b><br/> 
         * {@code ORDER_BY("Column1") }
         *
         * @return {@code ORDER BY Column1 }
         */
        public query ORDER_BY(String column) {
            if (isFormat)
                text += "\nORDER BY " + column + " ";
            else
                text += "ORDER BY " + column + " ";
            return this;
        }
        /**
         * <b>Example:</b><br/> 
         * {@code ASC() }
         *
         * @return {@code ASC }
         */
        public query ASC() {
            text += " ASC ";
            return this;
        }
        /**
         * <b>Example:</b><br/> 
         * {@code DESC() }
         *
         * @return {@code DESC }
         */
        public query DESC() {
            text += " DESC ";
            return this;
        }
        /**
         * <b>Example:</b><br/> 
         * {@code JOIN }
         *
         * @return {@code JOIN }
         */
        public query JOIN() {
            text += " JOIN ";
            return this;
        }
        /**
         * <b>Example:</b><br/> 
         * {@code JOIN("Table1") }
         *
         * @return {@code  JOIN Table1  }
         */
        public query JOIN(String table) {
            text += " JOIN " + table + " ";
            return this;
        }
        /**
         * <b>Note:</b><br/>
         * You have to specify first and second values in proper way.<br/>
         * If you decide to use aliases write:<br/>
         * {@code ON("t1.column","t2.column") }
         * <br/><br/>
         * Or if you use full qualified names:<br/>
         * {@code ON("table1.column","table2.column") }
         *
         * @return For aliases: {@code  ON t1.column = t2.column  }
         */
        public query ON(String firstValue, String secondValue) {
            text += " ON " + firstValue + " = " + secondValue + " ";
            return this;
        }
        /**
         * <b>Example:</b><br/> 
         * {@code USING("Column1") }
         *
         * @return {@code  USING(Column1)  }
         */
        public query USING(String column) {
            text += " USING (" + column + ") ";
            return this;
        }
        /**
         * <b>Example:</b><br/> 
         * {@code INTO("Table1") }
         *
         * @return {@code  INTO Table1  }
         */
        public query INTO(String table) {
            text += " INTO " + table;
            return this;
        }
        /**
         * Supported types at this moment:<br/>
         * <ul>
         * <li>java.lang.String -> 'Some text'</li>
         * <li>java.util.Date -> 'YYYY-MM-DD HH:MM:SS'</li>
         * <li>int -> 3</li>
         * <li>double -> 3.5</li>
         * <li>null -> NULL</li>
         * <li>boolean -> TRUE <i>Note: boolean values will be presented as 0 (false) and 1 (true) in database</li> 
         * </ul>
         * <br/>
         * <b>Example:</b><br/> {@code VALUES(145, new Date(), null, 36.5,"text")}
         *
         * @return {@code VALUES (145,'2015-7-22 13:17:31',NULL,36.5,'text')}
         */
        public query VALUES(Object... values) {
            text += " VALUES (";
            if (values.length == 0) {
                text += ") ";
                return this;
            }
            for (int i = 0; i < values.length - 1; i++) {
                Object value = values[i];
                if (value == null) {
                    text += "NULL,";
                    continue;
                }
                if (value instanceof Date) {
                    Date date = (Date) value;
                    text += convertDatetoDateTime(date) + ",";
                    continue;
                }
                if (value instanceof String) {
                    String string = (String) value;
                    text += "'" + string + "',";
                    continue;
                }
                if ((value instanceof Integer) || (value instanceof Double)) {
                    text += value + ",";
                }
                if (value instanceof  Boolean) {
                    if ((boolean)value == true)
                        text+= "TRUE,";
                    else 
                        text+="FALSE,";
                }
                    
            }
            Object value = values[values.length - 1];
            if (value == null) {
                text += "NULL";
            }
            if (value instanceof Date) {
                Date date = (Date) value;
                text += convertDatetoDateTime(date);
            }
            if (value instanceof String) {
                String string = (String) value;
                text += "'" + string + "'";
            }
            if ((value instanceof Integer) || (value instanceof Double)) {
                text += value;
            }
            if (value instanceof Boolean) {
                if ((boolean) value == true) {
                    text += "TRUE";
                } else {
                    text += "FALSE";
                }
            }
            text += ")";
            return this;
        }
        /**
         * Open the bracket<br/>
         * <b>Example:</b><br/> 
         * {@code _〱_() }
         *
         * @return {@code ( }
         * @see OPEN()()
         */
        public query _〱_() {
            text += " ( ";
            return this;
        }
        /**
         * Open the bracket<br/>
         * <b>Example:</b><br/> {@code _ノ_() }
         *
         * @return {@code ( }
         * @see CLOSE()()
         */
        public query _ノ_() {
            text += " ) ";
            return this;
        }
        /**
         * <b>Example:</b><br/> 
         * {@code GROUP_BY("Column1","Column2") }
         *
         * @return {@code GROUP BY Column1, Column2  }
         */
        public query GROUP_BY(String... columns) {
            if(isFormat)
                text+="\nGROUP BY ";
            else
                text+=" GROUP BY ";
            for (int i = 0; i < columns.length-1; i++) {
                text+=columns[i]+", ";
            }
            text+=columns[columns.length-1]+" ";
            return this;
        }
        /**
         * <b>Note:</b><br/>
         * This method creates as many question marks as the input parameter is. <br/>
         * It also added brackets around this question marks. <br/>
         * <br/>
         * <b>Example:</b><br/> 
         * {@code QUESTION_MARKS(5) }
         *
         * @return {@code (?,?,?,?,?) }
         */
        public query QUESTION_MARKS(int number) {
            text+=" (";
            for (int i = 0; i < number-1; i++) {
                text+="?,";
            }
            if (number > 0)
                text+="?";
            text+=")";
            return this;
        }
         /**
         * <b>Example:</b><br/> 
         * {@code VALUES() }
         *
         * @return {@code VALUES  }
         */
        public query VALUES() {
            text += " VALUES ";
            return this;
        }
         /**
         * <b>Example:</b><br/> 
         * {@code SET() }
         *
         * @return {@code SET  }
         */
        public query SET() {
            if (isFormat)
                text += "\nSET ";
            else
                text+=" SET ";
            return this;
        }
         /**
         * <b>Example:</b><br/> 
         * {@code VALUES(5) }
         *
         * @return {@code VALUES(?,?,?,?,?)  }
         */
        public query VALUES(int questionMarks) {
            text+=" VALUES";
            QUESTION_MARKS(questionMarks);
            return this;
        }
        /**
         * <b>Note:</b><br/>
         * This method creates single question mark without brackets. <br/>
         * <br/>
         * <b>Example:</b><br/> 
         * {@code QUESTION_MARK() }
         *
         * @return {@code ? }
         */
        public query QUESTION_MARK() {
            text+=" ? ";
            return this;
        }
        
        
        /**
         * Call this method if you want formatted resulted query. 
         */
        public query format() {
            isFormat = true;
            return this;
        }
        
        /**
         * Call this method at the end of your query.
         * @return SQL query
         */
        public String build() {
            text = text.replaceAll("  ", " ");
            return text.trim();
        }
        
         private  String convertDatetoDateTime(Date date) {
            String mySqlDate = "'";
            Calendar calendar = new GregorianCalendar();
            calendar.setTime(date);

            mySqlDate += calendar.get(Calendar.YEAR) + "-";
            mySqlDate += (calendar.get(Calendar.MONTH) + 1) + "-";
            mySqlDate += calendar.get(Calendar.DAY_OF_MONTH) + " ";
            mySqlDate += calendar.get(Calendar.HOUR_OF_DAY) + ":";
            mySqlDate += calendar.get(Calendar.MINUTE) + ":";
            mySqlDate += calendar.get(Calendar.SECOND) + "'";

            return mySqlDate;
        }
    }

    private String convertDatetoDateTime(Date date) {
        String mySqlDate = "'";
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);

        mySqlDate += calendar.get(Calendar.YEAR) + "-";
        mySqlDate += (calendar.get(Calendar.MONTH) + 1) + "-";
        mySqlDate += calendar.get(Calendar.DAY_OF_MONTH) + " ";
        mySqlDate += calendar.get(Calendar.HOUR_OF_DAY) + ":";
        mySqlDate += calendar.get(Calendar.MINUTE) + ":";
        mySqlDate += calendar.get(Calendar.SECOND) + "'";

        return mySqlDate;
    }

}
