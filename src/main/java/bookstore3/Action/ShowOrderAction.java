package bookstore3.Action;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.opensymphony.xwork2.ActionSupport;


import bookstore3.Entity.Order;
import bookstore3.Service.OrderService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class ShowOrderAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private int page ;
	private int rows ;
	private String key;
	private JSONObject result;

public String execute() throws Exception {
	List<Order> list;
	int total;
	int offset = (page - 1)*rows;
	ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
	OrderService orderService = (OrderService)ctx.getBean("orderService");	
	JSONArray array = new JSONArray();
	JSONObject order = null;
	JSONObject json = new JSONObject();
	if(key == null || key.equals("")){	  
	  list = orderService.listAllOrder(offset, rows);
	}
	else
	{
	  list = orderService.listOrder(key,offset, rows);
	}
	total = orderService.getTotalCount();
		 for (int i=0;i<list.size();i++) {
		        order = new JSONObject();
		        order.put("orderid", list.get(i).getOrderid());
		        order.put("username", list.get(i).getUser().getUsername());
		        order.put("amount", list.get(i).getAmount());
		        order.put("all_quantity", list.get(i).getQuantity());
		        order.put("datetime", list.get(i).getDate().toString());		  
		        array.add(order);
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
