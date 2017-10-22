package bookstore3.Service;

import java.util.List;

import bookstore3.DAO.OrderDao;
import bookstore3.Entity.Order;

public class OrderServiceImpl implements OrderService {
	private OrderDao orderDao;
	
	public List<Order> listAllOrder(int offset, int rows)
	{
		return orderDao.listAllOrder(offset, rows);
	}

	public List<Order> listOrder(String key, int offset, int rows)
	{
        return orderDao.listOrder(key, offset, rows);
	}
	
	public boolean addOrder(Order order)
	{
		return orderDao.addOrder(order);
	}
	
	public boolean remove(Order order)
	{
		return orderDao.remove(order);
	}
	public Order getOrder(int orderid)
	{
		return orderDao.getOrder(orderid);
	}
	
	public int getTotalCount()
	{
		return orderDao.getTotalCount();
	}
	
	 public OrderDao getOrderDao(){
		  return orderDao;
	  }
	  public void setOrderDao(OrderDao orderDao){
		  this.orderDao = orderDao;
	  }
}
