package bookstore3.DAO;


import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import bookstore3.HibernateUtil;
import bookstore3.Entity.Book;
import bookstore3.Entity.Category;

public class CategoryDaoImpl implements CategoryDao {
	private int count;  
	public List<Category> listAllCategory(int offset, int rows)
	{
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Query query = session.createQuery("from Category");
		count = query.list().size();
		query.setFirstResult(offset);
		query.setMaxResults(rows);
		List categoryList = query.list();
		session.getTransaction().commit();			
    	return (List<Category>)categoryList;
	}
	public List<Category> listCategory(String key, int offset, int rows)
	{
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Query query = session.createQuery("from Category category where category.catid = ? or category.catname like ?");
		query.setString(0,key);
		query.setString(1,"%"+key+"%");
		count = query.list().size();
		query.setFirstResult(offset);
		query.setMaxResults(rows);
		List categoryList = query.list();
		session.getTransaction().commit();			
    	return (List<Category>)categoryList;
	}
	
	public boolean add(Category category)
	{
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
    	try{
    	session.beginTransaction();
    	session.persist(category);
    	session.getTransaction().commit();
    	return true;
    	}
    	catch(Exception e)
    	{
    		session.getTransaction().rollback();
    		return false;
    	}
	}
	public boolean remove(Category category)
	{
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
    	try{
    	session.beginTransaction();
		session.delete(category);
    	session.getTransaction().commit();
    	return true;
    	}
    	catch(Exception e)
    	{
    		session.getTransaction().rollback();
    		return false;
    	}
	}

	public Category getCategory(String catname)
    {
    	Session session = HibernateUtil.getSessionFactory().getCurrentSession();
    	try{
    	session.beginTransaction();
    	Query query = session.createQuery("from Category c where c.catname = ?");
    	query.setString(0,catname);
    	Category category = (Category)query.uniqueResult();
    	session.getTransaction().commit();
    	return category;
    	}
    	catch(Exception e)
    	{
    		session.getTransaction().rollback();
    		return null;
    	}
    }
	
	public int getTotalCount()
	{
		return count;
	}
}
