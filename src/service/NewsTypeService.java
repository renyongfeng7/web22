package service;

import com.bean.NewsType;

import java.util.List;

public interface NewsTypeService {
    /*查询所有类别*/
    public List<NewsType> selectAll();
    /*����IDɾ��*/
    public int deleteById(Integer typeId);
    /*������*/
    public int insert(NewsType newstype);
    /*�޸�*/
    public int update(NewsType newstype);
}
