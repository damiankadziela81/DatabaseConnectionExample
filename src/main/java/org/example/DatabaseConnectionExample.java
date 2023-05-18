package org.example;

import java.sql.*;

public class DatabaseConnectionExample {
    // JDBC driver name and database URL
    //static final String JDBC_DRIVER = "com.mysql.jdbc.Driver"; //<- this is no longer needed
    static final String DB_URL = "jdbc:mysql://localhost:3306/mygameslibrary?useSSL=false";

    // Database credentials
    static final String USER = "root";
    static final String PASS = "pass";

    public static void read() {
        Connection conn = null;
        Statement stmt = null;

        try {
            // Register JDBC driver
            //Class.forName(JDBC_DRIVER); //<- this is no longer needed

            // Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            // Execute a query
            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            String sql = "SELECT id, title, rating FROM game";
            ResultSet rs = stmt.executeQuery(sql);

            // Process the result set
            while (rs.next()) {
                // Retrieve by column name
                int id = rs.getInt("id");
                String title = rs.getString("title");
                int rating = rs.getInt("rating");

                // Display values
                System.out.print("ID: " + id);
                System.out.print(", Title: " + title);
                System.out.println(", Rating: " + rating);
            }

            // Clean-up environment
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            // Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            // Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            // Close resources
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException se2) {
                se2.printStackTrace();
            }
            try {
                if (conn != null) conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        System.out.println("Goodbye!");
    }

    public static void write() {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            // Register JDBC driver
            //Class.forName(JDBC_DRIVER); //<- this is no longer needed

            // Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            // Prepare the SQL statement
            String sql = "INSERT INTO game (title, genre, release_year, developer, publisher, rating) " +
                    "VALUES (?, ?, ?, ?, ?, ?)";
            stmt = conn.prepareStatement(sql);

            // Set the parameter values
            stmt.setString(1, "USNF");
            stmt.setString(2, "flight sim");
            stmt.setInt(3, 1994);
            stmt.setString(4, "EA");
            stmt.setString(5, "EA");
            stmt.setInt(6, 9);

            // Execute the statement
            int rowsAffected = stmt.executeUpdate();

            // Check the number of rows affected
            if (rowsAffected > 0) {
                System.out.println("Data inserted successfully.");
            } else {
                System.out.println("No rows affected.");
            }

            // Close the statement and connection
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            // Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            // Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            // Close resources
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException se2) {
                se2.printStackTrace();
            }
            try {
                if (conn != null) conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        System.out.println("Goodbye!");
    }
}
