package project.laundry.data.response.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel(value = "캘린더에 표시할 DTO")
@Getter @Setter
public class dateDto {

    @ApiModelProperty(value = "날짜(일, 월) / Integer로 표현", example = "24(일) / 5(월)", required = true)
    private Integer date;
    @ApiModelProperty(value = "가격 / Integer로 표현", example = "30000", required = true)
    private Integer price;
}