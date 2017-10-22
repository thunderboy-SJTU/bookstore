package bookstore3.DAO;


import java.util.List;

import bookstore3.Entity.Category;

public interface CategoryDao {
	public List<Category> listAllCategory(int offset, int rows);
	public List<Category> listCategory(String key, int offset, int rows);
	public boolean add(Category category);
	public boolean remove(Category category);
	public Category getCategory(String catname);
	public int getTotalCount();
}
