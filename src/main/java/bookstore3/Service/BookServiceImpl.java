package bookstore3.Service;

import java.util.List;

import bookstore3.DAO.BookDao;
import bookstore3.Entity.Book;

public class BookServiceImpl implements BookService {
	private BookDao bookDao;
	
	public List<Book> listAllBook(int offset, int rows)
	{
		return bookDao.listAllBook(offset, rows);
	}

	public List<Book> listBook(String key, int offset, int rows)
	{
		return bookDao.listBook(key, offset, rows);
	}
	
	 public List<Book> listBook(String key)
	 {
		 return bookDao.listBook(key);
	 }
	public boolean add(Book book)
	{
		return bookDao.add(book);
	}
	
	public boolean update(Book book)
	{
		return bookDao.update(book);
	}

	public boolean remove(Book book)
	{
		return bookDao.remove(book);
	}

	public Book getBook(String isbn)
	{
		return bookDao.getBook(isbn);
	}

	public int getTotalCount()
	{
		return bookDao.getTotalCount();
	}
	
	  public BookDao getBookDao(){
		  return bookDao;
	  }
	  public void setBookDao(BookDao bookDao){
		  this.bookDao = bookDao;
	  }
}
