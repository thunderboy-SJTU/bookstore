package bookstore3.Action;

import bookstore3.Entity.Book;
import bookstore3.Service.BookService;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.opensymphony.xwork2.ActionSupport;

public class Search extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String key;

	public String execute() throws Exception {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		BookService bookService = (BookService) ctx.getBean("bookService");
		List<Book> list = bookService.listBook(key);
		HttpServletRequest request = ServletActionContext.getRequest();
		request.getSession().setAttribute("list", list);
		return SUCCESS;
	}
	
	public void setKey(String key)
	{
		this.key = key;
	}
	
	public String getKey()
	{
		return key;
	}

}
