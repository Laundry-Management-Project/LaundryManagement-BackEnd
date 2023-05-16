package project.laundry.service.owner.Calender;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.laundry.data.response.common.dateDto;
import project.laundry.data.response.incomeDtoList;
import project.laundry.repository.ReservationRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class OwnerCalenderService {
    private final ReservationRepository reservationRepository;

    public ResponseEntity<incomeDtoList> responseIncomeDto(String buId, String year, String month, String day) {
        // buId로 해당 메장의 수입만 조회
        incomeDtoList incomeDtoList = new incomeDtoList();

        // 월별, 일별 수입 목록
        List<dateDto> daily_income = findDailyIncome(buId, year, month);
        List<dateDto> monthly_income = findMonthlyIncome(buId, year);
        incomeDtoList.setDaily_income(daily_income);
        incomeDtoList.setMonthly_income(monthly_income);


        // 당월 수입 합계, 방문자 수
        Integer month_income = findMonthIncome(buId, year, month);
        Integer monthly_visit_count = findMonthlyVisitCount(buId, year, month);
        incomeDtoList.setCurrent_month_income(month_income);
        incomeDtoList.setCurrent_month_visitor(monthly_visit_count);

        // 당년 수입 합계, 방문자 수
        Integer year_income = findByYearIncome(buId, year);
        Integer yearly_visit_count = findByYearlyVisitCount(buId, year);
        incomeDtoList.setCurrent_year_income(year_income);
        incomeDtoList.setCurrent_year_visitor(yearly_visit_count);


        return ResponseEntity.ok(incomeDtoList);
    }

    private List<dateDto> findDailyIncome(String buId, String year, String month) {
        List<dateDto> dateDtoList = new ArrayList<>();
        Integer parseYear = Integer.parseInt(year);
        Integer parseMonth = Integer.parseInt(month);

        List<Object[]> dailyPrice = reservationRepository.findByDailyIncome(buId, parseYear, parseMonth);

        for (Object[] day : dailyPrice) {
            dateDto dto = new dateDto();
            dto.setDate((Integer) day[0]);
            dto.setPrice((Integer) day[1]);
            dateDtoList.add(dto);
        }

        return dateDtoList;
    }

    private List<dateDto> findMonthlyIncome(String buId, String year) {
        List<dateDto> dateDtoList = new ArrayList<>();
        Integer parseYear = Integer.parseInt(year);

        List<Object[]> monthlyPrice = reservationRepository.findByMonthlyIncome(buId, parseYear);

        for (Object[] month : monthlyPrice) {
            dateDto dto = new dateDto();
            dto.setDate((Integer) month[0]);
            dto.setPrice((Integer) month[1]);
            dateDtoList.add(dto);
        }
        
        return dateDtoList;
    }
    
    
    private Integer findMonthIncome(String buId, String year, String month) {
        Integer monthIncome = reservationRepository.findByMonthIncome(buId, Integer.parseInt(year), Integer.parseInt(month));

        if(monthIncome == null) {
            monthIncome = 0;
        }

        return monthIncome;
    }

    private Integer findMonthlyVisitCount(String buId, String year, String month) {
        return reservationRepository.findByMonthlyVisitCount(buId, Integer.parseInt(year), Integer.parseInt(month));
    }


    private Integer findByYearIncome(String buId, String year) {
        Integer yearIncome = reservationRepository.findByYearIncome(buId, Integer.parseInt(year));

        if(yearIncome == null) {
            yearIncome = 0;
        }
        return yearIncome;
    }
    

    private Integer findByYearlyVisitCount(String buId, String year) {
        return reservationRepository.findByYearlyVisitCount(buId, Integer.parseInt(year));
    }



}
