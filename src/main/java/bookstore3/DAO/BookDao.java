package bookstore3.DAO;

import java.util.List;

import bookstore3.Entity.Book;
import bookstore3.Entity.User;

public interface BookDao {
	public List<Book> listAllBook(int offset, int rows);

	public List<Book> listBook(String key, int offset, int rows);
	
    public List<Book> listBook(String key);
	public boolean add(Book book);

	public boolean update(Book book);

	public boolean remove(Book book);

	public Book getBook(String isbn);

	public int getTotalCount();
}
