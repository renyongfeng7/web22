package service.impl;

import com.bean.NewsInfor;
import com.bean.User;
import com.dao.NewsInforDao;
import com.dao.impl.NewsInforDaoImpl;
import service.NewsInforService;
import service.UserService;

import java.util.List;

public class NewsInforServiceImpl implements NewsInforService {

    NewsInforDao newsInforDao = new NewsInforDaoImpl();
    @Override
    public int insert(NewsInfor infor) {
        return this.newsInforDao.insert(infor);
    }

    /*删除*/
    public int delete(Integer newsId) {

        return this.newsInforDao.delete(newsId);
    }

    @Override
    public int deleteBytype(Integer newsType) {
        return 0;
    }

    /*修改*/
    public int update(NewsInfor infor) {

        return this.newsInforDao.update(infor);
    }

    @Override
    public NewsInfor select(Integer newsId) {

        return this.newsInforDao.select(newsId);
    }

    @Override
    public List<NewsInfor> selectAll() {

        return this.newsInforDao.selectAll();
    }


    public List<NewsInfor> selectBypage(int pnum, int size, String type,String keywords) {

        return this.newsInforDao.selectBypage(pnum,size,type,keywords);
    }


}
