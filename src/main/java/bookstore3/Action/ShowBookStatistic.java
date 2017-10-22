package bookstore3.Action;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.opensymphony.xwork2.ActionSupport;

import bookstore3.Entity.Book;
import bookstore3.Entity.Statistic;
import bookstore3.Service.BookService;
import bookstore3.Service.StatisticService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class ShowBookStatistic extends ActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String key;
	private JSONObject result;

public String execute() throws Exception {
	List<Statistic> list;

	ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
	StatisticService statisticService = (StatisticService)ctx.getBean("statisticService");	
	BookService bookService =(BookService)ctx.getBean("bookService");
	JSONArray array = new JSONArray();
	JSONObject statistic = null;
	JSONObject json = new JSONObject();
	if(key == null || key.equals("")){	  
	  list = statisticService.listAllBookStatistic();
	}
	else
	{
	  list = statisticService.listBookStatistic(key);
	}
		 for (int i=0;i<list.size();i++) {
		        statistic = new JSONObject();
		        statistic.put("key", list.get(i).getKeyword());
		        Book book = bookService.getBook(list.get(i).getKeyword());
		        statistic.put("title", book.getTitle());
		        statistic.put("quantity", list.get(i).getQuantity());
		        statistic.put("amount", list.get(i).getAmount());
		        array.add(statistic);
		      }			 
	json.put("rows", array);
	result = json;
	return SUCCESS;
  }

	
	public String getKey()
	{
		return key;
	}

	public JSONObject getResult() {
		return result;
	}


	public void setKey(String key)
	{
		this.key = key;
	}
	
	public void setResult(JSONObject result) {
		this.result = result;
	}
}
