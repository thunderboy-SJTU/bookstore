package bookstore3.Action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.opensymphony.xwork2.ActionSupport;

import bookstore3.Entity.Book;
import bookstore3.Entity.Cart;
import bookstore3.Entity.Item;
import bookstore3.Service.BookService;

public class Save extends ActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String execute() throws Exception {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		BookService bookService = (BookService) ctx.getBean("bookService");
		HttpServletRequest request = ServletActionContext.getRequest();
		Cart cart = (Cart) request.getSession().getAttribute("cart");
		List<Book> list = new ArrayList<Book>();
		for (Item item : cart.getItems()) {
			String isbn = item.getBook().getIsbn();
			String str = request.getParameter(isbn);
			if (!isInteger(str))
				continue;
			int count = Integer.parseInt(str);
			Book book = bookService.getBook(isbn);
			if (count <= 0)
				list.add(book);
			else
				cart.changeQuantity(book, count);
		}
		cart.removeAll(list);
		request.getSession().setAttribute("cart", cart);
		return SUCCESS;
	}

	public static boolean isInteger(String input) {
		Matcher mer = Pattern.compile("^[+-]?[0-9]+$").matcher(input);
		return mer.find();
	}
}
