import java.util.*;

interface CustomerRepository {
    String findCustomerById(String customerId);
}


class CustomerRepositoryImpl implements CustomerRepository {
    public String findCustomerById(String customerId) {
        return "Customer[id=" + customerId + ", name=John Doe]";
    }
}


class CustomerService {
    private CustomerRepository repository;

    public CustomerService(CustomerRepository repository) {
        this.repository = repository;
    }

    public void getCustomerDetails(String customerId) {
        String customer = repository.findCustomerById(customerId);
        System.out.println("Customer Details: " + customer);
    }
}


public class DependencyInjectionExample {
    public static void main(String[] args) {
        CustomerRepository repo = new CustomerRepositoryImpl();
        CustomerService service = new CustomerService(repo);

        service.getCustomerDetails("C101");
    }
}
