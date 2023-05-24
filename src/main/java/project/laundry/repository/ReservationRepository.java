package project.laundry.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import project.laundry.data.entity.Reservation;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, String> {


    List<Reservation> findReservationsByBusinessUid(@Param("bu_id") String bu_id);
    Page<Reservation> findReservationsByBusinessUid(@Param("bu_id") String bu_id, Pageable pageable);


    @Query(value = "SELECT r FROM Reservation r JOIN r.customer WHERE r.customer.uid = :customer_id", countQuery = "SELECT COUNT(r) FROM Reservation r JOIN r.customer WHERE r.customer.uid = :customer_id")
    Page<Reservation> findReservationsByCustomer_uid(@Param("customer_id") String customer_id, Pageable pageable);

    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query("DELETE FROM Reservation r WHERE r in :reservation")
    void deleteReservationsWithInQuery(@Param("reservation") Iterable<Reservation> reservation);

    @Query("SELECT DAY(r.createTime) as day, SUM(r.price) as price FROM Reservation r" +
            " WHERE YEAR(r.createTime) = :year AND MONTH(r.createTime) = :month AND r.business.uid = :buId GROUP BY DAY(r.createTime) ORDER BY DAY(r.createTime) ASC")
    List<Object[]> findByDailyIncome(@Param("buId") String buId, @Param("year") Integer year, @Param("month") Integer month);
    @Query("SELECT MONTH(r.createTime) as month, SUM(r.price) as price FROM Reservation r" +
            " WHERE YEAR(r.createTime) = :year AND r.business.uid = :buId GROUP BY MONTH(r.createTime) ORDER BY MONTH(r.createTime) ASC")
    List<Object[]> findByMonthlyIncome(@Param("buId") String buId, @Param("year") Integer year);

    @Query("SELECT sum(r.price) FROM Reservation r" +
            " WHERE YEAR(r.createTime) = :year AND MONTH(r.createTime) = :month AND r.business.uid = :buId")
    Integer findByMonthIncome(@Param("buId") String buId, @Param("year") Integer year, @Param("month") Integer month);

    @Query("SELECT COUNT(r) FROM Reservation r" +
            " WHERE YEAR(r.createTime) = :year AND MONTH(r.createTime) = :month AND r.business.uid = :buId")
    Integer findByMonthlyVisitCount(@Param("buId") String buId, @Param("year") Integer year, @Param("month") Integer month);

    @Query("SELECT sum(r.price) FROM Reservation r" +
            " WHERE YEAR(r.createTime) = :year AND r.business.uid = :buId")
    Integer findByYearIncome(@Param("buId") String buId, @Param("year") Integer year);

    @Query("SELECT COUNT(r) FROM Reservation r WHERE r.business.uid = :buId AND YEAR(r.createTime) = :year")
    Integer findByYearlyVisitCount(@Param("buId") String buId, @Param("year") Integer year);
}
