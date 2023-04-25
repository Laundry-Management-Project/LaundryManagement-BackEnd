package project.laundry.config.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import project.laundry.config.auth.PrincipalDetails.CustomerPrincipalDetails;
import project.laundry.data.entity.Customer;
import project.laundry.repository.CustomerRepository;

@Service
@RequiredArgsConstructor
public class CustomerPrincipalDetailService implements UserDetailsService {


    private final CustomerRepository customerRepository;

    @Override
    public UserDetails loadUserByUsername(String customer_id) throws UsernameNotFoundException {
        Customer customer = customerRepository.findByCustomer_id(customer_id);

        if(customer != null) {
            return new CustomerPrincipalDetails(customer);
        }
        return null;
    }
}
