package bookstore3.Service;

import java.sql.Date;
import java.util.List;

import bookstore3.DAO.StatisticDao;
import bookstore3.Entity.Statistic;

public class StatisticServiceImpl implements StatisticService {
	private StatisticDao statisticDao;
	public List<Statistic> listAllUserStatistic()
	{
		return statisticDao.listAllUserStatistic();
	}
	public List<Statistic> listUserStatistic(String key)
	{
		return statisticDao.listUserStatistic(key);
	}
	
	public List<Statistic> listAllDateStatistic()
	{
		return statisticDao.listAllDateStatistic();
	}
	public List<Statistic> listDateStatistic(Date from, Date to)
	{
		return statisticDao.listDateStatistic(from,to);
	}
	public List<Statistic> listDateBefore(Date to)
	{
		return statisticDao.listDateBefore(to);
	}
	public List<Statistic> listDateAfter(Date from)
	{
		return statisticDao.listDateAfter(from);
	}
	
	public List<Statistic> listAllBookStatistic()
	{
		return statisticDao.listAllBookStatistic();
	}
	public List<Statistic> listBookStatistic(String key)
	{
		return statisticDao.listBookStatistic(key);
	}
	
	public List<Statistic> listAllCategoryStatistic()
	{
		return statisticDao.listAllCategoryStatistic();
	}
	public List<Statistic> listCategoryStatistic(String key)
	{
		return statisticDao.listCategoryStatistic(key);
	}

	public StatisticDao getCategoryDao() {
		return statisticDao;
	}

	public void setStatisticDao(StatisticDao statisticDao) {
		this.statisticDao = statisticDao;
	}

}
