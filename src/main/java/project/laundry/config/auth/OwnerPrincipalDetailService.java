package project.laundry.config.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import project.laundry.config.auth.PrincipalDetails.OwnerPrincipalDetails;
import project.laundry.data.entity.Owner;
import project.laundry.repository.OwnerRepository;

@Service
@RequiredArgsConstructor
public class OwnerPrincipalDetailService implements UserDetailsService {

    private final OwnerRepository repository;

    @Override
    public UserDetails loadUserByUsername(String owner_id) throws UsernameNotFoundException {
        Owner owner = repository.findByOwner_id(owner_id);

        if(owner != null) {
            return new OwnerPrincipalDetails(owner);
        }
        return null;
    }
}
