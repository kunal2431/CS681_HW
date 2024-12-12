package umbcs681.uber;

import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.atomic.*;

public class Driver{
	private String name;
	private double driverID;

	public Driver(String name, double driverID) {
		this.name = name;
		this.driverID = driverID;
	}

	public String getName() {
		return this.name;
	}

	public double getDriverID(){return this.driverID;}

}
