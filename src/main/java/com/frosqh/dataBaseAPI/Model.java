package com.frosqh.dataBaseAPI;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

public class Model  {
    public static String tableName;
    public static List<Field> FIELDS;
    private HashMap<String, Object> values;

    public Object get(String name){
        return values.get(name);
    }

    public void set(String name, Object o){
        values.put(name,o);
    }

    public Model(ResultSet resultSet){
        init(resultSet);
    }

    public Model(){

    }

    public void init(ResultSet resultSet){
        try {
            for (Field a : FIELDS){
                String n = a.getName();
                set(n, resultSet.getObject(n));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
