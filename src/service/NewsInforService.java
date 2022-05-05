package service;

import com.bean.NewsInfor;

import java.util.List;

/*������Ϣ�ӿ�*/
public interface NewsInforService {
	/*���*/
	public int insert(NewsInfor infor);
	/*ͨ��newsIdɾ��*/
	public int delete(Integer newsId);
	/*�����������ɾ��*/
	public int deleteBytype(Integer newsType);
	/*�޸�*/
	public int update(NewsInfor infor);
	/*ͨ��ID��ѯ*/
	public NewsInfor select(Integer newsId);
	/*��ѯ����*/
	public List<NewsInfor> selectAll();
	/*��ҳ��ѯ*/
	public List<NewsInfor> selectBypage(int pnum,int size,String type,String keywords);
}
