package project.laundry.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import project.laundry.data.entity.Business;

import java.util.Optional;

public interface BusinessRepository extends JpaRepository<Business, String> {

    @Query("SELECT b FROM Business b JOIN b.owner WHERE b.owner.uid = :owner_id")
    Page<Business> findBusinessesByOwner_id(@Param("owner_id") String owner_id, Pageable pageable);

    @Query("SELECT b FROM Business b WHERE b.uid = :bu_id")
    Optional<Business> findBusinessByBusiness_id(@Param("bu_id") String bu_id);


}
