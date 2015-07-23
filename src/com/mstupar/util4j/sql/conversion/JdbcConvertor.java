/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mstupar.util4j.sql.conversion;

import com.mstupar.util4j.sql.annotation.Column;
import com.mstupar.util4j.sql.exception.AnnotationException;
import com.mstupar.util4j.sql.exception.JavaBeanConventionException;
import com.mstupar.util4j.sql.exception.ResultSetException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Mihailo Stupar
 */
public class JdbcConvertor {

    /**
     * Converts result set record to the instance of target class. Target class
     * should be java bean by convention.<br/>
     * This class should have private field, public setters and getters (named
     * by convention: setAttribute) and default public constructor without
     * parameters. <br/>
     * One more condition needs to be satisfied. These attributes have to be
     * annotated with {@code com.mstupar.util4j.sql.annotation.Column}
     * annotation. This annotation has one parameter: {@code name} and its value
     * is name of the corresponding column name in database.<br/>
     * <br/>
     * <b>Note:</b><br/>
     * If some values are null in database, in instance of targeted class will
     * be set to default value. For structural types it would be null
     * (String,Integer,Date...) and for primitives to default value(int =
     * 0,boolean = false...)<br/>
     * <br/>
     * <b>Example:</b><br/>
     * {@code MyClass myClassObject = JdbcConvertor.getSingleResult(resultSet,MyClass.class,true)}
     * 
     * @param resultSet with records from database
     * @param targetClass, the class (created properly as described above) 
     * @param checkSingularity if result set should have only one record, with
     * this parameter set to {@code true} it will be checked. If result set has
     * more than one element {@link ResultSetException } will be thrown. If this
     * parameter is set to {@code false} first record in result set will be
     * converted.
     * @throws 
     * ResultSetException if the result set is null.<br/>
     * {@link JavaBeanConventionException} - if the java bean convention is not
     * satisfied.<br/> 
     * {@link AnnotationException} - if in the target class is not all
     * attributes annotated properly.<br/>
     * @return instance of targeted class or <b>null if result set is empty.<b/>
     */
    public static <T extends Object> T getSingleResult(ResultSet resultSet, Class<T> targetClass, boolean checkSingularity) {
        if (resultSet == null) {
            throw new ResultSetException("Result set is null.");
        }
        try {
            boolean hasRows = resultSet.first();
            if (!hasRows) {
                return null;
            }
        } catch (SQLException ex) { ex.printStackTrace(); }
        T target = getOneResult(resultSet, targetClass);
        try {
            if (checkSingularity && resultSet.next()) {
                throw new ResultSetException("Result set has more than single element.");
            }
        } catch (SQLException ex) { ex.printStackTrace(); }
        return target;
    }

