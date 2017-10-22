package bookstore3.Action;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.opensymphony.xwork2.ActionSupport;

import bookstore3.Entity.Book;
import bookstore3.Service.BookService;
import net.sf.json.JSONObject;

public class RemoveBookAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private String isbn;
	private JSONObject result;
	public String execute() throws Exception {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		BookService bookService = (BookService) ctx.getBean("bookService");
		JSONObject json = new JSONObject();
		Book book = bookService.getBook(isbn);	
		boolean bool = bookService.remove(book);
		if (bool == true)
			json.put("success", true);
		else
			json.put("msg", "some error occured.");
		result = json;
		return SUCCESS;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	
	public void setResult(JSONObject result)
	{
		this.result = result;
	}
	
	public String getIsbn()
	{
		return isbn;
	}
	
	public JSONObject getResult()
	{
		return result;
	}
}
