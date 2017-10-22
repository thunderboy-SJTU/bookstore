package bookstore3.Action;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.opensymphony.xwork2.ActionSupport;

import bookstore3.Entity.Book;
import bookstore3.Entity.Category;
import bookstore3.Service.BookService;
import bookstore3.Service.CategoryService;
import net.sf.json.JSONObject;

public class EditBookAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private String isbn;
	private String title;
	private String author;
	private String catname;
	private double price;
	private String description;
	private JSONObject result;
   
	public String execute() throws Exception {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		BookService bookService = (BookService) ctx.getBean("bookService");
		CategoryService categoryService = (CategoryService)ctx.getBean("categoryService");
		Book book = bookService.getBook(isbn);
		Category category = categoryService.getCategory(catname);
		JSONObject json = new JSONObject();
		if(category == null)
			json.put("msg","No such category.");
		else
		{
		book.setTitle(title);
		book.setAuthor(author);
		book.setCategory(category);
		book.setPrice(price);
		book.setDescription(description);		
		boolean bool = bookService.update(book);
		if (bool == true)
			json.put("success", true);
		else
			json.put("msg", "some error occured.");
		}
		result = json;
		return SUCCESS;
	}
	
	public void setIsbn(String value) {
		isbn = value;
	}

	public void setTitle(String value) {
		title = value;
	}

	public void setAuthor(String value) {
		author = value;
	}
    
	public void setCatname(String value) {
		catname = value;
	}

	public void setPrice(double value) {
		price = value;
	}
    
	public void setDescription(String value)
	{
		description = value;
	}
	
	public void setResult(JSONObject result) {
		this.result = result;
	}
	

	public String getIsbn() {
		return isbn;
	}

	public String getTitle() {
		return title;
	}

	public String getAuthor() {
		return author;
	}
    
	public String getCatname() {
		return catname;
	}
    

	public double getPrice() {
		return price;
	}
	
	public String getDescription()
	{
		return description;
	}
	
	public JSONObject getResult(){
		return result;
	}


}
