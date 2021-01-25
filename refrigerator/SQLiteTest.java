package refrigerator;

import java.sql.*;

public class SQLiteTest {
	private static Connection con;
	private static boolean hasData = false;

	public ResultSet displayUsers() throws ClassNotFoundException, SQLException {
		if (con == null) {
			getConnection();
		}
		
		Statement state = con.createStatement();
		ResultSet res = state.executeQuery("SELECT fItemName, fExpiryDate FROM user");
		return res;
	}

	private static void getConnection() throws ClassNotFoundException, SQLException {
		con = DriverManager.getConnection("jdbc:sqlite:SQLiteTest1.db");
		initialize();
	}
	
	private static void initialize() throws SQLException {
		if (!hasData) {
			hasData = true;
			Statement state = con.createStatement();
			ResultSet res = state.executeQuery("SELECT name FROM sqlite_master WHERE type='table' AND name='user'");
			if (!res.next()) {
				System.out.println("Building the User table with prepopulated values.");
				// need to build the table
				Statement state2 = con.createStatement();
				state2.execute("CREATE TABLE user(id integer," 
						+ "fItemName varchar(60)," + "fExpiryDate date,"
						+ "primary key(id));");
			}
		}
	}
	
	public static void addUser(String foodItemName, Date foodExpiryDate) throws ClassNotFoundException, SQLException {
		if (con == null) {
			getConnection();
		}
		
		PreparedStatement prep = con.prepareStatement("INSERT INTO user values(?,?,?);");
		prep.setString(2,  foodItemName);
		prep.setDate(3, foodExpiryDate);
		prep.execute();
	}

}