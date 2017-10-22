package bookstore3.DAO;

import java.util.List;

import bookstore3.Entity.Order;

public interface OrderDao {
	public List<Order> listAllOrder(int offset, int rows);

	public List<Order> listOrder(String key, int offset, int rows);
	
	public boolean addOrder(Order order);
    
	public boolean remove(Order order);
	
    public Order getOrder(int orderid);
    
    public int getTotalCount();
    
}
