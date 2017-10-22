package bookstore3.Action;

import java.util.List;
import java.util.Set;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.opensymphony.xwork2.ActionSupport;

import bookstore3.Entity.Item;
import bookstore3.Entity.Order;
import bookstore3.Service.OrderService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class OrderDetailAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	private int orderid;
	private JSONObject result;

	public String execute() throws Exception {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		OrderService orderService = (OrderService) ctx.getBean("orderService");
		JSONObject json = new JSONObject();
		JSONArray array = new JSONArray();
		JSONObject orderItem = null;
		Order order = orderService.getOrder(orderid);
		List<Item> items = order.getItems();
		for (Item item : items) {
			orderItem = new JSONObject();
			orderItem.put("isbn", item.getBook().getIsbn());
			orderItem.put("title", item.getBook().getTitle());
			orderItem.put("author", item.getBook().getAuthor());
			orderItem.put("category", item.getBook().getCategory().getCatname());
			orderItem.put("price", item.getBook().getPrice());
			orderItem.put("quantity", item.getQuantity());
			orderItem.put("amount", item.getAmount());
			array.add(orderItem);
		}
		json.put("rows", array);
		result = json;
		return SUCCESS;
	}

	public int getOrderid() {
		return orderid;
	}

	public JSONObject getResult() {
		return result;
	}

	public void setOrderid(int orderid) {
		this.orderid = orderid;
	}

	public void setResult(JSONObject result) {
		this.result = result;
	}
}
