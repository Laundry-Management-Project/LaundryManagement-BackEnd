package project.laundry.data.dto.common.statistics;

import lombok.Builder;
import lombok.Data;
import project.laundry.data.dto.common.postDto;

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
