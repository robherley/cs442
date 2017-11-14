package hw3;

import java.sql.*;

public class Assignment {
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306?&useSSL=false";

    static final String USER = "root";
    static final String PASS = "test1234";

    // Method to print a separator in output
    public static void sep(String text) {
        System.out.println("\u001B[32m===== " + text + " =====\u001B[0m");
    }

    // Helper method to execute & update an array of queries
    public static void executeQueries(Statement stmt, String[] queries) {
        try {
            for (String query : queries) {
                stmt.executeUpdate(query);
            }
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        }
    }

    // Helper method to execute a query and print the output of the query column
    public static void printQuery(Connection conn, String query, String col) {
        try {
            Statement s = conn.createStatement();
            ResultSet rs = s.executeQuery(query);
            while (rs.next()) {
                String val = rs.getString(col);
                System.out.println(val);
            }
            rs.close();
            s.close();
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        try {
            // Setup the DB
            sep("Setup");
            Class.forName("com.mysql.jdbc.Driver");

            // Open DB Connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Creating database...");
            stmt = conn.createStatement();

            // Delete old DB if it exists
            String sql = "DROP DATABASE IF EXISTS BoatRental";
            stmt.executeUpdate(sql);

            // Create DB
            sql = "CREATE DATABASE BoatRental";
            stmt.executeUpdate(sql);
            System.out.println("Database created successfully...");

            //Select the database;
            sql = "USE BoatRental";
            stmt.executeUpdate(sql);

            // Create Sailor Table
            String tables[] = {
                    "CREATE TABLE Sailor(sid INTEGER NOT NULL PRIMARY KEY, sname VARCHAR(20) NOT NULL, rating REAL, age INTEGER NOT NULL);",
                    "CREATE TABLE boats(bid INTEGER NOT NULL PRIMARY KEY, bdriver_name VARCHAR(20) NOT NULL, color VARCHAR(20));",
                    "CREATE TABLE reserves(sid integer not null, bid INTEGER NOT NULL, day DATE NOT NULL, FOREIGN KEY (sid) REFERENCES sailor(sid), FOREIGN KEY (bid) REFERENCES boats(bid), PRIMARY KEY (sid, bid, day));" };
            executeQueries(stmt, tables);

            // Populate Sailor Table
            String sailors[] = { "INSERT INTO Sailor VALUES(22, 'Dustin', 7, 45);",
                    "INSERT INTO Sailor VALUES(29, 'Brutus', 1, 33);",
                    "INSERT INTO Sailor VALUES(31, 'Lubber', 8, 55);", "INSERT INTO Sailor VALUES(32, 'Andy', 8, 26);",
                    "INSERT INTO Sailor VALUES(58, 'Rusty', 10, 35);",
                    "INSERT INTO Sailor VALUES(64, 'Horatio', 7, 35);",
                    "INSERT INTO Sailor VALUES(71, 'Zorba', 20, 18);",
                    "INSERT INTO Sailor VALUES(74, 'Horatio', 9, 35);" };
            executeQueries(stmt, sailors);

            // Populate Boats Table
            String boats[] = { "INSERT INTO BOATS VALUES(101, 'Interlake', 'Blue');",
                    "INSERT INTO BOATS VALUES(102, 'Interlake', 'Red');",
                    "INSERT INTO BOATS VALUES(103, 'Clipper', 'Green');",
                    "INSERT INTO BOATS VALUES(104, 'Marine', 'Red');" };
            executeQueries(stmt, boats);

            // Populate Reserves Table

            String reserves[] = { "INSERT INTO RESERVES VALUES(22, 101, '2008-10-10');",
                    "INSERT INTO RESERVES VALUES(22, 102, '2008-10-10');",
                    "INSERT INTO RESERVES VALUES(22, 103, '2009-10-08');",
                    "INSERT INTO RESERVES VALUES(22, 104, '2009-10-09');",
                    "INSERT INTO RESERVES VALUES(31, 102, '2008-11-10');",
                    "INSERT INTO RESERVES VALUES(31, 103, '2008-11-06');",
                    "INSERT INTO RESERVES VALUES(31, 104, '2008-11-12');",
                    "INSERT INTO RESERVES VALUES(64, 101, '2008-09-05');",
                    "INSERT INTO RESERVES VALUES(64, 102, '2008-09-08');",
                    "INSERT INTO RESERVES VALUES(74, 103, '2008-09-08');" };
            executeQueries(stmt, reserves);

            // Query 1
            sep("First Query");
            sql = "SELECT r.sid FROM boats b, reserves r WHERE r.bid = b.bid AND b.color = 'Red' AND r.sid NOT IN ( SELECT r.sid FROM boats b, reserves r WHERE r.bid = b.bid AND b.color = 'Green');";
            printQuery(conn, sql, "sid");

            // Query 2
            sep("Second Query");
            sql = "SELECT s.sname FROM sailor s WHERE s.sid NOT IN (SELECT r.sid FROM boats b, reserves r WHERE r.bid = b.bid AND b.color = 'Red');";
            printQuery(conn, sql, "sname");

            // Query 3
            sep("Third Query");
            sql = "SELECT s.sname, s.sid FROM sailor s WHERE s.rating > ALL(SELECT s.rating FROM sailor s WHERE s.sname = 'Horatio');";
            printQuery(conn, sql, "s.sid");

            // Query 4
            sep("Fourth Query");
            sql = "SELECT s.sname FROM reserves r, sailor s WHERE r.sid = s.sid GROUP BY s.sid HAVING COUNT(DISTINCT r.bid) = (SELECT COUNT(bid) FROM boats);";
            printQuery(conn, sql, "s.sname");

            // Query 5
            sep("Fifth Query");
            sql = "SELECT COUNT(*) as boat_count FROM boats b, reserves r WHERE r.bid = b.bid AND b.color = 'Red' GROUP BY b.bid";
            printQuery(conn, sql, "boat_count");

        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
            } // nothing we can do
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            } //end finally try
        } //end try
    }//end main
}//end JDBCExample
