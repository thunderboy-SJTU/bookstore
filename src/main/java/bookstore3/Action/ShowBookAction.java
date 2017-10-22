package bookstore3.Action;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.opensymphony.xwork2.ActionSupport;

import bookstore3.Entity.Book;
import bookstore3.Service.BookService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class ShowBookAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private int page ;
	private int rows ;
	private String key;
	private JSONObject result;

public String execute() throws Exception {
	List<Book> list;
	int total;
	int offset = (page - 1)*rows;
	ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
	BookService bookService = (BookService)ctx.getBean("bookService");	
	JSONArray array = new JSONArray();
	JSONObject book = null;
	JSONObject json = new JSONObject();
	if(key == null || key.equals("")){	  
	  list = bookService.listAllBook(offset, rows);
	}
	else
	{
	  list = bookService.listBook(key,offset, rows);
	}
	total = bookService.getTotalCount();
		 for (int i=0;i<list.size();i++) {
		        book = new JSONObject();
		        book.put("isbn", list.get(i).getIsbn());
		        book.put("title", list.get(i).getTitle());
		        book.put("author", list.get(i).getAuthor());
		        book.put("price", list.get(i).getPrice());
		        book.put("catname", list.get(i).getCategory().getCatname());
		        book.put("description", list.get(i).getDescription());		  
		        array.add(book);
		      }			 
	json.put("total", total);
	json.put("rows", array);
	result = json;
	return SUCCESS;
  }
    public int getPage()
    {
    	return page;
    }
	public int getRows(){
		return rows;
	}
	
	public String getKey()
	{
		return key;
	}

	public JSONObject getResult() {
		return result;
	}

	public void setPage(int page)
	{
		this.page = page;
	}
	
	public void setRows(int rows)
	{
		this.rows = rows;
	}
	
	public void setKey(String key)
	{
		this.key = key;
	}
	
	public void setResult(JSONObject result) {
		this.result = result;
	}
	
	
	
}
