package project.laundry.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import project.laundry.domain.entity.Business;
import project.laundry.domain.entity.reservation;

import java.util.List;

public interface ReservationRepository extends JpaRepository<reservation, String> {

    @Query("SELECT r.business FROM reservation r JOIN r.business WHERE r.customer.id = :customer_id")
    List<Business> findByCustomerUid(@Param("customer_id") String customer_id);
}
