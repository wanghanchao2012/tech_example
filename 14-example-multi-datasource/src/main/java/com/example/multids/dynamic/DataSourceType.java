package com.example.multids.dynamic;

public class DataSourceType {
    private static final ThreadLocal<String> TYPE = new InheritableThreadLocal<>();

    public static void setDataBaseType(String dataBase) {
        if (dataBase == null) {
            throw new NullPointerException();
        }
        System.out.println("[切换数据源到]:" + dataBase);
    }

    public static String getDataBaseType() {
        String database = TYPE.get();
        System.out.println("[当前数据源为]:" + database);
        return database;
    }

    public static void clearDataBaseType() {
        TYPE.remove();
    }
}
