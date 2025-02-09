package pozdravlator;
import java.sql.*;

public class H2DataBase {

	private static String url = "jdbc:h2:./data/mydb";
	private static String users = "sa";
	private static String password = "";
	
	public void createTableDB() {
        
		String SQLTable = "CREATE TABLE IF NOT EXISTS BirthdaysInfo ("
		        + "id BIGINT AUTO_INCREMENT PRIMARY KEY, " 
		        + "fullName VARCHAR(100) NOT NULL, "
		        + "dayBirthday INT NOT NULL, "
		        + "monthBirthday INT NOT NULL, "
		        + "yearBirthday INT NOT NULL"
		        + ")";
	


        try (Connection connection = DriverManager.getConnection(url, users, password);
            
        	Statement stmt = connection.createStatement()) {
            stmt.execute(SQLTable);
            
        } catch (SQLException e) {
        	
            e.printStackTrace();
            
        }
        
    }
	
	public static Connection connect() {  
        
        try {

            return DriverManager.getConnection(url, users, password);
            
        } catch (SQLException e) {
        	
            e.printStackTrace();
            return null;
            
        }
    }
	
}
