package Create_Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class DynamicApplication {
	private static final String driver = "com.mysql.cj.jdbc.Driver";
	private static final String username = "root";
	private static final String password = "root";
	private static Connection conn;
	private static PreparedStatement pmst;
	public static void main(String[] args) {
		int choice;
		do {
			Scanner src = new Scanner(System.in);
			System.out.println("Choose your choice: ");
			DisplayMenu();
			choice = Integer.parseInt(src.next());
			switch (choice) {
			case 1:
				CreateDatabase();
				break;
			case 2:
				DropDatabase();
				break;
			case 3:
				InsertData();
				break;
			case 4:
				DeleteData();
				break;
			case 5:
				UpdateData();
				break;
			case 6:
				GetById();
				break;
			case 7:
				GetAll();
				break;
			case 8:
				Login();
				break;
			case 9: 
				Exit();
				break;
			default:
				System.out.println("Invalid Option");
				break;
			}
		} while (choice>0);
	}

	private static void Exit() {
		System.out.println("You are exited!");
		System.exit(0);	
	}

	private static void DisplayMenu() {
		System.out.println("\t1.create database");
		System.out.println("\t2.drop database");
		System.out.println("\t3.data insertion");
		System.out.println("\t4.delete by id");
		System.out.println("\t5.update data");
		System.out.println("\t6.get by id");
		System.out.println("\t7.getall");
		System.out.println("\t8.login");
		System.out.println("\t9.exit");
		
	}

	private static void CreateDatabase() {
		try {
			Scanner src = new Scanner(System.in);
			Class.forName(driver);
			String url = "jdbc:mysql://localhost:3306/";
			conn = DriverManager.getConnection(url, username,password);
			System.out.println("Enter database: ");
			String sql = "create database "+src.nextLine();
			pmst = conn.prepareStatement(sql);
			int i = pmst.executeUpdate();
			if (i > 0) {
				System.out.println("Database Created");
			}
			else {
				System.out.println("Database not Created");
			}
			conn.close();
			pmst.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void DropDatabase() {
		try {
			Scanner src = new Scanner(System.in);
			Class.forName(driver);
			String url = "jdbc:mysql://localhost:3306/";
			conn = DriverManager.getConnection(url,username,password);
			System.out.println("Enter database name: ");
			String sql="Drop Database "+src.nextLine();
			pmst = conn.prepareStatement(sql);
			int i = pmst.executeUpdate();
			if(i == 0 ) {
				System.out.println("Database dropped!");
			}else {
				System.out.println("Database not dropped");
			}
			conn.close();
			pmst.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	private static void InsertData() {
		try {
			Scanner src = new Scanner(System.in);
			System.out.println("Enter database name: ");
			String url = "jdbc:mysql://localhost:3306/"+src.next();
			Class.forName(driver);
			conn = DriverManager.getConnection(url,username,password);
			System.out.println("Enter table name: ");
			String sql = "insert into "+src.next()+ "(order_id,order_name,order_pincode,order_address) values(?,?,?,?)";
			pmst = conn.prepareStatement(sql);
			System.out.println("Enter order id");
			pmst.setLong(1, src.nextLong());
			System.out.println("Enter order name");
			pmst.setString(2, src.next());
			System.out.println("Enter order pincode");
			pmst.setInt(3, src.nextInt());
			System.out.println("Enter order address");
			pmst.setString(4, src.next());
			int i = pmst.executeUpdate();
			if(i>0) {
				System.out.println("Data Inserted");
			}
			else {
				System.out.println("Data not inserted");
			}
			pmst.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void DeleteData() {
		try {
			Scanner src = new Scanner(System.in);
			System.out.println("Enter database name: ");
			String url = "jdbc:mysql://localhost:3306/"+src.next();
			Class.forName(driver);
			conn = DriverManager.getConnection(url,username,password);
			System.out.println("Enter table name: ");
			String sql = "delete from "+src.next()+" where order_id=?";
			pmst = conn.prepareStatement(sql);
			System.out.println("Enter order id");
			pmst.setLong(1, src.nextLong());
			int i = pmst.executeUpdate();
			if(i>0) {
				System.out.println("Data Deleted");
			}
			else {
				System.out.println("Data not Deleted");
			}
			pmst.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	private static void UpdateData() {
		try {
			Scanner src = new Scanner(System.in);
			Class.forName(driver);
			System.out.println("Enter database name: ");
			String url = "jdbc:mysql://localhost:3306/"+src.next();
			conn = DriverManager.getConnection(url,username,password);
			System.out.println("Enter table name: ");
			String sql = "update "+src.next()+" set order_name=?,order_pincode=?,order_address=? where order_id=?";
			pmst = conn.prepareStatement(sql);
			System.out.println("Enter order name");
			pmst.setString(1, src.next());
			System.out.println("Enter order pincode");
			pmst.setInt(2, src.nextInt());
			System.out.println("Enter order address");
			pmst.setString(3, src.next());
			System.out.println("Enter order id");
			pmst.setLong(4, src.nextLong());
			int i = pmst.executeUpdate();
			if(i>0) {
				System.out.println("Data Updated");
			}
			else {
				System.out.println("Data not Updated");
			}
			pmst.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	private static void GetById() {
		try {
		Scanner src = new Scanner(System.in);
		Class.forName(driver);
		System.out.println("Enter database name: ");
		String url = "jdbc:mysql://localhost:3306/"+src.next();
		conn = DriverManager.getConnection(url,username,password);
		System.out.println("Enter table name: ");
		String sql = "select * from "+src.next()+" where order_id=?";
		pmst = conn.prepareStatement(sql);
		System.out.println("Enter order id");
		pmst.setLong(1, src.nextLong());
		ResultSet  rs = pmst.executeQuery();
		while(rs.next()) {
			System.out.println("Order_id :"+rs.getLong("order_id"));
			System.out.println("Order_name :"+rs.getString("order_name"));
			System.out.println("Order_pincode :"+rs.getInt("order_pincode"));
			System.out.println("Order_address :"+rs.getString("order_address"));
		}
		pmst.close();
		conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	private static void GetAll() {
		try {
			Scanner src = new Scanner(System.in);
			Class.forName(driver);
			System.out.println("Enter database name: ");
			String url = "jdbc:mysql://localhost:3306/"+src.next();
			conn = DriverManager.getConnection(url,username,password);
			System.out.println("Enter table name: ");
			String sql = "select * from "+ src.next();
			pmst = conn.prepareStatement(sql);
			ResultSet  rs = pmst.executeQuery();
			while(rs.next()) {
				System.out.println("Order_id :"+rs.getLong("order_id"));
				System.out.println("Order_name :"+rs.getString("order_name"));
				System.out.println("Order_pincode :"+rs.getInt("order_pincode"));
				System.out.println("Order_address :"+rs.getString("order_address"));
			}
			pmst.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	private static void Login() {
		try {
			Scanner src = new Scanner(System.in);
			Class.forName(driver);
			System.out.println("Enter database name: ");
			String url = "jdbc:mysql://localhost:3306/"+src.nextLine();
			conn = DriverManager.getConnection(url,username,password);
			System.out.println("Enter table name: ");
			String sql = "select * from "+src.nextLine()+" where user_id=? and user_password=? ";
			pmst = conn.prepareStatement(sql);
			System.out.println("Enter user id");
			pmst.setInt(1, src.nextInt());
			System.out.println("Enter user password");
			pmst.setString(2, src.next());
			ResultSet  rs = pmst.executeQuery();
			if(rs.next()) {
				System.out.println("Login Successful");
			}else {
				System.out.println("Login Failed!");
			}
			conn.close();
			pmst.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
