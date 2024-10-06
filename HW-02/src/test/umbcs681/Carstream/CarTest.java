package umbcs681.Carstream;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.stream.Stream;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class CarTest{

    private static ArrayList<Car> cars = new ArrayList<Car>();

    @BeforeAll
    public static void setUpCar(){
        cars.add(new Car("Supra","Toyota", 2019, 65000));
        cars.add(new Car("GTR", "Nissan", 2017, 45500));
        cars.add(new Car( "Q8", "Audi", 2023, 45000));
        cars.add(new Car( "CX5", "Mazda", 2020, 47000));
        cars.add(new Car( "Y", "Tesla", 2019, 48000));
    }

    @Test
    //Test Case: Apply .reduce() to car list
    public void verify_reduce(){
        Integer lowestPrice = cars.stream().map((Car car)->(int)car.getPrice()).reduce(0, (result, carPrice)->{ if(result==0) return carPrice; else if(carPrice < result) return carPrice; else return result;} );
        assertEquals(45000, lowestPrice);
        Integer highestPrice = cars.stream().map((Car car)->(int)car.getPrice()).reduce(100000, (result, carPrice)->{ if(result==100000) return carPrice; else if(carPrice > result) return carPrice; else return result;} );
        assertEquals(65000, highestPrice);
        /*Float lowestPrice = cars.stream().map((Car car)->car.getPrice()).reduce(0.0f, (result, carPrice)->{ if(result==0) return carPrice; else if(carPrice < result) return carPrice; else return result;} );
        assertEquals(45000, lowestPrice);
        Float highestPrice = cars.stream().map((Car car)->car.getPrice()).reduce(100000.0f, (result, carPrice)->{ if(result==100000) return carPrice; else if(carPrice > result) return carPrice; else return result;} );
        assertEquals(65000, highestPrice);*/
    }

    @Test
    //Test Case: verify average price to car list
    public void verify_avg(){
        Float averagePrice = cars.stream().map((Car car)->car.getPrice()).reduce(new ArrayList<>(Arrays.asList(0.0f, 0.0f)), (result, price)->{
            float avg = ((result.get(1) * result.get(0))+price)/ (result.get(0)+1);
            result.set(0, result.get(0)+1);
            result.set(1, avg);
            return result;},
                (finalResult, intermediateResult)->finalResult).get(1);
        assertEquals(50100, averagePrice);
    }

}