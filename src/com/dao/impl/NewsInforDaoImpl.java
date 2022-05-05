package com.dao.impl;

import com.bean.NewsInfor;
import com.bean.NewsType;
import com.bean.User;
import com.dao.NewsInforDao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NewsInforDaoImpl extends BaseDao implements NewsInforDao{
	/*���������Ϣ*/
	public int insert(NewsInfor infor) {
		return this.update("insert into tbl_newsinfor (newsTitle,newsType,sendUser,sendTime,newsContent,newsImg) values(?,?,?,?,?,?)",
				new Object[]{infor.getNewsTitle(),infor.getNewsType().getTypeId(),infor.getSendUser().getUserId(),infor.getSendTime(),infor.getNewsContent(),infor.getNewsImg()});
	}
	/*����IDɾ��������Ϣ*/
	public int delete(Integer newsId) {
		return this.update("delete from tbl_newsinfor where newsId=?", new Object[]{newsId});
	}
	/*������������ɾ��*/
	public int deleteBytype(Integer newsType) {
		return this.update("delete from tbl_newsinfor where newsType  =?", new Object[]{newsType});
	}
	/*�޸�������Ϣ*/
	public int update(NewsInfor infor){
		return this.update("UPDATE tbl_newsinfor SET newsTitle = ?,newsType = ?,sendUser = ?,sendTime = ?,newsContent = ? WHERE newsId = ?" ,
				new Object[]{infor.getNewsTitle(),infor.getNewsType().getTypeId(),infor.getSendUser().getUserId(),infor.getSendTime(),infor.getNewsContent(),infor.getNewsId()});
	}
	/*����ID��ѯ*/
	public NewsInfor select(Integer newsId){
		ResultSet rs = null;
		rs = this.select("select * from tbl_newsinfor INNER JOIN tbl_newstype ON tbl_newsinfor.newsType = tbl_newstype.typeId INNER JOIN tbl_user ON tbl_newsinfor.sendUser = tbl_user.userId where newsId = ? ", new Object[]{newsId});
		try {
			while(rs.next()){
				NewsInfor newsInfor= new NewsInfor();
				NewsType newsType = new NewsType();
				newsType.setTypeName(rs.getString("typeName"));
				User user = new User();
				newsInfor.setNewsId(rs.getInt("newsId"));
				newsInfor.setNewsTitle(rs.getString("newsTitle"));
				newsInfor.setNewsType(newsType);
				user.setRealName(rs.getString("realName"));
				newsInfor.setSendUser(user);
				newsInfor.setSendTime(rs.getDate("sendTime"));
				newsInfor.setNewsContent(rs.getString("newsContent"));
				return newsInfor;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			this.closeResult(rs);
		}
		return null;
	}
	/*��ѯ����������Ϣ*/
	public List<NewsInfor> selectAll() {
		ResultSet rs = null;
		rs = this.select("select * from tbl_newsinfor  INNER JOIN tbl_newstype ON tbl_newsinfor.newsType = tbl_newstype.typeId INNER JOIN tbl_user ON tbl_newsinfor.sendUser = tbl_user.userId ", null);
		List<NewsInfor> list = new ArrayList<NewsInfor>();
		try {
			while(rs.next()){
				NewsInfor newsInfor = new NewsInfor();
				NewsType newsType  = new NewsType();
				newsType.setTypeName(rs.getString("typeName"));
				User user = new User();
				user.setRealName(rs.getString("realName"));
				newsInfor.setNewsId(rs.getInt("newsId"));
				newsInfor.setNewsTitle(rs.getString("newsTitle"));
				newsInfor.setNewsType(newsType);
				newsInfor.setSendUser(user);
				newsInfor.setSendTime(rs.getDate("sendTime"));
				list.add(newsInfor);
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			this.closeResult(rs);
		}
		return null;
	}

	/*总条数*/
	public int count(){
		ResultSet rs = null;
		rs = this.select("SELECT COUNT(*) num FROM tbl_newsinfor ", null);
		try {
			while(rs.next()){
				int num =  rs.getInt("num");
				//int num1 = num/3;
				return num;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			this.closeResult(rs);
		}
		return 0;
	}
	/*总页数*/
	public int pageSize(){
		ResultSet rs = null;
		rs = this.select("SELECT COUNT(*) num FROM tbl_newsinfor ", null);
		try {
			while(rs.next()){
				int num =  rs.getInt("num");
				if(num%5==0){
					return num/5;
				}else{
					int num1 = (num/5)+1;
					return num1;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			this.closeResult(rs);
		}
		return 0;
	}
	/*��ҳ��ѯ*/
	public List<NewsInfor> selectBypage(int pagenum, int pagesize,String type ,String keywords){
		int page = (pagenum-1)*pagesize;
		ResultSet rs = null;
		try {
			StringBuilder builder = new StringBuilder("SELECT * FROM tbl_newsinfor INNER JOIN tbl_newstype ON tbl_newsinfor.newsType = tbl_newstype.typeId INNER JOIN tbl_user ON tbl_newsinfor.sendUser = tbl_user.userId WHERE 1 = 1");

			List<Object> params = new ArrayList<Object>();
			if(type!=null&&!"".equals(type)){
				builder.append(" AND typeName = ? ");
				params.add(type);
			}
			if(!"".equals(keywords)&&keywords!=null){
				builder.append(" AND newsTitle  LIKE ? ");
				params.add("%"+keywords+"%");
			}
			builder.append(" ORDER BY tbl_newsinfor.newsId DESC limit ?,? ");
			params.add(page);
			params.add(pagesize);
			String sql = builder.toString();
			Object[] paramsArray = params.toArray();
			System.out.println("------"+sql);

			rs = this.select(sql, paramsArray);

			List<NewsInfor> list = new ArrayList<NewsInfor>();
			while(rs.next()){
				NewsInfor newsInfor= new NewsInfor();
				NewsType newsType  = new NewsType();
				newsType.setTypeName(rs.getString("typeName"));
				User user = new User();
				user.setRealName(rs.getString("realName"));
				user.setUserName(rs.getString("userName"));
				newsInfor.setNewsId(rs.getInt("newsId"));
				newsInfor.setNewsTitle(rs.getString("newsTitle"));
				newsInfor.setNewsType(newsType);
				newsInfor.setSendUser(user);

				newsInfor.setSendTime(rs.getDate("sendTime"));
				list.add(newsInfor);
				/*-���-*/
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
