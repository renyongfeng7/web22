package com.bean;

import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;
import java.io.Serializable;
/**
 * �û�ʵ����
 */
public class User implements Serializable, HttpSessionBindingListener {
	@Override
	/*��֪�ͼ���*/
	public void valueBound(HttpSessionBindingEvent event) {
		System.out.println("add��"+event.getName()+"---------"+"value:"+event.getValue());
	}
	@Override
	public void valueUnbound(HttpSessionBindingEvent event) {
		System.out.println("unbound��name:"+event.getName()+"---------"+"value:"+event.getValue());
	}

	/**
	 */
	private static final long serialVersionUID = 7626058954331462265L;
	/**
	 * �û�ID*/
	private Integer userId;
	/**
	 * �û�����*/
	private String realName;
	/**
	 * �û��˺�*/
	private String userName;
	/**
	 * �û�����*/
	private String userPwd;
	
	/**
	 * �޲ι���*/
	public User() {
		super();
	}
	/**
	 * ���ι���*/
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
