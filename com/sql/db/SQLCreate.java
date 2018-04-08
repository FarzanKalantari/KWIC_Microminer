/**
 * 
 */
/**
 * @author ac3_o
 *
 */
package com.sql.db;

import java.sql.*;

public class SQLCreate {

        //create database name KWIC
        public static void createNewDatabase() {
                final String DB_URL = "jdbc:sqlite:./" +"KWIC";

                try {
                        //driver
                        Class.forName("org.sqlite.JDBC");
                } catch (ClassNotFoundException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                }

                try (Connection conn = DriverManager.getConnection(DB_URL)) {
                        if (conn != null) {
                                DatabaseMetaData meta = conn.getMetaData();
                                System.out.println("The driver name is " + meta.getDriverName());
                                System.out.println("A new database has been created.");
                        }

                } catch (SQLException e) {
                        System.out.println(e.getMessage());
                }
        }

        public synchronized static Connection connect() {
                //connects to database this location
                String DB_URL = "jdbc:sqlite:C:\\Users\\Zach\\Documents\\NetBeansProjects\\KWIC_Microminer\\KWIC.db";
                Connection c = null;

                try {
                        //driver
                        Class.forName("org.sqlite.JDBC");
                } catch (ClassNotFoundException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                }

                try {
                        if (c == null) {
                                c = DriverManager.getConnection(DB_URL);
                        }
                } catch (SQLException e) {
                        e.printStackTrace();
                }
                return c;
        }

        public synchronized static void createTable() {
                Connection con = connect();

                Statement stmt = null;
                try {
                        String sql;
                        stmt = con.createStatement();
                        sql = "CREATE TABLE IF NOT EXISTS KWICdata (\n"
                                        + " LineNum, \n"
                                        + " SortedLine text, \n"
                                        + " InputLine text" +");";

                        stmt.execute(sql);
                        System.out.println("Table created.");

                } catch (SQLException e) {
                        e.printStackTrace();
                }
        }

        public synchronized static void removeAllRecords() {
                Connection con = connect();

                try {
                        String sql;
                        Statement stmt = con.createStatement();
                        sql = "DELETE FROM KWIC";
                        stmt.execute(sql);
                        con.close();

                } catch (SQLException e) {
                        e.printStackTrace();
                }
        }

        public synchronized static void insertRecords(int lineNum, String SortedLine, String ShiftedLine, String InputLine) {

                Connection con = connect();
                String sql = "INSERT INTO KWICdata(LineNum, SortedLine, InputLine) VALUES(?,?, ?)";

                try {
                        PreparedStatement pstmt = con.prepareStatement(sql);
                        pstmt.setInt(1, lineNum);
                        pstmt.setString(2, SortedLine);
                        pstmt.setString(3, InputLine);

                        pstmt.executeUpdate();
                        con.close();
                }
                catch (SQLException e) {
                        e.printStackTrace();
                }

        }

        /**
         * @param args the command line arguments
         */
        public static void main(String[] args) {
                createNewDatabase();
                createTable();

        }


}