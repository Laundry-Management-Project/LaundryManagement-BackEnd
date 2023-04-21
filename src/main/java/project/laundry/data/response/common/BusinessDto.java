package project.laundry.data.response.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@ApiModel(value = "사업장 응답 데이터")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BusinessDto {

    @ApiModelProperty(value = "세탁소 고유 id", example = "0d45abec-134c-49ee-ac3c-09d7d211a21a", required = true)
    private String bu_id;

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


//    @ApiModelProperty(value = "응답 메시지", example = "올바르지 않은 요청입니다.", required = true)
//    private String message;
}
