package project.laundry.dto.StatisticsDto;

import lombok.Builder;
import lombok.Data;
import project.laundry.dto.post_dto.postDto;

import java.util.List;

@Data
@Builder
public class statisticsDto {

    private Long visitsInMonth;

    private Long revenueInMonth;

    private Long visitsInYear;

    private Long revenueInYear;

    private List<postDto> top5Posts;
}
