package com.thread.book.ch3.safe;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ThreadLocalTest {
    private static ThreadLocal<Connection> connectionHolder = new ThreadLocal<Connection>(){
        @Override
        public Connection initialValue(){
            try {
                return DriverManager.getConnection("xxx");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    };
    public static Connection getConnection(){
        return connectionHolder.get();
    }
}
