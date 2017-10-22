package bookstore3.Entity;

public class Statistic {
private String keyword;
private int quantity;
private double amount;

public void setKeyword(String keyword)
{
	this.keyword = keyword;
}

public void setQuantity(int quantity)
{
	this.quantity = quantity;
}

public void setAmount(double amount)
{
	this.amount = amount;
}

public String getKeyword()
{
	return keyword;
}

public int getQuantity()
{
	return quantity;
}

public double getAmount()
{
	return amount;
}
}
