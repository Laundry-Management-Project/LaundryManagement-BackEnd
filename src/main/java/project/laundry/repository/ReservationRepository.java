package project.laundry.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import project.laundry.data.entity.Reservation;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, String> {

    @Query("SELECT r FROM Reservation r JOIN FETCH r.business WHERE r.business.uid = :bu_id")
    List<Reservation> findReservationsByBusiness_id(@Param("bu_id") String bu_id);

}
