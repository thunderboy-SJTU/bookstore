package bookstore3.Action;



import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.opensymphony.xwork2.ActionSupport;

import bookstore3.Entity.Book;
import bookstore3.Service.BookService;
import net.sf.json.JSONObject;

public class ShowDetail extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private String isbn;
	private JSONObject result;

	public String execute() throws Exception {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		BookService bookService = (BookService) ctx.getBean("bookService");
		Book book = bookService.getBook(isbn);
		JSONObject object = new JSONObject();
		object.put("isbn", book.getIsbn());
		object.put("title", book.getTitle());
		object.put("author", book.getAuthor());
		object.put("price", book.getPrice());
		object.put("catname", book.getCategory().getCatname());
		    object.put("description", book.getDescription());
		result = object;
		return SUCCESS;
	}

	public String getIsbn() {
		return isbn;
	}

	public JSONObject getResult() {
		return result;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public void setResult(JSONObject result) {
		this.result = result;
	}

}
