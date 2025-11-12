package com.example.library;

public class App {
    public static void main(String[] args) {
        DB.init();
        javax.swing.SwingUtilities.invokeLater(UI::createAndShow);
    }
}
