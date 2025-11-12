package com.example.library;

import java.sql.*;

public class DB {
    private static final String URL = "jdbc:sqlite:library.db";

    public static void init() {
        try (Connection conn = DriverManager.getConnection(URL)) {
            try (Statement st = conn.createStatement()) {
                st.executeUpdate("CREATE TABLE IF NOT EXISTS books (" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "title TEXT NOT NULL," +
                        "author TEXT NOT NULL," +
                        "issued INTEGER NOT NULL DEFAULT 0" +
                        ")");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to init DB", e);
        }
    }

    public static Connection get() throws SQLException {
        return DriverManager.getConnection(URL);
    }
}
