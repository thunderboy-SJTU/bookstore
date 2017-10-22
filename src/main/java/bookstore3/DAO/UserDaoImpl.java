package bookstore3.DAO;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import bookstore3.HibernateUtil;
import bookstore3.Entity.User;

public class UserDaoImpl implements UserDao {
	 private int count;   
		public List<User> listAllUser(int offset, int rows)
		{
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			Query query = session.createQuery("from User user order by user.authority");
			count = query.list().size();
			query.setFirstResult(offset);
			query.setMaxResults(rows);
			List userList = query.list();
			session.getTransaction().commit();			
	    	return (List<User>)userList;
		}
		
		public List<User> listUser(String key,int offset, int rows)
		{
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			Query query = session.createQuery("from User user where user.username like ? or user.name like ? or user.address like ? or user.city like ? order by user.authority");
			query.setString(0,"%"+key+"%");
			query.setString(1,"%"+key+"%");
			query.setString(2,"%"+key+"%");
			query.setString(3,"%"+key+"%");
			count = query.list().size();
			query.setFirstResult(offset);
			query.setMaxResults(rows);
			List userList = query.list();
			session.getTransaction().commit();			
	    	return (List<User>)userList;
		}
	    public boolean add(User user)
	    {
	    	Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	    	try{
	    	session.beginTransaction();
	    	session.persist(user);
	    	session.getTransaction().commit();
	    	return true;
	    	}
	    	catch(Exception e)
	    	{
	    		session.getTransaction().rollback();
	    		return false;
	    	}
	    	
	    }
	    
	    public boolean update(User user)
	    {
	    	Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	    	try{
	    	session.beginTransaction();
	    	session.update(user);
	    	session.getTransaction().commit();
	    	return true;
	    	}
	    	catch(Exception e)
	    	{
	    		session.getTransaction().rollback();
	    		return false;
	    	}
	    }
	    
	    public boolean remove(User user)
	    {
	    	Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	    	try{
	    	session.beginTransaction();
	    	session.delete(user);
	    	session.getTransaction().commit();
	    	return true;
	    	}
	    	catch(Exception e)
	    	{
	    		session.getTransaction().rollback();
	    		return false;
	    	}
	    }
	    
	    public User getUser(String username)
	    {
	    	Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	    	session.beginTransaction();
	    	User user = (User)session.get(User.class,username);
	    	session.getTransaction().commit();
	    	return user;
	    }
	    
	    public int getTotalCount()
	    {
	    	return count;
	    }	    
	    
	}

