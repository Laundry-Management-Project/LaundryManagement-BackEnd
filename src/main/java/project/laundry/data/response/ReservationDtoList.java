package project.laundry.data.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import project.laundry.data.response.common.ReservationDto;

import java.util.List;

@ApiModel(value = "예약 목록 반환 DTO")
@Getter @Setter
@Builder
public class ReservationDtoList {
    @ApiModelProperty(value = "예약 목록", required = true)
    private List<ReservationDto> reservations;

    @ApiModelProperty(value = "전채 페이지", example = "2", required = true)
    Integer totalPages;

    @ApiModelProperty(value = "해당 리스트의 전채 개수", example = "11", required = true)
    Long totalItems;
}
