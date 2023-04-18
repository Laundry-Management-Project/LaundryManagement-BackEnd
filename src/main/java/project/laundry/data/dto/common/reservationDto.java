package project.laundry.data.dao.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@ApiModel(value = "예약 응답 데이터")
@Getter @Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class reservationDao {

    @ApiModelProperty(value = "예약 고유 id", example = "0d45abec-134c-49ee-ac3c-09d7d211a21a", required = true)
    private String id;

    @ApiModelProperty(value = "예약되어 있는 사업장(세탁소) 고유 id", example = "333ae2fb-0631-420b-9c29-5e8eb59a8f54", required = true)
    private String bu_id;

    @ApiModelProperty(value = "예약 신청을 한 손님 이름", example = "홍길동, test", required = true)
    private String cu_name;

    @ApiModelProperty(value = "사업장(세탁소) 이름", example = "XX 세탁소", required = true)
    private String bu_name;

    @ApiModelProperty(value = "사업장(세탁소) 주소", example = "서울시 XX XX", required = true)
    private String bu_address;

    @ApiModelProperty(value = "세탁물 개수", example = "3", required = true)
    private String clothCount;

    @ApiModelProperty(value = "세탁 상태", example = "세탁 전", required = true)
    private String clothStatus;

    @ApiModelProperty(value = "추가 내용", example = "세탁 전 옷 안에 내용물 확인해주세요.")
    private String content;

    @ApiModelProperty(value = "예약 생성 날짜", example = "2023/04/09 15:06:17", required = true)
    private String createdAt;
}
