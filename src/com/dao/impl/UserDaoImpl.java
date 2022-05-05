package com.dao.impl;

import com.bean.NewsType;
import com.bean.User;
import com.dao.UserDao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl extends BaseDao implements UserDao {
	/*��¼��ѯ�˺�����*/
	public User select(String userName, String userPwd) {
		ResultSet rs = null;
		try {
			rs = this.select("select * from tbl_user where userName = ? and userPwd = ? ",
					new Object[]{userName, userPwd});
			while (rs.next()) {
				User user = new User();
				user.setUserName(rs.getString("userName"));
				user.setUserPwd(rs.getString("userPwd"));
				user.setRealName(rs.getNString("realName"));
				user.setUserId(rs.getInt("userId"));
				return user;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.closeResult(rs);
		}
		return null;
	}

	/*根据用户名查询*/
	public User select(String userName) {
		ResultSet rs = null;
		try {
			rs = this.select("select * from tbl_user where userName = ? ",
					new Object[]{userName});
			while (rs.next()) {
				User user = new User();
				user.setRealName(rs.getString("realName"));
				user.setUserId(rs.getInt("userId"));
				return user;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.closeResult(rs);
		}
		return null;
	}
	@Override
	/*查询所有*/
	public List<User> selectAll() {
		ResultSet rs = null;
		List<User> list = new ArrayList<User>();
		rs = super.select("SELECT * FROM tbl_user",null);
		try {
			while (rs.next()){
				User user = new User();
				user.setUserId(rs.getInt("userId"));
				user.setRealName(rs.getString("realName"));
				user.setUserName(rs.getString("userName"));
				user.setUserPwd(rs.getString("userPwd"));
				list.add(user);
			}
			return list;

		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			this.closeResult(rs);
		}
		return null;
	}
	@Override
	public int deleteById(Integer id ) {
		return this.update("DELETE FROM tbl_user WHERE userId = ?",new Object[]{id});
	}
	@Override
	/*插入用户*/
	public int insert(User user) {
		return this.update("INSERT INTO tbl_user (realName,userName,userPwd) VALUES (?,?,?)",new Object[]{user.getRealName(),user.getUserName(),user.getUserPwd()});
	}

	@Override
	public List<User> selectByPage(Integer page, Integer pageSize) {
		/*起始页数*/
		int startPage = (page-1)*pageSize;
		ResultSet rs = null;
		rs =super.select("SELECT * FROM tbl_user LIMIT ?,?",new Object[]{startPage,pageSize});
		List<User> list = new ArrayList<User>();
		try {
			while (rs.next()) {
				User user = new User();
				user.setUserId(rs.getInt("userId"));
				user.setRealName(rs.getString("realName"));
				user.setUserName(rs.getString("userName"));
				user.setUserPwd(rs.getString("userPwd"));
				list.add(user);
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			this.closeResult(rs);
		}
		return null;
	}




}

