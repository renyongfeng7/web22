package com.dao;

import java.util.List;

import com.bean.NewsInfor;

/*新闻信息接口*/
public interface NewsInforDao {
	/*添加*/
	public int insert(NewsInfor infor);
	/*通过newsId删除*/
	public int delete(Integer newsId);
	/*根据新闻类别删除*/
	public int deleteBytype(Integer newsType);
	/*修改*/
	public int update(NewsInfor infor);
	/*通过ID查询*/
	public NewsInfor select(Integer newsId);
	/*查询所有*/
	public List<NewsInfor> selectAll();
	/*分页查询*/
	public List<NewsInfor> selectBypage(int pnum,int size,String type,String keywords);
}
