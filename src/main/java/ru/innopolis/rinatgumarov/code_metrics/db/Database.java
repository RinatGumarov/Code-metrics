package ru.innopolis.rinatgumarov.code_metrics.db;

import java.sql.*;

/**
 * Created by Rinat on 11.06.17.
 */
public class Database {
    private static Database INSTANCE;
    private static final String url = "jdbc:mysql://localhost:3306/code_metrics?useSSL=false";
    private static final String user = "root";
    private static final String password = "NSholy24";
    private static Statement stmt;

    public static Database getINSTANCE(){
        if (INSTANCE == null)
            INSTANCE = new Database();
        return INSTANCE;
    }

    private Database() {
        try {
            Connection con = DriverManager.getConnection(url, user, password);
            stmt = con.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void write(String query){
        try {
            stmt.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
