package homework;

import java.util.Deque;
import java.util.LinkedList;

public class CustomerReverseOrder {

    Deque<Customer> queue = new LinkedList<>();

    public void add(Customer customer) {
        queue.add(customer);
    }

    public Customer take() {
        return queue.pollLast();
    }
}
