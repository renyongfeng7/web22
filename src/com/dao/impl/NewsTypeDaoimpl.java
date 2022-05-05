package com.dao.impl;

import com.bean.NewsType;
import com.dao.NewsTypeDao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NewsTypeDaoimpl extends BaseDao implements NewsTypeDao{

	/*��������ѯ*/
	public List<NewsType> selectAll() {
		ResultSet rs =  null;
		List<NewsType> list  =new ArrayList<NewsType>();
		try {
			rs =  this.select("select * from tbl_newstype", null);
			while(rs.next()){
				NewsType newsType = new NewsType();
				newsType.setTypeId(rs.getInt("typeId"));
				newsType.setTypeName(rs.getString("typeName"));
				list.add(newsType);
			}
			return list;
		} catch (SQLException e) {

			e.printStackTrace();
		}finally{
			this.closeResult(rs);
		}

		return null;
	}
	/*��������ѯ*/
	public NewsType select(String typeName) {
		ResultSet rs =  null;
		try {
			rs =  this.select("select * from tbl_newstype where typeName = ?", new Object[]{typeName});
			while(rs.next()){
				NewsType newsType = new NewsType();
				newsType.setTypeId(rs.getInt("typeId"));
				return newsType;
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}finally{
			this.closeResult(rs);
		}

		return null;
	}
	/*����IDɾ��*/
	public int deleteById(Integer typeId) {
		return this.update("DELETE FROM tbl_newstype WHERE typeId = ?;",new Object[]{typeId});
	}

	/*�����������*/
	public int insert(NewsType newstype) {
		return this.update("INSERT INTO tbl_newstype (typeName) VALUES (?);",new Object[]{newstype.getTypeName()});
	}

	/*�޸����*/
	public int update(NewsType newstype) {
		return this.update("UPDATE tbl_newstype SET typeName = ? WHERE typeId = ?;",new Object[]{newstype.getTypeName(),newstype.getTypeId()});
	}






}
