package service.impl;

import com.bean.NewsType;
import com.dao.NewsTypeDao;
import com.dao.impl.NewsTypeDaoimpl;
import service.NewsTypeService;

import java.util.List;

public class NewsTypeServiceImpl implements service.NewsTypeService {

    NewsTypeDao newsTypeDao = new NewsTypeDaoimpl();
    /*查询所有类型*/
    public List<com.bean.NewsType> selectAll() {
        return this.newsTypeDao.selectAll();
    }
    /*根据ID删除类型*/
    @Override
    public int deleteById(Integer typeId)  {
        return this.newsTypeDao.deleteById(typeId);
    }
    /*添加类型*/
    @Override
    public int insert(NewsType newstype) {
        return this.newsTypeDao.insert(newstype);
    }
    /*修改*/
    @Override
    public int update(NewsType newstype) {
        return this.newsTypeDao.update(newstype);
    }
}
