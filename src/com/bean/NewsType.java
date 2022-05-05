package com.bean;

import java.io.Serializable;
/**
 * 新闻类型实体类*/
public class NewsType implements Serializable {
	/**
	 * 序列化ID
	 */
	private static final long serialVersionUID = -5103420377023157765L;
	/**
	 新闻类型ID
	 */
	private Integer typeId;
	/**
	 * 新闻类型名称
	 */
	private String typeName;
	public NewsType() {
		super();
	}
	
	
	
	public NewsType(Integer typeId) {
		super();
		this.typeId = typeId;
	}



	public NewsType(Integer typeId, String typeName) {
		super();
		this.typeId = typeId;
		this.typeName = typeName;
	}
	public Integer getTypeId() {
		return typeId;
	}
	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	
}
