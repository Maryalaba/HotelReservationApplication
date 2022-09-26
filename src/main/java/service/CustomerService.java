package service;

import model.Customer;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CustomerService {
    Map<String, Customer> customerList = new HashMap<>();
   private static CustomerService SINGLE_INSTANCE = new CustomerService();

    private CustomerService() {
    }

    public static CustomerService getSingleInstance() {
        return SINGLE_INSTANCE;
    }

    public void addCustomer(String firstName, String lastName, String email) {
        if (emailValidation(email)){
            if (!customerList.containsKey(email)){
                Customer customer = new Customer(firstName , lastName , email);
                customerList.put(email, customer);
                System.out.println("Successfully Created An Account for " + firstName + " " + lastName);
            }else {
                System.out.println("User Already Exists");
            }
        }else {
            System.out.println("Incorrect Email Address");
        }
    }

    public Customer getCustomer(String customerEmail){
        return customerList.get(customerEmail);
    }

    public Collection<Customer> getAllCustomers() {
        List<Customer> customers = new ArrayList<>();
        for (Map.Entry<String , Customer> customer : customerList.entrySet()){
            customers.add(customer.getValue());
        }
        return customers;
    }

    public boolean emailValidation(String email){
        Pattern pattern = Pattern.compile("^(.+)@(\\S+)$" , Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.find();
    }
}
