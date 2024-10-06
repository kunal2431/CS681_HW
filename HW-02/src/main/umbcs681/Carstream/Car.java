package umbcs681.Carstream;

public class Car {
	private String make = null;
	private String model = null;
	private int mileage = -1;
	private  int year = -1;
	private float price = -1.0f;

	public Car(String make, String model, int year, float price){
		this.make=make;
		this.model=model;
		this.year=year;
		this.price = price;
	}

//	Use below constructor if using static factory method
//	private Car(String make, String model, int mileage, int year, float price){
//		this.make=make;
//		this.model=model;
//		this.mileage=mileage;
//		this.year=year;
//		this.price=price;
//	}

//  Below is the Static Factory method
//	public static Car createCar(String make, String model, int year){
//		return new Car(make, model, -1, year, -1.0f);
//	}

	public String getMake(){ return this.make; }

	public String getModel(){ return this.model; }

	public int getMileage(){ return this.mileage; }

	public int getYear(){ return this.year; }

	public float getPrice(){ return this.price; }


}
