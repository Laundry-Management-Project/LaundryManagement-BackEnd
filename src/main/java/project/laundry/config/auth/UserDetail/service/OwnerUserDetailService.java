package project.laundry.config.auth.UserDetail.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import project.laundry.config.auth.UserDetail.OwnerPrincipalDetails;
import project.laundry.data.entity.Owner;
import project.laundry.repository.OwnerRepository;

@Service
public class OwnerUserDetailService implements UserDetailsService {
    private final OwnerRepository ownerRepository;

    public OwnerUserDetailService(OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        Owner owner = ownerRepository.findByOwner_id(id);

        if(owner != null) {
            return new OwnerPrincipalDetails(owner);
        }

        throw new UsernameNotFoundException("User '" + id + "' not found.");
    }
}
