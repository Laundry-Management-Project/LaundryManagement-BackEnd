package project.laundry.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import project.laundry.domain.entity.Business;

import java.util.List;

public interface BusinessRepository extends JpaRepository<Business, String> {

    @Query("SELECT b FROM Business b JOIN FETCH b.owner WHERE b.owner.id = :owner_id")
    List<Business> findBusinessesByOwner_id(@Param("owner_id") String owner_id);
}
