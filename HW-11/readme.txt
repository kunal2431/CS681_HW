HW-11: Taxi/Cab Booking Application (Uber)

This project demonstrates a thread-safe cab booking application(Similar to Uber/Lyft). The application allows users to book or cancel a cab, in a thread safe.

"drivers" linkedlist is list of available cabs/drivers for booking

1. <<Interface>> UberApp: Defines methods for booking and canceling cabs, as well as reading the list of available drivers.

2. BookCabRunnable and CancelCabRunnable: Runnable classes responsible for booking and canceling cabs/drivers respectively.

3. ThreadUnsafeUber: A non-thread-safe implementation of the cab booking application where the shared variable `drivers` is not thread safe.

4. ThreadSafeUber: A thread-safe implementation of the application that uses locks to ensure synchronized access to shared resources.

5. Driver: A class for drivers details.

6. ReadRunnable: A runnable class that retrieves and displays the list of available drivers or cabs.

7. The application was tested using JUnit.

The LinkedList "drivers" can cause race conditions if not implemented in a thread-safe manner. If multiple threads book a cab, they might book the same driver/cab in the thread-unsafe version. In the thread-safe version, a lock is acquired at the start of the bookCab() method, the driver is booked, and the lock is released, preventing other threads/users from booking the same driver/cab simultaneously.