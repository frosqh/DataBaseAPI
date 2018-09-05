package com.frosqh.dataBaseAPI;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionSQLite {

    private String filename;

    private final String prefix = "jdbc:sqlite";

    private Connection connect;

    private final Logger log = LogManager.getLogger(this.getClass());

    public ConnectionSQLite(String filename){
        this.filename = filename;
        connect = null;
    }

    public ConnectionSQLite(){
        this("database.db");
    }

    public Connection getInstance(){
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e){
            log.error("JDBC not found");
        }
        if (connect==null){
            try {
                connect = DriverManager.getConnection(prefix+filename);
            } catch (SQLException e) {
                log.error("SQL Exception",e.getMessage());
            }
        }
        return connect;
    }
}
