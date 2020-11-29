package com.liceu.notes.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    private static Connection connection;
    static Connection getConnection(){
        try{
            if (connection  == null){
                String url = "jdbc:sqlite:C:\\Users\\usuario\\Desktop\\Grado_Superior_segundo\\sqlite3\\sqlite-tools-win32-x86-3330000\\notesDatabase.db";
                Class.forName("org.sqlite.JDBC");
                connection = DriverManager.getConnection(url);
                connection.createStatement().execute("PRAGMA foreign_keys = ON");
            }
            return connection;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error Database conection");
        }
        return null;
    }

    static void closeConnection() throws SQLException {
        connection.close();
        connection = null;
    }
}
