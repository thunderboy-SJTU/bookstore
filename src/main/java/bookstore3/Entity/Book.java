package bookstore3.Entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "books")
public class Book {
	private String isbn;
	private String title;
	private String author;
	private Category category;
	private double price;
	private String description;

	public void setIsbn(String value) {
		isbn = value;
	}

	public void setTitle(String value) {
		title = value;
	}

	public void setAuthor(String value) {
		author = value;
	}
    
	public void setCategory(Category value) {
		category = value;
	}

	public void setPrice(double value) {
		price = value;
	}
    
	public void setDescription(String value)
	{
		description = value;
	}
	
	@Id
	public String getIsbn() {
		return isbn;
	}

	public String getTitle() {
		return title;
	}

	public String getAuthor() {
		return author;
	}
    
	@OneToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "catid")
	public Category getCategory() {
		return category;
	}
    

	public double getPrice() {
		return price;
	}
	
	public String getDescription()
	{
		return description;
	}
}
