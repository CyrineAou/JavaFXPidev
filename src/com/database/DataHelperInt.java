
package com.database;
public interface DataHelperInt {

    public boolean insert(String tableName, String p1, String p2);

    public boolean insert(String tableName, String p1, String p2, String p3);

    public boolean insert(String tableName, String p1, String p2, String p3, String p4);

    public boolean insert(String tableName, String p1, String p2, String p3, String p4, String p45);

    public boolean insert(String tableName, String p1, String p2, String p3, String p4, String p5, String p6);

    public boolean delete(String tableName, int condition);

    public boolean delete(String tableName, String fieldCondition, String condition);

    public boolean update(String sql);

    public boolean update(String tableName, String fields, String condition);
}
