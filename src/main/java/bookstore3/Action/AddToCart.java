package bookstore3.Action;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.opensymphony.xwork2.ActionSupport;

import bookstore3.Entity.Book;
import bookstore3.Entity.Cart;
import bookstore3.Service.BookService;

public class AddToCart extends ActionSupport {

	private static final long serialVersionUID = 1L;
	private String books;
	private String message;

	public String execute() throws Exception {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		BookService bookService = (BookService) ctx.getBean("bookService");
		HttpServletRequest request = ServletActionContext.getRequest();
		String user = (String) request.getSession().getAttribute("user");
		if (user == null)
		{
			 message = "You are not log in.";
			 return LOGIN;
		}			
		if (books == null) {
			message = "Please choose one.";
			return SUCCESS;
		}
		Book book = bookService.getBook(books);
		Cart cart = (Cart) request.getSession().getAttribute("cart");
		if (cart == null)
			cart = new Cart();
		cart.addBook(book);
		request.getSession().setAttribute("cart", cart);
		message = "Success!";
		return SUCCESS;
	}
	
	public void setBooks(String books)
	{
		this.books = books;
	}
	public void setMessage(String message)
	{
		this.message = message;
	}
	
	public String getBooks()
	{
		return books;
	}
	
	public String getMessage()
	{
		return message;
	}
}
