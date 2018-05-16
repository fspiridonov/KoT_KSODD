package ru.ksodd.Helpers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class dbConnection {
        /**
         * @param sql
         * @throws ClassNotFoundException
         * @throws SQLException
         */
        public static void connectToDB(String sql) throws ClassNotFoundException, SQLException {
            Class.forName("org.postgresql.Driver");
            Connection connection = null;
            PreparedStatement preparedStatement = null;
            try {
                connection = DriverManager.getConnection(Configuration.BD.db_url, Configuration.BD.db_login, Configuration.BD.db_password);
                System.out.println("Connecting to database..." + sql);

                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.execute();
                System.out.println("EXECUTED...");
            } catch (SQLException ex) {
                System.out.println("ERROR================================================================" + sql);
            } finally {
                if (connection != null) {
                    connection.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            }
        }
    }
