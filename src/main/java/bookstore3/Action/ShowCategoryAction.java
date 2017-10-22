package bookstore3.Action;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.opensymphony.xwork2.ActionSupport;

import bookstore3.Entity.Category;
import bookstore3.Service.CategoryService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class ShowCategoryAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private int page ;
	private int rows ;
	private String key;
	private JSONObject result;

public String execute() throws Exception {
	List<Category> list;
	int total;
	int offset = (page - 1)*rows;
	ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
	CategoryService categoryService = (CategoryService)ctx.getBean("categoryService");	
	JSONArray array = new JSONArray();
	JSONObject category = null;
	JSONObject json = new JSONObject();
	if(key == null || key.equals("")){	  
	  list = categoryService.listAllCategory(offset, rows);
	}
	else
	{
	  list = categoryService.listCategory(key,offset, rows);
	}
	total = categoryService.getTotalCount();
		 for (int i=0;i<list.size();i++) {
		        category = new JSONObject();
		        category.put("catid", list.get(i).getCatid());
		        category.put("catname", list.get(i).getCatname());
		        array.add(category);
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
