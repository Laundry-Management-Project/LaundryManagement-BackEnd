package project.laundry.domain.dto.status;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public abstract class responseStatus {

    private String message;

    private Boolean status;

    private String uid;
}