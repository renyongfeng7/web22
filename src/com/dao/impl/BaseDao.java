package com.dao.impl;

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class BaseDao {
	private static String driver ;
	private static String url;
	private static String user;
	private static String password;
	static{
		/*读取配置文件*/
		Properties pro = new Properties();
		InputStream is = BaseDao.class.getClassLoader().getResourceAsStream("pei.properties");
		try {
			/*System.out.println(is);*/
			pro.load(is);
			driver = pro.getProperty("driver");
			url = pro.getProperty("url");
			user= pro.getProperty("user");
			password = pro.getProperty("password");
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		/*加载驱动*/
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e){
			e.printStackTrace();
		}
	}
	/*提取链接方法*/
	public Connection connection() throws SQLException{
		return  DriverManager.getConnection(url, user, password);
	}
	/*增删改通用方法*/
	public int update(String sql,Object[] value){
		try {
			PreparedStatement pre = null;
			Connection con = this.connection();
			 pre = con.prepareStatement(sql);
			 if(value!=null&&value.length>0){
				 for (int i = 0; i < value.length; i++){
					pre.setObject(i+1, value[i]);
				}
			 }
			 int j = pre.executeUpdate();
			 this.closeAll(con, pre, null);
			 return j;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	/*查询*/
	public ResultSet select(String sql,Object[] value){
		try {
			PreparedStatement pre = null;
			Connection con =this.connection();
			 pre = con.prepareStatement(sql);
			 if(value!=null&&value.length>0){
				 for (int i = 0; i < value.length; i++){
					pre.setObject(i+1, value[i]);
				 }
			 }
			 ResultSet rs = pre.executeQuery();
			 return rs;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	/*释放全部资源*/
	public void closeAll(Connection c,Statement p,ResultSet r) throws SQLException{
		if(r!=null){
			r.close();
		}
		if(p!=null){
			p.close();
		}
		if(c!=null){
			c.close();
		}
	}
	/*释放结果集资源*/
	public void closeResult(ResultSet r){
		Connection con= null;
		Statement pre = null;
		try {
			if(r==null){
				return ;
			}else{
			pre = r.getStatement();
			con = pre.getConnection();
			this.closeAll(con,pre,r);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
