package project.laundry.config.auth.UserDetail;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import project.laundry.data.entity.Owner;

import java.util.Collection;
import java.util.stream.Collectors;

public class OwnerPrincipalDetails implements UserDetails {

    private final Owner owner;

    public OwnerPrincipalDetails(Owner owner) {
        this.owner = owner;
    }
    // 해당 User의 권한을 리턴하는 곳
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return owner.getRoles().stream().map(o -> new SimpleGrantedAuthority(
                o.getRole()
        )).collect(Collectors.toList());
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

