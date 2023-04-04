package project.laundry.data.dto.common.statistics;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class revenueGraphDto {

    List<Integer> list;

    List<Integer> progress;
}
