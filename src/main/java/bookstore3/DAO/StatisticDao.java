package bookstore3.DAO;

import java.sql.Date;
import java.util.List;

import bookstore3.Entity.Statistic;


public interface StatisticDao {
	public List<Statistic> listAllUserStatistic();
	public List<Statistic> listUserStatistic(String key);
	public List<Statistic> listAllDateStatistic();
	public List<Statistic> listDateStatistic(Date from, Date to);
	public List<Statistic> listDateBefore(Date to);
	public List<Statistic> listDateAfter(Date from);
	public List<Statistic> listAllBookStatistic();
	public List<Statistic> listBookStatistic(String key);
	public List<Statistic> listAllCategoryStatistic();
	public List<Statistic> listCategoryStatistic(String key);
}
