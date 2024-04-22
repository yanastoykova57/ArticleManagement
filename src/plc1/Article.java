package plc1;

/**
 * @author Yana Stoykova
 * @id 11916925
 */
import java.io.Serializable;
import java.text.DecimalFormat;
public abstract class Article implements Serializable {
    
	private int id; 
	private int release_year;
	private double base_price;
	private String title;
	private String publisher;
	
	public Article (final int id, final String title, final int release_year, final String publisher, final double base_price) {
		
		if(release_year > 2023 || release_year < 0) throw new IllegalArgumentException("Error: Invalid release year.");
		if(title.isEmpty() || publisher.isEmpty()) throw new IllegalArgumentException("Error: Invalid parameter."); 
		
		this.id = id;
		this.title = title;
		this.release_year = release_year;
		this.publisher = publisher;
		this.base_price = base_price;
	}
	
	public int getAge() {
		return 2023 - release_year;
	}
	
	public abstract int getDiscount();
	
	public Double getPrice() {
		return this.base_price*(1 - getDiscount()/100.0);
	}
	
	
	public int getId() {
		return id;
	}

	public int getRelease_year() {
		return release_year;
	}

	public double getBase_price() {
		return base_price;
	}


	public String getTitle() {
		return title;
	}


	public String getPublisher() {
		return publisher;
	}

	
	public static DecimalFormat getDecimalFormat() {
		return new DecimalFormat("0.00");
	}
	
	@Override
	public String toString() {
		DecimalFormat df = Article.getDecimalFormat();
		
		return "Id:         " + this.getId() + "\n" + "Title:      " + this.getTitle() + "\n" + "Year:       " + this.getRelease_year() + "\n" +
				"Publisher:  " + this.getPublisher() + "\n" + "Base price: " + df.format(this.getBase_price()) + "\n" +
				"Price:      " + df.format(this.getPrice());
	}
	

	}


