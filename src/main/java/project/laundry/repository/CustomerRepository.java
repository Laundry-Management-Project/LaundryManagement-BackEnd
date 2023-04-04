package project.laundry.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import project.laundry.data.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, String> {

    @Query("SELECT c FROM Customer c WHERE c.customer_id = :customer_id AND c.password = :password")
    Customer findByCustomer_idAndPassword(@Param("customer_id") String customer_id, @Param("password") String password);

    @Query("SELECT c FROM Customer c WHERE c.customer_id = :customer_id")
    Customer findByCustomer_id(@Param("customer_id") String customer_id);
}
