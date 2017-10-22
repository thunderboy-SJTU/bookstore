package bookstore3.Entity;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;





public class Order {
	private int orderid;
	private User user;
	private List<Item> items = new ArrayList<Item>();
	private Date date;
	
	public void setOrderid(int orderid)
	{
		this.orderid = orderid;
	}
	
	public void setUser(User user)
	{
		this.user = user;
	}
	
	public void setItems(List<Item> items)
	{
		this.items = items;
	}
	
	public void setDate(Date date)
	{
		this.date = date;
	}
	
	public int getOrderid()
	{
		return orderid;
	}
	
	public User getUser()
	{
		return user;
	}
	
	public List<Item> getItems()
	{
		return items;
	}
	
	public Date getDate()
	{
		return date;
	}
	
	public int getQuantity()
	{
		int quantity = 0;
		for (Item item : items) 
		{
			quantity += item.getQuantity();
		}
		return quantity;
	}
	
	public double getAmount()
	{
		double amount = 0;
		for (Item item : items) 
		{
			amount += item.getAmount();
		}
		return amount;
	}
	
	public boolean isEmpty()
	{
		return items.isEmpty();
	}
}
