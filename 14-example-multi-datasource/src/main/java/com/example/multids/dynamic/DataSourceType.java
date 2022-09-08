package com.example.multids.dynamic;

public class DataSourceType {
    private static final ThreadLocal<String> TYPE = new InheritableThreadLocal<>();

    public static void setDataBaseType(String dataBase) {
        if (dataBase == null) {
            throw new NullPointerException();
        }
        TYPE.set(dataBase);
        System.out.println("switch ds to :" + dataBase);
    }

    public static String getDataBaseType() {
        String database = TYPE.get();
        System.out.println("current ds is :" + database);
        return database;
    }

    public static void clearDataBaseType() {
        TYPE.remove();
    }
}
