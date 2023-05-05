package project.laundry.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import project.laundry.data.entity.Owner;

import java.util.Optional;

public interface OwnerRepository extends JpaRepository<Owner, String> {

    @Query("SELECT o FROM Owner o WHERE o.owner_id = :owner_id")
    Owner findByOwner_id(@Param("owner_id") String owner_id);

}
