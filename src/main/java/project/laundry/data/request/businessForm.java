package project.laundry.data.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel(value = "세탁소 등록 폼")
@Getter @Setter
public class businessForm {

    @ApiModelProperty(value = "세탁소 이름", example = "XX 세탁소", required = true)
    private String name;

    @ApiModelProperty(value = "세탁소 주소", example = "서울시 XX XX..", required = true)
    private String address;

    @ApiModelProperty(value = "세탁소 영업 시간", example = "07:00 ~ 19:00", required = true)
    private String bu_hour;

    @ApiModelProperty(value = "세탁소 전화번호", example = "010-232-3321", required = true)
    private String contact;

    @ApiModelProperty(value = "세탁소 소개글", example = "안녕하세요 XX 세탁소입니다.", required = true)
    private String intro;
}
