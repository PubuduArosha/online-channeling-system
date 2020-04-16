package DBrepository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginRepository {
	
	Connection con=null;
	public LoginRepository()
	{
		String url="jdbc:mysql://localhost:3306/pafdb?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
		String username="root";
		String password="";
		
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection(url,username,password);
			System.out.println("successfully connected to DB");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("SQL error in patient");
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("Unable to make connection");
			e.printStackTrace();
		}
		
	}
	
	public String login(String email,String password)
	{
		String sql=new String("SELECT * FROM patient WHERE email=? And password=?");
		String sql2=new String("SELECT * FROM doctor WHERE email=? And password=?");
		String sql3=new String("SELECT * FROM admin WHERE username=? And password=?");
		
		try {
			PreparedStatement stm = con.prepareStatement(sql3);
			stm.setString(1, email);
			stm.setString(2, password);
			ResultSet rs=stm.executeQuery();
		
			if(rs.next())
			{
				return "Admin Logged in";
				
			}else if(!rs.next())
			{
				PreparedStatement stm1 = con.prepareStatement(sql2);
				stm1.setString(1, email);
				stm1.setString(2, password);
				ResultSet rs1=stm1.executeQuery();
				
				if(rs1.next())
				{
					return "Doctor logged";
					
				}else if(!rs1.next())
				{
					PreparedStatement stm2 = con.prepareStatement(sql);
					stm2.setString(1, email);
					stm2.setString(2, password);
					ResultSet rs2=stm2.executeQuery();
					
					if(rs2.next())
					{
						return "patient logged in";
					}else {
						
						return "Invalid logins";
					}
					
				}
				
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
