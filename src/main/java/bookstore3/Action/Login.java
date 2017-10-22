package bookstore3.Action;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.opensymphony.xwork2.ActionSupport;

import bookstore3.Entity.User;
import bookstore3.Service.UserService;

public class Login extends ActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String username;
	String password;
	String message;

	public String execute() throws Exception {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		UserService userService = (UserService) ctx.getBean("userService");
		User user = userService.getUser(username);
		if (user == null) {
			message = "No Such User.";
			return ERROR;
		}
		if (!user.getPassword().equals(password)) {
			message = "Wrong password.";
			return ERROR;
		} 
		HttpServletRequest request = ServletActionContext.getRequest();		
		message = "Welcome.";
	    request.getSession().setAttribute("user", username);
		if(user.getAuthority() == 0)
		{
			  request.getSession().setAttribute("superuser", username);
			  return SUCCESS;
		}
		return LOGIN;
	}
	
	public void setUsername(String username)
	{
		this.username = username;
	}
	
	public void setPassword(String password)
	{
		this.password = password;
	}
	
	public void setMessage(String message)
	{
		this.message = message;
	}
	
	public String getUsername()
	{
		return username;
	}
	
	public String getPassword()
	{
		return password;
	}
	
	public String getMessage()
	{
		return message;
	}
}
