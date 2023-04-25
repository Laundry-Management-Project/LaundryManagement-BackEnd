package project.laundry.config.auth.PrincipalDetails;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import project.laundry.data.entity.Customer;
import project.laundry.data.entity.Owner;

import java.util.ArrayList;
import java.util.Collection;

public class OwnerPrincipalDetails implements UserDetails {

    private final Owner owner;

    public OwnerPrincipalDetails(Owner owner) {
        this.owner = owner;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collect = new ArrayList<>();
        collect.add((GrantedAuthority) () -> owner.getRole());

        return collect;
    }

    @Override
    public String getPassword() {
        return owner.getPassword();
    }

    @Override
    public String getUsername() {
        return owner.getOwner_id();
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
