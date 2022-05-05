package com.dao;

import java.util.List;
import com.bean.NewsType;

/*新闻类别接口*/
public interface NewsTypeDao {
	/*查询所有类别*/
	public List<NewsType> selectAll();
	/*根据ID删除*/
	public int deleteById(Integer typeId);
	/*添加类别*/
	public int insert(NewsType newstype);
	/*修改*/
	public int update(NewsType newstype);


}
