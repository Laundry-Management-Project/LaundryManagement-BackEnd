package project.laundry.data.dao.common;

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
public class businessDao {

    @ApiModelProperty(value = "사업장 고유 id", example = "0d45abec-134c-49ee-ac3c-09d7d211a21a", required = true)
    private String id;

    @ApiModelProperty(value = "사업장 이름", example = "XX 세탁소", required = true)
    private String name;

    @ApiModelProperty(value = "사업장 주소", example = "서울시 XX XX..", required = true)
    private String address;

    @ApiModelProperty(value = "사업장 영업 시간", example = "07:00 ~ 19:00", required = true)
    private String bu_hour;

//    @ApiModelProperty(value = "응답 메시지", example = "올바르지 않은 요청입니다.", required = true)
//    private String message;
}
