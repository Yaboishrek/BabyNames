import java.sql.*;

public class BabyNamesDatabase {
    
    private Connection connection;
    private Statement statement;
    
    public BabyNamesDatabase() {
        try {
            // Load the JDBC driver
            Class.forName("com.mysql.jdbc.Driver");
            
            // Connect to the database
            String url = "jdbc:mysql://localhost:3306/baby_names";
            String username = "root";
            String password = "password";
            connection = DriverManager.getConnection(url, username, password);
            
            // Create a statement object
            statement = connection.createStatement();
            
        } catch (ClassNotFoundException e) {
            System.out.println("Error: Unable to load JDBC driver.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Error: Unable to connect to the database.");
            e.printStackTrace();
        }
    }
    
    public void deleteBabyName(int name_id) {
        try {
            // Execute the SQL statement to delete the record
            String sql = "DELETE FROM `Baby Names` WHERE `name_id` = " + name_id;
            statement.executeUpdate(sql);
            System.out.println("Record deleted successfully.");
            
        } catch (SQLException e) {
            System.out.println("Error: Unable to delete record.");
            e.printStackTrace();
        }
    }
    
    public void insertBabyName(int name_id, String name, String gender, String origin, String meaning, int votes) {
        try {
            // Execute the SQL statement to insert the new record
            String sql = "INSERT INTO `Baby Names` (`name_id`, `name`, `gender`, `origin`, `meaning`, `votes`) "
                    + "VALUES (" + name_id + ", '" + name + "', '" + gender + "', '" + origin + "', '" + meaning + "', " + votes + ")";
            statement.executeUpdate(sql);
            System.out.println("Record inserted successfully.");
            
        } catch (SQLException e) {
            System.out.println("Error: Unable to insert record.");
            e.printStackTrace();
        }
    }
    
    public void uploadPopularityRankings(String filename) {
        try {
            // Execute the SQL statement to upload the data from the CSV file
            String sql = "LOAD DATA INFILE '" + filename + "' INTO TABLE `Popularity Rankings` "
                    + "FIELDS TERMINATED BY ',' ENCLOSED BY '\"' LINES TERMINATED BY '\\n' IGNORE 1 ROWS";
            statement.executeUpdate(sql);
            System.out.println("Data uploaded successfully.");
            
        } catch (SQLException e) {
            System.out.println("Error: Unable to upload data.");
            e.printStackTrace();
        }
    }
    
    public void closeConnection() {
        try {
            // Close the statement and connection
            statement.close();
            connection.close();
            
        } catch (SQLException e) {
            System.out.println("Error: Unable to close connection.");
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
        BabyNamesDatabase db = new BabyNamesDatabase();
        
        // Delete a record
        db.deleteBabyName(123);
        
        // Insert a new record
        db.insertBabyName(123, "Oliver", "Male", "English", "Olive tree", 100);
        
        // Upload data from a CSV file
        db.uploadPopularityRankings("/path/to/csv/file");
        
        db.closeConnection();
    }
}
