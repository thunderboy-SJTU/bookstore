package bookstore3.Action;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.opensymphony.xwork2.ActionSupport;

import bookstore3.Entity.Category;
import bookstore3.Service.CategoryService;
import net.sf.json.JSONObject;

public class AddCategoryAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    private String catname;
	private JSONObject result;

	public String execute() throws Exception {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		CategoryService categoryService = (CategoryService) ctx.getBean("categoryService");
		Category category = (Category)ctx.getBean("category");
		Category oldCategory = categoryService.getCategory(catname);
		JSONObject json = new JSONObject();
		if(oldCategory == null)
		{
		category.setCatname(catname);
		boolean bool = categoryService.add(category);
		if (bool == true)
			json.put("success", true);
		else
			json.put("msg", "some error occured.");
		}
		else
			json.put("msg","duplicated category.");
		result = json;
		return SUCCESS;
	}

	public void setCatname(String catname)
	{
		this.catname = catname;
	}
	
	public void setResult(JSONObject result) {
		this.result = result;
	}
	
	public String getCatname()
	{
		return catname;
	}
	
	public JSONObject getResult(){
		return result;
	}


}
