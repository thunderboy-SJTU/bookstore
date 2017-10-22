package bookstore3.Service;

import java.util.List;

import bookstore3.DAO.CategoryDao;
import bookstore3.Entity.Category;

public class CategoryServiceImpl implements CategoryService {
	private CategoryDao categoryDao;

	public List<Category> listAllCategory(int offset, int rows) {
		return categoryDao.listAllCategory(offset, rows);
	}

	public List<Category> listCategory(String key, int offset, int rows) {
		return categoryDao.listCategory(key, offset, rows);
	}

	public boolean add(Category category) {
		return categoryDao.add(category);
	}

	public boolean remove(Category category) {
		return categoryDao.remove(category);
	}

	public Category getCategory(String catname) {
		return categoryDao.getCategory(catname);
	}

	public int getTotalCount()
	{
		return categoryDao.getTotalCount();
	}

	public CategoryDao getCategoryDao() {
		return categoryDao;
	}

	public void setCategoryDao(CategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}
}
