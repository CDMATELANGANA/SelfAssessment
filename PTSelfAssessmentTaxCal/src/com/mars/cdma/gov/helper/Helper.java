package com.mars.cdma.gov.helper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;



public class Helper {
	public Connection con=null;
	public PreparedStatement ps=null;
	public ResultSet rs=null;
	public ResultSetMetaData rsmd=null;
	public  SessionFactory sessionFactory;
	public Session session;
	public Transaction transaction;
	
	public int insert(Object[]params,String sql) {
		int j=0;
			con=null;		
		try
		{
			System.out.println("insert helper");
			 session=sessionFactory.getCurrentSession();
			 transaction=session.beginTransaction();
	ps=con.prepareStatement(sql);
	System.out.println("ps is "+ps );
		{
	for (int i = 1; i<= params.length; i++) 
	{

		ps.setObject(i, params[i-1]);
		System.out.println("ps is"+ps);
	}
		}
		j=ps.executeUpdate();
		  
		sessionFactory.close();
		System.out.println("j sis"+j);
		} 
		catch (SQLException e) {
			System.out.println("error in insert helper");
		}
		
	return j;
		
	}

	 public Map viewtable(String sql) throws SQLException
	{
		System.out.println("dis is view helper.");
		
			con=Dbcon.getmysqldb(); 
			Map map=new HashMap();
try {
	
		ps=con.prepareStatement(sql);
		rs=ps.executeQuery();
		 rsmd=rs.getMetaData();
		Set h=new LinkedHashSet();
		List d=new ArrayList();

		while (rs.next())
	
			{
				List d1=new ArrayList();
		for (int i = 1; i<=rsmd.getColumnCount(); i++) 
				{
					h.add(rsmd.getColumnLabel(i));
					
					d1.add(rs.getString(i)==null?"":rs.getString(i));
					
				}
							d.add(d1);
			}
			map.put(h, d);
			System.out.println("h return......"+map);
	} catch (Exception e) {
		
	}
	
		return map;			
}
public Map  editdata(Object[]params, String sql)
{
	Map m=new HashMap();
	try {
		
		con=Dbcon.getmysqldb();
		ps=con.prepareStatement(sql);
		ps.setObject(1, params[0]);
		System.out.println("ps is   "+ps);
		rs=ps.executeQuery();
		 rsmd=rs.getMetaData();
	while(rs.next())
	{
		for (int i = 1; i <=rsmd.getColumnCount(); i++) 
		{
			m.put(rsmd.getColumnLabel(i), rs.getString(i));			}
	}
	System.out.println("map in helper...."+m);
	} catch (Exception e) {
		// TODO: handle exception
	}
	return m;
}

/*public List dropdown(String sql) throws Exception {
	con=Dbcon.getmysqldb();
	List list=new ArrayList();
	ps=con.prepareStatement(sql);
	rs=ps.executeQuery();
	while(rs.next())
	{
		Dropdown dd=new Dropdown();
		dd.setDropdownkey(rs.getString(1));
		dd.setDropdownvalues(rs.getString(2));
		list.add(dd);
	}
	return list;
}*/
	 
}