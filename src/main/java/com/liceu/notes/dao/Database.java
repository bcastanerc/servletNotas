package com.liceu.notes.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class Database {
    private static Connection connection;

    static Connection getConnection(){
        try{
            String url = "jdbc:sqlite:C:\\Users\\usuario\\Desktop\\Grado_Superior_segundo\\sqlite3\\sqlite-tools-win32-x86-3330000\\notesDatabase.db";
            if (connection  == null){
                Class.forName("org.sqlite.JDBC");
                connection = DriverManager.getConnection(url);
            }
            return connection;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
