package bookstore3.Action;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.opensymphony.xwork2.ActionSupport;

import bookstore3.Entity.Order;
import bookstore3.Entity.User;
import bookstore3.Service.OrderService;
import bookstore3.Service.UserService;
import net.sf.json.JSONObject;

public class RemoveOrderAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private int orderid;
	private JSONObject result;
	public String execute() throws Exception {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		OrderService orderService = (OrderService) ctx.getBean("orderService");
		JSONObject json = new JSONObject();
		Order order = orderService.getOrder(orderid);	
		boolean bool = orderService.remove(order);
		if (bool == true)
			json.put("success", true);
		else
			json.put("msg", "some error occured.");
		result = json;
		return SUCCESS;
	}
	
	public void setOrderid(int orderid) {
		this.orderid = orderid;
	}
	
	public void setResult(JSONObject result)
	{
		this.result = result;
	}
	
	public int getOrderid()
	{
		return orderid;
	}
	
	public JSONObject getResult()
	{
		return result;
	}
}
