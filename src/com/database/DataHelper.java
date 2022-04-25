package com.database;

/**
 * @author Yousra
 */
public class DataHelper extends DatabaseHandler implements DataHelperInt {

    private static DataHelper dataHelper = null;

    public DataHelper() {

    }

    public static DataHelper getInstance() {
        if (dataHelper == null) {
            dataHelper = new DataHelper();
        }
        return dataHelper;
    }

   
    public boolean insert(String tableName, String value) {
        return execAction("INSERT INTO " + tableName + " VALUES('" + value + "');");
    }

    /*
       insert into pharmacydatabase.users(UserName , UserPassword , Permission ) value('root' , 'admin' , 'admin');
     */
    @Override
    public boolean insert(String tableName, String p1, String p2) {
        return execAction("INSERT INTO " + tableName + " VALUES('" + p1 + "', '" + p2 + "');");
    }

    @Override
    public boolean insert(String tableName, String p1, String p2, String p3) {
        boolean execAction = execAction("INSERT INTO " + tableName
                + " VALUES('" + p1 + "', '" + p2 + "', '" + p3 + "');");
        System.out.println(execAction);
        return execAction;
    }

    @Override
    public boolean insert(String tableName, String p1, String p2, String p3, String p4) {
        return execAction("INSERT INTO " + tableName
                + " VALUES('" + p1 + "', '" + p2 + "', '" + p3 + "', '" + p4 + "');");

    }

    @Override
    public boolean insert(String tableName, String p1, String p2, String p3, String p4, String p5) {
        String sql = "INSERT INTO " + tableName
                + " VALUES('" + p1 + "', '" + p2 + "', '" + p3 + "', '" + p4 + "', '" + p5 + "' );";
        return execAction(sql);
    }

    @Override
    public boolean insert(String tableName, String p1, String p2, String p3, String p4, String p5, String p6) {
        String sql = "INSERT INTO " + tableName
                + " VALUES('" + p1 + "', '" + p2 + "', '" + p3 + "', '" + p4 + "', '" + p5 + "',"
                + "'" + p6 + "' );";
        return execAction(sql);
    }

    public boolean insert(String tableName, String p1, String p2, String p3, String p4, String p5, String p6, String p7) {
        String sql = "INSERT INTO " + tableName
                + " VALUES('" + p1 + "', '" + p2 + "', '" + p3 + "', '"
                + p4 + "', '" + p5 + "', '" + p6 + "', '" + p7 + "');";
        return execAction(sql);
    }

    /*
    delete FROM pharmacydatabase.users where ID = 4;
     */
    @Override
    public boolean delete(String tableName, int condition) {
        boolean execAction = execAction("DELETE FROM " + tableName + " WHERE ID  ='" + condition + "' ; ");
        if (execAction) {
            System.out.println("Data Succesfully deleted   ...");
        }
        return execAction;
    }

    @Override
    public boolean delete(String tableName, String fieldCondition, String condition) {
        boolean execAction = execAction("DELETE FROM  " + tableName + "  WHERE  " + fieldCondition + "   =  '" + condition + "' ; ");
        if (execAction) {
            System.out.println("Data deleted  Succesfully ...");
        }
        return execAction;
    }

    public String concatUpdate(String tableName, String fields, String condition) {
        return " UPDATE " + tableName + " set " + fields + " WHERE ID =" + condition + ";";
    }

    @Override
    public boolean update(String sql) {
        boolean execAction = execAction(sql);
        if (execAction) {
            System.out.println("Data updated Succesfully ...");
        }
        return execAction;
    }

    /*
update pharmacydatabase.users set UserName = 'aram' where id = 2
     */
    @Override
    public boolean update(String tableName, String fields, String condition) {
        boolean execAction = execAction(" UPDATE " + tableName + " set " + fields + " WHERE ID =" + condition + ";");
        if (execAction) {
            System.out.println("Data updated Succesfully ...");
        }
        return execAction;
    }
}
