package plc1;

import java.util.*;

public class DVD extends Article{

    private int length;
	private int age_rating;

	public DVD(int id, String title, int release_year, String publisher, double base_price, int length, int age_rating) {
		super(id, title, release_year, publisher, base_price);
		
		if(length < 0) throw new IllegalArgumentException ("Error: Invalid parameter.");
		
		List<Integer> help = new ArrayList<>();
		help.addAll(Arrays.asList(0,6,12,16,18));
		
		if(!help.contains(age_rating)) throw new IllegalArgumentException ("Error: Invalid age rating.");
		
		this.length = length;
		this.age_rating = age_rating;
	}

	@Override
	public int getDiscount() {
		int discount = 0;
		if (age_rating == 0) discount = 20;
		else if (age_rating == 6) discount = 15;
		else if (age_rating == 12) discount = 10;
		else if (age_rating == 16) discount = 5;
		
		return discount;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public int getAge_rating() {
		return age_rating;
	}

	public void setAge_rating(int age_rating) {
		this.age_rating = age_rating;
	}
	
	public String toString() {
		return "Type:       DVD" + "\n" +super.toString() +  "\n" + "Length:     " +  this.getLength()+
				"\n" + "Age rating: " + this.getAge_rating() + "\n";
	}
}
