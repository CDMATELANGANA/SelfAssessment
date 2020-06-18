package com.mars.cdma.gov.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import java.sql.Connection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.ResultSet;
/* Servlet implementation class Taxservlet
 */
@WebServlet("/Taxservlet")
public class Taxservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Taxservlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
				res.setContentType("text/html");  
				System.out.println("welcome");
				String restype=req.getParameter("res");
				int zid=Integer.parseInt(req.getParameter("zone"));
				int clsid=Integer.parseInt(req.getParameter("bcls"));
				int useid=Integer.parseInt(req.getParameter("busage"));
			
				int octy=Integer.parseInt(req.getParameter("octy"));
		
				
				int l=Integer.parseInt(req.getParameter("len"));
				int w=Integer.parseInt(req.getParameter("wid"));
                int buildage =	Integer.parseInt(req.getParameter("bage"));			
                HttpSession s=req.getSession(true);
			    
			   
				
				PrintWriter out=res.getWriter();
				Connection con=null;
				PreparedStatement ps=null,ps2=null,ps3=null,ps4=null,ps5=null,ps6=null,ps7=null;
				ResultSet rs=null,rs2=null,rs3=null,rs4=null,rs5=null,rs6=null,rs7=null;
				String sql,sqll,sql2,sql3,sql4,sql5,sql6,zonename=null,clsname=null,usename=null,resname=null;
				int ur = 0;
				int depr=0;
				int tr=0;
				try
				{
					DBconnection dbcon=new DBconnection();
					//Class.forName("com.mysql.jdbc.Driver");
					//con=DriverManager.getConnection("jdbc:mysql://localhost:3306/sysibm", "root","root");
				con=dbcon.getDBConnection();
				
				
				sql="select * from pt_ur_mstr_tbl where I_ZONEOBJID=? and  I_CLSCODE=? and I_BLDGUSECODE=?";
				sqll="select * from pt_deprate_mstr_tbl where ? between I_FROMBLDGAGE and I_TOBLDGAGE and I_OCPNTYPECODE=?";
				sql2="select * from pt_taxrate_mstr_tbl a,pt_bldguse_mstr_tbl b where a.VC_TAXRATETYPE=? and a.VC_TAXRATETYPE=b.I_RESTYPE";
				sql3="select * from ct_zone_mstr_tbl where I_ZONEOBJID=? ";
				sql4="select * from pt_bldgcls_mstr_tbl where I_CLSCODE=?";
				sql5="select * from pt_bldguse_mstr_tbl where I_BLDGUSECODE=?";
			//sql6 ="select * from pt_taxrate_mstr_tbl where I_TAXRATECODE=?";
				
				
				
				ps4=(PreparedStatement) con.prepareStatement(sql3);
				ps4.setInt(1, zid);
				rs4=(ResultSet) ps4.executeQuery();
				
				ps5=(PreparedStatement) con.prepareStatement(sql4);
				ps5.setInt(1,clsid );
				rs5=(ResultSet) ps5.executeQuery();
				
				
				ps6=(PreparedStatement) con.prepareStatement(sql5);
				ps6.setInt(1,useid );
				rs6=(ResultSet) ps6.executeQuery();
				
				
			
				ps3=(PreparedStatement) con.prepareStatement(sql2);
				ps3.setString(1,restype);
				rs3=(ResultSet) ps3.executeQuery();
				
				
				ps2=(PreparedStatement) con.prepareStatement(sqll);
				int bage=Integer.parseInt(req.getParameter("bage"));
				ps2.setInt(1, bage);
				ps2.setInt(2, octy);
				rs2=(ResultSet) ps2.executeQuery();
				
				
				System.out.println("qsl "+sql);
				ps=(PreparedStatement) con.prepareStatement(sql);
				ps.setInt(1,zid);
				ps.setInt(2,clsid);
				ps.setInt(3,useid);
				rs=(ResultSet) ps.executeQuery();
				
				
				//out.print(""+rs.getString(5));
				while(rs4.next())
				{
					zonename=rs4.getString(4);
				}
				while(rs5.next())
				{
					clsname=rs5.getString(2);
				}
				while(rs6.next())
				{
					usename=rs6.getString(2);
				}
				
				while(rs.next())
				{
				 ur=rs.getInt(5);
				}
				while(rs2.next())
				{
					depr=rs2.getInt(3);
				}
				while(rs3.next())
				{
					tr=rs3.getInt(3);
					
				}
				System.out.println(""+depr);
				System.out.println(""+ur);
				System.out.println(""+tr);
				
				
/****** Tax Logic**********/
/*****************/
				
				float pe=0;
				float pl=(float) 0.08;
				float plp=(float) 0.02;
				float pua=1;
				
	
				
				float pla=(l*w);
				float mrv=(ur*pla);
				float bv=(mrv*2/3);
				float sv=(mrv*1/3);
				float garv=(bv*12);
				float dep=((garv*depr)/100);
				float narv=(sv*12)+(garv-dep);
				float tnarv=narv;
			 
				float pt=((narv*tr)/100);
				float et=narv*pe;
				float lc=(pt+et)*pl;
				float tpt=(pt+et+lc);
				res.setContentType("text/html");
				
				
				
				System.out.print("Total property tax: "+tpt);
				
				s.setAttribute("bage1", buildage);
				s.setAttribute("len",l);
			    s.setAttribute("wid",w);
			    s.setAttribute("zname", zonename);
			    s.setAttribute("cname", clsname);
			    s.setAttribute("uname", usename);
			    s.setAttribute("rname", restype);
			    s.setAttribute("nrv", tnarv);
			    s.setAttribute("ptp", tpt);
			    
			    
			    
				//res.sendRedirect("/jsp/display.jsp");
				              


				}
				catch(Exception e)
				{
					System.out.println(e);
			}
					RequestDispatcher rd=req.getRequestDispatcher("/jsp/display.jsp");
						rd.forward(req, res);
	}
	}
				