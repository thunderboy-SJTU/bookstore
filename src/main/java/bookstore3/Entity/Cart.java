package bookstore3.Entity;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import bookstore3.Service.UserService;



public class Cart {
		private List<Item> items = new ArrayList<Item>();

		
		public void setItems(List<Item> items)
		{
			this.items = items;
		}
        
		
		public List<Item> getItems()
		{
			return items;
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

	
	   public Item findItem(Book book)
	   {
		   for(Item item : items)
		   {
			   if(item.getBook().getIsbn().equals(book.getIsbn()))
				   return item;
		   }
		   return null;
	   }
	   
	   public void addBook(Book book)
	   {
		   Item item = findItem(book);
		   if(item != null)
		   {
			   item.setQuantity(item.getQuantity()+1);
		   }
		   else
		   {
			   item = new Item();
			   item.setBook(book);
			   item.setQuantity(1);
			   items.add(item);
		   }
	   }
	   
	   public void removeBook(Book book)
	   {
		   for(int i = 0; i< items.size();i++)
		   {
			   if(items.get(i).getBook().getIsbn().equals(book.getIsbn()))
			   {
				   items.remove(i);
				   break;
			   }
		   }
	   }
	   
	   public void removeAll(List<Book> list)
	   {
		  if(list != null)
		  for(int i = 0;i<list.size();i++)
		  {
			  Book book = list.get(i);
			  removeBook(book);
		  }
	   }
	   
	   public void changeQuantity(Book book,int count)
	   {
		   if(count > 0)
		   {
			   Item item = findItem(book);
			   item.setQuantity(count);  
		   }	   
	   }
	   
	   public boolean isEmpty()
	   {
		  return items.isEmpty();
	   }
	   
	   public Order toOrder(String username)
	   {
		   ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		   UserService userService = (UserService) ctx.getBean("userService");
		   User user = userService.getUser(username);
		   Order order = new Order();
		   order.setUser(user);
		   order.setItems(getItems());
		   java.util.Date utilDate=new java.util.Date();
		   Date date = new Date(utilDate.getTime());
		   order.setDate(date);
		   return order;
	   }
	}

