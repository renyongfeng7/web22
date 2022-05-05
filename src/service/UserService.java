package service;

import com.bean.User;

import java.util.List;

public interface UserService {
    /*根据账号密码查询*/
    public User select(String userName, String userPwd);
    /*根据账号查询*/
    public User select(String userName);
    public List<User> selectAll();
    public  int deleteById(Integer id);
    public  int insert(User user);
    public  List<User> selectByPage(Integer page,Integer pageSize);


}
