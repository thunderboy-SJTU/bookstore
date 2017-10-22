package bookstore3.Action;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.opensymphony.xwork2.ActionSupport;

import bookstore3.Entity.Statistic;
import bookstore3.Service.StatisticService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class ShowDateStatistic extends ActionSupport {

	private static final long serialVersionUID = 1L;
	private String from;
	private String to;
	private JSONObject result;

public String execute() throws Exception {
	List<Statistic> list;

	ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
	StatisticService statisticService = (StatisticService)ctx.getBean("statisticService");	
	JSONArray array = new JSONArray();
	JSONObject statistic = null;
	JSONObject json = new JSONObject();
	SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
	if((from == null || from.equals(""))&&(to == null || to.equals(""))){	  
	   list = statisticService.listAllDateStatistic();
	}
	else if(from == null || from.equals(""))
	{
		 java.util.Date utildateto = sdf.parse(to);
		  Date dateto = new Date(utildateto.getTime());
		list = statisticService.listDateBefore(dateto);
	}
	else if(to == null ||to.equals(""))
	{
		java.util.Date utildatefrom=sdf.parse(from);
		Date datefrom = new Date(utildatefrom.getTime());
		list = statisticService.listDateAfter(datefrom);
	}
	else
	{
		java.util.Date utildatefrom=sdf.parse(from); 
		java.util.Date utildateto = sdf.parse(to);
		Date datefrom = new Date(utildatefrom.getTime());
		Date dateto = new Date(utildateto.getTime());
		list = statisticService.listDateStatistic(datefrom, dateto);
	}
	 for (int i=0;i<list.size();i++) {
	        statistic = new JSONObject();
	        statistic.put("key", list.get(i).getKeyword());
	        statistic.put("quantity", list.get(i).getQuantity());
	        statistic.put("amount", list.get(i).getAmount());
	        array.add(statistic);
	      }			 
    json.put("rows", array);
    result = json;
	return SUCCESS;
  }

	
	public String getFrom()
	{
		return from;
	}
	
	public String getTo()
	{
		return to;
	}

	public JSONObject getResult() {
		return result;
	}


	public void setFrom(String from)
	{
		this.from = from;
	}
	
	public void setTo(String to)
	{
		this.to = to;
	}
	public void setResult(JSONObject result) {
		this.result = result;
	}
	

}
