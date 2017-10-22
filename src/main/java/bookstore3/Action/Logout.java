package bookstore3.Action;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

public class Logout extends ActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String execute() throws Exception {
	HttpServletRequest request = ServletActionContext.getRequest();		
	request.getSession().removeAttribute("user");
	if(request.getSession().getAttribute("superuser") != null)
	{
	request.getSession().removeAttribute("superuser");
	return SUCCESS;
	}
	return LOGIN;
	}
}
