package org.hnote.mysql;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;

/**
 * mysql connection
 * 
 * @author Zehao Jin
 *
 */
public class MysqlConn {

	private static final String DRIVER = "com.mysql.jdbc.Driver";
	private static final String USER = "root";
	private static final String PASSWD = "mysql";
	private static final String URL = "jdbc:mysql://222.201.xxx.xx:3306/books?useUnicode=true&characterEncoding=UTF-8";
	private static final String tableName = "books2";
	private Connection con;
	private Statement statement;
	public MysqlConn(){
		try{
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWD);
		}catch(ClassNotFoundException ex){
			System.err.println("driver not found");
			ex.printStackTrace();
		}catch(SQLException ex){
			ex.printStackTrace();
		}
	}
	
	public void close(){
		try{
			if(con != null)
				con.close();
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	public void insertBatch(String host,Map<String,String> b){
		if(b.isEmpty()){
			System.out.println("Empty set.");
			return;
		}
//		try{
//			statement = con.createStatement();
			for(String w:b.keySet()){
				//System.out.println(b.get(w));
				String cat = b.get(w).split("#")[0].trim();
				String name = b.get(w).split("#")[1].trim();
				String sql = "insert into " +tableName + " values('" + host +"','" + w + "','" + cat + "','" + name +"')";
//				statement.addBatch(sql);
				insertSQL(sql);
			}
//			statement.executeBatch();
			//con.commit();
//		}catch(Exception ex){
//			System.out.println(ex.getMessage());
			//ex.printStackTrace();
//		}
	}
	
	public void insertSQL(String sql){
		try{
			statement = con.createStatement();
			statement.executeUpdate(sql);
		} catch (SQLException ex){
		}
	}
}
