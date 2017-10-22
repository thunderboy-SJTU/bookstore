package bookstore3.Action;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.opensymphony.xwork2.ActionSupport;

import bookstore3.Entity.Cart;
import bookstore3.Entity.Order;
import bookstore3.Service.OrderService;

public class CheckOut extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String message;

	public String execute() throws Exception {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		OrderService orderService = (OrderService) ctx.getBean("orderService");
		HttpServletRequest request = ServletActionContext.getRequest();
		String user = (String) request.getSession().getAttribute("user");
		if (user == null) {
			message = "You are not log in.";
			return LOGIN;
		}
		System.out.println("hello");
		Cart cart = (Cart) request.getSession().getAttribute("cart");
		String username = (String) request.getSession().getAttribute("user");
		Order order = cart.toOrder(username);
		System.out.println(order.getUser().getUsername());
		System.out.println(order.getOrderid());
		boolean bool = orderService.addOrder(order);
		System.out.println(bool);
		if(bool == true)
		{
		request.getSession().removeAttribute("cart");
		request.getSession().setAttribute("order", order);
		return SUCCESS;
		}
		else
		{
			message = "Some error occured.";
			return ERROR;
		}
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

}
