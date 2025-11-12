package com.example.library;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDAO {

    public static void add(String title, String author) {
        String sql = "INSERT INTO books(title, author, issued) VALUES(?,?,0)";
        try (Connection c = DB.get();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, title);
            ps.setString(2, author);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<Book> all() {
        List<Book> res = new ArrayList<Book>();
        String sql = "SELECT id, title, author, issued FROM books ORDER BY id DESC";
        try (Connection c = DB.get();
             Statement st = c.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                res.add(new Book(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getInt("issued") == 1
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return res;
    }

    public static void delete(int id) {
        try (Connection c = DB.get();
             PreparedStatement ps = c.prepareStatement("DELETE FROM books WHERE id=?")) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void toggleIssued(int id) {
        String sql = "UPDATE books SET issued = CASE issued WHEN 1 THEN 0 ELSE 1 END WHERE id=?";
        try (Connection c = DB.get();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
