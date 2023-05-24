package project.laundry.data.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import project.laundry.data.response.common.dateDto;

import java.util.ArrayList;
import java.util.List;

@ApiModel(value = "캘린더 수입 반환 DTO")
@Getter @Setter
public class incomeDtoList {

    @ApiModelProperty(value = "일간 수입 목록", required = true)
    private List<dateDto> daily_income = new ArrayList<>();
    @ApiModelProperty(value = "월간 수입 목록", required = true)
    private List<dateDto> monthly_income = new ArrayList<>();

    // 당월 수입, 방문자 수
    @ApiModelProperty(value = "당월 수입", example = "4500000", required = true)
    private Integer current_month_income;
    @ApiModelProperty(value = "당월 방문자 수", example = "300", required = true)
    private Integer current_month_visitor;

    // 당년 수입, 방문자 수
    @ApiModelProperty(value = "당년 수입", example = "50000000", required = true)
    private Integer current_year_income;
    @ApiModelProperty(value = "당년 방문자 수", example = "3000", required = true)
    private Integer current_year_visitor;

}
