package project.laundry.data.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel(value = "사장님 예약 수정 폼")
@Getter @Setter
public class OwnerReservationForm {

    @ApiModelProperty(value = "가격", example = "10000", required = true)
    private Integer price;

    @ApiModelProperty(value = "세탁 상태(무조건 예시에 있는 3개의 값 중 하나로 보내야함)", example = "WASH_BEFORE | WASH_IN_PROCESS | WASH_COMPLETE", required = true)
    private String cloth_status;
}
