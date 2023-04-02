package project.laundry.controller.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.laundry.domain.dto.statistics.revenueGraphDto;
import project.laundry.domain.dto.statistics.statisticsDto;
import project.laundry.domain.dto.postDto;
import project.laundry.service.PostService;
import project.laundry.service.StatisticsService;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;
@ApiIgnore
@RestController
@RequestMapping("/")
@RequiredArgsConstructor
@Slf4j
public class MainController {

    private final StatisticsService service;
    private final PostService postService;

    @GetMapping
    public ResponseEntity<statisticsDto> mainPage() {

        Long visitsInMonth = service.getVisitsInMonth();
        Long revenueInMonth = service.getRevenueInMonth();
        Long visitsInYear = service.getVisitsInYear();
        Long revenueInYear = service.getRevenueInYear();

        List<postDto> top5Posts = postService.findTop5ByOrderByIdDesc();

        statisticsDto dto = statisticsDto.builder()
                .visitsInMonth(visitsInMonth)
                .revenueInMonth(revenueInMonth)
                .visitsInYear(visitsInYear)
                .revenueInYear(revenueInYear)
                .top5Posts(top5Posts)
                .build();

        return ResponseEntity.ok(dto);
    }


    @GetMapping("/revenue-detail")
    public ResponseEntity<revenueGraphDto> revenue_graph(Model model) {

        List<Integer> list = service.getTotalRevenueByMonth();
        List<Integer> progress = service.getLaundryProgress();

        revenueGraphDto dto = revenueGraphDto.builder()
                .list(list)
                .progress(progress)
                .build();

        return ResponseEntity.ok(dto);
    }

}