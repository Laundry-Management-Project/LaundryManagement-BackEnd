package project.laundry.data.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel(value = "손님 예약 폼")
@Getter @Setter
public class CustomerReservationForm {

   @ApiModelProperty(value = "추가 내용", example = "흰색 반팔티랑 검은색 패딩은 따로 세탁 해주세요.")
   private String request_detail;

   @ApiModelProperty(value = "세탁물 종류", example = "흰색 반팔티, 검정색 패", required = true)
   private String clothing_type;


}
