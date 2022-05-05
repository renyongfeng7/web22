package service.impl;

import com.bean.User;
import com.dao.UserDao;
import com.dao.impl.UserDaoImpl;
import service.UserService;

import java.util.List;

public class NewsUserServiceImpl implements UserService {
    UserDao userDao= new UserDaoImpl();
    @Override
    public User select(String name, String pwd){
        return userDao.select(name,pwd);
    }
    @Override
    public User select(String userName) {
        return this.userDao.select(userName);
    }
    @Override
    public List<User> selectAll() {
        return this.userDao.selectAll();
    }
    @Override
    public int deleteById(Integer id) {
        return this.userDao.deleteById(id);
    }
    @Override
    public int insert(User user) {
        return this.userDao.insert(user);
    }
    @Override
    public List<User> selectByPage(Integer page, Integer pageSize) {
        return this.userDao.selectByPage(page, pageSize);
    }

}
