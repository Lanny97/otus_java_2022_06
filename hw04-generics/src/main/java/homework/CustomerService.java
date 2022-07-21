package homework;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

public class CustomerService {

    TreeMap<Customer, String> map = new TreeMap<>(Comparator.comparingLong(Customer::getScores));

    public Map.Entry<Customer, String> getSmallest() {
        Map.Entry<Customer, String> entry = map.firstEntry();
        if (entry == null) return null;
        Customer key = entry.getKey();
        return Map.entry(new Customer(key.getId(), key.getName(), key.getScores()), entry.getValue());
    }

    public Map.Entry<Customer, String> getNext(Customer customer) {
        TreeMap<Customer, String> newMap = new TreeMap<>(Comparator.comparingLong(Customer::getScores));
        newMap.putAll(map);
        map = newMap;
        return map.higherEntry(customer);
    }

    public void add(Customer customer, String data) {
        map.put(new Customer(customer.getId(), customer.getName(), customer.getScores()), data);
    }
}
