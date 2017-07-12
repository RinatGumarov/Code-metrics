package ru.innopolis.rinatgumarov.code_metrics.db;

import org.apache.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

/**
 * Created by Rinat on 11.06.17.
 */
public class Database {
    private static Database INSTANCE;

    static Logger logger = Logger.getLogger(Database.class.getName());

    private static Statement stmt;



    public static Database getINSTANCE(){
        if (INSTANCE == null)
            INSTANCE = new Database();
        return INSTANCE;
    }

    private Database() {
        try {
            Properties props = new Properties();
            FileInputStream in = new FileInputStream("src/main/resources/db.properties");
            props.load(in);
            in.close();

            String driver = props.getProperty("jdbc.driver");
            if (driver != null) {
                Class.forName(driver) ;
            }

            String url = props.getProperty("jdbc.url");
            String username = props.getProperty("jdbc.username");
            String password = props.getProperty("jdbc.password");

            Connection con = DriverManager.getConnection(url, username, password);
            stmt = con.createStatement();
            logger.info("Connection to database successful");
        } catch (SQLException | IOException | ClassNotFoundException e) {
            logger.info("Connection to database failed with message: " + e.getMessage());
        } catch (NullPointerException e){
            logger.info("Wrong properties");
        }
    }

    public void write(String query){
        try {
            stmt.execute(query);
        } catch (SQLException e) {
            logger.info("Writing to database failed: " + e.getMessage());
        }
    }
}