    /**
     * Converts result set record to the instance of target class. Target class
     * should be java bean by convention.<br/>
     * This class should have private field, public setters and getters (named
     * by convention: setAttribute) and default public constructor without
     * parameters. <br/>
     * One more condition needs to be satisfied. These attributes have to be
     * annotated with {@code com.mstupar.util4j.sql.annotation.Column}
     * annotation. This annotation has one parameter: {@code name} and its value
     * is name of the corresponding column name in database.<br/>
     * If in the result set are more than one record, the method will throw
     * {@link ResultSetException}. To avoid this use method
     * {@link getSingleResult(ResulSet,Class,boolean)}.<br/>
     * <br/>
     * <b>Note:</b><br/>
     * If some values are null in database, in instance of targeted class will
     * be set to default value. For structural types it would be null
     * (String,Integer,Date...) and for primitives to default value(int =
     * 0,boolean = false...)<br/>
     * <br/>
     * <b>Example:</b><br/>
     * {@code MyClass myClassObject = JdbcConvertor.getSingleResult(resultSet,MyClass.class)}
     * 
     * @param resultSet with records from database
     * @param targetClass, the class (created properly as described above) 
     * @throws 
     * ResultSetException if the result set is null.<br/>
     * {@link JavaBeanConventionException} - if the java bean convention is not
     * satisfied.<br/> 
     * {@link AnnotationException} - if in the target class is not all
     * attributes annotated properly.<br/>
     * @return instance of targeted class or <b>null if result set is empty.<b/>
     */
    public static <T extends Object> T getSingleResult(ResultSet resultSet, Class<T> type) {
        return getSingleResult(resultSet, type, true);
    }
    
    
    /**
     * Converts all result set records to the list with instances of target class. Target class
     * should be java bean by convention.<br/>
     * This class should have private field, public setters and getters (named
     * by convention: setAttribute) and default public constructor without
     * parameters. <br/>
     * One more condition needs to be satisfied. These attributes have to be
     * annotated with {@code com.mstupar.util4j.sql.annotation.Column}
     * annotation. This annotation has one parameter: {@code name} and its value
     * is name of the corresponding column name in database.<br/>
     * <br/>
     * <b>Note:</b><br/>
     * If some values are null in database, in instances of targeted class will
     * be set to default value. For structural types it would be null
     * (String,Integer,Date...) and for primitives to default value(int =
     * 0,boolean = false...)<br/>
     * <br/>
     * <b>Example:</b><br/>
     * {@code MyClass myClassObject = JdbcConvertor.getResultList(resultSet,MyClass.class)}
     * 
     * @param resultSet with records from database
     * @param targetClass, the class (created properly as described above) 
     * @throws 
     * ResultSetException if the result set is null.<br/>
     * {@link JavaBeanConventionException} -  if the java bean convention is not
     * satisfied.<br/> 
     * {@link AnnotationException} - if in the target class is not all
     * attributes annotated properly.<br/>
     * @return List with instances of targeted class or <b>Empty list if result set is empty.<b/><br/>
     * The List is implemented as {@link ArrayList }
     */
    public static <T> List<T> getResultList(ResultSet resultSet, Class<T> targetClass) {
        List<T> list = new ArrayList<>();
        try {
            while (resultSet.next()) {
                Object tmp = getOneResult(resultSet, targetClass);
                if (tmp != null)
                    list.add((T) tmp);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }

    private static <T extends Object> T getOneResult(ResultSet resultSet, Class<T> type) {
        T target;
        try {
            target = type.newInstance();
        } catch (InstantiationException | IllegalAccessException ex) {
            ex.printStackTrace();
            throw new JavaBeanConventionException("Class " + type.getName() + " does not have default public empty constructor.");
        }
        Field[] fields = type.getDeclaredFields();
        for (Field field : fields) {
            String attrName = field.getName();
            String methodName = createSetMethodName(attrName);
            Class attrType = field.getType();
            Method method = null;
            try {
                method = type.getMethod(methodName, attrType);
            } catch (NoSuchMethodException | SecurityException ex) {
                ex.printStackTrace();
                throw new JavaBeanConventionException("Method: " + methodName + " does not exist in class " + type.getName());
            }
            Column columnAnn = field.getAnnotation(Column.class);
            String columnName = columnAnn.name();
            if (columnName == null || columnName.isEmpty()) {
                throw new AnnotationException("Annotation: " + columnAnn.toString() + " for attribute " + attrName + " [" + attrType + "] does not have attribute name.");
            }
            try {
                Object o = resultSet.getObject(columnName);
                if (o==null) 
                    method.invoke(target,getDefaultValue(attrType));
                else
                    method.invoke(target, resultSet.getObject(columnName));
            } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | SQLException ex) {
                ex.printStackTrace();
            }
        }

        return target;
    }

    
    private static Object getDefaultValue(Class attrType) {
        if (attrType == double.class)
            return 0.0;
        if (attrType == int.class)
            return 0;
        if (attrType == long.class)
            return 0;
        if (attrType == boolean.class)
            return false;
        return null; //attribute type is either String, Date, Integer, Double or other structural type
    }
    

    private static String createSetMethodName(String attribute) {
        return "set" + attribute.substring(0, 1).toUpperCase() + attribute.substring(1);
    }

}
