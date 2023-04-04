package project.laundry.service.web;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.laundry.data.entity.status.ClothStatus;
import project.laundry.repository.PostRepository;
import project.laundry.repository.StatisticsRepository;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StatisticsService {

    private final StatisticsRepository repository;
    private final PostRepository postRepository;

    LocalDate currentDate = LocalDate.now();

    /**
     * 당월/당년 방문자 수 통계 메소드
     */
    public Long getVisitsInMonth() {
        LocalDate startOfMonth = currentDate.withDayOfMonth(1);
        LocalDate endOfMonth = currentDate.with(TemporalAdjusters.lastDayOfMonth());

        return repository.getVisitsInMonth(startOfMonth, endOfMonth);
    }
    public Long getVisitsInYear() {
        LocalDate startOfYear = currentDate.withDayOfYear(1);
        LocalDate endOfYear = currentDate.withDayOfYear(currentDate.lengthOfYear());

        return repository.getVisitsInYear(startOfYear, endOfYear);
    }

    /**
     * 당월/당년 수입 통계 메소드
     */

    public Long getRevenueInMonth() {
        LocalDate startOfMonth = currentDate.withDayOfMonth(1);
        LocalDate endOfMonth = currentDate.with(TemporalAdjusters.lastDayOfMonth());

        return repository.getRevenueInMonth(startOfMonth, endOfMonth);
    }
    public Long getRevenueInYear() {
        LocalDate startOfYear = currentDate.withDayOfYear(1);
        LocalDate endOfYear = currentDate.withDayOfYear(currentDate.lengthOfYear());

        return repository.getRevenueInYear(startOfYear, endOfYear);
    }

    public List<Integer> getTotalRevenueByMonth() {
        int[] month = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
        List<Integer> revenueList = new ArrayList<>();
        for(int i : month) {
            revenueList.add(repository.getTotalRevenueByMonth(i));

        }

        return revenueList;
    }

    public List<Integer> getLaundryProgress() {
        int WASH_BEFORE = postRepository.countClothesStatus(ClothStatus.WASH_BEFORE);
        int WASH_IN_PROCESS = postRepository.countClothesStatus(ClothStatus.WASH_IN_PROCESS);
        int WASH_COMPLETE = postRepository.countClothesStatus(ClothStatus.WASH_COMPLETE);

        Stream<Integer> stream = Stream.of(WASH_BEFORE, WASH_IN_PROCESS, WASH_COMPLETE);

        return stream.collect(Collectors.toList());
    }

}
