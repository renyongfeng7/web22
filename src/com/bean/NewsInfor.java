package com.bean;

import java.io.Serializable;
import java.util.Date;
/**
 * 新闻信息实体类*/
public class NewsInfor implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4263166742406889797L;
	/**
	 * 新闻ID */
	private Integer newsId;
	/**
	 * 新闻标题*/
	private String newsTitle;
	/**
	 * 新闻类型*/
	private NewsType newsType;
	/**
	 * 新闻图片*/
	private String newsImg;
	/**
	 * 新闻内容*/
	private String newsContent;
	/**
	 * 发送人*/
	private User sendUser;
	/**
	 * 发送时间*/
	private Date sendTime;
	
	public NewsInfor() {
		super();
	}
	public NewsInfor(Integer newsId, String newsTitle, NewsType newsType,
			String newsImg, String newsContent, User sendUser,
			Date sendTime) {
		super();
		this.newsId = newsId;
		this.newsTitle = newsTitle;
		this.newsType = newsType;
		this.newsImg = newsImg;
		this.newsContent = newsContent;
		this.sendUser = sendUser;
		this.sendTime = sendTime;
	}
	public Integer getNewsId() {
		return newsId;
	}
	public void setNewsId(Integer newsId) {
		this.newsId = newsId;
	}
	public String getNewsTitle() {
		return newsTitle;
	}
	public void setNewsTitle(String newsTitle) {
		this.newsTitle = newsTitle;
	}
	public NewsType getNewsType() {
		return newsType;
	}
	public void setNewsType(NewsType newsType) {
		this.newsType = newsType;
	}
	public String getNewsImg() {
		return newsImg;
	}
	public void setNewsImg(String newsImg) {
		this.newsImg = newsImg;
	}
	public String getNewsContent() {
		return newsContent;
	}
	public void setNewsContent(String newsContent) {
		this.newsContent = newsContent;
	}
	public User getSendUser() {
		return sendUser;
	}
	public void setSendUser(User sendUser) {
		this.sendUser = sendUser;
	}
	public Date getSendTime(){
		return sendTime;
	}
	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}
}
