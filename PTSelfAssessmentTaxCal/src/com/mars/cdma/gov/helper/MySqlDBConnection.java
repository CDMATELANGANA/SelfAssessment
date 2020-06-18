package com.mars.cdma.gov.helper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class MySqlDBConnection {

	public static Connection getEpayDB() {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");//10.166.7.188 localhost
			con = DriverManager.getConnection("jdbc:mysql://10.166.7.188:3306/ptassessmenttax",
					"root", "root");
			//System.out.println("con is" + con);

		} catch (Exception e) {
			System.out.println("error in db");
			e.printStackTrace();
			// TODO: handle exception
		}

		return con;
	}
	
	public static void main(String[] args) throws SQLException {
		Connection con=null;
			con=getEpayDB();
		while(true){
			System.out.println("in while");
			con.createStatement();
		}
	}

}
