package project.laundry.config.auth.UserDetail.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import project.laundry.config.auth.UserDetail.CustomerPrincipalDetails;
import project.laundry.data.entity.Customer;
import project.laundry.repository.CustomerRepository;

@Service
public class CustomerUserDetailService implements UserDetailsService {

    private final CustomerRepository customerRepository;

    public CustomerUserDetailService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        Customer customer = customerRepository.findByCustomer_id(id);

        if(customer != null) {
            return new CustomerPrincipalDetails(customer);
        }

        throw new UsernameNotFoundException("User '" + id + "' not found.");
    }
}
