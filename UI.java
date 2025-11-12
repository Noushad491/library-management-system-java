package com.example.library;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class UI {

    public static void createAndShow() {
        JFrame f = new JFrame("Library Management");
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        f.setSize(700, 500);
        f.setLocationRelativeTo(null);

        JTextField title = new JTextField();
        JTextField author = new JTextField();
        JButton add = new JButton("Add Book");

        String[] cols = {"ID", "Title", "Author", "Issued"};
        DefaultTableModel model = new DefaultTableModel(cols, 0) {
            @Override public boolean isCellEditable(int r, int c) { return false; }
        };
        JTable table = new JTable(model);
        JScrollPane scroll = new JScrollPane(table);

        JButton refresh = new JButton("Refresh");
        JButton toggle = new JButton("Issue/Return");
        JButton del = new JButton("Delete");

        JPanel top = new JPanel(new GridLayout(2,3,8,8));
        top.add(new JLabel("Title"));
        top.add(new JLabel("Author"));
        top.add(new JLabel(""));
        top.add(title);
        top.add(author);
        top.add(add);

        JPanel actions = new JPanel();
        actions.add(refresh);
        actions.add(toggle);
        actions.add(del);

        f.setLayout(new BorderLayout(10,10));
        f.add(top, BorderLayout.NORTH);
        f.add(scroll, BorderLayout.CENTER);
        f.add(actions, BorderLayout.SOUTH);

        add.addActionListener(e -> {
            String t = title.getText().trim();
            String a = author.getText().trim();
            if (t.isEmpty() || a.isEmpty()) {
                JOptionPane.showMessageDialog(f, "Enter title and author");
                return;
            }
            BookDAO.add(t, a);
            title.setText("");
            author.setText("");
            JOptionPane.showMessageDialog(f, "Book added!");
            load(model);
        });

        refresh.addActionListener(e -> load(model));
        toggle.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row < 0) { JOptionPane.showMessageDialog(f, "Select a row"); return; }
            int id = (int) model.getValueAt(row, 0);
            BookDAO.toggleIssued(id);
            load(model);
        });
        del.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row < 0) { JOptionPane.showMessageDialog(f, "Select a row"); return; }
            int id = (int) model.getValueAt(row, 0);
            BookDAO.delete(id);
            load(model);
        });

        load(model);
        f.setVisible(true);
    }

    private static void load(DefaultTableModel model) {
        model.setRowCount(0);
        List<Book> books = BookDAO.all();
        for (Book b : books) {
            model.addRow(new Object[]{b.id, b.title, b.author, b.issued ? "Issued" : "Available"});
        }
    }
}
