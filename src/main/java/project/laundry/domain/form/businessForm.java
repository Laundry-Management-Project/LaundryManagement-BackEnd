package project.laundry.domain.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel(value = "사업장(세탁소) 등록 시 필요한 정보")
@Getter @Setter
public class businessForm {

    @ApiModelProperty(value = "사업장 이름", example = "XX 세탁소", required = true)
    private String name;

    @ApiModelProperty(value = "사업장 주소", example = "서울시 XX XX..", required = true)
    private String address;

    @ApiModelProperty(value = "사업장 영업 시간", example = "07:00 ~ 19:00", required = true)
    private String bu_hour;
}
