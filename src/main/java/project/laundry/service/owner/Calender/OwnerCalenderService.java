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
        List<dateDto> daily_income = findDailyIncome(buId, year, month);
        List<dateDto> monthly_income = findMonthlyIncome(buId, year);

        incomeDtoList.setDaily_income(daily_income);
        incomeDtoList.setMonthly_income(monthly_income);


        return ResponseEntity.ok(incomeDtoList);
    }

    private List<dateDto> findDailyIncome(String buId, String year, String month) {
        List<dateDto> dateDtoList = new ArrayList<>();
        Integer parseYear = Integer.parseInt(year);
        Integer parseMonth = Integer.parseInt(month);

        List<Object[]> dailyPrice = reservationRepository.findByDailyPrice(buId, parseYear, parseMonth);

        for (Object[] day : dailyPrice) {
            dateDto dto = new dateDto();
            dto.setDate((Integer) day[0]);
            dto.setPrice((Long) day[1]);
            dateDtoList.add(dto);
        }

        return dateDtoList;
    }

    private List<dateDto> findMonthlyIncome(String buId, String year) {
        List<dateDto> dateDtoList = new ArrayList<>();
        Integer parseYear = Integer.parseInt(year);

        List<Object[]> monthlyPrice = reservationRepository.findByMonthlyPrice(buId, parseYear);

        for (Object[] month : monthlyPrice) {
            dateDto dto = new dateDto();
            dto.setDate((Integer) month[0]);
            dto.setPrice((Long) month[1]);
            dateDtoList.add(dto);
        }

        return dateDtoList;

    }
    
    
    



}
