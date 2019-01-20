package com.matao.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class RefInvoke {

    private RefInvoke() throws InstantiationException {
        throw new InstantiationException("RefInvoke cannot be instantiated");
    }

    public static Object createObject(String className) {
        Class[] paramTypes = {};
        Object[] paramValues = {};

        return createObject(className, paramTypes, paramValues);
    }

    public static Object createObject(Class clazz) {
        Class[] paramTypes = {};
        Object[] paramValues = {};

        return createObject(clazz, paramTypes, paramValues);
    }

    public static Object createObject(String className, Class paramType, Object paramValue) {
        Class[] paramTypes = {paramType};
        Object[] paramValues = {paramValue};

        return createObject(className, paramTypes, paramValues);
    }


    public static Object createObject(Class clazz, Class paramType, Object paramValue) {
        Class[] paramTypes = {paramType};
        Object[] paramValues = {paramValue};

        return createObject(clazz, paramTypes, paramValues);
    }

    public static Object createObject(String className, Class[] paramTypes, Object[] paramValues) {
        try {
            Class clazz = Class.forName(className);
            return createObject(clazz, paramTypes, paramValues);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Object createObject(Class clazz, Class[] paramTypes, Object[] paramValues) {
        try {
            Constructor constructor = clazz.getDeclaredConstructor(paramTypes);
            constructor.setAccessible(true);
            return constructor.newInstance(paramValues);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public static Object invokeInstanceMethod(Object obj, String methodName) {
        Class[] paramTypes = {};
        Object[] paramValues = {};

        return invokeInstanceMethod(obj, methodName, paramTypes, paramValues);
    }

    public static Object invokeInstanceMethod(Object obj, String methodName, Class paramType, Object paramValue) {
        Class[] paramTypes = {paramType};
        Object[] paramValues = {paramValue};

        return invokeInstanceMethod(obj, methodName, paramTypes, paramValues);
    }

    public static Object invokeInstanceMethod(Object obj, String methodName, Class[] paramTypes, Object[] paramValues) {
        if (obj == null) return null;

        try {
            //调用一个private方法
            Method method = obj.getClass().getDeclaredMethod(methodName, paramTypes); //在指定类中获取指定的方法
            method.setAccessible(true);
            return method.invoke(obj, paramValues);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public static Object invokeStaticMethod(String className, String methodName, Class paramType, Object paramValue) {
        Class[] paramTypes = {paramType};
        Object[] paramValues = {paramValue};

        return invokeStaticMethod(className, methodName, paramTypes, paramValues);
    }

    public static Object invokeStaticMethod(String className, String methodName, Class[] paramTypes, Object[] paramValues) {
        try {
            Class clazz = Class.forName(className);
            return invokeStaticMethod(clazz, methodName, paramTypes, paramValues);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Object invokeStaticMethod(Class clazz, String methodName) {
        Class[] paramTypes = {};
        Object[] paramValues = {};

        return invokeStaticMethod(clazz, methodName, paramTypes, paramValues);
    }

    public static Object invokeStaticMethod(Class clazz, String methodName, Class paramType, Object paramValue) {
        Class[] paramTypes = {paramType};
        Object[] paramValues = {paramValue};

        return invokeStaticMethod(clazz, methodName, paramTypes, paramValues);
    }

    public static Object invokeStaticMethod(Class clazz, String methodName, Class[] paramTypes, Object[] paramValues) {
        try {
            Method method = clazz.getDeclaredMethod(methodName, paramTypes);
            method.setAccessible(true);
            return method.invoke(null, paramValues);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public static Object getField(Object obj, String fieldName) {
        return getField(obj.getClass(), obj, fieldName);
    }

    public static Object getField(String className, Object obj, String fieldName) {
        try {
            Class clazz = Class.forName(className);
            return getField(clazz, obj, fieldName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Object getField(Class clazz, Object obj, String fieldName) {
        try {
            Field field = clazz.getDeclaredField(fieldName);
            field.setAccessible(true);
            return field.get(obj);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public static void setField(Object obj, String fieldName, Object fieldValue) {
        setField(obj.getClass(), obj, fieldName, fieldValue);
    }

    public static void setField(String className, Object obj, String fieldName, Object fieldValue) {
        try {
            Class clazz = Class.forName(className);
            setField(clazz, obj, fieldName, fieldValue);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void setField(Class clazz, Object obj, String fieldName, Object fieldValue) {
        try {
            Field field = clazz.getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(obj, fieldValue);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static Object getStaticField(String className, String fieldName) {
        return getField(className, null, fieldName);
    }

    public static Object getStaticField(Class clazz, String fieldName) {
        return getField(clazz, null, fieldName);
    }

    public static void setStaticField(String classname, String filedName, Object fieldValue) {
        setField(classname, null, filedName, fieldValue);
    }

    public static void setStaticField(Class clazz, String filedName, Object fieldValue) {
        setField(clazz, null, filedName, fieldValue);
    }
}
