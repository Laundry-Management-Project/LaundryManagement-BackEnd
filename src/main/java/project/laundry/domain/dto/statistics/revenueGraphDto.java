package project.laundry.domain.dto.statistics;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class revenueGraphDto {

    List<Integer> list;

    List<Integer> progress;
}