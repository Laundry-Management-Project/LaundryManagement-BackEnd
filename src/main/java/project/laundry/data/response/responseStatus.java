package project.laundry.data.response;

import io.swagger.annotations.ApiModel;
import lombok.*;

@ApiModel(value = "응답 메세지")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public abstract class responseStatus {

    private String message;

    private Boolean status;
}
