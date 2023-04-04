package project.laundry.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import project.laundry.data.entity.Visit;

import java.time.LocalDate;


public interface StatisticsRepository extends JpaRepository<Visit, Long> {

    // 한달 수입
    @Query("SELECT COALESCE(SUM(v.price), 0) FROM Visit v WHERE v.visitDateTime >= :startOfMonth AND v.visitDateTime <= :endOfMonth")
    Long getRevenueInMonth(@Param("startOfMonth") LocalDate startOfMonth, @Param("endOfMonth") LocalDate endOfMonth);

    // 일년 수입
    @Query("SELECT COALESCE(SUM(v.price), 0) FROM Visit v WHERE v.visitDateTime >= :startOfYear AND v.visitDateTime <= :endOfYear")
    Long getRevenueInYear(@Param("startOfYear") LocalDate startOfYear, @Param("endOfYear") LocalDate endOfYear);


    // 한달 방문자 수
    @Query("SELECT COUNT(v) FROM Visit v WHERE v.visitDateTime >= :startOfMonth AND v.visitDateTime <= :endOfMonth")
    Long getVisitsInMonth(@Param("startOfMonth") LocalDate startOfMonth, @Param("endOfMonth") LocalDate endOfMonth);


    // 일년 방문자 수
    @Query("SELECT COUNT(v) FROM Visit v WHERE v.visitDateTime >= :startOfYear AND v.visitDateTime <= :endOfYear")
    Long getVisitsInYear(@Param("startOfYear") LocalDate startOfYear, @Param("endOfYear") LocalDate endOfYear);

    // 당월 수입
    @Query("SELECT COALESCE(SUM(v.price), 0) FROM Visit v WHERE MONTH(v.visitDateTime) = :month")
    int getTotalRevenueByMonth(@Param("month") int month);


}
