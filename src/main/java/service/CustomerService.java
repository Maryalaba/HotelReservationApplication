package service;

import model.Customer;

import java.util.Collection;

public class CustomerService {
   private static CustomerService SINGLE_INSTANCE = new CustomerService();

    private CustomerService() {
    }

    public static CustomerService getSingleInstance() {
        return SINGLE_INSTANCE;
    }

    public void addCustomer(String email, String firstName, String lastName){

    }

    public Customer getCustomer(String customerEmail){

    }

    public Collection<Customer> getAllCustomers() {
        return null;
    }
}
