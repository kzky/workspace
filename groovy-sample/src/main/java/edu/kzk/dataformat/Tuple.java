package edu.kzk.dataformat;

public class Tuple {
	long id;
	float rating;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public float getRating() {
		return rating;
	}
	public void setRating(float rating) {
		this.rating = rating;
	}
	
	public String toString() {

		return String.valueOf(id) + "," + String.valueOf(rating); 
	}
	
}
