package fljd;

import java.sql.Connection;
import java.sql.DriverManager;
//import java.sql.PreparedStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class mysql {
public void server(double P,double R,int N,int M,double F) throws ClassNotFoundException, SQLException {
		
		String name = "root";
		String pwd = "123456";
		String url = "jdbc:mysql://localhost_3306/fu";
		
		Class.forName("com.mysql.jdbc.Driver");
	    //Class.forName("org.gjt.mm.mysql.Driver");
		
		Connection conn = DriverManager.getConnection(url,name,pwd);
		Statement stmt = conn.createStatement();
		String sql = "select * from calculation";
		
		ResultSet rs = stmt.executeQuery(sql);
		while(rs.next())
		{
			//P=rs.getString(P);
		}
		
		
		String sql1 = "insert into fuli(P,N,R,M,F) values (?,?,?,?,?)";
		PreparedStatement pstmt = conn.prepareStatement(sql1);
		//P,i,N,m,F
		pstmt.setDouble(1,P);
		pstmt.setDouble(2,R);
		pstmt.setInt(3,N);
		pstmt.setInt(4,M);
		pstmt.setDouble(5,F);
	
		///pstmt.executeUpdate(); 
		pstmt.executeUpdate(sql1);
		
//		try 
//		{
//			
//		} catch (Exception e) 
//		{
//			// TODO: handle exception
//		}finally
//		{
//			if(rs.next())
//			{
//				
//			}
//			else
//			{
//				
//				
//			}
//		}
//		
		
//		rs.close();
		stmt.close();
		conn.close();
	}


}
