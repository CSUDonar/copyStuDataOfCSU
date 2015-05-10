

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/***
 * 
 * @author Donar
 *
 */
public class dbUtils{
	public Connection conn = null;
	public Statement stmt = null;
	public ResultSet rs = null;
	 private static String propFileName = "prop.properties";	
	  private static Properties prop = new Properties();
	  private static String dbClassName ="com.mysql.jdbc.Driver";
	  private static String dbUrl =
	      "jdbc:mysql://127.0.0.1:3306/student?user=root&password=root&useUnicode=true";
	  public dbUtils(){
		  try {
			  InputStream in = getClass().getResourceAsStream(propFileName);
			  prop.load(in);
			  dbClassName=prop.getProperty("DB_CLASS_NAME");
			  dbUrl = prop.getProperty("DB_URL",
                      "jdbc:mysql://127.0.0.1:3306/student?user=root&password=root&useUnicode=true");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  }
	  
	  public static Connection getConnection(){
		  Connection conn =null;
		  try {
			Class.forName(dbClassName).newInstance();
			conn= DriverManager.getConnection(dbUrl);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.err.println("conn fail");
	
		}
		  if(conn==null){
			  System.err.println("error");
		  }
		  return conn;
	  }
	  public ResultSet executeQuery(String sql){
		  conn =getConnection();
		  try {
			stmt=conn.createStatement();
			rs=stmt.executeQuery(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  
		  
		  return rs;
	  }
	  
	  
	  
	  public int executeUpdate(String sql){
		  int result=0;
		  conn=getConnection();
		  try {
			stmt=conn.createStatement();
			result= stmt.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.err.println("fail");
			result=1;
		}
		  return result;
	  }
	  
	  public void close(){
		  try {
			  if (rs!=null) rs.close();
			  if (stmt!=null)stmt.close();
			  if (conn!=null)conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  }
}