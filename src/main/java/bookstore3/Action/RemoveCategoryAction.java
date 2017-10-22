package bookstore3.Action;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.opensymphony.xwork2.ActionSupport;

import bookstore3.Entity.Category;
import bookstore3.Service.CategoryService;
import net.sf.json.JSONObject;

public class RemoveCategoryAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private String catname;
	private JSONObject result;
	public String execute() throws Exception {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		CategoryService categoryService = (CategoryService) ctx.getBean("categoryService");
		JSONObject json = new JSONObject();
		Category category = categoryService.getCategory(catname);	
		if(category.getCatname().equals("others"))
			json.put("msg", "you cannot remove category  'others'.");
		else
		{
		boolean bool = categoryService.remove(category);
		if (bool == true)
			json.put("success", true);
		else
			json.put("msg", "some error occured.");
		}
		result = json;
		return SUCCESS;
	}

	public void setCatname(String catname) {
		this.catname = catname;
	}
	
	public void setResult(JSONObject result)
	{
		this.result = result;
	}
	
	public String getCatname()
	{
		return catname;
	}
	
	public JSONObject getResult()
	{
		return result;
	}

}
