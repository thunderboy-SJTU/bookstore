package bookstore3.Action;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.opensymphony.xwork2.ActionSupport;

import bookstore3.Entity.User;
import bookstore3.Service.UserService;
import net.sf.json.JSONObject;

public class Register extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private String username;
	private String password;
	private String name;
	private String address;
	private String city;
	private JSONObject result;

	public String execute() throws Exception {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		UserService userService = (UserService) ctx.getBean("userService");
		User user = (User)ctx.getBean("user");
		user.setUsername(username);
		user.setPassword(password);
		user.setName(name);
		user.setAddress(address);
		user.setCity(city);
		JSONObject json = new JSONObject();
		boolean bool = userService.add(user);
		if (bool == true)
		{
			json.put("success", true);
			HttpServletRequest request = ServletActionContext.getRequest();
			request.getSession().setAttribute("user", username);
		}
		else
			json.put("msg", "some error occured.");
		result = json;
		return SUCCESS;
	}

	public void setUsername(String value) {
		username = value;
	}

	public void setPassword(String value) {
		password = value;
	}

	public void setName(String value) {
		name = value;
	}

	public void setAddress(String value) {
		address = value;
	}

	public void setCity(String value) {
		city = value;
	}


	public void setResult(JSONObject result) {
		this.result = result;
	}
	
	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getName() {
		return name;
	}

	public String getAddress() {
		return address;
	}

	public String getCity() {
		return city;
	}


	public JSONObject getResult(){
		return result;
	}

}
