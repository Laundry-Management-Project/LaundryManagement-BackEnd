package project.laundry.config.auth.UserDetail;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import project.laundry.data.entity.Customer;

import java.util.Collection;
import java.util.stream.Collectors;

public class CustomerPrincipalDetails implements UserDetails {

    private final Customer customer;

    public CustomerPrincipalDetails(Customer customer) {
        this.customer = customer;
    }

    // 해당 User의 권한을 리턴하는 곳
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return customer.getRoles().stream().map(o -> new SimpleGrantedAuthority(
                o.getName()
        )).collect(Collectors.toList());
    }


    @Override
    public String getPassword() {
        return customer.getPassword();
    }

    @Override
    public String getUsername() {
        return customer.getCustomer_id();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}