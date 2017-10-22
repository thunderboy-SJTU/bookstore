package bookstore3.DAO;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import bookstore3.HibernateUtil;
import bookstore3.Entity.Statistic;


public class StatisticDaoImpl implements StatisticDao {

	public List<Statistic> listAllUserStatistic()
	{
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Query query = session.createSQLQuery("{CALL allUserStatistic()}");
		List<Object[]> list = query.list();
		List<Statistic> statisticList = new ArrayList<Statistic>();
		for(Object[] item :list)
		{
			Statistic statistic = new Statistic();
			statistic.setKeyword(String.valueOf(item[0]));
			statistic.setQuantity(Integer.parseInt(String.valueOf(item[1])));
			statistic.setAmount(Double.parseDouble(String.valueOf(item[2])));
			statisticList.add(statistic);
		}
		session.getTransaction().commit();			
    	return statisticList;
	}
	
	public List<Statistic> listUserStatistic(String key)
	{
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Query query = session.createSQLQuery("{CALL userStatistic(?)}");
		query.setString(0,key);
		List<Object[]> list = query.list();
		List<Statistic> statisticList = new ArrayList<Statistic>();
		for(Object[] item :list)
		{
			Statistic statistic = new Statistic();
			statistic.setKeyword(String.valueOf(item[0]));
			statistic.setQuantity(Integer.parseInt(String.valueOf(item[1])));
			statistic.setAmount(Double.parseDouble(String.valueOf(item[2])));
			statisticList.add(statistic);
		}
		session.getTransaction().commit();			
    	return statisticList;
	}
	
	public List<Statistic> listAllDateStatistic()
	{
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Query query = session.createSQLQuery("{CALL allDateStatistic()}");
		List<Object[]> list = query.list();
		List<Statistic> statisticList = new ArrayList<Statistic>();
		for(Object[] item :list)
		{
			Statistic statistic = new Statistic();
			statistic.setKeyword(String.valueOf(item[0]));
			statistic.setQuantity(Integer.parseInt(String.valueOf(item[1])));
			statistic.setAmount(Double.parseDouble(String.valueOf(item[2])));
			statisticList.add(statistic);
		}
		session.getTransaction().commit();			
    	return statisticList;
	}
	public List<Statistic> listDateStatistic(Date from, Date to)
	{
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Query query = session.createSQLQuery("{CALL dateStatistic(?,?)}");
		query.setDate(0,from);
		query.setDate(1, to);
		List<Object[]> list = query.list();
		List<Statistic> statisticList = new ArrayList<Statistic>();
		for(Object[] item :list)
		{
			Statistic statistic = new Statistic();
			statistic.setKeyword(String.valueOf(item[0]));
			statistic.setQuantity(Integer.parseInt(String.valueOf(item[1])));
			statistic.setAmount(Double.parseDouble(String.valueOf(item[2])));
			statisticList.add(statistic);
		}
		session.getTransaction().commit();			
    	return statisticList;
	}
	public List<Statistic> listDateBefore(Date to)
	{
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Query query = session.createSQLQuery("{CALL dateBefore(?)}");
		query.setDate(0, to);
		List<Object[]> list = query.list();
		List<Statistic> statisticList = new ArrayList<Statistic>();
		for(Object[] item :list)
		{
			Statistic statistic = new Statistic();
			statistic.setKeyword(String.valueOf(item[0]));
			statistic.setQuantity(Integer.parseInt(String.valueOf(item[1])));
			statistic.setAmount(Double.parseDouble(String.valueOf(item[2])));
			statisticList.add(statistic);
		}
		session.getTransaction().commit();			
    	return statisticList;
	}
	public List<Statistic> listDateAfter(Date from)
	{
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Query query = session.createSQLQuery("{CALL dateAfter(?)}");
		query.setDate(0,from);
		List<Object[]> list = query.list();
		List<Statistic> statisticList = new ArrayList<Statistic>();
		for(Object[] item :list)
		{
			Statistic statistic = new Statistic();
			statistic.setKeyword(String.valueOf(item[0]));
			statistic.setQuantity(Integer.parseInt(String.valueOf(item[1])));
			statistic.setAmount(Double.parseDouble(String.valueOf(item[2])));
			statisticList.add(statistic);
		}
		session.getTransaction().commit();			
    	return statisticList;
	}
	
	public List<Statistic> listAllBookStatistic()
	{
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Query query = session.createSQLQuery("{CALL allBookStatistic()}");
		List<Object[]> list = query.list();
		List<Statistic> statisticList = new ArrayList<Statistic>();
		for(Object[] item :list)
		{
			Statistic statistic = new Statistic();
			System.out.println(item[0]);
			statistic.setKeyword(String.valueOf(item[0]));
			System.out.println(item[1]);
			statistic.setQuantity(Integer.parseInt(String.valueOf(item[1])));
			System.out.println(item[2]);
			statistic.setAmount(Double.parseDouble(String.valueOf(item[2])));
			statisticList.add(statistic);
		}
		session.getTransaction().commit();			
    	return statisticList;
	}
	
	public List<Statistic> listBookStatistic(String key)
	{
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Query query = session.createSQLQuery("{CALL bookStatistic(?)}");
		query.setString(0,key);
		List<Object[]> list = query.list();
		List<Statistic> statisticList = new ArrayList<Statistic>();
		for(Object[] item :list)
		{
			Statistic statistic = new Statistic();
			statistic.setKeyword(String.valueOf(item[0]));
			statistic.setQuantity(Integer.parseInt(String.valueOf(item[1])));
			statistic.setAmount(Double.parseDouble(String.valueOf(item[2])));
			statisticList.add(statistic);
		}
		session.getTransaction().commit();			
    	return statisticList;
	}
	
	public List<Statistic> listAllCategoryStatistic()
	{
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Query query = session.createSQLQuery("{CALL allCategoryStatistic()}");
		List<Object[]> list = query.list();
		List<Statistic> statisticList = new ArrayList<Statistic>();
		for(Object[] item :list)
		{
			Statistic statistic = new Statistic();
			statistic.setKeyword(String.valueOf(item[0]));
			statistic.setQuantity(Integer.parseInt(String.valueOf(item[1])));
			statistic.setAmount(Double.parseDouble(String.valueOf(item[2])));
			statisticList.add(statistic);
		}
		session.getTransaction().commit();			
    	return statisticList;
	}
	public List<Statistic> listCategoryStatistic(String key){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Query query = session.createSQLQuery("{CALL categoryStatistic(?)}");
		query.setString(0,key);
		List<Object[]> list = query.list();
		List<Statistic> statisticList = new ArrayList<Statistic>();
		for(Object[] item :list)
		{
			Statistic statistic = new Statistic();
			statistic.setKeyword(String.valueOf(item[0]));
			statistic.setQuantity(Integer.parseInt(String.valueOf(item[1])));
			statistic.setAmount(Double.parseDouble(String.valueOf(item[2])));
			statisticList.add(statistic);
		}
		session.getTransaction().commit();			
    	return statisticList;
	}
	
}
