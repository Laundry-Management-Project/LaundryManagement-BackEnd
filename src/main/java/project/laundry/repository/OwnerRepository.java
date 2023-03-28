package project.laundry.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.laundry.entity.owner;

public interface OwnerRepository extends JpaRepository<owner, String> {
}
