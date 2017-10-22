package bookstore3.Entity;


public class Item {
	private Book book;
	private int quantity;
    
	public void setBook(Book book) {
		this.book = book;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Book getBook() {
		return book;
	}

	public int getQuantity() {
        return quantity;
	}
	
	public double getAmount()
	{
		return book.getPrice()*quantity;
	}
}