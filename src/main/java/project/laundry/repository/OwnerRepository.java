package project.laundry.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import project.laundry.data.entity.Owner;

public interface OwnerRepository extends JpaRepository<Owner, String> {

    @Query("SELECT o FROM Owner o LEFT JOIN FETCH o.businesses WHERE o.owner_id = :owner_id AND o.password = :password")
    Owner findByOwner_idAndPassword(@Param("owner_id") String owner_id, @Param("password") String password);
    @Query("SELECT o FROM Owner o WHERE o.owner_id = :owner_id")
    Owner findByOwner_id(@Param("owner_id") String owner_id);



}
