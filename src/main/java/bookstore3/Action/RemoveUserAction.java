package bookstore3.Action;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.opensymphony.xwork2.ActionSupport;

import bookstore3.Entity.User;
import bookstore3.Service.UserService;
import net.sf.json.JSONObject;

public class RemoveUserAction extends ActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String username;
	private JSONObject result;
	public String execute() throws Exception {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		UserService userService = (UserService) ctx.getBean("userService");
		JSONObject json = new JSONObject();
		User user = userService.getUser(username);	
		boolean bool = userService.remove(user);
		if (bool == true)
			json.put("success", true);
		else
			json.put("msg", "some error occured.");
		result = json;
		return SUCCESS;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	public void setResult(JSONObject result)
	{
		this.result = result;
	}
	
	public String getUsername()
	{
		return username;
	}
	
	public JSONObject getResult()
	{
		return result;
	}

}
