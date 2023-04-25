package project.laundry.config.auth.PrincipalDetails;

// 시큐리티가 /login 주소 요청이 오면 낚아채서 로그인을 진행시킨다.
// 로그인 진행이 완료가 되면 시큐리티 session을 만들어 준다.(Security Holder)
// 오브젝트 => Authentication 타입 객체
// Authenication 안에 User정보가 있어야 됨
// User 오브젝트 타입 => UserDetails 타입 객체
// Security Session => Authentication => UserDetails

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import project.laundry.data.entity.Customer;
import project.laundry.data.entity.Owner;

import java.util.ArrayList;
import java.util.Collection;

public class CustomerPrincipalDetails implements UserDetails {

    private final Customer customer;

    public CustomerPrincipalDetails(Customer customer) {
        this.customer = customer;
    }

    // 해당 User의 권한을 리턴하는 곳
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collect = new ArrayList<>();
        collect.add((GrantedAuthority) () -> customer.getRole());

        return collect;
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
