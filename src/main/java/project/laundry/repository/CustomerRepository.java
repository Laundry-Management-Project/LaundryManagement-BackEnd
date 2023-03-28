package project.laundry.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import project.laundry.entity.customer;

public interface CustomerRepository extends JpaRepository<customer, String> {

    @Query("SELECT c FROM customer c WHERE c.customer_id = :customer_id AND c.password = :password")
    customer findCustomerByCustomer_idAndPassword(@Param("customer_id") String customer_id, @Param("password") String password);
}
