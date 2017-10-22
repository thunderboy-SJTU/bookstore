package bookstore3.Action;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.opensymphony.xwork2.ActionSupport;

import bookstore3.Entity.User;
import bookstore3.Service.UserService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


public class ShowUserAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	private int page ;
	private int rows ;
	private String key;
	private JSONObject result;

public String execute() throws Exception {
	List<User> list;
	int total;
	int offset = (page - 1)*rows;
	ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
	UserService userService = (UserService)ctx.getBean("userService");	
	JSONArray array = new JSONArray();
	JSONObject user = null;
	JSONObject json = new JSONObject();
	if(key == null || key.equals("")){	  
	  list = userService.listAllUser(offset, rows);
	}
	else
	{
	  list = userService.listUser(key,offset, rows);
	}
	total = userService.getTotalCount();
		 for (int i=0;i<list.size();i++) {
		        user = new JSONObject();
		        user.put("username", list.get(i).getUsername());
		        user.put("password", list.get(i).getPassword());
		        user.put("name", list.get(i).getName());
		        user.put("address", list.get(i).getAddress());
		        user.put("city", list.get(i).getCity());
		        user.put("authority", list.get(i).getAuthority());		  
		        array.add(user);
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
