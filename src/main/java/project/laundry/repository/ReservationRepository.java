package project.laundry.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import project.laundry.data.entity.Business;
import project.laundry.data.entity.Customer;
import project.laundry.data.entity.reservation;

import java.util.List;

public interface ReservationRepository extends JpaRepository<reservation, String> {

    @Query("SELECT r, r.business FROM reservation r JOIN r.business WHERE r.customer.id = :customer_id")
    List<Business> findByCustomerUid(@Param("customer_id") String customer_id);

    @Query("SELECT r.customer FROM reservation r WHERE r.business.uid = :bu_id")
    List<Customer> findCustomerListByBusiness_id(@Param("bu_id") String bu_id);

}
