package Create_Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class Insertion {
	private static final String driver = "com.mysql.cj.jdbc.Driver"; 
	private static final String username = "root";
	private static final String password = "root";
	private static Connection conn;
	private static PreparedStatement pmst;
	public static void main(String[] args) {
		try {
			Scanner src = new Scanner(System.in);
			System.out.println("Enter database name: ");
			String url = "jdbc:mysql://localhost:3306/"+src.nextLine();
			Class.forName(driver);
			conn = DriverManager.getConnection(url,username,password);
			String sql = "insert into data(id,name,email) values(?,?,?)";
			pmst = conn.prepareStatement(sql);
			System.out.println("Enter faculty id");
			pmst.setString(1, src.nextLine());
			System.out.println("Enter faculty name");
			pmst.setString(2, src.nextLine());
			System.out.println("Enter faculty email");
			pmst.setString(3, src.nextLine());
			int i = pmst.executeUpdate();
			if(i>0) {
				System.out.println("Data Inserted");
			}
			else {
				System.out.println("Data not inserted");
			}
			pmst.close();
			conn.close();
			src.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
