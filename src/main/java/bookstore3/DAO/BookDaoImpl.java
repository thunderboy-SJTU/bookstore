package bookstore3.DAO;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import bookstore3.HibernateUtil;
import bookstore3.Entity.Book;
import bookstore3.Entity.User;

public class BookDaoImpl implements BookDao {
	private int count;  
	public List<Book> listAllBook(int offset, int rows)
	{
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Query query = session.createQuery("from Book");
		count = query.list().size();
		query.setFirstResult(offset);
		query.setMaxResults(rows);
		List bookList = query.list();
		session.getTransaction().commit();			
    	return (List<Book>)bookList;
	}

	public List<Book> listBook(String key, int offset, int rows)
	{
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Query query = session.createQuery("from Book book where book.isbn = ? or book.title like ? or book.author like ? or book.category.catname like ? ");
		query.setString(0,key);
		query.setString(1,"%"+key+"%");
		query.setString(2,"%"+key+"%");
		query.setString(3,"%"+key+"%");
		count = query.list().size();
		query.setFirstResult(offset);
		query.setMaxResults(rows);
		List bookList = query.list();
		session.getTransaction().commit();			
    	return (List<Book>)bookList;
	}
	
	 public List<Book> listBook(String key)
	 {
		 Session session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			Query query = session.createQuery("from Book book where book.isbn = ? or book.title like ? or book.author like ? or book.category.catname like ? ");
			query.setString(0,key);
			query.setString(1,"%"+key+"%");
			query.setString(2,"%"+key+"%");
			query.setString(3,"%"+key+"%");
			count = query.list().size();
			List bookList = query.list();
			session.getTransaction().commit();			
	    	return (List<Book>)bookList;
	 }

	public boolean add(Book book)
	{
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
    	try{
    	session.beginTransaction();
    	session.persist(book);
    	session.getTransaction().commit();
    	return true;
    	}
    	catch(Exception e)
    	{
    		session.getTransaction().rollback();
    		return false;
    	}
	}
	
	public boolean update(Book book)
	{
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
    	try{
    	session.beginTransaction();
    	session.update(book);
    	session.getTransaction().commit();
    	return true;
    	}
    	catch(Exception e)
    	{
    		session.getTransaction().rollback();
    		return false;
    	}
	}

	public boolean remove(Book book)
	{
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
    	try{
    	session.beginTransaction();
    	session.delete(book);
    	session.getTransaction().commit();
    	return true;
    	}
    	catch(Exception e)
    	{
    		session.getTransaction().rollback();
    		return false;
    	}
	}

	public Book getBook(String isbn)
	{
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
    	session.beginTransaction();
    	Book book = (Book)session.get(Book.class,isbn);
    	session.getTransaction().commit();
    	return book;
	}
	public int getTotalCount()
	{
		return count;
	}
}
