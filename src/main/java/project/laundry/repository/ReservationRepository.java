package project.laundry.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import project.laundry.data.entity.Reservation;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, String> {

    @Query("SELECT r FROM Reservation r JOIN FETCH r.business WHERE r.business.uid = :bu_id")
    List<Reservation> findReservationsByBusiness_uid(@Param("bu_id") String bu_id);

    @Query("SELECT r FROM Reservation r JOIN FETCH r.customer WHERE r.customer.uid = :customer_id")
    List<Reservation> findReservationsByCustomer_uid(@Param("customer_id") String customer_id);

    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query("DELETE FROM Reservation r WHERE r in :reservation")
    void deleteReservations(@Param("reservation") Iterable<Reservation> reservation);

    @Query("SELECT DAY(r.createTime) as day, SUM(r.price) as price FROM Reservation r WHERE YEAR(r.createTime) = :year AND MONTH(r.createTime) = :month AND r.id = :buId GROUP BY DAY(r.createTime) ORDER BY DAY(r.createTime) ASC")
    List<Object[]> findByDailyPrice(@Param("buId") String buId, @Param("year") Integer year, @Param("month") Integer month);
    @Query("SELECT MONTH(r.createTime) as month, SUM(r.price) as price FROM Reservation r WHERE YEAR(r.createTime) = :year AND r.id = :buId GROUP BY MONTH(r.createTime) ORDER BY MONTH(r.createTime) ASC")
    List<Object[]> findByMonthlyPrice(@Param("buId") String buId, @Param("year") Integer year);

}
