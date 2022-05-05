package com.bean;

import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;
import java.io.Serializable;
/**
 * 用户实体类
 */
public class User implements Serializable, HttpSessionBindingListener {
	@Override
	/*感知型监听*/
	public void valueBound(HttpSessionBindingEvent event) {
		System.out.println("add："+event.getName()+"---------"+"value:"+event.getValue());
	}
	@Override
	public void valueUnbound(HttpSessionBindingEvent event) {
		System.out.println("unbound：name:"+event.getName()+"---------"+"value:"+event.getValue());
	}

	/**
	 */
	private static final long serialVersionUID = 7626058954331462265L;
	/**
	 * 用户ID*/
	private Integer userId;
	/**
	 * 用户姓名*/
	private String realName;
	/**
	 * 用户账号*/
	private String userName;
	/**
	 * 用户密码*/
	private String userPwd;
	
	/**
	 * 无参构造*/
	public User() {
		super();
	}
	/**
	 * 带参构造*/
	public User(String userName){
		super();
		this.userName = userName;
	}
	
	public User(Integer userId, String realName, String userName, String userPwd) {
		super();
		this.userId = userId;
		this.realName = realName;
		this.userName = userName;
		this.userPwd = userPwd;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPwd() {
		return userPwd;
	}
	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}
	
	
	
}
