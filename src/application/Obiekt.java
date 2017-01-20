package application;
import javafx.beans.property.*;

public class Obiekt {
	
	private StringProperty adult = new SimpleStringProperty(this,"adult","");
	
	public StringProperty adultProperty(){
		return adult;
	}

	public String getAdult() {
		return adult.get();
	}

	public void setAdult(String adult) {
		this.adult.set(adult);
	}
	
}
