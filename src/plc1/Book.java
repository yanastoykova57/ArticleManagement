package plc1;


public class Book extends Article {

    private int pages;
	
	public Book (int id, String title, int release_year, String publisher, double base_price, int pages) {
		super(id, title, release_year, publisher, base_price);
		
		if(pages < 0)  throw new IllegalArgumentException("Error: Invalid parameter.");
		this.pages = pages;
	}

	public int getPages() {
		return pages;
	}

	public void setPages(int pages) {
		this.pages = pages;
	}

	@Override
	public int getDiscount() {
		int discount = 0;
		
		discount = 5 * getAge();
		if (discount > 30) discount = 30;
		if(pages > 1000) discount += 3;
		
		return discount;
	}
	
@Override
public String toString() {
	return "Type:       Book" + "\n" +super.toString() +  "\n" + "Pages:      " +  this.getPages() + "\n";
}
}
