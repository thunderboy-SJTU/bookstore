package bookstore3.DAO;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import bookstore3.HibernateUtil;
import bookstore3.Entity.Book;
import bookstore3.Entity.Order;
import bookstore3.Entity.User;

public class OrderDaoImpl implements OrderDao {
	private int count;

	public List<Order> listAllOrder(int offset, int rows) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Query query = session.createQuery("from Order");
		count = query.list().size();
		query.setFirstResult(offset);
		query.setMaxResults(rows);
		List orderList = query.list();
		session.getTransaction().commit();
		return (List<Order>) orderList;
	}

	public List<Order> listOrder(String key, int offset, int rows) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Query query = session.createQuery(
				"from Order order where order.orderid = ? or order.user.username like ? or order.date like ?");
		query.setString(0, key);
		query.setString(1, "%" + key + "%");
		query.setString(2, "%" + key + "%");
		count = query.list().size();
		query.setFirstResult(offset);
		query.setMaxResults(rows);
		List orderList = query.list();
		session.getTransaction().commit();
		return (List<Order>) orderList;
	}
    
	
	public boolean addOrder(Order order)
	{
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
    	try{
    	session.beginTransaction();
    	session.save(order);
    	session.getTransaction().commit();
    	return true;
    	}
    	catch(Exception e)
    	{
    		session.getTransaction().rollback();
    		return false;
    	}
   
	}
	
	public boolean remove(Order order) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.delete(order);
			session.getTransaction().commit();
			return true;
		} catch (Exception e) {
			session.getTransaction().rollback();
			return false;
		}
	}

	public Order getOrder(int orderid) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Order order = (Order) session.get(Order.class, orderid);
		session.getTransaction().commit();
		return order;
	}

	public int getTotalCount()
	{
		return count;
	}
}
