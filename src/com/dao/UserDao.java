package com.dao;

import com.bean.User;
import java.util.List;

/*�û��ӿ�*/
public interface UserDao {
	/*�����˺������ѯ*/
	public User select(String userName,String userPwd);
	/*�����˺Ų�ѯ*/
	public User select(String userName);
	public List<User> selectAll();
	public  int deleteById(Integer id);
	public  int insert(User user);
	public  List<User> selectByPage(Integer page,Integer pageSize);
}
